package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.add.items.guns.specials.AdvancedGunsInv;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public interface ItemAdvancedGun
{
	
	public boolean canHaveScope();
	
	public boolean canHaveGrenadeLauncher();
	
	public default NonNullList<ItemStack> getEquipmentList(ItemStack stack)
    {
    	if (!stack.hasTagCompound()) 
    	{
            stack.setTagCompound(new NBTTagCompound());
        }
    	
    	NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    	ItemStackHelper.loadAllItems(stack.getTagCompound(), list);
    	return list;
    }
	
	public default void setEquipmentList(ItemStack stack, NonNullList<ItemStack> list)
	{
		if (!stack.hasTagCompound()) 
    	{
            stack.setTagCompound(new NBTTagCompound());
        }
		
		ItemStackHelper.saveAllItems(stack.getTagCompound(), list);
	}
	
	public default IInventory equipInv(ItemStack stack)
	{
		return new AdvancedGunsInv(stack, this, this.getEquipmentList(stack));
	}

}
