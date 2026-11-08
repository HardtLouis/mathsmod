package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.container.ContainerMeltingFurnace;
import com.mod.mathsmod.add.contractsManager.ContractManagerServer;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.ItemThermoresistantBucket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TileEntityTomb extends TileEntity implements ITickable, IInventory
{
	
	public NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(70, ItemStack.EMPTY);
	
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.items = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        return compound;
    }


	@Override
	public int getSizeInventory() {
		return 70;
	}

	@Override
	public boolean isEmpty()
	{
		for(ItemStack stack : items)
		{
			if(!stack.isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return items.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		ItemStack itemstack = ItemStackHelper.getAndSplit(this.items, index, count);

        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return itemstack;

	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		items.set(index, ItemStack.EMPTY);
		return items.get(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		items.set(index, stack);
		
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		if(this.world.getTileEntity(this.pos) != this)
	    {
			return false;
	    }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
	}

	@Override
	public void openInventory(EntityPlayer player) 
	{
	}


	@Override
	public void closeInventory(EntityPlayer player) 
	{
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public int getFieldCount() 
	{
		return 0;
	}

	@Override
	public void clear() 
	{
		this.items.clear();
	}

	@Override
	public String getName() 
	{
		return "mathsmod:tomb";
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public void update() 
	{
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		
	}
}