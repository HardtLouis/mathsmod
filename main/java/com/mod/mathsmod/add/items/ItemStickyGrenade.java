package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityBasicGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityStickyGrenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ItemStickyGrenade  extends ItemGrenade {

	public ItemStickyGrenade() 
	{
		super("sticky_grenade");
		this.setMaxStackSize(4);
	}

	@Override
	public EntityGrenade grenade(World world, EntityLivingBase player) 
	{
		EntityStickyGrenade grenade = new EntityStickyGrenade(world, player);
		grenade.shoot(player, player.rotationPitch, player.rotationYaw, 0, 0.5F, 1);
		return grenade;
	}

}