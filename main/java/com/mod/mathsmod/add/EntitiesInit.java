package com.mod.mathsmod.add;

import java.awt.Color;
import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.*;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.VillagerProfessions.professionEnchanter;
import com.mod.mathsmod.add.Entity.Entity.VillagerProfessions.professionFrozen;
import com.mod.mathsmod.add.Entity.Entity.objects.*;
import com.mod.mathsmod.add.Entity.Entity.objects.magic.EntityMagicEnderman;
import com.mod.mathsmod.add.Entity.Entity.objects.magic.EntityReincarnedPlayer;
import com.mod.mathsmod.add.Entity.Models.ModelNightGolem;
import com.mod.mathsmod.add.Entity.Models.ModelPoulopop;
import com.mod.mathsmod.add.Entity.Models.ModelWoodGolem;
import com.mod.mathsmod.add.Entity.Rendermobs.*;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitiesInit {

	private static int mobID = 0;
	private static final EnumCreatureType HUNTER = EnumHelper.addCreatureType("hunter", EntityHunter.class, 4, Material.AIR, false, false);
	//private static final EnumCreatureType WATER_MONSTER = EnumHelper.addCreatureType("water_monster", AbstractWaterMonster.class, 10, Material.WATER, false, false);

	public static VillagerProfession ENCHANTER = new professionEnchanter();
	public static VillagerProfession FROZEN = new professionFrozen();
	
	public static void registerEntities()
	{
		FastEntity("XGolem", EntityXGolem.class, mobID++, 40);
		FastEntity("enderman", EntityMagicEnderman.class, mobID++, 30);
		FastEntity("plane", EntityPlane.class, mobID++, 120);
		FastEntity("glider", EntityGlider.class, mobID++, 30);
		FastEntity("absorbed_block", EntityAbsorbedBlock.class, mobID++, 30);
		FastEntity("obus", EntityObus.class, mobID++, 40);
		FastEntity("drilling_machine", EntityDrillingMachine.class, mobID++, 45);
		FastEntity("electric_boat", EntityElectricBoat.class, mobID++, 45);
		FastEntity("fragmentation_grenade", EntityFragmentationGrenade.class, mobID++, 20);
		FastEntity("destroyer", EntityDestroyer.class, mobID++, 100);
		FastEntity("wither_bowman", EntityWitherBowman.class, mobID++, 40, new Color(30, 30, 30).getRGB(), 0);
		//EntityRegistry.addSpawn(EntityWitherBowman.class, 7, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
		FastEntity("u_missile", EntityUMissile.class, mobID++, 110);
		FastEntity("mate_golem", EntityMateGolem.class, mobID++, 50);
		FastEntity("hunter", EntityHunter.class, mobID++, 60, new Color(30, 30, 30).getRGB(), new Color(255, 255, 255).getRGB());
		addSpawnAllBiomesExept(EntityHunter.class, 1, 3, 4, HUNTER, Biomes.SKY, Biomes.HELL);
		FastEntity("rocket", EntityRocket.class, mobID++, 50);
		FastEntity("shark", EntityShark.class, mobID++, 90, new Color(200, 200, 255).getRGB(), new Color(0, 0, 255).getRGB());
		FastEntity("tank", EntityTank.class, mobID++, 90);
		FastEntity("tank_rocket", EntityTankRocket.class, mobID++, 50);
		FastEntity("target_drone", EntityTargetDrone.class, mobID++, 60);
		FastEntity("dead_hunter", EntityDeadHunter.class, mobID++, 70, new Color(0, 100, 0).getRGB(), new Color(0, 0, 0).getRGB());
		FastEntity("blal", EntityBlal.class, mobID++, 40, new Color(100, 100, 100).getRGB(), new Color(200, 0, 0).getRGB());
		FastEntity("gaz_grenade", EntityGazGrenade.class, mobID++, 30);
		FastEntity("grenade", EntityBasicGrenade.class, mobID++, 30);
		FastEntity("sticky_grenade", EntityStickyGrenade.class, mobID++, 30);
		FastEntity("titank", EntityTitank.class, mobID++, 90);
		FastEntity("super_hunter", EntitySuperHunter.class, mobID++, 60);
		FastEntity("harpoon", EntityHarpoon.class, mobID++, 30);
		FastEntity("nuclear_rocket", EntityNuclearRocket.class, mobID++, 30);
		FastEntity("super_hunter_death_orb", EntitySuperHunterDeathOrb.class, mobID++, 50);
		FastEntity("nuclear_plane", EntityNuclearPlane.class, mobID++, 30);
		FastEntity("turret", EntityTurret.class, mobID++, 40);
		FastEntity("auto_turret", EntityTurretAuto.class, mobID++, 40);
		FastEntity("a_missile", EntityAMissile.class, mobID++, 50);
		FastEntity("nuclear_missile", EntityNuclearMissile.class, mobID++, 50);
		FastEntity("reincarned_player", EntityReincarnedPlayer.class, mobID++, 70);
		FastEntity("megalodon", EntityMegalodon.class, mobID++, 70);
		FastEntity("devil_hand", EntityDevilHand.class, mobID++, 70);
		FastEntity("nether_hunter", EntityNetherHunter.class, mobID++, 60, new Color(255, 100, 100).getRGB(), new Color(200, 0, 0).getRGB());
		EntityRegistry.addSpawn(EntityNetherHunter.class, 40, 1, 1, EnumCreatureType.MONSTER, Biomes.HELL);
		FastEntity("helicopter", EntityHelicopter.class, mobID++, 120);
		FastEntity("b_missile", EntityBMissile.class, mobID++, 50);
		FastEntity("t_missile", EntityTMissile.class, mobID++, 50);
		FastEntity("repulsive_grenade", EntityRepulsiveGrenade.class, mobID++, 30);
		FastEntity("platypus", EntityPlatypus.class, mobID++, 30, new Color(135, 55, 2).getRGB(), new Color(0, 0, 0).getRGB());
		FastEntity("camera_drone", EntityCameraDrone.class, mobID++, 200);
		FastEntity("skeledon", EntitySkeledon.class, mobID++, 80);
		FastEntity("improved_arrow", EntityImprovedArrow.class, mobID++, 40);
		FastEntity("remote_grenade", EntityRemoteGrenade.class, mobID++, 30);
		FastEntity("bot", EntityBot.class, mobID++, 50);
		FastEntity("repulsive_rocket", EntityRepulsiveRocket.class, mobID++, 30);
		FastEntity("crab", EntityCrab.class, mobID++, 30, new Color(255, 00, 0).getRGB(), new Color(100, 0, 0).getRGB());
		EntityRegistry.addSpawn(EntityCrab.class, 40, 1, 1, EnumCreatureType.CREATURE, Biomes.BEACH);
		FastEntity("mythical_item", EntityMythicalItem.class, mobID++, 30);
		FastEntity("copracreeper", EntityCCreeper.class, mobID++, 30, new Color(140, 140, 140).getRGB(), new Color(0, 0, 0).getRGB());
		FastEntity("copracrab", EntityCopracrab.class, mobID++, 30, new Color(255, 00, 0).getRGB(), new Color(70, 0, 0).getRGB());
		FastEntity("super_nuclear_missile", EntitySuperNuclearMissile.class, mobID++, 50);
		FastEntity("frost_blal", EntityFrostBlal.class, mobID++, 30, new Color(0, 240, 255).getRGB(), new Color(0, 0, 255).getRGB());
		FastEntity("boss_crab", EntitySBossCrab.class, mobID++, 60);
		FastEntity("sirojus_blaze", EntitySBlaze.class, mobID++, 40, new Color(200, 200, 255).getRGB(), new Color(200, 200, 255).getRGB());
		FastEntity("sirojus_blaze_fireball", EntitySijorusSFireball.class, mobID++, 20);
		FastEntity("frozen_skeleton", EntityFrostSkeleton.class, mobID++, 20, new Color(100, 100, 100).getRGB(), new Color(200, 200, 255).getRGB());
		FastEntity("frozen_bowman", EntityFrozenBowman.class, mobID++, 20, new Color(100, 100, 100).getRGB(), new Color(200, 200, 255).getRGB());
		FastEntity("golden_statue", EntityGoldDeathStatue.class, mobID++, 20);
		FastEntity("throwable_stone", EntityThrowableStone.class, mobID++, 20);
		FastEntity("rocket_squid", EntityRocketSquid.class, mobID++, 20);
		FastEntity("zombie_brute", EntityZomboss.class, mobID++, 40);
		FastEntity("ultimate_vehicle", EntityDeltaCarBoat.class, mobID++, 30);
		FastEntity("dev_hand", EntityDevHand.class, mobID++, 50);
		FastEntity("pyromancer", EntityPyromancer.class, mobID++, 30);
		FastEntity("black_hole", EntityBlackHole.class, mobID++, 120);
		FastEntity("pulsior", EntityDBoss.class, mobID++, 50);
		FastEntity("fast_black_hole", EntityFBlackHole.class, mobID++, 40);
		FastEntity("pulther", EntityDBossWitherPhase.class, mobID++, 50);
		FastEntity("pulther_skull", EntityPultherSkull.class, mobID++, 30);
		FastEntity("pigsior", EntityDBossPigPhase.class, mobID++, 30);
		FastEntity("pulgon", EntityDBossDragonPhase.class, mobID++, 120);
		FastEntity("black_hole_missile", EntityBlackHoleMissile.class, mobID++, 50);
		FastEntity("pulsior_portal", EntityPulsiorPortal.class, mobID++, 30);
		FastEntity("steroide_zombie", EntitySteroideZombie.class, mobID++, 40, new Color(100, 0, 0).getRGB(), new Color(150, 0, 0).getRGB());
		//addSpawnAllBiomesExept(EntitySteroideZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.SKY, Biomes.HELL);
		FastEntity("h_zombie", EntityHZombie.class, mobID++, 40, new Color(100, 0, 0).getRGB(), new Color(0, 0, 0).getRGB());
		//addSpawnAllBiomesExept(EntityHZombie.class, 30, 3, 6, EnumCreatureType.MONSTER, Biomes.SKY, Biomes.HELL);
		FastEntity("poulopop", EntityPoulopop.class, mobID++, 40);
		FastEntity("mini_sun", EntityPoulopopMiniSun.class, mobID++, 40);
		FastEntity("emmanuel_macron", EntityEmmanuelMacron.class, mobID++, 40);
		FastEntity("night_golem", EntityNightGolem.class, mobID++, 30);
		FastEntity("poulopop_root", EntityPoulopopRoot.class, mobID++, 40);
		FastEntity("cracker", EntityCrackerMiniboss.class, mobID++, 30);
		FastEntity("frec", EntityFrec.class, mobID++, 30, new Color(0, 0, 0).getRGB(), new Color(70, 70, 70).getRGB());
		FastEntity("fruit_punch_clown", EntityFruitPunchClown.class, mobID++, 40);
		FastEntity("launch_trap", EntityTrapHarpoon.class, mobID++, 30);
		FastEntity("ragdoll", EntityRagdoll.class, mobID++, 30);
		FastEntity("stupid_skeleton", EntityStupidSkeleton.class, mobID++, 40);
		FastEntity("steroid_skeleton", EntitySteroideSkeleton.class, mobID++, 30);
		FastEntity("steroid_creeper", EntitySteroideCreeper.class, mobID++, 30);
		FastEntity("blob", EntityBlob.class, mobID++, 30);
		FastEntity("friend", EntityHappyMazeFoe.class, mobID++, 50, 0, 0);
		FastEntity("bananman", EntityBananman.class, mobID++, 30, new Color(200, 200, 0).getRGB(), new Color(70, 70, 70).getRGB());
		FastEntity("chauvoput", EntityNChauvoput.class, mobID++, 40);
		FastEntity("chauvoput_magma", EntityChauvoputBlock.class, mobID++, 30);
		FastEntity("chauvoput_eruption", EntityChauvoputEruptionBlock.class, mobID++, 30);
		FastEntity("banana_strongman", EntityBananaStrongman.class, mobID++, 40, new Color(200, 200, 0).getRGB(), new Color(70, 70, 70).getRGB());
		FastEntity("mininana", EntityMininana.class, mobID++, 30, new Color(200, 200, 0).getRGB(), new Color(70, 70, 70).getRGB());
		FastEntity("mosquito", EntityMosquito.class, mobID++, 30, new Color(50, 0, 0).getRGB(), new Color(70, 70, 70).getRGB());
		EntityRegistry.addSpawn(EntityMosquito.class, 10, 10, 20, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
		EntityRegistry.addSpawn(EntityMosquito.class, 10, 10, 20, EnumCreatureType.MONSTER, Biomes.MUTATED_SWAMPLAND);
		FastEntity("beluga", EntityBeluga.class, mobID++, 30, new Color(50, 50, 50).getRGB(), new Color(200, 200, 200).getRGB());
		FastEntity("pub", EntityPub.class, mobID++, 50);
		FastEntity("pub_projectile", EntityPubProj.class, mobID++, 70);
		FastEntity("festive_rocket", EntityFestiveRocket.class, mobID++, 30);
		FastEntity("terraformer", EntityTerraformer.class, mobID++, 30);
		FastEntity("banana_creeper", EntityBananaCreeper.class, mobID++, 30);
		FastEntity("moustiquator", EntityMoustiquator.class, mobID++, 30);
		FastEntity("steroide_blal", EntitySteroideBlal.class, mobID++, 30);
		FastEntity("steroide_night_golem", EntitySteroidNightGolem.class, mobID++, 30);
		FastEntity("jarate", EntityJarate.class, mobID++, 30);
		FastEntity("black_sphere", EntityBlackSphere.class, mobID++, 100);
		FastEntity("icy_javelin", EntityIcyJavelin.class, mobID++, 100);
		FastEntity("speeding_boat", EntitySpeedingBoat.class, mobID++, 40);
		addSpawnAllWorldBiomesWithZombies(EntityHZombie.class, 20, 3, 4, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntitySteroideZombie.class, 6, 1, 1, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntitySteroideSkeleton.class, 6, 1, 1, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntityStupidSkeleton.class, 15, 1, 1, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntitySteroideCreeper.class, 6, 1, 2, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntityNightGolem.class, 10, 1, 1, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntityFrec.class, 10, 1, 1, EnumCreatureType.MONSTER);
		addSpawnAllWorldBiomesWithZombies(EntitySteroidNightGolem.class, 5, 1, 1, EnumCreatureType.MONSTER);
		FastEntity("wood_golem", EntityWoodGolem.class, mobID++, 40);
		FastEntity("cultist", EntityCultist.class, mobID++, 40);
		FastEntity("cultist_priest", EntityCultistPriest.class, mobID++, 40);
		FastEntity("air_balloon", EntityAirBalloon.class, mobID++, 150);
	}

	@SideOnly(Side.CLIENT)
	public static void registerEntitiesRenders()
	{
		RenderManager r = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityXGolem.class, new RenderXGolem(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlane.class, new RenderPlane(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlider.class, new RenderGlider(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityObus.class, new RenderObus(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityAbsorbedBlock.class, new RenderAbsorbedBlock(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(r, RegisterItems.FRAG_GRENADE, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDrillingMachine.class, new RenderDrillingMachine(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDestroyer.class, new RenderDestroyer(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityElectricBoat.class, new RenderElectricBoat(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityMateGolem.class, new RenderMateGolem(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityHunter.class, new RenderHunter(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, new RenderShark(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTank.class, new RenderTank(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTankRocket.class, new RenderTankRocket(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTargetDrone.class, new RenderDrone(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDeadHunter.class, new RenderDeadHunter(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlal.class, new RenderBlal(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityGazGrenade.class, new RenderGrenade(r, RegisterItems.GAZ_GRENADE, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBasicGrenade.class, new RenderGrenade(r, RegisterItems.GRENADE, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityStickyGrenade.class, new RenderGrenade(r, RegisterItems.STICKY_GRENADE, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTitank.class, new RenderTitank(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySuperHunter.class, new RenderSuperHunter(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityHarpoon.class, new RenderGrappleHarpoon(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityNuclearRocket.class, new RenderNuclearRocket(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySuperHunterDeathOrb.class, new RenderSuperHunterDeathOrb(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityNuclearPlane.class, new RenderNuclearPlane(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret.class, new RenderTurret(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurretAuto.class, new RenderTurret(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityAMissile.class, new RenderAMissile(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityReincarnedPlayer.class, new RenderReincarnedPlayer(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityMegalodon.class, new RenderMegalodon(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDevilHand.class, new RenderDevilHand(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityNetherHunter.class, new RenderNetherHunter(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityHelicopter.class, new RenderHelicopter(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityRepulsiveGrenade.class, new RenderGrenade(r, RegisterItems.REPULSIVE_GRENADE, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlatypus.class, new RenderPlatypus(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityCameraDrone.class, new RenderCameraDrone(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkeledon.class, new RenderSkeledon(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityRemoteGrenade.class, new RenderSnowball(r, Item.getItemFromBlock(Blocks.TNT), Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBot.class, new RenderBot(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrab.class, new RenderCrab(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityMythicalItem.class, new RenderMythicalItem(r, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCCreeper.class, new RenderCopraCreeper(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityCopracrab.class, new RenderCopracrab(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostBlal.class, new RenderFrostBlal(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySBossCrab.class, new RenderBossCrab(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySBlaze.class, new RenderSBlaze(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostSkeleton.class, new RenderFrozenSkeleton(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityGoldDeathStatue.class, new RenderGoldenDeathStatue(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityRocketSquid.class, new RenderSquidRocket(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityZomboss.class, new RenderZomboss(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDeltaCarBoat.class, new RenderDeltaCarBoat(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDevHand.class, new RenderDevilHand(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowableStone.class, new RenderThrowableStone(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityImprovedArrow.class, new RenderImprovedArrow(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPyromancer.class, new RenderPyromancer(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, new RenderBlackHole(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDBoss.class, new RenderDBoss(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDBossWitherPhase.class, new RenderPulther(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPultherSkull.class, new RenderPultherSkull(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDBossPigPhase.class, new RenderPigsior(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityDBossDragonPhase.class, new RenderPulgon(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPulsiorPortal.class, new RenderPulsiorPortal(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySteroideZombie.class, new RenderSteroideZombie(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityHZombie.class, new RenderHZombie(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoulopop.class, new RenderPoulopop(r, new ModelPoulopop()));
		RenderingRegistry.registerEntityRenderingHandler(EntityEmmanuelMacron.class, new RenderMauch(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityNightGolem.class, new RenderNightGolem(r, new ModelNightGolem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoulopopMiniSun.class, new RenderPoulopopMiniSun(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPoulopopRoot.class, new RenderPoulopopRoot(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrackerMiniboss.class, new RenderCracker(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrec.class, new RenderFrec(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityFruitPunchClown.class, new RenderEntityFpc(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTrapHarpoon.class, new RenderTrapHarpoon(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityRagdoll.class, new RenderRagdoll(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySteroideCreeper.class, new RenderSteroidCreeper(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySteroideSkeleton.class, new RenderSteroideSkeleton(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlob.class, new RenderBlob(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityHappyMazeFoe.class, new RenderDeltaCarBoat(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBananman.class, new RenderBananman(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityNChauvoput.class, new RenderChauvoput(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityChauvoputBlock.class, new RenderChauvoputFireball(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityChauvoputEruptionBlock.class, new RenderChauvoputFireball(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBananaStrongman.class, new RenderBananaStrongman(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityMininana.class, new RenderMininana(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeluga.class, new RenderBeluga(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPub.class, new RenderPub(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityPubProj.class, new RenderPubProj(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerraformer.class, new RenderTerraformer(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityBananaCreeper.class, new RenderBananaCreeper(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityMoustiquator.class, new RenderMoustiquator(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySteroideBlal.class, new RenderSteroideBlal(r));
		RenderingRegistry.registerEntityRenderingHandler(EntitySteroidNightGolem.class, new RenderNightGolem(r, new ModelNightGolem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityJarate.class, new RenderSnowball(r, RegisterItems.JARATE, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackSphere.class, new RenderBlackSphere(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityIcyJavelin.class, new RenderIcyJavelin(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodGolem.class, new RenderWoodGolem(r, new ModelWoodGolem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityCultist.class, new RenderCultist(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityCultistPriest.class, new RenderCultistPriest(r));
		RenderingRegistry.registerEntityRenderingHandler(EntityAirBalloon.class, new RenderAirBalloon(r));
	}
	
	public static void addSpawnAllBiomesExept(Class<? extends EntityLiving> entityClass, int prob, int min, int max, EnumCreatureType type, Biome... biomes)
	{
		List<Biome> list = Lists.newArrayList();
		for(Biome biome : Biome.REGISTRY)
		{
			list.add(biome);
		}
		for(Biome bio : biomes)
		{
			list.remove(bio);
		}
		
		for(Biome b : list)
		{
			EntityRegistry.addSpawn(entityClass, prob, min, max, type, b);
		}
		
	}
	
	public static void addSpawnAllWorldBiomes(Class<? extends EntityLiving> entityClass, int prob, int min, int max, EnumCreatureType type)
	{
		List<Biome> list = Lists.newArrayList();
		for(Biome biome : Biome.REGISTRY)
		{
			if(biome.getBaseHeight() > 0 && biome.getSpawnableList(EnumCreatureType.MONSTER).size() > 0)
			{
				EntityRegistry.addSpawn(entityClass, prob, min, max, type, biome);
			}
		}
	}
	
	public static void addSpawnAllWorldBiomesWithZombies(Class<? extends EntityLiving> entityClass, int prob, int min, int max, EnumCreatureType type)
	{
		List<Biome> list = Lists.newArrayList();
		for(Biome biome : Biome.REGISTRY)
		{
			boolean flag = false;
			
			for(SpawnListEntry spe : biome.getSpawnableList(EnumCreatureType.MONSTER))
			{
				if(spe.entityClass == EntityZombie.class)
				{
					flag = true;
				}
			}
			
			
			if(biome.getBaseHeight() > 0 && biome.getSpawnableList(EnumCreatureType.MONSTER).size() > 0 && flag)
			{
				EntityRegistry.addSpawn(entityClass, prob, min, max, type, biome);
			}
		}
	}
	
	public static void FastEntity(String idAndName , Class<? extends Entity> entityClass, int numberID, int tracking, int color, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Mathsmod.MODID, idAndName), entityClass, idAndName, numberID, Mathsmod.instance, tracking, 1, true, color, color2);
	}
	
	public static void FastEntity(String idAndName , Class<? extends Entity> entityClass, int numberID, int tracking)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Mathsmod.MODID, idAndName), entityClass, idAndName, numberID, Mathsmod.instance, tracking, 1, true);
	}
	
	public static void FastEntity(String idAndName , Class<? extends Entity> entityClass, int numberID, int tracking, int update, boolean vu)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Mathsmod.MODID, idAndName), entityClass, idAndName, numberID, Mathsmod.instance, tracking, update, vu);
	}

}
