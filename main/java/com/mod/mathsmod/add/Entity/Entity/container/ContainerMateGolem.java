package com.mod.mathsmod.add.Entity.Entity.container;

import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;
import com.mod.mathsmod.add.Entity.Entity.container.easyInv.EasyInventoryEntity;
import com.mod.mathsmod.add.items.golemUpgrades.ItemBaseGolemUpgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ContainerMateGolem extends Container
{
	
	private int Solution;
	private IInventory lowerChestInventory;
	private int numRows;

	public ContainerMateGolem(IInventory playerInventory, EntityMateGolem chestInventory, EntityPlayer player) {
		
		this.lowerChestInventory = chestInventory.getInv();
        this.numRows = chestInventory.getInv().getSizeInventory() / 9;
        chestInventory.getInv().openInventory(player);
        int e = 6*18 + 15;

        this.addSlotToContainer(new UpgradeSlot(chestInventory, 0, 9, 6 + 18));
        this.addSlotToContainer(new UpgradeSlot(chestInventory, 1, 9 + 18, 6 + 18));
        this.addSlotToContainer(new UpgradeSlot(chestInventory, 2, 9, 6 - 18 + 18));
        this.addSlotToContainer(new UpgradeSlot(chestInventory, 3, 9 + 18, 6 - 18 + 18));
        
        this.addSlotToContainer(new Slot(chestInventory.getInv(), 4, 9 + 8 * 18, 6)
        		{
        			public boolean isItemValid(ItemStack stack)
        			{
        				return chestInventory.getLevel() < chestInventory.getMaxLevel();
        			}
        		});
        
        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9,(9 * 18) + 8 + j1 * 18 - (18 * 9) + 1, 103 - 150 + l * 18 + e));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1,(9 * 18) + 8 + i1 * 18 - (18 * 9) + 1, 161 - 150 + e));
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
    
    private static class UpgradeSlot extends Slot
    {
    	EntityMateGolem golem;
    	
		public UpgradeSlot(EntityMateGolem inventoryIn, int index, int xPosition, int yPosition)
		{
			super(inventoryIn.getInv(), index, xPosition, yPosition);
			golem = inventoryIn;
		}
    	
		public boolean isItemValid(ItemStack stack)
	    {
	        return stack.getItem() instanceof ItemBaseGolemUpgrade && golem.getLevel() >= ((ItemBaseGolemUpgrade) stack.getItem()).golemMinLevel(golem);
	    }
    }
    
}