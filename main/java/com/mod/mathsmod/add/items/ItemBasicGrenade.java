package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityBasicGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBasicGrenade extends ItemGrenade {

	public ItemBasicGrenade() 
	{
		super("grenade");
		this.setMaxStackSize(8);
	}

	@Override
	public EntityGrenade grenade(World world, EntityLivingBase player) 
	{
		EntityBasicGrenade grenade = new EntityBasicGrenade(world, player);
		grenade.shoot(player, player.rotationPitch, player.rotationYaw, 0, 0.6F, 1);
		return grenade;
	}
}
