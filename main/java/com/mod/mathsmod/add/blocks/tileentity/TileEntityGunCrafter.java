package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.blocks.container.ContainerGunCrafter;
import com.mod.mathsmod.utils.IGunCraft;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityGunCrafter extends TileEntityLockableLoot implements ITickable
{
	
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	public int time;
	
	@Override
	public int getSizeInventory() {
		
		return 9;
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
		
		return 64;
	}

	@Override
	public String getName() {
		
		return "guns.crafter";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		
		return new ContainerGunCrafter(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		
		return "crafter";
	}

	@Override
	public void update() 
	{
		
		for(Item item : ItemStackUtils.craftsMap.keySet())
		{
			if(item instanceof IGunCraft)
			{
				if(((IGunCraft) item).Craft() != null)
				{
					int c = 0;
					for(int i = 0; i < ((IGunCraft) item).Craft().size(); ++i)
					{
						if(this.chestContents.get(i).getItem() == ((IGunCraft) item).Craft().get(i).getItem())
						{
							if(this.chestContents.get(i).getCount() >= ((IGunCraft) item).Craft().get(i).getCount())
							{
								c += 1;
							}
						}
					}
					if(c == ((IGunCraft) item).Craft().size())
					{
						if(time < this.getTime())
						{
							time += 1;
						}
						if(time > this.getTime())
						{
							time = this.getTime();
						}
						if(time == this.getTime() && this.chestContents.get(8).isEmpty())
						{
							this.chestContents.set(8, new ItemStack(item, ((IGunCraft) item).size()));
							
							for(int i = 0; i < ((IGunCraft) item).Craft().size(); ++i)
							{
								this.chestContents.get(i).shrink(((IGunCraft) item).Craft().get(i).getCount());
							}
							time = 0;
						}
						else if(time == this.getTime() && this.chestContents.get(8).getItem() == item && this.chestContents.get(8).getCount() + ((IGunCraft) item).size() <= item.getItemStackLimit())
						{
							this.chestContents.get(8).setCount(this.chestContents.get(8).getCount() + ((IGunCraft) item).size());
							
							for(int i = 0; i < ((IGunCraft) item).Craft().size(); ++i)
							{
								this.chestContents.get(i).shrink(((IGunCraft) item).Craft().get(i).getCount());
							}
							
							time = 0;
						}
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

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        this.time = compound.getInteger("time");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        compound.setInteger("time", time);
        return compound;
    }

	@Override
	protected NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }
	
	public int getTime()
	{
		return 80;
	}

}
