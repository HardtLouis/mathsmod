package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;
import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGrenadeLauncher;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class ItemGrenade extends ItemBaseBullet
{

	public ItemGrenade(String name)
	{
		super(name);
	}
	
	public abstract EntityGrenade grenade(World world, EntityLivingBase player);
	
	public Entity getBullet(World world, EntityLivingBase shooter, ItemGun gun, ItemStack stack) {
		
		EntityGrenade grenade = this.grenade(world, shooter);
		if(gun instanceof ItemGrenadeLauncher)
		{
			grenade.setDamagesAndPower(gun.getDamagesCaused() * gun.damageMultiplier(shooter.getHeldItemMainhand(), shooter, null), ((ItemGrenadeLauncher) gun).getExplosionStrenght());
		}
		
		grenade.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0, gun.getPower(stack, shooter), (gun.getInnacuracy(stack, shooter) + ItemGun.getShootDefault(stack)) * gun.innacuracyMultiplier(stack, shooter));
		return grenade;
	}

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("right click to throw or can be used with a grenade launcher");
    }
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote && handIn == EnumHand.MAIN_HAND)
		{
			worldIn.spawnEntity(this.grenade(worldIn, (EntityPlayer) playerIn));
			itemstack.shrink(1);
			for(Item item : Item.REGISTRY)
			{
				if(item instanceof ItemGrenade)
				{
					((EntityPlayer) playerIn).getCooldownTracker().setCooldown(item, 20);
				}
			}
			
			playerIn.swingArm(handIn);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
	@Override
	public void shoot(World world, EntityLivingBase entityIn, ItemGun gun, boolean HunterLeftCanon) 
	{
		Entity entity = this.getBullet(world, entityIn, gun, entityIn.getHeldItemMainhand());
		if(!entity.isDead && !world.isRemote)
		{
			world.spawnEntity(entity);
		}
	}

	@Override
	public EnumBulletType type() 
	{
		return EnumBulletType.GRENADE;
	}
}
