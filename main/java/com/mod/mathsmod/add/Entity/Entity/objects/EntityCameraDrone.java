package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.utils.DamageSourceGun;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityCameraDrone extends Entity
{
	
	protected static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityCameraDrone.class, DataSerializers.VARINT);

	public EntityCameraDrone(World worldIn) 
	{
		super(worldIn);
		this.setSize(1, 1);
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(ID, -1);
	}
	
	public void setOwner(EntityPlayer entity)
	{
		if(entity != null)
		{
			this.dataManager.set(ID, entity.getEntityId());
		}
		else
		{
			this.dataManager.set(ID, -1);
		}
	}
	
	public EntityPlayer getOwner()
	{
		if(this.dataManager.get(ID) > -1 && world.getEntityByID(this.dataManager.get(ID)) instanceof EntityPlayer)
		{
			return (EntityPlayer) world.getEntityByID(this.dataManager.get(ID));
		}
		else
		{
			return null;
		}
		
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if(source instanceof DamageSourceGun)
		{
			this.setDead();
			if(!world.isRemote)
			{
				world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.CAMERA_DRONE)));
			}
		}
		else if(source != DamageSource.FALL)
		{
			this.setDead();
			return true;
		}
		
		return false;
    }
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if(this.getOwner() == null && this.ticksExisted > 4)
		{
			this.setDead();
		}
		
		if(this.getOwner() != null)
		{
			EntityPlayer entity = this.getOwner();
			/*this.rotationPitch = entity.rotationPitch;
			this.rotationYaw = entity.rotationYaw;*/
			
			if(this.getDistance(this.getOwner()) > 300)
			{
				this.setDead();
			}
			
			if(entity.swingProgress > 0 && this.ticksExisted > 10)
			{
				this.setDead();
				if(!world.isRemote)
				{
					world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.CAMERA_DRONE)));
				}
			}
			
			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.3;
			this.motionY *= 0.3;
			this.motionZ *= 0.3;
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		if(world.getPlayerEntityByName(compound.getString("owner")) != null)
		{
			this.setOwner(world.getPlayerEntityByName(compound.getString("owner")));
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		if(this.getOwner() != null)
		{
			compound.setString("owner", this.getOwner().getName());
		}
	}

}
