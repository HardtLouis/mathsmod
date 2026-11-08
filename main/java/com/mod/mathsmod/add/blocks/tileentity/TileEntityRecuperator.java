package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.blocks.BlockRecuperator;
import com.mod.mathsmod.add.items.ItemAmmoBox;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileEntityRecuperator extends TileEntityLockableLoot implements ITickable
{
	
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	
	@Override
	public int getSizeInventory() {
		
		return 2;
	}

	public boolean isEmpty()
    {
        for (ItemStack itemstack : this.chestContents)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

	@Override
	public int getInventoryStackLimit() {
		
		return Integer.MAX_VALUE;
	}

	@Override
	public String getName() {
		
		return "guns.recuperator";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		
		return new ContainerChest(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		
		return "recuperator";
	}

	@Override
	public void update() 
	{
		IBlockState state = world.getBlockState(getPos());
		BlockPos obs = ((BlockRecuperator) state.getBlock()).observedBlock(state, world, pos);
		
			if(world.getTileEntity(obs) instanceof TileEntityGunCrafter && this.chestContents.get(1).getCount() < 9999 && (this.chestContents.get(0).getCount() < this.chestContents.get(0).getMaxStackSize() || (this.chestContents.get(1).getItem() instanceof ItemAmmoBox && ((ItemAmmoBox) this.chestContents.get(1).getItem()).getItemCount(this.chestContents.get(1)) < ((ItemAmmoBox) this.chestContents.get(1).getItem()).getMaxStored(this.chestContents.get(1)))))
			{
				TileEntityGunCrafter te = (TileEntityGunCrafter) world.getTileEntity(obs);
				if(te.getItems().get(8).getItem() instanceof ItemBaseBullet)
				{
					int i = 0;
					if(!te.getItems().get(8).isEmpty() && this.chestContents.get(i).isEmpty())
					{
						this.chestContents.set(i, te.getItems().get(8));
						te.getItems().set(8, ItemStack.EMPTY);
						return;
					}
					if(!te.getItems().get(8).isEmpty() && !this.chestContents.get(i).isEmpty())
					{
						if(te.getItems().get(8).isItemEqual(this.chestContents.get(i)))
						{
							this.chestContents.get(i).setCount(this.chestContents.get(i).getCount() + te.getItems().get(8).getCount());
							te.getItems().get(8).setCount(0);
							return;
						}
					}
				}
			}
			else if(world.getTileEntity(obs) instanceof TileEntityRecuperator && this.chestContents.get(1).getCount() < 9999 && (this.chestContents.get(0).getCount() < this.chestContents.get(0).getMaxStackSize() || (this.chestContents.get(1).getItem() instanceof ItemAmmoBox && ((ItemAmmoBox) this.chestContents.get(1).getItem()).getItemCount(this.chestContents.get(1)) < ((ItemAmmoBox) this.chestContents.get(1).getItem()).getMaxStored(this.chestContents.get(1)))))
			{
				TileEntityRecuperator te = (TileEntityRecuperator) world.getTileEntity(obs);
				if(te.getItems().get(1).getItem() instanceof ItemBaseBullet)
				{
					int i = 0;
					if(!te.getItems().get(1).isEmpty() && this.chestContents.get(i).isEmpty())
					{
						this.chestContents.set(i, te.getItems().get(1));
						te.getItems().set(1, ItemStack.EMPTY);
						return;
					}
					if(!te.getItems().get(1).isEmpty() && !this.chestContents.get(i).isEmpty())
					{
						if(te.getItems().get(1).isItemEqual(this.chestContents.get(i)))
						{
							this.chestContents.get(i).setCount(this.chestContents.get(i).getCount() + te.getItems().get(1).getCount());
							te.getItems().get(1).setCount(0);
							return;
						}
					}
				}
			}
			
			if(!this.chestContents.get(0).isEmpty() && this.chestContents.get(0).getItem() instanceof ItemBaseBullet)
			{
				if(this.chestContents.get(1).getItem() instanceof ItemAmmoBox)
				{
					ItemAmmoBox box = (ItemAmmoBox) this.chestContents.get(1).getItem();
					ItemStack stack = this.chestContents.get(1);
					if(box.getItemCount(stack) > 0)
					{
						if(box.getAmmo(stack).getItem() == this.chestContents.get(0).getItem() && box.getItemCount(stack) < box.getMaxStored(stack))
						{
							int count = this.chestContents.get(0).getCount();
							int stay = count - box.getMaxStored(stack);
							if(stay < 0)
							{
								stay = 0;
							}
							box.setItemCount(box.getItemCount(stack) + count - stay, stack);
							this.chestContents.get(0).shrink(count - stay);
						}
					}
					else
					{
						box.setItemName(this.chestContents.get(0).getItem().getRegistryName().toString(), stack);
						box.setItemCount(this.chestContents.get(0).getCount(), stack);
						this.chestContents.get(0).shrink(this.chestContents.get(0).getMaxStackSize());
					}
				}
				else
				{
					if(this.chestContents.get(1).getCount() < this.chestContents.get(1).getMaxStackSize())
					{
						if(this.chestContents.get(0).getCount() > this.chestContents.get(0).getMaxStackSize())
						{
							ItemStack stack = this.chestContents.get(0).copy();
							stack.setCount(stack.getMaxStackSize());
							this.chestContents.set(1, stack);
							this.chestContents.get(0).shrink(this.chestContents.get(0).getMaxStackSize());
						}
						else
						{
							if(this.chestContents.get(1).isEmpty())
							{
								this.chestContents.set(1, this.chestContents.get(0));
								this.chestContents.set(0, ItemStack.EMPTY);
							}
							else
							{
								ItemStack stack = this.chestContents.get(0).copy();
								this.chestContents.get(1).setCount(this.chestContents.get(1).getCount() + stack.getCount());
								this.chestContents.set(0, ItemStack.EMPTY);
							}
						}
					}
					if(this.chestContents.get(1).getCount() > this.chestContents.get(1).getMaxStackSize())
					{
						int t = this.chestContents.get(1).getCount() - this.chestContents.get(1).getMaxStackSize();
						this.chestContents.get(1).shrink(t);
						if(this.chestContents.get(0).isEmpty())
						{
							ItemStack stack = this.chestContents.get(1).copy();
							stack.setCount(t);
							this.chestContents.set(0, stack);
						}
						else
						{
							this.chestContents.get(0).setCount(this.chestContents.get(0).getCount() + t);
						}
					}
				}
			}
	}
	
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.chestContents);
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.chestContents);
		return compound;
    }

	@Override
	public NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }

}