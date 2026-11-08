package com.mod.mathsmod.add.items;

import net.minecraft.item.ItemAxe;

public class ItemMAxe extends ItemAxe{

	public ItemMAxe(ToolMaterial material) {
		super(material, 5 + material.getAttackDamage(), -3.0F);
	}

}
