package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityFestiveRocket;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemFestiveRocket extends ItemBaseBullet implements IGunCraft
{

	public ItemFestiveRocket() 
	{
		super("festive_rocket");
		this.setMaxStackSize(8);
	}

	@Override
	public EnumBulletType type() {
		return EnumBulletType.ROCKET;
	}
	
	public Entity getBullet(World world, EntityLivingBase shooter, ItemGun gun, ItemStack stack)
	{
		EntityFestiveRocket bullet;
		if(gun instanceof IRocketLauncher)
		{
			bullet = new EntityFestiveRocket(world, shooter, gun.getDamagesCaused(), ((IRocketLauncher) gun).explosionStrenght());
		}
		else
		{
			 bullet = new EntityFestiveRocket(world, shooter, gun.getDamagesCaused(), 4);
		}
		return bullet;
	}
	
	@Override
	public void shoot(World world, EntityLivingBase entityIn, ItemGun gun, boolean lchs)
	{
		Entity entity = this.getBullet(world, entityIn, gun, entityIn.getHeldItemMainhand());
		if(!entity.isDead && !world.isRemote)
		{
			world.spawnEntity(entity);
		}
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.FIREWORKS));
		list.set(1, new ItemStack(RegisterItems.ROCKET));
		
		return list;
	}

	@Override
	public int size() 
	{
		return 4;
	}
	
	public boolean isDestructive()
	{
		return false;
	}

	@Override
	public boolean displayRecipe() {
		return true;
	}

}