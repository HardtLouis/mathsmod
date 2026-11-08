package com.mod.mathsmod.add.Entity.Rendermobs;

import java.io.File;

import org.lwjgl.opengl.GL11;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Models.ModelPlane;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class RenderPlane extends EasyRenderEntity<EntityPlane> {

	public RenderPlane(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelPlane(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPlane entity) {
		
		/*if(entity.getTexture() != null)
		{
			TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
			
			if(this.getDownloadImageSkin(entity.getTexture()) != null)
			{
				return new ResourceLocation(Mathsmod.MODID, entity.getTexture());
			}
		}*/
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/plane/plane.png");
	}
	
	protected void preRenderCallback(EntityPlane entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch / 2, 1, 0, 0);
		GlStateManager.rotate(entity.strafing * 20, 0, 0, 1);
        GlStateManager.translate(0, 1.7D, 0);
        GlStateManager.scale(3F, 3F, 3F);
    }
	
	public ThreadDownloadImageData getDownloadImageSkin(String link)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(new ResourceLocation(Mathsmod.MODID, link));
        
        if (itextureobject == null)
        {
            itextureobject = new ThreadDownloadImageData((File)null, link, new ResourceLocation(Mathsmod.MODID, "textures/entity/plane/plane.png"), new ImageBufferDownload());
            texturemanager.loadTexture(new ResourceLocation(Mathsmod.MODID, link), itextureobject);
        }

        if(itextureobject instanceof ThreadDownloadImageData)
        {
        	 return (ThreadDownloadImageData)itextureobject;
        }
        else
        {
        	return null;
        }
       
    }

}
