package com.mod.mathsmod.add.event;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Nullable;
import javax.xml.transform.Source;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Enchantments;
import com.mod.mathsmod.add.EntitiesInit;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.MathsTriggers;
import com.mod.mathsmod.add.RegisterBiomes;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.Entity.Entity.EntityBananman;
import com.mod.mathsmod.add.Entity.Entity.EntityBeluga;
import com.mod.mathsmod.add.Entity.Entity.EntityBlal;
import com.mod.mathsmod.add.Entity.Entity.EntityDevilHand;
import com.mod.mathsmod.add.Entity.Entity.EntityFrec;
import com.mod.mathsmod.add.Entity.Entity.EntityFrostBlal;
import com.mod.mathsmod.add.Entity.Entity.EntityHZombie;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Entity.EntityMegalodon;
import com.mod.mathsmod.add.Entity.Entity.EntityMosquito;
import com.mod.mathsmod.add.Entity.Entity.EntityMoustiquator;
import com.mod.mathsmod.add.Entity.Entity.EntityNetherHunter;
import com.mod.mathsmod.add.Entity.Entity.EntityNightGolem;
import com.mod.mathsmod.add.Entity.Entity.EntityPlatypus;
import com.mod.mathsmod.add.Entity.Entity.EntityPyromancer;
import com.mod.mathsmod.add.Entity.Entity.EntityShark;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroidNightGolem;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroideBlal;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroideCreeper;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroideSkeleton;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroideZombie;
import com.mod.mathsmod.add.Entity.Entity.EntityStupidSkeleton;
import com.mod.mathsmod.add.Entity.Entity.EntitySuperHunter;
import com.mod.mathsmod.add.Entity.Entity.EntityWitherBowman;
import com.mod.mathsmod.add.Entity.Entity.EntityZomboss;
import com.mod.mathsmod.add.Entity.Entity.FrostResistance;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityEmmanuelMacron;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGoldDeathStatue;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRagdoll;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.World.MathsmodGeneration;
import com.mod.mathsmod.add.World.OresGen;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureCustom;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityFruitPunchClown;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityTomb;
import com.mod.mathsmod.add.capabilities.CapabilitiesManager;
import com.mod.mathsmod.add.capabilities.CapabilityContracts;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.contractsManager.ContractManagerServer;
import com.mod.mathsmod.add.event.MEvents.OnGunReload;
import com.mod.mathsmod.add.event.MEvents.onBlockImpactEvent;
import com.mod.mathsmod.add.event.MEvents.onImpactEvent;
import com.mod.mathsmod.add.event.MEvents.onShotEvent;
import com.mod.mathsmod.add.items.IModedFishingRod;
import com.mod.mathsmod.add.items.IReachWeapon;
import com.mod.mathsmod.add.items.ItemAbstractProtectionShield;
import com.mod.mathsmod.add.items.ItemAmmoBox;
import com.mod.mathsmod.add.items.ItemAssassinRapier;
import com.mod.mathsmod.add.items.ItemChargeShield;
import com.mod.mathsmod.add.items.ItemCommunistDisk;
import com.mod.mathsmod.add.items.ItemCustomSword;
import com.mod.mathsmod.add.items.ItemFrozenSword;
import com.mod.mathsmod.add.items.ItemInfinityGauntlet;
import com.mod.mathsmod.add.items.ItemMobSoul;
import com.mod.mathsmod.add.items.ItemRegenerativeSteak;
import com.mod.mathsmod.add.items.ItemSmasher;
import com.mod.mathsmod.add.items.ItemArmor.IExplosionProtection;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemFestiveJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemKurayumJetpack;
import com.mod.mathsmod.add.items.ItemArmor.wolf.ItemWolfArmor;
import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemBazookb;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemKB800;
import com.mod.mathsmod.add.items.guns.ItemShotGun;
import com.mod.mathsmod.add.items.metaltools.IShieldHit;
import com.mod.mathsmod.add.items.metaltools.ItemMetalicArmor;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.add.messages.MessageParticleEffects;
import com.mod.mathsmod.add.messages.MessageTempAnim;
import com.mod.mathsmod.add.messages.MessageTransform;
import com.mod.mathsmod.add.spell.EnumSpellReaction;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.add.spell.utils.SpellManager;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.server_Proxy;
import com.mod.mathsmod.utils.GenerationHelper;
import com.mod.mathsmod.utils.ItemStackUtils;
import com.mod.mathsmod.utils.KillsCounter;
import com.mod.mathsmod.utils.MDamageSource;
import com.mod.mathsmod.utils.PerfectExplosion;
import com.mod.mathsmod.utils.TwoIntAndString;
import com.mod.mathsmod.utils.playerNbtManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.oredict.OreDictionary;

