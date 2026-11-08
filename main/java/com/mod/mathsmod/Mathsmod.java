package com.mod.mathsmod;

import java.awt.Color;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.GolemCompetences;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBiomes;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterFluid;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.RegistryClass;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.Entity.Entity.VillagerProfessions.houses.VillagerHousesHandler;
import com.mod.mathsmod.add.Entity.Entity.hunterTrade.HunterTradeRegistry;
import com.mod.mathsmod.add.GUI.handler.guiHandler;
import com.mod.mathsmod.add.World.MathsmodGeneration;
import com.mod.mathsmod.add.World.OresGen;
import com.mod.mathsmod.add.World.dims.generator.structures.MapGenSiroFortress;
import com.mod.mathsmod.add.World.dims.generator.structures.MapGenSirojusVillage;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureSBridgePieces;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureSirojusVillage;
import com.mod.mathsmod.add.blocks.tileentity.*;
import com.mod.mathsmod.add.capabilities.CapabilitiesManager;
import com.mod.mathsmod.add.commands.*;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.crafting.MeltingRegistry.recipe;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenAllemanite;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenBorealite;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenEnderite;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenKurayum;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenOxonium;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenPolarite;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenSilver;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenTfosium;
import com.mod.mathsmod.add.crafting.molten_metals.MoltenThorium;
import com.mod.mathsmod.add.creativeTabs.*;
import com.mod.mathsmod.add.event.*;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.messages.*;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.proxy.common_Proxy;
import com.mod.mathsmod.proxy.server_Proxy;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Mathsmod.MODID, name = Mathsmod.MOD_NAME, version = Mathsmod.VERSION, guiFactory="com.mod.mathsmod.config.GuiConfig")

public class Mathsmod {
	
public static final String VERSION = "3.0.0 bêta je vais pas spoil le nom update";
public static final String MOD_NAME = "Math's mod";
public static final String MODID = "mathsmod";
public static final String COMMON_PROXY = "com.mod.mathsmod.proxy.common_Proxy";
public static final String CLIENT_PROXY = "com.mod.mathsmod.proxy.client_Proxy";
public static final String SERVER_PROXY = "com.mod.mathsmod.proxy.server_Proxy";

public static Logger LOGGER;


public static final List<ItemGun> GUNS_LIST = Lists.newArrayList();
public static final List<Block> BLOCKS_THAT_I_HATE = Lists.newArrayList();

public static SimpleNetworkWrapper network;

	public Mathsmod() {
		MinecraftForge.EVENT_BUS.register(new RegistryClass());
		MinecraftForge.EVENT_BUS.register(new WorldEvents());
		MinecraftForge.EVENT_BUS.register(new SavePlayerEvents());
	}
	
	public static final CreativeTabs MATHSMOD_TAB = new CreativeTabMathsmod();
	public static final CreativeTabs MATHSMOD_MET_TAB = new CreativeTabMetalurgy();
	public static final CreativeTabs MATHSMOD_GUNS_TAB = new CreativeTabMathsmodGuns();
	
	public static final float SHOTGUN_MULTIPLIER = 0.95F;
	public static final float AR_MULTIPLIER = 0.65F;
	public static final float SMG_MULTIPLIER = 0.75F;
	public static final float MINIGUNS_MULTIPLIER = 0.8F;    
	public static final float GL_MULTIPLIER = 1.0F;
	public static final float RL_MULTIPLIER = 1.0F;
	public static final float SNIPERS_MULTIPLIER = 1.0F;
	public static final int MAX_SPELL_ENERGY = 200;
	
	@Instance(Mathsmod.MODID)
	public static Mathsmod instance = Instance();

	static Mathsmod Instance()
	{
		MapGenStructureIO.registerStructure(MapGenSiroFortress.Start.class, "Sirojus_Fortress");
		StructureSBridgePieces.registerNetherFortressPieces();
		
		MapGenStructureIO.registerStructure(MapGenSirojusVillage.Start.class, "Sirojus_Village");
		StructureSirojusVillage.registerVillagePieces();
		FluidRegistry.enableUniversalBucket();
		return instance;
		
	}
	
	@SidedProxy(clientSide = Mathsmod.CLIENT_PROXY, serverSide = Mathsmod.SERVER_PROXY, modId = Mathsmod.MODID)
	public static common_Proxy proxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
		
		RegisterFluid.registerFluids();
		
