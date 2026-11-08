package com.mod.mathsmod.add.Entity.Entity.VillagerProfessions;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class professionFrozen extends VillagerProfession
{

	public professionFrozen() 
	{
		super("mathsmod:frozen", "mathsmod:textures/entity/villager/frozen.png", "mathsmod:textures/entity/zombie_villager/zombie_frozen.png");
		new VillagerCareer(this, "frozen").addTrade(1, new professionFrozen.tradesLevel1());
	}
	
	public static class tradesLevel1 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			if(merchant instanceof Entity && ((Entity) merchant).dimension == MathsDims.Sirojus.getId())
			{
				for(int i = 0; i <= 12; ++i)
				{
					Enchantment enchantment = (Enchantment)Enchantment.REGISTRY.getRandomObject(random);
					ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
					ItemEnchantedBook.addEnchantment(stack, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
					recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 6 + random.nextInt(8)), stack));
				}
				
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 4 + random.nextInt(3)), new ItemStack(RegisterItems.FROZEN_SWORD)));
				recipeList.add(new MerchantRecipe(new ItemStack(RegisterItems.THORIUM_INGOT, 15 + random.nextInt(6)), new ItemStack(RegisterItems.POLARITE_INGOT)));
				
				
				Item it = Items.APPLE;
				
				for(Item item : Item.REGISTRY)
				{
					List<Item> list = Lists.newArrayList();
					if(item instanceof IGoldenAnimatedGun || item instanceof IGoldenWeapon)
					{
						list.add(item);
					}
					
					if(!list.isEmpty() && list.size() > 0)
					{
						it = list.get(random.nextInt(list.size()));
					}
				}
				
				recipeList.add(new MerchantRecipe(new ItemStack(RegisterItems.POLARITE_INGOT, 6), new ItemStack(it)));
			}
			else
			{
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2), new ItemStack(Blocks.ICE)));
			}
			
		}
	}
}
