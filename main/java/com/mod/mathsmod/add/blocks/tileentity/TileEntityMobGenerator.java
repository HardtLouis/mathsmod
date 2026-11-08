package com.mod.mathsmod.add.blocks.tileentity;

import java.util.Random;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class TileEntityMobGenerator extends TileEntity implements ITickable
{

	public static final net.minecraft.util.math.AxisAlignedBB RENDER_AABB = new net.minecraft.util.math.AxisAlignedBB(-50, -50, -50, 50, 50, 50);
	
	public String entityName;
	public int number;
	private Random rand = new Random();
	
	public TileEntityMobGenerator()
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
        number = compound.getInteger("number");
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
        compound.setInteger("number", number);
        return compound;
    }

	@Override
	public void update() 
	{
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
    
    public static void generateEntities(BlockPos pos, World world, NBTTagCompound tag, Random rand)
    {
    	EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(new ResourceLocation(tag.getString("display")));
		if(entry != null)
		{
			for(int i = 0; i < tag.getInteger("number"); ++i)
			{
				Entity entity = entry.newInstance(world);
				entity.setPosition(pos.getX() + rand.nextDouble() - 0.5, pos.getY(), pos.getZ() - 0.5 + rand.nextDouble());
				if(entity instanceof EntityLiving)
				{
					((EntityLiving) entity).enablePersistence();
					((EntityLiving) entity).onInitialSpawn(world.getDifficultyForLocation(pos), null);
				}
				if(!world.isRemote)
				{
					world.spawnEntity(entity);
				}
			}
		}
    }
}
