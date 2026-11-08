package com.mod.mathsmod.add.items.guns;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class ItemGrenadeLauncher extends ItemGun
{

	public ItemGrenadeLauncher(String name) 
	{
		super(name);
	}

	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		ItemStack stack = entityIn.getHeldItem(handIn);
		
		if(this.bulletUsed(stack, entityIn) != null && this.getUsed(stack) != 0)
		{
			super.onItemUsedByEntity(worldIn, entityIn, handIn);
		}
    }
	
	public EnumBulletType getBullet()
	{
		return EnumBulletType.GRENADE;
	}
	
	public ItemBaseBullet getFirstBullet()
    {
    	return (ItemBaseBullet) RegisterItems.GRENADE;
    }
	
	public boolean canBeUseByHunter()
    {
    	return false;
    }
	
	public float handMovementMultiplicator()
    {
    	return 2F;
    }
	
	@Override
	public boolean lowBulletFar()
	{
		return false;
	}

	@Override
	public int getReach()
	{
		return 10;
	}
	
	public SoundEvent getShotSound()
    {
    	return MathsSounds.GL_SOUND;
    }
	
	public abstract float getExplosionStrenght();
}
