package com.mod.mathsmod.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class MDamageSource {

	public static final DamageSource FLAME_THROWER = (new DamageSource("onFire")).setDamageBypassesArmor();
	public static final DamageSource GITAN = (new DamageSource("gitan")).setDamageBypassesArmor();
	public static final DamageSource SUICIDE = (new DamageSource("suicide")).setDamageBypassesArmor();
	
	public static DamageSource causeInfinityDamage(EntityPlayer player)
    {
        return new EntityDamageSource("infinity", player).setDamageBypassesArmor();
    }
	
	public static DamageSource causePowerStoneDamage(EntityPlayer player)
    {
        return new EntityDamageSource("power_stone", player).setDamageBypassesArmor();
    }
	
	public static DamageSource causeStompDamage(EntityLivingBase player)
    {
        return new EntityDamageSource("stomp", player).setDamageBypassesArmor();
    }
	
	public static DamageSource causeHead(EntityPlayer player)
    {
        return new EntityDamageSource("head", player).setDamageBypassesArmor();
    }
	
	public static DamageSourceGun causeThrownDamage(Entity entityIn)
    {
        return (DamageSourceGun) new DamageSourceGun("thrown", entityIn).setProjectile().setDamageBypassesArmor();
        
    }
	
}
