package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.container.ContainerMeltingFurnace;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.contractsManager.ContractManagerServer;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.ItemThermoresistantBucket;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMetalurgicFurnace extends TileEntityLockable implements ITickable
{
	
	public NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	public int progress = 0;
	public int burntime = 0;
	public int timeToSmelt = 0;
	public int xpStored = 0;
	public String lastInteraction;
	
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.items = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
        progress = compound.getInteger("melting_progress");
        xpStored = compound.getInteger("exp");
        lastInteraction = compound.getString("li");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        compound.setInteger("exp", xpStored);
        if(!this.lastInteraction.isEmpty())
        {
            compound.setString("li", lastInteraction);
        }
        return compound;
    }


	@Override
	public int getSizeInventory() {
		return 6;
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
	    ItemStack stack = items.get(index);
	    items.set(index, ItemStack.EMPTY);
	    return stack;
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
	
	private void giveAdvancmentToLI(World world, BlockPos pos)
	{
		if(this.lastInteraction != null && world.getPlayerEntityByName(this.lastInteraction) != null)
		{
			ContractManagerServer.addProgress(world.getPlayerEntityByName(this.lastInteraction), RegisterContracts.SILVER_INGOT, 1);
		}
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
		if(index == 0 && (MeltingRegistry.getMeltingProperties().containsKey(stack.getItem())))
		{
			return true;
		}
		
		if(index == 1 && stack.getItem() == Items.BLAZE_ROD)
		{
			return true;
		}
		
		if(index == 2 && stack.getItem() == RegisterItems.THERMORESISTANT_BUCKET)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public int getField(int id) 
	{
		if(id == 0)
		{
			return this.timeToSmelt;
		}
		if(id == 2)
		{
			return this.burntime;
		}
		return this.progress;
	}

	@Override
	public void setField(int id, int value) 
	{
		if(id == 0)
		{
			this.timeToSmelt = value;
		}
		if(id == 2)
		{
			this.burntime = value;
		}
		this.progress = value;
	}

	@Override
	public int getFieldCount() 
	{
		return 3;
	}

	@Override
	public void clear() 
	{
		this.items.clear();
	}

	@Override
	public String getName() 
	{
		return "mathsmod:metalurgic_furnace";
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
	{
		return new ContainerMeltingFurnace(playerInventory, this);
	}

	@Override
	public String getGuiID() 
	{
		return "mathsmod:melting_furnace";
	}
	
	public int getTimeToSmelt()
	{
		int i = 900;
		if(this.items.get(3).getItem() == RegisterItems.METALURGIC_FURNACE_UPGRADE)
		{
			i *= 0.7;
		}
		
		if(this.items.get(4).getItem() == RegisterItems.METALURGIC_FURNACE_UPGRADE)
		{
			i *= 0.7;
		}
		
		if(this.items.get(3).getItem() == RegisterItems.CREATIVE_METALURGIC_FURNACE_UPGRADE)
		{
			i *= 0.2;
		}
		
		if(this.items.get(4).getItem() == RegisterItems.CREATIVE_METALURGIC_FURNACE_UPGRADE)
		{
			i *= 0.2;
		}
		
		return (int) (MathHelper.absMax(1, i));
	}

	@Override
	public void update() 
	{
		if(this.burntime == 0 && this.items.get(2).getItem() == RegisterItems.THERMORESISTANT_BUCKET && MeltingRegistry.getMeltingProperties().containsKey(this.items.get(0).getItem()) && ItemThermoresistantBucket.getMoltenStack(this.items.get(2)).isEmpty())
		{
			if(this.items.get(1).getItem() == Items.BLAZE_ROD)
			{
				this.items.get(1).shrink(1);
				this.burntime = 1000;
			}
			else if(this.items.get(1).getItem() == Items.BLAZE_POWDER)
			{
				this.items.get(1).shrink(1);
				this.burntime = 500;
			}
			else if(this.items.get(1).getItem() == Items.COAL)
			{
				this.items.get(1).shrink(1);
				this.burntime = 50;
			}
		}
		
		if(this.burntime > 0)
		{
			if(this.items.get(2).getItem() == RegisterItems.THERMORESISTANT_BUCKET && (ItemThermoresistantBucket.getMoltenStack(this.items.get(2)) == null || ItemThermoresistantBucket.getMoltenStack(this.items.get(2)).isEmpty()))
			{
				if(this.items.get(0).getItem() != null && this.items.get(5).getItem() != null && MeltingRegistry.getAlloy(this.items.get(0).getItem().getRegistryName().toString(), this.items.get(5).getItem().getRegistryName().toString()) != null)
				{
					this.timeToSmelt = this.getTimeToSmelt();
					this.progress++;
					if(this.progress >= this.timeToSmelt)
					{
						ItemStack stack = new ItemStack(RegisterItems.ALLOY_INGOT);
						ItemAlloyIngot.setMoltenMaterials(stack, this.items.get(0).getItem(), this.items.get(5).getItem());
						ItemThermoresistantBucket.setMoltenMaterial(this.items.get(2), stack);
						this.items.get(0).shrink(1);
						this.items.get(5).shrink(1);
						giveAdvancmentToLI(this.world, this.pos);
						this.progress = 0;
					}
				}
				else if(this.items.get(0).getItem() != null && MeltingRegistry.getMeltingProperties().containsKey(this.items.get(0).getItem()))
				{
					this.timeToSmelt = this.getTimeToSmelt();
					this.progress++;
					if(this.progress >= this.timeToSmelt)
					{
						ItemThermoresistantBucket.setMoltenMaterial(this.items.get(2), this.items.get(0));
						this.items.get(0).shrink(1);
						giveAdvancmentToLI(this.world, this.pos);
						this.progress = 0;
					}
				}
				else if(this.items.get(0).getItem() == RegisterItems.ALLOY_INGOT)
				{
					this.timeToSmelt = this.getTimeToSmelt();
					this.progress++;
					if(this.progress >= this.timeToSmelt)
					{
						ItemThermoresistantBucket.setMoltenMaterial(this.items.get(2), this.items.get(0));
						this.items.get(0).shrink(1);
						giveAdvancmentToLI(this.world, this.pos);
						this.progress = 0;
						
					}
				}
				else
				{
					progress = 0;
				}
			}
			else
			{
				progress = 0;
			}
			
			this.burntime--;
		}
		else
		{
			if(this.progress > 0)
			{
				this.progress--;
			}
		}
	}
}