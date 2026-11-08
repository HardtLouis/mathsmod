package com.mod.mathsmod.add.Potions;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionEffectFruitPunch extends Potion
{

	public PotionEffectFruitPunch() {
		super(false, new Color(255, 0, 213).getRGB());
		this.setPotionName("fruit_punch");
		this.setRegistryName("fruit_punch");
		this.setIconIndex(1, 1);
	}
	
	@SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/potion_effects/effects.png"));
        return super.getStatusIconIndex();
    }
}
