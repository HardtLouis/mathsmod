package com.mod.mathsmod.add.Potions;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionEffectExplosion extends Potion
{

	public PotionEffectExplosion() {
		super(false, new Color(200, 100, 100).getRGB());
		this.setPotionName("explosive_man");
		this.setRegistryName("explosive_man");
		this.setIconIndex(1, 2);
	}
	
	@SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/potion_effects/effects.png"));
        return super.getStatusIconIndex();
    }
}
