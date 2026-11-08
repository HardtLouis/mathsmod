package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.crafting.MeltingRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemMobSoul extends ItemM
{

	public ItemMobSoul() 
	{
		super("mob_soul");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB).setMaxStackSize(8);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.RED + "soul of a " + this.getEntity(stack));
	}
	
	public static void setEntity(ItemStack stack, String used)
	{

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setString("entity", used);
    }
	
	public static void setEntity(ItemStack stack, Entity entity)
	{
		setEntity(stack, EntityList.getKey(entity).toString());
	}
    
    public static String getEntity(ItemStack stack)
    {
        if (!stack.hasTagCompound()) {

            return "";
        }

        if (!stack.getTagCompound().hasKey("entity")) {

        	stack.getTagCompound().setString("entity", "null");
            return "";
        }

        return stack.getTagCompound().getString("entity");
    }
    
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(tab == this.getCreativeTab())
		{
			ItemStack stack = new ItemStack(this);
			this.setEntity(stack, "minecraft:creeper");
			items.add(stack);
		}
    }
}
