package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFruitPunch extends ItemFood
{

	public ItemFruitPunch()
	{
		super(6, 1F, false);
		this.setAlwaysEdible();
		this.setMaxDamage(9);
		this.setMaxStackSize(1).setCreativeTab(Mathsmod.MATHSMOD_TAB).setUnlocalizedName("fruit_punch").setRegistryName("fruit_punch");
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            stack.damageItem(1, entityLiving);
            entityplayer.getFoodStats().addStats(this, stack);
            //worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        
        return stack;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "when drank, make you able to punch really hard");
		tooltip.add(TextFormatting.YELLOW + "when drank with a mosquito jar on the left hand");
		tooltip.add(TextFormatting.YELLOW + "create something that will make you an entity's target");
    }
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        player.heal(1);
        player.addPotionEffect(new PotionEffect(RegisterPotions.FRUIT_PUNCH, 600));
        if(player.getHeldItemOffhand().getItem() == RegisterItems.MOSQUITO_JAR)
        {
        	player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(RegisterItems.JARATE));
        }
    }
	
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }
	
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 12;
    }
}
