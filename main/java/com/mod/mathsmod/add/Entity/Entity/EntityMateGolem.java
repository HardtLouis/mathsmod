package com.mod.mathsmod.add.Entity.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree.Competence;
import com.mod.mathsmod.add.Entity.EntityAi.GolemFollowOwner;
import com.mod.mathsmod.add.items.golemUpgrades.ItemBaseGolemUpgrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityMateGolem extends AbstractGolem implements IOnShot
{
	
	protected static final DataParameter<Integer> LEVEL = EntityDataManager.<Integer>createKey(EntityMateGolem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> XP = EntityDataManager.<Integer>createKey(EntityMateGolem.class, DataSerializers.VARINT);
	
	protected static final DataParameter<Integer> POINTS = new DataParameter<Integer>(253, DataSerializers.VARINT);
	
	private static final DataParameter<String> COMPS = new DataParameter<String>(254, DataSerializers.STRING);
	
	private int sitCooldown;
	
	public EntityMateGolem(World worldIn) {
		super(worldIn);
	}
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new GolemFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }

	@Override
	public float getSizeMultiplier() 
	{
		return 0.75F + this.getLevel() * 0.005F;
	}

	@Override
	public int getSizeInventory() 
	{
		return 5;
	}
	
	public int getMaxLevel()
	{
		return 100;
	}
	
	public int pointsMultiplier()
	{
		return this.getLevel() < 10 ? 1 : this.getLevel() < 20 ? 2 : 5;
	}
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(COMPS, "");
        this.dataManager.register(LEVEL, 1);
        this.dataManager.register(XP, 0);
        this.dataManager.register(POINTS, 0);
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D + (this.getLevel() * 1.5F));
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D + (this.getLevel() * 0.00125));
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
    }
	
	protected void modifyEntityAttributes()
	{
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D + (this.getLevel() * 1.5F));
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D + (this.getLevel() * 0.00125));
	}
	
	public List<ItemBaseGolemUpgrade> getUpgrades()
	{
		List<ItemBaseGolemUpgrade> list = Lists.newArrayList();
		for(ItemStack stack : this.chestContents)
		{
			if(stack.getItem() instanceof ItemBaseGolemUpgrade)
			{
				list.add((ItemBaseGolemUpgrade) stack.getItem());
			}
		}
		return list;
	}
	
	
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if(player.isSneaking() && !world.isRemote && player == this.getOwner() && this.sitCooldown == 0)
		{
    		this.setSitting(!this.isSitting());
    		
    		if(this.isSitting())
    		{
    			player.sendMessage(new TextComponentString("your golem isn't following"));
    			this.sitCooldown = 10;
    		}
    		else
    		{
    			player.sendMessage(new TextComponentString("your golem is following you"));
    			this.sitCooldown = 10;
    		}
    		
			return false;
		}
		if(!player.isSneaking())
		{
			if(player.capabilities.isCreativeMode || this.getOwner() == player)
			{
				player.openGui(Mathsmod.instance, 5, player.world, this.getEntityId(), 0, 0);
			}
			return true;
		}
		return false;
    }
    
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	for(int i = 0; i <= this.getSizeInventory(); ++i)
    	{
    		EntityItem entityitem = this.entityDropItem(this.getInv().getStackInSlot(i), 1F);
    		if (entityitem != null)
            {
                entityitem.setNoDespawn();
            }
    	}
    }
    
    public int getXpToLevel(int level)
    {
    	return level * level * level;
    }
    
    public void addXp(int xp)
    {
    	for(int i = 0; i < xp; ++i)
    	{
    		if(this.getLevel() < this.getMaxLevel())
        	{
        		if(this.getXpToLevel(this.getLevel()) <= this.getXP() + 1)
    			{
        			this.heal((this.getLevel() * 5));
        			this.addPoints(1 * this.pointsMultiplier());
    				this.setLevel(this.getLevel() + 1);
    			}
    			this.setXP(this.getXP() + 1);
        	}
    	}
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(super.attackEntityFrom(source, amount))
    	{
    		for(ItemBaseGolemUpgrade upgr : this.getUpgrades())
    		{
    			upgr.onGolemHurt(this, source);
    		}
    		
    		if(this.getCompetences().size() > 0)
        	{
        		for(Competence comp : this.getCompetences())
        		{
        			comp.onAttacked(this, source, amount);
        		}
        	}
    		
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public void setLevel(int level)
    {
        this.dataManager.set(LEVEL, level);
    }
    
    public int getLevel()
    {
        return this.dataManager.get(LEVEL);
    }
    
    public void setXP(int level)
    {
        this.dataManager.set(XP, level);
    }
    
    public int getXP()
    {
        return this.dataManager.get(XP);
    }
    
    public void setPoints(int points)
    {
        this.dataManager.set(POINTS, points);
    }
    
    public void addPoints(int points)
    {
        this.dataManager.set(POINTS, points + this.getPoints());
    }
    
    public int getPoints()
    {
        return this.dataManager.get(POINTS);
    }
    
    protected int attackCooldown()
    {
    	return 10 - this.getLevel() / this.getMaxLevel();
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
    	this.addXp(20);
        this.attackTimer = this.attackCooldown();
        this.world.setEntityState(this, (byte)4);
        float amount = (float)(14 + this.getLevel() / 10);
        
        for(ItemBaseGolemUpgrade upgr : this.getUpgrades())
		{
			upgr.onGolemAttack(this, entityIn, amount);
		}
    	for(Competence comp : this.getCompetences())
		{
			comp.onAttack(this, entityIn, amount);
		}
    	
    	amount = MathHelper.clamp(amount, 0, 30);
        
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), amount);

        if (flag)
        {
            entityIn.motionY += 0.2000000059604645D;
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	this.setSize(1.4F * this.getSizeMultiplier(), 2.7F * this.getSizeMultiplier());
    	if(this.getUpgrades().size() > 0)
    	{
    		for(ItemBaseGolemUpgrade upgr : this.getUpgrades())
    		{
    			upgr.onEquipedTick(this);
    		}
    	}
    	
    	if(this.getCompetences().size() > 0)
    	{
    		for(Competence comp : this.getCompetences())
    		{
    			comp.onUpdate(this);
    		}
    	}
    	
    	if(this.chestContents.get(4).getCount() > 0)
    	{
    		this.addXp(20 * this.chestContents.get(4).getCount());
    		this.modifyEntityAttributes();
    		this.chestContents.get(4).shrink(this.chestContents.get(4).getCount());
    	}
    	
    	if(this.sitCooldown > 0)
    	{
    		--this.sitCooldown;
    	}
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("level", this.getLevel());
        compound.setInteger("experience", this.getXP());
        compound.setInteger("points", this.getPoints());
        compound.setIntArray("comps", this.getComps());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setLevel(compound.getInteger("level"));
        this.setXP(compound.getInteger("experience"));
        this.setComps(compound.getIntArray("comps"));
        this.setPoints(compound.getInteger("points"));
    }
    
    public int getXpToChangeLevel()
    {
    	return this.getXpToLevel(this.getLevel() + 1) - this.getXpToLevel(this.getLevel());
    }
    
    public int getProgress()
    {
    	return this.getXpToLevel(this.getLevel()) - this.getXP();
    }
    
    public boolean canHaveCompetence(Competence comp, EntityPlayer player)
    {
    	return player.isCreative() && !this.getCompetences().contains(comp) ? true : this.canHaveCompetence(comp) && this.getOwner() == player;
    }
    
    public boolean canHaveCompetence(Competence comp)
    {
    	if(!this.getCompetences().contains(comp) && (this.getPoints() >= comp.points || (this.getOwner() instanceof EntityPlayer && ((EntityPlayer)this.getOwner()).capabilities.isCreativeMode)))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public int[] getComps()
	{
    	String s = this.dataManager.get(COMPS);
        String[] sl = s.split("//");
        int[] list = new int[sl.length];

        for(int i = 0; i  < sl.length; ++i)
        {
    		if(sl[i] != "")
    		{
    			list[i] = Integer.parseInt(sl[i]);
    		}
        }
        
        return list;
	}
    
    public void setComps(int[] list)
	{
    	
    	String s = "";
    	for(int i = 0; i < list.length; ++i)
    	{
    		s += list[i];
    		if(i != list.length - 1)
    		{
    			s += "//";
    		}
    	}
    	this.dataManager.set(COMPS, s);
	}
    
    public void addComp(int comp)
	{
    	List<Integer> list = Lists.<Integer>newArrayList();
    	for(int i : this.getComps())
    	{
    		list.add(i);
    	}
    	list.add(comp);
    	Integer[] comps = new Integer[list.size()];
    	comps = list.toArray(comps);
    	
    	this.setComps(list.stream().mapToInt(Integer::intValue).toArray());
	}
    
    public List<Competence> getCompetences()
	{
		List<Competence> list = Lists.newArrayList();
		for(int id : this.getComps())
		{
			if(Competence.getByNumberID(id) != null)
			{
				list.add(Competence.getByNumberID(id));
			}
		}
		return list;
	}

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
	}
}
