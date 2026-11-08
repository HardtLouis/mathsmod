package com.mod.mathsmod.add.items;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAbstractProtectionShield extends Item
{
	public ItemAbstractProtectionShield(String name)
	{
		this.setMaxStackSize(1).setRegistryName(name).setUnlocalizedName(name);
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(this.getCooldown(stack) > 0 && this.getCharge(stack) < this.getMaxCharge() && isSelected)
		{
			this.setCooldown(stack, this.getCooldown(stack) - 1);
		}
		else if(this.getCooldown(stack) <= 0)
		{
			this.setCharge(stack, this.getMaxCharge());
			this.setCooldown(stack, this.getMaxCooldown());
		}
    }
	
	public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity)
    {
		return true;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(this.getCharge(stack) + "/" + this.getMaxCharge());
    }
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
	    return 1.0F - (double)(this.getCharge(stack)) / (double)this.getMaxCharge();
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
		return true;
    }
	
	public int getRGBDurabilityForDisplay(ItemStack stack)
    {
		double i = (double)this.getCharge(stack) / (double)this.getMaxCharge();
        return new Color((int)(i * 200D), (int)(i * 200D), 255).getRGB();
    }
	
	public int getMaxCharge()
	{
		return 5;
	}
	
	public int getMaxCooldown()
	{
		return 200;
	}

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    public void setCooldown(ItemStack stack, int i)
    {
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	stack.getTagCompound().setInteger("cooldown", i);
    }
    
    public int getCooldown(ItemStack stack)
    {
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	if(!stack.getTagCompound().hasKey("cooldown"))
    	{
    		stack.getTagCompound().setInteger("cooldown", this.getMaxCooldown());
    		return this.getMaxCooldown();
    	}
    	
    	return stack.getTagCompound().getInteger("cooldown");
    }
    
    public void setCharge(ItemStack stack, int i)
    {
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	stack.getTagCompound().setInteger("charge", i);
    }
    
    public int getCharge(ItemStack stack)
    {
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	if(!stack.getTagCompound().hasKey("charge"))
    	{
    		stack.getTagCompound().setInteger("charge", this.getMaxCharge());
    		return this.getMaxCharge();
    	}
    	
    	return stack.getTagCompound().getInteger("charge");
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

}
