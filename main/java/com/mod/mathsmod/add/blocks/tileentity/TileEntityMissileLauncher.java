package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.blocks.container.ContainerMissileLauncher;
import com.mod.mathsmod.add.items.ItemLaunchedMissile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class TileEntityMissileLauncher extends TileEntityLockableLoot implements ITickable
{
	public BlockPos target;
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	
	public void readFromNBT(NBTTagCompound compound)
    {
		super.readFromNBT(compound);
        this.target = new BlockPos(compound.getInteger("target_x"), compound.getInteger("target_y"), compound.getInteger("target_z"));
        
        this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound))
        {
            ItemStackHelper.loadAllItems(compound, this.chestContents);
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
    	super.writeToNBT(compound);
    	if(target == null)
    	{
    		target = new BlockPos(0, 0, 0);
    	}
    	compound.setInteger("target_x", (int) target.getX());
    	compound.setInteger("target_y", (int) target.getY());
    	compound.setInteger("target_z", (int) target.getZ());
    	
    	if (!this.checkLootAndWrite(compound))
        {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }
    	
        return compound;
    }
    
    public void launchMissile()
    {
    	if(this.chestContents.get(0).getItem() instanceof ItemLaunchedMissile)
    	{
    		ItemLaunchedMissile item = (ItemLaunchedMissile) this.chestContents.get(0).getItem();
    		EntityMissile entity = item.getMissile(world, getPos(), target);
    		entity.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
    		this.chestContents.get(0).shrink(1);
    		
        	if(!world.isRemote)
        	{
        		world.spawnEntity(entity);
        	}
    	}
    }

	@Override
	public int getSizeInventory() 
	{
		return 1;
	}

	@Override
	public boolean isEmpty() 
	{
		return chestContents.isEmpty();
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 1;
	}

	@Override
	public String getName() 
	{
		return "missile_launcher";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
	{
		return new ContainerMissileLauncher(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() 
	{
		return "missile_launcher";
	}

	@Override
	public void update()
	{
		
	}

	@Override
	public NonNullList<ItemStack> getItems() 
	{
		return this.chestContents;
	}
}
