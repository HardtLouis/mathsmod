package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageParticleEffects;
import com.mod.mathsmod.add.messages.MessageTempAnim;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityIcyJavelin extends Entity
{
	
	int id;

	public EntityIcyJavelin(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		id = -1;
	}
	
	public List<AxisAlignedBB> getHitBoxes()
	{
		List<AxisAlignedBB> list = Lists.newArrayList();
		double velocity = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int n = (int) (velocity / 0.5F);
		for(int i = 0; i < n; ++i)
		{
			Vec3d pos = UtilsVoids.laser(0.5 * i, this.rotationYaw, this.rotationPitch).add(this.getPositionVector());
			list.add(new AxisAlignedBB(pos.x - 0.25, pos.y, pos.z - 0.25, pos.x + 0.25, pos.y + 0.5, pos.z + 0.25));
		}
		
		return list;
	}

	public void shoot(float rotationPitch, float rotationYaw, float speed, EntityLivingBase entity)
	{
		Vec3d vec = UtilsVoids.laser(speed, rotationYaw, rotationPitch);
		this.motionX = vec.x;
		this.motionY = vec.y;
		this.motionZ = vec.z;
		this.rotationYaw = rotationYaw;
		this.rotationPitch = rotationPitch;
		this.id = entity.getEntityId();
	}
	
	@Override
	protected void entityInit() 
	{
	}
	
	@Nullable
	public Entity getThrower()
	{
		if(id >= 0 && this.world.getEntityByID(id) != null)
		{
			return this.world.getEntityByID(id);
		}
		else
		{
			return null;
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		id = compound.getInteger("thrower_id");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("thrower_id", id);
	}
	
	public void onUpdate()
	{
		this.noClip = true;
		super.onUpdate();
		
		if(true)
		{
			try
			{
				for(AxisAlignedBB box : this.getHitBoxes())
				{
					for(Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this, box))
					{
						if(entity instanceof EntityLivingBase && entity.getEntityId() != id)
						{
							if(this.getThrower() instanceof EntityLivingBase && entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()).setDamageBypassesArmor(), 16))
							{
								Mathsmod.network.sendToAllAround(new MessageTempAnim(entity, 3), new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 60));
								if(entity instanceof EntityLivingBase)
								{
									((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 50));
									if(entity instanceof EntityPlayer)
									{
										if(!((EntityLivingBase) entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty())
										{
											try 
											{
												((EntityPlayer) entity).getCooldownTracker().setCooldown(((EntityLivingBase) entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem(), 80);
											} 
											catch (Exception e)
											{
												// AHAHAHAH Encore une autre exception de ce type, serieux
												// pourquoi les lists sont codées comme ca
											}
										}
									}
								}
								this.collision();
							}
						}
					}
				}
			}
			catch(java.util.ConcurrentModificationException except)
			{
				//Ptn cette exception de golmon, baise ta mere
			}
			
			if(UtilsVoids.checkBlockCollisionAdv(this.getEntityBoundingBox(), this.world))
			{
				this.collision();
			}
			
			if(this.ticksExisted > 200)
			{
				this.collision();
			}
		}
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}
	
	protected void collision()
	{
		this.setDead();
		Mathsmod.network.sendToAllAround(new MessageParticleEffects(1, this.getPositionVector(), 0), new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 20));
	}
}