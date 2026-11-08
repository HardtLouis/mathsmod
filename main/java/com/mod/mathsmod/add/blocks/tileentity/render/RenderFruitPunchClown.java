package com.mod.mathsmod.add.blocks.tileentity.render;

import java.util.Calendar;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityFruitPunchClown;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderFruitPunchClown extends TileEntitySpecialRenderer<TileEntityFruitPunchClown>
{

    public RenderFruitPunchClown()
    {
    }

    public void render(TileEntityFruitPunchClown te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    	if(!te.broken)
    	{
        	GlStateManager.translate(x + 0.5D, y + 0.72D, z + 0.5D);
        	GlStateManager.rotate(te.rotationYaw, 0, 1, 0);
        	BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        	IBakedModel fpc = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/fruit_punch_clown.json"), "normal"));
        	RenderLaser.renderModel(fpc, 0.8F);
        	GlStateManager.rotate(-te.rotationYaw, 0, 1, 0);
        	GlStateManager.translate(-x - 0.5D, -y - 0.72D, -z - 0.5D);
    	}
    	else
    	{
        	GlStateManager.translate(x + 0.5D, y + 0.72D, z + 0.5D);
        	GlStateManager.rotate(te.rotationYaw, 0, 1, 0);
        	BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        	IBakedModel fpc = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown.json"), "normal"));
        	RenderLaser.renderModel(fpc, 0.5F);
        	
        	
        	GlStateManager.translate(0, 1.5D - 0.72D, 0);
        	GlStateManager.rotate(te.headRotationYaw - te.rotationYaw, 0, 1, 0);
        	GlStateManager.rotate(te.headRotationPitch, 1, 0, 0);
        	IBakedModel fpch = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown_head.json"), "normal"));
        	RenderLaser.renderModel(fpch, 0.5F);
        	GlStateManager.rotate(-te.headRotationPitch, 1, 0, 0);
        	GlStateManager.rotate(te.rotationYaw - te.headRotationYaw, 0, 1, 0);
        	GlStateManager.translate(0, -1.5D + 0.72D, 0);
        	
        	if(te.stack != null && !te.stack.isEmpty())
        	{
            	GlStateManager.translate(-0.8, 0.2, 0.05);
            	Minecraft.getMinecraft().getRenderItem().renderItem(te.stack, TransformType.THIRD_PERSON_RIGHT_HAND);
            	GlStateManager.translate(0.8, -0.2, -0.05);
        	}
        	
        	GlStateManager.rotate(-te.rotationYaw, 0, 1, 0);
        	GlStateManager.translate(-x - 0.5D, -y - 0.72D, -z - 0.5D);
    	}
    }
}