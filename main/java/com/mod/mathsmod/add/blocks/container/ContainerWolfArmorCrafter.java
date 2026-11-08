package com.mod.mathsmod.add.blocks.container;

import com.mod.mathsmod.add.blocks.tileentity.TileEntityWolfArmorCrafter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;

public class ContainerWolfArmorCrafter extends Container
{
	
	private int Solution;
	private IInventory lowerChestInventory;
	private int numRows;

	public ContainerWolfArmorCrafter(IInventory playerInventory, TileEntityWolfArmorCrafter chestInventory, EntityPlayer player) {
		
		this.lowerChestInventory = chestInventory;
        this.numRows = chestInventory.getSizeInventory() / 9;
        chestInventory.openInventory(player);
        int i = -1 * 18;
        
        this.addSlotToContainer(new Slot(chestInventory, 0, 8 + 18 + 1 * 18, 103 + 18 + i - 10 - 18 * 3 - 3));
        this.addSlotToContainer(new Slot(chestInventory, 1, 8 + 18 + 1 * 18, 103 + 18 + i - 10 - 18 * 3 + 15));
        
        this.addSlotToContainer(new Slot(chestInventory, 2, 8 + 18 + 5 * 18, 103 + 18 + i - 3 - 18 * 3)
        {
        	public boolean isItemValid(ItemStack stack)
            {
                return false;
            }
        });
        
        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18 + 0 * 18, 103 + l * 18 + i - 10));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18 + 0 * 18, 161 + i - 10));
        }
	}
	
	public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.lowerChestInventory.isUsableByPlayer(playerIn);
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
        this.lowerChestInventory.closeInventory(playerIn);
    }

    /**
     * Return this chest container's lower chest inventory.
     */
    public IInventory getLowerChestInventory()
    {
        return this.lowerChestInventory;
    }
    
}