		LOGGER = event.getModLog();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("math_s_network");
		network.registerMessage(MessageRightClickMachineGun.rightClickGunHandler.class, MessageRightClickMachineGun.class, 0, Side.SERVER);
		network.registerMessage(LeftClickGun.LeftClickGunHandler.class, LeftClickGun.class, 1, Side.SERVER);
		network.registerMessage(SpellMessage.SpellHandler.class, SpellMessage.class, 2, Side.SERVER);
		network.registerMessage(PlaneBulletMessage.BulletHandler.class, PlaneBulletMessage.class, 3, Side.SERVER);
		network.registerMessage(TextureManagerMessage.TextureManagerHandler.class, TextureManagerMessage.class, 4, Side.SERVER);
		network.registerMessage(MessageResetGun.resetGunHandler.class, MessageResetGun.class, 5, Side.SERVER);
		network.registerMessage(MessageReloadGun.MessageReloadHandler.class, MessageReloadGun.class, 6, Side.SERVER);
		network.registerMessage(MessagePlayerStartElytra.MessageElytraHandler.class, MessagePlayerStartElytra.class, 7, Side.SERVER);
		network.registerMessage(LaunchMissileMessage.launchHandler.class, LaunchMissileMessage.class, 8, Side.SERVER);
		network.registerMessage(MessageDab.dabHandler.class, MessageDab.class, 9, Side.SERVER);
		network.registerMessage(MessageReachSword.leftClickReachHandler.class, MessageReachSword.class, 10, Side.SERVER);
		network.registerMessage(MessageHelicopter.HelicoHandler.class, MessageHelicopter.class, 11, Side.SERVER);
		network.registerMessage(MessageOptimisedShoot.MessageShootHandler.class, MessageOptimisedShoot.class, 12, Side.SERVER);
		network.registerMessage(MessageShowDamages.gdamagesHandler.class, MessageShowDamages.class, 13, Side.CLIENT);
		network.registerMessage(MessageModifyVelocity.velocityHandler.class, MessageModifyVelocity.class, 14, Side.CLIENT);
		network.registerMessage(MessageActivGrenadeLauncher.MiddleClickGunHandler.class, MessageActivGrenadeLauncher.class, 15, Side.SERVER);
		network.registerMessage(MessageTrackPlayer.trackHandler.class, MessageTrackPlayer.class, 16, Side.SERVER);
		network.registerMessage(MessageControlDrone.droneHandler.class, MessageControlDrone.class, 17, Side.SERVER);
		network.registerMessage(MessageBotChanges.EntityMessageHandler.class, MessageBotChanges.class, 18, Side.SERVER);
		network.registerMessage(EntityMessage.EntityMessageHandler.class, EntityMessage.class, 19, Side.SERVER);
		network.registerMessage(LaunchDroneMessage.launchHandler.class, LaunchDroneMessage.class, 20, Side.SERVER);
		network.registerMessage(MessageMiss.missHandler.class, MessageMiss.class, 21, Side.SERVER);
		network.registerMessage(MessageCasterChangeMod.casterHandler.class, MessageCasterChangeMod.class, 22, Side.SERVER);
		network.registerMessage(MessageTransformRelay.RelayHandler.class, MessageTransformRelay.class, 23, Side.SERVER);
		network.registerMessage(MessagePerformTrade.TradeHandler.class, MessagePerformTrade.class, 24, Side.SERVER);
		network.registerMessage(MessageCheapJumpscare.dabHandler.class, MessageCheapJumpscare.class, 25, Side.CLIENT);
		network.registerMessage(MessageSuperHit.shHandler.class, MessageSuperHit.class, 26, Side.CLIENT);
		network.registerMessage(MessagePelletsConnecter.pelletsHandler.class, MessagePelletsConnecter.class, 27, Side.SERVER);
		network.registerMessage(MessageTransform.MessageTransformHandler.class, MessageTransform.class, 28, Side.CLIENT);
		network.registerMessage(MessageTempAnim.MessageAnimHandler.class, MessageTempAnim.class, 29, Side.CLIENT);
		network.registerMessage(MessageParticleEffects.ParticleHandler.class, MessageParticleEffects.class, 30, Side.CLIENT);
		network.registerMessage(MessagePlaySound.soundHandler.class, MessagePlaySound.class, 31, Side.CLIENT);
		network.registerMessage(MessageAddsCSE.cseHandler.class, MessageAddsCSE.class, 32, Side.CLIENT);
		network.registerMessage(MessageBurnEntity.MessageBurnHandler.class, MessageBurnEntity.class, 33, Side.SERVER);
		network.registerMessage(MessageContract.ContractMessageHandler.class, MessageContract.class, 34, Side.SERVER);
		network.registerMessage(MessageInfiniteBullet.MessageIBHandler.class, MessageInfiniteBullet.class, 35, Side.CLIENT);
		network.registerMessage(MessageContractClient.ContractMessageHandler.class, MessageContractClient.class, 36, Side.CLIENT);
		network.registerMessage(MessageUpdateSpellAnims.SpellSyncHandler.class, MessageUpdateSpellAnims.class, 37, Side.CLIENT);
		network.registerMessage(MessageUpdateUserSpells.SpellSetHandler.class, MessageUpdateUserSpells.class, 38, Side.CLIENT);
		network.registerMessage(MessageUpdateClientSpell.SpellSetHandler.class, MessageUpdateClientSpell.class, 39, Side.CLIENT);
		network.registerMessage(MessageAddStructure.structureHandler.class, MessageAddStructure.class, 40, Side.SERVER);
		network.registerMessage(MessageHandleBalloonMovements.BalloonHandler.class, MessageHandleBalloonMovements.class, 41, Side.SERVER);
		
