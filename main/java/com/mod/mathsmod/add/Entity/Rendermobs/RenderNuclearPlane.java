package com.mod.mathsmod.add.Entity.Rendermobs;

import java.io.File;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Models.ModelPlane;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class RenderNuclearPlane  extends EasyRenderEntity<EntityPlane> {

	public RenderNuclearPlane(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelPlane(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPlane entity) 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/plane/nuclear.png");
	}
	
	protected void preRenderCallback(EntityPlane entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch / 2, 1, 0, 0);
		GlStateManager.rotate(entity.strafing * 20, 0, 0, 1);
        GlStateManager.translate(0, 3.4D, 0);
        GlStateManager.scale(7F, 6F, 6F);
    }
	
	public ThreadDownloadImageData getDownloadImageSkin(String link)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(new ResourceLocation(Mathsmod.MODID, link));
        
        if (itextureobject == null)
        {
            itextureobject = new ThreadDownloadImageData((File)null, link, new ResourceLocation(Mathsmod.MODID, "textures/entity/plane/plane.png"), new ImageBufferDownload());
            GlStateManager.popMatrix();
            texturemanager.loadTexture(new ResourceLocation(Mathsmod.MODID, link), itextureobject);
            GlStateManager.pushMatrix();
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