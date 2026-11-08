package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityChauvoputBlock extends EntityRocket
{
	
	float multiplier;

	public EntityChauvoputBlock(World worldIn)
	{
		super(worldIn);
		this.setSize(1F, 1F);
	}
	
	public EntityChauvoputBlock(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
		super(worldIn, throwerIn, damagesIn);
    }
	
	public EntityChauvoputBlock(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
	{
		super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
	}
	
	public EntityChauvoputBlock(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenght, float rotationYaw, float rotationPitch)
	{
		super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
		this.strenght = strenght;
	}

	@Override
	public void collideEntity(Entity entityIn)
	{
		if(entityIn != this.getThrower() && !world.isRemote && !(entityIn instanceof EntityACBoss) && !(entityIn instanceof EntityChauvoputBlock))
		{
			for(Entity ent : world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(3), null))
			{
				if(ent != this.getThrower() && !(ent instanceof EntityNChauvoput))
				{
					ent.attackEntityFrom(DamageSource.causeExplosionDamage((EntityLivingBase)null).setDamageBypassesArmor(), 10);
				}
			}

			world.createExplosion(this.getThrower(), this.posX, this.posY, this.posZ, this.strenght, true);

			this.setDead();
		}
	}
	
	@Override
	public void explode()
	{
		world.createExplosion(this.getThrower(), this.posX, this.posY, this.posZ, this.strenght, true);
		this.setDead();
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		super.readEntityFromNBT(compound);
		multiplier = compound.getFloat("speed_multiplier");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		super.writeEntityToNBT(compound);
		compound.setFloat("speed_multiplier", multiplier);
	}
	
	public void setMultiplier(float mult)
	{
		multiplier = mult;
	}

	@Override
	public float getSpeed() 
	{
		return 2F * multiplier;
	}

	@SideOnly(Side.CLIENT)
    public boolean canRenderOnFire()
    {
        return true;
    }
}
