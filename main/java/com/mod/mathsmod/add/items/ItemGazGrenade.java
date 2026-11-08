package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityGazGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ItemGazGrenade extends ItemGrenade {

	public ItemGazGrenade() 
	{
		super("gaz_grenade");
		this.setMaxStackSize(6);
	}

	@Override
	public EntityGrenade grenade(World world, EntityLivingBase player)
	{
		EntityGrenade grenade = new EntityGazGrenade(world, player);
		grenade.shoot(player, player.rotationPitch, player.rotationYaw, 0, 0.5F, 1);
		return grenade;
	}

}
