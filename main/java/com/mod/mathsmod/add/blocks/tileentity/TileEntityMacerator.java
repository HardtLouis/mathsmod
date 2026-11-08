package com.mod.mathsmod.add.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityMacerator extends TileEntity
{
	public int water;
	public int seeds;
	public int potatoes;
	

	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        water = compound.getInteger("water");
        seeds = compound.getInteger("seeds");
        potatoes = compound.getInteger("potatoes");
    }
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        compound.setInteger("water", water);
        compound.setInteger("seeds", seeds);
        compound.setInteger("potatoes", potatoes);
        
        return compound;
    }
}
