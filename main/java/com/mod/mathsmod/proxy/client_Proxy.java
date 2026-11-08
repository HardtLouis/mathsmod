package com.mod.mathsmod.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.EntitiesInit;
import com.mod.mathsmod.add.MathsKeys;
import com.mod.mathsmod.add.MathsParticles;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.CSE.ClientSideEnt;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.*;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.playerCosmetics.LayerGod;
import com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator.RenderPistolPlayer;
import com.mod.mathsmod.add.Entity.Rendermobs.tempAnim.TempAnim;
import com.mod.mathsmod.add.blocks.BlockUnbreakableCopy;
import com.mod.mathsmod.add.blocks.stateMapper.CopyStateMapper;
import com.mod.mathsmod.add.blocks.tileentity.*;
import com.mod.mathsmod.add.blocks.tileentity.render.*;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.event.ModelsEvent;
import com.mod.mathsmod.add.event.PlayerRenderManager;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.renderer.*;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.utils.TwoIntAndString;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class client_Proxy extends common_Proxy
{
	private static Random rand1 = new Random();
	private static Random rand2 = new Random();
    public static NoiseGeneratorPerlin biomeVectX = new NoiseGeneratorPerlin(rand1, 1);
    public static NoiseGeneratorPerlin biomeVectZ = new NoiseGeneratorPerlin(rand2, 1);
    
    public static File PANCARTE_FOLDER = new File("assets/" + Mathsmod.MODID + "/pancarte");
	
	public static HashMap<ResourceLocation, Integer> loadingList = Maps.newHashMap();
	
	public static HashMap<String, TwoIntAndString> usedSpells = Maps.newHashMap();
    
	public static HashMap<String, CopyOnWriteArrayList<String>> renderInfos = Maps.newHashMap();
	public static CopyOnWriteArrayList<TempAnim> anims = new CopyOnWriteArrayList();
	public static CopyOnWriteArrayList<ClientSideEnt> CSEL = new CopyOnWriteArrayList();
	
	public static int spellPhase = 0;
	public static int spellProgress = 0;
	public static int spellEnergy = Mathsmod.MAX_SPELL_ENERGY;
	public static int spellCooldown = 0;
	public static String spellUsed = null;
	public static HashMap<String, Integer> spellCooldowns = Maps.newHashMap();
	
	public static List<com.mod.mathsmod.add.spell.SpellBase> userSpells = Lists.newArrayList();
	
	public static int selectCooldown = 0;
	
	public client_Proxy()
	{
		MinecraftForge.EVENT_BUS.register(new KeyEvent());
		MinecraftForge.EVENT_BUS.register(new MConfig.ConfigEvent());
		MinecraftForge.EVENT_BUS.register(new ModelsEvent());
	}
	
	@Override
	public void preInit()
	{
		super.preInit();
		OBJLoader.INSTANCE.addDomain(Mathsmod.MODID);
		MathsKeys.registerKeys();
	}
	
	@Override
	public void Init()
	{
		super.Init();
		ClientRegistry.registerTileEntity(TileEntitySilverChest.class, "mathsmod:silver_chest", new RenderSilverChest());
		ClientRegistry.registerTileEntity(TileEntityEProjector.class, "mathsmod:entity_projector", new RenderEntityProjector());
		ClientRegistry.registerTileEntity(TileEntityMissileLauncher.class, "mathsmod:missile_launcher", new RenderEntityMissileLauncher());
		ClientRegistry.registerTileEntity(TileEntityBlackHole.class, "mathsmod:black_hole", new RenderBlackHole());
		ClientRegistry.registerTileEntity(TileEntityFruitPunchClown.class, "mathsmod:fruit_punch_clown", new RenderFruitPunchClown());
		ClientRegistry.registerTileEntity(TileEntityMysticCircle.class, "mathsmod:mystic_circle", new RenderMysticCircle());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGunCrafter.class, new TESRGuns());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMultiFurnace.class, new TESRFamasRendererSystem());
		
		MinecraftForge.EVENT_BUS.register(new PlayerRenderManager());
		
	}
	
	@Override
	public void postInit()
	{
		super.postInit();
		
		RegisterItems.renderItems();
		RegisterItems.createRenderTools("silver");
		RegisterItems.createRenderTools("tfosium");
		RegisterItems.createRenderTools("kurayum");
		RegisterItems.renderWolfArmor("iron");
		RegisterItems.renderWolfArmor("gold");
		RegisterItems.renderWolfArmor("diamond");
		RegisterItems.renderWolfArmor("leather");
		EntitiesInit.registerEntitiesRenders();
		
		MathsParticles.registerParticles();
		
		for (RenderPlayer renderPlayer : Minecraft.getMinecraft().getRenderManager().getSkinMap().values())
		{
			renderPlayer.addLayer(new LayerGod(renderPlayer));
        }
		
		Render<? extends Entity> render = Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(EntityWolf.class);
		if(render instanceof RenderWolf)
		{
			((RenderWolf) render).addLayer(new LayerWolfArmor((RenderWolf) render));
		}
	}
	
}