		LootTableList.register(new ResourceLocation(Mathsmod.MODID, "chests/sirofortress"));
		LootTableList.register(new ResourceLocation(Mathsmod.MODID, "chests/sirojus_tower"));
		LootTableList.register(new ResourceLocation(Mathsmod.MODID, "chests/underground_fortress"));
		LootTableList.register(new ResourceLocation(Mathsmod.MODID, "chests/underground_fortress_treasure"));
		LootTableList.register(new ResourceLocation(Mathsmod.MODID, "chests/cultist_chest"));
		LootTableList.register(new ResourceLocation(Mathsmod.MODID, "chests/hunter_hut"));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new guiHandler());
		
		ForgeChunkManager.setForcedChunkLoadingCallback(Mathsmod.instance, null);
		
		GolemCompetences.registerGolemCompetence();
		
		MConfig.InitConfig(event);
		
		SpellRegistry.registerAll();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.Init();
		VillagerRegistry.instance().registerVillageCreationHandler(new VillagerHousesHandler());
		
		GameRegistry.registerWorldGenerator(new OresGen(), 10);
		GameRegistry.addSmelting(RegisterBlocks.TFOSIUM_ORE, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot")), 1), 5);
		GameRegistry.addSmelting(RegisterBlocks.SILVER_ORE, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot")), 1), 3);
		GameRegistry.addSmelting(RegisterBlocks.KURAYUM_ORE, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 1), 10);
		GameRegistry.addSmelting(RegisterBlocks.THORIUM_ORE, new ItemStack(RegisterItems.THORIUM_INGOT, 1), 10);
		GameRegistry.addSmelting(RegisterBlocks.POLARITE_ORE, new ItemStack(RegisterItems.POLARITE_INGOT, 1), 30);
		GameRegistry.addSmelting(RegisterBlocks.BOREALITE_ORE, new ItemStack(RegisterItems.BOREALITE_INGOT, 1), 20);
		
		OreDictionary.registerOre("oreTfosium", RegisterBlocks.TFOSIUM_ORE);
		OreDictionary.registerOre("oreSilver", RegisterBlocks.SILVER_ORE);
		OreDictionary.registerOre("oreKurayum", RegisterBlocks.KURAYUM_ORE);
		OreDictionary.registerOre("oreThorium", RegisterBlocks.THORIUM_ORE);
		OreDictionary.registerOre("orePolarite", RegisterBlocks.POLARITE_ORE);
		OreDictionary.registerOre("oreBorealite", RegisterBlocks.BOREALITE_ORE);
		
		OreDictionary.registerOre("logWood", RegisterBlocks.BURNED_LOG);
		OreDictionary.registerOre("logWood", RegisterBlocks.CHERRY_LOG);
		OreDictionary.registerOre("treeSapling", RegisterBlocks.CHERRY_SAPLING);
		OreDictionary.registerOre("treeLeaves", RegisterBlocks.PINK_LEAVES);
		OreDictionary.registerOre("slabWood", RegisterBlocks.CHERRY_SLAB);
		OreDictionary.registerOre("stairWood", RegisterBlocks.CHERRY_STAIRS);
        OreDictionary.registerOre("plankWood", RegisterBlocks.CHERRY_PLANKS);
		OreDictionary.registerOre("treeLeaves", RegisterBlocks.GLOWBERRY_BUSH);
		OreDictionary.registerOre("slabWood", RegisterBlocks.BURNT_SLAB);
		OreDictionary.registerOre("stairWood", RegisterBlocks.BURNT_STAIRS);
        OreDictionary.registerOre("plankWood", RegisterBlocks.BURNT_PLANKS);
		
		RegisterPotions.registerPotionRecipes();
		
		WorldEvents.smelts.add(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_pickaxe")));
		WorldEvents.smelts.add(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_shovel")));
		
		MathsDims.init();
		
		RegisterBiomes.register();
		
		MeltingRegistry.registerMelting(Items.IRON_INGOT, new Molten_metal(400, new Color(200,200,200).getRGB(), 2, 2.5F, 7.0F, 500, 0, new float[]{2.5F, 5.5F, 6.5F, 2.5F}));
		MeltingRegistry.registerMelting(Items.GOLD_INGOT, new MeltingRegistry.Molten_metal(600, new Color(236, 214, 44).getRGB(), 2, 3.0F, 12.0F, 200, 0, new float[]{2, 4, 6, 3}));
		MeltingRegistry.registerMelting(Item.getByNameOrId("mathsmod:silver_ingot"), new MoltenSilver(800, new Color(255, 255, 255).getRGB(), 5, 5.0F, 12.0F, 1769, 0.4F));
		MeltingRegistry.registerMelting(Item.getByNameOrId("mathsmod:tfosium_ingot"), new MoltenTfosium(1000, new Color(0, 0, 195).getRGB(), 5, 7.5F, 15.0F, 2032, 0.7F));
		MeltingRegistry.registerMelting(Item.getByNameOrId("mathsmod:kurayum_ingot"), new MoltenKurayum(2000, new Color(255, 50, 50).getRGB(), 6, 10.5F, 18.0F, 0, 1F));
		MeltingRegistry.registerMelting(RegisterItems.THORIUM_INGOT, new MoltenThorium(10, new Color(205, 255, 100).getRGB(), 6, 14F, 20.0F, 0, 1.3F));
		MeltingRegistry.registerMelting(RegisterItems.POLARITE_INGOT, new MoltenPolarite(4000, new Color(150, 150, 255).getRGB(), 8, 21F, 24.0F, 0, 1.3F));
		MeltingRegistry.registerMelting(RegisterItems.BOREALITE_INGOT, new MoltenBorealite(3000, new Color(0, 255, 20).getRGB(), 8, 19F, 22.0F, 0, 1.3F));
		MeltingRegistry.registerMelting(RegisterItems.NIGHT_SHARD, new MoltenBorealite(300, new Color(30, 30, 30).getRGB(), 2, 2.5F, 7.0F, 0, 0));
		
		if(Item.getByNameOrId("chaosmod:oxonium_ingot") != null)
		{
			Item item = Item.getByNameOrId("chaosmod:oxonium_ingot");
			MeltingRegistry.registerMelting(item, new MoltenOxonium());
		}
		
		if(Item.getByNameOrId("chaosmod:allemanite_ingot") != null)
		{
			Item item = Item.getByNameOrId("chaosmod:allemanite_ingot");
			MeltingRegistry.registerMelting(item, new MoltenAllemanite());
		}
		
		if(Item.getByNameOrId("chaosmod:enderite_ingot") != null)
		{
			Item item = Item.getByNameOrId("chaosmod:enderite_ingot");
			MeltingRegistry.registerMelting(item, new MoltenEnderite());
		}
		
		MeltingRegistry.generateAlloyMaterials();
		
		HunterTradeRegistry.registerTrades();
		
		CapabilitiesManager.register();
		
		RegisterContracts.register();
		
		ItemStackUtils.registerGunsCrafts();
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
			proxy.postInit();
			GameRegistry.registerTileEntity(TileEntitySilverChest.class, "mathsmod:silver_chest");
			GameRegistry.registerTileEntity(TileEntityMultiFurnace.class, "mathsmod:furnace");
			GameRegistry.registerTileEntity(TileEntityGunCrafter.class, "mathsmod:gun_crafter");
			GameRegistry.registerTileEntity(TileEntityRecuperator.class, "mathsmod:recuperator");
			GameRegistry.registerTileEntity(TileEntityEProjector.class, "mathsmod:entity_projector");
			GameRegistry.registerTileEntity(TileEntityDisenchanter.class, "mathsmod:disenchanter");
			GameRegistry.registerTileEntity(TileEntityEAbsorber.class, "mathsmod:entity_absorber");
			GameRegistry.registerTileEntity(TileEntityWolfArmorCrafter.class, "mathsmod:wolf_armor_crafter");
			GameRegistry.registerTileEntity(TileEntityMissileLauncher.class, "mathsmod:missile_launcher");
			GameRegistry.registerTileEntity(TileEntityLightningGenerator.class, "mathsmod:lightning_generator");
			GameRegistry.registerTileEntity(TileEntityTeleporter.class, "mathsmod:teleporter");
			GameRegistry.registerTileEntity(TileEntityBlackHole.class, "mathsmod:black_hole");
			GameRegistry.registerTileEntity(TileEntityMacerator.class, "mathsmod:macerator");
			GameRegistry.registerTileEntity(TileEntityMetalurgicFurnace.class, "mathsmod:metalurgic_furnace");
			GameRegistry.registerTileEntity(TileEntityCaster.class, "mathsmod:caster");
			GameRegistry.registerTileEntity(TileEntityMobGenerator.class, "mathsmod:mob_generator");
			GameRegistry.registerTileEntity(TileEntityFruitPunchClown.class, "mathsmod:fruit_punch_clown");
			GameRegistry.registerTileEntity(TileEntityTomb.class, "mathsmod:tomb");
			GameRegistry.registerTileEntity(TileEntityRandomizedStructurePlacer.class, "mathsmod:randomized_structure_placer");
			GameRegistry.registerTileEntity(TileEntityMysticCircle.class, "mathsmod:mystic_circle");
			
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.BASIC_BLADE, 2);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.BASIC_GUARD, 1);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.BASIC_HANDLE, 1);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.DAMAGE_BLADE, 2);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.OFFENSE_GUARD, 1);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.DEFENCE_GUARD, 1);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.HANDLE_STYLE_2, 1);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.HAMMER_PART, 3);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.PICKAXE_PART, 2);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.PICKAXE_HANDLE, 1);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.BAM_BLADE, 2);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.HAMMER_BLADE, 1);
			
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.FUGITIVE_BOOTS, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.ATTACKER_BOOTS, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.AGGRESSIVE_BOOTS, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.SHIELD_BOOTS, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.TANK_BOOTS, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.WARRIOR_BOOTS, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.FUGITIVE_LEGGINGS, 7);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.ATTACKER_LEGGINGS, 7);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.AGGRESSIVE_LEGGINGS, 7);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.SHIELD_LEGGINGS, 7);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.TANK_LEGGINGS, 7);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.WARRIOR_LEGGINGS, 7);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.FUGITIVE_CHESTPLATE, 8);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.ATTACKER_CHESTPLATE, 8);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.AGGRESSIVE_CHESTPLATE, 8);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.SHIELD_CHESTPLATE, 8);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.TANK_CHESTPLATE, 8);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.WARRIOR_CHESTPLATE, 8);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.FUGITIVE_HELMET, 5);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.ATTACKER_HELMET, 5);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.AGGRESSIVE_HELMET, 5);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.SHIELD_HELMET, 5);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.TANK_HELMET, 5);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.WARRIOR_HELMET, 5);

			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.METALIC_SHIELD, 4);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.GOURDIN, 3);
			MeltingRegistry.METALIC_ITEMS.put(RegisterItems.ZDEUCH_HAMMER, 5);
			
			if(SharedMonsterAttributes.MAX_HEALTH instanceof RangedAttribute)
			{
				RangedAttribute connard = (RangedAttribute) SharedMonsterAttributes.MAX_HEALTH;
				ObfuscationReflectionHelper.setPrivateValue(RangedAttribute.class, connard, Float.POSITIVE_INFINITY, 1);
			}
			
			for(Item item : Item.REGISTRY)
			{
				if(item instanceof ItemGun)
				{
					this.GUNS_LIST.add((ItemGun) item);
				}
			}
			
			for(ItemStack stack : OreDictionary.getOres("logWood"))
			{
				if(Block.getBlockFromItem(stack.getItem()) != null)
				{
					BLOCKS_THAT_I_HATE.add(Block.getBlockFromItem(stack.getItem()));
				}
			}
			for(ItemStack stack : OreDictionary.getOres("treeLeaves"))
			{
				if(Block.getBlockFromItem(stack.getItem()) != null)
				{
					BLOCKS_THAT_I_HATE.add(Block.getBlockFromItem(stack.getItem()));
				}
			}
	}
	
	
	@EventHandler
	public void ServerInit(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandFeed());
		event.registerServerCommand(new CommandHeal());
		event.registerServerCommand(new CommandSpellsUse());
		event.registerServerCommand(new CommandHelp());
		event.registerServerCommand(new CommandDisguise());
		event.registerServerCommand(new CommandSetReincarned());
		event.registerServerCommand(new CommandForceEnchant());
		event.registerServerCommand(new CommandExplode());
		event.registerServerCommand(new CommandVanish());
		event.registerServerCommand(new CommandKS());
		event.registerServerCommand(new CommandTransform());
		event.registerServerCommand(new CommandPancarte());
		event.registerServerCommand(new CommandWarp());
		event.registerServerCommand(new CommandTop());
		event.registerServerCommand(new CommandUber());
		event.registerServerCommand(new CommandSuicide());
	}
}
