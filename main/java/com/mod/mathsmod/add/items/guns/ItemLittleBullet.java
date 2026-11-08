package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemLittleBullet extends ItemBaseBullet implements IGunCraft
{

	public ItemLittleBullet() {
		super("little_ammo");
	}

	@Override
	public EnumBulletType type() {
		return EnumBulletType.LITTLE;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.GUNPOWDER));
		list.set(1, new ItemStack(Items.IRON_INGOT));
		list.set(4, new ItemStack(Items.GUNPOWDER));
		list.set(5, new ItemStack(Items.IRON_INGOT));
		
		return list;
	}

	@Override
	public int size() 
	{
		return 32;
	}

	@Override
	public boolean displayRecipe() 
	{
		return true;
	}

}
