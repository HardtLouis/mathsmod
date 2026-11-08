package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.IOnShot;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySpeedingBoat extends EntityDeltaCarBoat implements IOnShot
{

	public String userName;
	
	public EntitySpeedingBoat(World worldIn) 
	{
		super(worldIn);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.moveLikeEntity(0, 0, 1);
		if(!world.isRemote && (this.userName == null || this.world.getPlayerEntityByName(userName) == null))
		{
			this.setDead();
		}
		
		if(!world.isRemote && this.world.getPlayerEntityByName(userName) != null)
		{
			for(Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(1.0F)))
			{
				EntityPlayer player = this.world.getPlayerEntityByName(userName);
				if(entity instanceof EntityLivingBase)
				{
					if(player instanceof EntityLivingBase && entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, player).setDamageBypassesArmor(), 6))
					{
						Vec3d vec = UtilsVoids.laser(1.4, this.rotationYaw, this.rotationPitch);
						vec.addVector(0, 0.5, 0);
						entity.addVelocity(vec.x, vec.y, vec.z);
						
						if(entity instanceof EntityPlayerMP)
						{
							Mathsmod.network.sendTo(new MessageModifyVelocity(vec.addVector(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
						}
					}
				}
			}
		}
		
		if(this.ticksExisted > 30)
		{
			for(int i = 0; i < 5; i++)
			{
				world.spawnParticle(EnumParticleTypes.END_ROD, this.posX + this.rand.nextDouble() - 0.5F, this.posY, this.posZ + this.rand.nextDouble() - 0.5F, 0, 0.02, 0);
			}
			this.setDead();
		}
	}
	
    @Nullable
    public Entity getControllingPassenger()
    {
        return null;
    }

	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
		return false;
    }
	
	public float getSpeed()
	{
		return 0.45F;
	}
	
	protected void rideOnCollide()
	{
	}

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
	}
}
