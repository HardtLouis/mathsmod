package com.mod.mathsmod.add.blocks.tileentity;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.container.ContainerCaster;
import com.mod.mathsmod.add.blocks.container.ContainerMeltingFurnace;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.contractsManager.ContractManagerServer;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.IMetalurgy;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.ItemThermoresistantBucket;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityCaster extends TileEntityLockable implements ITickable
{
	
	public NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	
	public int progress = 0;
	public String lastInt;
	
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.items = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
        progress = compound.getInteger("progress");
        lastInt = compound.getString("lastInteraction");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        compound.setInteger("progress", progress);
        if(this.lastInt != null)
        {
            compound.setString("lastInteraction", lastInt);
        }
        return compound;
    }


	@Override
	public int getSizeInventory() {
		return 2;
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
		if (!player.isSpectator())
        {
            this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
        }
	}


	@Override
	public void closeInventory(EntityPlayer player) 
	{
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		if((index == 0) && stack.getItem() == RegisterItems.THERMORESISTANT_BUCKET)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public int getField(int id) 
	{
		return this.progress;
	}

	@Override
	public void setField(int id, int value) 
	{
		this.progress = value;
	}

	@Override
	public int getFieldCount() 
	{
		return 1;
	}

	@Override
	public void clear() 
	{
		this.items.clear();
	}

	@Override
	public String getName() 
	{
		return "mathsmod:caster";
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
	{
		return new ContainerCaster(playerInventory, this);
	}
	
	private void giveAdvancmentToLI(World world)
	{
		if(this.lastInt != null && world.getPlayerEntityByName(this.lastInt) != null)
		{
			ContractManagerServer.addProgress(world.getPlayerEntityByName(this.lastInt), RegisterContracts.NIGHT_SHARD, 1);
		}
	}

	@Override
	public String getGuiID() 
	{
		return "mathsmod:caster";
	}

	@Override
	public void update() 
	{
		if(this.items.get(0).getItem() == RegisterItems.THERMORESISTANT_BUCKET && ItemThermoresistantBucket.material(this.items.get(0)) != null)
		{
			if(this.items.get(1).isEmpty())
			{
				if(this.progress >= 600)
				{
					ItemStack stack = ItemThermoresistantBucket.getMoltenStack(this.items.get(0)).copy();
					stack.setCount(1);
					this.giveAdvancmentToLI(world);
					this.items.set(1, stack);
					this.items.set(0, new ItemStack(RegisterItems.THERMORESISTANT_BUCKET));

					this.progress = 0;
				}
				else
				{
					this.progress += 4;
				}
			}
			else
			{
				if(ItemThermoresistantBucket.getMoltenStack(this.items.get(0)).getItem() == this.items.get(1).getItem() && this.items.get(1).getCount() < this.items.get(1).getMaxStackSize())
				{
					if(ItemThermoresistantBucket.getMoltenStack(this.items.get(0)).getItem() == RegisterItems.ALLOY_INGOT && ItemAlloyIngot.getMoltenMaterial(ItemThermoresistantBucket.getMoltenStack(this.items.get(0))) != null && ItemAlloyIngot.getMoltenMaterial(ItemThermoresistantBucket.getMoltenStack(this.items.get(0))) == ItemAlloyIngot.getMoltenMaterial(this.items.get(1)))
					{
						if(this.progress >= 600)
						{
							this.giveAdvancmentToLI(world);
							this.items.get(1).setCount(this.items.get(1).getCount() + 1);
							this.items.set(0, new ItemStack(RegisterItems.THERMORESISTANT_BUCKET));

							this.progress = 0;
						}
						else
						{
							this.progress += 4;
						}
					}
					else if(ItemThermoresistantBucket.getMoltenStack(this.items.get(0)).getItem() != RegisterItems.ALLOY_INGOT)
					{
						if(this.progress >= 600)
						{
							this.giveAdvancmentToLI(world);
							this.items.get(1).setCount(this.items.get(1).getCount() + 1);
							this.items.set(0, new ItemStack(RegisterItems.THERMORESISTANT_BUCKET));

							this.progress = 0;
						}
						else
						{
							this.progress += 4;
						}
					}
					else
					{
						this.progress = 0;
					}
				}
			}
			
		}
		else
		{
			this.progress = 0;
		}
	}

}
