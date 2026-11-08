package com.mod.mathsmod.add.Entity.Entity.NBoss;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityWoodGolem;
import com.mod.mathsmod.add.Entity.Entity.IOnShot;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.AttackSun;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.AttackThunder;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.AttackTree;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.AttackWater;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPoulopop extends EntityACBoss implements IOnShot
{
	
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS));
	List<BaseBossAttack> list = Lists.newArrayList();
	private static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityPoulopop.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TELEPORT_TIMER = EntityDataManager.<Integer>createKey(EntityPoulopop.class, DataSerializers.VARINT);
	
	private static final DataParameter<Integer> SPAWN_COOLDOWN = EntityDataManager.<Integer>createKey(EntityPoulopop.class, DataSerializers.VARINT);
	
	public EntityPoulopop(World worldIn) 
	{
		super(worldIn);
		this.setSize(1.9F, 2.5F);
		list.add(new AttackTree());
		list.add(new AttackSun());
		list.add(new AttackWater());
		list.add(new AttackThunder());
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
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, null));
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ID, -1);
        this.dataManager.register(TELEPORT_TIMER, 0);
        this.dataManager.register(SPAWN_COOLDOWN, 60);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.205D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2000.0D);
    }
    
	@Override
	public List<BaseBossAttack> getAttacksList() 
	{
		return list;
	}

	public void setTargetID(int id)
	{
		this.dataManager.set(ID, id);
	}
	
	public int getTargetID()
	{
		return this.dataManager.get(ID);
	}

	public void setSpawnCooldown(int id)
	{
		this.dataManager.set(SPAWN_COOLDOWN, id);
	}
	
	public int getSpawnCooldown()
	{
		return this.dataManager.get(SPAWN_COOLDOWN);
	}
	
	public void setTeleportTimer(int time)
	{
		this.dataManager.set(TELEPORT_TIMER, time);
	}
	
	public int getTeleportTimer()
	{
		return this.dataManager.get(TELEPORT_TIMER);
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
	
	public void onUpdate()
	{
		Mathsmod.LOGGER.info("fdp : " + this.getHealth());
		if(this.getSpawnCooldown() > 0)
		{
			this.setSpawnCooldown(this.getSpawnCooldown() - 1);
		}
		else
		{
			super.onUpdate();
			
			if(this.getHealth() > 0)
			{
				this.heal(0.1F);
				if(this.getTarget() != null)
				{
					this.setTargetID(this.getTarget().getEntityId());
					
					if(this.getTarget().getDistance(this) > 30)
					{
						if(this.getTeleportTimer() == 0)
						{
							this.setTeleportTimer(1);
						}
						
						if(this.getTeleportTimer() >= 60)
						{
							if(!world.isRemote)
							{
								this.setPosition(this.getTarget().posX, this.getTarget().posY + 4, this.getTarget().posZ);
							}
							
							this.setTeleportTimer(0);
						}
					}
					
					if(this.getTeleportTimer() > 0)
					{
						this.setTeleportTimer(this.getTeleportTimer() + 1);
					}
				}
				
				if(this.getAttackTarget() != null && this.getAttackTarget().getEntityId() != this.getTargetID())
				{
					this.setTargetID(this.getAttackTarget().getEntityId());
				}
			}
			
			if(this.getHealth() > 0 && this.getHealth() < this.getMaxHealth() * 0.6 && this.getTarget() != null)
			{
				if(this.ticksExisted % 300 == 0 && !world.isRemote)
				{
					Vec3d vec = UtilsVoids.laser(2, this.rotationYaw, this.rotationPitch).add(this.getPositionEyes(1.0F));
					EntityWoodGolem entity = new EntityWoodGolem(this.world);
					entity.setAttackTarget(this.getAttackTarget());
					entity.setPosition(vec.x, vec.y, vec.z);
					world.spawnEntity(entity);
				}
			}
			bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}
	}
	
	public void travel(float strafe, float vertical, float forward)
	{
		if(this.getUsedAttack() == "object.poulopopAttack.sun")
		{
			super.travel(0, vertical, 0);
		}
		else if(this.getUsedAttack() == "object.poulopopAttack.roots")
		{
			super.travel(0, vertical, 0);
		}
		else if(this.getUsedAttack() == "object.poulopopAttack.water")
		{
			super.travel(0, vertical, 0);
		}
		else
		{
			super.travel(strafe, vertical, forward);
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
	
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(this.getSpawnCooldown() <= 0 && (source.getTrueSource() != null || source == DamageSource.OUT_OF_WORLD))
        {
        	return super.attackEntityFrom(source, amount);
        }
        else
        {
        	return false;
        }
    }
    

	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("spawn_cooldown", this.getSpawnCooldown());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setSpawnCooldown(compound.getInteger("spawn_cooldown"));
    }
    
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
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
                this.dropItem(RegisterItems.SIROJUS_KEY, 1).motionY = 0.3F;
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

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
	}
}
