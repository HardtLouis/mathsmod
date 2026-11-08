package com.mod.mathsmod.add.event.MEvents;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class onImpactEvent extends Event
{
	
	private final Entity entity;
	private final World world;
	private final ItemStack stack;
	private final Entity target;
	private float amount;
	
	public onImpactEvent(@Nullable Entity entityIn, World worldIn, @Nullable ItemStack stackIn, Entity targetIn, float amountIn)
	{
		entity = entityIn;
		world = worldIn;
		stack = stackIn;
		target = targetIn;
		amount = amountIn;
	}
	
	public Entity getEntity()
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
	
	public void setDamageAmount(float amountS)
	{
		amount = amountS;
	}
	
	public float getDamageAmount()
	{
		return amount;
	}
	
	public Entity getTarget()
	{
		return target;
	}
	
	public boolean isCancelable()
    {
        return true;
    }
	
	@Nullable
    public static float onBulletImpactEvent(@Nullable Entity entityIn, World worldIn, @Nullable ItemStack stackIn, Entity targetIn, float amountIn)
    {
		onImpactEvent event = new onImpactEvent(entityIn, worldIn, stackIn, targetIn, amountIn);
        return (MinecraftForge.EVENT_BUS.post(event) ? 0 : event.getDamageAmount());
    }
}