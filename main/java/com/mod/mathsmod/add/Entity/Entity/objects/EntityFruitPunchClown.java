package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.EntityCrab;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageCheapJumpscare;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityFruitPunchClown extends Entity
{
	public Entity target;
	int brokenTime;
	public Vec3d initialPos;
	int lookedTicks;
	int tpCooldown;
	int cooldownBfKill;
	int agro;
	protected Random rand = new Random();
	public float baseRotationYaw;
	private static final DataParameter<Float> ROTATION_YAW = EntityDataManager.<Float>createKey(EntityFruitPunchClown.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> HEAD_ROTATION_YAW = EntityDataManager.<Float>createKey(EntityFruitPunchClown.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> ROTATION_PITCH = EntityDataManager.<Float>createKey(EntityFruitPunchClown.class, DataSerializers.FLOAT);

	public EntityFruitPunchClown(World worldIn)
	{
		super(worldIn);
		this.setSize(1F, 1.5F);
		this.initialPos = Vec3d.ZERO;
	}
	
	public float getEyeHeight()
    {
        return 1.8F;
    }

	@Override
	protected void entityInit()
	{
		this.dataManager.register(ROTATION_YAW, 0.0F);
		this.dataManager.register(HEAD_ROTATION_YAW, 0.0F);
		this.dataManager.register(ROTATION_PITCH, 0.0F);
	}
	
	public void setRotations(float yaw, float yawHead, float pitch)
	{
		this.dataManager.set(ROTATION_YAW, yaw);
		this.dataManager.set(HEAD_ROTATION_YAW, yawHead);
		this.dataManager.set(ROTATION_PITCH, pitch);
	}
	
	public float rotationYaw()
	{
		return this.dataManager.get(ROTATION_YAW);
	}
	
	public float rotationPitch()
	{
		return this.dataManager.get(ROTATION_PITCH);
	}
	
	public float rotationYawHead()
	{
		return this.dataManager.get(HEAD_ROTATION_YAW);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		++this.brokenTime;
		if(tpCooldown > 0)
		{
			--tpCooldown;
		}
		
		if(this.target == null && !world.isRemote)
		{
			this.setPosition(this.initialPos.x, this.initialPos.y, this.initialPos.z);
			this.setRotations(this.baseRotationYaw, this.baseRotationYaw, -30);
		}
		else
		{
			if(this.brokenTime <= 2400 && !world.isRemote)
			{
				Vec3d pos = this.initialPos.addVector((this.rand.nextFloat() - 0.5) * 0.2, 0, (this.rand.nextFloat() - 0.5) * 0.2);
				this.setPosition(pos.x, pos.y, pos.z);
				float rYaw = -UtilsVoids.getYawFromEntity(this.getPositionEyes(1.0F), this.target);
				float rPitch = UtilsVoids.getPitchFromPos(this.getPositionEyes(1.0F), this.target.posX, this.target.posY + this.target.getEyeHeight(), this.target.posZ);
				this.setRotations(this.rotationYaw(), rYaw, rPitch);
			}
			if(this.brokenTime > 4800 && tpCooldown == 0 && !world.isRemote)
			{
				for(int i = 0; i < 10; ++i)
				{
					Vec3d las = UtilsVoids.laser(Math.min(-13 + agro / 2, -2), this.target.rotationYaw, 0);
					BlockPos pos1 = new BlockPos(this.target).add(las.x, 0, las.z);
					for(int j = -5; j <= 5; ++j)
					{
						BlockPos pos2 = pos1.add(0, j, 0);
						Vec3d vec = new Vec3d(pos2.getX() + 0.5D, pos2.getY() + 1.8F, pos2.getZ() + 0.5D);
						if(this.canTeleport(pos2) && !this.lookedDirectly(this.getPositionEyes(1.0F)))
						{
							float rYaw = -UtilsVoids.getYawFromEntity(vec, this.target);
							float rPitch = UtilsVoids.getPitchFromPos(vec, this.target.posX, this.target.posY + this.target.getEyeHeight(), this.target.posZ);
							this.setRotations(rYaw - (this.rand.nextFloat() - 0.5F) * 180, rYaw, rPitch);
							this.setPosition(vec.x, pos2.getY(), vec.z);
							world.playSound(null, vec.x, vec.y, vec.z, MathsSounds.YES, SoundCategory.AMBIENT, 1, 0.8F);
							this.agro += 1;
							this.tpCooldown = 100;
							break;
						}
					}
				}
			
				if(this.agro > 20 && this.target.getDistance(this) < 5 && this.cooldownBfKill == 0)
				{
					this.jumpscare();
				}
			}
		}
		
		if(this.getDistance(this.initialPos.x, this.initialPos.y, this.initialPos.z) > 0.3F && this.lookedTicks > 5 && !world.isRemote && agro < 20)
		{
			this.setPosition(this.initialPos.x, this.initialPos.y, this.initialPos.z);
			this.setRotations(this.baseRotationYaw, this.baseRotationYaw, -30);
			this.agro = 0;
		}
		
		if(this.lookedDirectly(this.getPositionEyes(1.0F)))
		{
			this.lookedTicks += 1;
		}
		else
		{
			this.lookedTicks = 0;
		}
		
		if(this.cooldownBfKill > 0)
		{
			--this.cooldownBfKill;
			if(this.cooldownBfKill == 0 && this.target != null)
			{
				this.target.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), Float.POSITIVE_INFINITY);
				this.setDead();
			}
		}
	}
	
	protected void jumpscare() 
	{
		if(this.target instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageCheapJumpscare(), (EntityPlayerMP) this.target);
		}
		this.cooldownBfKill = 30;
		world.playSound(null, this.posX, this.posY + this.getEyeHeight(), this.posZ, MathsSounds.FRUIT_PUNCH, SoundCategory.HOSTILE, 10, 1);
	}

	public boolean lookedDirectly(Vec3d pos)
	{
		for(EntityPlayer player : UtilsVoids.getEntities(EntityPlayer.class, world, pos, 40))
		{
			Vec3d vec = UtilsVoids.laser(20, player.rotationYaw, player.rotationPitch).add(player.getPositionEyes(1.0F));
			AxisAlignedBB aabb = new AxisAlignedBB(vec.x - 20, vec.y - 20, vec.z - 20, vec.x + 20, vec.y + 20, vec.z + 20);
			if(aabb.contains(pos))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean canTeleport(BlockPos pos)
    {
        IBlockState iblockstate = this.world.getBlockState(pos.down());
        IBlockState iblockstate2 = this.world.getBlockState(pos);
        IBlockState iblockstate3 = this.world.getBlockState(pos.up());
        return !iblockstate.getMaterial().isReplaceable() && iblockstate2.getMaterial() == Material.AIR && iblockstate3.getMaterial() == Material.AIR;
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.target = world.getPlayerEntityByName(compound.getString("target"));
		this.brokenTime = compound.getInteger("broken_time");
		this.initialPos = new Vec3d(compound.getDouble("initX"), compound.getDouble("initY"), compound.getDouble("initZ"));
		this.baseRotationYaw = compound.getFloat("base_yaw");
		this.agro = compound.getInteger("agro");
		this.cooldownBfKill = compound.getInteger("cooldown_before_kill");
		this.setRotations(compound.getFloat("entity_yaw"), compound.getFloat("entity_yaw_head"), compound.getFloat("entity_pitch"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setString("target", this.target.getName());
		compound.setInteger("broken_time", this.brokenTime);
		compound.setDouble("initX", this.initialPos.x);
		compound.setDouble("initY", this.initialPos.y);
		compound.setDouble("initZ", this.initialPos.z);
		compound.setFloat("base_yaw", this.baseRotationYaw);
		compound.setInteger("agro", this.agro);
		compound.setInteger("cooldown_before_kill", this.cooldownBfKill);
		compound.setFloat("entity_yaw", this.rotationYaw());
		compound.setFloat("entity_yaw_head", this.rotationYawHead());
		compound.setFloat("entity_pitch", this.rotationPitch());
	}

}