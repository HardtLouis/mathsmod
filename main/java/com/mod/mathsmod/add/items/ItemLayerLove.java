package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemLayerLove extends ItemGunLayer
{

	public ItemLayerLove()
	{
		super("love_layer");
	}

	@Override
	public ResourceLocation getLayer()
	{
		return new ResourceLocation(Mathsmod.MODID, "misc/love");
	}

}
