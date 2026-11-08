package com.mod.mathsmod.add.Entity.Rendermobs;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderPoulopop extends RenderLiving<EntityPoulopop>
{
	
	List<Vec3d> waterParticleList;
	List<Vec3d> thunderParticleList;
	List<Vec3d> fireParticleList;
	Random rand = new Random();
    private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
	
	public RenderPoulopop(RenderManager rendermanagerIn, ModelBase modelbaseIn) 
	{
		super(rendermanagerIn, modelbaseIn, 0.0F);
	}

	public void doRender(EntityPoulopop entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		if(entity.getSpawnCooldown() > 0)
		{
			float progress = (entity.getSpawnCooldown() - partialTicks) / 100;
			float size = 1;
			
			if(progress < 0.2)
			{
				size = (progress) * 5;
			}
			
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/spawn_texture.png"), new Vec3d(x, y + 0.1F, z), 0.35F * size, -90, progress * 360);
			
			GlStateManager.translate(x, y, z);
			RenderLaser.renderDragonDeath(progress * 100, partialTicks, 0.5F * size, 100, 200, 100, 1F, 0.9F);
			RenderLaser.renderLaser(entity.getPositionEyes(partialTicks), entity.world, 0, entity.getPositionVector().addVector(0, 256 * size, 0), 60 - entity.getSpawnCooldown(), 60, new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/spawn_beam.png"));
			GlStateManager.translate(-x, -y, -z);
			
		}
		
		else if(entity.deathTime > 0)
		{
			float progress = ((float)entity.deathTime + partialTicks) / 200;
			float sizeMult = 1;
			if(entity.deathTime + partialTicks > 180)
			{
				sizeMult = 1 - (((float)entity.deathTime + partialTicks - 180) / 20);
			}
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/death_hole.png"), new Vec3d(x, y + 0.1F, z), 0.35F * sizeMult, -90, progress * 360 * 5);
			
			this.updateDeathParticles(progress, x, y, z);
		}
		
		if(entity.getAttackUsed() != null && entity.getAttackUsed().objectID() == "object.poulopopAttack.sun" && entity.getTimer() > 0 && entity.getTimer() < 40)
		{
			GlStateManager.translate(x, y + 4, z);
			RenderLaser.renderDragonDeath(MathHelper.absMax(0, entity.getTimer()), partialTicks, 1, 255, 255, 255, 3F, 0.9F);
			GlStateManager.translate(-x, -y - 4, -z);
		}
		else if(entity.getAttackUsed() != null && entity.getAttackUsed().objectID() == "object.poulopopAttack.roots" && entity.getTimer() > 0 && entity.getTimer() < 50)
		{
			GlStateManager.translate(x, y + 1, z);
			RenderLaser.renderDragonDeath(MathHelper.absMax(0, entity.getTimer()), partialTicks, 1, 100, 255, 100, 0.2F, 0.9F);
			GlStateManager.translate(-x, -y - 1, -z);
		}
		else if(entity.getAttackUsed() != null && entity.getAttackUsed().objectID() == "object.poulopopAttack.water" && entity.getTimer() > 100 && entity.getTimer() < 200)
		{
			if(entity.getTarget() != null)
			{
				Vec3d vec = entity.getTarget().getPositionEyes(partialTicks).addVector(0, -entity.getTarget().getEyeHeight() + entity.getTarget().height * 0.5F, 0);
				
				if(entity.world.rayTraceBlocks(entity.getPositionEyes(partialTicks), vec, false, true, false) != null)
				{
					vec = entity.world.rayTraceBlocks(entity.getPositionEyes(partialTicks), vec).hitVec;
				}
				
				RenderLaser.renderLaser(vec, entity.world, 0, entity.getPositionEyes(partialTicks), entity.getTimer() - 100, 100, new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/water_beam.png"));
			}
		}
		else if(entity.getAttackUsed() != null && entity.getAttackUsed().objectID() == "object.poulopopAttack.thunder" && entity.getTimer() > 0)
		{
			float progress = (entity.getTimer() + partialTicks) / 60;
			
			Entity entityIn = entity.getTarget();
			if(entityIn != null)
			{
				double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
		        double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
		        double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
		        
		        Entity ent = Minecraft.getMinecraft().player;
		        double p0 = ent.lastTickPosX + (ent.posX - ent.lastTickPosX) * (double)partialTicks;
		        double p1 = ent.lastTickPosY + (ent.posY - ent.lastTickPosY) * (double)partialTicks;
		        double p2 = ent.lastTickPosZ + (ent.posZ - ent.lastTickPosZ) * (double)partialTicks;
				
		        RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/thun_texture.png"), new Vec3d(d0 - p0, d1 - p1 + 0.1, d2 - p2), 0.25F, -90, progress * 360);
			}
		}
		
		if(entity.getTeleportTimer() > 0 && entity.getTarget() != null)
		{
			float progress = (entity.getTeleportTimer() + partialTicks) / 60;
			
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/tel_texture.png"), new Vec3d(x, y + 0.1F, z), 0.25F, -90, progress * 360);
			
			Entity entityIn = entity.getTarget();
			double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
	        double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
	        double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
	        
	        Entity ent = Minecraft.getMinecraft().player;
	        double p0 = ent.lastTickPosX + (ent.posX - ent.lastTickPosX) * (double)partialTicks;
	        double p1 = ent.lastTickPosY + (ent.posY - ent.lastTickPosY) * (double)partialTicks;
	        double p2 = ent.lastTickPosZ + (ent.posZ - ent.lastTickPosZ) * (double)partialTicks;
			
	        RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/tel_texture.png"), new Vec3d(d0 - p0, d1 - p1 + 4, d2 - p2), 0.25F, 90, progress * 360);
		}
		
		this.renderParticles(x, y, z, entity, partialTicks);
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityPoulopop entity) 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/poulopop.png");
	}
	
	protected void preRenderCallback(EntityPoulopop entitylivingbaseIn, float partialTickTime)
    {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
		
		if(entitylivingbaseIn.getSpawnCooldown() > 20)
		{
			float spawn = (((float)entitylivingbaseIn.getSpawnCooldown() -partialTickTime - 20F) / 80F);
			float size = (float) MathHelper.clamp(1.0F - spawn, 0, 1.0F);
			GlStateManager.scale(size, size, size);
		}
		else if(entitylivingbaseIn.deathTime > 0)
		{
			float progress = ((float)entitylivingbaseIn.deathTime + partialTickTime) / 200;
			if(progress < 0.5F)
			{
				GlStateManager.color(1 - progress * 2, 1 - progress * 2, 1 - progress * 2);
			}
			else
			{
				GlStateManager.color(0, 0, 0);
				float size = MathHelper.clamp(1.0F - ((progress - 0.5F) / 0.3F), 0, 1);
				GlStateManager.scale(size, size, size);
			}
		}
		
		if(entitylivingbaseIn.getUsedAttack() == "object.poulopopAttack.sun" && entitylivingbaseIn.getTimer() + partialTickTime > 0)
		{
			float f = (((float)entitylivingbaseIn.getTimer() + partialTickTime) / 20);
			
			if(entitylivingbaseIn.getTimer() + partialTickTime > 10)
			{
				f = 1 - ((((float)entitylivingbaseIn.getTimer() + partialTickTime) - 10) / 10);
			}
			GlStateManager.color(1, 1 - f, 1 - f);
		}
		else if(entitylivingbaseIn.getUsedAttack() == "object.poulopopAttack.roots" && entitylivingbaseIn.getTimer() + partialTickTime > 0)
		{
			float f = (((float)entitylivingbaseIn.getTimer() + partialTickTime) / 10);
			
			if(entitylivingbaseIn.getTimer() + partialTickTime > 10)
			{
				f = 1 - ((((float)entitylivingbaseIn.getTimer() + partialTickTime) - 10) / 10);
			}
			GlStateManager.color(1 - f, 1, 1 - f);
		}
		else if(entitylivingbaseIn.getUsedAttack() == "object.poulopopAttack.water" && entitylivingbaseIn.getTimer() + partialTickTime > 0)
		{
			float f = (((float)entitylivingbaseIn.getTimer() + partialTickTime) / 100);
			
			if(entitylivingbaseIn.getTimer() + partialTickTime > 100)
			{
				f = 1 - (((((float)entitylivingbaseIn.getTimer() + partialTickTime) - 100) / 100) * ((((float)entitylivingbaseIn.getTimer() + partialTickTime) - 100) / 100));
			}
			GlStateManager.color(1 - f, 1 - f, 1);
		}
		else if(entitylivingbaseIn.getUsedAttack() == "object.poulopopAttack.thunder" && entitylivingbaseIn.getTimer() + partialTickTime > 0)
		{
			float f = (((float)entitylivingbaseIn.getTimer() + partialTickTime) / 60);
			GlStateManager.color(1, 1, 1 - f);
		}
    }

	public void renderParticles(double x, double y, double z, EntityPoulopop entity, float partialTickTime)
	{
		if(entity.getUsedAttack() == "object.poulopopAttack.water" && entity.getTimer() + partialTickTime > 0)
		{
			float progress = (entity.getTimer() + partialTickTime) / 200;
			this.updateWaterParticles(progress);
			
			for(Vec3d vec : waterParticleList)
			{
				RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/particle.png"), new Vec3d(x + vec.x - 0.25D, y + vec.y + 1, z + vec.z - 0.25D), 0.05F);
			}
		}
		
		if(entity.getUsedAttack() == "object.poulopopAttack.thunder" && entity.getTimer() + partialTickTime > 0)
		{
			float progress = (entity.getTimer() + partialTickTime) / 200;
			this.updateThunderParticles(progress);
			
			for(Vec3d vec : thunderParticleList)
			{
				RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/spark.png"), new Vec3d(x + vec.x - 0.25D, y + vec.y + 1, z + vec.z - 0.25D), 0.05F);
			}
		}
	}
	
	public void updateWaterParticles(float progress)
	{
		if(waterParticleList == null)
		{
			waterParticleList = Lists.newArrayList();
		}
		
		if(progress < 0.5F)
		{
			waterParticleList.add(new Vec3d(rand.nextDouble() * 15 - 7.5, rand.nextDouble() * 15 - 7.5, rand.nextDouble() * 15 - 7.5));
		}
		
		for(int i = 0; i < waterParticleList.size(); ++i)
		{
			Vec3d vec = waterParticleList.get(i);
			Vec3d newVec = vec.scale(0.99D);
			waterParticleList.set(i, newVec);
			
			if(vec.squareDistanceTo(0, 0, 0) < 0.5F)
			{
				waterParticleList.remove(i);
			}
		}
	}
	
	public void updateThunderParticles(float progress)
	{
		if(thunderParticleList == null)
		{
			thunderParticleList = Lists.newArrayList();
		}
		
		if(progress < 0.5F)
		{
			if(rand.nextFloat() * 10 < 2)
			{
				thunderParticleList.add(new Vec3d(rand.nextDouble() * 15 - 7.5, rand.nextDouble() * 15 - 7.5, rand.nextDouble() * 15 - 7.5));
			}
		}
		
		for(int i = 0; i < thunderParticleList.size(); ++i)
		{
			Vec3d vec = thunderParticleList.get(i);
			Vec3d newVec = vec.scale(0.95D);
			thunderParticleList.set(i, newVec);
			
			if(vec.squareDistanceTo(0, 0, 0) < 0.5F)
			{
				thunderParticleList.remove(i);
			}
		}
	}
	
	public void updateDeathParticles(float progress, double x, double y, double z)
	{
		if(waterParticleList == null)
		{
			waterParticleList = Lists.newArrayList();
		}
		
		if(thunderParticleList == null)
		{
			thunderParticleList = Lists.newArrayList();
		}
		
		if(fireParticleList == null)
		{
			fireParticleList = Lists.newArrayList();
		}
		
		if(progress < 0.5F)
		{
			for(int i = 0; i < 4; ++i)
			{
				float r = rand.nextFloat();
				if(r < 0.33F)
				{
					thunderParticleList.add(new Vec3d(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1));
				}
				else if(r < 0.66)
				{
					waterParticleList.add(new Vec3d(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1));
				}
				else
				{
					fireParticleList.add(new Vec3d(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1));
				}
			}
		}
		
		thunderParticleList = this.deathRepulseParticles(thunderParticleList);
		waterParticleList = this.deathRepulseParticles(waterParticleList);
		fireParticleList = this.deathRepulseParticles(fireParticleList);
		
		for(Vec3d vec : thunderParticleList)
		{
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/spark.png"), new Vec3d(x + vec.x - 0.25D, y + vec.y + 1, z + vec.z - 0.25F), 0.025F);
		}
		
		for(Vec3d vec : waterParticleList)
		{
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/particle.png"), new Vec3d(x + vec.x - 0.25D, y + vec.y + 1, z + vec.z - 0.25F), 0.025F);
		}
		
		for(Vec3d vec : fireParticleList)
		{
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/fire_particle.png"), new Vec3d(x + vec.x - 0.25D, y + vec.y + 1, z + vec.z - 0.25F), 0.025F);
		}
	}
	
	public List<Vec3d> deathRepulseParticles(List<Vec3d> list)
	{
		for(int i = 0; i < list.size(); ++i)
		{
			Vec3d vec = list.get(i);
			Vec3d newVec = vec.scale(1.02D);
			list.set(i, newVec);
			
			if(vec.squareDistanceTo(0, 0, 0) < 0.5F)
			{
				list.remove(i);
			}
		}
		
		return list;
	}
	
	protected boolean setBrightness(EntityPoulopop entitylivingbaseIn, float partialTicks, boolean combineTextures)
    {
        float f = entitylivingbaseIn.getBrightness();
        int i = this.getColorMultiplier(entitylivingbaseIn, f, partialTicks);
        boolean flag = (i >> 24 & 255) > 0;
        boolean flag1 = entitylivingbaseIn.hurtTime > 0;

        if (!flag && !flag1)
        {
            return false;
        }
        else if (!flag && !combineTextures)
        {
            return false;
        }
        else
        {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            this.brightnessBuffer.position(0);

            if (flag1)
            {
                this.brightnessBuffer.put(1.0F);
                this.brightnessBuffer.put(0.0F);
                this.brightnessBuffer.put(0.0F);
                this.brightnessBuffer.put(0.3F);
            }
            else
            {
                float f1 = (float)(i >> 24 & 255) / 255.0F;
                float f2 = (float)(i >> 16 & 255) / 255.0F;
                float f3 = (float)(i >> 8 & 255) / 255.0F;
                float f4 = (float)(i & 255) / 255.0F;
                this.brightnessBuffer.put(f2);
                this.brightnessBuffer.put(f3);
                this.brightnessBuffer.put(f4);
                this.brightnessBuffer.put(1.0F - f1);
            }

            this.brightnessBuffer.flip();
            GlStateManager.glTexEnv(8960, 8705, this.brightnessBuffer);
            GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
            GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            return true;
        }
    }
}
