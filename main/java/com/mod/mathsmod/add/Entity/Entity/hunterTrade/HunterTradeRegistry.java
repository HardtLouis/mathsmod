package com.mod.mathsmod.add.Entity.Entity.hunterTrade;

import java.util.List;
import java.util.HashMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemGunLayer;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HunterTradeRegistry 
{
	public static final HashMap<ItemStack, ItemStack> HUNTER_TRADES_PRICES = Maps.newHashMap();
	
	public static void addTrade(ItemStack result, ItemStack price)
	{
		HUNTER_TRADES_PRICES.put(result, price);
	}
	
	public static void registerTrades()
	{
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.BASIC_SHOTGUN), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.R870), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.HUNTER_SHOTGUN), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.MINIGUN), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 9));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.MINIGUN), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 18));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.HUNTER_SHOTGUN), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 5));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.THOMPSON), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.MEDIC_S_SHOTGUN), new ItemStack(Item.getByNameOrId("mathsmod:thorium_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.FPC), new ItemStack(RegisterItems.BUFFED_BANANA));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.CARBINE), new ItemStack(Item.getByNameOrId("mathsmod:silver_ingot"), 5));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.BASIC_SIGHT), new ItemStack(Items.GOLD_INGOT, 3));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.BASIC_SOUND_MUFFLER), new ItemStack(Items.GOLD_INGOT, 3));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.HALF_MUFFLER), new ItemStack(Items.GOLD_INGOT, 3));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.AMMO_BOX), new ItemStack(Items.GOLD_INGOT, 3));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.MEDIGUN), new ItemStack(Item.getByNameOrId("mathsmod:silver_ingot"), 3));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.BEAR_TRAP), new ItemStack(Items.GOLD_INGOT, 3));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.DEAGLE), new ItemStack(Item.getByNameOrId("mathsmod:silver_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.CANARDIER), new ItemStack(Item.getByNameOrId("mathsmod:silver_ingot"), 6));
		HunterTradeRegistry.addTrade(new ItemStack(RegisterItems.BANANA_PORTAL_FRAME), new ItemStack(RegisterItems.HELL_FLOWER, 2));
		
		for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemGunLayer)
    		{
    			HunterTradeRegistry.addTrade(new ItemStack(item), new ItemStack(Items.GOLD_INGOT, 1));
    		}
    	}
	}
	
	public static List<Item> randomLootsItem()
	{
		List<Item> items = Lists.newArrayList();
		items.add(Items.ROTTEN_FLESH);
		items.add(Items.BONE);
		items.add(Items.ENDER_PEARL);
		items.add(Items.PORKCHOP);
		items.add(Items.BEEF);
		items.add(Items.GUNPOWDER);
		items.add(Items.SPIDER_EYE);
		items.add(RegisterItems.IRON_SHARD);
		items.add(RegisterItems.EYE_OF_BLAL);
		items.add(RegisterItems.ICE_ROD);
		items.add(RegisterItems.NIGHT_SHARD);
		items.add(RegisterItems.CRAB_CLAW);
		items.add(RegisterItems.BANANA);
		return items;
	}
}
