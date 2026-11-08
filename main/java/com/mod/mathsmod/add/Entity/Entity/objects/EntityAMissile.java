package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class EntityAMissile extends EntityMissile
{
	
	protected static final DataParameter<Float> TARGET_X = EntityDataManager.<Float>createKey(EntityAMissile.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> TARGET_Y = EntityDataManager.<Float>createKey(EntityAMissile.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> TARGET_Z = EntityDataManager.<Float>createKey(EntityAMissile.class, DataSerializers.FLOAT);

	public EntityAMissile(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.8F, 0.8F);
	}
	
	@Override
	protected void entityInit() 
	{
		super.entityInit();
		this.dataManager.register(TARGET_X, 0F);
		this.dataManager.register(TARGET_Y, 0F);
		this.dataManager.register(TARGET_Z, 0F);
	}
	
	public EntityAMissile(World worldIn, BlockPos pos) 
	{
		this(worldIn);
		this.setTarget(pos);
		this.rotationPitch = -90;
	}
	
	public void setTarget(BlockPos pos)
	{
		this.dataManager.set(TARGET_X, pos.getX() + 0.5F);
		this.dataManager.set(TARGET_Y, pos.getY() + 0.0F);
		this.dataManager.set(TARGET_Z, pos.getZ() + 0.5F);
	}
	
	public void setTarget(Vec3d vec)
	{
		this.dataManager.set(TARGET_X, (float)vec.x);
		this.dataManager.set(TARGET_Y, (float)vec.y);
		this.dataManager.set(TARGET_Z, (float)vec.z);
	}
	
	public Vec3d getTarget()
	{
		float x = dataManager.get(TARGET_X);
		float y = dataManager.get(TARGET_Y);
		float z = dataManager.get(TARGET_Z);
		return new Vec3d(x, y, z);
	}
	
	public float getEyeHeight()
    {
        return 0F;
    }

	@Override
	protected float Explosion()
	{
		return 0;
	}

	@Override
	protected float getSpeed() 
	{
		return 0.6F;
	}

	@Override
	protected double gravity() 
	{
		return 0;
	}

	@Override
	protected float getWaterSlowDown() 
	{
		return 0;
	}

	public void onUpdate()
	{
		super.onUpdate();
		
		if(this.getTarget() == null)
		{
			this.setTarget(new Vec3d(0, 0, 0));
		}
		
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		if(UtilsVoids.distanceIgnoreY(this.getTarget().x, this.getTarget().z, this) > 1)
		{
			this.moveRelative(0, 0, 1, this.getSpeed());
		}
		else
		{
			if(MathHelper.sqrt((this.posY - this.getTarget().y) * (this.posY - this.getTarget().y)) > 1)
			{
				this.setTarget(getTarget().addVector(0, -this.getTarget().y, 0));
				this.motionZ *= 0.5;
				this.motionX *= 0.5;
				this.motionY -= 0.04;
				this.motionY *= 0.98;
			}
			else
			{
				if(this.posY > this.getTarget().y)
				{
					this.setTarget(getTarget().addVector(0, -this.getTarget().y, 0));
					this.motionZ *= 0.5;
					this.motionX *= 0.5;
					this.motionY -= 0.04;
					this.motionY *= 0.98;
				}
				else
				{
					this.motionZ *= 0.5;
					this.motionX *= 0.5;
					this.moveRelative(0, 0, 1, 0.6F);
				}
			}
		}
		
		
		for(int i = 0; i < 5; ++i)
		{
			Random r = new Random();
			world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ
					, (r.nextFloat() - 0.5F) * 0.1F, (r.nextFloat() - 0.5F) * 0.1F, (r.nextFloat() - 0.5F) * 0.1F);
		}
		
		
		this.setLookPos(this.getTarget().x, this.getTarget().y, this.getTarget().z);
		
		for(int i = -2; i <= 2; ++i)
		{
			for(int j = -2; j <= 2; ++j)
			{
				if(i > -2 || i < 2)
				{
					if(j > -2 || j < 2)
					{
						if(!ForgeChunkManager.getPersistentChunksFor(world).containsValue(new ChunkPos(chunkCoordX + i, chunkCoordZ + j)) && !world.getChunkFromChunkCoords(chunkCoordX + i, chunkCoordZ + j).isLoaded())
						{
							ForgeChunkManager.forceChunk(this.getTicket(), new ChunkPos(chunkCoordX + i, chunkCoordZ + j));
						}
					}
					else
					{
						ForgeChunkManager.unforceChunk(this.getTicket(), new ChunkPos(chunkCoordX + i, chunkCoordZ + j));
					}
				}
				else
				{
					ForgeChunkManager.unforceChunk(this.getTicket(), new ChunkPos(chunkCoordX + i, chunkCoordZ + j));
				}
			}
		}
		
		if(!world.isRemote && this.collided)
    	{
			this.onImpact();
    	}
	}
	
	protected void onImpact()
	{
		world.newExplosion(null, posX, posY, posZ, 8, true, true);
		this.setDead();
	}
	
	public void setDead()
	{
		super.setDead();
		
		for(int i = -2; i <= 2; ++i)
		{
			for(int j = -2; j <= 2; ++j)
			{
				ForgeChunkManager.unforceChunk(ForgeChunkManager.requestTicket(Mathsmod.instance, world, Type.NORMAL), new ChunkPos(chunkCoordX + i, chunkCoordZ + j));
			}
		}
	}
	
	public void moveRelative(float strafe, float up, float forward, float friction)
    {
        float f = strafe * strafe + up * up + forward * forward;

        if (f >= 1.0E-4F)
        {
            f = MathHelper.sqrt(f);

            if (f < 1.0F)
            {
                f = 1.0F;
            }

            f = friction / f;
            
            float x = -MathHelper.sin(this.rotationYaw * 0.017453292F) * MathHelper.cos(this.rotationPitch * 0.017453292F);
            float y = -MathHelper.sin((this.rotationPitch) * 0.017453292F);
            float z = MathHelper.cos(this.rotationYaw * 0.017453292F) * MathHelper.cos(this.rotationPitch * 0.017453292F);
            float f23 = MathHelper.sqrt(x * x + y * y + z * z);
            x = x / (float)f23;
            y = y / (float)f23;
            z = z / (float)f23;
            
            this.motionX = x * f;
            this.motionY = y * f;
            this.motionZ = z * f;
        }
    }
	
	public Ticket getTicket()
	{
		Ticket ticket = this.createTicket();
		if(ticket == null && this != null && this.isAddedToWorld() && !this.isDead && !world.isRemote)
		{
			ticket = ForgeChunkManager.requestTicket(Mathsmod.instance, world, Type.ENTITY);
			ticket.bindEntity(this);
		}
		return ticket;
	}
	
	private Ticket createTicket()
	{
		for(Ticket ticket : ForgeChunkManager.getPersistentChunksFor(world).values().asList())
		{
			if(ticket.getEntity() != null && ticket.getEntity() == this)
			{
				return ticket;
			}
		}
		return null;
	}
	
	public void setLookPos(double x, double y, double z)
	{
		double LookposX = x;
		double LookposY = y;
		double LookposZ = z;
		
		double d0 = LookposX - this.posX;
        double d1 = LookposY - (this.posY + this.getEyeHeight());
        double d2 = LookposZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        
        this.rotationYaw = UtilsVoids.updateRotation(this.rotationYaw, f);
        
        float objectifPitch = 0;
        objectifPitch = UtilsVoids.updateRotation(objectifPitch, f1);
        if(this.rotationPitch > objectifPitch)
        {
        	this.rotationPitch = MathHelper.clamp(this.rotationPitch - 0.5F, objectifPitch, 90);
        }
        if(this.rotationPitch < objectifPitch)
        {
        	this.rotationPitch = MathHelper.clamp(this.rotationPitch + 0.5F, -90, objectifPitch);
        }
        
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setTarget(new Vec3d(compound.getDouble("targetX"), compound.getDouble("targetY"), compound.getDouble("targetZ")));
		ticksExisted = compound.getInteger("ticksExisted");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setDouble("targetX", this.getTarget().x);
		compound.setDouble("targetY", this.getTarget().y);
		compound.setDouble("targetZ", this.getTarget().z);
		compound.setInteger("ticksExisted", ticksExisted);
	}
	
}
