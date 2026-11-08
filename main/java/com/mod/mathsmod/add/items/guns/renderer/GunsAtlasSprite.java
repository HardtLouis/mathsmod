package com.mod.mathsmod.add.items.guns.renderer;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

public class GunsAtlasSprite extends TextureAtlasSprite
{

	protected GunsAtlasSprite(String spriteName) 
	{
		super(spriteName);
	}

	public static TextureAtlasSprite makeAtlasSprite(ResourceLocation spriteResourceLocation)
    {
        return TextureAtlasSprite.makeAtlasSprite(spriteResourceLocation);
    }
}
