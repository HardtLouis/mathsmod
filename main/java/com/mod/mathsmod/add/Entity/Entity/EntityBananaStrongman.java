package com.mod.mathsmod.add.Entity.Entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBananaStrongman extends EntityMob
{
	private Vec3d base;
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS));

    public EntityBananaStrongman(World worldIn)
    {
        super(worldIn);
        this.setSize(1.6F, 4F);
        this.isImmuneToFire = true;
    }

	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }
    
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    protected void initEntityAI()
    {
    	this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, null));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.235D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(750.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return -0.6D;
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            this.motionY = 1.2D;
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 1));
        }

        return flag;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityGolem)
        {
        	return super.attackEntityFrom(source, amount);
        }
        else
        {
        	return super.attackEntityFrom(source, amount * 0.3F);
        }
    }
    
    public void onUpdate()
    {
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		if(this.getAttackTarget() != null)
		{
			if(this.getRNG().nextInt(200) == 0 && !world.isRemote && this.getAttackTarget().posY >= base.y)
			{
				for(int i = 0; i < 5; ++i)
				{
					EntityMininana ent = new EntityMininana(world);
					ent.setPositionAndRotation(this.posX, this.posY, this.posZ, 0, this.rotationPitch);
					world.spawnEntity(ent);
				}
			}
		}
		
		if(this.base != null)
		{
			if(this.base.distanceTo(this.getPositionVector()) > 3 && !this.getMoveHelper().isUpdating())
			{
				this.getMoveHelper().setMoveTo(this.base.x, this.base.y, this.base.z, 0.5);
				
				if(this.base.distanceTo(this.getPositionVector()) > 100)
    			{
    				this.setPosition(this.base.x, this.base.y, this.base.z);
    			}
			}
			
			if(this.posY < this.base.y - 10)
			{
				this.noClip = true;
				this.motionY = 3;
			}
			else
			{
				this.noClip = false;
			}
			
			if(this.posY > this.base.y + 20)
			{
				Vec3d jmenbalec = this.getPositionVector().subtract(this.base).normalize();
				this.motionX = jmenbalec.x * -1;
				this.motionZ = jmenbalec.z * -1;
			}
		}
    	super.onUpdate();
    }
    
    protected boolean canDespawn()
    {
        return false;
    }

    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        this.dropItem(RegisterItems.BUFFED_BANANA, 1);
    }
    
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    	this.base = this.getPositionVector();
    	return super.onInitialSpawn(difficulty, livingdata);
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        
        if(this.base != null)
        {
        	compound.setDouble("base_x", base.x);
        	compound.setDouble("base_y", base.y);
        	compound.setDouble("base_z", base.z);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        if(compound.hasKey("base_x") && compound.hasKey("base_y") && compound.hasKey("base_z"))
        {
        	double x = compound.getDouble("base_x");
        	double y = compound.getDouble("base_y");
        	double z = compound.getDouble("base_z");
        	this.base = new Vec3d(x, y, z);
        }
    }
}
