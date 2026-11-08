package com.mod.mathsmod.add;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.blocks.*;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;

public class RegisterBlocks {
	
	public static final Block TURRET = new BlockTurret("turret");
	public static final Block AUTO_TURRET = new BlockAutoTurret();
	public static final Block SILVER_BLOCK = new BlockSilver();
	public static final Block SILVER_ORE = new BlockSilverOre();
	public static final BlockSilverChest SILVER_CHEST = new BlockSilverChest();
	public static final Block FURNACE = new BlockMultiFurnace();
	public static final Block FIRE = new BlockBlueFire();
	public static final Block TFOSIUM_BLOCK = new BlockTfosiumBlock();
	public static final Block TFOSIUM_ORE = new BlockTfosiumOre();
	public static final Block KURAYUM_BLOCK = new BlockKurayumBlock();
	public static final Block KURAYUM_ORE = new BlockKurayumOre();
	public static final Block GUN_CRAFTER = new BlockGunCrafter();
	public static final Block RECUPERATOR = new BlockRecuperator();
	public static final Block EPROJECTOR = new BlockEntityProjector();
	public static final Block DISENCHANTER = new BlockDisenchanter();
	public static final Block ENTITY_ABSORBER = new BlockEntityAbsorber();
	public static final Block WOLF_ARMOR_CRAFTER = new BlockWolfArmorCrafter();
	public static final Block COPRAVEG_RG = new BlockCopravegRG();
	public static final Block BLOCKSHS = new BlockSuperHunterSpawner();
	public static final BlockCopravegPortal PORTAL = new BlockCopravegPortal();
	public static final Block GREEN_LAVA = new BlockGreenLava();
	public static final Block DENANIUM = new BlockSpecialFlower();
	public static final Block EXPLODA = new BlockExplodaFlower();
	public static final Block DIAMONA = new BlockDiamonaFlower();
	public static final Block MISSILE_LAUNCHER = new BlockMissileLauncher();
	public static final Block LIGHTNING_GEN = new BlockLightningGenerator();
	public static final Block BURNED_LOG = new BlockBurnedLog();
	public static final Block BLACK_STONE = new BlockBlackStone();
	public static final Block COPRAVEG_PLANT = new BlockCopravegPlant();
	public static final Block LOCKER = new BlockLocker();
	public static final Block REDSTONE_JUKEBOX = new BlockRedstoneJukebox();
	public static final Block THORIUM_ORE = new BlockThoriumOre();
	public static final Block THORIUM_BLOCK = new BlockThoriumBlock();
	public static final Block DESTRUCTIVE_WALL = new BlockDestructiveWall();
	public static final BlockSirojusPortal SIROPORTAL = new BlockSirojusPortal();
	public static final Block FROZEN_BRICK = new BlockSirojusDeco(Material.ROCK, "frozen_brick", 7, 80, true);
	public static final Block FROZEN_STONE = new BlockSirojusDeco(Material.ROCK, "frozen_stone", 4, 4, true);
	public static final Block PERMICE_STONE = new BlockSirojusDeco(Material.ROCK, "permice_stone", 5, 5, true);
	public static final Block CRAB_PRISON = new BlockPrison();
	public static final BlockBoundaries CRAB_LIMITS= new BlockBoundaries();
	public static final Block FROZEN_WATER = new BlockFrozenWater();
	public static final Block REINFORCED_GLASS = new BlockReinforcedGlass();
	public static final Block LOOTBOX = new BlockLootBox();
	public static final Block POLARITE_ORE = new BlockPolariteOre();
	public static final Block POLARITE_BLOCK = new BlockPolarite();
	public static final Block CAVE_FINDER = new BlockCaveFinder();
	public static final Block SIROJUS_PORTAL_FRAME = new BlockSirojusPortalFrame();
	public static final Block UNSTABLE_TNT = new BlockBetterTNT();
	public static final Block ETERNAL_FIRE = new BlockEternalFire();
	public static final Block ELEVATOR = new BlockElevator();
	public static final Block TELEPORTER = new BlockTeleporter();
	public static final Block PINK_LEAVES = new BlockPinkLeaves();
	public static final Block STABILIZER = new BlockBlackHoleStabilizer();
	public static final Block BLACK_HOLE = new BlockBlackHole();
	//public static final Block MACERATOR = new BlockMacerator();
	public static final Block PULSIOR_ALTAR = new BlockPulsiorAltar();
	public static final Block METALURGIC_FURNACE = new BlockMetalurgicFurnace();
	public static final Block CASTER = new BlockCaster();
	public static final Block BOREALITE_ORE = new BlockBorealiteOre(true);
	public static final Block BOREALITE_BLOCK = new BlockBorealiteOre(false);
	public static final Block FROZEN_LEAVES = new BlockFrozenLeaves();
	public static final Block BLACKSTONE_BRICK = new BlockBlackstoneBrick();
	public static final Block FROZEN_GLASS = new BlockFrozenGlass();
	public static final Block POULOPOP_ALTAR = new BlockPoulopopAltar();
	public static final Block MOB_GENERATOR = new BlockMobGenerator();
	public static final Block FPC = new BlockFruitPunchClown();
	public static final Block BEAR_TRAP = new BlockBearsTrap();
	public static final Block RADIOACTIVE_BLACKSTONE = new BlockDecoBlackstone("radioactive_blackstone");
	public static final Block MOSSY_BLACK_STONE = new BlockDecoBlackstone("mossy_blackstone");
	public static final Block HELL_FLOWER = new BlockHellFlower();
	public static final Block BANANA_PORTAL_FRAME = new BlockBananaPortalFrame();
	public static final BlockBananaPortal BANANA_PORTAL = new BlockBananaPortal();
	public static final Block CHAUVOPUT_ALTAR = new BlockChauvoputAltar();
	public static final Block BLACK_KATANA_HOLDER_BLOCK = new BlockBlackKatanaHolder();
	public static final Block BASIC_LOOTBOX = new BlockBasicLootBox();
	
