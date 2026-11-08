package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.event.MEvents.onBlockImpactEvent;
import com.mod.mathsmod.add.event.MEvents.onImpactEvent;
import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.IGunCraft;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemLoveBullet extends ItemBaseBullet implements IGunCraft
{

	public ItemLoveBullet() 
	{
		super("love_bullet");
	}

	@Override
	public EnumBulletType type() 
	{
		return EnumBulletType.SHOTGUN;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "shoot a friend to heal him with your own health");
    }
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.IRON_INGOT));
		list.set(4, new ItemStack(Items.IRON_INGOT));
		list.set(1, new ItemStack(Items.GUNPOWDER));
		list.set(5, new ItemStack(Items.GUNPOWDER));
		list.set(2, new ItemStack(Item.getItemFromBlock(Blocks.WOOL)));
		list.set(6, new ItemStack(Item.getItemFromBlock(Blocks.WOOL)));
		
		return list;
	}
	
	@Override
	public int size() 
	{
		return 24;
	}

	@Override
	public boolean displayRecipe()
	{
		return true;
	}
	
	public void onImpact(RayTraceResult result, World worldIn, Entity entityIn, Entity shotEntity, float rotationYaw, float rotationPitch, double posX, double posY, double posZ, ItemStack stack, ItemGun item, double basePosX, double basePosY, double basePosZ, boolean antiVehicles)
	{
		if(entityIn instanceof EntityLivingBase)
		{
			stack = ((EntityLivingBase) entityIn).getHeldItemMainhand();
		}
		
		float damagesIn = item.getDamagesCaused() * item.damageMultiplier(stack, entityIn, null);
		if(item.bulletUsed(stack, entityIn) != null)
		{
			damagesIn *= item.bulletUsed(stack, entityIn).bulletMultiplier(stack, item, entityIn);
		}
		
		if(onImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.entityHit, damagesIn) != 0)
		{
			damagesIn = onImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.entityHit, damagesIn);
			
			if(result.entityHit != null && result.entityHit != entityIn && !worldIn.isRemote)
			{
				if(entityIn != null && entityIn.getRidingEntity() != null && result.entityHit == entityIn.getRidingEntity())
				{
					return;
				}
				if(result.entityHit instanceof MultiPartEntityPart)
				{
					MultiPartEntityPart entity = (MultiPartEntityPart) result.entityHit;
					if(entity.parent instanceof EntityLivingBase)
					{
						if(entityIn != null && entityIn instanceof EntityLivingBase)
						{
							UtilsVoids.attackEntityFrom((EntityLivingBase) entityIn, DamageSource.GENERIC, 5.0F);
						}
						((EntityLivingBase) entity.parent).heal(8.0F);
					}
				}
				else if(result.entityHit instanceof EntityLivingBase)
				{
					if(entityIn != null && entityIn instanceof EntityLivingBase)
					{
						UtilsVoids.attackEntityFrom((EntityLivingBase) entityIn, DamageSource.GENERIC, 5.0F);
					}
					((EntityLivingBase) result.entityHit).heal(8.0F);
				}
				return;
			}
		}
	}

}
