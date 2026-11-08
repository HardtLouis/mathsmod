package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.add.messages.MessagePlaySound;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFestiveRocket extends EntityRocket
{

	public EntityFestiveRocket(World worldIn)
    {
		super(worldIn);
    }
	
    public EntityFestiveRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
    	super(worldIn, throwerIn, damagesIn);
    }
    
    public EntityFestiveRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenghtIn)
    {
    	super(worldIn, throwerIn, damagesIn, strenghtIn);
    }
    
    public EntityFestiveRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
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
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 3))
		{
			if(world.rayTraceBlocks(this.getPositionVector(), entity.getPositionVector(), false, true, false) == null)
			{
				entity.posY += 1;
				entity.onGround = false;
				UtilsVoids.AttractEntity(this, entity, 10, -4);
				
				if(entity instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
				}
			}
		}
		
		Mathsmod.network.sendToAll(new MessagePlaySound(this.getPositionVector(), 1.0F, 1.0F, "A", 1));
		
		this.setDead();
	}
	
	public void onUpdate()
	{
		if(this.ticksExisted > 40)
		{
			this.explode();
		}
		
		super.onUpdate();
	}
	
	@Override
	protected float getSpeed()
	{
		return 1.5F;
	}
}