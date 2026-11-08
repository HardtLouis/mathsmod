package com.mod.mathsmod.add;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.*;
import com.mod.mathsmod.add.items.ItemArmor.ItemAfro;
import com.mod.mathsmod.add.items.ItemArmor.ItemBlackHat;
import com.mod.mathsmod.add.items.ItemArmor.ItemBulletproofVest;
import com.mod.mathsmod.add.items.ItemArmor.ItemChapka;
import com.mod.mathsmod.add.items.ItemArmor.ItemDirectTit;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemFestiveJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemHeavyBoots;
import com.mod.mathsmod.add.items.ItemArmor.ItemHood;
import com.mod.mathsmod.add.items.ItemArmor.ItemHunterGasMask;
import com.mod.mathsmod.add.items.ItemArmor.ItemIronJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemIronSkull;
import com.mod.mathsmod.add.items.ItemArmor.ItemJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemKepi;
import com.mod.mathsmod.add.items.ItemArmor.ItemKurayumArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemKurayumJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemPlatypusPaw;
import com.mod.mathsmod.add.items.ItemArmor.ItemPolariteArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemRebondiBoots;
import com.mod.mathsmod.add.items.ItemArmor.ItemRedCloth;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemThoriumArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemTutu;
import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemUnknownHelmet;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemAggressiveChestplate;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemAggressiveLeggings;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemAttackerChestplate;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemAttackerLeggings;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemFugitiveChestplate;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemFugitiveLeggings;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemShieldChestplate;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemShieldLeggings;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemStatMetalicArmor;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemTankChestplate;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemTankLeggings;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemWarriorChestplate;
import com.mod.mathsmod.add.items.ItemArmor.statArmor.ItemWarriorLeggings;
import com.mod.mathsmod.add.items.ItemArmor.wolf.ItemWolfArmor;
import com.mod.mathsmod.add.items.ItemArmor.wolf.ItemWolfKurayumArmor;
import com.mod.mathsmod.add.items.golemUpgrades.ItemFireUpgrade;
import com.mod.mathsmod.add.items.guns.*;
import com.mod.mathsmod.add.items.guns.accessories.ItemAssaultGrenadeLauncher;
import com.mod.mathsmod.add.items.guns.accessories.ItemBasicScope;
import com.mod.mathsmod.add.items.guns.accessories.ItemBasicSight;
import com.mod.mathsmod.add.items.guns.accessories.ItemBasicSoundMuffler;
import com.mod.mathsmod.add.items.guns.accessories.ItemHalfMuffler;
import com.mod.mathsmod.add.items.guns.renderer.GunSpecialRenderer;
import com.mod.mathsmod.add.items.guns.specials.ItemMegaRL;
import com.mod.mathsmod.add.items.metaltools.ItemBerserSword;
import com.mod.mathsmod.add.items.metaltools.ItemGourdin;
import com.mod.mathsmod.add.items.metaltools.ItemMetalicChargeShield;
import com.mod.mathsmod.add.items.metaltools.ItemZdeuchHammer;
import com.mod.mathsmod.add.items.toolparts.ItemBamBlade;
import com.mod.mathsmod.add.items.toolparts.ItemBasicBlade;
import com.mod.mathsmod.add.items.toolparts.ItemBasicGuard;
import com.mod.mathsmod.add.items.toolparts.ItemDamageBlade;
import com.mod.mathsmod.add.items.toolparts.ItemDefenceGuard;
import com.mod.mathsmod.add.items.toolparts.ItemHammerBlade;
import com.mod.mathsmod.add.items.toolparts.ItemHammerPart;
import com.mod.mathsmod.add.items.toolparts.ItemHandle;
import com.mod.mathsmod.add.items.toolparts.ItemHandleS2;
import com.mod.mathsmod.add.items.toolparts.ItemOffenseGuard;
import com.mod.mathsmod.add.items.toolparts.ItemPickaxeHandle;
import com.mod.mathsmod.add.items.toolparts.ItemPickaxePart;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.stats.StatList;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegisterItems {
	
	public static ArmorMaterial UNKNOWN = EnumHelper.addArmorMaterial("unknown", Mathsmod.MODID + ":unknown", 4, new int[]{0, 0, 0, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial HATS = EnumHelper.addArmorMaterial("hats", Mathsmod.MODID + ":hats", 0, new int[]{0, 0, 0, 4}, 9, SoundEvents.BLOCK_SNOW_BREAK, 0.3F);
	public static ArmorMaterial BULLETPROOF = EnumHelper.addArmorMaterial("bulletproof", Mathsmod.MODID + ":bulletproof_vest", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.BLOCK_SNOW_PLACE, 0.2F);
	public static ToolMaterial LEGENDARY = EnumHelper.addToolMaterial("legendary", 7, 0, 25F, 12F, 30);
	public static ArmorMaterial JETPACK_ARMOR = EnumHelper.addArmorMaterial("jetpack", Mathsmod.MODID + ":jetpack", 0, new int[]{0, 0, 0, 0}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial EXPLOSIVE_B = EnumHelper.addArmorMaterial("explosive", Mathsmod.MODID + ":explosive", 0, new int[]{0, 0, 0, 0}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	
	public static ArmorMaterial PLATIPUS = EnumHelper.addArmorMaterial("platipus", Mathsmod.MODID + ":platipus", 4, new int[]{0, 0, 0, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	
	public static ArmorMaterial FUGITIVE = EnumHelper.addArmorMaterial("fugitive", Mathsmod.MODID + ":fugitive", 0, new int[]{10, 12, 14, 10}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial ATTACKER = EnumHelper.addArmorMaterial("attacker", Mathsmod.MODID + ":attacker", 0, new int[]{10, 12, 14, 10}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial AGGRESSIVE= EnumHelper.addArmorMaterial("aggressive", Mathsmod.MODID + ":aggressive", 0, new int[]{10, 12, 14, 10}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial SHIELD = EnumHelper.addArmorMaterial("shield", Mathsmod.MODID + ":shield", 0, new int[]{10, 12, 14, 10}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial TANK = EnumHelper.addArmorMaterial("tank", Mathsmod.MODID + ":tank", 0, new int[]{10, 12, 14, 10}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial WARRIOR = EnumHelper.addArmorMaterial("warrior", Mathsmod.MODID + ":warrior", 0, new int[]{10, 12, 14, 10}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	
	public static ArmorMaterial THORIUM = EnumHelper.addArmorMaterial("thorium", Mathsmod.MODID + ":thorium", 0, new int[]{12, 15, 18, 12}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.2F);
	public static ArmorMaterial UBER = EnumHelper.addArmorMaterial("uber", Mathsmod.MODID + ":uber", 0, new int[]{Integer.MAX_VALUE / 5, Integer.MAX_VALUE / 5, Integer.MAX_VALUE / 5, Integer.MAX_VALUE / 5}, 9, SoundEvents.BLOCK_ANVIL_FALL, 0.2F);
	public static ArmorMaterial POLARITE = EnumHelper.addArmorMaterial("polarite", Mathsmod.MODID + ":polarite", 0, new int[]{14, 17, 20, 14}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.3F);
	
	public static ToolMaterial SILVER = EnumHelper.addToolMaterial("silver", 4, 1734, 12F, 4, 9);
	public static ArmorMaterial SILVER_ARMOR = EnumHelper.addArmorMaterial("silver", Mathsmod.MODID + ":silver", 38, new int[]{4, 7, 9, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.2F).setRepairItem(new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
	public static ToolMaterial TFOSIUM = EnumHelper.addToolMaterial("tfosium", 5, 2032, 14F, 6F, 8);
	public static ArmorMaterial TFOSIUM_ARMOR = EnumHelper.addArmorMaterial("tfosium", Mathsmod.MODID + ":tfosium", 50, new int[]{6, 9, 11, 6}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F).setRepairItem(new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
	public static ToolMaterial KURAYUM = EnumHelper.addToolMaterial("kurayum", 6, 0, 16F, 9F, 6);
	public static ArmorMaterial KURAYUM_ARMOR = EnumHelper.addArmorMaterial("kurayum", Mathsmod.MODID + ":kurayum", 65, new int[]{8, 10, 12, 8}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F).setRepairItem(new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
	
	public static final Item TURRET = new ItemBlock(RegisterBlocks.TURRET).setRegistryName("turret");
	public static final Item AUTO_TURRET = new ItemBlock(RegisterBlocks.AUTO_TURRET).setRegistryName("auto_turret");
	public static final Item FURNACE = new ItemBlock(RegisterBlocks.FURNACE).setRegistryName("multi_furnace");
	public static final Item GUN_CRAFTER = new ItemBlock(RegisterBlocks.GUN_CRAFTER).setRegistryName("gun_crafter");
	public static final Item RECUPERATOR = new ItemBlock(RegisterBlocks.RECUPERATOR).setRegistryName("recuperator");
	public static final Item EPROJECTOR = new ItemBlock(RegisterBlocks.EPROJECTOR).setRegistryName("entity_projector");
	public static final Item DISENCHANTER = new ItemBlock(RegisterBlocks.DISENCHANTER).setRegistryName("disenchanter");
	public static final Item ENTITY_ABSORBER = new ItemBlock(RegisterBlocks.ENTITY_ABSORBER).setRegistryName("entity_absorber");
	public static final Item WOLF_ARMOR_CRAFTER = new ItemBlock(RegisterBlocks.WOLF_ARMOR_CRAFTER).setRegistryName("wolf_armor_crafter");
	public static final Item COPRAVEG_RG = new ItemBlock(RegisterBlocks.COPRAVEG_RG).setRegistryName("copraveg_rg");
	public static final Item SUPER_HUNTER_SPAWNER = new ItemBlock(RegisterBlocks.BLOCKSHS).setRegistryName("super_hunter_spawner");
	public static final Item DENANIUM = new ItemBlock(RegisterBlocks.DENANIUM).setRegistryName("denanium");
	public static final Item EXPLODA = new ItemBlock(RegisterBlocks.EXPLODA).setRegistryName("exploda");
	public static final Item DIAMONA = new ItemBlock(RegisterBlocks.DIAMONA).setRegistryName("diamona");
	public static final Item MISSILE_LAUNCHER = new ItemBlock(RegisterBlocks.MISSILE_LAUNCHER).setRegistryName("missile_launcher");
	public static final Item LIGHTNING_GEN = new ItemBlock(RegisterBlocks.LIGHTNING_GEN).setRegistryName("lightning_generator");
	public static final Item BURNED_LOG = new ItemBlock(RegisterBlocks.BURNED_LOG).setRegistryName("burnt_log");
	public static final Item BLACK_STONE = new ItemBlock(RegisterBlocks.BLACK_STONE).setRegistryName("black_stone");
	public static final Item COPRAVEG_PLANT = new ItemBlock(RegisterBlocks.COPRAVEG_PLANT).setRegistryName("copraveg_plant");
	public static final Item LOCKER = new ItemBlock(RegisterBlocks.LOCKER).setRegistryName("locker");
	public static final Item REDSTONE_JUKEBOX = new ItemBlock(RegisterBlocks.REDSTONE_JUKEBOX).setRegistryName("redstone_jukebox");
	public static final Item FROZEN_BRICK = new ItemBlock(RegisterBlocks.FROZEN_BRICK).setRegistryName("frozen_brick");
	public static final Item FROZEN_STONE = new ItemBlock(RegisterBlocks.FROZEN_STONE).setRegistryName("frozen_stone");
	public static final Item PERMICE_STONE = new ItemBlock(RegisterBlocks.PERMICE_STONE).setRegistryName("permice_stone");
	public static final Item REINFORCED_GLASS = new ItemBlock(RegisterBlocks.REINFORCED_GLASS).setRegistryName("reinforced_glass");
	public static final Item LOOTBOX = new ItemBlock(RegisterBlocks.LOOTBOX).setRegistryName("loot_box");
	public static final Item CAVE_FINDER = new ItemBlock(RegisterBlocks.CAVE_FINDER).setRegistryName("cave_finder");
	public static final Item SIROJUS_PORTAL_FRAME = new ItemBlock(RegisterBlocks.SIROJUS_PORTAL_FRAME).setRegistryName("sirojus_portal_frame");
	public static final Item UNSTABLE_TNT = new ItemBlock(RegisterBlocks.UNSTABLE_TNT).setRegistryName("unstable_tnt");
	public static final Item ELEVATOR = new ItemBlock(RegisterBlocks.ELEVATOR).setRegistryName("elevator");
	public static final Item TELEPORTER = new ItemBlock(RegisterBlocks.TELEPORTER).setRegistryName("teleporter");
	public static final Item PINK_LEAVES = new ItemBlock(RegisterBlocks.PINK_LEAVES).setRegistryName("pink_leaves");
	public static final Item STABILIZER = new ItemBlock(RegisterBlocks.STABILIZER).setRegistryName("stabilizer");
	public static final Item BLACK_HOLE = new ItemBlock(RegisterBlocks.BLACK_HOLE).setRegistryName("black_hole");
	//public static final Item MACERATOR = new ItemBlock(RegisterBlocks.MACERATOR).setRegistryName("macerator");
	public static final Item PULSIOR_ALTAR = new ItemBlock(RegisterBlocks.PULSIOR_ALTAR).setRegistryName("pulsior_altar");
	public static final Item METALURGIC_FURNACE = new ItemBlock(RegisterBlocks.METALURGIC_FURNACE).setRegistryName("metalurgic_furnace");
	public static final Item CASTER = new ItemBlock(RegisterBlocks.CASTER).setRegistryName("caster");
	public static final Item BOREALITE_ORE = new ItemBlock(RegisterBlocks.BOREALITE_ORE).setRegistryName("borealite_ore");
	public static final Item BOREALITE_BLOCK = new ItemBlock(RegisterBlocks.BOREALITE_BLOCK).setRegistryName("borealite_block");
	public static final Item FROZEN_LEAVES = new ItemBlock(RegisterBlocks.FROZEN_LEAVES).setRegistryName("frozen_leaves");
	public static final Item BLACKSTONE_BRICK = new ItemBlock(RegisterBlocks.BLACKSTONE_BRICK).setRegistryName("blackstone_brick");
	public static final Item FROZEN_GLASS = new ItemBlock(RegisterBlocks.FROZEN_GLASS).setRegistryName("frozen_glass");
	public static final Item POULOPOP_ALTAR = new ItemBlock(RegisterBlocks.POULOPOP_ALTAR).setRegistryName("poulopop_altar");
	public static final Item MOB_GENERATOR = new ItemBlock(RegisterBlocks.MOB_GENERATOR).setRegistryName("mob_generator");
	public static final Item HELL_FLOWER = new ItemBlock(RegisterBlocks.HELL_FLOWER).setRegistryName("hell_flower");
	public static final Item FPC = new ItemBlock(RegisterBlocks.FPC)
	{
		protected final UUID CLOWN_MODIFIER = UUID.fromString("FA233E1C-8362-2834-B01B-BCCE9995ACA3");
		
		@Override
	    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
	    {
	        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
	    	
	    	if(slot == EntityEquipmentSlot.MAINHAND)
	    	{
	            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(CLOWN_MODIFIER, "Weapon modifier", 7, 0));
	    	}

	        return multimap;
	    }
		
	}.setRegistryName("fruit_punch_clown");
	public static final Item BEAR_TRAP = new ItemBlock(RegisterBlocks.BEAR_TRAP).setRegistryName("bear_trap");
	public static final Item MOSSY_BLACKSTONE = new ItemBlock(RegisterBlocks.MOSSY_BLACK_STONE).setRegistryName("mossy_blackstone");
	public static final Item RADIOACTIVE_BLACKSTONE = new ItemBlock(RegisterBlocks.RADIOACTIVE_BLACKSTONE).setRegistryName("radioactive_blackstone");
	public static final Item BANANA_PORTAL_FRAME = new ItemBlock(RegisterBlocks.BANANA_PORTAL_FRAME).setRegistryName("banana_portal_frame");
	public static final Item CHAUVOPUT_ALTAR = new ItemBlock(RegisterBlocks.CHAUVOPUT_ALTAR).setRegistryName("chauvoput_altar");
	public static final Item BLACK_KATANA_HOLDER_BLOCK = new ItemBlock(RegisterBlocks.BLACK_KATANA_HOLDER_BLOCK).setRegistryName("black_katana_holder_block");
	public static final Item BASIC_LOOTBOX = new ItemBlock(RegisterBlocks.BASIC_LOOTBOX).setRegistryName("basic_loot_box");
	public static final Item LOG_CHERRY = new ItemBlock(RegisterBlocks.CHERRY_LOG).setRegistryName("cherry_log");
	public static final Item PLANKS_CHERRY = new ItemBlock(RegisterBlocks.CHERRY_PLANKS).setRegistryName("cherry_planks");
	public static final Item SAPLING_CHERRY = new ItemBlock(RegisterBlocks.CHERRY_SAPLING).setRegistryName("cherry_sapling");
	public static final Item CHERRY_SLAB = new ItemSlab(RegisterBlocks.CHERRY_SLAB, RegisterBlocks.CHERRY_SLAB, RegisterBlocks.DOUBLE_CHERRY_SLAB).setRegistryName("cherry_slab");
	public static final Item CHERRY_STAIRS = new ItemBlock(RegisterBlocks.CHERRY_STAIRS).setRegistryName("cherry_stairs");
	public static final Item ORE_LOOTBOX = new ItemBlock(RegisterBlocks.ORE_LOOTBOX).setRegistryName("ore_loot_box");
	public static final Item BLOCK_RSP = new ItemBlock(RegisterBlocks.BLOCK_RSP).setRegistryName("randomized_structure_placer");
	
	public static final Item BLUE_CRYSTAL_BLOCK = new ItemBlock(RegisterBlocks.BLUE_CRYSTAL_BLOCK).setRegistryName("blue_crystal_block");
	public static final Item RED_CRYSTAL_BLOCK = new ItemBlock(RegisterBlocks.RED_CRYSTAL_BLOCK).setRegistryName("red_crystal_block");
	public static final Item GREEN_CRYSTAL_BLOCK = new ItemBlock(RegisterBlocks.GREEN_CRYSTAL_BLOCK).setRegistryName("green_crystal_block");
	public static final Item PURPLE_CRYSTAL_BLOCK = new ItemBlock(RegisterBlocks.PURPLE_CRYSTAL_BLOCK).setRegistryName("purple_crystal_block");
	public static final Item BLUE_CRYSTAL = new ItemBlock(RegisterBlocks.BLUE_CRYSTAL).setRegistryName("blue_crystal");
	public static final Item RED_CRYSTAL = new ItemBlock(RegisterBlocks.RED_CRYSTAL).setRegistryName("red_crystal");
	public static final Item GREEN_CRYSTAL = new ItemBlock(RegisterBlocks.GREEN_CRYSTAL).setRegistryName("green_crystal");
	public static final Item PURPLE_CRYSTAL = new ItemBlock(RegisterBlocks.PURPLE_CRYSTAL).setRegistryName("purple_crystal");
	public static final Item SILVER_CRYSTAL = new ItemBlock(RegisterBlocks.SILVER_CRYSTAL).setRegistryName("silver_crystal");
	public static final Item GRASSY_STONE = new ItemBlock(RegisterBlocks.GRASSY_STONE).setRegistryName("grassy_stone");
	public static final Item GLOWBERRY_BUSH = new ItemBlock(RegisterBlocks.GLOWBERRY_BUSH).setRegistryName("glowberry_bush");
	public static final Item PLANKS_BURNT = new ItemBlock(RegisterBlocks.BURNT_PLANKS).setRegistryName("burnt_planks");
	public static final Item BURNT_SLAB = new ItemSlab(RegisterBlocks.BURNT_SLAB, RegisterBlocks.BURNT_SLAB, RegisterBlocks.DOUBLE_BURNT_SLAB).setRegistryName("burnt_slab");
	public static final Item BURNT_STAIRS = new ItemBlock(RegisterBlocks.BURNT_STAIRS).setRegistryName("burnt_stairs");
	public static final Item WEIRD_STONE = new ItemBlock(RegisterBlocks.WEIRD_STONE).setRegistryName("weird_stone");
	public static final Item CANDLE = new ItemBlock(RegisterBlocks.CANDLE).setRegistryName("candle");
	public static final Item MYSTIC_CIRCLE = new ItemBlock(RegisterBlocks.MYSTIC_CIRCLE).setRegistryName("mystic_circle");
	public static final Item MAGMATIC_SOIL = new ItemBlock(RegisterBlocks.MAGMATIC_SOIL).setRegistryName("magmatic_soil");
	
	public static final Item SILVER_BLOCK = new ItemBlock(RegisterBlocks.SILVER_BLOCK).setRegistryName("silver_block");
	public static final Item SILVER_ORE = new ItemBlock(RegisterBlocks.SILVER_ORE).setRegistryName("silver_ore");
	public static final Item SILVER_CHEST = new ItemBlock(RegisterBlocks.SILVER_CHEST).setRegistryName("silver_chest");
	
	public static final Item TFOSIUM_BLOCK = new ItemBlock(RegisterBlocks.TFOSIUM_BLOCK).setRegistryName("tfosium_block");
	public static final Item TFOSIUM_ORE = new ItemBlock(RegisterBlocks.TFOSIUM_ORE).setRegistryName("tfosium_ore");
	
	public static final Item KURAYUM_HELMET = new ItemKurayumArmor(KURAYUM_ARMOR, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("kurayum_helmet").setRegistryName("kurayum_helmet");
	public static final Item KURAYUM_CHESTPLATE = new ItemKurayumArmor(KURAYUM_ARMOR, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("kurayum_chestplate").setRegistryName("kurayum_chestplate");
	public static final Item KURAYUM_LEGGINGS = new ItemKurayumArmor(KURAYUM_ARMOR, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("kurayum_leggings").setRegistryName("kurayum_leggings");
	public static final Item KURAYUM_BOOTS = new ItemKurayumArmor(KURAYUM_ARMOR, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("kurayum_boots").setRegistryName("kurayum_boots");
	
	public static final Item UNKNOWN_HELMET = new ItemUnknownHelmet().setUnlocalizedName("unknown_helmet").setRegistryName("unknown_helmet");
	public static final Item BULLETPROOF_VEST = new ItemBulletproofVest().setUnlocalizedName("bulletproof_vest").setRegistryName("bulletproof_vest");
	
	public static final Item KURAYUM_SWORD = new ItemKurayumSword().setRegistryName( "kurayum_sword").setUnlocalizedName("kurayum_sword").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item KURAYUM_AXE = new ItemKurayumAxe().setRegistryName("kurayum_axe").setUnlocalizedName("kurayum_axe").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	
	public static final Item KURAYUM_BLOCK = new ItemBlock(RegisterBlocks.KURAYUM_BLOCK).setRegistryName("kurayum_block");
	public static final Item KURAYUM_ORE = new ItemBlock(RegisterBlocks.KURAYUM_ORE).setRegistryName("kurayum_ore");
	
	public static final Item THORIUM_ORE = new ItemBlock(RegisterBlocks.THORIUM_ORE).setRegistryName("thorium_ore");
	public static final Item THORIUM_BLOCK = new ItemBlock(RegisterBlocks.THORIUM_BLOCK).setRegistryName("thorium_block");
	
	public static final Item POLARITE_BLOCK = new ItemBlock(RegisterBlocks.POLARITE_BLOCK).setRegistryName("polarite_block");
	public static final Item POLARITE_ORE = new ItemBlock(RegisterBlocks.POLARITE_ORE).setRegistryName("polarite_ore");
	
	public static final Item FROZEN_SWORD = new ItemFrozenSword().setRegistryName("frozen_sword").setUnlocalizedName("frozen_sword").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	
	public static final Item PLANE = new ItemPlane();
	public static final Item OBUS = new ItemObus().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final ItemBasicGrenade GRENADE = (ItemBasicGrenade) new ItemBasicGrenade().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item FRAG_GRENADE = new ItemFragmentationGrenade().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item GAZ_GRENADE = new ItemGazGrenade().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item STICKY_GRENADE = new ItemStickyGrenade().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item DRILLING_MACHINE = new ItemDrillingMachine();
	public static final Item ELECTRIC_BOAT = new ItemElectricBoat();
	public static final Item PROJECTOR_CHANGER = new ItemProjectorChanger();
	public static final Item TANK_ITEM = new ItemTank();
	public static final Item INSTRUC_BOOK = new ItemInstructionBook();
	public static final Item COPRAVEG_ROD = new ItemCopravegRod();
	public static final Item EYE_OF_BLAL = new ItemM("eye_of_blal");
	public static final Item IRON_SHARD = new ItemM("iron_shard");
	public static final Item NUC_PLANE = new ItemNuclearPlane();
	public static final Item A_MISSILE = new ItemAMissile("a_missile");
	public static final Item EXPLOSIVE_BELT = new ItemExplosiveBelt(false);
	public static final Item FAKE_EXPLOSIVE_BELT = new ItemExplosiveBelt(true);
	public static final Item NUCLEAR_MISSILE = new ItemNuclearMissile();
	public static final Item ASSASSIN_RAPIER = new ItemAssassinRapier();
	public static final Item SHARK_TOOTH = new ItemM("shark_tooth");
	public static final Item HELICOPTER = new ItemHelicopter();
	public static final Item REGENERATIVE_STEAK = new ItemRegenerativeSteak();
	public static final Item KURAYUM_APPLE = new ItemKurayumApple();
	public static final Item B_MISSILE = new ItemBMissile();
	public static final Item THORIUM_INGOT = new ItemM("thorium_ingot");
	public static final Item T_MISSILE = new ItemTMissile();
	public static final Item PLATYPUS_PAW = new ItemPlatypusPaw();
	public static final Item PLAYER_TRACKER = new ItemPlayerTracker();
	public static final Item CAMERA_DRONE = new ItemCameraDrone();
	public static final Item BOT = new ItemBot();
	public static final Item BAT = new ItemBat();
	public static final Item REPULSIVE_ROCKET = new ItemRepulsiveRocket();
	public static final Item SILVER_ROD = new ItemSilverFishingRod();
	public static final Item BLUMON = new ItemBlumon();
	public static final Item HEART_FISH = new ItemHeartFish().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item ENDER_FISH = new ItemEnderFish().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item ARMOR_FISH = new ItemArmorFish().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item FIRE_FISH = new ItemFireFish().setCreativeTab(Mathsmod.MATHSMOD_TAB);
	public static final Item CHARGE_SHIELD = new ItemChargeShield();
	public static final Item FRYING_PAN = new ItemFryingPan();
	public static final Item COPRAVEG_KEY = new ItemM("copraveg_key");
	public static final Item CRAB_ARENA_KEY = new ItemM("crab_key");
	public static final Item ICE_ROD = new ItemM("ice_rod");
	public static final Item GUIDED_ROCKET = new ItemGuidedRocket();
	public static final Item AMMO_BOX = new ItemAmmoBox("ammo_box");
	public static final Item GIPSY_STRENGHT = new ItemGipsyStrenght();
	public static final Item ULTIMATE_VEHICLE = new ItemUltimateVehicle();
	public static final Item SOUTH_SWORD = new ItemSouthSword();
	public static final Item HEAVY_BOOTS = new ItemHeavyBoots();
	public static final Item DIAMOND_SNIPER_BULLET = new ItemDiamondSniperBullet();
	public static final Item PYRITE_GEM = new ItemM("pyrite_gem", new String[] {TextFormatting.GRAY + "extinguish his owner"});
	public static final Item PYRITE_STAFF = new ItemPyriteStaff();
	public static final Item PYRITE_SHIELD = new ItemPyriteShield();
	public static final Item FROZEN_GEM = new ItemM("frozen_gem", new String[] {TextFormatting.GRAY + "avoid death but player lose his stuff and his killstreak"});
	public static final Item TELEPORTERS_BINDER = new ItemTeleportersBinder();
	public static final Item GOLDEN_EXPLOSIVE_BELT = new ItemGoldenExplosiveBelt();
	public static final Item BLACK_HOLE_FRYING_PAN = new ItemBlackHoleFryingPan();
	public static final Item BLACK_HOLE_MISSILE = new ItemBlackHoleMissile();
	public static final Item THERMORESISTANT_BUCKET = new ItemThermoresistantBucket();
	public static final Item BOREALITE_INGOT = new ItemM("borealite_ingot");
	public static final Item THORIUM_FRAGMENT = new ItemM("thorium_fragment");
	public static final Item METALURGIC_FURNACE_UPGRADE = new ItemM("furnace_upgrade").setMaxStackSize(1);
	public static final Item CREATIVE_METALURGIC_FURNACE_UPGRADE = new ItemM("creative_furnace_upgrade").setMaxStackSize(1);
	
	public static final Item FORGING_HAMMER = new ItemM("blacksmith_hammer", new String[] {TextFormatting.YELLOW + "You need to have to carry an ingot in your off hand", TextFormatting.YELLOW + "and shift + right click on an anvil with this item to forge"}).setMaxStackSize(1);
	
	public static final Item POLARITE_SWORD = new ItemPolariteSword();
	public static final Item POLARITE_PICKAXE = new ItemPolaritePickaxe();
	public static final Item POLARITE_AXE = new ItemPolariteAxe();
	
	public static final ItemToolPart BASIC_BLADE = new ItemBasicBlade();
	public static final ItemToolPart BASIC_HANDLE = new ItemHandle();
	public static final ItemToolPart BASIC_GUARD = new ItemBasicGuard();
	
	public static final Item RAINBOW_LAYER = new ItemBaseLayer("rainbow", 1);
	public static final Item LOVE_LAYER = new ItemBaseLayer("love", 2);
	public static final Item SILVER_LAYER = new ItemBaseLayer("silver", 0);
	public static final Item DARK_LAYER = new ItemBaseLayer("dark", 0);
	public static final Item DARK2_LAYER = new ItemBaseLayer("dark2", 0);
	public static final Item CLASSICAL_LAYER = new ItemBaseLayer("classical", 0);
	public static final Item END_LAYER = new ItemBaseLayer("end", 1);
	public static final Item RB_LAYER = new ItemBaseLayer("red_and_black", 0);
	public static final Item CSCAR_LAYER = new ItemBaseLayer("classical_scar", 0);
	public static final Item OLD_LAYER = new ItemBaseLayer("old", 0);
	public static final Item GALAXY_LAYER = new ItemBaseLayer("galaxy", 1);
	public static final Item BW_LAYER = new ItemBaseLayer("blue_and_white", 0);
	public static final Item TFOSIUM_ROD = new ItemTfosiumFishingRod();
	public static final Item VODKA = new ItemVodka();
	public static final Item KEPI = new ItemKepi();
	public static final Item SKELETON_MANAGER = new ItemSkeletonInventoryManager();
	public static final Item SLICER = new ItemM("slicer");
	public static final Item CRUSHER = new ItemM("crusher");
	public static final Item GOLDEN_FRYING_PAN = new ItemGoldenFryingPan();
	public static final Item POLARITE_INGOT = new ItemM("polarite_ingot");
	public static final Item HEAD_CUTTER = new ItemHeadCutter();
	public static final Item FROZEN_HEART = new ItemM("frozen_heart");
	public static final Item POLARITE_FRYING_PAN = new ItemPolariteFryingPan();
	public static final Item CABER = new ItemCaber();
	public static final Item UGLY_LAYER = new ItemBaseLayer("ugly", 2);
	public static final Item ANIMATED_ORANGE_LAYER = new ItemBaseLayer("orange_animated", 2);
	public static final Item ORANGE_PH_LAYER = new ItemBaseLayer("orange_and_black", 0);
	public static final Item FROZEN_LAYER = new ItemBaseLayer("frozen", 1);
	public static final Item COPRAVEG_LAYER = new ItemBaseLayer("copraveg", 1);
	public static final Item WIND_LAYER = new ItemBaseLayer("storm", 0);
	public static final Item NETHER_LAYER = new ItemBaseLayer("nether", 0);
	public static final Item GOLD_LAYER = new ItemBaseLayer("guns_gold", 0);
	public static final Item GOLD_PLATED_LAYER = new ItemBaseLayer("gold_plated", 0);
	
	public static final Item SNIPER = new ItemSniper();
	public static final Item MACHINE_PISTOL = new Itemp90();
	public static final Item BASIC_SHOTGUN = new ItemBasicShotgun();
	public static final Item FAMAS = new ItemFamas("famas");
	public static final Item DEAGLE = new ItemDEagle();
	public static final Item SCAR_H = new ItemSCAR();
	public static final Item AA12 = new ItemAA12();
	public static final Item R870 = new ItemRemington870();
	public static final Item ROCKET_LAUNCHER = new ItemRocketLauncher();
	public static final Item AK47 = new ItemAK47();
	public static final Item AR15 = new ItemAR15();
	public static final Item CARBINE = new ItemCarbine();
	public static final Item HUNTER_SHOTGUN = new ItemHunterShotGun();
	public static final Item THOMPSON = new ItemThompson();
	public static final Item GRENADE_LAUNCHER = new ItemBasicGrenadeLauncher();
	public static final Item FAFR = new ItemFAFR();
	public static final Item GLOCK17 = new ItemGlock17();
	public static final Item KB800 = new ItemKB800();
	public static final Item HEAVY_FAMAS = new ItemHeavyFamas("heavy_famas");
	public static final Item TACTICAL_BOW = new ItemTacticalBow();
	public static final Item MEDIGUN = new ItemMedigun();
	public static final Item BAZOOKB = new ItemBazookb();
	//public static final Item ENDER_SNIPER = new ItemEnderSniper();
	public static final Item ZIP22 = new ItemZip22();
	public static final Item MEDIC_S_THOMPSON = new ItemMedicsThompson();
	public static final Item MEDIC_S_SHOTGUN = new ItemMedicsShotgun();
	public static final Item NTT_SNIPER = new ItemNTTSniper();
	public static final Item SQUID_LAUNCHER = new ItemSquidLauncher();
	public static final Item SCS7 = new ItemSCS7();
	public static final Item SHOTBOW = new ItemShotBow();
	public static final Item CHICKEN_LAUNCHER = new ItemChickenLauncher();
	public static final Item ANTIVEHICLE_RL = new ItemAntivehicleRocketLauncher();
	public static final Item MANJTEMOR = new ItemManjtemor();
	public static final Item BLACK_HOLE_LAUNCHER = new ItemBlackHoleLauncher();
	public static final Item CANARDIER = new ItemCanardier();
	public static final Item REVOLVER = new ItemRevolver();
	public static final Item AERIAN_MONSTRUOSITY = new ItemAerianMonstruosity();
	public static final Item TR_SNIPER = new ItemTRSniper();
	
	public static final Item LAUNCH_TRAP = new ItemLauchTrap();
	
	public static final Item MINIGUN = new ItemMinigun();
	public static final Item GOLDEN_BAT = new ItemGoldenBaseballBat();
	
	public static final Item NUCLEAR_ROCKET_LAUNCHER = new ItemMegaRL();
	
	public static final Item BASIC_SOUND_MUFFLER = new ItemBasicSoundMuffler();
	public static final Item BASIC_SCOPE = new ItemBasicScope();
	public static final Item HALF_MUFFLER = new ItemHalfMuffler();
	public static final Item GUN_GRENADE_LAUNCHER = new ItemAssaultGrenadeLauncher();
	public static final Item BASIC_SIGHT = new ItemBasicSight();
	
	public static final Item FLAME_THROWER = new ItemFlameThrower("flame_thrower");
	public static final Item GRAPPLE = new ItemGrapple();
	public static final Item HARPOON = new ItemHarpoon();
	
	public static final Item SIROJUS_KEY = new ItemM("sirojus_key").setMaxStackSize(1);
	
	public static final Item SMASHER = new ItemSmasher();
	public static final Item INFGAUNT = new ItemInfinityGauntlet();
	
	//public static final Item FIRE_UPGRADE = new ItemFireUpgrade();
	
	public static final ItemSniperBullet SNIPER_BULLET = new ItemSniperBullet();
	public static final ItemBuckshot BUCKSHOT = new ItemBuckshot();
	public static final ItemLittleBullet LITTLE_AMMO = new ItemLittleBullet();
	public static final ItemLongAmmo LONG_AMMO = new ItemLongAmmo();
	public static final ItemRocket ROCKET = new ItemRocket();
	public static final ItemLoveBullet LOVE_BULLET = new ItemLoveBullet();
	public static final ItemHuntingBullet HUNTING_BULLET = new ItemHuntingBullet();
	public static final ItemGrenade REPULSIVE_GRENADE = new ItemRepulsiveGrenade(false);
	public static final ItemGrenade KNOCKBACK_GRENADE = new ItemRepulsiveGrenade(true);
	
	public static final Item DSWORD = new ItemDestroyersSword();
	public static final Item IRON_HAMMER = new ItemHammer(Item.ToolMaterial.IRON, "iron_hammer", 1);
	public static final Item DIAMOND_HAMMER = new ItemHammer(Item.ToolMaterial.DIAMOND, "diamond_hammer", 1);
	public static final Item SILVER_HAMMER = new ItemHammer(SILVER, "silver_hammer", 1);
	public static final Item KURAYUM_HAMMER = new ItemHammer(KURAYUM, "kurayum_hammer", 2);
	public static final Item TFOSIUM_HAMMER = new ItemHammer(TFOSIUM, "tfosium_hammer", 1);
	
	public static final Item COMMUNIST_DISK = new ItemCommunistDisk();
	public static final Item FRENCH_DISK = new ItemFrenchDisk();
	public static final Item ISSOU_DISK = new ItemIssouDisk();
	public static final Item DRONE_LAUNCHER = new ItemDroneLauncher();
	
	public static final Item THORIUM_BOOTS = new ItemThoriumArmor(EntityEquipmentSlot.FEET, "thorium_boots");
	public static final Item THORIUM_LEGS = new ItemThoriumArmor(EntityEquipmentSlot.LEGS, "thorium_leggings");
	public static final Item THORIUM_CHESTPLATE = new ItemThoriumArmor(EntityEquipmentSlot.CHEST, "thorium_chestplate");
	public static final Item THORIUM_HEAD = new ItemThoriumArmor(EntityEquipmentSlot.HEAD, "thorium_helmet");
	
	public static final Item UBER_BOOTS = new ItemUberArmor(EntityEquipmentSlot.FEET, "uber_boots");
	public static final Item UBER_LEGS = new ItemUberArmor(EntityEquipmentSlot.LEGS, "uber_leggings");
	public static final Item UBER_CHESTPLATE = new ItemUberArmor(EntityEquipmentSlot.CHEST, "uber_chestplate");
	public static final Item UBER_HEAD = new ItemUberArmor(EntityEquipmentSlot.HEAD, "uber_helmet");
	
	public static final Item POLARITE_BOOTS = new ItemPolariteArmor(EntityEquipmentSlot.FEET, "polarite_boots");
	public static final Item POLARITE_LEGS = new ItemPolariteArmor(EntityEquipmentSlot.LEGS, "polarite_leggings");
	public static final Item POLARITE_CHESTPLATE = new ItemPolariteArmor(EntityEquipmentSlot.CHEST, "polarite_chestplate");
	public static final Item POLARITE_HEAD = new ItemPolariteArmor(EntityEquipmentSlot.HEAD, "polarite_helmet");
	
	public static final Item KURAYUM_WOLF_BOOTS = new ItemWolfKurayumArmor(EntityEquipmentSlot.FEET).setUnlocalizedName("kurayum_wolf_boots").setRegistryName("kurayum_wolf_boots");
	public static final Item KURAYUM_WOLF_LEGS = new ItemWolfKurayumArmor(EntityEquipmentSlot.LEGS).setUnlocalizedName("kurayum_wolf_leggings").setRegistryName("kurayum_wolf_leggings");
	public static final Item KURAYUM_WOLF_CHEST = new ItemWolfKurayumArmor(EntityEquipmentSlot.CHEST).setUnlocalizedName("kurayum_wolf_chestplate").setRegistryName("kurayum_wolf_chestplate");
	public static final Item KURAYUM_WOLF_HEAD = new ItemWolfKurayumArmor(EntityEquipmentSlot.HEAD).setUnlocalizedName("kurayum_wolf_helmet").setRegistryName("kurayum_wolf_helmet");
	
	public static final Item FUGITIVE_BOOTS = new ItemStatMetalicArmor(FUGITIVE, 0, EntityEquipmentSlot.FEET, -1, 0, 1, 1, 1.5, 5, "fugitive_boots");
	public static final Item WARRIOR_BOOTS = new ItemStatMetalicArmor(WARRIOR, 0, EntityEquipmentSlot.FEET, 0, 0.5, 0.5, 1, 1, 3, "warrior_boots");
	public static final Item ATTACKER_BOOTS = new ItemStatMetalicArmor(ATTACKER, 0, EntityEquipmentSlot.FEET, 0.5, -0.5, 0.5, 1, 1, 3, "attacker_boots");
	public static final Item SHIELD_BOOTS = new ItemStatMetalicArmor(SHIELD, 0, EntityEquipmentSlot.FEET, 0, 0.5, 0, 0.98F, 1, 3, "shield_boots");
	public static final Item TANK_BOOTS = new ItemStatMetalicArmor(TANK, 0, EntityEquipmentSlot.FEET, 0, 1, -0.5, 0.95F, 0.8, 2, "tank_boots");
	public static final Item AGGRESSIVE_BOOTS = new ItemStatMetalicArmor(AGGRESSIVE, 0, EntityEquipmentSlot.FEET, 0.5, -0.5, 0.5, 0.94F, 1, 4, "aggressive_boots");
	
	public static final Item FUGITIVE_LEGGINGS = new ItemFugitiveLeggings();
	public static final Item WARRIOR_LEGGINGS = new ItemWarriorLeggings();
	public static final Item ATTACKER_LEGGINGS = new ItemAttackerLeggings();
	public static final Item SHIELD_LEGGINGS = new ItemShieldLeggings();
	public static final Item TANK_LEGGINGS = new ItemTankLeggings();
	public static final Item AGGRESSIVE_LEGGINGS = new ItemAggressiveLeggings();
	
	public static final Item FUGITIVE_CHESTPLATE = new ItemFugitiveChestplate();
	public static final Item WARRIOR_CHESTPLATE = new ItemWarriorChestplate();
	public static final Item ATTACKER_CHESTPLATE = new ItemAttackerChestplate();
	public static final Item SHIELD_CHESTPLATE = new ItemShieldChestplate();
	public static final Item TANK_CHESTPLATE = new ItemTankChestplate();
	public static final Item AGGRESSIVE_CHESTPLATE = new ItemAggressiveChestplate();
	
	public static final Item GAS_MASK = new ItemHunterGasMask();
	
	public static final Item FUGITIVE_HELMET = new ItemStatMetalicArmor(FUGITIVE, 0, EntityEquipmentSlot.HEAD, 0, 0, 0, 1, 1.5, 5, "fugitive_helmet");
	public static final Item WARRIOR_HELMET = new ItemStatMetalicArmor(WARRIOR, 0, EntityEquipmentSlot.HEAD, 0.25, 0, 0, 0.987F, 1, 3, "warrior_helmet");
	public static final Item ATTACKER_HELMET = new ItemStatMetalicArmor(ATTACKER, 0, EntityEquipmentSlot.HEAD, 0.5, 0, 0, 1, 1, 3, "attacker_helmet");
	public static final Item SHIELD_HELMET = new ItemStatMetalicArmor(SHIELD, 0, EntityEquipmentSlot.HEAD, 0, 0.5, 0, 0.974F, 1, 3, "shield_helmet");
	public static final Item TANK_HELMET = new ItemStatMetalicArmor(TANK, 0, EntityEquipmentSlot.HEAD, 0, 1, -0.5, 0.96F, 0.85, 2, "tank_helmet");
	public static final Item AGGRESSIVE_HELMET = new ItemStatMetalicArmor(AGGRESSIVE, 0, EntityEquipmentSlot.HEAD, 0, 0, 0, 0.96F, 1, 4, "aggressive_helmet");
	
	public static final Item JETPACK = new ItemJetpack("basic_jetpack", 0.13, 5);
	public static final Item IRON_JETPACK = new ItemIronJetpack();
	public static final Item BLACK_HAT = new ItemBlackHat();
	public static final Item CHAPKA = new ItemChapka();
	public static final Item HOOD = new ItemHood();
	public static final Item IRON_SKULL = new ItemIronSkull();
	public static final Item DIRECT_TIT = new ItemDirectTit("direct_tit");
	public static final ItemAlloyIngot ALLOY_INGOT = new ItemAlloyIngot();
	public static final Item CUSTOM_SWORD = new ItemCustomSword();
	public static final ItemToolPart DAMAGE_BLADE = new ItemDamageBlade();
	public static final ItemToolPart OFFENSE_GUARD = new ItemOffenseGuard();
	public static final ItemToolPart DEFENCE_GUARD = new ItemDefenceGuard();
	public static final ItemToolPart HANDLE_STYLE_2 = new ItemHandleS2();
	public static final Item CUSTOM_PICKAXE = new ItemCustomPickaxe();
	public static final ItemToolPart PICKAXE_PART = new ItemPickaxePart();
	public static final ItemToolPart PICKAXE_HANDLE = new ItemPickaxeHandle();
	public static final ItemToolPart HAMMER_PART = new ItemHammerPart();
	public static final Item NIGHT_SHARD = new ItemM("night_shard");
	public static final ItemToolPart BAM_BLADE = new ItemBamBlade();
	public static final ItemToolPart HAMMER_BLADE = new ItemHammerBlade();
	public static final Item DISGUISE_KIT = new ItemDisguiseKit();
	public static final Item FRUIT_PUNCH = new ItemFruitPunch();
	public static final ItemKurayumJetpack KURAYUM_JETPACK = new ItemKurayumJetpack();
	public static final Item NIGHTMARE_STAFF = new ItemNightmareStaff();
	public static final Item CRAB_CLAW = new ItemM("crab_claw");
	public static final Item SUPER_HUNTER_CORE = new ItemSHCore();
	public static final Item BANANA = new ItemFood(4, 20, false).setRegistryName("banana").setUnlocalizedName("banana").setMaxStackSize(64);
	public static final Item METALIC_SHIELD = new ItemMetalicChargeShield();
	public static final Item GOURDIN = new ItemGourdin();
	public static final Item BERSERSWORD = new ItemBerserSword();
	public static final Item REBONDI_BOOTS = new ItemRebondiBoots();
	public static final Item MAGMA_CORE = new ItemM("magma_core");
	public static final Item GHALI_AIRCUT = new ItemAfro();
	public static final Item BELUGA_SADDLE = new ItemM("beluga_saddle").setMaxStackSize(1);
	public static final Item BUFFED_BANANA = new ItemM("buffed_banana");
	public static final Item FESTIVE_JETPACK = new ItemFestiveJetpack();
	public static final Item FESTIVE_ROCKET = new ItemFestiveRocket();
	public static final Item TERRAFORMER = new ItemTerraformer();
	public static final Item CHAINSAW = new ItemChainsaw();
	public static final Item PANCARTE = new ItemPancarte();
	public static final Item TECH_SHIELD = new ItemAbstractProtectionShield("tech_shield");
	public static final Item TATANATOR = new ItemTatanator();
	public static final Item KURAYUM_FISHING_ROD = new ItemKurayumFishingRod();
	public static final Item WHITE_KATANA = new ItemWhiteKatana();
	public static final Item BLACK_KATANA = new ItemBlackKatana();
	public static final Item MOSQUITO_JAR = new ItemMosquitoJar();
	public static final Item TUTU = new ItemTutu();
	public static final Item JARATE = new ItemPissBottle();
	public static final Item MOB_SOUL = new ItemMobSoul();
	public static final Item GLOWBERRY = new ItemGlowberry();
	public static final Item CLOTH = new ItemRedCloth("red_cloth", ArmorMaterial.IRON, 0, 0, 0, 0, 1);
	public static final Item SACRIFICE_DAGGER = new ItemSacrificeDagger();
	public static final Item ZDEUCH_HAMMER = new ItemZdeuchHammer();
	public static final Item ETERNAL_PUNISHMENT = new ItemEternalPunishment();
	public static final Item DEBUFF_SERINGE = new ItemDebuffSeringe();
	public static final Item AIR_BALLOON = new ItemAirBalloon();
	
	public static final Item EARTH_SWORD = new ItemEarthSword();
	public static final Item SEA_SWORD = new ItemWaterSword();
	public static final Item SKY_SWORD = new ItemSkySword();
	
	@SideOnly(Side.CLIENT)
	public static void renderItems()
	{
		RegisterItems.FastRender(FURNACE);
		RegisterItems.FastRender(TURRET);
		RegisterItems.FastRender(AUTO_TURRET);
		RegisterItems.FastRender(GUN_CRAFTER);
		RegisterItems.FastRender(RECUPERATOR);
		RegisterItems.FastRender(EPROJECTOR);
		RegisterItems.FastRender(SILVER_BLOCK);
		RegisterItems.FastRender(SILVER_CHEST);
		RegisterItems.FastRender(SILVER_ORE);
		RegisterItems.FastRender(TFOSIUM_BLOCK);
		RegisterItems.FastRender(TFOSIUM_ORE);
		RegisterItems.FastRender(ENTITY_ABSORBER);
		RegisterItems.FastRender(KURAYUM_HELMET);
		RegisterItems.FastRender(KURAYUM_CHESTPLATE);
		RegisterItems.FastRender(KURAYUM_LEGGINGS);
		RegisterItems.FastRender(KURAYUM_BOOTS);
		RegisterItems.FastRender(UNKNOWN_HELMET);
		RegisterItems.FastRender(BULLETPROOF_VEST);
		RegisterItems.FastRender(PLANE);
		PLANE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(KURAYUM_BLOCK);
		RegisterItems.FastRender(KURAYUM_ORE);
		RegisterItems.FastRender(OBUS);
		RegisterItems.FastRender(GRENADE);
		RegisterItems.FastRender(FRAG_GRENADE);
		RegisterItems.FastRender(GAZ_GRENADE);
		RegisterItems.FastRender(STICKY_GRENADE);
		RegisterItems.FastRender(DRILLING_MACHINE);
		RegisterItems.FastRender(ELECTRIC_BOAT);
		RegisterItems.FastRender(PROJECTOR_CHANGER);
		RegisterItems.FastRender(TANK_ITEM);
		RegisterItems.FastRender(SUPER_HUNTER_SPAWNER);
		RegisterItems.FastRender(COPRAVEG_ROD);
		RegisterItems.FastRender(EYE_OF_BLAL);
		RegisterItems.FastRender(IRON_SHARD);
		RegisterItems.FastRender(NUC_PLANE);
		NUC_PLANE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MISSILE_LAUNCHER);
		A_MISSILE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(A_MISSILE);
		RegisterItems.FastRender(EXPLOSIVE_BELT);
		RegisterItems.FastRender(FAKE_EXPLOSIVE_BELT);
		NUCLEAR_MISSILE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(NUCLEAR_MISSILE);
		RegisterItems.FastRender(LIGHTNING_GEN);
		RegisterItems.FastRender(ASSASSIN_RAPIER);
		RegisterItems.FastRender(BURNED_LOG);
		RegisterItems.FastRender(BLACK_STONE);
		RegisterItems.FastRender(COPRAVEG_PLANT);
		RegisterItems.FastRender(SHARK_TOOTH);
		RegisterItems.FastRender(LOCKER);
		HELICOPTER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(HELICOPTER);
		RegisterItems.FastRender(REGENERATIVE_STEAK);
		RegisterItems.FastRender(KURAYUM_APPLE);
		RegisterItems.FastRender(REDSTONE_JUKEBOX);
		B_MISSILE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(B_MISSILE);
		RegisterItems.FastRender(THORIUM_BLOCK);
		RegisterItems.FastRender(THORIUM_ORE);
		RegisterItems.FastRender(THORIUM_INGOT);
		T_MISSILE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(T_MISSILE);
		RegisterItems.FastRender(REPULSIVE_GRENADE);
		RegisterItems.FastRender(KNOCKBACK_GRENADE);
		RegisterItems.FastRender(GUN_GRENADE_LAUNCHER);
		RegisterItems.FastRender(PLAYER_TRACKER);
		RegisterItems.FastRender(CAMERA_DRONE);
		RegisterItems.FastRender(BOT);
		RegisterItems.FastRender(BAT);
		RegisterItems.FastRender(REPULSIVE_ROCKET);
		RegisterItems.FastRender(SILVER_ROD);
		RegisterItems.FastRender(BLUMON);
		RegisterItems.FastRender(BLACK_HAT);
		RegisterItems.FastRender(HEART_FISH);
		RegisterItems.FastRender(ENDER_FISH);
		RegisterItems.FastRender(ARMOR_FISH);
		RegisterItems.FastRender(FIRE_FISH);
		RegisterItems.FastRender(CHAPKA);
		RegisterItems.FastRender(HOOD);
		RegisterItems.FastRender(TFOSIUM_ROD);
		RegisterItems.FastRender(CHARGE_SHIELD);
		RegisterItems.FastRender(FROZEN_BRICK);
		RegisterItems.FastRender(FROZEN_STONE);
		RegisterItems.FastRender(PERMICE_STONE);
		RegisterItems.FastRender(REINFORCED_GLASS);
		RegisterItems.FastRender(GOLDEN_FRYING_PAN);
		RegisterItems.FastRender(LOOTBOX);
		RegisterItems.FastRender(AMMO_BOX);
		GIPSY_STRENGHT.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(GIPSY_STRENGHT);
		RegisterItems.FastRender(POLARITE_BLOCK);
		RegisterItems.FastRender(POLARITE_ORE);
		RegisterItems.FastRender(CAVE_FINDER);
		RegisterItems.FastRender(POLARITE_SWORD);
		RegisterItems.FastRender(POLARITE_PICKAXE);
		RegisterItems.FastRender(POLARITE_AXE);
		RegisterItems.FastRender(POLARITE_BOOTS);
		RegisterItems.FastRender(POLARITE_LEGS);
		RegisterItems.FastRender(POLARITE_CHESTPLATE);
		RegisterItems.FastRender(POLARITE_HEAD);
		RegisterItems.FastRender(HEAD_CUTTER);
		RegisterItems.FastRender(FROZEN_HEART);
		RegisterItems.FastRender(ULTIMATE_VEHICLE);
		RegisterItems.FastRender(POLARITE_FRYING_PAN);
		RegisterItems.FastRender(CABER);
		RegisterItems.FastRender(SOUTH_SWORD);
		RegisterItems.FastRender(HEAVY_BOOTS);
		RegisterItems.FastRender(DIAMOND_SNIPER_BULLET);
		RegisterItems.FastRender(SIROJUS_PORTAL_FRAME);
		RegisterItems.FastRender(UNSTABLE_TNT);
		RegisterItems.FastRender(PYRITE_GEM);
		RegisterItems.FastRender(PYRITE_STAFF);
		RegisterItems.FastRender(PYRITE_SHIELD);
		RegisterItems.FastRender(FROZEN_GEM);
		RegisterItems.FastRender(ELEVATOR);
		RegisterItems.FastRender(TELEPORTERS_BINDER);
		RegisterItems.FastRender(TELEPORTER);
		RegisterItems.FastRender(IRON_SKULL);
		RegisterItems.FastRender(IRON_JETPACK);
		RegisterItems.FastRender(PINK_LEAVES);
		RegisterItems.FastRender(STABILIZER);
		RegisterItems.FastRender(GOLDEN_EXPLOSIVE_BELT);
		RegisterItems.FastRender(BLACK_HOLE);
		BLACK_HOLE_MISSILE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(BLACK_HOLE_MISSILE);
		//RegisterItems.FastRender(MACERATOR);
		RegisterItems.FastRender(PULSIOR_ALTAR);
		RegisterItems.FastRender(DIRECT_TIT);
		RegisterItems.FastRender(BLACKSTONE_BRICK);
		RegisterItems.FastRender(BASIC_LOOTBOX);
		
		RegisterItems.FastRender(RAINBOW_LAYER);
		RegisterItems.FastRender(LOVE_LAYER);
		RegisterItems.FastRender(SILVER_LAYER);
		RegisterItems.FastRender(DARK_LAYER);
		RegisterItems.FastRender(DARK2_LAYER);
		RegisterItems.FastRender(CLASSICAL_LAYER);
		RegisterItems.FastRender(END_LAYER);
		RegisterItems.FastRender(RB_LAYER);
		RegisterItems.FastRender(CSCAR_LAYER);
		RegisterItems.FastRender(OLD_LAYER);
		RegisterItems.FastRender(GALAXY_LAYER);
		RegisterItems.FastRender(BW_LAYER);
		RegisterItems.FastRender(UGLY_LAYER);
		RegisterItems.FastRender(ANIMATED_ORANGE_LAYER);
		RegisterItems.FastRender(ORANGE_PH_LAYER);
		RegisterItems.FastRender(FROZEN_LAYER);
		RegisterItems.FastRender(COPRAVEG_LAYER);
		RegisterItems.FastRender(TERRAFORMER);
		RegisterItems.FastRender(TECH_SHIELD);

		RegisterItems.FastRender(GOLDEN_BAT);
		GOLDEN_BAT.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		
		SNIPER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(SNIPER);
		MACHINE_PISTOL.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MACHINE_PISTOL);
		RegisterItems.FastRender(SNIPER_BULLET);
		BASIC_SHOTGUN.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(BASIC_SHOTGUN);
		FAMAS.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(FAMAS);
		DEAGLE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(DEAGLE);
		SCAR_H.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(SCAR_H);
		AA12.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(AA12);
		R870.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(R870);
		ROCKET_LAUNCHER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(ROCKET_LAUNCHER);
		AK47.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(AK47);
		AR15.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(AR15);
		CARBINE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(CARBINE);
		HUNTER_SHOTGUN.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(HUNTER_SHOTGUN);
		THOMPSON.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(THOMPSON);
		GRENADE_LAUNCHER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(GRENADE_LAUNCHER);
		FAFR.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(FAFR);
		GLOCK17.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(GLOCK17);
		KB800.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(KB800);
		HEAVY_FAMAS.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(HEAVY_FAMAS);
		CANARDIER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(CANARDIER);
		AERIAN_MONSTRUOSITY.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(AERIAN_MONSTRUOSITY);
		TR_SNIPER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(TR_SNIPER);
		
		RegisterItems.FastRender(TACTICAL_BOW);
		
		MEDIGUN.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MEDIGUN);
		RegisterItems.FastRender(BAZOOKB);
		
		//RegisterItems.FastRender(ENDER_SNIPER);
		
		ZIP22.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(ZIP22);
		MEDIC_S_THOMPSON.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MEDIC_S_THOMPSON);
		MEDIC_S_SHOTGUN.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MEDIC_S_SHOTGUN);
		NTT_SNIPER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(NTT_SNIPER);
		
		SQUID_LAUNCHER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(SQUID_LAUNCHER);

		SCS7.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(SCS7);
		RegisterItems.FastRender(SHOTBOW);
		
		CHICKEN_LAUNCHER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(CHICKEN_LAUNCHER);
		ANTIVEHICLE_RL.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(ANTIVEHICLE_RL);
		MANJTEMOR.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MANJTEMOR);
		BLACK_HOLE_LAUNCHER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(BLACK_HOLE_LAUNCHER);
		MINIGUN.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(MINIGUN);
		REVOLVER.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(REVOLVER);
		
		RegisterItems.FastRender(NUCLEAR_ROCKET_LAUNCHER);
		
		RegisterItems.FastRender(BASIC_SOUND_MUFFLER);
		RegisterItems.FastRender(BASIC_SCOPE);
		RegisterItems.FastRender(HALF_MUFFLER);
		RegisterItems.FastRender(BASIC_SIGHT);
		
		RegisterItems.FastRender(FLAME_THROWER);
		RegisterItems.FastRender(GRAPPLE);
		RegisterItems.FastRender(HARPOON);
		
		RegisterItems.FastRender(SMASHER);
		RegisterItems.FastRender(INFGAUNT);
		//RegisterItems.FastRender(FIRE_UPGRADE);
		RegisterItems.FastRender(BUCKSHOT);
		RegisterItems.FastRender(LITTLE_AMMO);
		RegisterItems.FastRender(LONG_AMMO);
		RegisterItems.FastRender(ROCKET);
		RegisterItems.FastRender(LOVE_BULLET);
		RegisterItems.FastRender(HUNTING_BULLET);
		RegisterItems.FastRender(DSWORD);
		RegisterItems.FastRender(COMMUNIST_DISK);
		RegisterItems.FastRender(KURAYUM_WOLF_BOOTS);
		RegisterItems.FastRender(KURAYUM_WOLF_LEGS);
		RegisterItems.FastRender(KURAYUM_WOLF_CHEST);
		RegisterItems.FastRender(KURAYUM_WOLF_HEAD);
		RegisterItems.FastRender(DISENCHANTER);
		RegisterItems.FastRender(WOLF_ARMOR_CRAFTER);
		RegisterItems.FastRender(KURAYUM_HAMMER);
		RegisterItems.FastRender(TFOSIUM_HAMMER);
		RegisterItems.FastRender(IRON_HAMMER);
		RegisterItems.FastRender(DIAMOND_HAMMER);
		RegisterItems.FastRender(SILVER_HAMMER);
		RegisterItems.FastRender(DRONE_LAUNCHER);
		RegisterItems.FastRender(FRENCH_DISK);
		RegisterItems.FastRender(ISSOU_DISK);
		RegisterItems.FastRender(PLATYPUS_PAW);
		RegisterItems.FastRender(FRYING_PAN);
		RegisterItems.FastRender(SKELETON_MANAGER);
		RegisterItems.FastRender(COPRAVEG_KEY);
		RegisterItems.FastRender(CRAB_ARENA_KEY);
		RegisterItems.FastRender(ICE_ROD);
		RegisterItems.FastRender(FROZEN_SWORD);
		RegisterItems.FastRender(SLICER);
		RegisterItems.FastRender(CRUSHER);
		RegisterItems.FastRender(POLARITE_INGOT);
		RegisterItems.FastRender(GUIDED_ROCKET);
		RegisterItems.FastRender(BLACK_HOLE_FRYING_PAN);
		RegisterItems.FastRender(BOREALITE_INGOT);
		RegisterItems.FastRender(BOREALITE_BLOCK);
		RegisterItems.FastRender(BOREALITE_ORE);
		RegisterItems.FastRender(NIGHT_SHARD);
		RegisterItems.FastRender(WIND_LAYER);
		RegisterItems.FastRender(NETHER_LAYER);
		RegisterItems.FastRender(GOLD_LAYER);
		RegisterItems.FastRender(GOLD_PLATED_LAYER);
		
		RegisterItems.FastRender(FUGITIVE_BOOTS);
		RegisterItems.FastRender(WARRIOR_BOOTS);
		RegisterItems.FastRender(ATTACKER_BOOTS);
		RegisterItems.FastRender(SHIELD_BOOTS);
		RegisterItems.FastRender(TANK_BOOTS);
		RegisterItems.FastRender(AGGRESSIVE_BOOTS);
		
		RegisterItems.FastRender(FUGITIVE_LEGGINGS);
		RegisterItems.FastRender(WARRIOR_LEGGINGS);
		RegisterItems.FastRender(ATTACKER_LEGGINGS);
		RegisterItems.FastRender(SHIELD_LEGGINGS);
		RegisterItems.FastRender(TANK_LEGGINGS);
		RegisterItems.FastRender(AGGRESSIVE_LEGGINGS);
		
		RegisterItems.FastRender(FUGITIVE_CHESTPLATE);
		RegisterItems.FastRender(WARRIOR_CHESTPLATE);
		RegisterItems.FastRender(ATTACKER_CHESTPLATE);
		RegisterItems.FastRender(SHIELD_CHESTPLATE);
		RegisterItems.FastRender(TANK_CHESTPLATE);
		RegisterItems.FastRender(AGGRESSIVE_CHESTPLATE);
		
		RegisterItems.FastRender(FUGITIVE_HELMET);
		RegisterItems.FastRender(WARRIOR_HELMET);
		RegisterItems.FastRender(ATTACKER_HELMET);
		RegisterItems.FastRender(SHIELD_HELMET);
		RegisterItems.FastRender(TANK_HELMET);
		RegisterItems.FastRender(AGGRESSIVE_HELMET);
		
		RegisterItems.FastRender(THORIUM_LEGS);
		RegisterItems.FastRender(THORIUM_BOOTS);
		RegisterItems.FastRender(THORIUM_CHESTPLATE);
		RegisterItems.FastRender(THORIUM_HEAD);
		RegisterItems.FastRender(UBER_LEGS);
		RegisterItems.FastRender(UBER_BOOTS);
		RegisterItems.FastRender(UBER_CHESTPLATE);
		RegisterItems.FastRender(UBER_HEAD);
		
		RegisterItems.FastRender(JETPACK);
		RegisterItems.FastRender(VODKA);
		RegisterItems.FastRender(KEPI);
		
		RegisterItems.FastRender(COPRAVEG_RG);
		RegisterItems.FastRender(INSTRUC_BOOK);
		RegisterItems.FastRender(DENANIUM);
		RegisterItems.FastRender(EXPLODA);
		RegisterItems.FastRender(DIAMONA);
		
		RegisterItems.FastRender(SEA_SWORD);
		RegisterItems.FastRender(SKY_SWORD);
		RegisterItems.FastRender(EARTH_SWORD);
		
		RegisterItems.FastRender(METALURGIC_FURNACE);
		RegisterItems.FastRender(THERMORESISTANT_BUCKET);
		RegisterItems.FastRender(ALLOY_INGOT);
		RegisterItems.FastRender(BASIC_BLADE);
		RegisterItems.FastRender(BASIC_HANDLE);
		RegisterItems.FastRender(BASIC_GUARD);
		CUSTOM_SWORD.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(CUSTOM_SWORD);
		RegisterItems.FastRender(CASTER);
		RegisterItems.FastRender(DAMAGE_BLADE);
		RegisterItems.FastRender(OFFENSE_GUARD);
		RegisterItems.FastRender(DEFENCE_GUARD);
		RegisterItems.FastRender(HANDLE_STYLE_2);
		RegisterItems.FastRender(FROZEN_LEAVES);
		CUSTOM_PICKAXE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(CUSTOM_PICKAXE);
		RegisterItems.FastRender(PICKAXE_HANDLE);
		RegisterItems.FastRender(PICKAXE_PART);
		RegisterItems.FastRender(HAMMER_PART);
		RegisterItems.FastRender(BAM_BLADE);
		RegisterItems.FastRender(HAMMER_BLADE);
		RegisterItems.FastRender(FROZEN_GLASS);
		RegisterItems.FastRender(POULOPOP_ALTAR);
		RegisterItems.FastRender(SIROJUS_KEY);
		RegisterItems.FastRender(MOB_GENERATOR);
		RegisterItems.FastRender(GAS_MASK);
		RegisterItems.FastRender(DISGUISE_KIT);
		RegisterItems.FastRender(FPC);
		RegisterItems.FastRender(FRUIT_PUNCH);
		RegisterItems.FastRender(KURAYUM_JETPACK);
		RegisterItems.FastRender(NIGHTMARE_STAFF);
		RegisterItems.FastRender(CRAB_CLAW);
		RegisterItems.FastRender(BEAR_TRAP);
		RegisterItems.FastRender(LAUNCH_TRAP);
		RegisterItems.FastRender(MOSSY_BLACKSTONE);
		RegisterItems.FastRender(RADIOACTIVE_BLACKSTONE);
		RegisterItems.FastRender(THORIUM_FRAGMENT);
		RegisterItems.FastRender(SUPER_HUNTER_CORE);
		RegisterItems.FastRender(BANANA);
		RegisterItems.FastRender(METALIC_SHIELD);
		RegisterItems.FastRender(GOURDIN);
		RegisterItems.FastRender(HELL_FLOWER);
		RegisterItems.FastRender(BANANA_PORTAL_FRAME);
		RegisterItems.FastRender(CHAUVOPUT_ALTAR);
		RegisterItems.FastRender(BERSERSWORD);
		RegisterItems.FastRender(REBONDI_BOOTS);
		RegisterItems.FastRender(MAGMA_CORE);
		RegisterItems.FastRender(GHALI_AIRCUT);
		RegisterItems.FastRender(METALURGIC_FURNACE_UPGRADE);
		RegisterItems.FastRender(CREATIVE_METALURGIC_FURNACE_UPGRADE);
		RegisterItems.FastRender(BELUGA_SADDLE);
		RegisterItems.FastRender(BUFFED_BANANA);
		RegisterItems.FastRender(FESTIVE_JETPACK);
		RegisterItems.FastRender(FESTIVE_ROCKET);
		RegisterItems.FastRender(CHAINSAW);
		RegisterItems.FastRender(PANCARTE);
		PANCARTE.setTileEntityItemStackRenderer(GunSpecialRenderer.instance);
		RegisterItems.FastRender(TATANATOR);
		RegisterItems.FastRender(KURAYUM_FISHING_ROD);
		RegisterItems.FastRender(WHITE_KATANA);
		RegisterItems.FastRender(BLACK_KATANA);
		RegisterItems.FastRender(BLACK_KATANA_HOLDER_BLOCK);
		RegisterItems.FastRender(MOSQUITO_JAR);
		RegisterItems.FastRender(TUTU);
		RegisterItems.FastRender(JARATE);
		RegisterItems.FastRender(FORGING_HAMMER);
		RegisterItems.FastRender(LOG_CHERRY);
		RegisterItems.FastRender(PLANKS_CHERRY);
		RegisterItems.FastRender(SAPLING_CHERRY);
		RegisterItems.FastRender(CHERRY_SLAB);
		RegisterItems.FastRender(CHERRY_STAIRS);
		RegisterItems.FastRender(ORE_LOOTBOX);
		RegisterItems.FastRender(MOB_SOUL);

		RegisterItems.FastRender(BLUE_CRYSTAL_BLOCK);
		RegisterItems.FastRender(RED_CRYSTAL_BLOCK);
		RegisterItems.FastRender(GREEN_CRYSTAL_BLOCK);
		RegisterItems.FastRender(PURPLE_CRYSTAL_BLOCK);
		RegisterItems.FastRender(BLUE_CRYSTAL);
		RegisterItems.FastRender(RED_CRYSTAL);
		RegisterItems.FastRender(GREEN_CRYSTAL);
		RegisterItems.FastRender(PURPLE_CRYSTAL);
		RegisterItems.FastRender(SILVER_CRYSTAL);
		RegisterItems.FastRender(GRASSY_STONE);
		RegisterItems.FastRender(GLOWBERRY_BUSH);
		RegisterItems.FastRender(GLOWBERRY);
		RegisterItems.FastRender(BLOCK_RSP);
		RegisterItems.FastRender(BURNT_SLAB);
		RegisterItems.FastRender(BURNT_STAIRS);
		RegisterItems.FastRender(PLANKS_BURNT);
		RegisterItems.FastRender(WEIRD_STONE);
		RegisterItems.FastRender(CANDLE);
		RegisterItems.FastRender(MYSTIC_CIRCLE);
		RegisterItems.FastRender(CLOTH);
		RegisterItems.FastRender(SACRIFICE_DAGGER);
		RegisterItems.FastRender(MAGMATIC_SOIL);
		RegisterItems.FastRender(ZDEUCH_HAMMER);
		RegisterItems.FastRender(ETERNAL_PUNISHMENT);
		RegisterItems.FastRender(DEBUFF_SERINGE);
		RegisterItems.FastRender(AIR_BALLOON);
		
		RegisterItems.registerColor(THERMORESISTANT_BUCKET, ALLOY_INGOT, BASIC_BLADE, BASIC_HANDLE, BASIC_GUARD, DAMAGE_BLADE
				, OFFENSE_GUARD, DEFENCE_GUARD, HANDLE_STYLE_2, PICKAXE_HANDLE, PICKAXE_PART, HAMMER_PART, BAM_BLADE, HAMMER_BLADE
				, SUPER_HUNTER_CORE, METALIC_SHIELD, FUGITIVE_BOOTS, WARRIOR_BOOTS, ATTACKER_BOOTS, SHIELD_BOOTS, TANK_BOOTS, AGGRESSIVE_BOOTS
				, FUGITIVE_LEGGINGS, WARRIOR_LEGGINGS, ATTACKER_LEGGINGS, SHIELD_LEGGINGS, TANK_LEGGINGS, AGGRESSIVE_LEGGINGS
				, FUGITIVE_CHESTPLATE, WARRIOR_CHESTPLATE, ATTACKER_CHESTPLATE, SHIELD_CHESTPLATE, TANK_CHESTPLATE, AGGRESSIVE_CHESTPLATE
				, FUGITIVE_HELMET, WARRIOR_HELMET, ATTACKER_HELMET, SHIELD_HELMET, TANK_HELMET, AGGRESSIVE_HELMET, GOURDIN, BERSERSWORD, ZDEUCH_HAMMER);
		
		RegisterItems.registerColorBlock(RegisterBlocks.GREEN_LAVA);
		RegisterItems.registerColorBlock(RegisterBlocks.WEIRD_WATER);
		/*for(Item item : RegisterBlocks.items)
		{
			RegisterItems.FastRenderCopies(item);
		}*/
	}
	
	public static void FastRender(Item item)
	{
		RegisterItems.FastRender(item, 0);
	}
	
	public static void FastRender(Item item, int meta)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}
	
	public static void FastRenderCopies(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(new ResourceLocation("minecraft", "bedrock"), "inventory"));
	}
	
	public static void createTools(String ingotName, ToolMaterial material, ArmorMaterial armorMaterial, CreativeTabs tab, RegistryEvent.Register<Item> items)
	{
		Item INGOT = new Item().setRegistryName(ingotName + "_ingot").setUnlocalizedName(ingotName + "_ingot").setCreativeTab(tab);
		Item SWORD = new ItemSword(material).setRegistryName(ingotName + "_sword").setUnlocalizedName(ingotName + "_sword").setCreativeTab(tab);
		Item AXE = new ItemMAxe(material).setRegistryName(ingotName + "_axe").setUnlocalizedName(ingotName + "_axe").setCreativeTab(tab);
		Item SHOVEL = new ItemSpade(material).setRegistryName(ingotName + "_shovel").setUnlocalizedName(ingotName + "_shovel").setCreativeTab(tab);
		Item PICKAXE = new ItemMPickaxe(material).setRegistryName(ingotName + "_pickaxe").setUnlocalizedName(ingotName + "_pickaxe").setCreativeTab(tab);
		Item HOE = new ItemHoe(material).setRegistryName(ingotName + "_hoe").setUnlocalizedName(ingotName + "_hoe").setCreativeTab(tab);
		
		Item HELMET = new ItemArmor(armorMaterial, 0, EntityEquipmentSlot.HEAD).setRegistryName(ingotName + "_helmet").setUnlocalizedName(ingotName + "_helmet").setCreativeTab(tab);
		Item CHESTPLATE = new ItemArmor(armorMaterial, 0, EntityEquipmentSlot.CHEST).setRegistryName(ingotName + "_chestplate").setUnlocalizedName(ingotName + "_chestplate").setCreativeTab(tab);
		Item LEGGINGS = new ItemArmor(armorMaterial, 0, EntityEquipmentSlot.LEGS).setRegistryName(ingotName + "_leggings").setUnlocalizedName(ingotName + "_leggings").setCreativeTab(tab);
		Item BOOTS = new ItemArmor(armorMaterial, 0, EntityEquipmentSlot.FEET).setRegistryName(ingotName + "_boots").setUnlocalizedName(ingotName + "_boots").setCreativeTab(tab);
		
		items.getRegistry().registerAll(INGOT, SWORD, AXE, SHOVEL, PICKAXE, HOE,HELMET,CHESTPLATE,LEGGINGS,BOOTS);
	}
	
	public static void createTools(String ingotName,ToolMaterial material, CreativeTabs tab, RegistryEvent.Register<Item> items)
	{
		Item INGOT = new Item().setRegistryName(ingotName + "_ingot").setUnlocalizedName(ingotName + "_ingot").setCreativeTab(tab);
		Item SWORD = new ItemSword(material).setRegistryName(ingotName + "_sword").setUnlocalizedName(ingotName + "_sword").setCreativeTab(tab);
		Item AXE = new ItemMAxe(material).setRegistryName(ingotName + "_axe").setUnlocalizedName(ingotName + "_axe").setCreativeTab(tab);
		Item SHOVEL = new ItemSpade(material).setRegistryName(ingotName + "_shovel").setUnlocalizedName(ingotName + "_shovel").setCreativeTab(tab);
		Item PICKAXE = new ItemMPickaxe(material).setRegistryName(ingotName + "_pickaxe").setUnlocalizedName(ingotName + "_pickaxe").setCreativeTab(tab);
		Item HOE = new ItemHoe(material).setRegistryName(ingotName + "_hoe").setUnlocalizedName(ingotName + "_hoe").setCreativeTab(tab);
		
		items.getRegistry().registerAll(INGOT, SWORD, AXE, SHOVEL, PICKAXE, HOE);
	}
	
	public static void createWolfArmor(String ingotName, ArmorMaterial material, CreativeTabs tab, RegistryEvent.Register<Item> items)
	{
		Item BOOTS = new ItemWolfArmor(material, 0, EntityEquipmentSlot.FEET).setRegistryName(ingotName + "_wolf_boots").setUnlocalizedName(ingotName + "_wolf_boots").setCreativeTab(tab);
		Item LEGS = new ItemWolfArmor(material, 0, EntityEquipmentSlot.LEGS).setRegistryName(ingotName + "_wolf_leggings").setUnlocalizedName(ingotName + "_wolf_leggings").setCreativeTab(tab);
		Item CHEST = new ItemWolfArmor(material, 0, EntityEquipmentSlot.CHEST).setRegistryName(ingotName + "_wolf_chestplate").setUnlocalizedName(ingotName + "_wolf_chestplate").setCreativeTab(tab);
		Item HEAD = new ItemWolfArmor(material, 0, EntityEquipmentSlot.HEAD).setRegistryName(ingotName + "_wolf_helmet").setUnlocalizedName(ingotName + "_wolf_helmet").setCreativeTab(tab);
		
		items.getRegistry().registerAll(BOOTS, LEGS, CHEST, HEAD);
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderWolfArmor(String ingotName)
	{
		Item i1 = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_wolf_boots"));
		RegisterItems.FastRender(i1);
		Item i2 = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_wolf_leggings"));
		RegisterItems.FastRender(i2);
		Item i3 = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_wolf_chestplate"));
		RegisterItems.FastRender(i3);
		Item i4 = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_wolf_helmet"));
		RegisterItems.FastRender(i4);
	}
	
	@SideOnly(Side.CLIENT)
	public static void createRenderTools(String ingotName)
	{
		Item ingot = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_ingot"));
		RegisterItems.FastRender(ingot);
		Item sword = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_sword"));
		RegisterItems.FastRender(sword);
		Item axe = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_axe"));
		RegisterItems.FastRender(axe);
		Item shovel = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_shovel"));
		RegisterItems.FastRender(shovel);
		Item pickaxe = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_pickaxe"));
		RegisterItems.FastRender(pickaxe);
		Item hoe = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_hoe"));
		RegisterItems.FastRender(hoe);
		Item HELMET = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_helmet"));
		RegisterItems.FastRender(HELMET);
		Item CHESTPLATE = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_chestplate"));
		RegisterItems.FastRender(CHESTPLATE);
		Item LEGGINGS = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_leggings"));
		RegisterItems.FastRender(LEGGINGS);
		Item BOOTS = Item.REGISTRY.getObject(new ResourceLocation(Mathsmod.MODID, ingotName + "_boots"));
		RegisterItems.FastRender(BOOTS);
	}
	
	public static void createKurayumTools(String ingotName,ToolMaterial material, CreativeTabs tab, RegistryEvent.Register<Item> items)
	{
		Item INGOT = new Item().setRegistryName(ingotName + "_ingot").setUnlocalizedName(ingotName + "_ingot").setCreativeTab(tab);
		Item SHOVEL = new ItemSpade(material).setRegistryName(ingotName + "_shovel").setUnlocalizedName(ingotName + "_shovel").setCreativeTab(tab);
		Item PICKAXE = new ItemMPickaxe(material).setRegistryName(ingotName + "_pickaxe").setUnlocalizedName(ingotName + "_pickaxe").setCreativeTab(tab);
		Item HOE = new ItemHoe(material).setRegistryName(ingotName + "_hoe").setUnlocalizedName(ingotName + "_hoe").setCreativeTab(tab);
		
		items.getRegistry().registerAll(INGOT, SHOVEL, PICKAXE, HOE);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerColor(Item... itemsIn)
	{
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ((ICustomColor) stack.getItem()).getColorMultiplier(stack, tintIndex);
            }
        }, itemsIn);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerColorBlock(Block... blockIn)
	{
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) 
			{
				if(worldIn.getBlockState(pos).getBlock() instanceof ICustomColor)
				{
					ICustomColor color = (ICustomColor) worldIn.getBlockState(pos).getBlock();
					return color.getColorMultiplier(state, worldIn, pos, tintIndex);
				}
				return -1;
			}
		}, blockIn);
	}
	
	public static final EnumRarity COMMUNIST = EnumHelper.addRarity("communist", TextFormatting.RED, "communist");
	public static final EnumRarity FRENCH = EnumHelper.addRarity("french", TextFormatting.DARK_BLUE, "french");
}
