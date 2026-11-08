package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntitySBossCrab extends EntityMob implements FrostResistance, IOnShot
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	private boolean isCharging;
	protected static final DataParameter<Integer> RIGHT_P = EntityDataManager.<Integer>createKey(EntitySBossCrab.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> LEFT_P = EntityDataManager.<Integer>createKey(EntitySBossCrab.class, DataSerializers.VARINT);
	private int timer;
	
	public EntitySBossCrab(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(3.5F, 2F);
        this.isImmuneToFire = true;
        this.experienceValue = 300;
        this.isImmuneToExplosions();
        this.setLeftHanded(false);
    }

	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(RIGHT_P, 0);
        this.dataManager.register(LEFT_P, 0);
    }
	
	public int leftP()
	{
		return this.dataManager.get(LEFT_P);
	}
	
	public int rightP()
	{
		return this.dataManager.get(RIGHT_P);
	}
	
	public void setLeftP(int i)
	{
		this.dataManager.set(LEFT_P, i);
	}
	
	public void setRightP(int i)
	{
		this.dataManager.set(RIGHT_P, i);
	}
	
	public int maxRAttack()
	{
		return 30;
	}
	
	public int maxLAttack()
	{
		return 40;
	}
	
	public float maxRAttackProgress()
	{
		return ((float) this.rightP()) / ((float)this.maxRAttack());
	}
	
	public float maxLAttackProgress()
	{
		return ((float) this.leftP()) / ((float)this.maxLAttack());
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
    	int r = rand.nextInt(100);
    	int c = rand.nextInt(100);
    	
    	if(this.isLeftHanded())
    	{
    		this.setLeftHanded(false);
    	}
    	
    	if(this.getHealth() > 0)
    	{
    		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    		
    		this.heal(0.2F);
    		
    		if(this.getAttackTarget() != null)
        	{
    			if(r == 0 && this.rightP() == 0)
    			{
    				this.setRightP(1);
    			}
    			if(c == 0 && this.leftP() == 0)
    			{
    				this.setLeftP(1);
    			}
    			
    			if(this.rightP() > 0)
    			{
    				if(this.rightP() >= this.maxRAttack())
    				{
    					this.setRightP(0);
    				}
    				else
    				{
    					this.setRightP(this.rightP() + 1);
    				}
    				
    				if(this.rightP() == this.maxRAttack() / 2)
    				{
    					if(this.getAttackTarget().getDistance(this) < 5)
    					{
    						this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), 10);
    					}
    				}
    			}
    			
    			if(this.leftP() > 0)
    			{
    				if(this.leftP() >= this.maxLAttack())
    				{
    					for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 10))
    		    		{
    		    			if(entity != this && entity.onGround)
    		    			{
    		    				entity.motionY = 2;
    		        			UtilsVoids.attackEntityFrom(entity, DamageSource.causeMobDamage(this), 8);
    		    			}
    		    		}
    					
    					
    					for(int i = -7; i <= 7; ++i)
    		    		{
    		    			for(int j = -7; j <= 7; ++j)
    		        		{
    		    				BlockPos b = new BlockPos(this).add(i, 0, j);
    		    				BlockPos pos = b.add(0, world.getHeight(b.getX(), b.getZ()) - b.getY() - 1, 0);
    		        			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5D, pos.getZ() + 0.5D, this) < 7 && (i != 0 || j != 0))
    		        			{
    		        				IBlockState state = world.getBlockState(pos);
    		        				if(state.getBlockHardness(world, pos) <= 10 && state.getBlockHardness(world, pos) >= 0
    		        						&& world.getBlockState(pos.down()).isFullCube())
    		        				{
    		        					world.setBlockToAir(pos);
    		        					EntityAbsorbedBlock entity = new EntityAbsorbedBlock(world, state, pos);
    		        					entity.posY += 0.2F;
    		        					entity.motionY = 0.05F * UtilsVoids.distanceIgnoreY(entity.posX, entity.posZ, this);
    		        					if(!world.isRemote)
    		        					{
    		            					world.spawnEntity(entity);
    		        					}
    		        				}
    		        			}
    		        		}
    		    		}
    					this.setLeftP(0);
    				}
    				else
    				{
        				this.setLeftP(this.leftP() + 1);
    				}
    			}
    			
        	}
    		else if(!world.isRemote)
    		{
    			this.setLeftP(0);
    			this.setRightP(0);
    		}
    		//this.setAttackTarget(world.getNearestAttackablePlayer(this, 30, 100));
    	}
    	
    	super.onUpdate();
    	
    }
    
    public void addPotionEffect(PotionEffect potioneffectIn)
    {
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0D);
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
        if(this.deathTime >= 200)
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
            	world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.CRAB_ARENA_KEY)));
            	world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.SLICER)));
            	world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.CRUSHER)));
            }
            
            this.setDead();
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
    		super.attackEntityFrom(source, amount * 2);
    	}
    	if(source instanceof EntityDamageSource && !source.isProjectile())
    	{
    		return super.attackEntityFrom(source, amount);
    	}
    	return false;
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
		this.attackEntityFrom(MDamageSource.causeThrownDamage(shooter), amount);
	}
    
}