public class WorldEvents {

	
	public static final Predicate<Entity> IS_ENTITYLIVING = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_ instanceof EntityLivingBase;
        }
    };
    
	public static final List<Item> smelts = Lists.newArrayList();
	public static final UUID ID = UUID.fromString("845DB27C-C724-495F-8C9F-4520B9A58B6B");
	public static Random rand = new Random();
	
	@SubscribeEvent
	public void onBlockBroken(BlockEvent.HarvestDropsEvent event)
	{
		if(event.getHarvester() != null)
		{
			if(event.getState().getBlock() == RegisterBlocks.PERMICE_STONE && event.getWorld().getBiome(event.getPos()) == RegisterBiomes.HIGH_MOUNTAINS)
			{
				if(new Random().nextInt(100) == 0)
				{
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(RegisterItems.FROZEN_GEM));
				}
			}
			if(this.Smelt(event.getHarvester()) || smelts.contains(event.getHarvester().getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem()))
			{
				int fortune = event.getFortuneLevel();
				
				Item item = event.getState().getBlock().getItemDropped(event.getState(), new Random(), fortune);
				
				event.getWorld().setBlockToAir(event.getPos());
				List<ItemStack> drops = event.getDrops();//event.getState().getBlock().getDrops(event.getWorld(), event.getPos(), event.getState(), fortune);
				//event.getDrops();
				
				for(int i = 0; i < drops.size(); ++i)
				{
					ItemStack stack = drops.get(i);
					if(FurnaceRecipes.instance().getSmeltingResult(stack).getCount() != 0)
					{
						ItemStack result = FurnaceRecipes.instance().getSmeltingResult(stack);
						if(result.getItem() instanceof ItemBlock)
						{
							result.setCount(1);
						}
						else
						{
							result.setCount(event.getState().getBlock().quantityDropped(new Random()) + (fortune / 3));
						}
						drops.set(i, result);
						return;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockDestroyed(BlockEvent.BreakEvent event)
	{

		if(event.getState().getBlock() == RegisterBlocks.FPC)
		{
			TileEntity te = event.getWorld().getTileEntity(event.getPos());
			if(te instanceof TileEntityFruitPunchClown && ((TileEntityFruitPunchClown) te).broken)
			{
				event.setCanceled(true);
			}
		}
	}
	
	private boolean Smelt(EntityPlayer player)
	{
		if(player.getHeldItemMainhand().getEnchantmentTagList() != null)
		{
			for (int i = 0; i < player.getHeldItemMainhand().getEnchantmentTagList().tagCount(); i++)
			{
				if(player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.SMELT))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	@SubscribeEvent
	public void onBlockImpact(onBlockImpactEvent event)
	{
	}
	
	@SubscribeEvent
	public void onCraft(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event)
	{
		if(event.crafting.getItem() == Item.getItemFromBlock(Blocks.FURNACE))
		{
			ContractManagerServer.addProgress(event.player, RegisterContracts.FIRST_STEP, 1);
		}
		
		if(event.crafting.getItem() == Item.getItemFromBlock(Blocks.WOOL))
		{
			ContractManagerServer.addProgress(event.player, RegisterContracts.TUTU, 1);
		}
	}
	
	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event)
	{
		if(event.getEntityPlayer().isDead || event.getEntityPlayer().getHealth() <= 0)
		{
			event.setCanceled(true);
		}
		
		if(event.getEntityPlayer() != null && event.getTarget() != null && event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof IReachWeapon)
		{
			IReachWeapon item = (IReachWeapon) event.getEntityPlayer().getHeldItemMainhand().getItem();
			if(item.bonusReach(event.getEntityPlayer(), event.getEntityPlayer().getHeldItemMainhand()) < 0)
			{
				if(event.getEntityPlayer().getDistance(event.getTarget()) > 3.0 + item.bonusReach(event.getEntityPlayer()))
				{
					event.setCanceled(true);
				}
			}
		}
		
		if(event.getEntityPlayer() != null && event.getEntityPlayer().getEntityData() != null && event.getEntityPlayer().getEntityData().hasKey("disintegrating_hand_touched_counter"))
		{
			event.getEntityPlayer().getEntityData().setInteger("disintegrating_hand_touched_counter", 0);
		}
		
		if(event.getEntityPlayer() != null && event.getTarget() != null && event.getEntityPlayer().getHeldItemMainhand().getItem() == RegisterItems.FPC)
		{
			event.getEntityPlayer().world.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY + 1, event.getEntityPlayer().posZ, MathsSounds.FRUIT_PUNCH, SoundCategory.AMBIENT, 1, 1);
		}
	}
	
	@SubscribeEvent
	public void onLootTableLoad(LootTableLoadEvent event)
	{
		if(event.getName() == LootTableList.CHESTS_END_CITY_TREASURE)
		{
			event.getTable().getPool("main").addEntry(new LootEntryItem(RegisterItems.HELICOPTER, 5, 1, new LootFunction[0], new LootCondition[0], "mathsmod:helico"));
			event.getTable().getPool("main").addEntry(new LootEntryItem(RegisterItems.PLANE, 3, 1, new LootFunction[0], new LootCondition[0], "mathsmod:plane"));
			event.getTable().getPool("main").addEntry(new LootEntryItem(RegisterItems.KURAYUM_ORE, 2, 1, new LootFunction[0], new LootCondition[0], "mathsmod:kurayum_ore"));
		}
	}
	
	@SubscribeEvent
	public void onItemUse(PlayerInteractEvent.RightClickItem event)
	{
		if(event.getEntityLiving() != null && event.getEntityLiving().getRidingEntity() != null)
		{
			if(event.getEntityLiving().getRidingEntity() instanceof IVehicle)
			{
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent event)
	{
		if(event.getEntityLiving() != null)
		{
			BlockPos pos = new BlockPos(event.getEntityLiving()).down();
			if(event.getEntityLiving().world.getBlockState(pos).getBlock() == RegisterBlocks.ELEVATOR)
			{
				for(int i = 1; i <= 20; ++i )
				{
					if(pos.getY() + i + 2 <= 255)
					{
						World worldIn = event.getEntityLiving().world;
						if(worldIn.getBlockState(pos.up(i)).getBlock() == RegisterBlocks.ELEVATOR && worldIn.getBlockState(pos.up(i + 1)).getMaterial() == Material.AIR && worldIn.getBlockState(pos.up(i + 2)).getMaterial() == Material.AIR)
						{
							event.getEntityLiving().setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + i + 1, pos.getZ() + 0.5);
							i = 21;
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingEvent(LivingEvent.LivingUpdateEvent event)
	{
		if(event.getEntityLiving() != null)
		{
			if(event.getEntityLiving().isPotionActive(RegisterPotions.FIRE_VULNERABILITY) && !event.getEntityLiving().world.isRemote)
			{
				int fire = ObfuscationReflectionHelper.getPrivateValue(Entity.class, event.getEntity(), 50);
				if(fire > 0 && fire % 20 == 0)
				{
					UtilsVoids.attackEntityFrom(event.getEntityLiving(), DamageSource.ON_FIRE, 1);
				}
			}
			
			if(event.getEntityLiving().getEntityData() != null && event.getEntityLiving().getEntityData().hasKey("manipulation_owner"))
			{
				EntityPlayer player = event.getEntityLiving().world.getPlayerEntityByName(event.getEntityLiving().getEntityData().getString("manipulation_owner"));
				if(player != null)
				{
					if(event.getEntityLiving().getRevengeTarget() == player)
					{
						event.getEntityLiving().setRevengeTarget(null);
						event.getEntityLiving().getCombatTracker().reset();
					}
					
					if(event.getEntityLiving() instanceof EntityLiving && ((EntityLiving) event.getEntityLiving()).getAttackTarget() == player)
					{
						((EntityLiving) event.getEntityLiving()).setAttackTarget(null);
					}
				}
				
				if(event.getEntityLiving().getHealth() > 30.0F)
				{
					event.getEntityLiving().setHealth(30.0F);
				}
			}
			if(event.getEntityLiving() instanceof EntityPlayer)
			{
				
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				Item legs = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem();
				
				if(player.dimension == MathsDims.Copraveg.getId() && !player.capabilities.isCreativeMode && !player.isPotionActive(RegisterPotions.FALL_PROTECTION))
				{
					player.fallDistance = 0;
		    		if(player.motionY < 0)
		    		{
		    			player.motionY *= 0.8F;
		    		}
				}
				
				if(player.dimension == MathsDims.bananaID)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, false, false));
				}
				
				if(player.isBurning() && UtilsVoids.playerHasItemMain(player, RegisterItems.PYRITE_GEM))
				{
					player.extinguish();
					if(!player.isPotionActive(MobEffects.FIRE_RESISTANCE))
					{
						player.attackEntityFrom(DamageSource.IN_FIRE, 1);
					}
				}
				
				if((legs == RegisterItems.POLARITE_BOOTS || legs == RegisterItems.HEAVY_BOOTS) && player.motionY < -0.9)
				{
					for(Entity entity : player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(0.2F)))
					{
						if(entity instanceof EntityLivingBase && player.posY > entity.posY + entity.getEyeHeight() - 0.7F)
						{
							((EntityLivingBase) entity).attackEntityFrom(MDamageSource.causeStompDamage((EntityPlayer) event.getEntityLiving()), 30);
						}
					}
				}
				
				if(!(player.getHeldItemMainhand().getItem() instanceof ItemAxe))
				{
					player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(Float.POSITIVE_INFINITY);
				}
				else
				{
					player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(5);
				}
				if(player.getHeldItemMainhand().getItem() instanceof ItemInfinityGauntlet)
				{
					if(player.isSneaking())
					{
						ItemInfinityGauntlet.setSize(2.3F, player);
						player.eyeHeight = player.getDefaultEyeHeight() * 1.2F;
					}
					else
					{
						ItemInfinityGauntlet.setSize(2.7F, player);
						player.eyeHeight = player.getDefaultEyeHeight() * 1.5F;
					}
					
				}
				else
				{
					player.eyeHeight = player.getDefaultEyeHeight();
				}
				
				if(player.isSneaking() && player.world.getBlockState(new BlockPos(player).down()).getBlock() == RegisterBlocks.ELEVATOR && player.ticksExisted % 5 == 0)
				{
					BlockPos pos = new BlockPos(player).down();
					for(int i = 1; i <= 20; ++i )
					{
						if(pos.getY() - i >= 0)
						{
							World worldIn = player.world;
							if(worldIn.getBlockState(pos.down(i)).getBlock() == RegisterBlocks.ELEVATOR && worldIn.getBlockState(pos.down(i - 1)).getMaterial() == Material.AIR && worldIn.getBlockState(pos.down(i - 2)).getMaterial() == Material.AIR)
							{
								player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() - i + 1, pos.getZ() + 0.5);
								i = 21;
							}
						}
					}
				}
			}
			else if(event.getEntityLiving() instanceof EntityWolf)
			{
				EntityWolf entity = (EntityWolf) event.getEntityLiving();
				for(ItemStack is : entity.getArmorInventoryList())
		        {
		            if (is.getItem() instanceof ItemWolfArmor)
		            {
		                ((ItemWolfArmor) is.getItem()).onWolfArmorTick(is, entity.world, entity);
		            }
		        }
			}
			
			if(event.getEntityLiving() instanceof EntityLivingBase)
			{
				if(event.getEntityLiving().isPotionActive(RegisterPotions.SLOWFALL) && !(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ItemBazookb) && !(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ItemKB800))
				{
					if(event.getEntityLiving().motionY < 0 && !event.getEntityLiving().isOnLadder() && !event.getEntityLiving().isInWater() && !event.getEntityLiving().isInLava())
					{
						if(event.getEntityLiving() instanceof EntityPlayer && ((EntityPlayer)event.getEntityLiving()).capabilities.isFlying)
						{
						}
						else
						{
							event.getEntityLiving().motionY = -0.125F;
						}
					}
				}
				if(event.getEntityLiving().isPotionActive(RegisterPotions.CHARGE))
				{
					EntityLivingBase ent = event.getEntityLiving();
					UtilsVoids.EasyShootEntity(ent, ent.rotationPitch, ent.rotationYaw, 0.7F, 0);
					//ent.moveForward = 1.0F;
					
					if(ent.getHeldItemMainhand().getItem() instanceof IShieldHit)
					{
						((IShieldHit) ent.getHeldItemMainhand().getItem()).onShieldCharge(ent, ent.getHeldItemMainhand());
					}
					else if(ent.getHeldItemOffhand().getItem() instanceof IShieldHit)
					{
						((IShieldHit) ent.getHeldItemOffhand().getItem()).onShieldCharge(ent, ent.getHeldItemOffhand());
					}
					
					if(ent.world.getEntitiesWithinAABBExcludingEntity(ent, ent.getEntityBoundingBox()).size() > 0 && !(ent instanceof EntityBananman))
					{
						for(Entity entity : ent.world.getEntitiesWithinAABBExcludingEntity(ent, ent.getEntityBoundingBox()))
						{
							if(entity instanceof EntityLivingBase)
							{
								if(ent instanceof EntityNChauvoput)
								{
									UtilsVoids.attackEntityFrom((EntityLivingBase) entity, DamageSource.causeMobDamage(ent), 30);
								}
								else
								{
									UtilsVoids.attackEntityFrom((EntityLivingBase) entity, DamageSource.causeMobDamage(ent), 10);
								}
								
								UtilsVoids.EasyShootEntityModifyY((EntityLivingBase) entity, ent.rotationPitch, ent.rotationYaw, 0.6F * 5F, 0);
								entity.motionY = entity.motionY * 0.1 + 0.6;
								
								if(entity instanceof EntityPlayerMP)
								{
									Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
								}
								
								if(ent.getHeldItemMainhand().getItem() instanceof IShieldHit)
								{
									((IShieldHit) ent.getHeldItemMainhand().getItem()).onShieldHit(ent, (EntityLivingBase) entity, ent.getHeldItemMainhand());
								}
								else if(ent.getHeldItemOffhand().getItem() instanceof IShieldHit)
								{
									((IShieldHit) ent.getHeldItemOffhand().getItem()).onShieldHit(ent, (EntityLivingBase) entity, ent.getHeldItemOffhand());
								}
								
								ent.removePotionEffect(RegisterPotions.CHARGE);
							}
						}
					}
				}
				if(event.getEntityLiving().isPotionActive(RegisterPotions.EXPLOSIVE_MAN))
				{
					EntityLivingBase ent = event.getEntityLiving();
					UtilsVoids.EasyShootEntity(ent, ent.rotationPitch, ent.rotationYaw, 0.75F, 0);
					ent.addPotionEffect(new PotionEffect(RegisterPotions.CHARGE));
					
					if(ent.world.getEntitiesInAABBexcluding(ent, ent.getEntityBoundingBox().grow(0.1D), IS_ENTITYLIVING).size() > 0 || ent.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RegisterItems.KURAYUM_JETPACK || !(ent.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemExplosiveBelt))
					{
						Mathsmod.network.sendToAllAround(new MessageParticleEffects(0, ent.getPositionVector(), 5), new TargetPoint(ent.dimension, ent.posX, ent.posY, ent.posZ, 40));

						ent.world.playSound(ent.posX, ent.posY, ent.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 2, 1, false);
	        			
						if(!ent.world.isRemote)
						{
							for(Entity ent2 : ent.world.getEntitiesWithinAABBExcludingEntity(ent, ent.getEntityBoundingBox().grow(3)))
				    		{
				    			if(ent2 instanceof EntityLivingBase)
				    			{
				    				if(ent.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RegisterItems.FAKE_EXPLOSIVE_BELT)
									{
					        			((EntityLivingBase) ent2).heal(20);
									}
				    				else
				    				{
					        			UtilsVoids.attackEntityFrom((EntityLivingBase) ent2, DamageSource.causeExplosionDamage(ent), 30);
				    				}
				    			}
				    		}
						}
						
						if(ent.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RegisterItems.GOLDEN_EXPLOSIVE_BELT)
						{
							ent.setHealth(1);
						}
						else
						{
							ent.attackEntityFrom(DamageSource.GENERIC, 3000);
							ent.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
						}
						
						if(!ent.world.isRemote)
						{
							Mathsmod.network.sendToAll(new MessageTempAnim(ent, -2));
						}
						ent.removePotionEffect(RegisterPotions.EXPLOSIVE_MAN);
					}
				}
				if(event.getEntityLiving().isPotionActive(MobEffects.NAUSEA) && event.getEntityLiving() instanceof EntityLiving)
				{
					if(event.getEntityLiving().getActivePotionEffect(MobEffects.NAUSEA).getDuration() % 2 == 0)
					{
						if(event.getEntityLiving().world.isRemote)
						{
							event.getEntityLiving().rotationPitch += event.getEntityLiving().getRNG().nextInt(40) - 20;
							event.getEntityLiving().rotationYaw += event.getEntityLiving().getRNG().nextInt(40) - 20;
						}
						if(!event.getEntityLiving().world.isRemote)
						{
							event.getEntityLiving().rotationPitch += event.getEntityLiving().getRNG().nextInt(40) - 20;
							event.getEntityLiving().rotationYaw += event.getEntityLiving().getRNG().nextInt(40) - 20;
						}
					}
				}
			}
			
			if(event.getEntityLiving().isPotionActive(RegisterPotions.FIRE_REGENERATION))
			{
				event.getEntityLiving().setFire(1);
				if(event.getEntityLiving().isInWater() && event.getEntityLiving().getActivePotionEffect(RegisterPotions.FIRE_REGENERATION).getDuration() % 5 == 0)
				{
					event.getEntityLiving().attackEntityFrom(DamageSource.DROWN, 1);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onFallEvent(LivingFallEvent event)
	{
		if(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ItemSmasher)
		{
			event.setDamageMultiplier(0);
			ItemSmasher item = (ItemSmasher) event.getEntityLiving().getHeldItemMainhand().getItem();
			if(event.getDistance() > 8)
			{
				//PerfectExplosion.EntityBoom(Blocks.AIR, event.getEntityLiving(), 4, true, true, false);
				item.onLanding(event.getEntityLiving().world, event.getEntityLiving(), event.getEntityLiving().getHeldItemMainhand());
			}
		}
		
		if(event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == RegisterItems.REBONDI_BOOTS)
		{
			event.setCanceled(true);
		}
		
		if(event.getEntityLiving().isPotionActive(RegisterPotions.SLOWFALL))
		{
			if(event.getEntityLiving().motionY < 0 && !event.getEntityLiving().isOnLadder() && !event.getEntityLiving().isInWater() && !event.getEntityLiving().isInLava())
			{
				if(event.getEntityLiving() instanceof EntityPlayer && ((EntityPlayer)event.getEntityLiving()).capabilities.isFlying)
				{
				}
				else
				{
					event.setDamageMultiplier(0);
				}
			}
		}
		
		if(event.getEntityLiving().isPotionActive(RegisterPotions.FALL_PROTECTION))
		{
			event.setDamageMultiplier(0);
			event.getEntityLiving().removeActivePotionEffect(RegisterPotions.FALL_PROTECTION);
		}
		
		if(event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == RegisterItems.POLARITE_BOOTS)
		{
			event.setDamageMultiplier(0);
			
			/*BlockPos a = new BlockPos(event.getEntityLiving()).down();
			
			if(event.getDistance() > 8)
			{
				if(event.getEntityLiving() instanceof EntityPlayer)
				{
					for(EntityLivingBase entity : UtilsVoids.getEntitiesPos(EntityLivingBase.class, event.getEntityLiving().world, a, 5))
		    		{
		    			if(entity != event.getEntityLiving() && entity.onGround)
		    			{
		    				entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) event.getEntityLiving()).setDamageBypassesArmor(), 4);
		    			}
		    		}
				}
				
				for(int i = -5; i <= 5; ++i)
	    		{
	    			for(int j = -5; j <= 5; ++j)
	        		{
	    				World world = event.getEntityLiving().world;
	    				
	    				BlockPos b = a.add(i, 0, j);
	    				BlockPos pos = b.add(0, world.getHeight(b.getX(), b.getZ()) - b.getY() - 1, 0);
	        			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5D, pos.getZ() + 0.5D, event.getEntityLiving()) < 6 && (i != 0 || j != 0))
	        			{
	        				IBlockState state = world.getBlockState(pos);
	        				if(state.getBlockHardness(world, pos) <= 10 && state.getBlockHardness(world, pos) >= 0
	        						&& world.getBlockState(pos.down()).isFullCube())
	        				{
	        					world.setBlockToAir(pos);
	        					EntityAbsorbedBlock entity = new EntityAbsorbedBlock(world, state, pos);
	        					entity.posY += 0.1F;
	        					entity.motionY = 0.05F * UtilsVoids.distanceIgnoreY(entity.posX, entity.posZ, event.getEntityLiving());
	        					if(!world.isRemote)
	        					{
	            					world.spawnEntity(entity);
	        					}
	        				}
	        			}
	        		}
	    		}
			}*/
		}
		else if(event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == RegisterItems.HEAVY_BOOTS)
		{
			event.setDamageMultiplier(0.15F);
		}
	}
	
	@SubscribeEvent
	public void onAttackEvent(LivingAttackEvent event)
	{
		if(event.getSource() != DamageSource.OUT_OF_WORLD)
		{
			if(!event.getSource().isMagicDamage())
			{
				if(event.getEntityLiving().getRidingEntity() instanceof EntityTank)
				{
					event.setCanceled(true);
				}
			}
			if(event.getEntityLiving().isPotionActive(RegisterPotions.INVUL) || event.getEntityLiving().isPotionActive(RegisterPotions.UBER))
			{
				event.setCanceled(true);
			}
		}
		if(event.getSource().getTrueSource() != null)
		{
			if(event.getSource().getTrueSource() instanceof EntityLivingBase && ((EntityLivingBase) event.getSource().getTrueSource()).isPotionActive(RegisterPotions.INVUL))
			{
				event.setCanceled(true);
			}
			else if(event.getSource().getTrueSource() instanceof EntityLivingBase && ((EntityLivingBase) event.getSource().getTrueSource()).isPotionActive(RegisterPotions.FRUIT_PUNCH))
			{
				EntityLivingBase entity = event.getEntityLiving();
				Vec3d vec = UtilsVoids.laser(5, ((EntityLivingBase) event.getSource().getTrueSource()).rotationYaw, ((EntityLivingBase) event.getSource().getTrueSource()).rotationPitch - 30).addVector(0, 0.2, 0);
				if(entity.getMaxHealth() >= 100)
				{
					vec = UtilsVoids.laser(0.3, ((EntityLivingBase) event.getSource().getTrueSource()).rotationYaw, ((EntityLivingBase) event.getSource().getTrueSource()).rotationPitch - 30).addVector(0, 0.2, 0);
				}
				
				entity.motionX = vec.x;
				entity.motionY = vec.y;
				entity.motionZ = vec.z;
				
				if(entity instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageModifyVelocity(vec, 0), (EntityPlayerMP) entity);
				}
			}
			
			if(event.getSource().getTrueSource().getEntityData() != null && event.getSource().getTrueSource().getEntityData().hasKey("manipulation_owner"))
			{
				EntityPlayer player = event.getEntityLiving().world.getPlayerEntityByName(event.getEntityLiving().getEntityData().getString("manipulation_owner"));
				if(player != null && event.getSource().getTrueSource() instanceof EntityLivingBase)
				{
					if(event.getEntityLiving() == player)
					{
						((EntityLivingBase) event.getSource().getTrueSource()).setRevengeTarget(null);
						((EntityLivingBase) event.getSource().getTrueSource()).getCombatTracker().reset();
						event.setCanceled(true);
					}
				}
			}
			
			if(event.getSource().getTrueSource() instanceof EntityPlayer && !event.getEntityLiving().world.isRemote)
			{
				for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLiving.class, event.getEntityLiving().world, event.getSource().getTrueSource(), 20))
				{
					if(entity.getEntityData().hasKey("manipulation_owner") && entity.getEntityData().getString("manipulation_owner") == event.getSource().getTrueSource().getName())
					{
						if(entity instanceof EntityMob)
						{
							EntityMob entity2 = (EntityMob) entity;
							entity2.setAttackTarget(event.getEntityLiving());
						}
					}
				}
				
				EntityPlayer attacker = (EntityPlayer) event.getSource().getTrueSource();
				
				if(!attacker.world.isRemote && SpellManager.getActiveSpellServer(attacker) != null)
				{
					TwoIntAndString tias = SpellManager.getActiveSpellServer(attacker);
					if(tias.spellName != null && SpellRegistry.REGISTRY.containsKey(tias.spellName))
					{
						SpellBase spell = SpellRegistry.REGISTRY.get(tias.spellName);
						spell.spellUseMelee(tias.phase, tias.progress, attacker, event.getEntityLiving(), attacker.getHeldItemMainhand());
					
						if(spell.reaction(tias.phase, tias.progress, attacker) == EnumSpellReaction.CANT || spell.reaction(tias.phase, tias.progress, attacker) == EnumSpellReaction.CAN_SHOOT)
						{
							event.setCanceled(true);
						}
					}
				}
			}
		}
		if(event.getSource().isFireDamage() && event.getEntityLiving().isPotionActive(RegisterPotions.FIRE_REGENERATION))
		{
			event.setCanceled(true);
			if(event.getEntityLiving().getActivePotionEffect(RegisterPotions.FIRE_REGENERATION).getDuration() % 20 == 0)
			{
				event.getEntityLiving().heal(event.getAmount());
			}
		}
		
		if(event.getEntityLiving() instanceof EntityWolf)
		{
			EntityWolf entity = (EntityWolf) event.getEntityLiving();
			
			/*if(entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == this
			&& entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this
			&& entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this
			&& entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == this)
			{
				
			}*/
		}
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event)
	{
		float amount = event.getAmount();
		
		if(event.getSource().isExplosion())
		{
			
			for(ItemStack stack : event.getEntityLiving().getArmorInventoryList())
        	{
        		if(stack.getItem() instanceof IExplosionProtection)
        		{
        			amount *= ((IExplosionProtection) stack.getItem()).explosionMultiplier(event.getEntityLiving(), stack.getItem(), stack);
        		}
        	}
		}
		
		if(event.getEntityLiving().isPotionActive(RegisterPotions.EXPLOSIVE_MAN) && event.getSource().getTrueSource() != null)
		{
			amount *= 10.0F;
		}
		
		if((event.getSource().getTrueSource() == null || event.getSource().getTrueSource() instanceof EntityLivingBase) && event.getEntityLiving().getHeldItemMainhand().getItem() == RegisterItems.CUSTOM_SWORD)
		{
			for(ItemStack stack : ItemCustomSword.getComponents(event.getEntityLiving().getHeldItemMainhand()))
			{
				if(stack.getItem() instanceof ItemToolPart)
				{
					((ItemToolPart) stack.getItem()).userHit((EntityLivingBase) event.getSource().getTrueSource(), event.getEntityLiving(), event.getEntityLiving().world, stack);
				}
			}
		}
		
		if(event.getSource().getTrueSource() != null)
		{
			for(int i = 2; i < 6; i++)
			{
				EntityEquipmentSlot slot = EntityEquipmentSlot.values()[i];
	    		ItemStack stack = event.getEntityLiving().getItemStackFromSlot(slot);
	    		if(!stack.isEmpty() && stack.getItem() instanceof ItemMetalicArmor)
	    		{
	    			ItemMetalicArmor item = (ItemMetalicArmor) stack.getItem();
	    			item.getMetal(stack).onWearerHurt(stack, event.getEntityLiving(), slot, event.getSource());
	    		}
			}
		}
		
		if(event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemKurayumJetpack)
		{
			ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if(ItemKurayumJetpack.getUsing(stack))
			{
				amount *= 10;
			}
		}
		event.setAmount(amount);
	}
	
	@SubscribeEvent
	public void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event)
	{
		if(event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockJukebox)
		{
			BlockJukebox block = (BlockJukebox) event.getWorld().getBlockState(event.getPos()).getBlock();
			if(event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemCommunistDisk)
			{
				for(EntityHunter entity : UtilsVoids.getEntitiesPos(EntityHunter.class, event.getWorld(), event.getPos(), 20))
				{
					entity.setCommunist(true);
					entity.setCustomNameTag("communist");
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
	{
		if(event.getTarget() instanceof EntityWolf)
		{
			EntityWolf entity = (EntityWolf) event.getTarget();
			
			if(entity.isOwner(event.getEntityPlayer()) && !(event.getEntityLiving().getHeldItemMainhand().getItem() instanceof ItemFood) && event.getEntity().isSneaking())
			{
				event.setCanceled(true);
				event.getEntityPlayer().openGui(Mathsmod.instance, 8, event.getWorld(), event.getTarget().getEntityId(), 0, 0);
			}
			else if(!event.getWorld().isRemote && entity.isTamed() && entity.getHealth() < entity.getMaxHealth() && event.getEntityLiving().getHeldItem(event.getHand()).getItem() instanceof ItemRegenerativeSteak)
			{
				event.getEntityLiving().getHeldItem(event.getHand()).setCount(2);
			}
			else if(!event.getWorld().isRemote && !entity.isInLove() && event.getEntityLiving().getHeldItem(event.getHand()).getItem() instanceof ItemRegenerativeSteak)
			{
				event.getEntityLiving().getHeldItem(event.getHand()).setCount(2);
			}
		}
		
		if(event.getTarget() instanceof EntitySkeleton && event.getEntityLiving().getHeldItem(event.getHand()).getItem() == RegisterItems.SKELETON_MANAGER)
		{
			EntitySkeleton entity = (EntitySkeleton) event.getTarget();
			event.setCanceled(true);
			event.getEntityPlayer().openGui(Mathsmod.instance, 8, event.getWorld(), event.getTarget().getEntityId(), 0, 0);
		}
	}

	@SubscribeEvent
	public void onGunShot(onShotEvent event)
	{
		if(event.getEntity().getRidingEntity() != null && event.getEntity().getRidingEntity() instanceof EntityTank)
		{
			if(event.getEntity().getRidingEntity().getPassengers().indexOf(event.getEntity()) < 2)
			{
				event.setCanceled(true);
			}
		}
		if(event.getEntity().isPotionActive(RegisterPotions.INVUL))
		{
			event.setCanceled(true);
		}
		/*if(event.getEntity() instanceof EntityPlayer && event.getEntity().hasCustomName())
		{
			if(SpellDisguise.getDisguisment((EntityLivingBase) event.getEntity()) != null)
			{
				event.getEntity().setCustomNameTag("");
			}
		}*/
	}
	
	
	@SubscribeEvent
	public void onFoodEaten(LivingEntityUseItemEvent.Finish event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			if(event.getItem().getItem() instanceof ItemFood)
			{
				ItemStack stack = event.getItem();
				if(stack.hasTagCompound() && stack.getTagCompound().hasKey("is_glowberry_poisonned") && stack.getTagCompound().getBoolean("is_glowberry_poisonned"))
				{
					event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.GLOWING, 1200));
				}
			}
		}
	}
	
	
	//TODO impact manager
	@SubscribeEvent
	public void onBulletImpact(onImpactEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer && SpellManager.getActiveSpellServer((EntityPlayer) event.getEntity()) != null && SpellManager.getActiveSpellServer((EntityPlayer) event.getEntity()).phase == 1 && SpellManager.getActiveSpellServer((EntityPlayer) event.getEntity()).spellName == "gun_goes_boom")
		{
			if(event.getTarget() != null && event.getStack().getItem() instanceof ItemGun)
			{
				ItemGun gun = (ItemGun) event.getStack().getItem();
				if(gun.getBullet() == EnumBulletType.SHOTGUN && rand.nextInt(5) == 0)
				{
					event.getWorld().createExplosion(event.getEntity(), event.getTarget().posX, event.getTarget().posY, event.getTarget().posZ, 1, false);
				}
			}
		}
		if(!event.getStack().isEmpty())
		{
			if(event.getTarget() instanceof EntityLivingBase && (((EntityLivingBase) event.getTarget()).isPotionActive(RegisterPotions.INVUL) || ((EntityLivingBase) event.getTarget()).isPotionActive(RegisterPotions.UBER)))
			{
				event.setCanceled(true);
				return;
				
			}
			
			if(event.getEntity() instanceof EntityLivingBase && ((EntityLivingBase) event.getEntity()).isPotionActive(RegisterPotions.INVUL))
			{
				event.setCanceled(true);
				return;
				
			}
			
			if(event.getEntity().getRidingEntity() == null || !(event.getEntity().getRidingEntity() instanceof IVehicle))
			{
				if(event.getEntity() instanceof EntityLivingBase && ((EntityLivingBase) event.getEntity()).isPotionActive(RegisterPotions.UBER))
				{
					event.setDamageAmount(1.3F * event.getDamageAmount());
				}
				
				if(event.getTarget() instanceof EntityLivingBase)
				{
					EntityLivingBase entity = (EntityLivingBase) event.getTarget();
					
					if(entity.isActiveItemStackBlocking() && entity.getActiveItemStack() != null && !entity.getActiveItemStack().isEmpty() && entity.getActiveItemStack().getItem() instanceof ItemAbstractProtectionShield)
			    	{
			    		Vec3d vec1 = event.getEntity().getPositionEyes(1.0F).subtract(entity.getPositionEyes(1.0F));
			    		Vec3d vec2 = entity.getLookVec();
			        	ItemAbstractProtectionShield item = (ItemAbstractProtectionShield) entity.getActiveItemStack().getItem();
			        	
			    		if(UtilsVoids.dotProduct(vec1, vec2) >= 0.2 && item.getCharge(entity.getActiveItemStack()) > 0)
			    		{
			    			UtilsVoids.decreaseShield(entity);
			    			event.setCanceled(true);
			    		}
			    	}
				}
				
				if(event.getEntity() instanceof EntitySuperHunter)
				{
					event.setDamageAmount(event.getDamageAmount() * 1.5F);
				}
				
				if(event.getTarget() instanceof EntityLivingBase && ((EntityLivingBase) event.getTarget()).isPotionActive(RegisterPotions.UBER))
				{
					event.setDamageAmount(0.0F);
				}
				
				if(event.getTarget() instanceof EntityLivingBase && ((EntityLivingBase) event.getTarget()).isPotionActive(RegisterPotions.EXPLOSIVE_MAN))
				{
					event.setDamageAmount(10.0F);
				}
				
				if(event.getStack().getItem() instanceof ItemKB800)
				{
					if(event.getTarget() instanceof EntityLivingBase)
					{
						EntityLivingBase target = (EntityLivingBase) event.getTarget();
						
						
						target.knockBack(event.getTarget(), 2, event.getEntity().posX - target.posX, event.getEntity().posZ - target.posZ);
						
						if(event.getTarget() instanceof EntityPlayerMP)
						{
							Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(target.motionX, target.motionY, target.motionZ)), (EntityPlayerMP) event.getTarget());
						}
					}
				}
				
				if(event.getStack().getItem() == RegisterItems.MANJTEMOR)
				{
					if(event.getTarget() != null && event.getTarget() instanceof EntityLivingBase)
					{
						EntityLivingBase entity = (EntityLivingBase) event.getTarget();
						entity.heal(event.getDamageAmount());
						Vec3d vec = UtilsVoids.laser(0.15F, event.getEntity().rotationYaw, event.getEntity().rotationPitch);
						entity.motionX += vec.x;
						entity.motionY += vec.y;
						entity.motionZ += vec.z;
						if(entity instanceof EntityPlayerMP)
						{
							Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ), 1), (EntityPlayerMP) entity);
						}
					}
				}
				
				if(event.getStack().getItem() == RegisterItems.MEDIC_S_SHOTGUN)
				{
					ItemGun item = (ItemGun) event.getStack().getItem();
					ItemStack s = new ItemStack(item.getFirstBullet());
					
					if(event.getTarget() != null)
					{
						if(item.getCharger(event.getStack()) < item.getChargerSize())
						{
							item.chargeCharger(event.getStack(), item.getFirstBullet(), item.getCharger(event.getStack()) + 1);
						}
					}
				}
				
				if(event.getStack().getItem() == RegisterItems.MEDIC_S_THOMPSON)
				{
					if(event.getTarget() != null)
					{
						if(event.getEntity() != null && event.getEntity() instanceof EntityLivingBase)
						{
							EntityLivingBase user = (EntityLivingBase) event.getEntity();
							user.heal(0.125F);
						}
					}
				}
				
				/*if(event.getStack().getItem() == RegisterItems.ENDER_SNIPER)
				{
					if(event.getTarget() != null && event.getTarget() instanceof EntityLivingBase)
					{
						if(event.getEntity() != null && event.getEntity() instanceof EntityLivingBase)
						{
							EntityLivingBase user = (EntityLivingBase) event.getEntity();
							user.setPositionAndUpdate(event.getTarget().posX, event.getTarget().posY, event.getTarget().posZ);
							event.getTarget().setPositionAndUpdate(user.posX, user.posY, user.posZ);
						}
					}
				}*/
				
				if(event.getStack().getItem() == RegisterItems.THOMPSON || event.getStack().getItem() == RegisterItems.MEDIC_S_THOMPSON)
				{
					if(event.getEntity() != null && event.getEntity() instanceof EntityLivingBase)
					{
						EntityLivingBase user = (EntityLivingBase) event.getEntity();
						
						if(user.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == RegisterItems.BLACK_HAT)
						{
							event.setDamageAmount(event.getDamageAmount() * 1.75F);
						}
					}
				}
				else if(event.getStack().getItem() == RegisterItems.HUNTER_SHOTGUN)
				{
					if(event.getEntity() != null && event.getEntity() instanceof EntityLivingBase)
					{
						EntityLivingBase user = (EntityLivingBase) event.getEntity();
						
						if(user.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == RegisterItems.BLACK_HAT)
						{
							event.setDamageAmount(event.getDamageAmount() * 1.5F);
						}
					}
				}
				else if(event.getStack().getItem() == RegisterItems.AK47)
				{
					if(event.getEntity() != null && event.getEntity() instanceof EntityLivingBase)
					{
						EntityLivingBase user = (EntityLivingBase) event.getEntity();
						
						if(user.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == RegisterItems.CHAPKA)
						{
							event.setDamageAmount(event.getDamageAmount() * 1.75F);
						}
					}
				}
				
				if(event.getTarget() != null && event.getTarget() instanceof EntityLivingBase && event.getStack().getItem() instanceof ItemShotGun)
				{
					if(((EntityLivingBase) event.getTarget()).isPotionActive(RegisterPotions.CHARGE))
					{
						ItemShotGun item = (ItemShotGun) event.getStack().getItem();
						
						event.setDamageAmount(event.getDamageAmount() * 0.5F);
						int i = ((EntityLivingBase) event.getTarget()).getActivePotionEffect(RegisterPotions.CHARGE).getDuration();
						int lv = ((EntityLivingBase) event.getTarget()).getActivePotionEffect(RegisterPotions.CHARGE).getAmplifier() - item.stopPower();
						((EntityLivingBase) event.getTarget()).removeActivePotionEffect(RegisterPotions.CHARGE);
						if(lv >= 0)
						{
							((EntityLivingBase) event.getTarget()).addPotionEffect(new PotionEffect(RegisterPotions.CHARGE, i, lv));
						}
					}
				}

				int serialLvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.SERIAL, event.getStack());
				if(serialLvl > 0 && event.getTarget() != null && event.getTarget() != event.getEntity())
				{
					event.setDamageAmount(event.getDamageAmount() * (1F + ((((float)(serialLvl))) - 2) * 0.2F));
					ItemStackUtils.removeEnchantment(event.getStack(), Enchantments.SERIAL);
					event.getStack().addEnchantment(Enchantments.SERIAL, MathHelper.clamp(serialLvl + 1, 1, 4));
				}
				else if(serialLvl > 0)
				{
					ItemStackUtils.removeEnchantment(event.getStack(), Enchantments.SERIAL);
					event.getStack().addEnchantment(Enchantments.SERIAL, 1);
				}
			}
		}
	}
	
	public static void reload(OnGunReload event, int num, ItemBaseBullet item)
	{
		if(event.getItem().rechargeOneByOne())
		{
			event.getItem().chargeCharger(event.getStack(), item, event.getItem().getCharger(event.getStack()) + 1);
			event.setBlocked();
			
			if(event.getItem().getCharger(event.getStack()) >= event.getItem().getChargerSize())
			{
				event.getItem().setReloading(event.getStack(), false);
			}
			if(event.getEntityLiving() instanceof EntityPlayer)
			{
				event.getItem().substractReload(event.getStack(), (EntityPlayer) event.getEntityLiving(), event.getItem().getReload(event.getStack()));
			}
			else
			{
				event.getItem().substractReload(event.getStack(), null, event.getItem().getReload(event.getStack()));
			}
		}
		else if(num < 0)
		{
			event.getItem().chargeCharger(event.getStack(), item, event.getItem().getChargerSize());
			event.getItem().setReloading(event.getStack(), false);
			
			if(event.getEntityLiving() instanceof EntityPlayer)
			{
				event.getItem().substractReload(event.getStack(), (EntityPlayer) event.getEntityLiving(), event.getItem().getReload(event.getStack()));
			}
			else
			{
				event.getItem().substractReload(event.getStack(), null, event.getItem().getReload(event.getStack()));
			}
		}
		else
		{
			event.getItem().chargeCharger(event.getStack(), item, event.getItem().getCharger(event.getStack()) + num);
			event.getItem().setReloading(event.getStack(), false);
			if(event.getEntityLiving() instanceof EntityPlayer)
			{
				event.getItem().substractReload(event.getStack(), (EntityPlayer) event.getEntityLiving(), event.getItem().getReload(event.getStack()));
			}
			else
			{
				event.getItem().substractReload(event.getStack(), null, event.getItem().getReload(event.getStack()));
			}
		}
	}
	
	@SubscribeEvent
	public void onGunReload(OnGunReload event)
	{
		if(MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "infinite bullets", false).getBoolean())
		{
			if(event.getEntityLiving() != null && event.getEntityLiving().getHeldItemOffhand().getItem() instanceof ItemBaseBullet && event.getItem().goodBulletType((ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem()))
			{
				this.reload(event, -1, (ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem());
			}
			else if(event.getItem().getFirstBullet() != null)
			{
				this.reload(event, -1, event.getItem().getFirstBullet());
			}
		}
		else 
		{
			if(EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, event.getStack()) > 0)
			{
				if(event.getItem().getChargerSize() > 6)
				{
					if(event.getItem().getCharger(event.getStack()) < event.getItem().getChargerSize() / 2)
					{
							if(event.getEntityLiving() != null && event.getEntityLiving().getHeldItemOffhand().getItem() instanceof ItemBaseBullet && event.getItem().goodBulletType((ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem()))
							{
								this.reload(event, -1, (ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem());
							}
							else if(event.getItem().getFirstBullet() != null)
							{
								this.reload(event, -1, event.getItem().getFirstBullet());
							}
					}
					else
					{
						if(event.getEntityLiving() != null && event.getEntityLiving().getHeldItemOffhand().getItem() instanceof ItemBaseBullet && event.getItem().goodBulletType((ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem()))
						{
							this.reload(event, -1, (ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem());
						}
						else if(event.getItem().getFirstBullet() != null)
						{
							this.reload(event, -1, event.getItem().getFirstBullet());
						}
					}
				}
				else
				{
					if(event.getEntityLiving() != null && event.getEntityLiving().getHeldItemOffhand().getItem() instanceof ItemBaseBullet && event.getItem().goodBulletType((ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem()))
					{
						this.reload(event, -1, (ItemBaseBullet) event.getEntityLiving().getHeldItemOffhand().getItem());
					}
					else if(event.getItem().getFirstBullet() != null)
					{
						this.reload(event, -1, event.getItem().getFirstBullet());
					}
				}
			}
			else if(event.getEntityLiving() instanceof EntityPlayer)
			{
				if(!UtilsVoids.getAmmoBoxes((EntityPlayer) event.getEntityLiving(), event.getItem()).isEmpty())
				{
					for(ItemStack stack : UtilsVoids.getAmmoBoxes((EntityPlayer) event.getEntityLiving(), event.getItem()))
					{
						if(stack.getItem() instanceof ItemAmmoBox && ((ItemAmmoBox) stack.getItem()).getAmmo(stack).getItem() instanceof ItemBaseBullet)
						{
							ItemAmmoBox box = (ItemAmmoBox) stack.getItem();
							ItemBaseBullet item = (ItemBaseBullet) ((ItemAmmoBox) stack.getItem()).getAmmo(stack).getItem();
							
							if(event.getItem().goodBulletType(item) && (event.getItem().bulletUsed(stack, null) == item || event.getItem().getCharger(stack) == 0))
							{
								int val = event.getItem().getChargerSize() - event.getItem().getCharger(event.getStack());
								if(box.getItemCount(stack) < val)
								{
									val = box.getItemCount(stack);
								}
								
								box.setItemCount(box.getItemCount(stack) - val, stack);
								this.reload(event, val, item);
							}
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent event)
	{
		EntityLivingBase vic  = event.getEntityLiving();
		
		if(vic instanceof EntityPlayer && event.getSource() != null && !((EntityPlayer) vic).getCooldownTracker().hasCooldown(RegisterItems.FROZEN_GEM) && event.getSource() != DamageSource.OUT_OF_WORLD && !UtilsVoids.getItemMain((EntityPlayer) vic, RegisterItems.FROZEN_GEM).isEmpty())
		{
			event.setCanceled(true);
			vic.setHealth(1F);
			vic.clearActivePotions();
			vic.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 2, true, false));
			vic.addPotionEffect(new PotionEffect(RegisterPotions.INVUL, 60, 1, true, false));
			vic.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 60, 1, true, false));
			vic.addPotionEffect(new PotionEffect(RegisterPotions.DISAPPEAR, 60, 1, true, false));
			((EntityPlayer) vic).getCooldownTracker().setCooldown(RegisterItems.FROZEN_GEM, 12000);
			EntityRagdoll entity = new EntityRagdoll(event.getEntityLiving().world);
			entity.setPosition(event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ);
			entity.setCustomNameTag(event.getEntityLiving().getName());
			event.getEntityLiving().world.spawnEntity(entity);
			ItemStackUtils.dropPlayerInventory((EntityPlayer) vic);
		}
		
		if(vic instanceof EntityPlayer)
		{
			
			BlockPos pos = new BlockPos(vic);
			vic.sendMessage(new TextComponentString("you died in " + pos.toString()));
			
			if(!vic.world.isRemote)
			{
				EntityRagdoll entity = new EntityRagdoll(event.getEntityLiving().world);
				entity.setPosition(event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ);
				entity.setCustomNameTag(event.getEntityLiving().getName());
				event.getEntityLiving().world.spawnEntity(entity);
				Mathsmod.network.sendToAll(new MessageTempAnim(vic, -1));
			}
			
			ContractManagerServer.addProgress((EntityPlayer) vic, RegisterContracts.REVOLVER, 1);
			if(event.getSource() == DamageSource.FALL)
			{
				ContractManagerServer.addProgress((EntityPlayer) vic, RegisterContracts.REBONDI_BOOTS, 1);
			}
			
			if(vic.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == RegisterItems.FESTIVE_JETPACK)
			{
				((ItemFestiveJetpack) vic.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).setUsing(vic.getItemStackFromSlot(EntityEquipmentSlot.CHEST), false);
			}
			
			if(event.getSource() != null && event.getSource().getTrueSource() instanceof EntityACBoss)
			{
				((EntityACBoss) event.getSource().getTrueSource()).heal(((EntityACBoss) event.getSource().getTrueSource()).getMaxHealth() * 0.4F);
			}
		}
		
		if(event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer killer = (EntityPlayer) event.getSource().getTrueSource();
			
			if(killer.getCooldownTracker().hasCooldown(RegisterItems.CHARGE_SHIELD))
			{
				killer.getCooldownTracker().setCooldown(RegisterItems.CHARGE_SHIELD, 0);
			}
			
			if(vic instanceof EntityPlayer)
			{
				if(killer.getHeldItemMainhand().getItem() == RegisterItems.GOLDEN_FRYING_PAN || killer.getHeldItemMainhand().getItem() == RegisterItems.POLARITE_FRYING_PAN)
				{
					if(!killer.world.isRemote)
					{
						EntityGoldDeathStatue entity = new EntityGoldDeathStatue(killer.world);
						entity.setPositionAndRotation(vic.posX, vic.posY, vic.posZ, vic.rotationYaw, vic.rotationPitch);
						entity.setPName(vic.getName());
						if(killer.getHeldItemMainhand().getItem() == RegisterItems.POLARITE_FRYING_PAN)
						{
							entity.setFrozen(true);
						}
						killer.world.spawnEntity(entity);
					}
				}
				
				if(!playerNbtManager.getMathsmodTag(killer).hasKey("lastKilled"))
				{
					playerNbtManager.getMathsmodTag(killer).setInteger("lastKilled", 0);
				}
				
				if(playerNbtManager.getMathsmodTag(killer).getInteger("lastKilled") == 0)
				{
					playerNbtManager.getMathsmodTag(killer).setString("reincarned_0", event.getEntityLiving().getName());
					playerNbtManager.getMathsmodTag(killer).setInteger("lastKilled", 1);
				}
				else
				{
					playerNbtManager.getMathsmodTag(killer).setString("reincarned_1", event.getEntityLiving().getName());
					playerNbtManager.getMathsmodTag(killer).setInteger("lastKilled", 0);
				}
				
				KillsCounter.setKills(killer, KillsCounter.getKills(killer) + 1);
				if(KillsCounter.getKills(killer) > 3)
				{
		        	killer.sendMessage(new TextComponentString(killer.getName() + " : " + KillsCounter.getKills(killer) + " kills"));
				}
				
				((EntityLivingBase) event.getSource().getTrueSource()).heal(10);
			}
			else
			{
				((EntityLivingBase) event.getSource().getTrueSource()).heal(4);
				
				if(vic instanceof EntityMosquito)
				{
					ContractManagerServer.addProgress(killer, RegisterContracts.TATANATOR, 1);
				}
				
				if(vic instanceof FrostResistance)
				{
					if(vic.getMaxHealth() > 100)
					{
						ContractManagerServer.addProgress(killer, RegisterContracts.POLARITE_LOOT_BOX, 5);
					}
					else
					{
						ContractManagerServer.addProgress(killer, RegisterContracts.POLARITE_LOOT_BOX, 1);
					}
				}
				
				if(vic instanceof EntityMob && vic.dimension == MathsDims.copraID)
				{
					ContractManagerServer.addProgress(killer, RegisterContracts.LOOT_BOX, 1);
				}
				
				ContractManagerServer.addProgress(killer, RegisterContracts.ORE_LOOT_BOX, 1);
				
				if(vic instanceof EntityBat)
				{
					if(event.getSource().getImmediateSource() instanceof EntityArrow)
					{
						ContractManagerServer.addProgress(killer, RegisterContracts.SHOTBOW, 1);
					}
				}
				
				if(vic instanceof EntityPyromancer)
				{
					ContractManagerServer.addProgress(killer, RegisterContracts.FURNACE_UPGRADE, 1);
				}
				
				if(vic instanceof EntityNetherHunter)
				{
					ContractManagerServer.addProgress(killer, RegisterContracts.KURAYUM_INGOT, 1);
				}
				
				if(vic instanceof EntityHunter && !(vic instanceof EntityNetherHunter))
				{
					ContractManagerServer.addProgress(killer, RegisterContracts.TECH_SHIELD, 1);
				}
				
				if(vic instanceof EntityShulker)
				{
					ContractManagerServer.addProgress(killer, RegisterContracts.KURAYUM_JETPACK, 1);
				}
			}
		}
	}
	
	
	//TODO spawnModification
	@SubscribeEvent
	public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		Random rand = new Random();
		
		if(entity instanceof EntitySquid && entity.dimension == MathsDims.Copraveg.getId())
		{
			if(!entity.world.isRemote && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL) 
			{
				if(rand.nextInt(2) == 0) 
				{
					EntityShark entityIn = new EntityShark(entity.world);
					entityIn.copyLocationAndAnglesFrom(entity);
					entity.world.spawnEntity(entityIn);
					event.setCanceled(true);
				}
				else if(rand.nextInt(2000) == 0) 
				{
					EntityMegalodon entityIn = new EntityMegalodon(entity.world);
					entityIn.copyLocationAndAnglesFrom(entity);
					entity.world.spawnEntity(entityIn);
					event.setCanceled(true);
				}
			}
		}
		else if(entity instanceof EntityBlal && entity.dimension == MathsDims.Copraveg.getId())
		{
			if(rand.nextInt(20) == 0) 
			{
				EntitySteroideBlal entityIn = new EntitySteroideBlal(entity.world);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			if(rand.nextInt(200) == 0) 
			{
				EntityDevilHand entityIn = new EntityDevilHand(entity.world);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
		}
		else if(entity instanceof EntityFrostBlal && entity.dimension == MathsDims.Sirojus.getId())
		{
			if(!entity.world.isRemote && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL) 
			{
				if(rand.nextInt(400) == 0) 
				{
					EntityZomboss entityIn = new EntityZomboss(entity.world);
					entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
					entityIn.copyLocationAndAnglesFrom(entity);
					entity.world.spawnEntity(entityIn);
					event.setCanceled(true);
				}
			}
		}
		else if(entity instanceof EntityWitherSkeleton)
		{
			if(rand.nextInt(3) == 0) 
			{
				EntityWitherBowman entityIn = new EntityWitherBowman(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
		}
		else if(entity instanceof EntityMob && entity.dimension == 0)
		{
			int i = rand.nextInt(100);
			/*if(i < 1) 
			{
				EntityHunter entityIn = new EntityHunter(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			if(i < 4) 
			{
				EntityNightGolem entityIn = new EntityNightGolem(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			
			else if(i < 5) 
			{
				EntityFrec entityIn = new EntityFrec(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			else if(i < 6) 
			{
				EntitySteroidNightGolem entityIn = new EntitySteroidNightGolem(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			else */if(i < 22 && entity.world.getBiome(new BlockPos(entity)) == RegisterBiomes.DESOLATE_LANDS)
			{
				EntityPyromancer entityIn = new EntityPyromancer(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			else if(i == 22 && rand.nextInt(1200) == 0) 
			{
				EntityEmmanuelMacron entityIn = new EntityEmmanuelMacron(entity.world);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
		}
		else if(entity instanceof EntitySquid && (event.getWorld().getBiome(new BlockPos(entity)) == Biomes.OCEAN || event.getWorld().getBiome(new BlockPos(entity)) == Biomes.DEEP_OCEAN))
		{
			int r = rand.nextInt(10);
			if(r == 0) 
			{
				EntityShark entityIn = new EntityShark(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
			else if(r == 1)
			{
				EntityBeluga entityIn = new EntityBeluga(entity.world);
				entityIn.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				entityIn.copyLocationAndAnglesFrom(entity);
				entity.world.spawnEntity(entityIn);
				event.setCanceled(true);
			}
		}
		else
		{
			if(event.getWorld().getBiome(new BlockPos(entity)) == Biomes.JUNGLE)
			{
				if(entity instanceof EntitySquid && rand.nextInt(10) == 0)
				{
					event.setCanceled(true);
					EntityPlatypus sp = new EntityPlatypus(event.getWorld());
					sp.setPosition(entity.posX, entity.posY, entity.posZ);
					event.getWorld().spawnEntity(sp);
				}
			}
				
		}
		
		if(event.getEntityLiving() instanceof EntityZombie && event.getEntityLiving().dimension == 0)
		{
			
			boolean flag = event.getWorld().getBiome(new BlockPos(entity)) == RegisterBiomes.PINK_TREE;
			
			if(flag)
			{
				entity.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegisterItems.WHITE_KATANA));
			}
			
			/*if(rand.nextInt(5) == 0)
			{
				event.setCanceled(true);
				EntityHZombie sp = new EntityHZombie(event.getWorld());
				sp.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				if(flag)
				{
					sp.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegisterItems.WHITE_KATANA));
				}
				
				if(rand.nextInt(5) == 0)
				{
					sp.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(RegisterItems.TUTU));
				}
				
				event.getWorld().spawnEntity(sp);
			}
			else if(rand.nextInt(15) == 0)
			{
				event.setCanceled(true);
				EntitySteroideZombie sp = new EntitySteroideZombie(event.getWorld());
				sp.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				if(flag)
				{
					sp.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegisterItems.WHITE_KATANA));
				}
				event.getWorld().spawnEntity(sp);
			}
		}
		
		if(event.getEntityLiving() instanceof EntitySkeleton && event.getEntityLiving().dimension == 0)
		{
			if(rand.nextInt(5) == 0)
			{
				event.setCanceled(true);
				EntityStupidSkeleton sp = new EntityStupidSkeleton(event.getWorld());
				sp.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				event.getWorld().spawnEntity(sp);
			}
			else if(rand.nextInt(15) == 0)
			{
				event.setCanceled(true);
				EntitySteroideSkeleton sp = new EntitySteroideSkeleton(event.getWorld());
				sp.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				event.getWorld().spawnEntity(sp);
			}
		}
		
		if(event.getEntityLiving() instanceof EntityCreeper && event.getEntityLiving().dimension == 0)
		{
			if(rand.nextInt(15) == 0)
			{
				event.setCanceled(true);
				EntitySteroideCreeper sp = new EntitySteroideCreeper(event.getWorld());
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				event.getWorld().spawnEntity(sp);
			}
		}*/
	}
		
		if(event.getEntityLiving() instanceof EntityCreeper && event.getEntityLiving().dimension == 0)
		{
			if(rand.nextInt(100) == 0)
			{
				EntityMosquito sp = new EntityMosquito(event.getWorld());
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				event.getWorld().spawnEntity(sp);
			}
		}
		
		if(event.getEntityLiving() instanceof EntityBat && event.getEntityLiving().dimension == MathsDims.Banana.getId())
		{
			event.setCanceled(true);
			int r = rand.nextInt(1000);
			if(r < 200 && r != 0)
			{
				EntityMosquito sp = new EntityMosquito(event.getWorld());
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				event.getWorld().spawnEntity(sp);
			}
			else if(r == 0)
			{
				EntityMoustiquator sp = new EntityMoustiquator(event.getWorld());
				sp.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
				event.getWorld().spawnEntity(sp);
			}
		}
	}
	
	private void setItem(EntityFishHook entity, World world, ItemStack stack)
	{
		EntityItem item = new EntityItem(world, entity.posX, entity.posY, entity.posZ, stack);
        double d0 = entity.getAngler().posX - entity.posX;
        double d1 = entity.getAngler().posY - entity.posY;
        double d2 = entity.getAngler().posZ - entity.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        double d4 = 0.1D;
        item.motionX = d0 * 0.1D;
        item.motionY = d1 * 0.1D + (double)MathHelper.sqrt(d3) * 0.08D;
        item.motionZ = d2 * 0.1D;
        world.spawnEntity(item);
        entity.getAngler().world.spawnEntity(new EntityXPOrb(entity.getAngler().world, entity.getAngler().posX, entity.getAngler().posY + 0.5D, entity.getAngler().posZ + 0.5D, new Random().nextInt(6) + 1));
	}
	
	private void catchEntity(EntityFishHook entity, World world, Entity item)
	{
		item.setPosition(entity.posX, entity.posY, entity.posZ);
        double d0 = entity.getAngler().posX - entity.posX;
        double d1 = entity.getAngler().posY - entity.posY;
        double d2 = entity.getAngler().posZ - entity.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        double d4 = 0.1D;
        item.motionX = d0 * 0.1D;
        item.motionY = d1 * 0.1D + (double)MathHelper.sqrt(d3) * 0.08D;
        item.motionZ = d2 * 0.1D;
        world.spawnEntity(item);
        entity.getAngler().world.spawnEntity(new EntityXPOrb(entity.getAngler().world, entity.getAngler().posX, entity.getAngler().posY + 0.5D, entity.getAngler().posZ + 0.5D, new Random().nextInt(6) + 1));
	}
	
	//TODO fishing manager
	@SubscribeEvent
	public void fishingManager(ItemFishedEvent event)
	{
		Item item = event.getEntityPlayer().getHeldItemMainhand().getItem();
		ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
		
		if(item instanceof IModedFishingRod && event.getEntityPlayer().dimension != MathsDims.Sirojus.getId())
		{
			if(item == RegisterItems.TFOSIUM_ROD)
			{
				ContractManagerServer.addProgress(event.getEntityPlayer(), RegisterContracts.KURAYUM_ROD, 1);
			}
			if(new Random().nextInt(5) < 2)
			{
				Random rand = new Random();
				if(((IModedFishingRod) item).getTier() >= 0)
				{
					if(((IModedFishingRod) item).getTier() >= 1 && rand.nextBoolean())
					{
						int r = new Random().nextInt(5701 * 4);
						
						r = r/(1 + Math.min(EnchantmentHelper.getFishingLuckBonus(stack), 3));
						
						if(r < 400 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.SQUID_LAUNCHER));
							event.setCanceled(true);
						}
						else if(r < 800 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(Item.getByNameOrId("mathsmod:tfosium_helmet")));
							event.setCanceled(true);
						}
						else if(r < 1200 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.GIPSY_STRENGHT));
							event.setCanceled(true);
						}
						else if(r < 1400 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.TFOSIUM_ORE));
							event.setCanceled(true);
						}
						else if(r < 1800 && ((IModedFishingRod) item).getTier() >= 1)
						{
							ItemStack stack2 = new ItemStack(Item.getByNameOrId("mathsmod:silver_helmet"));
							stack2.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 4);
							stack2.addEnchantment(net.minecraft.init.Enchantments.UNBREAKING, 3);
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, stack2);
							event.setCanceled(true);
						}
						else if(r < 2200 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.IRON_SKULL));
							event.setCanceled(true);
						}
						else if(r < 2400 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.HOOD));
							event.setCanceled(true);
						}
						else if(r < 2600 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.CHAPKA));
							event.setCanceled(true);
						}
						else if(r < 2800 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.KEPI));
							event.setCanceled(true);
						}
						else if(r < 3000 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.BLACK_HAT));
							event.setCanceled(true);
						}
						else if(r < 3100 && ((IModedFishingRod) item).getTier() >= 0)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.BELUGA_SADDLE));
							event.setCanceled(true);
						}
						else if(r < 3200 && ((IModedFishingRod) item).getTier() >= 1)
						{
							ItemStack stack2 = new ItemStack(RegisterItems.VODKA);
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, stack2);
							event.setCanceled(true);
						}
						else if(r < 3900 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(Item.getByNameOrId("mathsmod:silver_sword")));
							event.setCanceled(true);
						}
						else if(r < 4100 && ((IModedFishingRod) item).getTier() >= 1)
						{
							EntitySkeleton entity = new EntitySkeleton(event.getEntityPlayer().world);
							entity.setCustomNameTag("squelepute");
							entity.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.WHEAT_SEEDS));
							entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 10000));
							entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 10000));
							this.catchEntity(event.getHookEntity(), event.getEntityPlayer().world, entity);
							event.setCanceled(true);
						}
						else if(r < 4200 && ((IModedFishingRod) item).getTier() >= 1)
						{
							EntityShark entity = new EntityShark(event.getEntityPlayer().world);
							entity.setAttackTarget(event.getEntityPlayer());
							this.catchEntity(event.getHookEntity(), event.getEntityPlayer().world, entity);
							event.setCanceled(true);
						}
						else if(r < 4300 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(Item.getByNameOrId("mathsmod:tfosium_helmet")));
							event.setCanceled(true);
						}
						else if(r < 4600 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.HEART_FISH));
							event.setCanceled(true);
						}
						else if(r < 4700 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.ENDER_FISH));
							event.setCanceled(true);
						}
						else if(r < 5000 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.ARMOR_FISH));
							event.setCanceled(true);
						}
						else if(r < 5300 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.FIRE_FISH));
							event.setCanceled(true);
						}
						else if(r < 5500 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.MOSQUITO_JAR));
							event.setCanceled(true);
						}
						else if(r < 5700 && ((IModedFishingRod) item).getTier() >= 1)
						{
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.TUTU));
							event.setCanceled(true);
						}
						else if(r == 5700 && ((IModedFishingRod) item).getTier() >= 1)
						{
							ItemStack stack2 = new ItemStack(Blocks.DIRT);
							stack2.addEnchantment(net.minecraft.init.Enchantments.FIRE_ASPECT, 2);
							stack2.setStackDisplayName(TextFormatting.BLUE + "" + TextFormatting.BOLD + "the Holy dirt");
							this.setItem(event.getHookEntity(), event.getEntityPlayer().world, stack2);
							event.setCanceled(true);
						}
					}
					else if(new Random().nextInt(MathHelper.clamp(100 - 4 * EnchantmentHelper.getFishingLuckBonus(stack), 1, 500)) == 0)
					{
						this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.KB800));
						event.setCanceled(true);
					}
					else if(new Random().nextInt(MathHelper.clamp(100 - 10 * EnchantmentHelper.getFishingLuckBonus(stack), 1, 100)) == 0)
					{
						this.catchEntity(event.getHookEntity(), event.getEntityPlayer().world, new EntityGuardian(event.getEntityPlayer().world));
						event.setCanceled(true);
					}
					else if(new Random().nextInt(MathHelper.clamp(70 - 3 * EnchantmentHelper.getFishingLuckBonus(stack), 1, 100)) == 0)
					{
						this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.SILVER_ORE));
						event.setCanceled(true);
					}
					else if(new Random().nextInt(MathHelper.clamp(30 - 2 * EnchantmentHelper.getFishingLuckBonus(stack), 1, 60)) == 0)
					{
						ItemStack stack2 = new ItemStack(RegisterItems.BAT);
						stack2.setStackDisplayName(TextFormatting.DARK_RED + "Bonk");
						this.setItem(event.getHookEntity(), event.getEntityPlayer().world, stack2);
						event.setCanceled(true);
					}
					else if(new Random().nextInt(MathHelper.clamp(20 - 1 * EnchantmentHelper.getFishingLuckBonus(stack), 1, 40)) == 0)
					{
						this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(Items.DIAMOND));
						event.setCanceled(true);
					}
					else if(new Random().nextInt(6) == 0)
					{
						this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(RegisterItems.BLUMON));
						event.setCanceled(true);
					}
				}
			}
		}
		else if(event.getEntityPlayer().dimension == MathsDims.Sirojus.getId())
		{
			this.setItem(event.getHookEntity(), event.getEntityPlayer().world, new ItemStack(Blocks.PACKED_ICE));
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onKnockback(LivingKnockBackEvent event)
	{
		if(event.getEntityLiving().isPotionActive(RegisterPotions.CHARGE))
		{
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void dropsEvent(LivingDropsEvent event) 
	{
		if(event.getEntityLiving().getEntityData() != null && event.getEntityLiving().getEntityData().hasKey("manipulation_owner"))
		{
			event.setCanceled(true);
			return;
		}
		
		if(event.getSource() != null && event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			
			if((player.getHeldItemMainhand().getItem() == RegisterItems.SACRIFICE_DAGGER || SpellManager.hasSpell(player, SpellRegistry.MOB_MANIPULATION)) && event.getEntityLiving() instanceof EntityLiving)
			{
				ItemStack stack = new ItemStack(RegisterItems.MOB_SOUL);
				ItemMobSoul.setEntity(stack, event.getEntityLiving());
				event.getEntityLiving().entityDropItem(stack, 0.2F);
			}
		}
		/*if(event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer killer = (EntityPlayer) event.getSource().getTrueSource();
			EntityLivingBase vic = event.getEntityLiving();
			
			if(killer.getHeldItemMainhand().getItem() == RegisterItems.HEAD_CUTTER)
			{
				if(vic instanceof EntityCreeper)
				{
					ItemStack stack = new ItemStack(Blocks.SKULL, 1, 4);
					event.
				}
				else if(vic instanceof EntityWitherSkeleton)
				{
					ItemStack stack = new ItemStack(Blocks.SKULL, 1, 1);
					vic.entityDropItem(stack, 0.2F);
				}
				else if(vic instanceof EntitySkeleton)
				{
					ItemStack stack = new ItemStack(Blocks.SKULL, 1, 0);
					vic.entityDropItem(stack, 0.2F);
				}
				else if(vic instanceof EntityZombie)
				{
					ItemStack stack = new ItemStack(Blocks.SKULL, 1, 2);
					vic.entityDropItem(stack, 0.2F);
				}
				else if(killer instanceof EntityPlayer)
				{
					ItemStack stack = new ItemStack(Blocks.SKULL, 1, 3);
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), killer.getGameProfile()));
					stack.writeToNBT(nbt);
					vic.entityDropItem(stack, 0.2F);
				}
				else if(vic instanceof EntityDragon)
				{
					ItemStack stack = new ItemStack(Blocks.SKULL, 1, 5);
					vic.entityDropItem(stack, 0.2F);
				}
			}
		}*/
	}
	

	//TODO crash fix
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) 
	{
		if(event.getWorld() instanceof WorldServer)
		{
			WorldServer serv = (WorldServer) event.getWorld();
			CopyOnWriteArraySet<EntityTrackerEntry> entries = new CopyOnWriteArraySet<EntityTrackerEntry>();
			ObfuscationReflectionHelper.setPrivateValue(EntityTracker.class, serv.getEntityTracker(), entries, 2);
		}
	}
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event) 
	{
		if(rand.nextInt(300) == 0 && event.world.loadedEntityList.size() < 10000 && !event.world.isDaytime() && event.world.getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			World world = event.world;
			for(EntityPlayer player : world.playerEntities)
			{
				for(int i = 0; i < 2; ++i)
				{
					float dis = rand.nextFloat() * 35 + 40;
					Vec3d vec = UtilsVoids.laser(dis, rand.nextFloat() * 360 - 180, 0).add(player.getPositionVector());
					BlockPos pos = new BlockPos(vec);
					
					List<Biome.SpawnListEntry> list = world.getBiome(pos).getSpawnableList(EnumCreatureType.MONSTER);
					if(!list.isEmpty())
					{
						Biome.SpawnListEntry biome$spawnlistentry = WeightedRandom.getRandomItem(this.rand, list);
						
						for(int j = 0; j < biome$spawnlistentry.minGroupCount + rand.nextInt(biome$spawnlistentry.maxGroupCount); ++j)
						{
								
							pos = world.getHeight(pos.add(rand.nextInt(5) - 2, 0, rand.nextInt(5) - 2));
							
							EntityLiving entity;
							try 
							{
								entity = biome$spawnlistentry.newInstance(world);
								entity.setPosition(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
								if(entity.getCanSpawnHere() && world.checkLight(pos) && ForgeEventFactory.doSpecialSpawn(entity, event.world, (float)(pos.getX() + 0.5D), pos.getY(), (float)(pos.getZ() + 0.5D)))
								{
									entity.onInitialSpawn(world.getDifficultyForLocation(pos), null);
									entity.setPositionAndRotation(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, rand.nextFloat() * 360 - 180, 0);
									world.spawnEntity(entity);
								}
								
							} 
							catch (Exception e) 
							{
							}
							
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
        if(event.isWasDeath())
        {
            if(event.getOriginal().hasCapability(CapabilitiesManager.CONTRACTS, null))
            {
                CapabilityContracts Old = event.getOriginal().getCapability(CapabilitiesManager.CONTRACTS, null);
                CapabilityContracts New = event.getEntityPlayer().getCapability(CapabilitiesManager.CONTRACTS, null);
                New.setContracts(Old.getContracts(event.getEntityPlayer()), event.getEntityPlayer());
            }
            
            if(SpellManager.spellExists(event.getOriginal()))
			{
				SpellManager.copySpells(event.getOriginal(), event.getEntityPlayer());
			}
			else
			{
				SpellManager.giveRandomSpells(event.getEntityPlayer());
			}
        }
    }
	
	@SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
		if (event.getObject() instanceof EntityPlayer)
        {
	        event.addCapability(new ResourceLocation(Mathsmod.MODID, "contracts"), new CapabilityContracts());
        }
    }
	

	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event)
	{
		if(event.player != null && event.player instanceof EntityPlayerMP)
		{
			if(event.player.hasCapability(CapabilitiesManager.CONTRACTS, null))
			{
				//System.out.print("feur" + System.lineSeparator());
				HashMap<ContractsBase, Integer> map = event.player.getCapability(CapabilitiesManager.CONTRACTS, null).loadPlayerContracts(event.player);
				ServerHelperEvents.contracts.put(event.player.getName(), map);
			}
			
			if(SpellManager.spellExists(event.player))
			{
				SpellManager.loadSpells(event.player);
			}
			else
			{
				SpellManager.giveRandomSpells(event.player);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerDropItemOnDeath(PlayerDropsEvent event)
	{
		if(event.getEntityPlayer() != null && event.getEntityPlayer().world.getBlockState(new BlockPos(event.getEntityPlayer())).getBlock().getBlockHardness(event.getEntityPlayer().world.getBlockState(new BlockPos(event.getEntityPlayer())), event.getEntityPlayer().world, new BlockPos(event.getEntityPlayer())) >= 0)
		{
			BlockPos pos = new BlockPos(event.getEntityPlayer());
			while(!event.getEntityPlayer().world.getBlockState(pos).getBlock().isReplaceable(event.getEntityPlayer().world, pos))
			{
				pos = pos.up();
				
				if(pos.getY() >= 256)
				{
					return;
				}
			}
			event.getEntityPlayer().world.setBlockState(pos, RegisterBlocks.TOMB.withRotation(RegisterBlocks.TOMB.getDefaultState(), Rotation.values()[this.rand.nextInt(4)]));
			if(event.getEntityPlayer().world.getTileEntity(pos) != null && event.getEntityPlayer().world.getTileEntity(pos) instanceof TileEntityTomb)
			{
				TileEntityTomb tomb = (TileEntityTomb) event.getEntityPlayer().world.getTileEntity(pos);
				for (int i = 0; i < 60; i++)
		        {
					if (event.getDrops().size() > i && !event.getDrops().get(i).getItem().isEmpty())
		            {
						tomb.setInventorySlotContents(i, event.getDrops().get(i).getItem());
		            }
		        }
			}
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onPlayerLeave(PlayerLoggedOutEvent event)
	{
		if(event.player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			//System.out.print("feur" + System.lineSeparator());
			event.player.getCapability(CapabilitiesManager.CONTRACTS, null).savePlayerContracts(event.player, ServerHelperEvents.contracts);
			
			ServerHelperEvents.contracts.remove(event.player.getName());
		}
	}
	
	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Post event)
	{
		OresGen.generateFlower(event.getWorld(), event.getRand(), 120, 3, 50, 90, Blocks.GRASS, RegisterBlocks.DENANIUM.getDefaultState(), event.getChunkX() * 16, event.getChunkZ() * 16);
		OresGen.generateFlower(event.getWorld(), event.getRand(), 60, 4, 50, 90, Blocks.GRASS, RegisterBlocks.EXPLODA.getDefaultState(), event.getChunkX() * 16, event.getChunkZ() * 16);
		OresGen.generateFlower(event.getWorld(), event.getRand(), 500, 2, 50, 90, Blocks.GRASS, RegisterBlocks.DIAMONA.getDefaultState(), event.getChunkX() * 16, event.getChunkZ() * 16);
	}
	
	@SubscribeEvent
	public void generateStructuresNiqueLesArbres(DecorateBiomeEvent.Pre event)
	{
		if(event.getWorld().provider.getDimension() == 0 && event.getWorld().getWorldType() != WorldType.FLAT && event.getWorld().getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES)
		{
			int i = rand.nextInt(20000);
			if(i < 100)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "underground_fortress/underground_fortress_base"), 0);
				BlockPos pos = new BlockPos(event.getChunkPos().x * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkPos().z * 16 + event.getWorld().rand.nextInt(16));
				
				pos = UtilsVoids.getHeightIgnoreBlocks(pos.getX(), pos.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE);
				
				if((event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND) && pos.getY() < event.getWorld().getSeaLevel() + 10)
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -12, 0), "r");
				}
			}
			else if(i < 200)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "cultist_place"), 0);
				BlockPos pos = new BlockPos(event.getChunkPos().x * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkPos().z * 16 + event.getWorld().rand.nextInt(16));

				pos = UtilsVoids.getHeightIgnoreBlocks(pos.getX(), pos.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE);
				
				if((event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND) && pos.getY() < event.getWorld().getSeaLevel() + 10)
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -1, 0), "r");
				}
			}
			else if(i < 400)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "cultist_church/cultist_church"), 0);
				BlockPos pos = new BlockPos(event.getChunkPos().x * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkPos().z * 16 + event.getWorld().rand.nextInt(16));
				if(true)
				{

					pos = UtilsVoids.getHeightIgnoreBlocks(pos.getX(), pos.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE);
					
					int rot = rand.nextInt(4);
					BlockPos offset = GenerationHelper.rotateBlockPos(new BlockPos(-12, -1, -16), rot);
					
					int maxDifAbs = 0;
					
					for(int x = 0; x < 2; x++)
					{
						for(int z = 0; z < 2; z++)
						{
							BlockPos offset2 = GenerationHelper.rotateBlockPos(new BlockPos(25 * x, 0, 33 * z), rot);
							BlockPos pos2 = pos.add(offset2);
							int y = UtilsVoids.getHeightIgnoreBlocks(pos2.getX(), pos2.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE).getY();
							if(Math.abs(y - pos.getY()) > maxDifAbs)
							{
								maxDifAbs = Math.abs(y - pos.getY());
							}
						}
					}
					
					if(maxDifAbs <= 1 && (event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.ROCK))
					{
						genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(offset), rot + "");
					}
				}
			}
			else if(i < 900)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "hunter_hut"), 0);
				BlockPos pos = new BlockPos(event.getChunkPos().x * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkPos().z * 16 + event.getWorld().rand.nextInt(16));
				
				pos = UtilsVoids.getHeightIgnoreBlocks(pos.getX(), pos.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE);
				
				int rot = rand.nextInt(4);
				int dy = 0;
				
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						BlockPos offset2 = GenerationHelper.rotateBlockPos(new BlockPos(15 * x, 0, 13 * z), rot);
						BlockPos pos2 = pos.add(offset2);
						int y = UtilsVoids.getHeightIgnoreBlocks(pos2.getX(), pos2.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE).getY();
						if(Math.abs(y - pos.getY()) > dy)
						{
							dy = Math.abs(y - pos.getY());
						}
					}
				}
				
				if(dy < 2 && (event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND) && pos.getY() < event.getWorld().getSeaLevel() + 10)
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -1, 0), rot + "");
				}
			}
			else if(i < 1400)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "ruined_tower/ruined_tower"), 0);
				BlockPos pos = new BlockPos(event.getChunkPos().x * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkPos().z * 16 + event.getWorld().rand.nextInt(16));
				
				pos = UtilsVoids.getHeightIgnoreBlocks(pos.getX(), pos.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE);
				
				int rot = rand.nextInt(4);
				int dy = 0;
				
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						BlockPos offset2 = GenerationHelper.rotateBlockPos(new BlockPos(17 * x, 0, 17 * z), rot);
						BlockPos pos2 = pos.add(offset2);
						int y = UtilsVoids.getHeightIgnoreBlocks(pos2.getX(), pos2.getZ(), event.getWorld(), Mathsmod.BLOCKS_THAT_I_HATE).getY();
						if(Math.abs(y - pos.getY()) > dy)
						{
							dy = Math.abs(y - pos.getY());
						}
					}
				}
				
				if(dy < 4 && (event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND))
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -4, 0), rot + "");
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPopulateChunkPre(PopulateChunkEvent.Pre event)
	{
		if(event.getWorld().provider.getDimension() == 0 && event.getWorld().getWorldType() != WorldType.FLAT && event.getWorld().getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES)
		{
			MathsmodGeneration.genererGrotteType1(event.getChunkX(), event.getChunkZ(), event.getWorld(), event.getGen());
			
			/*int i = rand.nextInt(1000);
			if(i < 30)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "underground_fortress/underground_fortress_base"), 0);
				BlockPos pos = new BlockPos(event.getChunkX() * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkZ() * 16 + event.getWorld().rand.nextInt(16));
				pos = new BlockPos(pos.getX(), event.getWorld().getHeight(pos.getX(), pos.getZ()), pos.getZ());
				if((event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND) && pos.getY() < event.getWorld().getSeaLevel() + 10)
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -12, 0), "r");
				}
			}
			else if(i < 100)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "cultist_place"), 0);
				BlockPos pos = new BlockPos(event.getChunkX() * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkZ() * 16 + event.getWorld().rand.nextInt(16));
				pos = new BlockPos(pos.getX(), event.getWorld().getHeight(pos.getX(), pos.getZ()), pos.getZ());
				if((event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND) && pos.getY() < event.getWorld().getSeaLevel() + 10)
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -1, 0), "r");
				}
			}
			else if(i < 400)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "cultist_church/cultist_church"), 0);
				BlockPos pos = new BlockPos(event.getChunkX() * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkZ() * 16 + event.getWorld().rand.nextInt(16));
				if(true)
				{
					pos = new BlockPos(pos.getX(), event.getWorld().getHeight(pos.getX(), pos.getZ()), pos.getZ());
					int rot = rand.nextInt(4);
					BlockPos offset = GenerationHelper.rotateBlockPos(new BlockPos(-12, -1, -16), rot);
					
					int maxDifAbs = 0;
					
					for(int x = 0; x < 2; x++)
					{
						for(int z = 0; z < 2; z++)
						{
							BlockPos offset2 = GenerationHelper.rotateBlockPos(new BlockPos(25 * x, 0, 33 * z), rot);
							BlockPos pos2 = pos.add(offset2);
							int y = event.getWorld().getHeight(pos2.getX(), pos2.getZ());
							if(Math.abs(y - pos.getY()) > maxDifAbs)
							{
								maxDifAbs = Math.abs(y - pos.getY());
							}
						}
					}
					
					if(maxDifAbs <= 1 && (event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.ROCK))
					{
						genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(offset), rot + "");
					}
				}
			}
			else if(i < 460)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "hunter_hut"), 0);
				BlockPos pos = new BlockPos(event.getChunkX() * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkZ() * 16 + event.getWorld().rand.nextInt(16));
				pos = new BlockPos(pos.getX(), event.getWorld().getHeight(pos.getX(), pos.getZ()), pos.getZ());
				int rot = rand.nextInt(4);
				int dy = 0;
				
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						BlockPos offset2 = GenerationHelper.rotateBlockPos(new BlockPos(15 * x, 0, 13 * z), rot);
						BlockPos pos2 = pos.add(offset2);
						int y = event.getWorld().getHeight(pos2.getX(), pos2.getZ());
						if(Math.abs(y - pos.getY()) > dy)
						{
							dy = Math.abs(y - pos.getY());
						}
					}
				}
				
				if(dy < 2 && (event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND) && pos.getY() < event.getWorld().getSeaLevel() + 10)
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -1, 0), rot + "");
				}
			}
			else if(i < 610)
			{
				StructureCustom genUF = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "ruined_tower/ruined_tower"), 0);
				BlockPos pos = new BlockPos(event.getChunkX() * 16 + event.getWorld().rand.nextInt(16), 70, event.getChunkZ() * 16 + event.getWorld().rand.nextInt(16));
				pos = new BlockPos(pos.getX(), event.getWorld().getHeight(pos.getX(), pos.getZ()), pos.getZ());
				int rot = rand.nextInt(4);
				int dy = 0;
				
				for(int x = 0; x < 2; x++)
				{
					for(int z = 0; z < 2; z++)
					{
						BlockPos offset2 = GenerationHelper.rotateBlockPos(new BlockPos(17 * x, 0, 17 * z), rot);
						BlockPos pos2 = pos.add(offset2);
						int y = event.getWorld().getHeight(pos2.getX(), pos2.getZ());
						if(Math.abs(y - pos.getY()) > dy)
						{
							dy = Math.abs(y - pos.getY());
						}
					}
				}
				
				if(dy < 4 && (event.getWorld().getBlockState(pos.down()).getMaterial() == Material.GRASS || event.getWorld().getBlockState(pos.down()).getMaterial() == Material.SAND))
				{
					genUF.generateSpecial(event.getWorld(), event.getWorld().rand, pos.add(0, -4, 0), rot + "");
				}
			}*/
		}
	}
	
	@SubscribeEvent
	public void onWorldGeneration(ChunkGeneratorEvent.ReplaceBiomeBlocks event)
	{
		if(event.getWorld().provider.getDimension() == 0 && event.getWorld().getWorldType() != WorldType.FLAT && event.getWorld().getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES)
		{
			MathsmodGeneration.generatePrettyMountains(event.getX(), event.getZ(), event.getWorld(), event.getGen(), event.getPrimer());
		}
	}
}
