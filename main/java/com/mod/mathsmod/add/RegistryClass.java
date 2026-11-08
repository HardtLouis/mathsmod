package com.mod.mathsmod.add;

import java.util.List;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.crafting.RecipeCustomSword;
import com.mod.mathsmod.add.items.ItemGlowberry;
import com.mod.mathsmod.add.items.ItemGunLayer;
import com.mod.mathsmod.add.items.ItemArmor.wolf.ItemWolfArmor;
import com.mod.mathsmod.add.recipe.RecipeGlowberryFood;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class RegistryClass {
	
	@SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(RegisterBlocks.TURRET, RegisterBlocks.AUTO_TURRET, RegisterBlocks.SILVER_BLOCK, RegisterBlocks.SILVER_ORE,RegisterBlocks.SILVER_CHEST
        		,RegisterBlocks.FURNACE,RegisterBlocks.FIRE, RegisterBlocks.TFOSIUM_BLOCK, RegisterBlocks.TFOSIUM_ORE,RegisterBlocks.KURAYUM_ORE, RegisterBlocks.KURAYUM_BLOCK
        		, RegisterBlocks.GUN_CRAFTER, RegisterBlocks.RECUPERATOR, RegisterBlocks.EPROJECTOR, RegisterBlocks.DISENCHANTER
        		, RegisterBlocks.ENTITY_ABSORBER, RegisterBlocks.WOLF_ARMOR_CRAFTER, RegisterBlocks.COPRAVEG_RG, RegisterBlocks.BLOCKSHS
        		,RegisterBlocks.PORTAL, RegisterBlocks.GREEN_LAVA, RegisterBlocks.DENANIUM, RegisterBlocks.EXPLODA, RegisterBlocks.DIAMONA
        		, RegisterBlocks.MISSILE_LAUNCHER, RegisterBlocks.LIGHTNING_GEN, RegisterBlocks.BURNED_LOG, RegisterBlocks.BLACK_STONE
        		,RegisterBlocks.COPRAVEG_PLANT, RegisterBlocks.LOCKER, RegisterBlocks.REDSTONE_JUKEBOX, RegisterBlocks.THORIUM_BLOCK, RegisterBlocks.THORIUM_ORE
        		, RegisterBlocks.DESTRUCTIVE_WALL, RegisterBlocks.SIROPORTAL, RegisterBlocks.FROZEN_BRICK, RegisterBlocks.FROZEN_STONE, RegisterBlocks.PERMICE_STONE
        		, RegisterBlocks.CRAB_PRISON, RegisterBlocks.CRAB_LIMITS, RegisterBlocks.FROZEN_WATER, RegisterBlocks.REINFORCED_GLASS, RegisterBlocks.LOOTBOX
        		, RegisterBlocks.POLARITE_ORE, RegisterBlocks.POLARITE_BLOCK, RegisterBlocks.CAVE_FINDER, RegisterBlocks.SIROJUS_PORTAL_FRAME
        		, RegisterBlocks.UNSTABLE_TNT, RegisterBlocks.ETERNAL_FIRE, RegisterBlocks.ELEVATOR, RegisterBlocks.TELEPORTER
        		, RegisterBlocks.PINK_LEAVES, RegisterBlocks.STABILIZER, RegisterBlocks.BLACK_HOLE/*, RegisterBlocks.MACERATOR*/, RegisterBlocks.PULSIOR_ALTAR, RegisterBlocks.METALURGIC_FURNACE
        		, RegisterBlocks.CASTER, RegisterBlocks.BOREALITE_BLOCK, RegisterBlocks.BOREALITE_ORE, RegisterBlocks.FROZEN_LEAVES, RegisterBlocks.BLACKSTONE_BRICK
        		, RegisterBlocks.FROZEN_GLASS, RegisterBlocks.POULOPOP_ALTAR, RegisterBlocks.MOB_GENERATOR, RegisterBlocks.FPC, RegisterBlocks.BEAR_TRAP
        		, RegisterBlocks.MOSSY_BLACK_STONE, RegisterBlocks.RADIOACTIVE_BLACKSTONE, RegisterBlocks.HELL_FLOWER, RegisterBlocks.BANANA_PORTAL_FRAME
        		, RegisterBlocks.BANANA_PORTAL, RegisterBlocks.CHAUVOPUT_ALTAR, RegisterBlocks.BLACK_KATANA_HOLDER_BLOCK, RegisterBlocks.BASIC_LOOTBOX
        		, RegisterBlocks.CHERRY_LOG, RegisterBlocks.CHERRY_PLANKS, RegisterBlocks.CHERRY_SAPLING, RegisterBlocks.CHERRY_SLAB, RegisterBlocks.DOUBLE_CHERRY_SLAB, RegisterBlocks.CHERRY_STAIRS
        		, RegisterBlocks.TOMB, RegisterBlocks.ORE_LOOTBOX, RegisterBlocks.BLUE_CRYSTAL_BLOCK, RegisterBlocks.GREEN_CRYSTAL_BLOCK, RegisterBlocks.RED_CRYSTAL_BLOCK
        		, RegisterBlocks.PURPLE_CRYSTAL_BLOCK, RegisterBlocks.BLUE_CRYSTAL, RegisterBlocks.RED_CRYSTAL, RegisterBlocks.GREEN_CRYSTAL, RegisterBlocks.PURPLE_CRYSTAL
        		, RegisterBlocks.SILVER_CRYSTAL, RegisterBlocks.GRASSY_STONE, RegisterBlocks.GLOWBERRY_BUSH, RegisterBlocks.BLOCK_RSP, RegisterBlocks.WEIRD_WATER
        		, RegisterBlocks.BURNT_PLANKS, RegisterBlocks.BURNT_SLAB, RegisterBlocks.DOUBLE_BURNT_SLAB, RegisterBlocks.BURNT_STAIRS, RegisterBlocks.WEIRD_STONE
        		, RegisterBlocks.CANDLE, RegisterBlocks.MYSTIC_CIRCLE, RegisterBlocks.MAGMATIC_SOIL);
        
        //RegisterBlocks.registerBlockCopies(event);
    }
	
	@SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event2) 
	{
		RegisterItems.createTools("silver", RegisterItems.SILVER, RegisterItems.SILVER_ARMOR, Mathsmod.MATHSMOD_TAB, event2);
		RegisterItems.createTools("tfosium", RegisterItems.TFOSIUM, RegisterItems.TFOSIUM_ARMOR, Mathsmod.MATHSMOD_TAB, event2);
		RegisterItems.createKurayumTools("kurayum", RegisterItems.KURAYUM, Mathsmod.MATHSMOD_TAB, event2);
		RegisterItems.createWolfArmor("leather", ItemArmor.ArmorMaterial.LEATHER, CreativeTabs.COMBAT, event2);
		RegisterItems.createWolfArmor("iron", ItemArmor.ArmorMaterial.IRON, CreativeTabs.COMBAT, event2);
		RegisterItems.createWolfArmor("gold", ItemArmor.ArmorMaterial.GOLD, CreativeTabs.COMBAT, event2);
		RegisterItems.createWolfArmor("diamond", ItemArmor.ArmorMaterial.DIAMOND, CreativeTabs.COMBAT, event2);
		
		event2.getRegistry().registerAll(RegisterItems.TURRET, RegisterItems.AUTO_TURRET, RegisterItems.SILVER_BLOCK, RegisterItems.SILVER_ORE,RegisterItems.SILVER_CHEST
				, RegisterItems.FURNACE,RegisterItems.TFOSIUM_BLOCK, RegisterItems.TFOSIUM_ORE, RegisterItems.KURAYUM_HELMET, RegisterItems.KURAYUM_CHESTPLATE,
				RegisterItems.KURAYUM_LEGGINGS, RegisterItems.KURAYUM_BOOTS, RegisterItems.KURAYUM_SWORD, RegisterItems.KURAYUM_AXE,RegisterItems.PLANE,RegisterItems.KURAYUM_ORE
				, RegisterItems.KURAYUM_BLOCK,RegisterItems.OBUS, RegisterItems.GRENADE, RegisterItems.FRAG_GRENADE, RegisterItems.DRILLING_MACHINE, RegisterItems.SNIPER, RegisterItems.SNIPER_BULLET
				, RegisterItems.MACHINE_PISTOL,RegisterItems.BASIC_SHOTGUN, RegisterItems.FAMAS ,RegisterItems.DEAGLE,RegisterItems.SMASHER,RegisterItems.INFGAUNT
				, RegisterItems.SCAR_H, RegisterItems.FLAME_THROWER/*, RegisterItems.FIRE_UPGRADE*/, RegisterItems.BUCKSHOT, RegisterItems.LITTLE_AMMO, RegisterItems.LONG_AMMO
				, RegisterItems.AA12, RegisterItems.R870,RegisterItems.ROCKET,RegisterItems.ROCKET_LAUNCHER,RegisterItems.GUN_CRAFTER, RegisterItems.RECUPERATOR
				, RegisterItems.AK47, RegisterItems.UNKNOWN_HELMET,RegisterItems.DSWORD, RegisterItems.EPROJECTOR, RegisterItems.PROJECTOR_CHANGER
				, RegisterItems.COMMUNIST_DISK,RegisterItems.ELECTRIC_BOAT,RegisterItems.KURAYUM_WOLF_BOOTS, RegisterItems.KURAYUM_WOLF_LEGS
				, RegisterItems.KURAYUM_WOLF_CHEST, RegisterItems.KURAYUM_WOLF_HEAD, RegisterItems.DISENCHANTER, RegisterItems.TANK_ITEM, RegisterItems.ENTITY_ABSORBER
				, RegisterItems.WOLF_ARMOR_CRAFTER, RegisterItems.KURAYUM_HAMMER, RegisterItems.TFOSIUM_HAMMER, RegisterItems.AR15, RegisterItems.DRONE_LAUNCHER
				, /*RegisterItems.DOUBLE_SNIPER, */RegisterItems.FUGITIVE_BOOTS, RegisterItems.WARRIOR_BOOTS, RegisterItems.ATTACKER_BOOTS
				, RegisterItems.SHIELD_BOOTS, RegisterItems.TANK_BOOTS, RegisterItems.AGGRESSIVE_BOOTS, RegisterItems.FUGITIVE_LEGGINGS, RegisterItems.WARRIOR_LEGGINGS
				, RegisterItems.ATTACKER_LEGGINGS, RegisterItems.SHIELD_LEGGINGS, RegisterItems.TANK_LEGGINGS, RegisterItems.AGGRESSIVE_LEGGINGS, RegisterItems.FUGITIVE_CHESTPLATE
				, RegisterItems.WARRIOR_CHESTPLATE, RegisterItems.ATTACKER_CHESTPLATE, RegisterItems.SHIELD_CHESTPLATE, RegisterItems.TANK_CHESTPLATE, RegisterItems.AGGRESSIVE_CHESTPLATE
				, RegisterItems.FUGITIVE_HELMET, RegisterItems.WARRIOR_HELMET, RegisterItems.ATTACKER_HELMET, RegisterItems.SHIELD_HELMET, RegisterItems.TANK_HELMET
				, RegisterItems.AGGRESSIVE_HELMET, RegisterItems.COPRAVEG_RG, RegisterItems.CARBINE, RegisterItems.HUNTER_SHOTGUN
				, RegisterItems.GAZ_GRENADE, RegisterItems.STICKY_GRENADE
				, RegisterItems.THOMPSON, RegisterItems.BULLETPROOF_VEST, RegisterItems.INSTRUC_BOOK, RegisterItems.GRENADE_LAUNCHER
				, RegisterItems.LOVE_BULLET, RegisterItems.HUNTING_BULLET, RegisterItems.GLOCK17, RegisterItems.FAFR
				, RegisterItems.RAINBOW_LAYER, RegisterItems.LOVE_LAYER
				, RegisterItems.DARK_LAYER, RegisterItems.DARK2_LAYER, RegisterItems.CLASSICAL_LAYER, RegisterItems.GRAPPLE, RegisterItems.HARPOON, RegisterItems.END_LAYER
				, RegisterItems.RB_LAYER, RegisterItems.CSCAR_LAYER, RegisterItems.OLD_LAYER, RegisterItems.NUCLEAR_ROCKET_LAUNCHER, RegisterItems.GALAXY_LAYER, RegisterItems.BW_LAYER
				, RegisterItems.SUPER_HUNTER_SPAWNER, RegisterItems.JETPACK, RegisterItems.COPRAVEG_ROD, RegisterItems.EYE_OF_BLAL
				, RegisterItems.IRON_SHARD, RegisterItems.MINIGUN, RegisterItems.DENANIUM, RegisterItems.EXPLODA, RegisterItems.DIAMONA, RegisterItems.BASIC_SOUND_MUFFLER
				, RegisterItems.BASIC_SCOPE, RegisterItems.NUC_PLANE, RegisterItems.HALF_MUFFLER, RegisterItems.MISSILE_LAUNCHER, RegisterItems.A_MISSILE, RegisterItems.EXPLOSIVE_BELT
				, RegisterItems.FAKE_EXPLOSIVE_BELT, RegisterItems.NUCLEAR_MISSILE, RegisterItems.KB800, RegisterItems.HEAVY_FAMAS
				, RegisterItems.LIGHTNING_GEN, RegisterItems.ASSASSIN_RAPIER
				, RegisterItems.BURNED_LOG, RegisterItems.BLACK_STONE, RegisterItems.COPRAVEG_PLANT, RegisterItems.SKY_SWORD, RegisterItems.SEA_SWORD
				, RegisterItems.SHARK_TOOTH, RegisterItems.EARTH_SWORD
				, RegisterItems.LOCKER, RegisterItems.HELICOPTER, RegisterItems.FRENCH_DISK, RegisterItems.ISSOU_DISK
				, RegisterItems.REGENERATIVE_STEAK, RegisterItems.KURAYUM_APPLE, RegisterItems.REDSTONE_JUKEBOX
				, RegisterItems.B_MISSILE, RegisterItems.THORIUM_BLOCK, RegisterItems.THORIUM_ORE, RegisterItems.THORIUM_INGOT, RegisterItems.T_MISSILE
				, RegisterItems.REPULSIVE_GRENADE, RegisterItems.KNOCKBACK_GRENADE, RegisterItems.PLATYPUS_PAW, RegisterItems.THORIUM_BOOTS
				, RegisterItems.THORIUM_LEGS, RegisterItems.THORIUM_CHESTPLATE, RegisterItems.THORIUM_HEAD, RegisterItems.UBER_BOOTS
				, RegisterItems.UBER_LEGS, RegisterItems.UBER_CHESTPLATE, RegisterItems.UBER_HEAD, RegisterItems.GUN_GRENADE_LAUNCHER, RegisterItems.PLAYER_TRACKER
				, RegisterItems.CAMERA_DRONE, RegisterItems.TACTICAL_BOW, RegisterItems.MEDIGUN, RegisterItems.BOT, RegisterItems.BAZOOKB//, RegisterItems.ENDER_SNIPER
				, RegisterItems.BAT, RegisterItems.REPULSIVE_ROCKET, RegisterItems.IRON_HAMMER, RegisterItems.DIAMOND_HAMMER
				, RegisterItems.SILVER_HAMMER, RegisterItems.SILVER_ROD, RegisterItems.BLUMON, RegisterItems.BLACK_HAT, RegisterItems.ENDER_FISH, RegisterItems.HEART_FISH
				, RegisterItems.ARMOR_FISH, RegisterItems.FIRE_FISH, RegisterItems.CHAPKA, RegisterItems.HOOD, RegisterItems.TFOSIUM_ROD
				, RegisterItems.BASIC_SIGHT, RegisterItems.CHARGE_SHIELD, RegisterItems.VODKA, RegisterItems.KEPI
				, RegisterItems.MEDIC_S_THOMPSON, RegisterItems.MEDIC_S_SHOTGUN, RegisterItems.SILVER_LAYER
				, RegisterItems.GOLDEN_BAT, RegisterItems.FRYING_PAN, RegisterItems.SKELETON_MANAGER, RegisterItems.FROZEN_BRICK, RegisterItems.FROZEN_STONE
				, RegisterItems.PERMICE_STONE, RegisterItems.COPRAVEG_KEY, RegisterItems.CRAB_ARENA_KEY, RegisterItems.ICE_ROD, RegisterItems.FROZEN_SWORD
				, RegisterItems.SLICER, RegisterItems.CRUSHER, RegisterItems.REINFORCED_GLASS, RegisterItems.GOLDEN_FRYING_PAN
				, RegisterItems.POLARITE_INGOT, RegisterItems.LOOTBOX, RegisterItems.GUIDED_ROCKET, RegisterItems.AMMO_BOX, RegisterItems.GIPSY_STRENGHT
				, RegisterItems.NTT_SNIPER, RegisterItems.POLARITE_ORE, RegisterItems.POLARITE_BLOCK
				, RegisterItems.POLARITE_SWORD, RegisterItems.POLARITE_PICKAXE, RegisterItems.POLARITE_AXE, RegisterItems.CAVE_FINDER
				, RegisterItems.POLARITE_BOOTS, RegisterItems.POLARITE_LEGS, RegisterItems.POLARITE_CHESTPLATE, RegisterItems.POLARITE_HEAD, RegisterItems.SQUID_LAUNCHER
				, RegisterItems.HEAD_CUTTER, RegisterItems.FROZEN_HEART, RegisterItems.SCS7
				, RegisterItems.ULTIMATE_VEHICLE, RegisterItems.POLARITE_FRYING_PAN, RegisterItems.CABER, RegisterItems.UGLY_LAYER, RegisterItems.ORANGE_PH_LAYER
				, RegisterItems.ANIMATED_ORANGE_LAYER, RegisterItems.FROZEN_LAYER, RegisterItems.COPRAVEG_LAYER, RegisterItems.SOUTH_SWORD, RegisterItems.HEAVY_BOOTS
				, RegisterItems.DIAMOND_SNIPER_BULLET, RegisterItems.SIROJUS_PORTAL_FRAME
				, RegisterItems.SHOTBOW, RegisterItems.CHICKEN_LAUNCHER, RegisterItems.UNSTABLE_TNT, RegisterItems.PYRITE_GEM, RegisterItems.PYRITE_STAFF
				, RegisterItems.PYRITE_SHIELD, RegisterItems.FROZEN_GEM, RegisterItems.ELEVATOR, RegisterItems.TELEPORTERS_BINDER
				, RegisterItems.TELEPORTER, RegisterItems.IRON_SKULL, RegisterItems.IRON_JETPACK
				, RegisterItems.ANTIVEHICLE_RL, RegisterItems.MANJTEMOR, RegisterItems.PINK_LEAVES, RegisterItems.GOLDEN_EXPLOSIVE_BELT
				, RegisterItems.STABILIZER, RegisterItems.BLACK_HOLE/*, RegisterItems.BLACK_HOLE_LAUNCHER*/, RegisterItems.BLACK_HOLE_FRYING_PAN, RegisterItems.BLACK_HOLE_MISSILE
				, /*RegisterItems.MACERATOR, */RegisterItems.PULSIOR_ALTAR, RegisterItems.DIRECT_TIT, RegisterItems.METALURGIC_FURNACE, RegisterItems.THERMORESISTANT_BUCKET
				, RegisterItems.ALLOY_INGOT, RegisterItems.BASIC_BLADE, RegisterItems.BASIC_GUARD, RegisterItems.BASIC_HANDLE
				, RegisterItems.CUSTOM_SWORD, RegisterItems.CASTER, RegisterItems.BOREALITE_BLOCK, RegisterItems.BOREALITE_ORE, RegisterItems.BOREALITE_INGOT
				, RegisterItems.DAMAGE_BLADE, RegisterItems.OFFENSE_GUARD, RegisterItems.DEFENCE_GUARD, RegisterItems.HANDLE_STYLE_2, RegisterItems.FROZEN_LEAVES
				, RegisterItems.CUSTOM_PICKAXE, RegisterItems.PICKAXE_HANDLE, RegisterItems.PICKAXE_PART, RegisterItems.HAMMER_PART
				, RegisterItems.NIGHT_SHARD, RegisterItems.BLACKSTONE_BRICK, RegisterItems.BAM_BLADE, RegisterItems.HAMMER_BLADE, RegisterItems.FROZEN_GLASS
				, RegisterItems.SIROJUS_KEY, RegisterItems.POULOPOP_ALTAR, RegisterItems.MOB_GENERATOR, RegisterItems.GAS_MASK, RegisterItems.DISGUISE_KIT
				, RegisterItems.FPC, RegisterItems.FRUIT_PUNCH, RegisterItems.KURAYUM_JETPACK, RegisterItems.NIGHTMARE_STAFF
				, RegisterItems.CRAB_CLAW, RegisterItems.BEAR_TRAP, RegisterItems.CANARDIER, RegisterItems.LAUNCH_TRAP
				, RegisterItems.WIND_LAYER, RegisterItems.GOLD_LAYER, RegisterItems.NETHER_LAYER, RegisterItems.GOLD_PLATED_LAYER, RegisterItems.MOSSY_BLACKSTONE
				, RegisterItems.RADIOACTIVE_BLACKSTONE, RegisterItems.THORIUM_FRAGMENT, RegisterItems.SUPER_HUNTER_CORE, RegisterItems.BANANA
				, RegisterItems.METALIC_SHIELD, RegisterItems.GOURDIN, RegisterItems.HELL_FLOWER, RegisterItems.BANANA_PORTAL_FRAME, RegisterItems.CHAUVOPUT_ALTAR
				, RegisterItems.REVOLVER, RegisterItems.BERSERSWORD, RegisterItems.REBONDI_BOOTS, RegisterItems.MAGMA_CORE, RegisterItems.GHALI_AIRCUT
				, RegisterItems.METALURGIC_FURNACE_UPGRADE, RegisterItems.CREATIVE_METALURGIC_FURNACE_UPGRADE, RegisterItems.AERIAN_MONSTRUOSITY, RegisterItems.TR_SNIPER
				, RegisterItems.BELUGA_SADDLE, RegisterItems.BUFFED_BANANA, RegisterItems.FESTIVE_JETPACK, RegisterItems.FESTIVE_ROCKET, RegisterItems.TERRAFORMER
				, RegisterItems.CHAINSAW, RegisterItems.PANCARTE, RegisterItems.TECH_SHIELD, RegisterItems.TATANATOR, RegisterItems.KURAYUM_FISHING_ROD
				, RegisterItems.WHITE_KATANA, RegisterItems.BLACK_KATANA, RegisterItems.BLACK_KATANA_HOLDER_BLOCK, RegisterItems.MOSQUITO_JAR, RegisterItems.TUTU
				, RegisterItems.BASIC_LOOTBOX, RegisterItems.JARATE, RegisterItems.FORGING_HAMMER, RegisterItems.LOG_CHERRY, RegisterItems.PLANKS_CHERRY
				, RegisterItems.SAPLING_CHERRY, RegisterItems.CHERRY_SLAB, RegisterItems.CHERRY_STAIRS, RegisterItems.ORE_LOOTBOX, RegisterItems.MOB_SOUL
				, RegisterItems.BLUE_CRYSTAL_BLOCK, RegisterItems.GREEN_CRYSTAL_BLOCK, RegisterItems.RED_CRYSTAL_BLOCK, RegisterItems.PURPLE_CRYSTAL_BLOCK
				, RegisterItems.BLUE_CRYSTAL, RegisterItems.GREEN_CRYSTAL, RegisterItems.RED_CRYSTAL, RegisterItems.PURPLE_CRYSTAL, RegisterItems.SILVER_CRYSTAL
				, RegisterItems.GRASSY_STONE, RegisterItems.GLOWBERRY_BUSH, RegisterItems.GLOWBERRY, RegisterItems.BLOCK_RSP, RegisterItems.PLANKS_BURNT
				, RegisterItems.BURNT_SLAB, RegisterItems.BURNT_STAIRS, RegisterItems.WEIRD_STONE, RegisterItems.CANDLE, RegisterItems.MYSTIC_CIRCLE
				, RegisterItems.CLOTH, RegisterItems.SACRIFICE_DAGGER, RegisterItems.MAGMATIC_SOIL, RegisterItems.ZDEUCH_HAMMER, RegisterItems.ETERNAL_PUNISHMENT
				, RegisterItems.DEBUFF_SERINGE, RegisterItems.AIR_BALLOON);
	}
	
	@SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		event.getRegistry().registerAll(RegisterBiomes.COPRANIGHT, RegisterBiomes.EXTREM_MOUNTAINS, RegisterBiomes.FROZEN_PLAINS, RegisterBiomes.HIGH_MOUNTAINS
				, RegisterBiomes.FLOWER_PLAINS, RegisterBiomes.DESOLATE_LANDS, RegisterBiomes.PINK_TREE, RegisterBiomes.UNUSUAL_FOREST
				, RegisterBiomes.FROZEN_FOREST, RegisterBiomes.NIGHTMARE, RegisterBiomes.COPRARAD, RegisterBiomes.BANANA);
	}

	@SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event)
	{
		event.getRegistry().registerAll(Enchantments.SMELT, Enchantments.POWER, Enchantments.SERIAL);
	}
	
	@SubscribeEvent
    public void registerPotions(RegistryEvent.Register<Potion> event) 
	{
		event.getRegistry().registerAll(RegisterPotions.SLOWFALL, RegisterPotions.INVUL, RegisterPotions.FALL_PROTECTION, RegisterPotions.FRIENDS_OF_HUNTERS
				, RegisterPotions.FIRE_REGENERATION, RegisterPotions.UBER, RegisterPotions.CHARGE, RegisterPotions.DISAPPEAR
				, RegisterPotions.FRUIT_PUNCH, RegisterPotions.FIRE_VULNERABILITY);
	}
	
	@SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) 
	{
		event.getRegistry().registerAll(MathsSounds.COMMUNIST_HYMN, MathsSounds.GL_SOUND, MathsSounds.P90_SOUND, MathsSounds.AR15, MathsSounds.HUNTER_SHOTGUN_SOUND, 
				MathsSounds.SHOTGUN_SOUND, MathsSounds.AA12_SOUND, MathsSounds.THOMPSON_SOUND, MathsSounds.GLOCK_SOUND, MathsSounds.ISSOU, MathsSounds.FRENCH_ANTHEM
				, MathsSounds.FAMAS_SOUND, MathsSounds.DAB, MathsSounds.ULTRA_MEGA_SHOTGUN, MathsSounds.MNJ_SOUND, MathsSounds.MEEM, MathsSounds.FRYING_PAN
				, MathsSounds.YES, MathsSounds.PROJET, MathsSounds.CLOWN, MathsSounds.FRUIT_PUNCH, MathsSounds.MOUCHE, MathsSounds.TATANATOR);
	}
	
	@SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) 
	{
		event.getRegistry().registerAll(ItemWolfArmor.DYE, ItemGunLayer.LAYERED, RecipeCustomSword.CUSTOM_SWORD, ItemGlowberry.GLOWBERRY);
	}
	
	@SubscribeEvent
    public void registerPotionTypes(RegistryEvent.Register<PotionType> event) 
	{
		event.getRegistry().registerAll(RegisterPotions.INVUL_TYPE, RegisterPotions.FIRE_REGEN_TYPE);
	}
	
	@SubscribeEvent
    public void registerVillagerTypes(RegistryEvent.Register<VillagerProfession> event)
	{
		event.getRegistry().registerAll(EntitiesInit.ENCHANTER, EntitiesInit.FROZEN);
	}
	
}