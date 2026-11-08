package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTrapHarpoon;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderTrapHarpoon extends Render<EntityTrapHarpoon>
{
    private final RenderItem itemRenderer;

    public RenderTrapHarpoon(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.itemRenderer = Minecraft.getMinecraft().getRenderItem();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityTrapHarpoon entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        

        
        if(entity.getThrower() != null)
    	{
        	EntityPlayer pl = Minecraft.getMinecraft().player;
    		Vec3d vec = pl.getPositionEyes(partialTicks).addVector(0, -entity.getThrower().getEyeHeight() +  entity.getThrower().height * 0.4F, 0);
    		
    		RenderLaser.renderLaser(vec, entity.world, 0, entity.getPositionEyes(partialTicks), 1, 100, new ResourceLocation(Mathsmod.MODID, "textures/effects/chains.png"));
    	}
        
        GlStateManager.scale(1.2, 1.2, 1.2);
        GlStateManager.rotate(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(90, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }
        
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        ItemStack stack = new ItemStack(RegisterBlocks.BEAR_TRAP);
        GlStateManager.translate(-0.5F, -0.5F, 1F);
        if(entity.getEntityHit() != null)
        {
            blockrendererdispatcher.renderBlockBrightness(RegisterBlocks.BEAR_TRAP.getStateFromMeta(1), entity.getBrightness());
        }
        else
        {
            blockrendererdispatcher.renderBlockBrightness(RegisterBlocks.BEAR_TRAP.getDefaultState(), entity.getBrightness());
        }

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        
        GlStateManager.disableRescaleNormal();
        GlStateManager.translate(-x, -y, -z);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTrapHarpoon entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}