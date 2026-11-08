package com.mod.mathsmod.add.Potions;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionEffectFriendofHunters extends Potion
{

	public PotionEffectFriendofHunters() {
		super(false, new Color(132, 0, 200).getRGB());
		this.setPotionName("friend_of_hunters");
		this.setRegistryName("friend_of_hunters");
		this.shouldRenderInvText(new PotionEffect(this, 300));
		this.setIconIndex(3, 0);
	}
	
	@SideOnly(Side.CLIENT)
    public int getStatusIconIndex()
    {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/potion_effects/effects.png"));
        return super.getStatusIconIndex();
    }
}
