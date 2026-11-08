package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.AttackCharge;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemMobSoul;
import com.mod.mathsmod.add.spell.SpellBase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityCultistPriest extends EntityMob
{
	
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS));
	public float chargeYaw;
	private static final DataParameter<Integer> PHASE = EntityDataManager.<Integer>createKey(EntityCultistPriest.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PROGRESS = EntityDataManager.<Integer>createKey(EntityCultistPriest.class, DataSerializers.VARINT);
	private static final DataParameter<String> SPELL = EntityDataManager.<String>createKey(EntityCultistPriest.class, DataSerializers.STRING);

	SpellBase[] usableSpells = new SpellBase[] {SpellRegistry.BLACK_SPHERE, SpellRegistry.ICY_JAVELIN, SpellRegistry.MOB_MANIPULATION, SpellRegistry.MAGIC_REGENERATION};
	
	public EntityCultistPriest(World worldIn) 
	{
		super(worldIn);
		this.stepHeight = 0.5F;
		this.setSize(0.8F, 1.8F);
		this.experienceValue = 20;
	}
	
	@Override
    protected void initEntityAI()
    {
    	super.initEntityAI();
    	this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, null));
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(PHASE, 0);
        this.dataManager.register(PROGRESS, 0);
        this.dataManager.register(SPELL, "null");
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
	
	public void onUpdate()
	{
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.onUpdate();
		
		if(this.getAttackTarget() != null)
		{
			if(this.getAttackTarget().getHealth() <= 0.0F)
			{
				this.resetSpell();
			}
			else
			{
				if(this.getSpell() != null)
				{
					UtilsVoids.setLookEntity(this.getAttackTarget(), this);
					
					if(this.getSpell() == SpellRegistry.MOB_MANIPULATION)
					{
						ItemStack stack = new ItemStack(RegisterItems.MOB_SOUL);
						ItemMobSoul.setEntity(stack, "minecraft:zombie");
						this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
					}
					
					SpellBase spell = this.getSpell();
					if(spell.phases(this) <= this.getPhase())
					{
						this.resetSpell();
					}
					else
					{
						spell.spellUseCommon(this.getPhase(), this.getProgress(), this);
						
						if(spell.duration(this.getPhase(), this) > this.getProgress())
						{
							this.setProgress(this.getProgress() + 1);
						}
						else
						{
							if(this.getSpell() == SpellRegistry.MOB_MANIPULATION && this.getPhase() == 1)
							{
								this.resetSpell();
							}
							else
							{
								this.setPhase(this.getPhase() + 1);
							}
							this.setProgress(0);
						}
					}
				}
				else
				{
					this.setSpell(this.usableSpells[this.getRNG().nextInt(this.usableSpells.length)].spellName());
				}
			}
		}
		else
		{
			this.resetSpell();
		}
	}
	
	protected void resetSpell()
	{
		this.setPhase(0);
		this.setProgress(0);
		this.setSpell("null");
	}
	
	public int getPhase()
	{
		return this.dataManager.get(PHASE);
	}
	
	public int getProgress()
	{
		return this.dataManager.get(PROGRESS);
	}
	
	public String getSpellId()
	{
		return this.dataManager.get(SPELL);
	}
	
	public void setPhase(int phase)
	{
		this.dataManager.set(PHASE, phase);
	}
	
	public void setProgress(int progress)
	{
		this.dataManager.set(PROGRESS, progress);
	}
	
	public void setSpell(String spellId)
	{
		this.dataManager.set(SPELL, spellId);
	}
	
	public SpellBase getSpell()
	{
		if(!SpellRegistry.REGISTRY.containsKey(this.getSpellId()))
		{
			return null;
		}
		else
		{
			return SpellRegistry.REGISTRY.get(this.getSpellId());
		}
	}

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.205D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    }



    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        this.dropItem(RegisterItems.CLOTH, 1);
    }
}
