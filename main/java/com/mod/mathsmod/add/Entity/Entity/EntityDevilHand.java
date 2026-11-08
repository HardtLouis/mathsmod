package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAINearestAttackableTargetWater;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityDevilHand extends EntityMob implements IOnShot
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	
	public EntityDevilHand(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(2F, 4F);
        this.isImmuneToFire = true;
        this.experienceValue = 400;
        this.isImmuneToExplosions();
        this.setLeftHanded(false);
        this.setGlowing(true);
    }
	

    protected boolean canDespawn()
    {
        return false;
    }

	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_.isInWater();
            }
        }));
    }

	public boolean canBeCollidedWith()
    {
    	return this.deathTime == 0;
    }
	
    public void onUpdate()
    {
    	int i = rand.nextInt(100);
    	int c = rand.nextInt(100);
    	
    	if(this.isLeftHanded())
    	{
    		this.setLeftHanded(false);
    	}
    	
    	if(i == 0)
    	{
    		for(float a = 0; a < 10; a+= 0.25F)
    		{
    			Vec3d vec = UtilsVoids.laser(a, this.rotationYaw, this.rotationPitch).add(this.getPositionVector());
    			List<EntityLivingBase> list = UtilsVoids.getHitEntities(EntityLivingBase.class, world, this, vec.x, vec.y + this.getEyeHeight(), vec.z);
    			world.spawnParticle(EnumParticleTypes.PORTAL, vec.x, vec.y, vec.z, 0, 0, 0);
    			if(!list.isEmpty())
    			{
    				list.get(0).attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this), 8);
    			}
    		}
    	}
    	
    	if(this.getHealth() > 0)
    	{
    		
    		if(this.getRNG().nextInt(100) == 0)
    		{
    			this.teleportRandomly();
    		}
    		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    		
    		this.heal(0.2F);
    		
    		if(this.getAttackTarget() != null)
        	{
    			
        	}
    		
    		this.setAttackTarget(world.getNearestAttackablePlayer(this, 30, 100));
    	}
    	
    	super.onUpdate();
    	
    }
    

	@Override
    protected void entityInit()
    {
        super.entityInit();
    }
    
    public void addPotionEffect(PotionEffect potioneffectIn)
    {
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
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
    
    @Override
    protected void onDeathUpdate()
    {
    	this.deathTime += 1;
    	this.setGlowing(true);
    	if(this.deathTime >= 58)
    	{
    	}
        if(this.deathTime >= 60)
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
            if(!world.isRemote)
            {
            	world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.SKY_SWORD)));
            }
            
            this.setDead();
        }
        else
        {
        	float i = 60.0F - this.deathTime;
        	this.bossInfo.setColor(BossInfo.Color.WHITE);
        	this.bossInfo.setPercent(i / 60);
        	this.bossInfo.setName(new TextComponentString("Death"));
        }
    }

    protected void collideWithEntity(Entity entityIn)
    {
    	/*if(this.isCharging)
    	{
    		entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 24);
    	}*/
    	
        super.collideWithEntity(entityIn);
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source == DamageSource.OUT_OF_WORLD)
    	{
    		super.attackEntityFrom(source, amount * 2);
    	}
    	if(source instanceof EntityDamageSource && !source.isProjectile())
    	{
    		return super.attackEntityFrom(source, amount);
    	}
    	return false;
    }
    
    protected boolean teleportRandomly()
    {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 40.0D;
        double d1 = this.posY + (double)(this.rand.nextInt(32) - 16);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 40.0D;
        return this.attemptTeleport(d0, d1, d2);
    }
    
    public void move(MoverType type, double x, double y, double z)
    {
    	if(this.deathTime == 0)
    	{
    		super.move(type, x, y, z);
    	}
    }


	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
	}
    
}
