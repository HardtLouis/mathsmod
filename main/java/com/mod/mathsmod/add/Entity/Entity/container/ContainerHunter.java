package com.mod.mathsmod.add.Entity.Entity.container;

import com.mod.mathsmod.add.Entity.Entity.EntityHunter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHunter extends Container
{
    protected IInventory lowerChestInventory;
   	protected int numRows;

    public ContainerHunter(IInventory playerInventory, EntityHunter chestInventory)
    {
        this.lowerChestInventory = new EntityHunter.InventoryHunter(chestInventory);
        for(int i = 0; i < chestInventory.trades.size(); ++i)
        {
        	Slot slot = new Slot(lowerChestInventory, i, 0, 0)
        			{
		        		@SideOnly(Side.CLIENT)
		        	    public boolean isEnabled()
		        	    {
		        	        return false;
		        	    }
        			};
            this.inventorySlots.add(slot);
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
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
    
    public void detectAndSendChanges()
    {
        for (int i = 0; i < this.inventorySlots.size(); ++i)
        {
        	if(i < this.inventoryItemStacks.size())
        	{
        		ItemStack itemstack = ((Slot)this.inventorySlots.get(i)).getStack();
                ItemStack itemstack1 = this.inventoryItemStacks.get(i);

                if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
                {
                    boolean clientStackChanged = !ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack);
                    itemstack1 = itemstack.isEmpty() ? ItemStack.EMPTY : itemstack.copy();
                    this.inventoryItemStacks.set(i, itemstack1);

                    if (clientStackChanged)
                    for (int j = 0; j < this.listeners.size(); ++j)
                    {
                        ((IContainerListener)this.listeners.get(j)).sendSlotContents(this, i, itemstack1);
                    }
                }
        	}
        }
    }
}