	public static final Block CHERRY_LOG = new BlockCherryLog();
	public static final Block CHERRY_PLANKS = new BlockCherryPlanks();
	public static final Block CHERRY_SAPLING = new BlockCherrySapling();
	public static final BlockSlab DOUBLE_CHERRY_SLAB = new BlockCherrySlab(true);
	public static final BlockSlab CHERRY_SLAB = new BlockCherrySlab(false);
	public static final Block CHERRY_STAIRS = new BlockCherryStairs();
	public static final Block TOMB = new BlockTomb();
	public static final Block ORE_LOOTBOX = new BlockOreLootbox();
	public static final Block BLUE_CRYSTAL_BLOCK = new BlockCrystalBlock("blue");
	public static final Block RED_CRYSTAL_BLOCK = new BlockCrystalBlock("red");
	public static final Block GREEN_CRYSTAL_BLOCK = new BlockCrystalBlock("green");
	public static final Block PURPLE_CRYSTAL_BLOCK = new BlockCrystalBlock("purple");
	public static final Block BLUE_CRYSTAL = new BlockCrystal("blue");
	public static final Block RED_CRYSTAL = new BlockCrystal("red");
	public static final Block GREEN_CRYSTAL = new BlockCrystal("green");
	public static final Block PURPLE_CRYSTAL = new BlockCrystal("purple");
	public static final Block SILVER_CRYSTAL = new BlockCrystal("silver");
	public static final Block GRASSY_STONE = new BlockGrassyStone();
	public static final Block GLOWBERRY_BUSH = new BlockGlowberryBush();
	public static final Block BLOCK_RSP= new BlockRandomisedStructurePlacer();
	public static final Block WEIRD_WATER = new BlockWeirdWater();
	public static final Block BURNT_PLANKS = new BlockBurntPlanks();
	public static final BlockSlab DOUBLE_BURNT_SLAB = new BlockBurntSlab(true);
	public static final BlockSlab BURNT_SLAB = new BlockBurntSlab(false);
	public static final Block BURNT_STAIRS = new BlockBurntStairs();
	public static final Block WEIRD_STONE = new BlockStoneWeird();
	public static final Block CANDLE = new BlockCandle();
	public static final Block MYSTIC_CIRCLE = new BlockMysticCircle();
	public static final Block MAGMATIC_SOIL = new BlockMagmaticSoil();
	
	
	//public static List<Item> items = Lists.newArrayList();
	//public static List<BlockUnbreakableCopy> copies = Lists.newArrayList();
	
	/*public static void registerBlockCopies(RegistryEvent.Register<Block> event)
	{
		for(int i = 0; i <= 255; ++i)
		{
			if(Block.getBlockById(i) != null && Block.getBlockById(i).getRegistryName().getResourceDomain() == "minecraft"
					&& !(Block.getBlockById(i) instanceof BlockLiquid) && !(Block.getBlockById(i) instanceof BlockContainer)
					&& Block.getBlockById(i).getStateFromMeta(0) == Block.getBlockById(i).getStateFromMeta(1)
					&& Block.getBlockById(i).getBlockHardness(null, null, null) >= 0 && Block.getBlockById(i).getBlockState().getProperties().size() == 0
					&& Block.getBlockById(i) != Blocks.AIR && !(Block.getBlockById(i) instanceof BlockUnbreakableCopy) && Block.getBlockById(i) != Blocks.DEADBUSH
					&& Block.getBlockById(i) != Blocks.STRUCTURE_VOID)
			{
				BlockUnbreakableCopy block = new BlockUnbreakableCopy(Block.getBlockById(i));
				event.getRegistry().register(block);
				copies.add(block);
				items.add(new ItemBlock(block).setUnlocalizedName(Item.getItemFromBlock(Block.getBlockById(i)).getUnlocalizedName().substring(5)).setRegistryName("unbreakable_" + block.getRegistryName().getResourcePath()));
			}
		}
	}*/
	
	/*public static void registerItemBlockCopies(RegistryEvent.Register<Item> event)
	{
		for(Item item : items)
		{
			event.getRegistry().register(item);
		}
	}*/
}
