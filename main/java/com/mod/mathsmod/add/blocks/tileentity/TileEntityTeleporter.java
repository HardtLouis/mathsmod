package com.mod.mathsmod.add.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityTeleporter extends TileEntity
{
	protected BlockPos posT;
	
	public TileEntityTeleporter()
	{
	}
	
	public void setTpPos(BlockPos posIn)
	{
		posT = posIn;
	}
	
	public BlockPos getTpPos()
	{
		return posT;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        posT = new BlockPos(compound.getInteger("tpX"), compound.getInteger("tpY"), compound.getInteger("tpZ"));
    }
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if(posT != null)
        {
            compound.setInteger("tpX", posT.getX());
            compound.setInteger("tpY", posT.getY());
            compound.setInteger("tpZ", posT.getZ());
        }
        return compound;
    }
}
