package com.mod.mathsmod.add.Entity.Entity.objects;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMythicalItem extends EntityItem
{
	public EntityMythicalItem(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityMythicalItem(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}
	
	public EntityMythicalItem(World worldIn, double x, double y, double z, ItemStack stack)
	{
		super(worldIn, x, y, z, stack);
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(!source.isFireDamage() && !source.isExplosion() && source != DamageSource.CACTUS)
        {
        	return super.attackEntityFrom(source, amount);
        }
        else
        {
        	return false;
        }
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		if(this.isInLava())
		{
			this.motionY = 0.2F;
		}
	}
}
