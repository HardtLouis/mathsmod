package com.mod.mathsmod.add.Entity.Entity.NBoss;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class EntityACBoss extends EntityMob
{
	
	private static final DataParameter<String> USED_ATTACK_ID = EntityDataManager.<String>createKey(EntityACBoss.class, DataSerializers.STRING);
	private static final DataParameter<Integer> TIMER = EntityDataManager.<Integer>createKey(EntityACBoss.class, DataSerializers.VARINT);
	
	public EntityACBoss(World worldIn) 
	{
		super(worldIn);
	}
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(USED_ATTACK_ID, "null");
        this.dataManager.register(TIMER, 0);
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
	public abstract List<BaseBossAttack> getAttacksList();

	public void onUpdate()
	{
		super.onUpdate();
		
		if(!world.isRemote)
		{
			if(this.getHealth() > 0 && this.getAttackTarget() != null && this.getAttacksList() != null && this.getAttacksList().size() > 0)
			{
				this.setTimer(this.getTimer() + 1);
				
				if(this.getAttackUsed() == null && this.getTimer() == -1)
				{
					this.getRandomAttack();
				}
				else
				{
					if(this.getAttackUsed() != null)
					{
						if(this.getTimer() >= this.getAttackUsed().timeToUse(this))
						{
							this.getAttackUsed().onStop(this, this.world, this.getAttackTarget());
							this.setTimer(-this.getAttackUsed().cooldown(this));
							
							this.setAttackTo("null");
						}
						else
						{
							this.getAttackUsed().onUseTick(this, this.world, this.getAttackTarget(), this.getTimer());
						}
					}
					else if(this.getTimer() >= 0)
					{
						this.setTimer(-2);
					}
				}
			}
			else
			{
				this.setTimer(-10);
			}
		}
	}
	
	public BaseBossAttack getAttackUsed()
	{
		BaseBossAttack attq = null;
		
		for(BaseBossAttack att : this.getAttacksList())
		{
			if(this.getUsedAttack().equals(att.objectID()))
			{
				attq = att;
			}
		}
		
		return attq;
	}
	
	public void getRandomAttack()
	{
		int totalChances = 0;
		List<String> ids = Lists.newArrayList();
		
		for(BaseBossAttack attack : this.getAttacksList())
		{
			if(attack != null && attack.canUse(this))
			{
				totalChances += attack.chancesFactor();
				for(int i = 0; i < attack.chancesFactor(); ++i)
				{
					ids.add(attack.objectID());
				}
			}
		}
		
		if(ids != null && ids.size() > 0 && totalChances > 0)
		{
			this.setAttackTo(ids.get(this.getRNG().nextInt(totalChances)));
		}
	}
	
	public void setAttackTo(String id)
	{
		if(id == null || id.isEmpty())
		{
			this.dataManager.set(USED_ATTACK_ID, "null");
		}
		else
		{
			this.dataManager.set(USED_ATTACK_ID, id);
		}
	}
	
	public String getUsedAttack()
	{
		return this.dataManager.get(USED_ATTACK_ID);
	}
	
	public void setTimer(int i)
	{
		this.dataManager.set(TIMER, i);
	}
	
	public int getTimer()
	{
		return this.dataManager.get(TIMER);
	}
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setString("usedAttack", this.getUsedAttack().length() > 0 ? this.getUsedAttack() : "null");
        compound.setInteger("timer", this.getTimer());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setAttackTo(compound.getString("usedAttack"));
        this.setTimer(compound.getInteger("timer"));
    }
}
