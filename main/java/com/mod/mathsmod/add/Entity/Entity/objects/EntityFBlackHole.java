package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityFBlackHole extends EntityBlackHole
{

	public EntityFBlackHole(World worldIn) 
	{
		super(worldIn);
	}

	public void onUpdate()
	{
		if(this.getSize() < this.maxSize())
		{
			this.setSize(!this.isPulsior ? this.getSize() + 1F : this.getSize() + 0.75F);
			for(Entity entity : UtilsVoids.getEntities(world, this, this.maxSize()))
			{
				if(!(entity instanceof EntityLivingBase) || ((EntityLivingBase) entity).getMaxHealth() < 200)
				{
					UtilsVoids.AttractEntity(this, entity, this.maxSize(), 0.25 * this.maxSize() / 20F * attractMultiplier * (this.getSize() / this.maxSize()) * (this.getSize() / this.maxSize()));
					
					if(entity instanceof EntityPlayerMP)
					{
						Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
					}
				}
			}
		}
		else
		{
			if(!world.isRemote)
			{
				if(this.damageMultiplier > 0)
				{
					PerfectExplosion.EntityBoom(Blocks.AIR, this, this.maxSize() / 2, true, true, false);
				}
				
				for(Entity entity : UtilsVoids.getEntities(world, this, this.maxSize() / 2))
				{
					if(!(entity instanceof EntityLivingBase) || ((EntityLivingBase) entity).getMaxHealth() < 200)
					{
						entity.attackEntityFrom(DamageSource.causeExplosionDamage((EntityLivingBase)null), ((this.maxSize() * 4) - (entity.getDistance(this) * (this.maxSize() / 2.5F))) * this.damageMultiplier);
					}
				}
				
				this.setDead();
			}
		}
	}
}
