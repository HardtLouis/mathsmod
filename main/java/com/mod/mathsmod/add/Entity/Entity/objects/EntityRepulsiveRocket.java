package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.DamageSourceGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRepulsiveRocket extends EntityRocket
{

	public EntityRepulsiveRocket(World worldIn)
    {
		super(worldIn);
    }
	
    public EntityRepulsiveRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
    	super(worldIn, throwerIn, damagesIn);
    }
    
    public EntityRepulsiveRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenghtIn)
    {
    	super(worldIn, throwerIn, damagesIn, strenghtIn);
    }
    
    public EntityRepulsiveRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
    {
    	super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
    }

    
    public void collideEntity(Entity entityIn)
	{
	}
	
	public void collideTank(EntityTank entity)
	{
	}
	
	@SideOnly(Side.CLIENT)
	public Vec3d colorChanges()
	{
		return new Vec3d(0.6, 0, 0.6);
	}
	
	public void explode()
	{
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 6))
		{
			if(world.rayTraceBlocks(this.getPositionVector(), entity.getPositionVector(), false, true, false) == null)
			{
				entity.posY += 1;
				entity.onGround = false;
				UtilsVoids.AttractEntity(this, entity, 10, -5);
				if(this.getThrower() != null && entity == this.getThrower())
				{
					entity.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 10000));
				}
				if(entity instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
				}
			}
		}
		this.setDead();
	}
	
	@Override
	protected float getSpeed()
	{
		return 2F;
	}
}
