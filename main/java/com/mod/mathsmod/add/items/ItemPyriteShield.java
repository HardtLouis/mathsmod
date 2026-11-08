package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterPotions;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPyriteShield extends ItemChargeShield
{
	public ItemPyriteShield()
	{
		super("pyrite_shield");
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.RED + "- -50% stop power resistance");
        tooltip.add(TextFormatting.RED + "- -20% charge time");
        tooltip.add(TextFormatting.RED + "- +25% cooldown after charge");
        tooltip.add(TextFormatting.GREEN + "+ sneak + jump + right click to charge");
        tooltip.add(TextFormatting.GREEN + "+ +100% flamethrower resistance");
    }
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        
		if(playerIn.isSneaking() && !playerIn.onGround)
		{
	        playerIn.getCooldownTracker().setCooldown(this, 250);
	        playerIn.addPotionEffect(new PotionEffect(RegisterPotions.CHARGE, 80, 49));
		}
		
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
