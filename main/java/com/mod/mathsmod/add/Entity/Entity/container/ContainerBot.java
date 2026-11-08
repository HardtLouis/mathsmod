package com.mod.mathsmod.add.Entity.Entity.container;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Entity.container.easyInv.EasyInventoryEntity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerBot extends Container
{
	
	private int Solution;
	private int numRows;
	private static final EntityEquipmentSlot[] VALID_EQUIPMENT_SLOTS = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

	public ContainerBot(IInventory playerInventory, EntityBot chestInventory, EntityPlayer player) 
	{
        int e = 6*18 + 15;
        
        NonNullList<ItemStack> list = chestInventory.armorItems();
        IInventory inv = new EasyInventoryEntity(chestInventory, list, 4);
        
        NonNullList<ItemStack> list2 = chestInventory.handItems();
        IInventory inv2 = new EasyInventoryEntity(chestInventory, list2, 2);

        for (int k = 0; k < 4; ++k)
        {
            final EntityEquipmentSlot entityequipmentslot = VALID_EQUIPMENT_SLOTS[k];
            
            this.addSlotToContainer(new Slot(inv,  3 - k, 44, 1 + k * 18)
            {
                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                 * in the case of armor slots)
                 */
                public int getSlotStackLimit()
                {
                    return 1;
                }
                /**
                 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace
                 * fuel.
                 */
                public boolean isItemValid(ItemStack stack)
                {
                	if(entityequipmentslot == EntityEquipmentSlot.HEAD)
                	{
                		if(stack.getItem() instanceof ItemBlock)
                		{
                			return true;
                		}
                		else
                		{
                			return stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).armorType == entityequipmentslot;
                		}
                	}
                	else
                	{
                		return stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).armorType == entityequipmentslot;
                	}
                    
                }
                @Nullable
                @SideOnly(Side.CLIENT)
                public String getSlotTexture()
                {
                    return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot.getIndex()];
                }
            });
        }
        
        this.addSlotToContainer(new Slot(inv2, 0, 116, 19));
        
        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9,(9 * 18) + 8 + j1 * 18 - (18 * 9), 103 - 150 + l * 18 + e));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1,(9 * 18) + 8 + i1 * 18 - (18 * 9), 161 - 150 + e));
        }
	}
	
	public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
    }
    
}