package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRepulsiveRocket;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemRepulsiveRocket extends ItemBaseBullet implements IGunCraft
{

	public ItemRepulsiveRocket() 
	{
		super("repulsive_rocket");
		this.setMaxStackSize(8);
	}

	@Override
	public EnumBulletType type() {
		return EnumBulletType.ROCKET;
	}
	
	public Entity getBullet(World world, EntityLivingBase shooter, ItemGun gun, ItemStack stack)
	{
		EntityRepulsiveRocket bullet;
		if(gun instanceof IRocketLauncher)
		{
			bullet = new EntityRepulsiveRocket(world, shooter, gun.getDamagesCaused(), ((IRocketLauncher) gun).explosionStrenght());
		}
		else
		{
			 bullet = new EntityRepulsiveRocket(world, shooter, gun.getDamagesCaused(), 4);
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
		
		list.set(0, new ItemStack(Items.ENDER_PEARL));
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