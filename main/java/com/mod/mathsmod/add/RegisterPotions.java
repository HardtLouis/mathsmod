package com.mod.mathsmod.add;

import com.mod.mathsmod.add.Potions.PotionEffectCharge;
import com.mod.mathsmod.add.Potions.PotionEffectDisappear;
import com.mod.mathsmod.add.Potions.PotionEffectExplosion;
import com.mod.mathsmod.add.Potions.PotionEffectFireRegeneration;
import com.mod.mathsmod.add.Potions.PotionEffectFireVulnerability;
import com.mod.mathsmod.add.Potions.PotionEffectFriendofHunters;
import com.mod.mathsmod.add.Potions.PotionEffectFruitPunch;
import com.mod.mathsmod.add.Potions.PotionEffectInvulnerable;
import com.mod.mathsmod.add.Potions.PotionEffectSlowFall;
import com.mod.mathsmod.add.Potions.PotionEffectUber;
import com.mod.mathsmod.add.Potions.PotionFallProtection;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class RegisterPotions {

	public static final Potion SLOWFALL = new PotionEffectSlowFall();
	public static final Potion INVUL = new PotionEffectInvulnerable();
	public static final Potion FALL_PROTECTION = new PotionFallProtection();
	public static final Potion FRIENDS_OF_HUNTERS = new PotionEffectFriendofHunters();
	public static final Potion FIRE_REGENERATION = new PotionEffectFireRegeneration();
	public static final Potion UBER = new PotionEffectUber();
	public static final Potion CHARGE = new PotionEffectCharge();
	public static final Potion DISAPPEAR = new PotionEffectDisappear();
	public static final Potion FRUIT_PUNCH = new PotionEffectFruitPunch();
	public static final Potion EXPLOSIVE_MAN = new PotionEffectExplosion();
	public static final Potion FIRE_VULNERABILITY = new PotionEffectFireVulnerability();
	
	public static final PotionType INVUL_TYPE = new PotionType("invulnerability", new PotionEffect[] {new PotionEffect(RegisterPotions.INVUL, 200)}).setRegistryName("invulnerability");
	public static final PotionType FIRE_REGEN_TYPE = new PotionType("fire_regeneration", new PotionEffect[] {new PotionEffect(RegisterPotions.FIRE_REGENERATION, 9600)}).setRegistryName("fire_regeneration");
	
	public static void registerPotionRecipes()
	{
		RegisterPotions.createPotion(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), INVUL_TYPE);
		RegisterPotions.createPotion(Item.getItemFromBlock(RegisterBlocks.DENANIUM), FIRE_REGEN_TYPE);
	}
	
	public static void createPotion(Item item, PotionType type)
	{
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD), new ItemStack(item), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), type));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), type), new ItemStack(Items.GUNPOWDER), PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), type));
		BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), type), new ItemStack(Items.DRAGON_BREATH), PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), type));
	}
}
