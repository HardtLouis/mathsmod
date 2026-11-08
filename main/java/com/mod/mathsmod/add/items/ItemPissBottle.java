package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityJarate;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemPissBottle extends ItemM implements ILeftClickItem
{

	public ItemPissBottle()
	{
		super("jarate");
		this.setMaxStackSize(1);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		EntityJarate entity = new EntityJarate(worldIn, playerIn);
		entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 2.0F);
		if(!worldIn.isRemote)
		{
			worldIn.spawnEntity(entity);
		}
		playerIn.getCooldownTracker().setCooldown(this, 100);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

	@Override
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks) 
	{
		if(ticks >= 40)
		{
			playerIn.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegisterItems.MOSQUITO_JAR));
		}
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "pisses of the targeted entity");
    }
}
