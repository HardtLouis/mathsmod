package com.mod.mathsmod.add.items.ItemArmor;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemIronJetpack extends ItemJetpack
{

	public ItemIronJetpack() 
	{
		super("iron_jetpack", 0.10, 2);
		this.setMaxDamage(60);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/iron_jetpack.png";
    }
}
