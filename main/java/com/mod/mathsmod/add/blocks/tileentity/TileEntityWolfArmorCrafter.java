package com.mod.mathsmod.add.blocks.tileentity;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.blocks.container.ContainerGunCrafter;
import com.mod.mathsmod.add.blocks.container.ContainerWolfArmorCrafter;
import com.mod.mathsmod.add.items.ItemArmor.wolf.ItemWolfArmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileEntityWolfArmorCrafter extends TileEntityLockableLoot implements ITickable
{
	
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	public int time;
	
	@Override
	public int getSizeInventory() {
		
		return 3;
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
		
		return new ContainerWolfArmorCrafter(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		
		return "crafter";
	}

	@Override
	public void update() 
	{
		if(this.getItems().get(2).isEmpty())
		{
			if(this.getItems().get(0).getCount() == 1 && this.getItems().get(0).getItem() instanceof ItemArmor)
			{
				if(this.getItems().get(1).getCount() == 1 && this.getItems().get(1).getItem() instanceof ItemArmor)
				{
					if(this.getItems().get(1).getItem() == this.getItems().get(0).getItem()
					&& ((ItemArmor) this.getItems().get(0).getItem()).armorType == ((ItemArmor) this.getItems().get(1).getItem()).armorType)
					{
						if(this.getWolfArmorByMaterial(((ItemArmor) this.getItems().get(0).getItem()).getArmorMaterial(), ((ItemArmor) this.getItems().get(0).getItem()).armorType) != null)
						{
							this.chestContents.set(2, new ItemStack(this.getWolfArmorByMaterial(((ItemArmor) this.getItems().get(0).getItem()).getArmorMaterial(), ((ItemArmor) this.getItems().get(0).getItem()).armorType)));
							this.chestContents.set(1, ItemStack.EMPTY);
							this.chestContents.set(0, ItemStack.EMPTY);
						}
					}
				}
			}
		}
	}
	
	@Nullable
	private ItemWolfArmor getWolfArmorByMaterial(ItemArmor.ArmorMaterial material, EntityEquipmentSlot slot)
	{
		for(Item item : Item.REGISTRY)
		{
			if(item instanceof ItemWolfArmor && ((ItemWolfArmor) item).getArmorMaterial() == material && ((ItemWolfArmor) item).armorType == slot)
			{
				return (ItemWolfArmor) item;
			}
		}
		return null;
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

}
