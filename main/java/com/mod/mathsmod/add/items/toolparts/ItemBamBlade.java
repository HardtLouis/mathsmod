package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBamBlade extends ItemToolPart
{

	public ItemBamBlade() 
	{
		super("bam_blade");
	}

	@Override
	public EnumParts part() 
	{
		return EnumParts.BLADE;
	}

	@Override
	public float damageMultiplier()
	{
		return 0.8F;
	}

	@Override
	public float efficiencyMultiplier() 
	{
		return 1.0F;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		if(this.getMetal(itemStack) != null)
		{
			return this.getMetal(itemStack).bonusReach;
		}
		return 0;
	}
	
	public void hitEntity(EntityLivingBase attacker, EntityLivingBase target, World world, ItemStack stack, int part, int type)
	{
		if(this.getMetal(stack) != null)
		{
			this.getMetal(stack).effectOnHit(part, type, world, attacker, target, stack);
		}
		
		if(!attacker.isPotionActive(MobEffects.STRENGTH))
		{
			attacker.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 30, 1));
		}
		else if(attacker.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier() < 3)
		{
			attacker.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 30, attacker.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier() + 1));
		}
		else
		{
			attacker.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 30, 3));
		}
	}
	
	public void userHit(EntityLivingBase attacker, EntityLivingBase user, World world, ItemStack stack)
	{
		if(!attacker.isPotionActive(MobEffects.STRENGTH))
		{
			attacker.removePotionEffect(MobEffects.STRENGTH);
		}
	}

	@Override
	public float protectionBonus() 
	{
		return -2;
	}

}