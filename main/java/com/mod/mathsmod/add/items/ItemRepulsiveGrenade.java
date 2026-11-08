package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBasicGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRepulsiveGrenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ItemRepulsiveGrenade extends ItemGrenade
{

	boolean bool;
	
	public ItemRepulsiveGrenade(boolean fallDamages)
	{
		super(fallDamages ? "knockback_grenade" : "repulsive_grenade");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(4);
		bool = fallDamages;
	}
	
	public boolean isDestructive()
	{
		return false;
	}

	@Override
	public EntityGrenade grenade(World world, EntityLivingBase player) 
	{
		EntityRepulsiveGrenade grenade = new EntityRepulsiveGrenade(world, player);
		grenade.setUserId(player.getName());
		grenade.shoot(player, player.rotationPitch, player.rotationYaw, 0, 0.7F, 1);
		grenade.setDoesFallDamages(bool);
		return grenade;
	}

}
