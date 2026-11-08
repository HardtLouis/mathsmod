package com.mod.mathsmod.add.Entity.Entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGlider;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAIAttackMeleeWater;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAINearestAttackableTargetWater;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAiHurtByTargetWatermob;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityShark extends AbstractWaterMonster
{
    public float attackTimer;
    //protected static final DataParameter<BlockPos> MOVE_TO = EntityDataManager.<BlockPos>createKey(EntityShark.class, DataSerializers.BLOCK_POS);
    private boolean tailR;
    public float tail;
    
    private int changeDirection;

	public EntityShark(World worldIn)
    {
        super(worldIn);
        this.setSize(3, 1);
        this.experienceValue = 40;
        //this.moveHelper = new AbstractWaterMonster.WaterMoveHelper(this);
    }
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		//this.dataManager.register(MOVE_TO, new BlockPos(0, 0, 0));
	}
    
    protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityAIAttackMeleeWater(this, 1.0D, false));
        this.tasks.addTask(1, new EntityShark.AIRandomSwim(this));
        this.targetTasks.addTask(1, new EntityAiHurtByTargetWatermob(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTargetWater(this, EntityLiving.class, 0, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityShark) && p_apply_1_.isInWater();
            }
        }));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTargetWater(this, EntitySquid.class, 0, true, true, null));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTargetWater(this, EntityPlayer.class, 0, false, true, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_.getHealth() < p_apply_1_.getMaxHealth() && p_apply_1_.isInWater();
            }
        }));
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
    }
    
    /*protected BlockPos getMoveTo()
    {
    	//return this.dataManager.get(MOVE_TO);
    }
    
    protected void setMoveTo(BlockPos pos, double speedIn)
    {
    	this.moveHelper.setMoveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, speedIn);
    	//this.dataManager.set(MOVE_TO, pos);
    }*/

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return -1;
    }
    
    public void onUpdate()
    {
    	
    	if(tail == -10 || tail == 10)
    	{
    		tailR = !tailR;
    	}
    	if (this.attackTimer > 0)
        {
            --this.attackTimer;
        }
    	if(tailR && tail < 10)
    	{
    		tail += 0.5F;
    	}
    	if(!tailR && tail > -10)
    	{
    		tail -= 0.5F;
    	}
    	
    	if(this.attackTimer > 0)
    	{
    		attackTimer -= 0.5F;
    	}
    	
    	super.onUpdate();
    	
		if(this.isInWater())
		{
			if(this.getAttackTarget() != null)
			{
				
			}
			else
			{
				if(this.getMoveHelper().getY() > this.posY + 2)
				{
					this.motionY = 0.3D;
				}
				else if(this.getMoveHelper().getY() + this.getEyeHeight() + 1 < this.posY)
				{
					this.motionY = -0.3D;
				}
				else
				{
					this.motionY = 0D;
				}
				
				//this.getLookHelper().setLookPosition(this.getMoveTo().getX() + 0.5D, this.getMoveTo().getY(), this.getMoveTo().getZ() + 0.5D, 0, 0);
				//this.setRotationYawHead(this.rotationYaw);
			}
			
			this.travel(0, 0, 1);
		}
		
		if(this.getRevengeTarget() != null)
		{
			this.setAttackTarget(this.getRevengeTarget());
		}
		
		if(this.getAttackTarget() == null)
		{
			this.getLookHelper().setLookPosition(this.getMoveHelper().getX(), this.getMoveHelper().getY(), this.getMoveHelper().getZ(), 0, 0);
		}
		
		
    	
    }
    
    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }
   
    static class sharkMoveHelper extends EntityMoveHelper
    {
        private final EntityShark parentEntity;
        private int courseChangeCooldown;

        public sharkMoveHelper(EntityShark shark)
        {
            super(shark);
            this.parentEntity = shark;
        }

        public void onUpdateMoveHelper()
        {
        	if(this.parentEntity.isInWater())
        	{
        		if(this.parentEntity.getAttackTarget() == null)
        		{
        			if (this.action == EntityMoveHelper.Action.MOVE_TO)
                    {
                        double d0 = this.posX - this.parentEntity.posX;
                        double d1 = this.posY - this.parentEntity.posY;
                        double d2 = this.posZ - this.parentEntity.posZ;
                        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                        if (this.courseChangeCooldown-- <= 0)
                        {
                            this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(12) + 40;
                            d3 = (double)MathHelper.sqrt(d3);

                            if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
                            {
                                this.parentEntity.motionX += d0 / d3 * 0.7D;
                                this.parentEntity.motionY += d1 / d3 * 0.7D;
                                this.parentEntity.motionZ += d2 / d3 * 0.7D;
                            }
                            else
                            {
                                this.action = EntityMoveHelper.Action.WAIT;
                            }
                        }
                    }
        		}
        		else
        		{
                	super.onUpdateMoveHelper();
        		}
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
    
    class AIRandomSwim extends EntityAIBase
    {
        private final EntityShark parentEntity;

        public AIRandomSwim(EntityShark shark)
        {
            this.parentEntity = shark;
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();

            if(this.parentEntity.getAttackTarget() == null)
            {
            	if (!entitymovehelper.isUpdating())
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
            else
            {
            	return false;
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
        	if(this.parentEntity.getRNG().nextInt(20) == 0 || this.parentEntity.getDistance(this.parentEntity.getMoveHelper().getX(), this.parentEntity.getMoveHelper().getY(), this.parentEntity.getMoveHelper().getZ()) < 2)
        	{
        		Random random = this.parentEntity.getRNG();
                double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 32.0F);
                double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 4.0F);
                double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 32.0F);
                while(this.parentEntity.world.getBlockState(new BlockPos(d0, d1, d2)).getMaterial() != Material.WATER && d1 > 1)
                {
                	d1 -= 1;
                }
                //this.parentEntity.setMoveTo(new BlockPos(d0, d1, d2), 1.0D);
                this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        	}
        }
    }
    
    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	if(!world.isRemote && this.getRNG().nextInt(5) == 0)
		{
			EntityItem entityitem = this.dropItem(RegisterItems.SHARK_TOOTH, 1);
		}
    }
}