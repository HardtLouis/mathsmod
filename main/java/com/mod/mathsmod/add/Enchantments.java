package com.mod.mathsmod.add;

import com.mod.mathsmod.add.enchantments.EnchantmentInfinity;
import com.mod.mathsmod.add.enchantments.EnchantmentPower;
import com.mod.mathsmod.add.enchantments.EnchantmentSerial;
import com.mod.mathsmod.add.enchantments.EnchantmentSmelt;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemRocketLauncher;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;

public class Enchantments {

	public static EnumEnchantmentType GUNS = EnumHelper.addEnchantmentType("guns", (item)->item instanceof ItemGun);
	public static EnumEnchantmentType ROCKET_LAUNCHERS = EnumHelper.addEnchantmentType("rocket_launchers", (item)->item instanceof ItemGun && ((ItemGun) item).goodBulletType(RegisterItems.ROCKET));
	
	public static final Enchantment SMELT = new EnchantmentSmelt();
	public static final Enchantment INFINITY = new EnchantmentInfinity();
	public static final Enchantment POWER = new EnchantmentPower();
	public static final Enchantment SERIAL = new EnchantmentSerial();
}
