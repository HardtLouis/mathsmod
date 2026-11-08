package com.mod.mathsmod.add.Potions;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionEffectCharge extends Potion
{

	public PotionEffectCharge() {
		super(false, new Color(0, 0, 0).getRGB());
		this.setPotionName("charge");
		this.setRegistryName("charge");
		//this.shouldRenderInvText(new PotionEffect(this, 300));
		this.setIconIndex(7, 0);
	}
	
	@SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/potion_effects/effects.png"));
        return super.getStatusIconIndex();
    }
}
