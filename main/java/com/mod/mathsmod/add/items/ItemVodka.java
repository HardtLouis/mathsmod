package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVodka extends Item
{
    public ItemVodka()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setUnlocalizedName("vodka").setRegistryName("vodka").setMaxStackSize(1);
        this.setMaxDamage(0);
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "drink it to be drunk, when drank with a kepi");
		tooltip.add(TextFormatting.YELLOW + "on your head, gives more strength as you drink more");
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	//if(this.getDamage(stack) < this.getMaxDamage())
    	{
    		entityLiving.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, 0));

        	if(entityLiving.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == RegisterItems.KEPI)
        	{
        		if(!entityLiving.isPotionActive(MobEffects.STRENGTH))
        		{
        			entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 1));
        		}
        		else if(entityLiving.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier() < 5)
        		{
        			entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, entityLiving.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier() + 1));
        		}
        		else
        		{
        			entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 4));
        		}
        	}
        	
        	stack.damageItem(1, entityLiving);
    	}
    	
        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}