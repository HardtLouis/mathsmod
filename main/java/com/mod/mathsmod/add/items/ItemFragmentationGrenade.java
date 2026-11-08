package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityFragmentationGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;
import com.mod.mathsmod.add.items.guns.EnumBulletType;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemFragmentationGrenade extends ItemGrenade {

	public ItemFragmentationGrenade() {
		super("fragmentation_grenade");
		this.setMaxStackSize(8);
	}

	@Override
	public EntityGrenade grenade(World world, EntityLivingBase player) {
		EntityFragmentationGrenade grenade = new EntityFragmentationGrenade(world, player);
		grenade.shoot(player, player.rotationPitch, player.rotationYaw, 0, 0.45F, 1);
		return grenade;
	}

}
