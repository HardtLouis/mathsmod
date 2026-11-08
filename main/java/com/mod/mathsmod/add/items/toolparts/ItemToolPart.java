package com.mod.mathsmod.add.items.toolparts;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ICustomColor;
import com.mod.mathsmod.add.items.ItemAlloyIngot;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemToolPart extends Item implements ICustomColor
{
	
	public ItemToolPart(String name)
	{
		this.setMaxStackSize(1).setCreativeTab(Mathsmod.MATHSMOD_MET_TAB).setUnlocalizedName(name).setRegistryName(name);
	}
	
	public static void setAlloy(ItemStack stack, Item mat, Item mat2)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", mat.getRegistryName().toString() + "%" + mat2.getRegistryName().toString());
	}
	
	public static void setAlloyFromString(ItemStack stack, String str)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", str);
	}
	
	public static void setMetal(ItemStack stack, Item mat)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", mat.getRegistryName().toString());
	}
	
	public static String getMetalString(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
			return null;
		}
		
		return stack.getTagCompound().getString("metal");
	}
	
	public static Molten_metal getMetal(ItemStack stack)
	{
		return MeltingRegistry.getMetal(getMetalString(stack));
	}
	
	public abstract EnumParts part();
	
	public abstract float damageMultiplier();
	
	public abstract float efficiencyMultiplier();
	
	public abstract float protectionBonus();
	
	public abstract float reachBonus(ItemStack itemStack);
	
	public void hitEntity(EntityLivingBase attacker, EntityLivingBase target, World world, ItemStack stack, int part, int type)
	{
		if(this.getMetal(stack) != null)
		{
			this.getMetal(stack).effectOnHit(part, type, world, attacker, target, stack);
		}
	}
	
	public void userHit(EntityLivingBase attacker, EntityLivingBase user, World world, ItemStack stack)
	{
	}
	
	public void onBlockDestroyed(EntityLivingBase user, BlockPos pos, World world, ItemStack stack, int part, int type)
	{
		if(this.getMetal(stack) != null)
		{
			this.getMetal(stack).effectOnBreakBlock(part, type, world, user, pos, stack);
		}
	}

	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		if(this.getMetal(stack) != null)
		{
			return this.getMetal(stack).color;
		}
		return -1;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("reach bonus : " + (this.reachBonus(stack)));
		tooltip.add("protection bonus : " + (this.protectionBonus()));
		tooltip.add("damage factor : " + (this.damageMultiplier()));
		tooltip.add("efficiency factor : " + (this.efficiencyMultiplier()));
		tooltip.add(this.getMetalString(stack).replace("%", " + "));
    }
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(this.getCreativeTab() == tab)
		{
			for(Item metal : MeltingRegistry.getMeltingProperties().keySet())
			{
				ItemStack stack = new ItemStack(this);
				setMetal(stack, metal);
				items.add(stack);
			}
			
			for(String metal : MeltingRegistry.getAlloyMap().keySet())
			{
				alloy al = MeltingRegistry.getAlloyMap().get(metal);
				ItemStack stack = new ItemStack(this);
				
				setAlloyFromString(stack, metal);
				items.add(stack);
			}
		}
    }
	
	public static List<ItemStack> types(Item item)
    {
		List<ItemStack> list = Lists.newArrayList();
		
		for(Item metal : MeltingRegistry.getMeltingProperties().keySet())
		{
			ItemStack stack = new ItemStack(item);
			setMetal(stack, metal);
			list.add(stack);
		}
		
		for(String metal : MeltingRegistry.getAlloyMap().keySet())
		{
			alloy al = MeltingRegistry.getAlloyMap().get(metal);
			ItemStack stack = new ItemStack(item);
			
			setAlloyFromString(stack, metal);
			list.add(stack);
		}
		
		return list;
    }
	
}
