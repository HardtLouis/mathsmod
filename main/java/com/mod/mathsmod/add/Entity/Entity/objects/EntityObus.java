package com.mod.mathsmod.add.Entity.Entity.objects;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EntityObus extends EntityMissile
{
	public EntityObus(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 1.5F);
	}

	@Override
	protected float getSpeed() {
		return 1;
	}

	@Override
	protected double gravity() {
		return 0.08;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0;
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.MoveLikeEntity(0, 0, 0);
	}

	@Override
	protected float Explosion() {
		return 4;
	}

}
