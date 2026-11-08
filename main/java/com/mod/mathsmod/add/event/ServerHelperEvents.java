package com.mod.mathsmod.add.event;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTargetDrone;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.capabilities.CapabilitiesManager;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.event.MEvents.onShotEvent;
import com.mod.mathsmod.add.messages.MessageInfiniteBullet;
import com.mod.mathsmod.add.messages.MessageTransform;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.server_Proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;

public class ServerHelperEvents 
{
	
	int ticks;
	int max;
	int maxTicks;
	boolean activate;
	boolean init;
	
	public static HashMap<String, HashMap<ContractsBase, Integer>> contracts = Maps.newHashMap();


	@SubscribeEvent
	public void onAttackedEvent(LivingAttackEvent event)
	{
		if(event.getEntityLiving().getServer() != null && event.getEntityLiving().getServer().getWorld(0) != null && event.getEntityLiving() instanceof EntityPlayer)
		{
			WorldServer world = event.getEntityLiving().getServer().getWorld(0);
			EntityLivingBase entity = event.getEntityLiving();
			BlockPos pos = world.getSpawnPoint();
			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5, pos.getZ() + 0.5, entity) < entity.getServer().getSpawnProtectionSize())
			{
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onExplosed(ExplosionEvent event)
	{
		if(event.getWorld().getMinecraftServer() != null && event.getWorld().getMinecraftServer().getWorld(0) != null)
		{
			WorldServer world = event.getWorld().getMinecraftServer().getWorld(0);
			Explosion explosion = event.getExplosion();
			BlockPos pos = world.getSpawnPoint();
			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5, pos.getZ() + 0.5, explosion.getPosition()) < event.getWorld().getMinecraftServer().getSpawnProtectionSize())
			{
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onShoot(onShotEvent event)
	{
		if(event.getEntity() != null && event.getEntity().getServer() != null)
		{
			WorldServer world = event.getEntity().getServer().getWorld(0);
			EntityLivingBase entity = event.getEntity();
			BlockPos pos = world.getSpawnPoint();
			if(entity.getServer() != null && UtilsVoids.distanceIgnoreY(pos.getX() + 0.5, pos.getZ() + 0.5, entity) < entity.getServer().getSpawnProtectionSize())
			{
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event)
	{
		if(event.player != null && event.player instanceof EntityPlayerMP)
		{
			for(String names : server_Proxy.renderInfos.keySet())
			{
				for(String transforms : server_Proxy.renderInfos.get(names))
				{
					Mathsmod.network.sendTo(new MessageTransform(names, transforms, false), (EntityPlayerMP) event.player);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onContructing(EntityConstructing event)
	{
		if(event.getEntity() instanceof EntityTargetDrone || event.getEntity() instanceof EntityMissile || event.getEntity() instanceof EntityRocket || event.getEntity() instanceof EntityThrowable)
		{
			if(event.getEntity().getServer() != null)
			{
				WorldServer world = event.getEntity().getServer().getWorld(0);
				Entity entity = event.getEntity();
				BlockPos pos = world.getSpawnPoint();
				if(entity.getServer() != null && UtilsVoids.distanceIgnoreY(pos.getX() + 0.5, pos.getZ() + 0.5, entity) < entity.getServer().getSpawnProtectionSize())
				{
					entity.setDead();
				}
			}
		}
		
		if(event.getEntity() instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			
			if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
			{
				for(ContractsBase contr : player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).keySet())
				{
					System.out.print(contr.id + " : " + contr.getAdvancment(player) + System.lineSeparator());
				}
			}
		}
		
	}
	
	@SubscribeEvent
	public void onPlayerServerTick(PlayerTickEvent event)
	{
		if(!init)
		{
			activate = MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "clear lag", false).getBoolean();
			maxTicks = MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "clear lag time", 300).getInt();
			init = true;
		}
		
		if(activate)
		{
			World world = event.player.world;
			
			CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
			CopyOnWriteArrayList<Entity> cleared = new CopyOnWriteArrayList<Entity>();
			int count = 0;
			
			if(max == 0)
			{
				max = MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "entity limit", 300).getInt();
				
				if(max <= 0)
				{
					max = 200;
				}
			}
			
			if(entities.size() > max)
			{
				for(Entity entity : entities)
				{
					if(entity instanceof EntityItem)
					{
						cleared.add(entity);
						count += 1;
					}
				}
			}
			
			if(count > 50 || ticks != 0)
			{
				if(ticks == 0)
				{
					for(EntityPlayer player : world.playerEntities)
					{
						float time = ((float)maxTicks) / 20F;
						player.sendMessage(new TextComponentString("clear lag in " + time + " seconds"));
					}
				}
				if(ticks < maxTicks)
				{
					ticks += 1;
				}
				else
				{
					for(Entity entity : cleared)
					{
						entity.setDead();
					}
					ticks = 0;
				}
			}
		}
	}
}
