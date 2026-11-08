package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityEmmanuelMacron extends Entity
{
	
	Entity target;

	public EntityEmmanuelMacron(World worldIn)
	{
		super(worldIn);
		this.setSize(3, 3);
	}

	@Override
	protected void entityInit()
	{
		
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		if(this.target == null)
		{
			this.target = world.getNearestAttackablePlayer(this, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		}
		else
		{
			UtilsVoids.setLookEntity(target, this);
			if(this.ticksExisted % 60 == 10)
			{
				world.playSound(null, posX, posY, posZ, MathsSounds.PROJET, SoundCategory.AMBIENT, 10, 1);
				if(target instanceof EntityPlayer)
				{
					((EntityPlayer) target).sendStatusMessage(new TextComponentString("you need to pay your taxes, drop a gold ingot to Macron or pay the price"), true);
				}
			}
			Vec3d vec = UtilsVoids.laser(0.2, this.rotationYaw, this.rotationPitch);
			
			this.motionX = vec.x;
			this.motionY = vec.y;
			this.motionZ = vec.z;
			
			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			
			for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(posX - 1.5F, posY, posZ - 1.5F, posX + 1.5F, posY + 3.0F, posZ + 1.5F)))
			{
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.POSITIVE_INFINITY);
				if(entity == target || (entity instanceof EntityItem && ((EntityItem) entity).getItem().getItem() == Items.GOLD_INGOT))
				{
					target = null;
					this.setDead();
				}
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		
	}

}