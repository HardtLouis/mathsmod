package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityEhancedProjectile extends Entity
{
	
	private static final DataParameter<String> USER_ID = EntityDataManager.<String>createKey(EntityEhancedProjectile.class, DataSerializers.STRING);
	private EntityLivingBase thrower;
	protected List alreadyHit = Lists.newArrayList();
	
	public EntityEhancedProjectile(World worldIn) 
	{
		super(worldIn);
        this.setSize(0.1F, 0.1F);
	}

	public EntityEhancedProjectile(World worldIn, EntityLivingBase throwerIn, float strenght, float in) 
	{
		this(worldIn);
		this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight(), throwerIn.posZ);
		float inX = (float) (rand.nextGaussian() * 0.007499999832361937F * in);
		float inY = (float) (rand.nextGaussian() * 0.007499999832361937F * in);
		float inZ = (float) (rand.nextGaussian() * 0.007499999832361937F * in);
		Vec3d vec = UtilsVoids.laserWithInnacuracy(strenght, throwerIn.rotationYaw, throwerIn.rotationPitch, inX, inY, inZ);
		if(throwerIn instanceof EntityPlayer)
		{
			this.setUserId(throwerIn.getName());
		}
		else
		{
			this.setUserId(throwerIn.getUniqueID().toString());
		}
		this.motionX = vec.x;
		this.motionY = vec.y;
		this.motionZ = vec.z;
		this.rotationPitch = throwerIn.rotationPitch;
		this.rotationYaw = throwerIn.rotationYaw;
	}
	
	@Override
	protected void entityInit() 
	{
		this.dataManager.register(USER_ID, "-");
	}
	
	public void setUserId(String i)
	{
		this.dataManager.set(USER_ID, i);
	}
	
	public String getUserId()
	{
		return this.dataManager.get(USER_ID);
	}
	
	@Nullable
    public EntityLivingBase getUser()
    {
        if (this.thrower == null && this.getUserId() != null && this.getUserId() != "-")
        {
            this.thrower = this.world.getPlayerEntityByName(this.getUserId());

            if (this.thrower == null && this.world instanceof WorldServer && this.getUserId() != "-")
            {
                try
                {
                    Entity entity = ((WorldServer)this.world).getEntityFromUuid(UUID.fromString(this.getUserId()));

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
	
	public List<AxisAlignedBB> hitList()
	{
		List list = Lists.newArrayList();
		int n =  MathHelper.ceil(MathHelper.absMax(1,MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ) / this.width));
		for(int i = 0; i < n; ++i)
		{
			float t = ((float) i) / ((float) n);
			double x = this.posX + t * motionX;
			double y = this.posY + t * motionY;
			double z = this.posZ + t * motionZ;
			list.add(new AxisAlignedBB(x - this.width / 2, y, z - this.width / 2, x + this.width / 2, y + this.height, z + this.width / 2));
		}
		
		return list;
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		
		this.motionY -= this.getGravityVelocity();
		this.motionY *= 0.999F;

		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		List<AxisAlignedBB> hitboxs = this.hitList();
		
		for(int i = hitboxs.size() - 1; i >= 0; --i)
		{
			AxisAlignedBB hitbox = hitboxs.get(i);
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, hitbox);
			
			for(Entity ent : list)
			{
				if(!alreadyHit.contains(ent))
				{
					alreadyHit.add(ent);
					this.onImpactWithEntity(ent, hitbox);
				}
			}
		}
		

		
		Vec3d vec = this.getPositionVector().addVector(this.motionX, this.motionY, this.motionZ).addVector(0, this.height / 2, 0);
		Vec3d vec2 = new Vec3d(this.prevPosX, this.prevPosY, this.prevPosZ).addVector(0, this.height / 2, 0);
		RayTraceResult res = world.rayTraceBlocks(vec2, vec, false, true, false);
		
		if(res != null && !world.isRemote)
		{
			this.onImpactWithBlock(res);
		}
	}

	protected abstract void onImpactWithBlock(RayTraceResult res);
	
	protected abstract void onImpactWithEntity(Entity entity, AxisAlignedBB box);
	
    protected abstract float getGravityVelocity();
    
    @Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setUserId(compound.getString("user_id"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setString("user_id", this.getUserId());
	}
}