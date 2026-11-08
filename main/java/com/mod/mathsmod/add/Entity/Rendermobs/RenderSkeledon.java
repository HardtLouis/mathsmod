package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntitySkeledon;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityDrillingMachine;
import com.mod.mathsmod.add.Entity.Models.ModelSkeledon;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDSword;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderSkeledon extends AbstractRenderBaseBoss<EntitySkeledon>
{

	public RenderSkeledon(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSkeledon(), 1);
		this.addLayer(new BossDeathLayer(100, 100, 000));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySkeledon entity) 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/skeledon.png");
	}
    
    public void doRender(EntitySkeledon entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	/*EntityPlayer entityplayer = Minecraft.getMinecraft().player;
    	double posX = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)partialTicks;
    	double posY = entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)partialTicks;
    	double posZ = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)partialTicks;
    	
    	double eX = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks;
    	double eY = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks;
    	double eZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;
        
    	GlStateManager.translate(-x, -y, -z);
    	GlStateManager.translate(eX, eY, eZ);
    	if(entity.deathTime > 0)
		{
			RenderLaser.renderLaser(entity, 0, entity.getPositionVector().addVector(0, -100, 0), entity.deathTime, 100, new ResourceLocation(Mathsmod.MODID, "textures/misc/rainbow.png"));
		}
    	//else if(entity.getAttackType() == 2 && entity.getCooldown() >= 30)
    	{
			GlStateManager.translate(0, entity.height * 0.65F, 0);
			GlStateManager.rotate(-entity.rotationYaw, 0, 1, 0);
			GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
			RenderLaser.renderLaser(entity, 1, entity.getPositionVector().addVector(0, 0, 20), entity.getCooldown() - 30, 60, new ResourceLocation(Mathsmod.MODID, "textures/misc/fire.png"));
			GlStateManager.rotate(-entity.rotationPitch, 1, 0, 0);
			GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
			GlStateManager.translate(0, -entity.height * 0.65F, 0);
    	}
    	GlStateManager.translate(-eX, -eY, -eZ);
    	GlStateManager.translate(x, y, z);*/
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

	protected void preRenderCallback(EntitySkeledon entity, float partialTickTime)
    {
		GlStateManager.scale(4, 4, 4);
		GlStateManager.translate(0, 1F, 0);
		GlStateManager.color(1, 1 - entity.getRed(), 1 - entity.getRed());
		
    }
}
