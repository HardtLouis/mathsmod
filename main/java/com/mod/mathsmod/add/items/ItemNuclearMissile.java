package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityNuclearMissile;
import com.mod.mathsmod.add.Entity.Models.ModelAMissile;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNuclearMissile extends ItemLaunchedMissile implements IAnimatedGun
{

	public ItemNuclearMissile()
	{
		super("nuclear_missile");
	}

	@Override
	public EntityMissile getMissile(World world, BlockPos pos, BlockPos target)
	{
		EntityNuclearMissile entity = new EntityNuclearMissile(world, target);
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
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/nuclear_missile.png");
	}

}
