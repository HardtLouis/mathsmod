package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.add.Enchantments;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityUMissile;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemRocket extends ItemBaseBullet implements IGunCraft
{

	public ItemRocket() 
	{
		super("rocket");
		this.setMaxStackSize(4);
	}

	@Override
	public EnumBulletType type() {
		return EnumBulletType.ROCKET;
	}
	
	public Entity getBullet(World world, EntityLivingBase shooter, ItemGun gun, ItemStack stack)
	{
		EntityRocket bullet;
		
		float directMult = 1;
		float explMult = 1;
		
		if(gun instanceof IRocketLauncher)
		{
			bullet = new EntityRocket(world, shooter, gun.getDamagesCaused() * gun.damageMultiplier(shooter.getHeldItemMainhand(), shooter, null), ((IRocketLauncher) gun).explosionStrenght() * explMult);
			bullet.multiplyDamagesAndSpeed(1.0F, ((IRocketLauncher) gun).baseRockedSpeed());
		}
		else
		{
			bullet = new EntityRocket(world, shooter, gun.getDamagesCaused(), 4 * explMult);
		}
		
		bullet.setStack(stack);
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
		
		list.set(0, new ItemStack(Items.GUNPOWDER));
		list.set(1, new ItemStack(Items.IRON_INGOT));
		list.set(2, new ItemStack(Items.IRON_INGOT));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(4, new ItemStack(Items.COAL));
		list.set(5, new ItemStack(Items.IRON_INGOT));
		list.set(6, new ItemStack(Items.IRON_INGOT));
		list.set(7, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		
		return list;
	}

	@Override
	public int size() 
	{
		return 4;
	}

	@Override
	public boolean displayRecipe() {
		return true;
	}

	@Override
	public boolean isDestructive() 
	{
		return true;
	}
}
