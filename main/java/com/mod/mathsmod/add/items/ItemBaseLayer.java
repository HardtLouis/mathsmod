package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBaseLayer extends ItemGunLayer
{
	
	protected final String LAYER_NAME;
	
	public ItemBaseLayer(String layername, int animType) 
	{
		super(layername + "_layer");
		this.LAYER_NAME = layername + (animType == 0 ? "" : (animType == 1 ? "_move" : "_anim"));
	}

	@Override
	public ResourceLocation getLayer() 
	{
		return new ResourceLocation(Mathsmod.MODID, "misc/" + LAYER_NAME);
	}

}
