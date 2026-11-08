package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerWolfArmor;

import net.minecraft.client.renderer.entity.RenderManager;

public class RenderWolf extends net.minecraft.client.renderer.entity.RenderWolf
{

	public RenderWolf(RenderManager p_i47187_1_) {
		super(p_i47187_1_);
		this.addLayer(new LayerWolfArmor(this));
	}

}
