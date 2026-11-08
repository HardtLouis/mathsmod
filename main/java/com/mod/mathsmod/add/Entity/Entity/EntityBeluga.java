package com.mod.mathsmod.add.Entity.Entity;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityBeluga extends EntityWaterMob
{
    private static final DataParameter<Integer> ATTACKING = EntityDataManager.<Integer>createKey(EntityBeluga.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityBeluga.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityBeluga.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityTameable.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    
    boolean isFollowing;
    int interacCooldown;
    public int cooldown;

	public EntityBeluga(World worldIn)
	{
		super(worldIn);
		this.setSize(2.0F, 1.0F);
		this.moveHelper = new EntityBeluga.BelugaMoveHelper(this);
		this.setPathPriority(PathNodeType.WATER, 1.0F);
	}
	
	protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityBeluga.AIRandomSwim(this));
        this.tasks.addTask(3, new EntityBeluga.AILookAround(this));
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ATTACKING, 0);
        this.dataManager.register(TAMED, false);
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(SADDLED, false);
    }

    @Nullable
    public UUID getOwnerId()
    {
        return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }

    public void setOwnerId(@Nullable UUID p_184754_1_)
    {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
    }

    public void setTamedBy(EntityPlayer player)
    {
    	this.setTamed(true);
        this.setOwnerId(player.getUniqueID());
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if (this.getOwnerId() == null)
        {
            compound.setString("OwnerUUID", "");
        }
        else
        {
            compound.setString("OwnerUUID", this.getOwnerId().toString());
        }

        compound.setBoolean("Following", this.isFollowing);
        compound.setBoolean("Tamed", this.isTamed());
        compound.setBoolean("Saddled", this.isSaddled());
    }
    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        String s;

        if (compound.hasKey("OwnerUUID", 8))
        {
            s = compound.getString("OwnerUUID");
        }
        else
        {
            String s1 = compound.getString("Owner");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }

        if (!s.isEmpty())
        {	
        	this.setOwnerId(UUID.fromString(s));
        }

        this.isFollowing = compound.getBoolean("Following");
        this.setTamed(compound.getBoolean("Tamed"));
        this.setSaddled(compound.getBoolean("Saddled"));
    }

    @Nullable
    public EntityLivingBase getOwner()
    {
        try
        {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : this.world.getPlayerEntityByUUID(uuid);
        }
        catch (IllegalArgumentException var2)
        {
            return null;
        }
    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if(this.getOwner() != null && !world.isRemote)
    	{
        	if(this.getOwner() == player && this.interacCooldown <= 0)
        	{
        		if(this.getOwner().getHeldItemMainhand().getItem() == RegisterItems.BELUGA_SADDLE && !this.isSaddled())
        		{
        			this.getOwner().getHeldItemMainhand().shrink(1);
        			this.setSaddled(true);
        		}
        		else
        		{
        			if(!this.getOwner().isSneaking() && this.isSaddled())
        			{
        				player.startRiding(this);
        			}
        			else
        			{
        				if(player.getHeldItemMainhand().getItem() == Items.NAME_TAG)
        				{
        					player.getHeldItemMainhand().interactWithEntity(player, this, hand);
        				}
            			this.isFollowing = !this.isFollowing;
                		this.interacCooldown = 10;
                		if(this.isFollowing)
                		{
                			player.sendStatusMessage(new TextComponentString("this beluga follows you"), true);
                		}
                		else
                		{
                			player.sendStatusMessage(new TextComponentString("this beluga don't follows you now"), true);
                		}
        			}
        		}
        	}
    	}
    	else if(!this.isTamed() && player.getHeldItemMainhand().getItem() == Items.FISH)
    	{
    		if(this.getRNG().nextInt(10) == 0)
    		{
        		this.setTamedBy(player);
        		for(int i = 0; i < 5;++i)
        		{
        			world.spawnParticle(EnumParticleTypes.HEART, this.posX, this.posY, this.posZ, 0, 0.1, 0);
        		}
    		}
    		player.getHeldItemMainhand().shrink(1);
    	}
    	return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    }
	
	public int isAttacking()
    {
        return this.dataManager.get(ATTACKING);
    }

    public void setAttacking(int attacking)
    {
        this.dataManager.set(ATTACKING, attacking);
    }
    
    public boolean isSaddled()
    {
        return this.dataManager.get(SADDLED);
    }

    public void setSaddled(boolean tamer)
    {
        this.dataManager.set(SADDLED, tamer);
    }
    
    public boolean isTamed()
    {
        return this.dataManager.get(TAMED);
    }

    public void setTamed(boolean tamed)
    {
        this.dataManager.set(TAMED, tamed);
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.isAttacking() > 0)
    	{
    		this.setAttacking(this.isAttacking() - 1);
    		Vec3d vec = UtilsVoids.laser(1.3F, this.rotationYaw, this.rotationPitch);
    		if(this.inWater)
    		{
    			this.motionX = vec.x;
    			this.motionY = vec.y;
    			this.motionZ = vec.z;
    		}
    		
    		for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()))
    		{
    			if(!this.isPassenger(entity) && !world.isRemote)
    			{
    				entity.attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), 10);
    				this.setAttacking(0);
    				Vec3d vec1 = UtilsVoids.laser(0.4F, this.rotationYaw, this.rotationPitch);
    				entity.addVelocity(vec1.x, vec1.y, vec1.z);
    				if(entity instanceof EntityPlayerMP)
    				{
    					Mathsmod.network.sendTo(new MessageModifyVelocity(vec1), (EntityPlayerMP) entity);
    				}
    			}
    		}
    		
    		if(this.isAttacking() == 0)
    		{
    			cooldown = 300;
    		}
    	}
    	
    	if(this.isFollowing && this.getPassengers().isEmpty() && this.getOwner() != null)
    	{
    		this.getLookHelper().setLookPositionWithEntity(this.getOwner(), 0, 0);
    		this.moveHelper.setMoveTo(this.getOwner().posX, this.getOwner().posY, this.getOwner().posZ, 0.5F);
    	}
    	
    	if(cooldown > 0)
    	{
    		--cooldown;
    	}
    	
    	if(interacCooldown > 0)
    	{
    		--interacCooldown;
    	}
    }
    
    public boolean canBePushed()
    {
        return !this.isBeingRidden();
    }
    
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }

    protected float getWaterSlowDown()
    {
        return 0.997F;
    }
    
    public void travel(float strafe, float vertical, float forward)
    {
    	if(this.inWater)
    	{
    		if(!this.getPassengers().isEmpty() && this.getPassengers().get(0) == this.getOwner())
    		{
    			this.rotationYaw = this.getPassengers().get(0).rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = this.getPassengers().get(0).rotationPitch;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.motionY = -this.rotationPitch / 90 * 0.3F;
                this.motionX *= 0.95;
                this.motionZ *= 0.95;
    			super.travel(0, 0, this.getOwner().moveForward);
    		}
    		else
    		{
    			super.travel(strafe, vertical, forward);
    		}
    		
    	}
    	else
    	{
    		super.travel(strafe, vertical, forward);
    	}
    }
    
    public boolean hasNoGravity()
    {
        return super.hasNoGravity() || this.isInWater();
    }

	static class AIRandomSwim extends EntityAIBase
    {
        private final EntityBeluga parentEntity;

        public AIRandomSwim(EntityBeluga beluga)
        {
            this.parentEntity = beluga;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();
            if(!this.parentEntity.inWater)
            {
            	return false;
            }
            else if (!entitymovehelper.isUpdating())
            {
                return true;
            }
            else
            {
                double d0 = entitymovehelper.getX() - this.parentEntity.posX;
                double d1 = entitymovehelper.getY() - this.parentEntity.posY;
                double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
        	if(this.parentEntity.getOwner() == null || !this.parentEntity.isFollowing)
        	{
        		Random random = this.parentEntity.getRNG();
                double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 32.0F);
                double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 6.0F);
                double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 32.0F);

                BlockPos pos = new BlockPos(d0,d1,d2);
                int i = 0;
                while(i < 10 && this.parentEntity.world.getBlockState(pos).getMaterial() != Material.WATER)
                {
                	i += 1;
                    d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 32.0F);
                    d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 6.0F);
                    d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 32.0F);
                    pos = new BlockPos(d0,d1,d2);
                }
            	
                this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        	}
        	else
        	{
        		Vec3d vec = this.parentEntity.getOwner().getPositionVector();
        		this.parentEntity.getMoveHelper().setMoveTo(vec.x, vec.y, vec.z, 1.0D);
        	}
        }
    }
	
	static class BelugaMoveHelper extends EntityMoveHelper
    {
        private final EntityBeluga parentEntity;
        private int courseChangeCooldown;

        public BelugaMoveHelper(EntityBeluga beluga)
        {
            super(beluga);
            this.parentEntity = beluga;
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && this.parentEntity.getPassengers().isEmpty())
            {
                double d0 = this.posX - this.parentEntity.posX;
                double d1 = this.posY - this.parentEntity.posY;
                double d2 = this.posZ - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0)
                {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = (double)MathHelper.sqrt(d3);

                    if (this.parentEntity.isInWater())
                    {
                        this.parentEntity.motionX = d0 / d3 * 0.3D;
                        this.parentEntity.motionY = d1 / d3 * 0.3D;
                        this.parentEntity.motionZ = d2 / d3 * 0.3D;
                    }
                    else
                    {
                        this.action = EntityMoveHelper.Action.WAIT;
                    }
                }
            }
            else
            {
            	super.onUpdateMoveHelper();
            }
        }

        /**
         * Checks if entity bounding box is not colliding with terrain
         */
        private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
        {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double)i < p_179926_7_; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }
	
	static class AILookAround extends EntityAIBase
    {
        private final EntityBeluga parentEntity;

        public AILookAround(EntityBeluga beluga)
        {
            this.parentEntity = beluga;
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            if (this.parentEntity.getAttackTarget() == null)
            {
                this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float)Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            }
            else
            {
                EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D)
                {
                    double d1 = entitylivingbase.posX - this.parentEntity.posX;
                    double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }
        }
    }
	
	protected boolean canDespawn()
    {
        return !this.isTamed();
    }
}
