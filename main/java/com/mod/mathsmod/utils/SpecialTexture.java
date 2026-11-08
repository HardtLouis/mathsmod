package com.mod.mathsmod.utils;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.util.ResourceLocation;

public class SpecialTexture extends AbstractTexture
{
    private static final Logger LOGGER = LogManager.getLogger();
    protected final BufferedImage texture;

    public SpecialTexture(BufferedImage textureResource)
    {
        this.texture = textureResource;
    }

    @Override
	public void loadTexture(IResourceManager resourceManager) throws IOException
    {
        this.deleteGlTexture();
        BufferedImage bufferedimage = texture;
        boolean flag = false;
        boolean flag1 = false;

        TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, flag, flag1);
    }
}