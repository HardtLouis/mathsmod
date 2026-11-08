package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemEnderSniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemAmmoBox extends ItemM
{

	public ItemAmmoBox(String name)
	{
		super("ammo_box");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(this.getItemCount(stack) + "/" + this.getMaxStored(stack));
		tooltip.add(this.getItemName(stack));
		
		tooltip.add(TextFormatting.YELLOW + "can stockpile ammos, need to be put on the output of a gun crafter to stockpile");
    }
	
	public int getMaxStored(ItemStack stack)
	{
		if(this.getAmmo(stack).getItem() instanceof ItemBaseBullet && ((ItemBaseBullet)this.getAmmo(stack).getItem()).type() == EnumBulletType.ROCKET)
		{
			return 40;
		}
		else if(this.getAmmo(stack).getItem() instanceof ItemBaseBullet && ((ItemBaseBullet)this.getAmmo(stack).getItem()).type() == EnumBulletType.GRENADE)
		{
			return 80;
		}
		else if(this.getAmmo(stack).getItem() instanceof ItemBaseBullet && ((ItemBaseBullet)this.getAmmo(stack).getItem()).type() == EnumBulletType.LITTLE)
		{
			return 20000;
		}
		else
		{
			return 9999;
		}
	}
	
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
	    return (double)(this.getMaxStored(stack) - this.getItemCount(stack)) / (double)this.getMaxStored(stack);
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
		return this.getItemCount(stack) > 0;
    }
	
	public void setItemCount(int count, ItemStack stack)
    {
		count = MathHelper.clamp(count, 0, this.getMaxStored(stack));
		
    	if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("item_count", count);
    }
    
    public int getItemCount(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("item_count")) {

            return 0;
        }

        return stack.getTagCompound().getInteger("item_count");
    }
    
    public static void setItemName(String name, ItemStack stack)
    {
    	if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setString("item_name", name);
    }
    
    public String getItemName(ItemStack stack) 
    {

        if (!stack.hasTagCompound()) {

            return "";
        }

        if (!stack.getTagCompound().hasKey("item_name")) {

            return "";
        }

        return stack.getTagCompound().getString("item_name");
    }

    public boolean isSameItem(ItemStack stack, Item item)
    {
    	return this.getItemName(stack).equals(item.getRegistryName().toString());
    }
    
    public ItemStack getAmmo(ItemStack stack)
    {
    	if(this.getItemName(stack).length() > 0)
    	{
    		if(Item.getByNameOrId(this.getItemName(stack)) != null)
    		{
    			return this.getItemCount(stack) > 0 ? new ItemStack(Item.getByNameOrId(this.getItemName(stack)), this.getItemCount(stack)) : ItemStack.EMPTY;
    		}
    		else
    		{
    			return ItemStack.EMPTY;
    		}
    	}
    	else
    	{
    		return ItemStack.EMPTY;
    	}
    }
}
