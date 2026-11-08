package com.mod.mathsmod.add.CSE;

import java.util.concurrent.CopyOnWriteArrayList;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.messages.MessageBurnEntity;
import com.mod.mathsmod.add.messages.MessageOptimisedShoot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ClientSideEnt 
{
	public Vec3d pos;
	public Vec3d vec;
	public Vec3d speed;
	
	public String userName;
	
	public float damages;
	public ItemBaseBullet bullet;
	
	public float rotationPitch;
	public float rotationYaw;
	
	public int ticks;
	public int dim;
	
	CopyOnWriteArrayList<Entity> list = new CopyOnWriteArrayList<Entity>();
	public int type;
	
	public ClientSideEnt(Vec3d posIn, Vec3d vecIn, Vec3d speedIn, String userNameIn, float damagesIn, ItemBaseBullet bulletIn, float pitchIn, float YawIn, int maxTicksIn, int dimIn)
	{
		pos = posIn;
		vec = vecIn;
		speed = speedIn;
		userName = userNameIn;
		
		damages = damagesIn;
		bullet = bulletIn;
		
		rotationPitch = pitchIn;
		rotationYaw = YawIn;
		
		ticks = maxTicksIn;
		dim = dimIn;
	}
	
	public boolean update(World world)
	{
		this.pos = this.pos.add(this.speed);
		
		if(this.type == 0)
		{
			for(EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos, pos.add(speed.scale(2)))))
			{
				if(entity.getEntityBoundingBox().intersects(pos, pos.add(speed)))
				{
					this.onHitEntity(entity);
				}
			}
		}
		else
		{
			for(EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.addVector(-0.25, -0.25, -0.25), pos.addVector(0.25, 0.25, 0.25))))
			{
				if(!(entity instanceof EntityPlayer) || !entity.getName().equals(this.userName))
				{
					this.onHitEntity(entity);
				}
			}
		}
		
		RayTraceResult res = world.rayTraceBlocks(pos, pos.add(speed), false, true, false);
		if(res != null)
		{
			this.onHitBlock(world, res.getBlockPos(), res.hitVec);
			return true;
		}
		--ticks;
		return ticks <= 0;
	}
	
	public void onHitBlock(World world, BlockPos pos, Vec3d vec)
	{
		Mathsmod.network.sendToServer(new MessageOptimisedShoot(damages, (float) this.pos.x, (float) this.pos.y, (float) this.pos.z, rotationYaw, rotationPitch, null, pos, (float) this.pos.y));
	}
	
	public void onHitEntity(EntityLivingBase hitEntity)
	{
		if(!list.contains(hitEntity) && this.damages > 0.0F && (!(hitEntity instanceof EntityPlayer) || !hitEntity.getName().equals(userName)))
		{
			list.add(hitEntity);
			
			if(this.type == 0)
			{
				Mathsmod.network.sendToServer(new MessageOptimisedShoot(damages, (float) this.pos.x, (float) this.pos.y, (float) this.pos.z, rotationYaw, rotationPitch, hitEntity, null, (float) this.pos.y));
			}
			else if(Math.abs(this.type) == 1)
			{
				Mathsmod.network.sendToServer(new MessageBurnEntity(hitEntity, true, 0.0F));
			}
			else if(Math.abs(this.type) == 4)
			{
				Mathsmod.network.sendToServer(new MessageBurnEntity(hitEntity, false, this.damages));
			}
		}
	}
}
