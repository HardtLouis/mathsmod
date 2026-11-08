package com.mod.mathsmod.add.Entity.Entity;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityDestroyer extends EntityMob
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	private boolean isCharging;
	private int timer;
	float chargingPitch;
	float chargingYaw;
	
	public EntityDestroyer(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(0.8F, 2F);
        this.isImmuneToFire = true;
        this.experienceValue = 300;
        this.isImmuneToExplosions();
        this.setLeftHanded(false);
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
    	
    	if(this.getHealth() > 0)
    	{
    		
    		if(this.getRNG().nextInt(50) == 0)
    		{
    			this.teleportRandomly();
    		}
    		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    		
    		this.heal(0.2F);
    		
    		if(this.getAttackTarget() != null)
        	{
        		
        		if(c == 0)
            	{
            		this.isCharging = true;
            	}
        		
        		if(this.isCharging)
        		{
        			if(timer < 100)
            		{
            			this.travel(0, 0, 1);
            			this.rotationPitch = this.chargingPitch;
            			this.rotationYaw = this.chargingYaw;
            			timer += 1;
            		}
            		if(timer >= 100)
            		{
            			timer = 0;
            			this.isCharging = false;
            		}
        		}
        	}
    		
    		this.setAttackTarget(world.getNearestAttackablePlayer(this, 30, 100));
    	}
    	if(!this.isCharging)
    	{
    		this.chargingPitch = this.rotationPitch;
    		this.chargingYaw = this.rotationYaw;
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3500000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.1D);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
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
    	if(this.deathTime >= 498)
    	{
    		for(Entity entity : UtilsVoids.getEntities(EntityAbsorbedBlock.class, world, this, 10))
        	{
        		if(entity != null && entity != this)
        		{
        			entity.posY = this.posY + 2;
        			entity.setNoGravity(false);
        			UtilsVoids.AttractEntity(this, entity, 10, -2);
        		}
        	}
    	}
        if(this.deathTime >= 500)
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
            	world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.DSWORD)));
            	world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.COPRAVEG_KEY)));
            }
            
            this.setDead();
        }
        else
        {
        	
        	for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 40))
        	{
        		if(entity != this && entity != null)
        		{
        			if(!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).capabilities.isCreativeMode)
        			{
        				UtilsVoids.AttractEntity(this, entity, 40, 0.1D * this.deathTime / 100);
        				if(entity.getDistance(this) < 2)
        				{
        					entity.attackEntityFrom(DamageSource.causeMobDamage(this), this.deathTime / 25);
        				}
        			}
        		}
        	}
        	for(Entity entity : UtilsVoids.getEntities(EntityAbsorbedBlock.class, world, this, 10))
        	{
        		if(entity != null && entity != this && entity.getDistance(this) > 3)
        		{
        			UtilsVoids.AttractEntity(this, entity, 10, 0.1);
        			if(entity.ticksExisted >= 40)
        			{
        				entity.setDead();
        			}
        		}
        	}
        	for(int x = -10; x <= 10; ++x)
        	{
        		for(int y = -10; y <= 10; ++y)
            	{
        			for(int z = -10; z <= 10; ++z)
                	{
        				BlockPos pos = new BlockPos(this).add(x, y, z);
                		if(this.getDistanceSq(pos) <= 100 && world.getBlockState(pos).getBlock().getBlockHardness(world.getBlockState(pos), world, pos) >= 0 && world.getBlockState(pos).getBlock() != null)
                		{
                			if(this.getRNG().nextInt((int) (world.getBlockState(pos).getBlock().getBlockHardness(world.getBlockState(pos), world, pos) * 100 + 2)) == 0
                					&& world.getBlockState(pos).getBlock().getMaterial(world.getBlockState(pos)) != Material.AIR)
                			{
                				if(!world.isRemote)
                				{
                					EntityAbsorbedBlock block = new EntityAbsorbedBlock(world, world.getBlockState(pos), pos);
                    				world.setBlockToAir(pos);
                    				world.spawnEntity(block);
                				}
                			}
                		}
                		if(this.getDistanceSq(pos) <= 16 && world.getBlockState(pos).getBlock().getBlockHardness(world.getBlockState(pos), world, pos) >= 0 && world.getBlockState(pos).getBlock().getBlockHardness(world.getBlockState(pos), world, pos) < 3
                				&& world.getBlockState(pos).getBlock().getMaterial(world.getBlockState(pos)) != Material.AIR)
                		{
                			if(!world.isRemote)
            				{
            					EntityAbsorbedBlock block = new EntityAbsorbedBlock(world, world.getBlockState(pos), pos);
                				world.setBlockToAir(pos);
                				world.spawnEntity(block);
            				}
                		}
                	}
            	}
        	}
        	float i = 500.0F - this.deathTime;
        	this.bossInfo.setColor(BossInfo.Color.WHITE);
        	this.bossInfo.setPercent(i / 500);
        	this.bossInfo.setName(new TextComponentString("Death"));
        }
    }

    protected void collideWithEntity(Entity entityIn)
    {
    	if(this.isCharging)
    	{
    		entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 24);
    	}
    	
        super.collideWithEntity(entityIn);
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source == DamageSource.OUT_OF_WORLD)
    	{
    		super.attackEntityFrom(source, amount * 20);
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
    
}
