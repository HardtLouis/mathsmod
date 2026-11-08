package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNTTSniper extends ItemMachineGun implements IGunScope
{

	public ItemNTTSniper() 
	{
		super("ntt_sniper");
	}
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 2));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 2));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot")), 2));
		return list;
	}

	@Override
	protected int shootCadence()
	{
		return 27;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 9;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 40;
	}

	@Override
	public int getChargerSize() 
	{
		return 1;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) >= this.timeToFullScope() ? 0 : 45;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.3141593F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, 0.7330383F, 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.085F, 0.62F, -90, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		float i = 0;
		float y = 0;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload >= 0.5F)
			{
				y = (reload - 1F) * -2F;
			}
			if(reload <= 0.5F)
			{
				y = reload * 2F;
			}
			
			y *= 88 * 1.5F;
		}
		else
		{
		}
		
		return this.Animated(stack, player) ? new armsLoRo(-0.65F, -0.12F, 0.2F, -88 - y, -15, -i - 15) : null;
	}

	@Override
	public boolean scopeToShoot()
	{
		return true;
	}
	
	@Override
	protected float innacuracyOnShoot() 
	{
		return 0;
	}

	@Override
	protected int maxInnacuracy()
	{
		return 0;
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
		return 3;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 8 * Mathsmod.SNIPERS_MULTIPLIER;
	}
	
	public float HeadMultiplier()
	{
		return 1.5F;
	}

	@Override
	public int timeToUse() 
	{
		return 10;
	}

	@Override
	public int getReach()
	{
		return 1000;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LONG;
	}

	@Override
	public boolean autoMod() 
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getScope() 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/gui/sniper_scope.png");
	}

	@Override
	public boolean hadCustomGuiScope() 
	{
		return false;
	}
	
	public boolean changeMouseSensibilityOnScope()
    {
    	return true;
    }

	public boolean smoothScope()
	{
		return true;
	}
	
	public float smoothMultiplier()
	{
		return 1.3F;
	}
	
	@Override
	public boolean keepBaseScope(ItemStack stack) 
	{
		return false;
	}

	@Override
	public int timeToFullScope() 
	{
		return 6;
	}

	public float soundPitch(World worldIn)
    {
    	return 0.7F;
    }
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.GLOCK_SOUND;
	}
}
