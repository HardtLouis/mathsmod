package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPoulopopRoot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RenderPoulopopRoot extends Render<EntityPoulopopRoot>
{
    public RenderPoulopopRoot(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityPoulopopRoot entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
                World world = entity.world;
                float time = entity.ticksExisted + partialTicks;

                	
                	
                	BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)x, (float)y, (float)z);

                    if(time < 11)
            		{
            			GlStateManager.scale(0.1F * time, 0.15F * time, 0.1F * time);
            		}
                    else
                    {
                    	GlStateManager.scale(1.1F, 1.65F + time * 1F, 1.1F);
                    }

                    //float f2 = (1.0F - ((float)entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;
                    this.bindEntityTexture(entity);
                    GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.translate(-0.5F, -0.5F, 0.5F);
                    blockrendererdispatcher.renderBlockBrightness(Blocks.LOG.getDefaultState(), entity.getBrightness());
                    GlStateManager.translate(0.0F, 0.0F, 1.0F);

                    if (this.renderOutlines)
                    {
                        GlStateManager.disableOutlineMode();
                        GlStateManager.disableColorMaterial();
                    }

                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                    super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPoulopopRoot entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}