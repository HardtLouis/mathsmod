package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityRebondiGrenade extends Entity
{
	
	private static final DataParameter<String> USER_ID = EntityDataManager.<String>createKey(EntityRebondiGrenade.class, DataSerializers.STRING);
	private EntityLivingBase thrower;
	double prevMotionY;
	int rebondi;
	
	public EntityRebondiGrenade(World worldIn) 
	{
		super(worldIn);
        this.setSize(0.3F, 0.3F);
	}

	public EntityRebondiGrenade(World worldIn, EntityLivingBase throwerIn) 
	{
		this(worldIn);
		this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight(), throwerIn.posZ);
		if(throwerIn instanceof EntityPlayer)
		{
			this.setUserId(throwerIn.getName());
		}
		else
		{
			this.setUserId(throwerIn.getUniqueID().toString());
		}
	}
	
	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy)
	{

		float inX = (float) (rand.nextGaussian() * 0.007499999832361937F * inaccuracy);
		float inY = (float) (rand.nextGaussian() * 0.007499999832361937F * inaccuracy);
		float inZ = (float) (rand.nextGaussian() * 0.007499999832361937F * inaccuracy);
		Vec3d vec = UtilsVoids.laserWithInnacuracy(velocity, rotationYawIn, rotationPitchIn + pitchOffset, inX, inY, inZ);
		this.motionX = vec.x;
		this.motionY = vec.y;
		this.motionZ = vec.z;
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
	
	public int rebondiLimite()
	{
		return 4;
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
	
	public void onUpdate()
	{
		prevMotionY = this.motionY;
		super.onUpdate();
		this.motionY -= this.getGravityVelocity();
		this.motionY *= 0.999F;

		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if(UtilsVoids.checkBlockCollisionAdv(this.getEntityBoundingBox(), world))
		{
			this.onImpactWithBlock();
		}
		
		if(this.rebondi < 1 && !world.isRemote && ((world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.6F)).size() >= 1 && this.rebondi >= 1) || world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.6F)).size() >= 2 || !world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.6F)).contains(this.getUser())))
		{
			this.hitEntities(world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.6F)));
		}
		
		if(this.onGround)
		{
			this.motionY = -this.prevMotionY;
			this.motionX *= 0.99F;
			this.motionZ *= 0.99F;
			this.rebondi += 1;
		}
		
		if(this.rebondi >= this.rebondiLimite() && !world.isRemote)
		{
			this.Explode();
		}
		
		this.boing();
	}
	
	public abstract void Explode();
	
	public abstract void hitEntities(List<Entity> entity);

	public abstract void onImpactWithBlock();

	public void boing()
	{
		Vec3d vec = this.getPositionVector().addVector(this.motionX, this.motionY, this.motionZ);
		AxisAlignedBB box = new AxisAlignedBB(vec.x - this.width / 2, vec.y, vec.z - this.width / 2, vec.x + this.width / 2, vec.y + this.height, vec.z + this.width / 2);
		if(UtilsVoids.checkBlockCollisionAdv(box, world))
		{
			Vec3d vecTest = this.getPositionVector().addVector(-this.motionX, this.motionY, this.motionZ);
			AxisAlignedBB boxTest = new AxisAlignedBB(vecTest.x - this.width / 2, vec.y, vecTest.z - this.width / 2, vecTest.x + this.width / 2, vecTest.y + this.height, vecTest.z + this.width / 2);

			if(!UtilsVoids.checkBlockCollisionAdv(boxTest, world))
			{
				this.motionX *= -0.3F;
				this.rebondi += 1;
			}
			
			vecTest = this.getPositionVector().addVector(this.motionX, -this.motionY, this.motionZ);
			boxTest = new AxisAlignedBB(vecTest.x - this.width / 2, vec.y, vecTest.z - this.width / 2, vecTest.x + this.width / 2, vecTest.y + this.height, vecTest.z + this.width / 2);

			if(!UtilsVoids.checkBlockCollisionAdv(boxTest, world) && !this.onGround)
			{
				this.motionY *= -0.5F;
				this.motionX *= 0.5F;
				this.motionZ *= 0.5F;
				this.rebondi += 1;
			}
			
			vecTest = this.getPositionVector().addVector(this.motionX, this.motionY, -this.motionZ);
			boxTest = new AxisAlignedBB(vecTest.x - this.width / 2, vec.y, vecTest.z - this.width / 2, vecTest.x + this.width / 2, vecTest.y + this.height, vecTest.z + this.width / 2);

			if(!UtilsVoids.checkBlockCollisionAdv(boxTest, world))
			{
				this.motionZ *= -0.3F;
				this.rebondi += 1;
			}
		}
	}
	
    protected float getGravityVelocity()
    {
        return 0.03F;
    }
    
    @Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setUserId(compound.getString("user_id"));
		this.rebondi = compound.getInteger("rebonds");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setString("user_id", this.getUserId());
		compound.setInteger("rebonds", this.rebondi);
	}
}