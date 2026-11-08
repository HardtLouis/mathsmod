package com.mod.mathsmod.add.Entity.Entity.objects;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAbsorbedBlock extends Entity
{
	
	protected static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityAbsorbedBlock.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> META = EntityDataManager.<Integer>createKey(EntityAbsorbedBlock.class, DataSerializers.VARINT);
	
	public EntityAbsorbedBlock(World worldIn, IBlockState state, BlockPos pos)
	{
		super(worldIn);
		this.setSize(1, 1);
		this.setPosition(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
		this.setID(Block.getIdFromBlock(state.getBlock()));
		this.setMeta(state.getBlock().getMetaFromState(state));
	}
	
	public EntityAbsorbedBlock(World worldIn)
	{
		super(worldIn);
		this.setSize(1, 1);
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(ID, Integer.valueOf(0));
		this.dataManager.register(META, Integer.valueOf(1));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
        if (compound.getInteger("Block") > 0)
        {
            this.setID(compound.getInteger("Block"));
            this.setMeta(compound.getInteger("Data"));
        }
        
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
        compound.setInteger("Block", this.getID());
        compound.setInteger("Data", this.getMeta());
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		
		this.gravity();
		this.motionX *= 0.98;
		this.motionZ *= 0.98;
		if(!world.isRemote)
		{
			if(this.getID() == 0)
			{
				this.setDead();
			}
			if(this.motionX == 0 && this.motionY == 0 && this.motionZ == 0 && this.ticksExisted > 20)
			{
				this.setDead();
				world.spawnEntity(new EntityItem(world, posX, posY, posZ, Block.getBlockById(getID()).getItem(world, new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()))));
			}
			if(this.onGround && this.ticksExisted > 20)
			{
				if(world.getBlockState(new BlockPos(this)).getMaterial().isReplaceable())
				{
					this.setDead();
					if(Block.getBlockById(getID()).canPlaceBlockAt(world, new BlockPos(this)))
					{
						world.setBlockState(new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()));
					}
					else
					{
						this.setDead();
						world.spawnEntity(new EntityItem(world, posX, posY, posZ, Block.getBlockById(getID()).getItem(world, new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()))));
					}
				}
				else
				{
					this.setDead();
					world.spawnEntity(new EntityItem(world, posX, posY, posZ, Block.getBlockById(getID()).getItem(world, new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()))));
				}
			}
		}
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}
	
	public int getID()
    {
    	return this.dataManager.get(ID);
    }
    
    public void setID(int id)
    {
        this.dataManager.set(ID, id);
    }
    
    public int getMeta()
    {
    	return this.dataManager.get(META);
    }
    
    public void setMeta(int meta)
    {
        this.dataManager.set(META, meta);
    }
    
    public void fall(float distance, float damageMultiplier)
    {
    	if(!world.isRemote)
    	{
    		if(world.getBlockState(new BlockPos(this)).getMaterial().isReplaceable())
    		{
    			this.setDead();
    			if(Block.getBlockById(getID()).canPlaceBlockAt(world, new BlockPos(this)))
    			{
    				world.setBlockState(new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()), 2);
    			}
    			else
    			{
    				this.setDead();
    				world.spawnEntity(new EntityItem(world, posX, posY, posZ, Block.getBlockById(getID()).getItem(world, new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()))));
    			}
    		}
    		else
    		{
    			this.setDead();
    			world.spawnEntity(new EntityItem(world, posX, posY, posZ, Block.getBlockById(getID()).getItem(world, new BlockPos(this), Block.getBlockById(getID()).getStateFromMeta(getMeta()))));
    		}
    	}
    }

    protected void gravity()
    {
    	if(!this.hasNoGravity())
		{
    		this.motionY -= 0.02;
    		this.motionY *= 0.9900000190734863D;
		}
    }
    
}
