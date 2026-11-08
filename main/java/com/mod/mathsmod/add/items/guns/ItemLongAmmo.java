package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemLongAmmo extends ItemBaseBullet implements IGunCraft
{

	public ItemLongAmmo() {
		super("long_ammo");
	}

	@Override
	public EnumBulletType type() {
		return EnumBulletType.NORMAL;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.GUNPOWDER));
		list.set(1, new ItemStack(Items.GOLD_INGOT));
		list.set(2, new ItemStack(Items.GOLD_INGOT));
		
		return list;
	}

	@Override
	public int size() {
		
		return 16;
	}

	@Override
	public boolean displayRecipe() 
	{
		return true;
	}
	
}
