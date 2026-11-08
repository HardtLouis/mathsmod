package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityThrowableStone extends EntityThrowable
{
	public int timer;
	protected float damages;
	
    public EntityThrowableStone(World worldIn)
    {
        super(worldIn);
        this.setSize(1, 1);
        damages = 10;
    }

    public EntityThrowableStone(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        damages = 10;
    }

    public EntityThrowableStone(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
        damages = 10;
    }
    
    public void setDamages(float damages)
    {
    	this.damages = damages;
    }
    
    protected void onImpact(RayTraceResult result)
    {
        if(result.entityHit != null && !world.isRemote && result.entityHit != this.getThrower())
        {
        	if(result.entityHit instanceof EntityLivingBase)
			{
        		result.entityHit.attackEntityFrom(MDamageSource.GITAN.setDamageBypassesArmor(), damages);
        		
				this.setDead();
				for(int i = 0; i < 5; ++i)
				{
					world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, posX, posY, posZ, 0, 0, 0, Block.getIdFromBlock(Blocks.COBBLESTONE));
				}
			}
        }
        else if(result.typeOfHit == RayTraceResult.Type.BLOCK)
        {
        	if(world.checkBlockCollision(this.getEntityBoundingBox()))
        	{
        		for(int i = 0; i < 5; ++i)
				{
					world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, posX, posY, posZ, 0, 0, 0, Block.getIdFromBlock(Blocks.COBBLESTONE));
				}
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
    }
    
}