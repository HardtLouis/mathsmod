package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackHoleMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Models.ModelAMissile;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlackHoleMissile extends ItemLaunchedMissile implements IAnimatedGun
{

	public ItemBlackHoleMissile() 
	{
		super("black_hole_missile");
	}

	@Override
	public EntityMissile getMissile(World world, BlockPos pos, BlockPos target)
	{
		EntityBlackHoleMissile entity = new EntityBlackHoleMissile(world, target);
		return entity;
	}

	@Override
	public modelBaseGun model(float scale) 
	{
		return new ModelAMissile();
	}

	@Override
	public ResourceLocation texture(ItemStack stack) 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/black_hole_missile.png");
	}

}
