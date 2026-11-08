package com.mod.mathsmod.add.crafting;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MeltingRegistry
{
	private static final HashMap<Item, Molten_metal> MELTING_PROPERTIES = Maps.newHashMap();
	private static final HashMap<String, alloy> ALLOY_PROPERTIES = Maps.newHashMap();
	public static HashMap<Item, Integer> METALIC_ITEMS = Maps.newHashMap();
	private static final List<String> ORDER = Lists.newArrayList();
	
	public static void registerMelting(Item item, Molten_metal metalProp)
	{
		metalProp.setItem(item);
		MELTING_PROPERTIES.put(item, metalProp);
		ORDER.add(item.getRegistryName().toString());
	}
	
	public static void registerAlloy(Item item1, Item item2, Molten_metal metalProp)
	{
		if(!ORDER.contains(item1.getRegistryName().toString()))
		{
			ORDER.add(item1.getRegistryName().toString());
		}
		
		if(!ORDER.contains(item2.getRegistryName().toString()))
		{
			ORDER.add(item2.getRegistryName().toString());
		}
		
		String str = item1.getRegistryName().toString() + "%" + item2.getRegistryName().toString();
		
		if(ORDER.indexOf(item1.getRegistryName().toString()) > ORDER.indexOf(item2.getRegistryName().toString()))
		{
			str = item2.getRegistryName().toString() + "%" + item1.getRegistryName().toString();
		}
		
		ALLOY_PROPERTIES.put(str, new alloy(metalProp, new Item[] {item1, item2}));
	}
	
	public static HashMap<Item, Molten_metal> getMeltingProperties()
	{
		return MELTING_PROPERTIES;
	}
	
	public static alloy getAlloy(String str1, String str2)
	{
		if(!ORDER.contains(str1) || !ORDER.contains(str2))
		{
			return null;
		}
		else
		{
			String str = str1 + "%" + str2;
			
			if(ORDER.indexOf(str1) > ORDER.indexOf(str2))
			{
				str = str2 + "%" + str1;
			}
			
			return ALLOY_PROPERTIES.get(str);
		}
	}
	
	public static HashMap<String, alloy> getAlloyMap()
	{
		return ALLOY_PROPERTIES;
	}
	
	public static Molten_metal getMetal(String str)
	{
		if(str != null)
		{
			if(str.contains("%"))
			{
				return getAlloy(str.split("%")[0], str.split("%")[1]).result;
			}
			else
			{
				Item item = Item.getByNameOrId(str);
				return MELTING_PROPERTIES.get(item);
			}
		}
		return null;
	}
	
	public static void generateAlloyMaterials()
	{
		HashMap<Item, Molten_metal> map = getMeltingProperties();
		for(int i = 0; i < map.values().size(); ++i)
		{
			for(int j = 0; j < i; ++j)
			{
				Molten_metal metal1 = map.get((Item) map.keySet().toArray() [i]);
				Molten_metal metal2 = map.get((Item) map.keySet().toArray() [j]);
				
				if(metal1 != metal2 && metal1.canMakeAlloyWith(metal2) && metal2.canMakeAlloyWith(metal1))
				{
					Color color1 = new Color(metal1.color);
					Color color2 = new Color(metal2.color);
					int r = (color1.getRed() + color2.getRed()) / 2;
					int g = (color1.getGreen() + color2.getGreen()) / 2;
					int b = (color1.getBlue() + color2.getBlue()) / 2;
					
					int harvestlvl = Math.max(metal1.level, metal2.level);
					float attackdam = Math.max(metal1.attack_damages, metal2.attack_damages);
					float efficiency = Math.max(metal1.efficiency, metal2.efficiency);
					float armorprotection0 = Math.max(metal1.armor_protection[0], metal2.armor_protection[0]);
					float armorprotection1 = Math.max(metal1.armor_protection[1], metal2.armor_protection[1]);
					float armorprotection2 = Math.max(metal1.armor_protection[2], metal2.armor_protection[2]);
					float armorprotection3 = Math.max(metal1.armor_protection[3], metal2.armor_protection[3]);
					float[] armor_protection = new float[] {armorprotection0, armorprotection1, armorprotection2, armorprotection3};
					
					float bonusReach = Math.max(metal1.bonusReach, metal2.bonusReach);
					
					int tts = (metal1.ticks_to_solidify + metal2.ticks_to_solidify) / 2;
					
					int uses = (metal1.durability == 0 || metal2.durability == 0) ? 0 : Math.max(metal1.durability, metal2.durability);
					
					Molten_metal al = new Molten_metal(tts, new Color(r,g,b).getRGB(), harvestlvl, attackdam, efficiency, uses, bonusReach, armor_protection)
							{
								@Override
								public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
								{
									metal2.effectOnHit(part, type, world, attacker, entity, stack);
									metal1.effectOnHit(part, type, world, attacker, entity, stack);
								}
								
								@Override
								public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase player, BlockPos pos, ItemStack stack)
								{
									metal2.effectOnBreakBlock(part, type, world, player, pos, stack);
									metal1.effectOnBreakBlock(part, type, world, player, pos, stack);
								}
								
								@Override
								public float bonusHealth()
								{
									return Math.max(metal2.bonusHealth(), metal1.bonusHealth());
								}
								
								@Override
								public float explosionMultiplier()
								{
									return Math.min(metal2.explosionMultiplier(), metal1.explosionMultiplier());
								}
								
								@Override
								public void onArmorUpdate(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot)
								{
									metal2.onArmorUpdate(stack, entity, slot);
									metal1.onArmorUpdate(stack, entity, slot);
								}
								
								@Override
								public void onWearerHurt(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot, DamageSource source)
								{
									metal2.onWearerHurt(stack, entity, slot, source);
									metal1.onWearerHurt(stack, entity, slot, source);
								}
							};
							
					registerAlloy(metal1.ingot, metal2.ingot, al);
				}
			}
		}
		System.out.print(ALLOY_PROPERTIES.toString() + System.lineSeparator());
	}
	
	public static class Molten_metal
	{
		public final int level;
		public final int ticks_to_solidify;
		public final int color;
		public final float bonusReach;
		
		public final float attack_damages;
		public final float efficiency;
		public final int durability;
		
		public final float[] armor_protection;
		
		private Item ingot;
		
		public Molten_metal(int tts, int colorIn, int harvestLvl , float attack_damagesIn, float efficiencyIn, int durabilityIn, float bonusReachIn, float[] armorProtectionIn)
		{
			level = harvestLvl;
			ticks_to_solidify = tts;
			color = colorIn;
			
			attack_damages = attack_damagesIn;
			efficiency = efficiencyIn;
			durability = durabilityIn;
			ingot = Items.AIR;
			bonusReach = bonusReachIn;
			armor_protection = armorProtectionIn;
		}
		
		public void setItem(Item item)
		{
			ingot = item;
		}
		
		public Item getIngot()
		{
			return ingot;
		}
		
		public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
		{
		}
		
		public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase user, BlockPos pos, ItemStack stack)
		{
		}
		
		public boolean canMakeAlloyWith(Molten_metal metal)
		{
			return true;
		}
		
		public float explosionMultiplier()
		{
			return 1.0F;
		}
		
		public float bonusHealth()
		{
			return 0.0F;
		}
		
		public void onArmorUpdate(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot)
		{
			
		}
		
		public void onWearerHurt(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot, DamageSource source)
		{
			
		}
	}
	
	public static class alloy
	{
		public final Molten_metal result;
		public final Item[] items;
		
		public alloy(Molten_metal resultIn, Item[] itemsIn)
		{
			result = resultIn;
			items = itemsIn;
		}
	}
	
	public static class recipe
	{
		public final Item item;
		public final int cost;
		
		public recipe(Item itemIn, int costIn)
		{
			item = itemIn;
			cost = costIn;
		}
	}
}
