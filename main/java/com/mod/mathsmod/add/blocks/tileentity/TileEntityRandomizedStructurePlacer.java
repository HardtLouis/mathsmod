package com.mod.mathsmod.add.blocks.tileentity;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityRandomizedStructurePlacer extends TileEntity
{
	public List<String> data = Lists.newArrayList();
	
	public TileEntityRandomizedStructurePlacer()
	{
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        int size = compound.getInteger("data_size");
        for(int i = 0; i < size; i++)
        {
        	if(compound.getString("structure_name_" + i) != null && !compound.getString("structure_name_" + i).isEmpty())
        	{
        		data.add(compound.getString("structure_name_" + i));
        	}
        }
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("data_size", data.size());
        for(int i = 0; i < data.size(); i++)
        {
        	compound.setString("structure_name_" + i, data.get(i));
        }
        return compound;
    }
}