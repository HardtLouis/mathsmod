package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityGrenade extends EntityRebondiGrenade
{
	public int timer;
	protected float damages;
	protected float power;
	protected Random rand = new Random();
	
    public EntityGrenade(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3F, 0.3F);
        power = 1F;
        damages = 10;
    }

    public EntityGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        power = 2.5F;
        damages = 10;
    }

    public EntityGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.setPosition(x, y, z);
        power = 2.5F;
        damages = 10;
    }
    
    public void setDamagesAndPower(float damages, float power)
    {
    	this.damages = damages;
    	this.power = power;
    }
    
    public void multiplyDamagesAndPower(float damages, float power)
    {
    	this.damages *= damages;
    	this.power *= power;
    }

    public static void registerFixesSnowball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Snowball");
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.DRIP_LAVA, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
    
    public int timeToExplode()
    {
    	return 60;
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setInteger("timer", this.timer);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	this.timer = compound.getInteger("timer");
    }
    
    protected float getGravityVelocity()
    {
        return 0.03F;
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	this.timer += 1;
    	
    	for(int i = 0; i < 4; ++i)
        {
    		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0, 0.3, 0);
        }
    	if(this.timer >= this.timeToExplode() && !world.isRemote)
        {
        	this.Explode();
        }
    }
    
}
