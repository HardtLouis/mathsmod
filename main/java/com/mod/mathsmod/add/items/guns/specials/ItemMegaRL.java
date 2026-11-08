package com.mod.mathsmod.add.items.guns.specials;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityNuclearRocket;
import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.IRocketLauncher;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ThreeFloat;

import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemMegaRL extends ItemGun implements IRocketLauncher
{

	public ItemMegaRL() 
	{
		super("nuclear_rocket_launcher");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	@Override
	public NonNullList<ItemStack> Craft()
	{
		return null;
	}

	@Override
	public float explosionStrenght() 
	{
		return 30;
	}

	@Override
	public int cooldown() 
	{
		return 0;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1.5F;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 0;
	}

	@Override
	public int getChargerSize() 
	{
		return 0;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 3;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 3;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 1;
	}

	@Override
	public float getBulletDrop() 
	{
		return 0;
	}

	@Override
	public float getPower() 
	{
		return 4;
	}

	@Override
	public float getDamagesCaused()
	{
		return Float.POSITIVE_INFINITY;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return 2;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-90F * 0.017453292F, 0, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return null;
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public int timeToUse() 
	{
		return 1;
	}

	@Override
	public boolean autoMod() 
	{
		return false;
	}

	@Override
	public boolean scopeToShoot() 
	{
		return true;
	}

	@Override
	public boolean lowBulletFar() 
	{
		return false;
	}

	@Override
	public int getReach() 
	{
		return 1000;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.ROCKET;
	}
	
	@Override
	public boolean displayRecipe()
	{
		return false;
	}
	
	public boolean canBeUseByHunter()
    {
    	return false;
    }
	
	public boolean ChargerEmpty(ItemStack stack)
    {
    	return false;
    }
	
	public static boolean getReloading(ItemStack stack) 
    {
    	return false;
    }
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	if(this.getUsed(stack) != 0)
		{
    		if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse())
            {
        		if(stack.getItem() instanceof ItemGun)
        		{
        			if(!worldIn.isRemote)
        			{
        				worldIn.spawnEntity(new EntityNuclearRocket(worldIn, entityIn, getDamagesCaused(), explosionStrenght()));
        			}
        			
    				if(!this.autoMod())
    	    		{
    	    			this.setReset(stack, false);
    	    		}
    				
    				this.onShoot(entityIn);
    				
    				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 1000, this.soundPitch(worldIn));
    				if(!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.isCreativeMode)
    				{
    					this.removeBulletInCharger(stack);
    				}
        			this.substractReload(stack, null, this.getReload(stack));
        			if(this.getShootDefault(stack) + this.innacuracyOnShoot() <= this.maxInnacuracy())
        			{
        				this.setShootDefault(stack, this.getShootDefault(stack) + this.innacuracyOnShoot());
        			}
        			if(this.getShootDefault(stack) + this.innacuracyOnShoot() > this.maxInnacuracy())
        			{
        				this.setShootDefault(stack, this.maxInnacuracy());
        			}
        			if(entityIn instanceof EntityPlayer)
        			{
        				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), this.cooldown(stack, (EntityPlayer) entityIn));
        			}
                }
            }
		}
    }
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
        }
    }
}
