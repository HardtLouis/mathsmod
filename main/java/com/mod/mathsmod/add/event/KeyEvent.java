package com.mod.mathsmod.add.event;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.Project;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsKeys;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.CSE.ClientSideEnt;
import com.mod.mathsmod.add.Entity.Entity.EntityBeluga;
import com.mod.mathsmod.add.Entity.Entity.EntitySkeledon;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAirBalloon;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityCameraDrone;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGlider;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHelicopter;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTrapHarpoon;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurret;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityUMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;
import com.mod.mathsmod.add.Entity.Rendermobs.tempAnim.TempAnim;
import com.mod.mathsmod.add.GUI.elements.*;
import com.mod.mathsmod.add.GUI.guiBasics.*;
import com.mod.mathsmod.add.GUI.gunCrafting.GuiGunCraftingHelper;
import com.mod.mathsmod.add.GUI.screen.guiLaunchDrone;
import com.mod.mathsmod.add.GUI.spellManager.GuiSpellHelper;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.contracts.gui.GuiContracts;
import com.mod.mathsmod.add.contractsManager.ContractManager;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.event.MEvents.onShotEvent;
import com.mod.mathsmod.add.items.IHeavyHitterItem;
import com.mod.mathsmod.add.items.ILeftClickItem;
import com.mod.mathsmod.add.items.IReachWeapon;
import com.mod.mathsmod.add.items.ISpecialRightClickItem;
import com.mod.mathsmod.add.items.ItemDroneLauncher;
import com.mod.mathsmod.add.items.ItemFryingPan;
import com.mod.mathsmod.add.items.ItemGunLayer;
import com.mod.mathsmod.add.items.ItemSmasher;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemRedCloth;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemThoriumArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemUnknownHelmet;
import com.mod.mathsmod.add.items.ItemArmor.ItemUseableArmors;
import com.mod.mathsmod.add.items.guns.*;
import com.mod.mathsmod.add.items.guns.accessories.GunAccessoryBase;
import com.mod.mathsmod.add.items.guns.renderer.TESRFamasRendererSystem;
import com.mod.mathsmod.add.items.guns.renderer.superHitRenderer;
import com.mod.mathsmod.add.items.guns.stats.ICustomGunStats;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;
import com.mod.mathsmod.add.messages.*;
import com.mod.mathsmod.add.spell.EnumSpellReaction;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.add.spell.utils.SpellManager;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.CameraHelper;
import com.mod.mathsmod.utils.DamageSourceGun;
import com.mod.mathsmod.utils.EntityConstantSound;
import com.mod.mathsmod.utils.EntityRendererS;
import com.mod.mathsmod.utils.HelicoManager;
import com.mod.mathsmod.utils.RenderEffects;
import com.mod.mathsmod.utils.RenderLaser;
import com.mod.mathsmod.utils.RenderMHelper;
import com.mod.mathsmod.utils.TwoIntAndString;
import com.mod.mathsmod.utils.playerNbtManager;
import com.mod.mathsmod.utils.reachHelper;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderLoader.ShaderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import scala.tools.nsc.interactive.Global.OnTypeError;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class KeyEvent 
{
	
	boolean tankScope = false;
	public static String selectedSpell;
	int pressTicks;
	int rightPressTicks;
	private boolean changed;
	private int dabTicks;
	private double airbornMotionY;
	private int gndTicks;
	public static Vec3d vectorF;
	public static Entity observed;
	public static int showTicks;
	boolean stop;
	
	public static int jumpscareTicks;
	int jump;
	
	public static int hhAttackTick;
	public static IHeavyHitterItem selectedItem;
	
	public static CopyOnWriteArrayList<superHitRenderer> listSuperHits = new CopyOnWriteArrayList<superHitRenderer>();
	Random rand = new Random();
	EntityRendererS renderer = new EntityRendererS(Minecraft.getMinecraft(), Minecraft.getMinecraft().getResourceManager());
	
	@SubscribeEvent
	public void onKeyPressed(InputEvent event)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if(player.world.isRemote)
		{
			if(MathsKeys.reload.isPressed() && player.getHeldItemMainhand().getItem() instanceof ItemGun && ((ItemGun)player.getHeldItemMainhand().getItem()).getCharger(player.getHeldItemMainhand()) < ((ItemGun)player.getHeldItemMainhand().getItem()).getChargerSize())
			{
				Mathsmod.network.sendToServer(new MessageReloadGun());
			}
			
			if(player.getHeldItemMainhand().getItem() instanceof ItemSCS7 || player.getHeldItemMainhand().getItem() == RegisterItems.BAT || player.getHeldItemMainhand().getItem() == RegisterItems.GOLDEN_BAT)
			{
				if(Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed())
				{
					int i = ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, player, 66);
					
					if(!player.onGround && jump < 1 && i == 0)
					{
						player.motionY = 0.42F;
						UtilsVoids.EasyShootEntity(player, player.rotationPitch, player.rotationYaw, player.getAIMoveSpeed() * 1.6F, 0);
						jump += 1;
					}
				}
			}
			
			if(player != null && player.getRidingEntity() instanceof EntityBeluga && Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed())
			{
				Mathsmod.network.sendToServer(new MessageHelicopter());
			}
			
			if(MathsKeys.UseSpell2.isPressed() && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemExplosiveBelt && (client_Proxy.spellUsed == null || !SpellRegistry.REGISTRY.containsKey(client_Proxy.spellUsed)))
			{
				ItemExplosiveBelt item = (ItemExplosiveBelt) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
				Mathsmod.network.sendToServer(new MessagePlayerStartElytra());
				item.onExplUsed(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST), player.world, player);
			}
			
			if(player.getHeldItemMainhand().getItem() == RegisterItems.DISGUISE_KIT && Minecraft.getMinecraft().gameSettings.keyBindUseItem.isPressed())
			{
				Minecraft.getMinecraft().displayGuiScreen(new guiDisguiseKit());
			}
			
			if(player.getHeldItemMainhand().getItem() instanceof ItemMachineGun)
			{
				if(Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isPressed())
				{
					Mathsmod.network.sendToServer(new MessageActivGrenadeLauncher());
					if(player != null)
	            	{
	            		if(player.getHeldItemMainhand().getItem() instanceof ItemMachineGun)
	            		{
	            			ItemStack stack = player.getHeldItemMainhand();
	            			ItemMachineGun item = (ItemMachineGun) player.getHeldItemMainhand().getItem();
	            			
	            			if(item.canEquipModifier() && !item.getModifiers(stack).get(0).isEmpty())
	            			{
	            				ItemStack modifier = item.getModifiers(stack).get(0);
	            				if(modifier.getItem() instanceof GunAccessoryBase)
	            				{
	            					GunAccessoryBase gl = (GunAccessoryBase) modifier.getItem();
	            					gl.middleClick(player.world, item, stack, player);
	            				}
	            			}
	            		}
	            	}
				}
			}
			
			if(Minecraft.getMinecraft().player.getRidingEntity() != null)
			{
				if(Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityPlane)
				{
					if(Minecraft.getMinecraft().gameSettings.keyBindUseItem.isPressed())
					{
						Mathsmod.network.sendToServer(new PlaneBulletMessage(true));
					}
				}
			}
		
			if(MathsKeys.UseSpell1.isPressed() && selectedSpell != null && SpellRegistry.REGISTRY.containsKey(selectedSpell))
			{
				if((client_Proxy.spellUsed == null || !SpellRegistry.REGISTRY.containsKey(client_Proxy.spellUsed)) && (!client_Proxy.spellCooldowns.containsKey(selectedSpell) || client_Proxy.spellCooldowns.get(selectedSpell) <= 0) && client_Proxy.spellCooldown <= 0 && client_Proxy.spellEnergy - SpellRegistry.REGISTRY.get(selectedSpell).cost(player) >= 0)
				{
					client_Proxy.spellUsed = selectedSpell;
					client_Proxy.spellPhase = 0;
					client_Proxy.spellProgress = 0;
				}
			}
			
			if(player.getRidingEntity() instanceof EntityTank)
        	{
        		if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed())
    			{
            		this.tankScope = !this.tankScope;
    			}
        	}
			
			if(player.getHeldItemMainhand().getItem() instanceof IHeavyHitterItem)
			{
				if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed() && this.hhAttackTick <= 0 && ((IHeavyHitterItem) player.getHeldItemMainhand().getItem()).shouldUnpressAttackKey())
				{
					this.selectedItem = (IHeavyHitterItem) player.getHeldItemMainhand().getItem();
					this.hhAttackTick = ((IHeavyHitterItem) player.getHeldItemMainhand().getItem()).hitTime();
				}
			}
		}
		
		
		if(player.getHeldItemMainhand().getItem() instanceof IReachWeapon)
		{
			reachHelper.getMouseOver(Minecraft.getMinecraft().getRenderPartialTicks(), player, (IReachWeapon) player.getHeldItemMainhand().getItem());
			if(reachHelper.result != null && reachHelper.result.entityHit != null && reachHelper.result.entityHit.getDistance(player) > 3)
			{
				if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed())
				{
					reachHelper.clickMouse();
				}
			}
		}
		
		if(MathsKeys.contracts.isPressed())
		{
			//asks for sync
			Mathsmod.network.sendToServer(new MessageContract());
			Minecraft.getMinecraft().displayGuiScreen(new GuiContracts());
		}
	}
	
	@SubscribeEvent
	public void onRenderTick(RenderWorldLastEvent event)
	{
		EntityPlayerSP e = Minecraft.getMinecraft().player;
		float partialTick = Minecraft.getMinecraft().getRenderPartialTicks();
		Minecraft mc = Minecraft.getMinecraft();
		
		for(EntityPlayer entity : e.world.playerEntities)
		{
			if(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
					entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
			{
				ItemUberArmor item = (ItemUberArmor) entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
				
				if(item.getLaser(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST)) >= 100 && entity.getHeldItemMainhand().isEmpty())
				{
					RenderLaser.renderLaser(entity.getPositionEyes(partialTick), e.world, -0.3F, entity.getPositionEyes(partialTick).add(entity.getLook(1.0F).scale(20)), item.getLaser(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST)) - 100, 40, new ResourceLocation(Mathsmod.MODID, "textures/misc/rainbow_move.png"));
				}
			}
			
			if(entity.getHeldItemMainhand().getItem() instanceof ItemMedigun)
			{
				ItemMedigun item = (ItemMedigun) entity.getHeldItemMainhand().getItem();
				
				if(item.getTargetID(entity.getHeldItemMainhand()) != -1 && item.getUsed(entity.getHeldItemMainhand()) > 0 && entity.world.getEntityByID(item.getTargetID(entity.getHeldItemMainhand())) != null)
				{
					Entity ent = entity.world.getEntityByID(item.getTargetID(entity.getHeldItemMainhand()));
					if(ent.getDistance(entity) < 10 && ent.getDistance(entity) > 0)
					{
						//GlStateManager.rotate(-UtilsVoids.getYawFromEntity(ent.getPositionVector(), entity), 0, 1, 0);
						//GlStateManager.rotate(UtilsVoids.getPitchFromPos(ent.getPositionVector().addVector(0, 2, 0), entity.posX, entity.posY + 1, entity.posZ), 1, 0, 0);
						RenderLaser.renderLaser(entity.getPositionEyes(partialTick), e.world, -0.4F, ent.getPositionEyes(partialTick), item.getUsed(entity.getHeldItemMainhand()), 200, new ResourceLocation(Mathsmod.MODID, "textures/misc/medigun.png"));
						//GlStateManager.rotate(-UtilsVoids.getPitchFromPos(ent.getPositionVector().addVector(0, 2, 0), entity.posX, entity.posY + 1, entity.posZ), 1, 0, 0);
						//GlStateManager.rotate(UtilsVoids.getYawFromEntity(ent.getPositionVector(), entity), 0, 1, 0);
					}
				}
			}
		}
		
		Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		
		for(ClientSideEnt cte : client_Proxy.CSEL)
    	{
			if(cte.dim == e.dimension)
			{
				if(cte.type == 0)
				{
					for(float i = 0; i < 1.0F; i += 0.1F)
					{
						e.world.spawnAlwaysVisibleParticle(60, cte.pos.x + cte.speed.x * i, cte.pos.y + cte.speed.y * i, cte.pos.z + cte.speed.z * i, 0, 0, 0);
					}
				}
				else if(cte.type == 1)
				{
					e.world.spawnAlwaysVisibleParticle(61, cte.pos.x, cte.pos.y, cte.pos.z, cte.speed.x, cte.speed.y, cte.speed.z, Block.getIdFromBlock(Blocks.FIRE), 10);
					cte.type = -1;
				}
				else if(cte.type == 2)
				{
			        double d0 = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)partialTicks;
			        double d1 = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)partialTicks;
			        double d2 = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)partialTicks;
			        GlStateManager.translate(-d0, -d1, -d2);
					RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/pub/dark_particle.png"), cte.pos, 0.007F);
					GlStateManager.translate(d0, d1, d2);
				}
				else if(cte.type == 3)
				{
			        double d0 = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)partialTicks;
			        double d1 = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)partialTicks;
			        double d2 = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)partialTicks;
			        GlStateManager.translate(-d0, -d1, -d2);
					RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/snow.png"), cte.pos, 0.007F);
					GlStateManager.translate(d0, d1, d2);
				}
				else if(cte.type == 4)
				{
					e.world.spawnAlwaysVisibleParticle(61, cte.pos.x, cte.pos.y, cte.pos.z, cte.speed.x, cte.speed.y, cte.speed.z, Block.getIdFromBlock(RegisterBlocks.FIRE), 10);
					cte.type = -4;
				}
			}
    	}
			
		if(jumpscareTicks > 0)
		{
			--this.jumpscareTicks;
			Vec3d pos = UtilsVoids.laser(0.6, e.rotationYaw, e.rotationPitch).addVector(rand.nextFloat() * 0.2 - 0.1, -0.05 + rand.nextFloat() * 0.2 - 0.1, rand.nextFloat() * 0.2 - 0.1);
			
			GlStateManager.translate(pos.x, pos.y + 0.72D, pos.z);
	    	GlStateManager.rotate(-e.rotationYaw, 0, 1, 0);
	    	BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
	    	IBakedModel fpc = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown.json"), "normal"));
	    	RenderLaser.renderModel(fpc, 0.5F);
	    	
	    	
	    	GlStateManager.translate(0, 1.5D - 0.72D, 0);
	    	IBakedModel fpch = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown_head.json"), "normal"));
	    	RenderLaser.renderModel(fpch, 0.5F);
	    	GlStateManager.rotate(e.rotationYaw, 0, 1, 0);
	    	GlStateManager.translate(-pos.x, -pos.y - 0.72D, -pos.z);
			
		}
		
        double d0 = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)partialTicks;
        double d1 = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)partialTicks;
        double d2 = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)partialTicks;
        
        GlStateManager.translate(-d0, -d1, -d2);
        
        for(superHitRenderer sh : listSuperHits)
		{
			Entity ent = sh.entity;
			if(ent != null)
			{
				RenderLaser.renderFlatTextureXCentered(new ResourceLocation(Mathsmod.MODID, "textures/effects/super_hit.png"), new Vec3d(ent.posX, ent.posY + ent.height + 1, ent.posZ), -0.0625F);
			}
		}
        
        GlStateManager.translate(d0, d1, d2);
		
		if(mc.player != null && PlayerRenderManager.isUsingSpell(mc.player) && mc.gameSettings.thirdPersonView == 0)
		{
			if(client_Proxy.spellUsed != null && SpellRegistry.REGISTRY.containsKey(client_Proxy.spellUsed))
			{
				GlStateManager.pushMatrix();
				GlStateManager.translate(0, 0, 0);
				SpellBase spell = SpellRegistry.REGISTRY.get(client_Proxy.spellUsed);
				spell.renderFirstPerson(client_Proxy.spellPhase, client_Proxy.spellProgress, mc.player);
				
				GlStateManager.popMatrix();
			}
		}
	}
	
	@SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if(client_Proxy.selectCooldown > 0)
		{
			client_Proxy.selectCooldown--;
		}
		
		for(superHitRenderer sh : listSuperHits)
		{
			if(sh.ticks > 0)
			{
				sh.ticks -= 1;
			}
			else
			{
				listSuperHits.remove(sh);
			}
		}
		
		//TODO new spell implementation
		if(player != null && client_Proxy.spellUsed != null && SpellRegistry.REGISTRY.containsKey(client_Proxy.spellUsed))
		{
			SpellBase spell = SpellRegistry.REGISTRY.get(client_Proxy.spellUsed);
			
			spell.spellUseClient(client_Proxy.spellPhase, client_Proxy.spellProgress, player);
			spell.spellUseCommon(client_Proxy.spellPhase, client_Proxy.spellProgress, player);
			
			if(spell.duration(client_Proxy.spellPhase, player) <= client_Proxy.spellProgress)
			{
				client_Proxy.spellPhase += 1;
				client_Proxy.spellProgress = 0;
				if(spell.phases(player) <= client_Proxy.spellPhase)
				{
					client_Proxy.spellUsed = null;
					client_Proxy.spellEnergy -= spell.cost(player);
					client_Proxy.spellCooldown = spell.cooldown(player);
					client_Proxy.spellCooldowns.put(spell.spellName(), spell.spellCooldown(player));
					Mathsmod.network.sendToServer(new SpellMessage("null", 0, 0));
				}
				else
				{
					Mathsmod.network.sendToServer(new SpellMessage(client_Proxy.spellUsed, client_Proxy.spellProgress, client_Proxy.spellPhase));
				}
			}
			else
			{
				client_Proxy.spellProgress += 1;
				Mathsmod.network.sendToServer(new SpellMessage(client_Proxy.spellUsed, client_Proxy.spellProgress, client_Proxy.spellPhase));
			}
		}
		else if(player != null)
		{
			int rate = 5;
			int regenVal = 1;
			if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemRedCloth)
			{
				rate = ((ItemRedCloth) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).rate();
				regenVal = ((ItemRedCloth) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).regenPower();
			}
			
			if(client_Proxy.spellEnergy <= Mathsmod.MAX_SPELL_ENERGY && player != null && player.ticksExisted % rate == 0)
			{
				client_Proxy.spellEnergy += regenVal;
			}
		}
		
		if(client_Proxy.spellCooldown > 0)
		{
			client_Proxy.spellCooldown--;
		}
		
		
		try
		{
			for(String str : client_Proxy.spellCooldowns.keySet())
			{
				if(client_Proxy.spellCooldowns.get(str) == 1)
				{
					client_Proxy.spellCooldowns.remove(str);
				}
				else
				{
					client_Proxy.spellCooldowns.replace(str, client_Proxy.spellCooldowns.get(str) - 1);
				}
			}
		} 
		catch (Exception e) 
		{
			// TODO: current modification ta mere la pute
		}
		
		
		
		for(TempAnim anim : client_Proxy.anims)
		{
			if(anim.time == 0)
			{
				client_Proxy.anims.remove(anim);
			}
			
			if(anim.time > 0)
			{
				anim.time -= 1;
			}
		}
		
		if(MathsKeys.SpellGui.isKeyDown())
		{
			guiSpellNew.selectSpell(Mouse.getDX(), Mouse.getDY());
		}
		
        if(player != null)
		{
        	boolean dab = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, MathsKeys.dab, 8);
    		
			if(player.getHeldItemMainhand().getItem() instanceof IHeavyHitterItem)
			{
				if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown() && this.hhAttackTick <= 0 && !((IHeavyHitterItem) player.getHeldItemMainhand().getItem()).shouldUnpressAttackKey())
				{
					this.selectedItem = (IHeavyHitterItem) player.getHeldItemMainhand().getItem();
					this.hhAttackTick = ((IHeavyHitterItem) player.getHeldItemMainhand().getItem()).hitTime();
				}
			}
			
        	if(this.hhAttackTick > 0 && player.getHeldItemMainhand().getItem() == this.selectedItem)
        	{
        		if(this.hhAttackTick == this.selectedItem.hitTime() - this.selectedItem.hitFrame())
        		{
        			reachHelper.getMouseOver(Minecraft.getMinecraft().getRenderPartialTicks(), player, null);
                	
        			if(reachHelper.result != null && reachHelper.result.entityHit != null)
        			{
        				reachHelper.attackEntity(player, reachHelper.pointedEntity, true);
        			}
        		}
        		this.hhAttackTick--;
        	}
        	else
        	{
        		this.hhAttackTick = -1;
        	}
        	
        	if(!player.onGround)
        	{
        		this.airbornMotionY = player.motionY;
        		this.gndTicks = 0;
        	}
        	else
        	{
        		if(this.gndTicks == 1 && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == RegisterItems.REBONDI_BOOTS)
        		{
        			player.motionY = -airbornMotionY;
        		}
        		
        		this.gndTicks = Math.min(this.gndTicks + 1, 100);
        	}
        	
        	/*Gloire à Steph pass */
        	
    		if(dab)
    		{
    			Mathsmod.network.sendToServer(new MessageDab(true, dabTicks == 0));
    			if(this.dabTicks == 0)
    			{
    				if(player.getHeldItemMainhand().getItem() instanceof ItemMedigun)
    				{
    					player.world.playSound( ((EntityPlayer) null), player.posX,  player.posY,  player.posZ, MathsSounds.MEEM, SoundCategory.AMBIENT, 3, 1);
    				}
    				else
    				{
    					player.world.playSound( ((EntityPlayer) null), player.posX,  player.posY,  player.posZ, MathsSounds.DAB, SoundCategory.AMBIENT, 3, 1);
    				}
    			}
    			dabTicks += 1;
    		}
    		else if(client_Proxy.renderInfos.containsKey(player.getName()) && client_Proxy.renderInfos.get(player.getName()).contains("dab"))
    		{
    			Mathsmod.network.sendToServer(new MessageDab(false, false));
    			dabTicks = 0;
    		}
    		
    		for(TempAnim anim : client_Proxy.anims)
    		{
    			EntityPlayer pl = player.world.getPlayerEntityByUUID(UUID.fromString(anim.entityId));
    			if(pl != null && anim.animIndex == 1)
    			{
    				player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pl.posX, pl.posY + 1, pl.posZ, 0, 0.1F, 0);
    			}
    			
    			if(player.getHealth() <= 0.0F)
    			{
    				anim.time = 1;
    			}
    		}
    		
    		if(player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityHelicopter)
    		{
    			boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindUseItem, 8);
    			boolean pressed2 = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindJump, 8);
    			boolean attack = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindAttack, 8);
    			
    			if(pressed)
    			{
    				HelicoManager.HelicoAction(0);
    			}
    			else if(pressed2)
    			{
    				HelicoManager.HelicoAction(1);
    			}
    			if(attack)
    			{
    				HelicoManager.HelicoAction(2);
    			}
    		}
    		
        	if(player.getHeldItemMainhand().getItem() instanceof ItemGun)
        	{
        		ItemGun item = (ItemGun) player.getHeldItemMainhand().getItem();
        		
        		if((item.smoothScope() || item.smoothMultiplier(player.getHeldItemMainhand(), player) != 1.0F) && item.getUsed(player.getHeldItemMainhand()) != 0)
        		{
        			renderer.updateRenderer(item.smoothMultiplier() * item.smoothMultiplier(player.getHeldItemMainhand(), player));
        		}
        		else
        		{
        			renderer.resetAll();
        		}
        	}
        	else
    		{
    			renderer.resetAll();
    		}
        	
        	if(player.getHeldItemMainhand().getItem() instanceof ILeftClickItem || player.getHeldItemMainhand().getItem() instanceof ItemFryingPan)
    		{
    			boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindAttack, 8);
    			
    			if(pressed)
    			{
    				if(!(player.getHeldItemMainhand().getItem() instanceof ItemGun) || player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != RegisterItems.KURAYUM_JETPACK || !RegisterItems.KURAYUM_JETPACK.getUsing(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST)))
    				{
    					this.pressTicks += 1;
        				Mathsmod.network.sendToServer(new LeftClickGun(MathHelper.clamp(pressTicks, 0, 200)));
        				if(player.getHeldItemMainhand().getItem() instanceof ILeftClickItem)
        				{
            				((ILeftClickItem) player.getHeldItemMainhand().getItem()).onClientLeftClick(player.world, player, pressTicks);
        				}
        				else if(player.getHeldItemMainhand().getItem() instanceof ItemFryingPan)
        				{
            				((ItemFryingPan) player.getHeldItemMainhand().getItem()).onItemLeftClick(player.world, player, pressTicks);
        				}
    				}
    			}
    			else
    			{
    				if(this.pressTicks != 0)
    				{
    					Mathsmod.network.sendToServer(new LeftClickGun(0));
    					
    					if(player.getHeldItemMainhand().getItem() instanceof ILeftClickItem)
        				{
            				((ILeftClickItem) player.getHeldItemMainhand().getItem()).onClientLeftClick(player.world, player, 0);
        				}
        				else if(player.getHeldItemMainhand().getItem() instanceof ItemFryingPan)
        				{
            				((ItemFryingPan) player.getHeldItemMainhand().getItem()).onItemLeftClick(player.world, player, 0);
        				}
    				}
    				this.pressTicks = 0;
    			}
    		}
        	
        	if(player.getHeldItemMainhand().getItem() instanceof ItemGun && !((ItemGun) player.getHeldItemMainhand().getItem()).autoMod())
    		{
        		
    			boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindUseItem, 8);
    			
    			if(!pressed && !((ItemGun) player.getHeldItemMainhand().getItem()).getReset(player.getHeldItemMainhand()) && !player.getCooldownTracker().hasCooldown(player.getHeldItemMainhand().getItem()))
    			{
    				Mathsmod.network.sendToServer(new MessageResetGun());
    			}
    		}
        	
        	if(player.getHeldItemMainhand().getItem() instanceof ISpecialRightClickItem)
    		{
        		ISpecialRightClickItem item = (ISpecialRightClickItem) player.getHeldItemMainhand().getItem();
    			boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindUseItem, 8);
    			
    			if(pressed && (player.getRidingEntity() == null || (player.getRidingEntity().getControllingPassenger() != player && !(player.getRidingEntity() instanceof IVehicle))))
    			{
    				++this.rightPressTicks;
    				item.onRightClickPressed(player.world, player, this.rightPressTicks);
    				Mathsmod.network.sendToServer(new MessageRightClickMachineGun(this.rightPressTicks));
    			}
    			else if(!pressed && this.rightPressTicks != 0)
    			{
    				this.rightPressTicks = 0;
    				item.onRightClickPressed(player.world, player, 0);
    				Mathsmod.network.sendToServer(new MessageRightClickMachineGun(0));
    			}
    		}
        	
        	if(player.getRidingEntity() != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityPlane)
			{
        		boolean pressed = Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown();
    			
    			if(pressed)
				{
					Mathsmod.network.sendToServer(new PlaneBulletMessage(false));
					if(((EntityPlane) player.getRidingEntity()).canShoot())
        			{
						((EntityPlane) player.getRidingEntity()).Shoot();
        			}
					
				}
			}
        	
        	if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUseableArmors)
			{
        		boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindAttack, 8);
        		boolean pressed1 = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindUseItem, 8);
        		
    			if(pressed && pressed1)
				{
					Mathsmod.network.sendToServer(new PlaneBulletMessage(false));
					((ItemUseableArmors) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).onChestUsed(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST), player.world, player);
				}
			}
        	
        	if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemJetpack)
        	{
        		boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindJump, 8);
        		
        		if(pressed)
        		{
        			ItemJetpack item = (ItemJetpack) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
            		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItemDamage() < item.getMaxDamage())
            		{
            			if(item.getYBoost() > player.motionY)
            			{
            				Mathsmod.network.sendToServer(new MessagePlayerStartElytra());
                			player.fallDistance = 0;
                			Vec3d vec = UtilsVoids.laser(-0.3, player.rotationYaw, 0);
                			player.world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + vec.x, player.posY + 1.4D, player.posZ + vec.z, 0, -0.1D, 0);
                			player.motionY = item.getYBoost();
                			item.onUse(player, player.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
            			}
            		}
        		}
        	}
        	
        	if(Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityCameraDrone || (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityUMissile && ((EntityUMissile) Minecraft.getMinecraft().getRenderViewEntity()).isControlled()))
        	{
        		Entity entity =  Minecraft.getMinecraft().getRenderViewEntity();
        		//Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
        		boolean pressedStop = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindAttack, 8);
        		
        		if(entity != null)
        		{
        			boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindJump, 8);
        			boolean pressed2 = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindForward, 8);
        			boolean pressed3 = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindSneak, 8);
        			boolean pressed4 = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindBack, 8);
        			
        			int y = pressed ? 1 : 0;
        			y += pressed3 ? -1 : 0;
        			int fo = pressed2 ? 1 : 0;
        			fo += pressed4 ? -1 : 0;
        			
        			MouseHelper mh = Minecraft.getMinecraft().mouseHelper;
        			Minecraft mc = Minecraft.getMinecraft();
        			
        			mh.mouseXYChange();
        			
        			float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
                    float f1 = f * f * f * 8.0F;
                    float f2 = (float)mc.mouseHelper.deltaX * f1;
                    float f3 = (float)mc.mouseHelper.deltaY * f1;
        			
        			Mathsmod.network.sendToServer(new MessageControlDrone(entity.getEntityId(), fo, y, f3, f2));
        			
        			if(pressedStop || (entity instanceof EntityUMissile && entity.getDistance(player) > ((EntityUMissile) entity).getControlDistance()))
        			{
        				Mathsmod.network.sendToServer(new EntityMessage(entity.getUniqueID().toString(), "talent"));
        				Minecraft.getMinecraft().setRenderViewEntity(player);
        			}
        		}
        		if(entity == null || entity.isDead || (entity instanceof EntityUMissile && !((EntityUMissile) entity).isControlled()))
        		{
        			Minecraft.getMinecraft().setRenderViewEntity(player);
        		}
        	}
        	else if(!player.isSpectator())
        	{
        		this.setRenderedEntity(player);
        	}
        	
        	if(player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityTurret)
    		{
    			boolean pressed = Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown();
    			
    			if(pressed)
    			{
    				Mathsmod.network.sendToServer(new PlaneBulletMessage(false));
    				if(((EntityTurret) player.getRidingEntity()).canShoot())
    				{
    					((EntityTurret) player.getRidingEntity()).shoot();
    				}
    			}
    		}
        	
        	if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
        		player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor && player.getHeldItemMainhand().isEmpty())
			{
        		boolean pressed = ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, Minecraft.getMinecraft().gameSettings.keyBindAttack, 8);
    			
    			if(pressed)
    			{
    				ItemUberArmor item = (ItemUberArmor) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
    				
    				this.stop = false;
    				Mathsmod.network.sendToServer(new LeftClickGun(0));
    				
    				if(item.getLaser(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST)) >= 100)
    				{
    					player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 10, 10);
    					player.motionX *= 0.01F;
    					player.motionY *= 0.01F;
    					player.motionZ *= 0.01F;
    				}
    				else
    				{
    					player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.AMBIENT, 10, 1);
    				}
    			}
    			else if(!stop)
    			{
    				Mathsmod.network.sendToServer(new LeftClickGun(1));
    				stop = true;
    			}
			}
        	
        	double x = player.posX;
    		double y = player.posY;
    		double z = player.posZ;
    		
        	if(player.getHeldItemMainhand().getItem() instanceof ItemGun
        	&& !((ItemGun) player.getHeldItemMainhand().getItem()).ChargerEmpty(player.getHeldItemMainhand())
            && ((ItemGun) player.getHeldItemMainhand().getItem()).getUsed(player.getHeldItemMainhand()) != 0
            && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
            {
            	float zoom = ((ItemGun) player.getHeldItemMainhand().getItem()).zoomOnUsed() * ((ItemGun) player.getHeldItemMainhand().getItem()).zoomMultiplier(player.getHeldItemMainhand(), player);
                this.zoom(1 / zoom);
            }
        	
        	if(player.onGround && this.jump > 0)
        	{
        		this.jump = 0;
        	}
        	/*for(EntityUMissile entity : UtilsVoids.getEntities(EntityUMissile.class, player.world, player, -1))
        	{
        		if(entity != null)
        		{
        			if(entity.getOwner() == player && entity.getDistance(player) <= entity.getControlDistance())
            		{
        				player.renderOffsetX = (float) (entity.posX - player.posX);
        				player.renderOffsetY = (float) (entity.posY - player.posY);
        				player.renderOffsetZ = (float) (entity.posZ - player.posZ);
            		}
        		}
        	}*/
        	
        	for(ClientSideEnt cte : client_Proxy.CSEL)
        	{
        		if(cte.update(player.world))
        		{
        			client_Proxy.CSEL.remove(cte);
        		}
        	}
        	
        	if(player.getRidingEntity() instanceof EntityAirBalloon)
        	{
        		int dismount = 0;
        		int forward = 0;
        		int up = 0;
        		if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown())
        		{
        			dismount = 1;
        		}
        		
        		if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
        		{
        			up = 1;
        		}
        		
        		if(Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown())
        		{
        			up = -1;
        		}
        		
        		if(Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown())
        		{
        			forward = 1;
        			
        			EntityAirBalloon balloon = (EntityAirBalloon) player.getRidingEntity();
            		
            		double dy = (balloon.rotationYaw - player.rotationYaw) % 360;
    				
    				if(Math.abs(dy) < balloon.manSpeed())
    				{
    					balloon.rotationYaw = (float) player.rotationYaw;
    				}
    				else
    				{
    					if(dy < 0)
    					{
    						balloon.rotationYaw += balloon.manSpeed();
    					}
    					else if(dy > 0)
    					{
    						balloon.rotationYaw -= balloon.manSpeed();
    					}
    				}
    				
    				Vec3d vel = UtilsVoids.laser(0.001, balloon.rotationYaw, 0);
    				Vec3d velBalloon = new Vec3d(balloon.motionX, 0, balloon.motionZ);
    				Vec3d trueVec = velBalloon.add(vel);
    				if(trueVec.lengthVector() > 0.2)
    				{
    					trueVec = trueVec.normalize().scale(0.2);
    				}
    				
    				balloon.motionX = trueVec.x;
    				balloon.motionZ = trueVec.z;
        		}
        		
        		Mathsmod.network.sendToServer(new MessageHandleBalloonMovements(forward, up, dismount, player.getRidingEntity().getEntityId()));
        		if(dismount != 0)
        		{
        			player.dismountRidingEntity();
        		}
        	}
        }
    }
	
	@SubscribeEvent
	public void onPlayerMovement(InputUpdateEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if(player != null && player.getRidingEntity() instanceof EntityAirBalloon)
    	{
			event.getMovementInput().sneak = false;
    	}
	}
	
	/*@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event)
	{
		if(!event.getEntityPlayer().world.isRemote && client_Proxy.spellUsed != null)
		{
			TwoIntAndString tias = new TwoIntAndString(client_Proxy.spellPhase, client_Proxy.spellProgress, client_Proxy.spellUsed);
			if(tias.spellName != null && SpellRegistry.REGISTRY.containsKey(tias.spellName))
			{
				SpellBase spell = SpellRegistry.REGISTRY.get(tias.spellName);
				spell.spellUseMelee(tias.phase, tias.progress, event.getEntityPlayer(), event.getTarget(), event.getEntityPlayer().getHeldItemMainhand());
				if(spell.reaction(tias.phase, tias.progress, event.getEntityPlayer()) == EnumSpellReaction.CANT || spell.reaction(tias.phase, tias.progress, event.getEntityPlayer()) == EnumSpellReaction.CAN_SHOOT)
				{
					event.setCanceled(true);
				}
			}
		}
	}*/
	
	@SubscribeEvent
	public void changeViewAngle(EntityViewRenderEvent.CameraSetup event)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
        if(player != null)
		{
        	/*for(EntityUMissile entity : UtilsVoids.getEntities(EntityUMissile.class, player.world, player, -1))
        	{
        		if(entity != null)
        		{
        			if(entity.getOwner() == player && entity.getDistance(player) <= entity.getControlDistance())
            		{
        				Vec3d vec = new Vec3d(player.posX - entity.posX, player.posY - entity.posY, player.posZ - entity.posZ).rotateYaw(-player.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
        				GlStateManager.translate(-vec.x, 1, -vec.z);
            		}
        		}
        	}*/
        	
        	
        	
        	if(player.getRidingEntity() instanceof EntityPlane)
        	{
        		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 1)
        		{
        			GlStateManager.translate(0, 0, -5);
        		}
        		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2)
        		{
        			GlStateManager.translate(0, 0, 5);
        		}
        	}
        	if(player.getRidingEntity() instanceof EntityTank && player == player.getRidingEntity().getControllingPassenger())
        	{
        		if(tankScope && player.getPassengers().size() <= 1)
        		{
        			if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
            		{
        				Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
            		}
            		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 1)
            		{
            			GlStateManager.translate(0, 0F, ((EntityTank) player.getRidingEntity()).getCameraChanges(false));
            		}
            		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2)
            		{
            			GlStateManager.translate(0, 0, ((EntityTank) player.getRidingEntity()).getCameraChanges(false) + 2);
            		}
        		}
        		else
        		{
        			if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
            		{
            			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
            		}
            		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 1)
            		{
            			GlStateManager.translate(0, 0, ((EntityTank) player.getRidingEntity()).getCameraChanges(true));
            		}
            		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2)
            		{
            			GlStateManager.translate(0, 0, -((EntityTank) player.getRidingEntity()).getCameraChanges(true));
            		}
        		}
        	}
        	/*if(UtilsVoids.getEntities(EntityCameraDrone.class, player.world, player, 100).size() > 0)
        	{
        		EntityCameraDrone entity = null;
        		for(EntityCameraDrone entityIn : UtilsVoids.getEntities(EntityCameraDrone.class, player.world, player, 100))
        		{
        			if(entityIn.getOwner() == player)
        			{
        				entity = entityIn;
        			}
        		}
        		
        		if(entity != null)
        		{
        			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
        			
        			float partialTicks = (float) event.getRenderPartialTicks();
                	
                	double posX = player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks;
                	double posY = player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks + player.getEyeHeight();
                	double posZ = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks;
                	
                	double EposX = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks;
                	double EposY = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks + entity.getEyeHeight();
                	double EposZ = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;
                	
                	float yaw = (float) (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks + 180.0F);
                    float pitch = (float) (player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks);
                	
                    event.setYaw(0);
                    event.setPitch(0);
                    
                    ICamera icamera = new Frustum();
                    double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
                    double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
                    double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
                    icamera.setPosition(d0, d1, d2);
                    
                    Minecraft.getMinecraft().getRenderManager();
                    WorldClient world = (WorldClient) player.world;
                    ChunkProviderClient visibleChunks = ObfuscationReflectionHelper.getPrivateValue(WorldClient.class, world, 1);
                    Long2ObjectMap<Chunk> map = ObfuscationReflectionHelper.getPrivateValue(ChunkProviderClient.class, visibleChunks, 2);
                    
                    for(int i = -3; i <= 3; ++i)
                    {
                    	for(int j = -3; j <= 3; ++j)
                        {
                    		map.put(ChunkPos.asLong(entity.chunkCoordX * 16 + i * 16, entity.chunkCoordZ * 16 + j * 16), player.world.getChunkFromChunkCoords(entity.chunkCoordX + i, entity.chunkCoordZ + j));
                        }
                    }
                    
                    ObfuscationReflectionHelper.setPrivateValue(ChunkProviderClient.class, visibleChunks, map, 2);
                    //ObfuscationReflectionHelper.setPrivateValue(WorldClient.class, world, visibleChunks, 7);
                    //Minecraft.getMinecraft().renderGlobal.setupTerrain(entity, (double)partialTicks, icamera, 1, player.isSpectator());
                    drone = entity;
                    
                    GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
                    GlStateManager.translate(posX - EposX, posY - EposY, posZ - EposZ);
        		}
        		
                
            	/*Vec3d entityLocation = new Vec3d(EposX - posX, EposY - posY, EposZ - posZ);
            	//Vec3d entityLocation = new Vec3d(-3, 0, -3);//location.subtract(posX, posY, posZ);
            	//Vec3d playerLocation = new Vec3d(posX, posY, posZ);
            	Vec3d cameraBasePos = entityLocation.rotateYaw(-event.getYaw() * 0.017453292F - ((float)Math.PI / 2F));
            	
            	//event.setPitch(0);
            	
            	GlStateManager.translate(3, 0vec2.y, 3);
            	
            	//double rotYaw = UtilsVoids.getYawFromPos(cameraBasePos, entityLocation.x, entityLocation.z);
            	//double rotPitch = UtilsVoids.getPitchFromPos(cameraBasePos, entityLocation.x, entityLocation.y, entityLocation.z);
            	
            	//Vec3d vec2 = UtilsVoids.laser(entityLocation.distanceTo(cameraBasePos), (float)rotYaw, (float)rotPitch);
            	
            	//Vec3d tr = UtilsVoids.laser(-2, (float)rotYaw, (float)rotPitch);
            	
            	
            	GlStateManager.translate(entityLocation.x + (cameraBasePos.x - entityLocation.x), 0/*vec2.y, entityLocation.z + (cameraBasePos.z - entityLocation.z));
            	
            	System.out.print(posX + cameraBasePos.x + (entityLocation.x - cameraBasePos.x) + " || " + (posZ + cameraBasePos.z + (entityLocation.z - cameraBasePos.z)));
            	System.out.print(System.lineSeparator());
        	}*/
        	if(player.getRidingEntity() instanceof EntityTank && player != player.getRidingEntity().getControllingPassenger())
        	{
        		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
        		{
        			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
        		}
        		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 1)
        		{
        			GlStateManager.translate(0, 0F, ((EntityTank) player.getRidingEntity()).getCameraChanges(false));
        		}
        		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2)
        		{
        			GlStateManager.translate(0, 0, ((EntityTank) player.getRidingEntity()).getCameraChanges(false) + 2);
        		}
        	}
		}
	}
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Post event)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		if (event.getType() != ElementType.EXPERIENCE) 
		{
			return;
		}
		if(player.getHeldItemMainhand().getItem() instanceof ItemGun && (!(player.getHeldItemMainhand().getItem() instanceof IGunScope) || (player.getHeldItemMainhand().getItem() instanceof IGunScope && ((IGunScope) player.getHeldItemMainhand().getItem()).keepBaseScope(player.getHeldItemMainhand()))))
		{
			if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && Minecraft.getMinecraft().currentScreen == null)
			{
				new GuiGunScope(Minecraft.getMinecraft());
			}
		}
		if(player.getHeldItemMainhand().getItem() instanceof ItemGun && player.getHeldItemMainhand().getItem() instanceof IGunScope)
		{
			if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && ((ItemGun)player.getHeldItemMainhand().getItem()).getUsed(player.getHeldItemMainhand()) != 0 && !((IGunScope)player.getHeldItemMainhand().getItem()).hadCustomGuiScope() && Minecraft.getMinecraft().currentScreen == null)
			{
				new GuiSniperScope(Minecraft.getMinecraft(), (IGunScope) player.getHeldItemMainhand().getItem());
			}
		}
		if((player.getHeldItemMainhand().getItem() instanceof ItemGun || player.getHeldItemMainhand().getItem() instanceof ICustomGunStats) && MConfig.getConfig().get(Configuration.CATEGORY_CLIENT, "showGunStats", true).getBoolean())
		{
			new GuiGunStats(Minecraft.getMinecraft());
		}
		if(player.getRidingEntity() instanceof IVehicle)
		{
			new guiVehicleHealthBar(Minecraft.getMinecraft(), (IVehicle) player.getRidingEntity());
		}
		if(player.getRidingEntity() instanceof IReloadVehicle)
		{
			new GuiReloadVehicle(Minecraft.getMinecraft(), (IReloadVehicle) player.getRidingEntity());
		}
		if(player.getHeldItemMainhand().getItem() instanceof ItemMedigun)
		{
			ItemMedigun item = (ItemMedigun) player.getHeldItemMainhand().getItem();
			if(item.getUsed(player.getHeldItemMainhand()) > 0)
			{
				new guiUberProgress(Minecraft.getMinecraft(), item);
			}
		}
		
		new guiMagics(Minecraft.getMinecraft());
		
		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 1)
		{
			if(tankScope && player.getPassengers().size() <= 1)
			{
				new guiTankScope(Minecraft.getMinecraft());
			}
			if(player.getRidingEntity() instanceof EntityTank && player != player.getRidingEntity().getControllingPassenger())
	    	{
				new guiTankScope(Minecraft.getMinecraft());
	    	}
		}
		
		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
		{
			if(showTicks > 0)
			{
				new guiDamagesBullet(Minecraft.getMinecraft(), vectorF);
				--showTicks;
			}
			
			if(MathsKeys.SpellGui.isKeyDown())
			{
				//new guiSpellNew(Minecraft.getMinecraft());
			}
		}
		
		new GuiSpellHelper(Minecraft.getMinecraft());
	}

    public static void zoom(float zoomValue)
    {
    	try
    	{
    		ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, zoomValue, "fovModifierHand", "field_78507_R");
    	} 
    	catch(Exception e) 
    	{
    		System.err.println(e);
    	}
    }
    
    @SubscribeEvent
	public void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event)
	{
    	if(event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.ANVIL && event.getEntityPlayer().isSneaking())
		{
			
			if(event.getEntityPlayer().getHeldItemMainhand().getItem() == RegisterItems.FORGING_HAMMER)
			{
				ItemStack stack = event.getEntityPlayer().getHeldItemOffhand();
				Item item = event.getEntityPlayer().getHeldItemOffhand().getItem();
				
				if(item == RegisterItems.ALLOY_INGOT || MeltingRegistry.getMeltingProperties().containsKey(item))
				{
					event.getEntityPlayer().openGui(Mathsmod.instance, 20, event.getWorld(), 0, 0, 0);
				}
				else
				{
					event.getEntityPlayer().sendStatusMessage(new TextComponentString("take an ingot on your left hand to be able to forge"), true);
				}
			}
			else
			{
				event.getEntityPlayer().sendStatusMessage(new TextComponentString("you need a blacksmith hammer to use an anvil to forge"), true);
			}
		}
	}
    
    @SubscribeEvent
    public void renderSniper(TickEvent.RenderTickEvent event)
    {
        if(Minecraft.getMinecraft() != null && Minecraft.getMinecraft().player != null)
		{
        	EntityPlayerSP player = Minecraft.getMinecraft().player;
        	if(player.getHeldItemMainhand().getItem() instanceof ItemGun)
        	{
        		ItemGun item = (ItemGun) player.getHeldItemMainhand().getItem();
        		
        		if((item.smoothScope() || item.smoothMultiplier(player.getHeldItemMainhand(), player) != 1.0F) && item.getUsed(player.getHeldItemMainhand()) != 0)
        		{
        			renderer.updateCameraAndRender(event.renderTickTime, item.smoothMultiplier() * item.smoothMultiplier(player.getHeldItemMainhand(), player));
        		}
        	}
		}
    }
    
    @SubscribeEvent
    public void textureStitchEvent(TextureStitchEvent.Pre event)
    {
    	event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "misc/guns_gold"));
    	event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "misc/guns_bazookb_gold"));
    	event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "misc/guns_med_gold"));
    	List<ItemGunLayer> layers = Lists.newArrayList();
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemGunLayer)
    		{
    			event.getMap().registerSprite(((ItemGunLayer) item).getLayer());
    		}
    	}
    }
    
    /*@SubscribeEvent
    public void registerItemHandlers(ColorHandlerEvent.Item event)
    {
    	System.out.print("color");
    	System.out.print(System.lineSeparator());
    	event.getItemColors().registerItemColorHandler(new CurseGunsColorer(), RegisterItems.AK47);
    }*/
    
    /*public static void smooth(float smooth)
    {
    	try
    	{
    		ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, smooth, 18);
    		ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, smooth, 17);
    	} 
    	catch(Exception e) 
    	{
    		System.err.println(e);
    	}
    }*/
    
    public void setRenderedEntity(EntityPlayer player)
    {
    	if(UtilsVoids.getEntities(Entity.class, player.world, player, 20).size() > 0)
    	{
    		Entity entity = null;
    		for(Entity entityIn : UtilsVoids.getEntities(Entity.class, player.world, player, 10))
    		{
    			if(entityIn instanceof EntityCameraDrone && ((EntityCameraDrone) entityIn).getOwner() == player)
    			{
    				entity = entityIn;
    			}
    			else if(entityIn instanceof EntityUMissile && ((EntityUMissile) entityIn).getOwner() == player && ((EntityUMissile) entityIn).isControlled())
    			{
    				entity = entityIn;
    			}
    		}
    		
    		if(entity != null)
    		{
    			Minecraft.getMinecraft().setRenderViewEntity(entity);
    		}
    	}
    }
    
    @SubscribeEvent
	public void onGunShot(onShotEvent event)
	{
    	if(client_Proxy.spellUsed != null && SpellRegistry.REGISTRY.containsKey(client_Proxy.spellUsed))
    	{
    		SpellBase spell = SpellRegistry.REGISTRY.get(client_Proxy.spellUsed);
    		EnumSpellReaction reac = spell.reaction(client_Proxy.spellPhase, client_Proxy.spellProgress, event.getEntity());
    		if(reac == EnumSpellReaction.CANT || reac == EnumSpellReaction.CAN_MELEE)
    		{
        		event.setCanceled(true);
    		}
    	}
	}
}
