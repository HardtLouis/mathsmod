package com.mod.mathsmod.add.Entity.Entity.NBoss;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks.ChauvoputChargeAttack;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks.ChauvoputEarthquakeAttack;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks.ChauvoputLavaAttack;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks.ChauvoputSpikes;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks.FireBallAttack;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityNChauvoput extends EntityACBoss
{
	
	private static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityNChauvoput.class, DataSerializers.VARINT);
	List<BaseBossAttack> list = Lists.newArrayList();
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS));
	private float attackYaw;
	private boolean isJumpingOnPlayer;
	
	public EntityNChauvoput(World worldIn) 
	{
		super(worldIn);
		this.setSize(2, 5.2F);
		this.experienceValue = 300;
		this.isImmuneToFire = true;
		list.add(new FireBallAttack());
		list.add(new ChauvoputLavaAttack());
		list.add(new ChauvoputEarthquakeAttack());
		list.add(new ChauvoputChargeAttack());
		list.add(new ChauvoputSpikes());
	}
	
	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
	@Override
    protected void initEntityAI()
    {
    	super.initEntityAI();
    	this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false)
        		{
        			protected double getAttackReachSqr(EntityLivingBase attackTarget)
        			{
        				return (double)(this.attacker.width * 1.2F * this.attacker.width * 1.2F + attackTarget.width);
        			}
        		});
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, null));
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ID, -1);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1200.0D);
    }

	@Override
	public List<BaseBossAttack> getAttacksList()
	{
		return list;
	}
	
	public void onUpdate()
	{
		/*if(this.isWet())
		{
			if(this.getHealth() > 1.0)
			{
				this.setHealth(this.getHealth() - 1F);
			}
		}*/
		
		if(this.getAttackTarget() != null)
		{
			this.setTargetID(this.getAttackTarget().getEntityId());
		}
		else
		{
			this.setTargetID(-1);
		}
		
		if(this.isBurning() || this.isInLava())
		{
			this.heal(1);
		}
		
		if(this.hurtTime == this.maxHurtTime)
		{
			this.destroyBlocks(world);
		}
		
		if((this.isPotionActive(RegisterPotions.CHARGE) && this.getTarget() != null) || (this.getTarget() != null && this.getTarget().getDistance(this) > 40))
		{
			double disX = this.getTarget().posX - this.posX;
			double disZ = this.getTarget().posZ - this.posZ;
			double dis = Math.max(10, MathHelper.sqrt(disX * disX + disZ * disZ));
			
			this.motionX = disX  / dis * 0.6F;
			this.motionZ = disZ / dis * 0.6F;
			this.destroyBlocks(world);
			this.rotationYaw = this.prevRotationYaw;
			
			if(this.getTarget().posY > this.posY + 8 && this.onGround)
			{
				this.motionY = 1.5F;
			}
			
			for(Entity entity : world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox(), null))
			{
				if(entity instanceof EntityLivingBase)
				{
					entity.attackEntityFrom(DamageSource.causeMobDamage(this), 15);
					entity.addVelocity(disX  / dis * 0.6F, 0.2F, disZ  / dis * 0.6F);
				}
			}
			
			if(this.getTarget().getDistance(this) > 40)
			{
				this.addPotionEffect(new PotionEffect(RegisterPotions.CHARGE, 60));
			}
		}
		
		if(this.getTarget() != null && UtilsVoids.distanceIgnoreY(this.posX, this.posZ, this.getTarget()) < 30)
		{
			this.isJumpingOnPlayer = true;
		}
		
		if(this.isJumpingOnPlayer && this.getTarget() != null && UtilsVoids.distanceIgnoreY(this.posX, this.posZ, this.getTarget()) < 2)
		{
			this.isJumpingOnPlayer = false;
			Vec3d vec = UtilsVoids.laser(1, UtilsVoids.getYawFromPos(this.getTarget().getPositionVector(), this.posX, this.posZ), -10);
			this.addVelocity(vec.x, vec.y, vec.z);
		}
		
		if(this.getTarget() != null && this.getTarget().posY > this.posY + 10 && this.onGround)
		{
			this.motionY = 2;
		}
		
		if(this.motionY >= 0.6F)
		{

			this.destroyBlocks(world);
		}
		
		
		bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		
		super.onUpdate();
	}
	
	@Override
	public void fall(float distance, float damageMultiplier)
	{
		if(distance > 6)
		{
			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 20))
			{
				if(entity != this && entity.onGround)
				{
					entity.addVelocity(0, 2, 0);
					UtilsVoids.attackEntityFrom(entity, DamageSource.causeMobDamage(this), 40 - entity.getDistance(this) * 2);
				}
			}
			
			for(int i = -20; i <= 20; ++i)
			{
				for(int j = -20; j <= 20; ++j)
	    		{
					BlockPos b = new BlockPos(this).add(i, 0, j);
					BlockPos pos = b.add(0, world.getHeight(b.getX(), b.getZ()) - b.getY() - 1, 0);
	    			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5D, pos.getZ() + 0.5D, this) < 10 && (i != 0 || j != 0))
	    			{
	    				IBlockState state = world.getBlockState(pos);
	    				if(state.getBlockHardness(world, pos) <= 10 && state.getBlockHardness(world, pos) >= 0
	    						&& world.getBlockState(pos.down()).isFullCube())
	    				{
	    					EntityFallingBlock entity = new EntityFallingBlock(world, pos.getX() + 0.5F, pos.getY() + 0.1, pos.getZ() + 0.5F, world.getBlockState(pos));
	    					entity.posY += 0.2F;
	    					entity.motionY = 0.025F * UtilsVoids.distanceIgnoreY(entity.posX, entity.posZ, this);
	    					if(!world.isRemote)
	    					{
	        					world.spawnEntity(entity);
	    					}
	    				}
	    			}
	    		}
			}
		}
	}

    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
	
	public void addPotionEffect(PotionEffect potioneffectIn)
    {
		if(potioneffectIn.getPotion() == RegisterPotions.CHARGE)
		{
			super.addPotionEffect(potioneffectIn);
		}
    }

	public void setTargetID(int id)
	{
		this.dataManager.set(ID, id);
	}
	
	public int getTargetID()
	{
		return this.dataManager.get(ID);
	}
	
	@Nullable
	public Entity getTarget()
	{
		if(this.getTargetID() >= 0 && this.world.getEntityByID(this.getTargetID()) != null)
		{
			return this.world.getEntityByID(this.getTargetID());
		}
		else
		{
			return null;
		}
	}
	
	protected void destroyBlocks(World world)
	{
		if (this.isPotionActive(RegisterPotions.CHARGE) && this.getActivePotionEffect(RegisterPotions.CHARGE).getDuration() % 2 == 0)
        {
            int i1 = MathHelper.floor(this.posY);
            int l1 = MathHelper.floor(this.posX);
            int i2 = MathHelper.floor(this.posZ);
            boolean flag = false;

            for (int k2 = -3; k2 <= 3; ++k2)
            {
                for (int l2 = -3; l2 <= 3; ++l2)
                {
                    for (int j = 0; j <= 6; ++j)
                    {
                    	float i3 = l1 + k2;
                    	float l = i2 + l2;
                    	float k = i1 + j;
                        
                        BlockPos blockpos = new BlockPos(i3, k, l);
                        IBlockState iblockstate = world.getBlockState(blockpos);
                        Block block = iblockstate.getBlock();

                        if (block.getBlockHardness(iblockstate, world, blockpos) >= 0 && !block.isAir(iblockstate, world, blockpos) && this.canDestroyBlock(iblockstate, world, blockpos))
                        {
                            flag = world.destroyBlock(blockpos, false) || flag;
                            
                            if(this.getRNG().nextInt(100) == 0 && j == 0 && Blocks.FIRE.canPlaceBlockAt(world, blockpos))
                            {
                            	world.setBlockState(blockpos, Blocks.FLOWING_LAVA.getStateFromMeta(2));
                            }
                        }
                    }
                }
                if (flag)
	            {
	                world.playEvent((EntityPlayer)null, 1022, new BlockPos(this), 0);
	            }
            }
		}
	}

	private static boolean canDestroyBlock(IBlockState iblockstate, World world, BlockPos blockpos) 
	{
		return iblockstate.getBlock() != Blocks.CHEST && iblockstate.getBlock() != Blocks.TRAPPED_CHEST && world.getBlockState(blockpos).getBlockHardness(world, blockpos) >= 0 && world.getBlockState(blockpos).getBlockHardness(world, blockpos) < 10;
	}
	
	protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == 200)
        {
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")))
            {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }

            if(!this.world.isRemote)
            {
                this.dropItem(RegisterItems.EARTH_SWORD, 1).motionY = 0.7F;
            }
            
            this.setDead();

            for (int k = 0; k < 20; ++k)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("isJumpingOnTarget", this.isJumpingOnPlayer);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.isJumpingOnPlayer = compound.getBoolean("isJumpingOnTarget");
    }
}
