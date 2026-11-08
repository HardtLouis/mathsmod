package com.mod.mathsmod.add.items.guns;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelFAMAS;
import com.mod.mathsmod.add.items.guns.renderer.models.modelZip22;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemZip22 extends ItemFamas 
{
	public ItemZip22() 
	{
		super("zip22");
	}
    
    public SoundEvent getShotSound()
    {
    	return MathsSounds.THOMPSON_SOUND;
    }
    
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.BLUE + "One of the worst weapon");
    }
    
    @Override
	public int getChargerSize() {
		
		return 10;
	}
    
    public float getSoundPower()
    {
    	return 2F;
    }
    
    @Override
    public float soundPitch(World worldIn) 
    {
    	return 1.3F;
    }
    
    @Override
	public float getDamagesCaused()
    {
		return 2F;
	}
    
    @Override
    public EnumBulletType getBullet() {
		
		return EnumBulletType.LITTLE;
	}

	@Override
	public boolean lowBulletFar() {
		
		return true;
	}
	
	protected int getBurst()
	{
		return new Random().nextInt(3) == 0 ? 3 : 1;
	}
	
	protected int timeBetweenShots()
	{
		return 2;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() {
		
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(RegisterBlocks.SILVER_BLOCK));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		
		return list;
	}

	@Override
	public int getReach() 
	{
		return 40;
	}
	
	@Override
    protected String getDPS(ItemStack stack)
	{
    	float damagesOnBurst = (this.getDamagesCaused() * this.damageMultiplier(stack, null, null) * 1.3F);
		float damagePerSeconde = damagesOnBurst * 20 / (this.cooldown() + 1.3F * this.timeBetweenShots());
		damagePerSeconde /= 2;
		String dps = Float.toString(damagePerSeconde);
		if(dps.length() > 5)
		{
			dps = dps.substring(0, dps.length() - 4);
		}
		
		return dps;
	}

	@Override
	public boolean autoMod()
	{
		return false;
	}

	@Override
	public boolean hasTravel()
	{
		return false;
	}

	@Override
	protected int shootCadence()
	{
		return 4;
	}

	@Override
	public int timeToUse()
	{
		return 7;
	}
	
	@Override
	public boolean canEquipModifier()
	{
		return false;
	}
}
