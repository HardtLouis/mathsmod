package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTeleportersBinder extends Item
{
	public ItemTeleportersBinder()
	{
		this.setMaxStackSize(1);
		this.setRegistryName("teleporters_binder").setUnlocalizedName("teleporters_binder").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "allow you to bind two teleporters on right click");
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
		return this.getPos(stack) != null;
    }
	
	public BlockPos getPos(ItemStack stack) 
	{
        if (!stack.hasTagCompound()) {

            return null;
        }

        if (!stack.getTagCompound().hasKey("tpX") || !stack.getTagCompound().hasKey("tpY") || !stack.getTagCompound().hasKey("tpZ")) {

            return null;
        }

        return new BlockPos(stack.getTagCompound().getInteger("tpX"), stack.getTagCompound().getInteger("tpY"), stack.getTagCompound().getInteger("tpZ"));
    }
	
	public void setPos(ItemStack stack, BlockPos pos) 
	{
		if(pos != null)
		{

	        if (!stack.hasTagCompound()) {

	            stack.setTagCompound(new NBTTagCompound());
	        }
	        
	        stack.getTagCompound().setInteger("tpX", pos.getX());
	        stack.getTagCompound().setInteger("tpY", pos.getY());
	        stack.getTagCompound().setInteger("tpZ", pos.getZ());
		}
		else
		{
			stack.getTagCompound().removeTag("tpX");
			stack.getTagCompound().removeTag("tpY");
			stack.getTagCompound().removeTag("tpZ");
		}
    }
}
