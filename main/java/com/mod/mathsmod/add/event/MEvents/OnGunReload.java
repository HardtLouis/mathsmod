package com.mod.mathsmod.add.event.MEvents;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class OnGunReload extends Event
{
	private final EntityLivingBase entity;
	private final World world;
	private final ItemStack stack;
	private final ItemGun item;
	private boolean blocked;
	
	public OnGunReload(World worldIn, EntityLivingBase entityIn, ItemStack stackIn, ItemGun itemIn)
	{
		entity = entityIn;
		world = worldIn;
		stack = stackIn;
		item = itemIn;
	}
	
	public EntityLivingBase getEntityLiving()
	{
		return entity;
	}
	
	public void setBlocked()
	{
		blocked = true;
	}
	
	public boolean isBlocked()
	{
		return blocked;
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
	
	public boolean isCancelable()
    {
        return true;
    }
	
}
