package com.mod.mathsmod.add.items;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemAlloyIngot extends Item implements ICustomColor
{
	
	public ItemAlloyIngot()
	{
		this.setMaxStackSize(64).setRegistryName("alloy_ingot").setUnlocalizedName("alloy_ingot").setCreativeTab(Mathsmod.MATHSMOD_MET_TAB);
	}
	
	public static String[] getMoltenNames(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		return new String[] {stack.getTagCompound().getString("ingot1"), stack.getTagCompound().getString("ingot2")};
	}
	
	public static alloy getMoltenMaterial(ItemStack stack)
	{
		return MeltingRegistry.getAlloy(getMoltenNames(stack)[0], getMoltenNames(stack)[1]);
	}
	
	public static void setMoltenMaterials(ItemStack stack, Item ingot1, Item ingot2)
	{
		if(!stack.hasTagCompound())
		{
			NBTTagCompound tag = new NBTTagCompound();
			String[] id = new String[] {ingot1.getRegistryName().toString(), ingot2.getRegistryName().toString()};
			Arrays.sort(id,String.CASE_INSENSITIVE_ORDER);
			tag.setString("ingot1", id[0]);
			tag.setString("ingot2", id[1]);
			stack.setTagCompound(tag);
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			String[] id = new String[] {ingot1.getRegistryName().toString(), ingot2.getRegistryName().toString()};
			Arrays.sort(id,String.CASE_INSENSITIVE_ORDER);
			tag.setString("ingot1", id[0]);
			tag.setString("ingot2", id[1]);
		}
		
	}
	
	public static void setMoltenMaterials(ItemStack stack, String ingot1, String ingot2)
	{
		if(!stack.hasTagCompound())
		{
			NBTTagCompound tag = new NBTTagCompound();
			String[] id = new String[] {ingot1, ingot2};
			Arrays.sort(id,String.CASE_INSENSITIVE_ORDER);
			tag.setString("ingot1", id[0]);
			tag.setString("ingot2", id[1]);
			stack.setTagCompound(tag);
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			String[] id = new String[] {ingot1, ingot2};
			Arrays.sort(id,String.CASE_INSENSITIVE_ORDER);
			tag.setString("ingot1", id[0]);
			tag.setString("ingot2", id[1]);
		}
	}
	

	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(tab == this.getCreativeTab())
		{
			for(String metals : MeltingRegistry.getAlloyMap().keySet())
			{
				ItemStack stack = new ItemStack(this);
				setMoltenMaterials(stack, metals.split("%")[0], metals.split("%")[1]);
				items.add(stack);
			}
		}
    }
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(this.getMoltenNames(stack) != null)
		{
			/*Item[] item = ItemAlloyIngot.getMoltenMaterial(stack).items;
			String str = I18n.translateToLocal(item[0].getUnlocalizedName() + ".name");
			String str2 = I18n.translateToLocal(item[1].getUnlocalizedName() + ".name");
			this*/
			tooltip.add("alloy : " + this.getMoltenNames(stack)[0] + " + " + this.getMoltenNames(stack)[1]);
		}
		else
		{
			tooltip.add("put");
		}
    }

	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		Item item1 = Item.getByNameOrId(this.getMoltenNames(stack)[0]);
		Item item2 = Item.getByNameOrId(this.getMoltenNames(stack)[0]);
		if(MeltingRegistry.getMeltingProperties().get(item1) != null && MeltingRegistry.getMeltingProperties().get(item2) != null)
		{
			if(this.getMoltenMaterial(stack) == null)
			{
				return 0;
			}
			
			return this.getMoltenMaterial(stack).result.color;
		}
		return -1;
	}
}
