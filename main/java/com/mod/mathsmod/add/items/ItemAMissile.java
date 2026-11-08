package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Models.ModelAMissile;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAMissile extends ItemLaunchedMissile implements IAnimatedGun
{

	public ItemAMissile(String s) {
		super(s);
	}

	@Override
	public EntityMissile getMissile(World world, BlockPos pos, BlockPos target)
	{
		EntityAMissile entity = new EntityAMissile(world, target);
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
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/a_missile.png");
	}

}
