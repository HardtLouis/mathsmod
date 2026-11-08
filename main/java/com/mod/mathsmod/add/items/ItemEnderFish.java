package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemEnderFish extends Item 
{
	public ItemEnderFish()
	{
		this.setUnlocalizedName("ender_fish").setRegistryName("ender_fish").setMaxStackSize(6);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(handIn == EnumHand.MAIN_HAND)
		{
			boolean b = false;
			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, worldIn, playerIn, 50))
			{
				if(entity != playerIn)
				{
					Vec3d vec = UtilsVoids.laser(entity.getDistance(playerIn), playerIn.rotationYaw, playerIn.rotationPitch).add(playerIn.getPositionVector()).addVector(0, playerIn.getEyeHeight(), 0);
					if(!UtilsVoids.getHitEntities(worldIn, playerIn, vec, 50).isEmpty())
					{
						b = true;
						Vec3d vec2 = UtilsVoids.laser(-3, entity.rotationYaw, 0).add(entity.getPositionVector()).addVector(0, entity.getEyeHeight(), 0);
						playerIn.rotationYaw = entity.rotationYaw;
						playerIn.setPositionAndUpdate(vec2.x, vec2.y, vec2.z);
						playerIn.getCooldownTracker().setCooldown(this, 600);
						playerIn.addPotionEffect(new PotionEffect(RegisterPotions.INVUL, 60));
						playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100));
					}
				}
			}
			
			if(!b && !worldIn.isRemote)
			{
				playerIn.sendStatusMessage(new TextComponentString("right click on an entity to teleport behind it"), true);
			}
			
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("need a tfosium fishing rod");
		tooltip.add("");
		tooltip.add(TextFormatting.YELLOW + "on right click on an entity, teleports behind it");
		tooltip.add(TextFormatting.YELLOW + "and gives you invulnerability and slowness");
		tooltip.add("right click with it on a thorium");
		tooltip.add("block with a bone block under");
		tooltip.add("to spawn a boss");
    }
	
}
