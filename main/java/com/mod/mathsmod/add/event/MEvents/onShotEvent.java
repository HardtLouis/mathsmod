package com.mod.mathsmod.add.event.MEvents;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class onShotEvent extends Event
{
	
	private final EntityLivingBase entity;
	private final World world;
	private final ItemStack stack;
	private final ItemGun item;
	private final Entity bullet;
	
	public onShotEvent(EntityLivingBase entityIn, World worldIn, ItemStack stackIn, ItemGun itemIn, @Nullable Entity bulletIn)
	{
		entity = entityIn;
		world = worldIn;
		stack = stackIn;
		item = itemIn;
		bullet = bulletIn;
	}
	
	public EntityLivingBase getEntity()
	{
		return entity;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public ItemStack getStack()
	{
		return stack;
	}
	
	public ItemGun getItem()
	{
		return item;
	}
	
	@Nullable
	public Entity getBullet()
	{
		return bullet;
	}
	
	public boolean isCancelable()
    {
        return true;
    }
}
