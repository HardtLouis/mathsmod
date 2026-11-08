package com.mod.mathsmod.add.Entity.Entity.NBoss;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks.AttackPubCharge;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks.AttackPubJumpAttack;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks.AttackPubProj;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks.AttackPubShoot;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks.AttackPubSpin;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityPub extends EntityACBoss
{
	List<BaseBossAttack> list = Lists.newArrayList();
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS));
	private static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> COOLDOWN_AA = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PROTECT = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> STRIKE = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PREPARE = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> ATTACK_ANIM = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SHOOT_ANIM = EntityDataManager.<Integer>createKey(EntityPub.class, DataSerializers.VARINT);
	public double prevMotionX = 0;
	public double prevMotionY = 0;
	public double prevMotionZ = 0;
	public double chMotionX = 0;
	public double chMotionY = 0;
	public double chMotionZ = 0;
	public double angleCape = 0;
	public Vec3d onVec = new Vec3d(0,0,0);
	
    public EntityPub(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 2.5F);
        list.add(new AttackPubCharge());
        list.add(new AttackPubJumpAttack());
        list.add(new AttackPubShoot());
        list.add(new AttackPubProj());
        this.stepHeight = 1F;
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.155D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(45.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2200.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ID, -1);
        this.dataManager.register(COOLDOWN_AA, 0);
        this.dataManager.register(PROTECT, 0);
        this.dataManager.register(STRIKE, 0);
        this.dataManager.register(PREPARE, 0);
        this.dataManager.register(ATTACK_ANIM, 0);
        this.dataManager.register(SHOOT_ANIM, 0);
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

	public void setTargetID(int id)
	{
		this.dataManager.set(ID, id);
	}
	
	public int getTargetID()
	{
		return this.dataManager.get(ID);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        world.playSound(null, this.posX, this.posY + 2, this.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 20.0F, 1.0F);
        if (flag && entityIn instanceof EntityLivingBase)
        {
        	this.setAttackAnim(0);
            this.setStrikeTime(7);
        }

        return flag;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source == DamageSource.IN_WALL && !world.isRemote)
    	{
    		this.posY = world.getHeight(this.getPosition().getX(), this.getPosition().getZ());
    		for(int i = 0; i < 20; ++i)
    		{
    			world.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, this.posX, this.posY + 2, this.posZ, this.getRNG().nextFloat() * 0.1F - 0.05F, this.getRNG().nextFloat() * 0.1F - 0.05F, this.getRNG().nextFloat() * 0.1F - 0.05F);
    		}
    	}
    	if(this.getCoolTime() > 0)
    	{
            if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityGolem)
            {
            	return super.attackEntityFrom(source, amount * 0.25F);
            }
            else
            {
            	return super.attackEntityFrom(source, amount * 3);
            }
    	}
    	else
    	{
    		this.setProtecTime(20);
    		return super.attackEntityFrom(source, 0);
    	}
    }
    
    public void onUpdate()
    {
    	prevMotionX = this.motionX;
    	prevMotionY = this.motionY;
    	prevMotionZ = this.motionZ;
    	super.onUpdate();
        	
        	
    	if(this.getAttackTarget() != null && this.getAttackTarget().getEntityId() != this.getTargetID())
		{
			this.setTargetID(this.getAttackTarget().getEntityId());
		}
    	
    	if(this.getTarget() != null)
    	{
    		if(this.getTarget().getDistance(this) < 50)
    		{
    			UtilsVoids.setLookEntity(this.getTarget(), this);
    		}
    		
    		if(UtilsVoids.distanceIgnoreY(this.posX, this.posZ, this.getTarget()) > 40)
    		{
        		float yaw = UtilsVoids.getYawFromPos(this.getTarget().getPositionVector(), this.posX, this.posZ);
        		Vec3d vec = UtilsVoids.laser(39.9F, yaw, 0).add(this.getPositionVector());
        		float y = world.getHeight((int)vec.x, (int)vec.z);
        		this.getTarget().setPositionAndUpdate(vec.x, y, vec.z);
    		}
    		
    		if(this.getTarget() instanceof EntityLivingBase && ((EntityLivingBase) this.getTarget()).getHealth() <= 0)
    		{
    			this.setTargetID(-1);
    		}
    	}
    	
    	if(this.getStrikeTime() > 0)
    	{
    		this.setStrikeTime(this.getStrikeTime() - 1);
    	}
    	
    	if(this.getProtecTime() > 0)
    	{
    		this.setProtecTime(this.getProtecTime() - 1);
    	}
    	
    	if(this.getShootTime() > 0)
    	{
    		this.setShootTime(this.getShootTime() - 1);
    	}
    	
    	if(this.getPrepareTime() < 10 && this.getTarget() != null)
    	{
    		this.setPrepareTime(this.getPrepareTime() + 1);
    	}
    	else if(this.getPrepareTime() > 0 && this.getTarget() == null)
    	{
    		this.setPrepareTime(this.getPrepareTime() - 1);
    	}
    	
    	if(this.getTarget() != null && this.getTarget() instanceof EntityLivingBase)
    	{
    		if(((EntityLivingBase) this.getTarget()).getHealth() < 4.0F)
    		{
    	        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.155D * 2);
    		}
    		else
    		{
    			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.155D);
    		}
    	}
    	else
    	{
    		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.155D);
    	}
    	
    	if(this.getCoolTime() > 0)
    	{
    		this.setCoolTime(this.getCoolTime() - 1);
    	}
    	
    	if(MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) < 0.2F)
    	{
        	angleCape = 90 * MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.5F;
    	}
    	
    	if(angleCape > 0)
    	{
    		angleCape = MathHelper.clamp(angleCape - 4, 0, 100);
    	}
    	
		bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
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

    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        this.dropItem(RegisterItems.BLACK_KATANA, 1);
    }

	@Override
	public List<BaseBossAttack> getAttacksList()
	{
		return list;
	}
	
	public void setProtecTime(int time)
	{
		this.dataManager.set(PROTECT, time);
	}
	
	public int getProtecTime()
	{
		return this.dataManager.get(PROTECT);
	}
	
	public void setAttackAnim(int animInd)
	{
		this.dataManager.set(ATTACK_ANIM, animInd);
	}
	
	public int getAttackAnim()
	{
		return this.dataManager.get(ATTACK_ANIM);
	}
	
	public void setStrikeTime(int time)
	{
		this.dataManager.set(STRIKE, time);
	}
	
	public int getStrikeTime()
	{
		return this.dataManager.get(STRIKE);
	}
	
	public void setPrepareTime(int time)
	{
		this.dataManager.set(PREPARE, time);
	}
	
	public int getPrepareTime()
	{
		return this.dataManager.get(PREPARE);
	}
	
	public void setCoolTime(int time)
	{
		this.dataManager.set(COOLDOWN_AA, time);
	}
	
	public int getCoolTime()
	{
		return this.dataManager.get(COOLDOWN_AA);
	}
	
	public void setShootTime(int time)
	{
		this.dataManager.set(SHOOT_ANIM, time);
	}
	
	public int getShootTime()
	{
		return this.dataManager.get(SHOOT_ANIM);
	}
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setDouble("jumpOnX", onVec.x);
        compound.setDouble("jumpOnY", onVec.y);
        compound.setDouble("jumpOnZ", onVec.z);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.onVec = new Vec3d(compound.getDouble("jumpOnX"), compound.getDouble("jumpOnY"), compound.getDouble("jumpOnZ"));
    }
    
    @Override
	public void fall(float distance, float damageMultiplier)
	{
    	if(this.getAttackUsed() != null && this.getAttackUsed().objectID() == "attack.pub.jump_attack")
    	{
    		this.setAttackAnim(4);
    		this.setStrikeTime(7);
			this.getAttackUsed().cancel(this);
			this.setCoolTime(20);
			this.motionX = 0;
			this.motionZ = 0;
			BadExplosionS.newExplosion(world, this, posX, posY, posZ, 4, 10, true);
    	}
	}
}