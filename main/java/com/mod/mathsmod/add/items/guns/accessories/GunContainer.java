package com.mod.mathsmod.add.items.guns.accessories;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.blocks.tileentity.TileEntityMultiFurnace;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GunContainer extends Container
{
    private final ItemStack stack;

    public GunContainer(InventoryPlayer playerInventory, ItemMachineGun item, ItemStack itemstack)
    {
        this.stack = itemstack;
        InventoryGun inv = new InventoryGun(playerInventory.player, item, itemstack);
        
        this.addSlotToContainer(new modifierSlot(inv, item, 1, 79, 12, EnumAccessoryType.SCOPE));
        this.addSlotToContainer(new weaponSlot(itemstack, 79, 30));
        this.addSlotToContainer(new modifierSlot(inv, item, 0, 97, 30, EnumAccessoryType.MUFFLER));
        this.addSlotToContainer(new modifierSlot(inv, item, 2, 97, 48, EnumAccessoryType.GRENADE_LAUNCHER));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 132));
        }
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

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}
	
	private static class weaponSlot extends Slot
	{
		ItemStack st;
		
		public weaponSlot(ItemStack stack, int xPosition, int yPosition) 
		{
			super(new InventoryBasic("", false, 1), 0, xPosition, yPosition);
			st = stack;
		}
		
		public boolean isItemValid(ItemStack stack)
	    {
	        return false;
	    }

		public ItemStack getStack()
	    {
	        return st;
	    }
		
	    public boolean canTakeStack(EntityPlayer playerIn)
	    {
	        return false;
	    }
	}
	
	private static class modifierSlot extends Slot
	{
		
		EnumAccessoryType type;
		ItemMachineGun item;
		
		public modifierSlot(IInventory inventoryIn, ItemMachineGun itemIn, int index, int xPosition, int yPosition, EnumAccessoryType typeIn)
	    {
			super(inventoryIn, index, xPosition, yPosition);
			type = typeIn;
			item = itemIn;
		}
		
		public boolean isItemValid(ItemStack stack)
	    {
			if(stack.getItem() instanceof GunAccessoryBase)
			{
				GunAccessoryBase acc = (GunAccessoryBase) stack.getItem();
				if(item.onlyMuffler())
				{
					if(type != EnumAccessoryType.MUFFLER)
					{
						return false;
					}
					else
					{
						return acc.modifierType() == EnumAccessoryType.MUFFLER;
					}
				}
				else
				{
					return type == acc.modifierType();
				}
			}
			else
			{
				return false;
			}
	    }
	}
}