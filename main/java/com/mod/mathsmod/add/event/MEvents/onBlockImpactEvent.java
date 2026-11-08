package com.mod.mathsmod.add.event.MEvents;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class onBlockImpactEvent extends Event
{
	
	private final Entity entity;
	private final World world;
	private final ItemStack stack;
	private final BlockPos pos;
	private float amount;
	
	public onBlockImpactEvent(@Nullable Entity entityIn, World worldIn, @Nullable ItemStack stackIn, BlockPos blockpos, float amountIn)
	{
		entity = entityIn;
		world = worldIn;
		stack = stackIn;
		pos = blockpos;
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
	
	public BlockPos getPos()
	{
		return pos;
	}
	
	public boolean isCancelable()
    {
        return true;
    }
	
	@Nullable
    public static boolean onBulletImpactEvent(@Nullable Entity entityIn, World worldIn, @Nullable ItemStack stackIn, BlockPos pos, float amountIn)
    {
		onBlockImpactEvent event = new onBlockImpactEvent(entityIn, worldIn, stackIn, pos, amountIn);
		MinecraftForge.EVENT_BUS.post(event);
        return !event.isCanceled();
    }
}