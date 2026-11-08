package com.mod.mathsmod.add.items.golemUpgrades;

import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;

public abstract class ItemBaseGolemUpgrade extends Item{

	public ItemBaseGolemUpgrade(String name)
	{
		this.setMaxStackSize(1).setRegistryName(name).setUnlocalizedName(name);
	}
	
	public abstract void onGolemHurt(EntityMateGolem entity, DamageSource source);
	
	public abstract void onEquipedTick(EntityMateGolem entity);
	
	public abstract int golemMinLevel(EntityMateGolem entity);

	public abstract void onGolemAttack(EntityMateGolem entityMateGolem, Entity entityIn, float amount);
}
