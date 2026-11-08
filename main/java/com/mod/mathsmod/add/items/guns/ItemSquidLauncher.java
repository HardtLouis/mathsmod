package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocketSquid;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityThrowableStone;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemSquidLauncher extends ItemGun
{

	public ItemSquidLauncher()
	{
		super("squid_launcher");
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		return null;
	}

	@Override
	public int cooldown()
	{
		return 40;
	}

	@Override
	public float zoomOnUsed()
	{
		return 1;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 1;
	}

	@Override
	public int getChargerSize()
	{
		return 0;
	}

	@Override
	protected float innacuracyOnShoot()
	{
		return 6;
	}

	@Override
	protected int maxInnacuracy()
	{
		return 6;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 2;
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
		return 14;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return 0;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-90F * 0.017453292F, 0, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.02F, 0.7F, -90, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public int timeToUse() 
	{
		return 4;
	}

	@Override
	public boolean autoMod()
	{
		return true;
	}

	@Override
	public boolean scopeToShoot() 
	{
		return false;
	}

	@Override
	public boolean lowBulletFar() 
	{
		return false;
	}

	@Override
	public int getReach()
	{
		return 20;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LITTLE;
	}

	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		if(!worldIn.isRemote && handIn == EnumHand.MAIN_HAND)
		{
			if(entityIn instanceof EntityPlayer)
			{
				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(this, this.cooldown(entityIn.getHeldItemMainhand(), (EntityPlayer) entityIn));
			}
			
			worldIn.spawnEntity(new EntityRocketSquid(worldIn, entityIn, this.getDamagesCaused(), entityIn.rotationYaw, entityIn.rotationPitch));
		}
    }
}