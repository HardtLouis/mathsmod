package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.UUID;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class EntityTargetDrone extends Entity
{
	
	protected float power;
	
	protected int explodeTime;
	
	public Vec3d basePos;
	public Vec3d target;
	
	private String throwerName;
	private EntityLivingBase thrower;

	public EntityTargetDrone(World worldIn) 
	{
		super(worldIn);
		this.setSize(1, 1);
	}
	
	public EntityTargetDrone(World worldIn, EntityLivingBase base, BlockPos target) 
	{
		this(worldIn);
		this.target = new Vec3d(target.getX() + 0.5D, target.getY() + 1, target.getZ() + 0.5D);
		this.basePos = new Vec3d(base.posX, base.posY, base.posZ);
		this.throwerName = base.getName();
		this.setPosition(base.posX, base.posY, base.posZ);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		if(this.basePos != null && this.target != null)
		{
			if(256 > this.posY && new BlockPos(this.posX, 0, this.posZ).distanceSq(new BlockPos(basePos.x, 0, basePos.z)) < 4)
			{
				if(new BlockPos(this.posX, 0, this.posZ).distanceSq(new BlockPos(target.x, 0, target.z)) > 4)
				{
					this.moveRelative(0, 1, 0, 0.1F);
				}
			}
			else if(256 > this.posY && new BlockPos(this.posX, 0, this.posZ).distanceSq(new BlockPos(target.x, 0, target.z)) < 4)
			{
				this.moveRelative(0, -1, 0, 0.1F);
			}
			else if(0 < this.posY && new BlockPos(this.posX, 0, this.posZ).distanceSq(new BlockPos(target.x, 0, target.z)) < 4)
			{
				
				++this.explodeTime;
				if(this.explodeTime >= 60)
				{
					this.motionY -= 0.08D;
					
					if(this.onGround || this.inWater || this.isInLava())
					{
						world.createExplosion(null, posX, posY, posZ, 8, true);
						this.setDead();
					}
				}
			}
			else
			{
				UtilsVoids.setLookPos(target.x, target.y, target.z, this);
				this.moveRelative(0, 0, 1, this.getSpeed());
			}
			
		}
		
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		this.motionY *= 0.98D;
        this.motionX *= (double)0;
        this.motionZ *= (double)0;
		
		if(target == null)
		{
			this.target = new Vec3d(0, 0, 0);
		}
		
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
		
		if(basePos == null)
		{
			this.basePos = new Vec3d(this.posX, this.posY, this.posZ);
		}
		
		if(this.onGround)
		{
			world.createExplosion(null, posX, posY, posZ, 8, true);
			this.setDead();
		}
	}

	
	private float getSpeed() 
	{
		return this.getDistance(target.x, target.y, target.z) > 100 ? 0.5F : 0.1F;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.power = compound.getFloat("power");
		this.explodeTime = compound.getInteger("timer");
		this.basePos = new Vec3d(compound.getDouble("baseX"), compound.getDouble("baseY"), compound.getDouble("baseZ"));
		this.target = new Vec3d(compound.getDouble("targetX"), compound.getDouble("targetY"), compound.getDouble("targetZ"));
		
		this.throwerName = compound.getString("thrower");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setDouble("baseX", this.basePos.x);
		compound.setDouble("baseY", this.basePos.y);
		compound.setDouble("baseZ", this.basePos.z);
		compound.setDouble("targetX", this.target.x);
		compound.setDouble("targetY", this.target.y);
		compound.setDouble("targetZ", this.target.z);
		
		compound.setFloat("power", this.power);
		
		compound.setInteger("timer", this.explodeTime);
		
		compound.setString("thrower", this.throwerName);
	}

	@Override
	protected void entityInit()
	{
		
	}
	
	@Nullable
    public EntityLivingBase getThrower()
    {
        if (this.thrower == null && this.throwerName != null && !this.throwerName.isEmpty())
        {
            this.thrower = this.world.getPlayerEntityByName(this.throwerName);

            if (this.thrower == null && this.world instanceof WorldServer)
            {
                try
                {
                    Entity entity = ((WorldServer)this.world).getEntityFromUuid(UUID.fromString(this.throwerName));

                    if (entity instanceof EntityLivingBase)
                    {
                        this.thrower = (EntityLivingBase)entity;
                    }
                }
                catch (Throwable var2)
                {
                    this.thrower = null;
                }
            }
        }

        return this.thrower;
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(!source.isMagicDamage())
    	{
    		this.setDead();
    		return true;
    	}
    	else
    	{
    		return false;
    		
    	}
    }
	
	public Ticket getTicket()
	{
		Ticket ticket = this.createTicket();
		if(ticket == null && this != null && this.isAddedToWorld())
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
	
	public void setDead()
	{
		super.setDead();
		
		for(int i = -2; i <= 2; ++i)
		{
			for(int j = -2; j <= 2; ++j)
			{
				ForgeChunkManager.unforceChunk(this.getTicket(), new ChunkPos(chunkCoordX + i, chunkCoordZ + j));
			}
		}
	}
	
	public boolean canBePushed()
    {
        return true;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
	
	public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
}
