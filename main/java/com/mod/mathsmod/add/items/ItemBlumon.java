package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBlumon extends ItemFood
{

	public ItemBlumon()
	{
		super(20, 100, true);
		this.setUnlocalizedName("blumon").setRegistryName("blumon").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("need a tfosium fishing rod");
		tooltip.add("when eaten, gives invulnerability and slowness");
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        player.addPotionEffect(new PotionEffect(RegisterPotions.INVUL, 300, 2));
        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 2));
        player.getCooldownTracker().setCooldown(this, 1200);
    }
	
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 96;
    }
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
            
        }

        
        return stack;
    }
}
