package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackHole;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.MDamageSource;

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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityDBoss extends EntityMob implements FrostResistance, IOnShot
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(false);
	protected static final DataParameter<Integer> SPAWN_TIME = EntityDataManager.<Integer>createKey(EntityDBoss.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> CREATING_BH = EntityDataManager.<Integer>createKey(EntityDBoss.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> BH_ON_PLAYER = EntityDataManager.<Integer>createKey(EntityDBoss.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> STOMP = EntityDataManager.<Integer>createKey(EntityDBoss.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> ROCKET = EntityDataManager.<Integer>createKey(EntityDBoss.class, DataSerializers.VARINT);
	
	float tPosX;
	float tPosY;
	float tPosZ;
	
	public EntityDBoss(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(2F, 3.5F);
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
        this.dataManager.register(CREATING_BH, 0);
        this.dataManager.register(BH_ON_PLAYER, 0);
        this.dataManager.register(STOMP, 0);
        this.dataManager.register(ROCKET, 0);
        this.dataManager.register(SPAWN_TIME, 0);
    }
	
	public int getSpawnTime()
	{
		return this.dataManager.get(SPAWN_TIME);
	}
	
	public void setSpawnTime(int i)
	{
		this.dataManager.set(SPAWN_TIME, i);
	}
	
	public int getARocket()
	{
		return this.dataManager.get(ROCKET);
	}
	
	public void setARocket(int i)
	{
		this.dataManager.set(ROCKET, i);
	}
	
	public int isCreatingBlackHole()
	{
		return this.dataManager.get(CREATING_BH);
	}
	
	public void setCreatingBlackHole(int i)
	{
		this.dataManager.set(CREATING_BH, i);
	}
	
	public int isAttPlayDir()
	{
		return this.dataManager.get(BH_ON_PLAYER);
	}
	
	public void setAttPlayDir(int i)
	{
		this.dataManager.set(BH_ON_PLAYER, i);
	}
	
	public int isAboutToStomp()
	{
		return this.dataManager.get(STOMP);
	}
	
	public void setGoingToStomp(int i)
	{
		this.dataManager.set(STOMP, i);
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
	
	@Override
	public void travel(float strafe, float vertical, float forward)
	{
		if(this.getSpawnTime() <= 0)
		{
			super.travel(strafe, vertical, forward);
		}
	}
	
    public void onUpdate()
    {
    	if(this.getSpawnTime() > 0)
    	{
    		if(this.getSpawnTime() > 1)
    		{
    			this.noClip = true;
    		}
    		else
    		{
    			this.noClip = false;
    		}
    		this.motionY = 0.075F;
    		this.move(MoverType.SELF, 0, this.motionY, 0);
    		this.setSpawnTime(this.getSpawnTime() - 1);
    	}
    	else
    	{
        	super.onUpdate();
        	
    		int r = rand.nextInt(100);
        	int c = rand.nextInt(100);
        	
        	if(this.isLeftHanded())
        	{
        		this.setLeftHanded(false);
        	}
        	
        	if(this.getHealth() > 0)
        	{
        		this.bossInfo.setPercent(0.75F + (this.getHealth() / this.getMaxHealth()) * 0.25F);
        		
        		this.heal(0.05F);
        		
        		if(this.getAttackTarget() != null)
        		{
        			if(this.isCreatingBlackHole() == 0 && this.isAttPlayDir() == 0 && this.isAboutToStomp() == 0 && this.getARocket() == 0)
            		{
        				if(this.getAttackTarget().getDistance(this) > 20)
            			{
            				this.setGoingToStomp(1);
            			}
        				
            			//if(this.getRNG().nextInt(30) == 0)
            			{
            				int attack = this.getRNG().nextInt(12);
            				if(attack < 2)
            				{
            					this.setCreatingBlackHole(1);
            				}
            				else if(attack < 6)
            				{
            					this.setAttPlayDir(1);
            				}
            				else if(attack < 9)
            				{
            					this.setGoingToStomp(1);
            				}
            				else
            				{
            					this.setARocket(1);
            				}
            			}
            		}
        			
        			//Black Hole On Head Attack
            		if(this.isCreatingBlackHole() > 0 && this.isCreatingBlackHole() < 88)
            		{
            			this.motionX *= 0;
            			this.motionZ *= 0;
            			
            			if(this.isCreatingBlackHole() == 20)
            			{
            				EntityBlackHole entity = new EntityBlackHole(world);
            				entity.setPosition(this.posX, this.posY + 6, this.posZ);
            				entity.setSize(0F);
            				entity.setMaxSize(20F);
            				entity.damageMultiplier = 1.5F;
            				entity.attractMultiplier = 1.2F;
            				entity.isPulsior = true;
            				world.spawnEntity(entity);
            			}
            			this.setCreatingBlackHole(this.isCreatingBlackHole() + 1);
            		}
            		else
            		{
            			this.setCreatingBlackHole(0);
            			this.swingArm(EnumHand.MAIN_HAND);
            		}
            		
            		//Black Hole random
            		if(this.isAttPlayDir() > 0 && this.isAttPlayDir() < 50)
            		{
            			if(this.isAttPlayDir() == 4)
            			{
            				for(int i = 0; i < 13; ++i)
            				{
            					EntityBlackHole entity = new EntityBlackHole(world);
                				entity.setPosition(this.posX + this.getRNG().nextInt(41) - 20, this.posY + 1, this.posZ + this.getRNG().nextInt(41) - 20);
                				entity.setSize(0F);
                				entity.damageMultiplier = 4;
                				entity.setMaxSize(7F);
                				entity.attractMultiplier = 3.4F;
                				world.spawnEntity(entity);
            				}
            			}
            			
            			this.setAttPlayDir(this.isAttPlayDir() + 1);
            		}
            		else
            		{
            			this.setAttPlayDir(0);
            			this.swingArm(EnumHand.MAIN_HAND);
            		}
            		
            		//Stomp Attack
            		if(this.isAboutToStomp() > 0 && this.isAboutToStomp() <= 20)
            		{
            			this.motionX *= 0;
            			this.motionZ *= 0;
            			
            			if(this.isAboutToStomp() == 1)
            			{
            				EntityBlackHole entity = new EntityBlackHole(world);
            				entity.setPosition(this.posX, this.posY + 1, this.posZ);
            				entity.setSize(0F);
            				entity.setMaxSize(4F);
            				entity.isPulsior = true;
            				entity.attractMultiplier = -2;
            				world.spawnEntity(entity);
            				
            				EntityBlackHole entity2 = new EntityBlackHole(world);
            				entity2.setPosition(this.getAttackTarget().posX, this.getAttackTarget().posY + 7, this.getAttackTarget().posZ);
            				entity2.setSize(0F);
            				entity2.setMaxSize(4F);
            				entity2.attractMultiplier = -2;
            				entity2.isPulsior = true;
            				world.spawnEntity(entity2);
            				
            				this.tPosX = (float) this.getAttackTarget().posX;
            				this.tPosY = (float) this.getAttackTarget().posY + 7;
            				this.tPosZ = (float) this.getAttackTarget().posZ;
            			}
            			else if(this.isAboutToStomp() == 20)
            			{
            				this.setPosition(this.tPosX, this.tPosY, this.tPosZ);
            			}
            			this.setGoingToStomp(this.isAboutToStomp() + 1);
            		}
            		else
            		{
            			this.setGoingToStomp(0);
            			this.swingArm(EnumHand.MAIN_HAND);
            		}
            		
            		//Slow rocket attack
            		if(this.getARocket() > 0 && this.getARocket() < 110)
            		{
            			this.motionX *= 0.01;
            			this.motionZ *= 0.01;
            			
            			if(this.getARocket() == 10)
            			{
            				EntityBlackHole entity = new EntityBlackHole(world);
            				entity.setPosition(this.posX, this.posY + 1, this.posZ);
            				entity.setSize(0F);
            				entity.setMaxSize(2F);
            				entity.attractMultiplier = -2F;
            				world.spawnEntity(entity);
            				
            				EntityRocket entity2 = new EntityRocket(world, this, 30, 4);
            				entity2.rotationPitch = UtilsVoids.getPitchFromPos(this.getAttackTarget().getPositionEyes(1.0F), this.posX, this.posY + 1, this.posZ);
            				entity2.rotationYaw = UtilsVoids.getYawFromPos(this.getAttackTarget().getPositionEyes(1.0F), this.posX, this.posZ);
            				entity2.multiplyDamagesAndSpeed(1, 1.2F);
            				world.spawnEntity(entity2);
            			}
            			
            			this.setARocket(this.getARocket() + 1);
            		}
            		else
            		{
            			this.setARocket(0);
            			this.swingArm(EnumHand.MAIN_HAND);
            		}
        		}
        		
        		if(this.fallDistance > 5)
        		{
        			for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()))
        			{
        				entity.attackEntityFrom(MDamageSource.causeStompDamage(this), 20);
        			}
        		}
        	}
    	}
    }
    
    public void addPotionEffect(PotionEffect potioneffectIn)
    {
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(27.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(20.0D);
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
        if(this.deathTime >= 61)
        {
            if(!world.isRemote)
            {
            	//world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.BLACK_HOLE)));
            	//world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.STABILIZER, 4)));
            	//world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.CRUSHER)));
            	EntityDBossWitherPhase entity = new EntityDBossWitherPhase(world);
    			entity.setPosition(this.posX, this.posY, this.posZ);
    			entity.ignite();
    			world.spawnEntity(entity);
            }
            
            this.setDead();
        }

        if(this.deathTime == 1 && !world.isRemote)
        {
        	EntityBlackHole entity = new EntityBlackHole(world);
			entity.setPosition(this.posX, this.posY, this.posZ);
			entity.setSize(0F);
			entity.setMaxSize(3F);
			entity.attractMultiplier = -2F;
			world.spawnEntity(entity);
        }
        
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source == DamageSource.OUT_OF_WORLD)
    	{
    		super.attackEntityFrom(source, amount * 10);
    	}
    	if(source instanceof EntityDamageSource && !source.isProjectile())
    	{
    		return super.attackEntityFrom(source, amount * 0.75F);
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
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setCreatingBlackHole(compound.getInteger("attack_1"));
		this.setAttPlayDir(compound.getInteger("attack_2"));
		this.setGoingToStomp(compound.getInteger("attack_3"));
		this.setARocket(compound.getInteger("attack_4"));
		this.tPosX = compound.getFloat("targetX");
		this.tPosY = compound.getFloat("targetY");
		this.tPosZ = compound.getFloat("targetZ");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("attack_1", this.isCreatingBlackHole());
		compound.setInteger("attack_2", this.isAttPlayDir());
		compound.setInteger("attack_3", this.isAboutToStomp());
		compound.setInteger("attack_4", this.getARocket());
		compound.setFloat("targetX", this.tPosX);
		compound.setFloat("targetY", this.tPosY);
		compound.setFloat("targetZ", this.tPosZ);
	}
    
}