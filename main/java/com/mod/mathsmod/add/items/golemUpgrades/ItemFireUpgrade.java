package com.mod.mathsmod.add.items.golemUpgrades;

import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class ItemFireUpgrade extends ItemBaseGolemUpgrade
{

	public ItemFireUpgrade() {
		super("fire_upgrade");
	}

	@Override
	public void onGolemAttack(EntityMateGolem entity, Entity target, float amount)
	{	
		if(target instanceof EntityLivingBase)
		{
			target.setFire(10);
		}
	}

	@Override
	public void onGolemHurt(EntityMateGolem entity, DamageSource source) {
		
	}

	@Override
	public void onEquipedTick(EntityMateGolem entity) {
		
	}

	@Override
	public int golemMinLevel(EntityMateGolem entity) {
		
		return 5;
	}

}
