package com.mod.mathsmod.add.blocks.tileentity;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityEProjector extends TileEntity implements ITickable
{

	public static final net.minecraft.util.math.AxisAlignedBB RENDER_AABB = new net.minecraft.util.math.AxisAlignedBB(-50, -50, -50, 50, 50, 50);
	
	public String entityName;
	public boolean holoRender;
	public int rotation;
	public int cooldown;
	
	public TileEntityEProjector()
	{
		if(entityName == null)
		{
			entityName = "creeper";
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        entityName = compound.getString("display");
        rotation = compound.getInteger("rotation");
        holoRender = compound.getBoolean("hologram");
    }
	
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox()
    {
		return INFINITE_EXTENT_AABB;
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        compound.setString("display", entityName);
        compound.setInteger("rotation", rotation);
        compound.setBoolean("hologram", holoRender);
        return compound;
    }

	@Override
	public void update() 
	{
		if(cooldown > 0)
		{
			cooldown -= 1;
		}
	}
	
	@Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        NBTTagCompound tag = pkt.getNbtCompound();
        this.readFromNBT(tag);
    }
    
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
    	SPacketUpdateTileEntity packet = super.getUpdatePacket();
        NBTTagCompound tag = new NBTTagCompound();

        this.writeToNBT(tag);

        return new SPacketUpdateTileEntity(this.pos, 3, tag);
    }
    
    @Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
}
