package com.mod.mathsmod.add.CSE;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.messages.MessageBurnEntity;
import com.mod.mathsmod.add.messages.MessageOptimisedShoot;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EffectClientSideEnt extends ClientSideEnt
{

	public EffectClientSideEnt(Vec3d posIn, Vec3d speedIn,float pitchIn, float YawIn, int maxTicksIn, int dimIn)
	{
		super(posIn, Vec3d.ZERO, speedIn, null, 0.0F, null, pitchIn, YawIn, maxTicksIn, dimIn);
	}
	

	
	public boolean update(World world)
	{
		this.pos = this.pos.add(this.speed);
		--ticks;
		return ticks <= 0;
	}
	

	
	public void onHitBlock(World world, BlockPos pos, Vec3d vec) {}
	
	public void onHitEntity(EntityLivingBase hitEntity) {}
}
