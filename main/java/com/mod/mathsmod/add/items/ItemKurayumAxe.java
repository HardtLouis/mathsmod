package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemKurayumAxe extends ItemAxe
{

	public ItemKurayumAxe() {
		super(RegisterItems.KURAYUM, 5 + RegisterItems.KURAYUM.getAttackDamage(), -2.0F);;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		target.setFire(10);
        return true;
    }
	
}
