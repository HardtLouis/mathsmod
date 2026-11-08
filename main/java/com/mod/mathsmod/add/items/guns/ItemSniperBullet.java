package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemSniperBullet extends ItemBaseBullet implements IGunCraft
{

	public ItemSniperBullet() {
		super("bullet_sniper");
	}

	@Override
	public EnumBulletType type() {
		return EnumBulletType.LONG;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.GUNPOWDER));
		list.set(1, new ItemStack(Items.GOLD_INGOT));
		list.set(2, new ItemStack(Items.GOLD_INGOT));
		list.set(3, new ItemStack(Items.GOLD_INGOT));
		list.set(5, new ItemStack(Items.GOLD_INGOT));
		list.set(6, new ItemStack(Items.GOLD_INGOT));
		
		return list;
	}

	@Override
	public int size() 
	{
		return 4;
	}

	@Override
	public boolean displayRecipe() 
	{
		return true;
	}
	
}
