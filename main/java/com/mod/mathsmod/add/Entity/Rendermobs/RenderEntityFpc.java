package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityFruitPunchClown;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;

public class RenderEntityFpc extends Render<EntityFruitPunchClown>
{
    public RenderEntityFpc(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityFruitPunchClown entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	GlStateManager.translate(x, y + 0.72D, z);
    	GlStateManager.rotate(entity.rotationYaw(), 0, 1, 0);
    	BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
    	IBakedModel fpc = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown.json"), "normal"));
    	RenderLaser.renderModel(fpc, 0.5F);
    	
    	
    	GlStateManager.translate(0, 1.5D - 0.72D, 0);
    	GlStateManager.rotate(entity.rotationYawHead() - entity.rotationYaw(), 0, 1, 0);
    	GlStateManager.rotate(entity.rotationPitch(), 1, 0, 0);
    	IBakedModel fpch = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown_head.json"), "normal"));
    	RenderLaser.renderModel(fpch, 0.5F);
    	GlStateManager.rotate(-entity.rotationPitch(), 1, 0, 0);
    	GlStateManager.rotate(entity.rotationYaw() - entity.rotationYawHead(), 0, 1, 0);
    	GlStateManager.translate(0, -1.5D + 0.72D, 0);
    	
    	GlStateManager.rotate(-entity.rotationYaw(), 0, 1, 0);
    	GlStateManager.translate(-x, -y - 0.72D, -z);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityFruitPunchClown entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}