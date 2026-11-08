package com.mod.mathsmod.add.Entity.Entity.VillagerProfessions;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class professionEnchanter extends VillagerProfession
{

	public professionEnchanter() 
	{
		super("mathsmod:enchanter", "mathsmod:textures/entity/villager/enchanter.png", "mathsmod:textures/entity/zombie_villager/zombie_enchanter.png");
		new VillagerCareer(this, "enchanter").addTrade(1, new professionEnchanter.tradesLevel1()).addTrade(2, new professionEnchanter.tradesLevel2())
		.addTrade(3, new professionEnchanter.tradesLevel3()).addTrade(4, new professionEnchanter.tradesLevel4()).addTrade(5, new professionEnchanter.tradesLevel5())
		.addTrade(6, new professionEnchanter.tradesLevel6());
	}

	public static class tradesLevel1 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2 + random.nextInt(3)), EnchantmentHelper.addRandomEnchantment(random, new ItemStack(Items.BOOK), 1, false)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2 + random.nextInt(3)), EnchantmentHelper.addRandomEnchantment(random, new ItemStack(Items.BOOK), 1, false)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2 + random.nextInt(3)), EnchantmentHelper.addRandomEnchantment(random, new ItemStack(Items.BOOK), 1, false)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 2 + random.nextInt(3)), EnchantmentHelper.addRandomEnchantment(random, new ItemStack(Items.BOOK), 1, false)));
		}
	}
	
	public static class tradesLevel2 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			recipeList.add(new MerchantRecipe(new ItemStack(Items.LEATHER, 3), new ItemStack(Items.BOOK, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.PAPER, 8), new ItemStack(Items.BOOK, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.BOOK, 10)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.BOOK, 5)));
		}
	}

	public static class tradesLevel3 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			for(int i = 0; i <= 3; ++i)
			{
				Enchantment enchantment = (Enchantment)Enchantment.REGISTRY.getRandomObject(random);
				ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
				ItemEnchantedBook.addEnchantment(stack, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 8 + random.nextInt(8)), stack));
			}
		}
	}
	
	public static class tradesLevel4 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			for(int i = 0; i <= 3; ++i)
			{
				Enchantment enchantment = (Enchantment)Enchantment.REGISTRY.getRandomObject(random);
				ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
				ItemEnchantedBook.addEnchantment(stack, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 6 + random.nextInt(8)), stack));
			}
		}
	}
	
	public static class tradesLevel5 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			for(int i = 0; i <= 7; ++i)
			{
				Enchantment enchantment = (Enchantment)Enchantment.REGISTRY.getRandomObject(random);
				ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
				ItemEnchantedBook.addEnchantment(stack, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, 4 + random.nextInt(8)), stack));
			}
		}
	}
	
	public static class tradesLevel6 implements ITradeList
	{
		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
		{
			recipeList.add(new MerchantRecipe(new ItemStack(Items.BOOK, 10), new ItemStack(Items.EMERALD, 1)));
		}
	}
}
