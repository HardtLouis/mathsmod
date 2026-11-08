package com.mod.mathsmod.add.event;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityUMissile;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerWolfArmor;
import com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator.*;
import com.mod.mathsmod.add.Entity.Rendermobs.tempAnim.TempAnim;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityEProjector;
import com.mod.mathsmod.add.items.ICustomAnimation;
import com.mod.mathsmod.add.items.ItemInfinityGauntlet;
import com.mod.mathsmod.add.items.ItemArmor.ItemKurayumJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemUnknownHelmet;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderEffects;
import com.mod.mathsmod.utils.RenderLaser;
import com.mod.mathsmod.utils.TextureHelper;
import com.mod.mathsmod.utils.TwoIntAndString;
import com.mod.mathsmod.utils.playerNbtManager;
import com.mojang.authlib.GameProfile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager.DataEntry;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class PlayerRenderManager {

	
	@SubscribeEvent
    public void onPlayerRenderedPre(RenderPlayerEvent.Pre event)
    {
		boolean arms = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.getRenderer(), 0);
		EntityPlayer player = event.getEntityPlayer();
    	Item item = event.getEntityPlayer().getHeldItemMainhand().getItem();
    	
    	String[] disguise = null;
    	boolean hasTransforms = false;
    	
    	Vec3d scale = new Vec3d(1,1,1);
    	Vec3d trans = Vec3d.ZERO;
    	Vec3d color = new Vec3d(1,1,1);
    	
    	GlStateManager.translate(event.getX(), event.getY(), event.getZ());
    	if(client_Proxy.usedSpells.containsKey(event.getEntityPlayer().getName()))
    	{
    		TwoIntAndString tias = client_Proxy.usedSpells.get(event.getEntityPlayer().getName());
    		if(tias.spellName != null && SpellRegistry.REGISTRY.containsKey(tias.spellName))
    		{
    			SpellBase spell = SpellRegistry.REGISTRY.get(client_Proxy.usedSpells.get(event.getEntityPlayer().getName()).spellName);
    			spell.renderUse(tias.phase, tias.progress, player);
    		}
    	}
    	GlStateManager.translate(-event.getX(), -event.getY(), -event.getZ());
    	
    	if(client_Proxy.renderInfos != null && client_Proxy.renderInfos.containsKey(player.getName()))
    	{
    		List<String> list = client_Proxy.renderInfos.get(player.getName());
    		hasTransforms = true;
    		for(String str : list)
    		{
    			if(str.startsWith("scale:"))
    			{
    				String[] str2 = str.split(":");
    				if(str2.length == 4)
    				{
    					scale = new Vec3d(UtilsVoids.getFloatFromString(str2[1]), UtilsVoids.getFloatFromString(str2[2]), UtilsVoids.getFloatFromString(str2[3]));
    				}
    			}
    			
    			if(str.startsWith("trans:"))
    			{
    				String[] str2 = str.split(":");
    				if(str2.length == 4)
    				{
    					trans = new Vec3d(UtilsVoids.getFloatFromString(str2[1]), UtilsVoids.getFloatFromString(str2[2]), UtilsVoids.getFloatFromString(str2[3]));
    				}
    			}
    			
    			if(str.startsWith("color:"))
    			{
    				String[] str2 = str.split(":");
    				if(str2.length == 4)
    				{
    					color = new Vec3d(UtilsVoids.getFloatFromString(str2[1]), UtilsVoids.getFloatFromString(str2[2]), UtilsVoids.getFloatFromString(str2[3]));
    				}
    			}
    			
    			if(str.startsWith("disguise:"))
    			{
    				String[] str2 = str.split(":");
    				if(str2.length == 3)
    				{
        				disguise = new String[] {str2[1], str2[2]};
    				}
    				else if(str2.length == 2)
    				{
        				disguise = new String[] {"minecraft", str2[1]};
    				}
    			}
    		}
    	}
    	
    	double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)event.getPartialRenderTick();
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)event.getPartialRenderTick();
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)event.getPartialRenderTick();

        
        GlStateManager.pushMatrix();
		GlStateManager.translate(trans.x, trans.y, trans.z);
		GlStateManager.scale(scale.x, scale.y, scale.z);
    	GlStateManager.color((float)color.x, (float)color.y, (float)color.z);
    	
    	if(player.getCustomNameTag().contains("Im-Vanished-Baby") || player.isPotionActive(RegisterPotions.DISAPPEAR))
    	{
    		event.getRenderer().setRenderOutlines(false);
    		event.getEntityPlayer().setInvisible(true);
    		event.setCanceled(true);
    	}
    	else
    	{
    		if(player.isPotionActive(RegisterPotions.UBER))
    		{
    			GlStateManager.color(1, 0, 0);
    		}
    		if(item instanceof ItemGun && (!((ItemGun) item).isPistol() || ((ItemGun) item).Animated(player.getHeldItemMainhand(), player)) && (!player.hasCustomName() || !player.getCustomNameTag().contains("-dab")))
        	{
    			event.setCanceled(true);
        		RenderPistolPlayer playerRenderer = new RenderPistolPlayer(event.getRenderer().getRenderManager(), arms, (ItemGun) item);
            	playerRenderer.doRender((AbstractClientPlayer) event.getEntityLiving(), event.getX(), event.getY(), event.getZ(), event.getEntityPlayer().rotationYaw, event.getPartialRenderTick());
        	}
    		else if(item instanceof ItemInfinityGauntlet)
    		{
    			event.setCanceled(true);
    			RenderThanos playerRenderer = new RenderThanos(event.getRenderer().getRenderManager());
            	playerRenderer.doRender((AbstractClientPlayer) event.getEntityLiving(), event.getX(), event.getY(), event.getZ(), event.getEntityPlayer().rotationYaw, event.getPartialRenderTick());
    		}
    		else if(hasTransforms)
    		{
    			if(client_Proxy.renderInfos.containsKey(player.getName()) && client_Proxy.renderInfos.get(player.getName()).contains("dab"))
    	    	{
    				event.setCanceled(true);
    				RenderDabPlayer playerRenderer = new RenderDabPlayer(event.getRenderer().getRenderManager(), arms);
    	        	playerRenderer.doRender((AbstractClientPlayer) event.getEntityLiving(), event.getX(), event.getY(), event.getZ(), event.getEntityPlayer().rotationYaw, event.getPartialRenderTick());
    	    	}
    			else if(disguise != null)
    			{
    				EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(new ResourceLocation(disguise[0], disguise[1]));
    				if(entry != null)
    				{
    					if(!this.isUsingSpell(player))
    					{
    						event.setCanceled(true);
    						this.renderEntity(player, event.getX(), event.getY(), event.getZ(), event.getPartialRenderTick(), entry.newInstance(Minecraft.getMinecraft().player.world));
    					}
    				}
    				else if(Block.REGISTRY.containsKey(new ResourceLocation(disguise[0], disguise[1])))
    				{
    					if(!this.isUsingSpell(player))
    					{
    						event.setCanceled(true);
    						this.renderBlock(event.getEntityPlayer(), d0, d1, d2, Block.REGISTRY.getObject(new ResourceLocation(disguise[0], disguise[1])), event.getPartialRenderTick());
    					}
    				}
    				else
    				{
    					//EntityEntry playerEntry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue("");
    					event.setCanceled(true);
    					String name = disguise[1];
    					arms = TextureHelper.smallArms(name);
    					RenderDisguisePlayer renderer = new RenderDisguisePlayer(event.getRenderer().getRenderManager(), arms, name);
    					renderer.doRender((AbstractClientPlayer) event.getEntityLiving(), event.getX(), event.getY(), event.getZ(), event.getEntityPlayer().rotationYaw, event.getPartialRenderTick());
    				}
    			}
    		}
    	}
    	
    	if(!event.isCanceled() && this.isUsingSpell(player))
    	{
			event.setCanceled(true);
    		RenderPistolPlayer playerRenderer = new RenderPistolPlayer(event.getRenderer().getRenderManager(), arms, null);
        	playerRenderer.doRender((AbstractClientPlayer) event.getEntityLiving(), event.getX(), event.getY(), event.getZ(), event.getEntityPlayer().rotationYaw, event.getPartialRenderTick());
    	}
    	
    	if(!event.isCanceled() && event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ICustomAnimation)
    	{
			event.setCanceled(true);
    		RenderPistolPlayer playerRenderer = new RenderPistolPlayer(event.getRenderer().getRenderManager(), arms, null);
        	playerRenderer.doRender((AbstractClientPlayer) event.getEntityLiving(), event.getX(), event.getY(), event.getZ(), event.getEntityPlayer().rotationYaw, event.getPartialRenderTick());
    		((ICustomAnimation) event.getEntityLiving().getHeldItemMainhand().getItem()).renderThirdPerson(event.getEntityLiving().getHeldItemMainhand(), event.getEntityLiving(), event.getPartialRenderTick());
    	}
    	
    	if(event.isCanceled())
    	{
    		GlStateManager.popMatrix();
    	}
    }
	
	public static void tryModifyRendererForSpell(ModelBiped renderer, EntityPlayer player)
	{
		if(isUsingSpell(player))
		{
			TwoIntAndString tias = client_Proxy.usedSpells.get(player.getName());
			SpellBase spell = tias.spellName != null ? SpellRegistry.REGISTRY.get(tias.spellName) : null;
			
			if(spell != null)
			{
				float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
				float limbSwing = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * partialTicks;
                float limbSwingAmount = player.limbSwing - player.limbSwingAmount * (1.0F - partialTicks);
                
				spell.animateModel(renderer, player, tias.phase, tias.progress, limbSwing, limbSwingAmount, partialTicks);
			}
		}
	}
	
	public static boolean isUsingSpell(EntityPlayer player)
	{
		return client_Proxy.usedSpells.containsKey(player.getName());
	}
	
	@SubscribeEvent
    public void onPlayerRenderedPost(RenderPlayerEvent.Post event)
    {
		EntityPlayer player = event.getEntityPlayer();
		
    	Vec3d scale = new Vec3d(1,1,1);
    	Vec3d trans = Vec3d.ZERO;

		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)event.getPartialRenderTick();
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)event.getPartialRenderTick();
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)event.getPartialRenderTick();
        

    	GlStateManager.popMatrix();
    }
	
	@SubscribeEvent
	public void onRenderHand(RenderHandEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		
		boolean flag = Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityLivingBase && ((EntityLivingBase)Minecraft.getMinecraft().getRenderViewEntity()).isPlayerSleeping();
		
		if((player.getHeldItemMainhand().getItem() instanceof ItemGun || player.getHeldItemMainhand().getItem() instanceof ICustomAnimation) && !flag && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !Minecraft.getMinecraft().gameSettings.hideGUI && !Minecraft.getMinecraft().playerController.isSpectator())
		{
			event.setCanceled(true);
			HandAnimator.renderItemInFirstPerson(event.getPartialTicks());
		}
	}
	
	@SubscribeEvent
	public void renderLivingPre(RenderLivingEvent.Specials.Pre event)
	{
		EntityLivingBase player = event.getEntity();
		String[] disguise = null;
        
		if(client_Proxy.renderInfos.containsKey(player.getName()))
    	{
    		List<String> list = client_Proxy.renderInfos.get(player.getName());
    		for(String str : list)
    		{
    			if(str.startsWith("disguise:"))
    			{
    				String[] str2 = str.split(":");
    				if(str2.length == 3)
    				{
        				disguise = new String[] {str2[1], str2[2]};
    				}
    				else if(str2.length == 2)
    				{
        				disguise = new String[] {"minecraft", str2[1]};
    				}
    			}
    		}
    	}
    		
		if(event.getEntity().getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUnknownHelmet)
		{
			event.setCanceled(true);
		}
		else if(event.getEntity() instanceof EntityPlayer && event.getEntity().hasCustomName())
		{
			if(disguise != null)
			{
				EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(new ResourceLocation(disguise[0], disguise[1]));
				
				if(entry != null)
				{
					event.setCanceled(true);
				}
				else if(Block.REGISTRY.containsKey(new ResourceLocation(disguise[0], disguise[1])))
				{
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void renderLivingPre(RenderLivingEvent.Pre event)
	{
		if(MConfig.getConfig().get(Configuration.CATEGORY_CLIENT, "lgbt mod", false).getBoolean())
		{
			Color col = new Color(RenderLaser.getColorFromAngleQuarter(((float)(event.getEntity().ticksExisted + event.getPartialRenderTick())) / 1000.0F * 20000.0F));
			GlStateManager.color(((float)col.getRed()) / 255, ((float)col.getGreen()) / 255, ((float)col.getBlue()) / 255);
		}
		
		
		Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
		double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)event.getPartialRenderTick();
        double d1 = event.getEntity().lastTickPosY + (event.getEntity().posY - event.getEntity().lastTickPosY) * (double)event.getPartialRenderTick();
        
		for(TempAnim anim : client_Proxy.anims)
		{
			if(anim.entityId.equals(event.getEntity().getUniqueID().toString()))
			{
				if(anim.animIndex == 0 && anim.time <= 20)
				{
					GlStateManager.scale(1, 1 - 0.8 * (((float)anim.time) / 20.0F), 1);
					GlStateManager.translate(0, (d1 - d4) * ((1 / (1 - 0.8 * (((float)anim.time) / 20.0F))) -1), 0);
				}
				else if(anim.time > 20 && anim.animIndex == 0)
				{
					GlStateManager.scale(1, 0.2, 1);
					GlStateManager.translate(0, (d1 - d4) * (4), 0);
				}
				
				if(anim.animIndex == 1)
				{
					GlStateManager.color(1, 0, 0);
				}
				
				if(anim.animIndex == 2)
				{
					GlStateManager.color(1, 1, 0);
				}
				
				if(anim.animIndex == 3)
				{
					GlStateManager.color(0.7F, 0.7F, 1);
					RenderEffects.renderPlayerInIce(new Vec3d(event.getX(), event.getY(), event.getZ()), event.getEntity().height, event.getEntity().width);
					
					if(anim.time <= 1)
					{
						for(int i = 0; i < 20; i++)
	        			{
							float f = event.getEntity().width * 1.43F * 6F;
	        				event.getEntity().world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, event.getEntity().posX, event.getEntity().posY + entity.height * 0.7, event.getEntity().posZ, (event.getEntity().world.rand.nextDouble() - 0.5) * f, (event.getEntity().world.rand.nextDouble() - 0.5) * f, (event.getEntity().world.rand.nextDouble() - 0.5) * f, Block.getIdFromBlock(Blocks.PACKED_ICE));
	        			}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void renderLivingPost(RenderLivingEvent.Post event)
	{
		
		Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
		double d4 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)event.getPartialRenderTick();
        double d1 = event.getEntity().lastTickPosY + (event.getEntity().posY - event.getEntity().lastTickPosY) * (double)event.getPartialRenderTick();
        
		for(TempAnim anim : client_Proxy.anims)
		{
			if(anim.entityId.equals(event.getEntity().getUniqueID().toString()))
			{
				if(anim.animIndex == 0 && anim.time <= 20)
				{
					GlStateManager.translate(0, -(d1 - d4) * ((1 / (1 - 0.8 * (((float)anim.time) / 20.0F))) -1), 0);
					GlStateManager.scale(1, 1/(1 - 0.8 * (((float)anim.time) / 20.0F)), 1);
				}
				else if(anim.time > 20 && anim.animIndex == 0)
				{
					GlStateManager.translate(0, -(d1 - d4) * (4), 0);
					GlStateManager.scale(1, 5, 1);
				}
				
				if(anim.animIndex == 1 || anim.animIndex == 2 || anim.animIndex == 3)
				{
					GlStateManager.color(1, 1, 1);
				}
			}
		}
	}
	
	/*@SubscribeEvent
	public void onTextureStitchPre(TextureStitchEvent.Pre event)
	{
		event.setCanceled(true);
	}*/
	
	/*@SubscribeEvent
	public void onEntityRenderedPre(RenderLivingEvent.Pre event)
    {
    }*/
	
	public static void renderEntity(EntityPlayer entity, double x, double y, double z, float partialTicks, Entity ent)
	{
        GlStateManager.pushMatrix();
        //GlStateManager.rotate(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        ent.rotationPitch = entity.rotationPitch;
        ent.prevRotationPitch = entity.prevRotationPitch;
        ent.rotationYaw = entity.rotationYaw;
        ent.prevRotationYaw = entity.prevRotationYaw;
        ent.setPosition(entity.posX, entity.posY, entity.posZ);
        ent.lastTickPosX = entity.lastTickPosX;
        ent.lastTickPosY = entity.lastTickPosY;
        ent.lastTickPosZ = entity.lastTickPosZ;
        ent.ticksExisted = entity.ticksExisted;
        
        if(ent instanceof EntityLivingBase)
        {
        	((EntityLivingBase) ent).limbSwing = entity.limbSwing;
        	((EntityLivingBase) ent).limbSwingAmount = entity.limbSwingAmount;
        	((EntityLivingBase) ent).prevLimbSwingAmount = entity.prevLimbSwingAmount;
        	((EntityLivingBase) ent).deathTime = entity.deathTime;
        	((EntityLivingBase) ent).hurtTime = entity.hurtTime;
        	((EntityLivingBase) ent).prevRotationYawHead = entity.prevRotationYawHead;
        	((EntityLivingBase) ent).rotationYawHead = entity.rotationYawHead;
        	((EntityLivingBase) ent).renderYawOffset = entity.renderYawOffset;
        	((EntityLivingBase) ent).prevRenderYawOffset = entity.prevRenderYawOffset;
        	((EntityLivingBase) ent).swingProgress = entity.swingProgress;
        	((EntityLivingBase) ent).swingProgressInt = entity.swingProgressInt;
        	((EntityLivingBase) ent).prevSwingProgress = entity.prevSwingProgress;
        	((EntityLivingBase) ent).setHeldItem(EnumHand.MAIN_HAND, entity.getHeldItemMainhand());
        	((EntityLivingBase) ent).setHeldItem(EnumHand.OFF_HAND, entity.getHeldItemOffhand());
        	if(entity.getActiveHand() != null)
           	{
           		((EntityLivingBase) ent).setActiveHand(entity.getActiveHand());
           	}
        }
        
        if(ent instanceof EntityPoulopop)
        {
        	((EntityPoulopop) ent).setSpawnCooldown(0);
        }
        
        if(ent instanceof EntityLiving)
        {
        	EntityLiving e = (EntityLiving) ent;
        	e.setLeftHanded(entity.getPrimaryHand() == EnumHandSide.LEFT);
        }
       // GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
        RenderHelper.enableStandardItemLighting();
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityStatic(ent, partialTicks, false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
	}
	
	private void renderBlock(EntityPlayer entity, double x, double y, double z, Block ent, float partialTicks)
	{
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState state = ent.getDefaultState();
    	GlStateManager.pushMatrix();
    	
    	if(entity != Minecraft.getMinecraft().player)
    	{
    		EntityPlayer client = Minecraft.getMinecraft().player;
    		pos = new BlockPos(x, y, z);
    		
    		double d0 = client.lastTickPosX + (client.posX - client.lastTickPosX) * partialTicks;
	        double d1 = client.lastTickPosY + (client.posY - client.lastTickPosY) * partialTicks;
	        double d2 = client.lastTickPosZ + (client.posZ - client.lastTickPosZ) * partialTicks;
    		
    		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
    		
    		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.translate(-d0 + pos.getX(), -d1 + pos.getY(), -d2 + pos.getZ());
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            blockrendererdispatcher.renderBlockBrightness(state, entity.getBrightness());
            GlStateManager.translate(0.0F, 0.0F, 1.0F);
    	}
    	else
    	{
    		if(state != null)
        	{
        		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();

                Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                GlStateManager.translate(pos.getX() - x, pos.getY() - y, pos.getZ() - z);
                GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                
                blockrendererdispatcher.renderBlockBrightness(state, entity.getBrightness());
                GlStateManager.translate(0.0F, 0.0F, 1.0F);
                /*if (false)
                {
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                    blockrendererdispatcher.renderBlockBrightness(Blocks.TNT.getDefaultState(), 1.0F);
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                }*/
        	}
    	}
    	
    	GlStateManager.popMatrix();
	}
}
