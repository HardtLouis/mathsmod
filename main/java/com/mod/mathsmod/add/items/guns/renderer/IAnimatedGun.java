package com.mod.mathsmod.add.items.guns.renderer;

import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimatedGun {

	public modelBaseGun model(float scale);
	
	public ResourceLocation texture(ItemStack stack);
}
