package com.mod.mathsmod.add.blocks.tileentity;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityFruitPunchClown;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessagePlaySound;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.ITickable;

public class TileEntityFruitPunchClown extends TileEntity implements ITickable
{

	int time;
	public int brokenTime;
	public int cycles;
	public float rotationYaw;
	public boolean broken;
	public float headRotationYaw;
	public float headRotationPitch;
	public String breakerName;
	public int opportunity;
	public ItemStack stack;
	Random rand = new Random();
	
	public TileEntityFruitPunchClown() 
	{
		time = 0;
	}
	
	@Override
	public void update() 
	{
		if(!broken)
		{
			if(time % 550 == 60)
			{
				if(cycles > 0 || cycles < 0)
				{
					world.playSound(null, getPos(), MathsSounds.CLOWN, SoundCategory.AMBIENT, 4, 1);
					
					if(cycles > 0)
					{
						--cycles;
					}
				}
				else
				{
					world.playSound(null, getPos(), MathsSounds.FRUIT_PUNCH, SoundCategory.AMBIENT, 1000, 1);
					cycles = -1;
					time = 510;
					if(!world.isRemote)
					{
						Vec3d vec = UtilsVoids.laser(1, rotationYaw, 0).addVector(this.pos.getX() + 0.5, this.pos.getY(), this.pos.getZ() + 0.5);
						EntityItem entity = new EntityItem(world, vec.x, vec.y, vec.z, new ItemStack(RegisterItems.FRUIT_PUNCH));
						world.spawnEntity(entity);
					}
				}
			}
			
			if(!world.isRemote)
			{
				++time;
				if(time == 550)
				{
					time = 0;
				}
			}
		}
		else
		{
			++brokenTime;
			if(brokenTime > 1200)
			{
				if(rand.nextInt(1000) == 0 && opportunity == 0)
				{
					opportunity = 1;
				}
				
				Vec3d look = new Vec3d(this.getPos().getX() + 0.5D, this.getPos().getY() + 1.8D, this.getPos().getZ() + 0.5D);
				if(opportunity > 0 && lookedInTheEyes(look))
				{
					--opportunity;
					EntityPlayer player = world.getPlayerEntityByName(breakerName);
					if(player != null)
					{
						this.headRotationYaw = -UtilsVoids.getYawFromPos(look, player.getPositionEyes(1.0F).x, player.getPositionEyes(1.0F).z);
						this.headRotationPitch = -UtilsVoids.getPitchFromPos(player.getPositionEyes(1.0F), look.x, look.y, look.z);
						world.playSound(null, getPos(), SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF, SoundCategory.AMBIENT, 1, 1);
					}
				}
				
				if(brokenTime > 3600)
				{
					if((stack == null || stack.isEmpty()) && rand.nextInt(400) == 0)
					{
						int tr = 0;
						while((stack == null || stack.isEmpty()) && tr < 20)
						{
							for(int i = -10; i <= 10; ++i)
							{
								for(int j = -10; j <= 10; ++j)
								{
									for(int k = -10; k <= 10; ++k)
									{
										if(world.getTileEntity(pos.up(j).north(i).east(k)) != null && world.getTileEntity(pos.up(j).north(i).east(k)) instanceof IInventory)
										{
											IInventory inv = (IInventory) world.getTileEntity(pos.up(j).north(i).east(k));
											if(rand.nextInt(10) == 0 && inv.getSizeInventory() > 0)
											{
												int r = rand.nextInt(inv.getSizeInventory());
												stack = inv.getStackInSlot(r).copy();
												inv.setInventorySlotContents(r, ItemStack.EMPTY);
											}
										}
									}
								}
							}
							tr += 1;
						}
					}
					
					if(brokenTime > 6000)
					{
						if(time > 0)
						{
							time -= 1;
						}
						
						if(this.rand.nextInt(1000) == 0 && time <= 0)
						{
							world.playSound(null, getPos(), MathsSounds.CLOWN, SoundCategory.AMBIENT, 1, 0.7F);
							time = 1100;
						}
						
						//TODO Passage à Entité classique (phase 5)
						if(brokenTime > 0 && !world.isRemote)
						{
							world.setBlockToAir(this.getPos());
							EntityFruitPunchClown entityIn = new EntityFruitPunchClown(world);
							entityIn.setPosition(this.getPos().getX() + 0.5F, this.getPos().getY(), this.getPos().getZ() + 0.5F);
							entityIn.target = world.getPlayerEntityByName(breakerName);
							entityIn.initialPos = new Vec3d(this.getPos().getX() + 0.5F, this.getPos().getY(), this.getPos().getZ() + 0.5F);
							entityIn.setRotations(rotationYaw, headRotationYaw, headRotationPitch);
							world.spawnEntity(entityIn);
						}
					}
				}
			}
		}
	}
	
	public boolean lookedDirectly(Vec3d pos)
	{
		for(EntityPlayer player : UtilsVoids.getEntities(EntityPlayer.class, world, pos, 40))
		{
			Vec3d vec = UtilsVoids.laser(20, player.rotationYaw, player.rotationPitch).add(player.getPositionEyes(1.0F));
			AxisAlignedBB aabb = new AxisAlignedBB(vec.x - 20, vec.y - 20, vec.z - 20, vec.x + 20, vec.y + 20, vec.z + 20);
			if(aabb.contains(pos))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean lookedInTheEyes(Vec3d pos)
	{
		for(EntityPlayer player : UtilsVoids.getEntities(EntityPlayer.class, world, pos, 20))
		{
			Vec3d vec = UtilsVoids.laser(20, player.rotationYaw, player.rotationPitch).add(player.getPositionEyes(1.0F));
			AxisAlignedBB aabb = new AxisAlignedBB(pos.x - 0.2, pos.y - 0.2, pos.z - 0.2, pos.x + 0.2, pos.y + 0.2, pos.z + 0.2);
			if(aabb.intersects(player.getPositionEyes(1.0F), vec))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        compound.setFloat("rotationYaw", rotationYaw);
        compound.setInteger("cycles", cycles);
        compound.setBoolean("broken", broken);
        compound.setFloat("headRotationYaw", headRotationYaw);
        compound.setFloat("headRotationPitch", headRotationPitch);
        compound.setInteger("brokenTime", brokenTime);
        if(breakerName == null || breakerName.isEmpty())
        {
            compound.setString("breakerName", "_");
        }
        else
        {
            compound.setString("breakerName", breakerName);
        }
        
        if(stack == null || stack.isEmpty())
        {
        	NBTTagCompound nbttagcompound = new NBTTagCompound();
            ItemStack.EMPTY.writeToNBT(nbttagcompound);
            compound.setTag("stolen_stack", nbttagcompound);
        }
        else
        {
        	NBTTagCompound nbttagcompound = new NBTTagCompound();
            stack.writeToNBT(nbttagcompound);
            compound.setTag("stolen_stack", nbttagcompound);
        }
        compound.setInteger("opportunity", opportunity);
        return compound;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        rotationYaw = compound.getFloat("rotationYaw");
        cycles = compound.getInteger("cycles");
        broken = compound.getBoolean("broken");
        headRotationYaw = compound.getFloat("headRotationYaw");
        headRotationPitch = compound.getFloat("headRotationPitch");
        brokenTime = compound.getInteger("brokenTime");
        breakerName = compound.getString("breakerName");
        stack = new ItemStack(compound.getCompoundTag("stolen_stack"));
        opportunity = compound.getInteger("opportunity");
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
