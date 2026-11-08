package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackHole;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBlackHole extends Render<EntityBlackHole>
{
    public RenderBlackHole(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityBlackHole entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	IBlockState state = RegisterBlocks.BLACK_HOLE.getDefaultState();
    	GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 0.5F, (float)z);
        GlStateManager.color(0, 0, 0);
    	
    	if(state != null && state.getRenderType() == EnumBlockRenderType.MODEL)
    	{
    		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();

            GlStateManager.scale(entity.getSize(), entity.getSize(), entity.getSize());

            this.bindEntityTexture(entity);
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.5F);
            blockrendererdispatcher.renderBlockBrightness(state, 0);
            GlStateManager.translate(0.0F, 0.0F, 1.0F);
            if (this.renderOutlines)
            {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                blockrendererdispatcher.renderBlockBrightness(Blocks.TNT.getDefaultState(), 1.0F);
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
            }
    	}
    	
    	GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBlackHole entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}