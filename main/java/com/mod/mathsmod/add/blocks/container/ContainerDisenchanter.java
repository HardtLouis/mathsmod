package com.mod.mathsmod.add.blocks.container;

import com.mod.mathsmod.add.blocks.tileentity.TileEntityDisenchanter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;

public class ContainerDisenchanter extends Container
{
	
	private int Solution;
	private IInventory lowerChestInventory;
	private int numRows;

	public ContainerDisenchanter(IInventory playerInventory, TileEntityDisenchanter chestInventory, EntityPlayer player) {
		
		this.lowerChestInventory = chestInventory;
        this.numRows = chestInventory.getSizeInventory() / 9;
        chestInventory.openInventory(player);
        int i = -1 * 18;
        
        this.addSlotToContainer(new Slot(chestInventory, 0, 8 + 18 + 0 * 18, 103 + 18 + i - 10 - 18 * 3)
        {
        	public boolean isItemValid(ItemStack stack)
            {
                return stack.isItemEnchanted() || stack.getItem() instanceof ItemEnchantedBook;
            }
        });
        this.addSlotToContainer(new Slot(chestInventory, 1, 8 + 18 + 2 * 18, 103 + 18 + i - 10 - 18 * 3)
        {
        	public boolean isItemValid(ItemStack stack)
            {
                return stack.getItem() instanceof ItemBook;
            }
        });
        
        this.addSlotToContainer(new Slot(chestInventory, 2, 8 + 18 + 6 * 18, 103 + 18 + i - 10 - 18 * 3)
        {
        	public boolean isItemValid(ItemStack stack)
            {
                return false;
            }
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
                this.onSlotChanged();
                ItemStack itemstack = chestInventory.getItems().get(0);
                if(!(itemstack.getItem() instanceof ItemEnchantedBook))
                {
                	for (int j = itemstack.getEnchantmentTagList().tagCount() - 1; j >= 0; --j)
                    {
                		itemstack.getEnchantmentTagList().removeTag(j);
                    }
                }
                chestInventory.getItems().get(1).shrink(1);
                //thePlayer.world.playSound(null, thePlayer.posX, thePlayer.posY, thePlayer.posZ, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 1, (1.0F + (thePlayer.world.rand.nextFloat() - thePlayer.world.rand.nextFloat()) * 0.2F) * 0.7F);
                return stack;
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