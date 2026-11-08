package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHuntingBullet extends ItemBaseBullet implements IGunCraft
{

	public ItemHuntingBullet() 
	{
		super("hunting_bullet");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "just a single precise bullet with no spread");
		tooltip.add(TextFormatting.RED + "-40% damages");
    }
	
	public float bulletMultiplier(ItemStack stack, ItemGun item, Entity shooter)
	{
		return 0.6F;
	}

	@Override
	public EnumBulletType type()
	{
		return EnumBulletType.SHOTGUN;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.IRON_INGOT));
		list.set(4, new ItemStack(Items.IRON_INGOT));
		list.set(1, new ItemStack(Items.GUNPOWDER));
		list.set(5, new ItemStack(Items.GUNPOWDER));
		list.set(2, new ItemStack(Items.GOLD_INGOT));
		list.set(6, new ItemStack(Items.GOLD_INGOT));
		
		return list;
	}
	
	@Override
	public int size() 
	{
		return 18;
	}

	@Override
	public boolean displayRecipe()
	{
		return true;
	}
}
