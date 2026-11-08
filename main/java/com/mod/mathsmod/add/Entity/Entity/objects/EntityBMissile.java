package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.CustomExplosion;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBMissile extends EntityAMissile
{

	public EntityBMissile(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityBMissile(World worldIn, BlockPos pos) 
	{
		super(worldIn, pos);
	}

	protected void onImpact()
	{
		CustomExplosion.EntityBoom(Blocks.FLOWING_LAVA.getStateFromMeta(10), this, 12, true);
		this.setDead();
	}
	
	@Override
	protected float getSpeed() 
	{
		return 0.8F;
	}

}
