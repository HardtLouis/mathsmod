package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityGoldDeathStatue;
import com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator.RenderDisguisePlayer;
import com.mod.mathsmod.utils.RenderMHelper;
import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class RenderGoldenDeathStatue extends Render<EntityGoldDeathStatue>
{

	public RenderGoldenDeathStatue(RenderManager renderManager) 
	{
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGoldDeathStatue entity) 
	{
		return null;
	}
	
	public void doRender(EntityGoldDeathStatue entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		if(entity.getEntityName() != null)
		{
			EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entity.getEntityName()));
			
			if(entry != null)
			{
				Entity ent = entry.newInstance(entity.world);
				
				if(ent instanceof EntityLivingBase)
				{
					GlStateManager.color(172, 132, 0);
					RenderMHelper.renderEntity(entity.posX, entity.posY, entity.posZ, 0, (EntityLivingBase) ent, entity);
				}
			}
			else
			{
				if(!entity.getPName().equals(" "))
				{
					String name = entity.getPName();
					boolean arms = TextureHelper.smallArms(name);
					RenderPlayerModel renderer = new RenderPlayerModel(this.getRenderManager(), name, arms);
					renderer.doRender(entity, x, y, z, entity.rotationYaw, 0);
				}
			}
		}
		else if(!entity.getPName().equals(" "))
		{
			String name = entity.getPName();
			boolean arms = TextureHelper.smallArms(name);
			RenderPlayerModel renderer = new RenderPlayerModel(this.getRenderManager(), name, arms);
			if(entity.getFrozen())
			{
				renderer.frozen = true;
			}
			renderer.doRender(entity, x, y, z, entity.rotationYaw, 0);
		}
		
    }

}
