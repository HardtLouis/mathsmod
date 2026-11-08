package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.EntityMosquito;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMosquitoJar extends ItemM implements ILeftClickItem
{
	public ItemMosquitoJar()
	{
		super("mosquito_jar");
		this.setMaxDamage(20);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.GREEN + "can contain up to 20 mosquitoes");
        tooltip.add(TextFormatting.GREEN + "right click on a mosquito to capture it, left click to release");
    }
	
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
		if(target instanceof EntityMosquito)
		{
			target.setDead();
			stack.damageItem(1, playerIn);
		}
        return false;
    }

	@Override
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks) 
	{
		Random rand = new Random();
		
		if(!worldIn.isRemote && this.getDamage(playerIn.getHeldItemMainhand()) > 1)
		{
			for(int i = 0; i < this.getDamage(playerIn.getHeldItemMainhand()); ++i)
			{
				EntityMosquito entity = new EntityMosquito(worldIn);
	        	entity.setPosition(playerIn.posX, playerIn.posY + 1, playerIn.posZ);
	        	entity.motionX = rand.nextFloat() * 1.0F - 0.5F;
	        	entity.motionZ = rand.nextFloat() * 1.0F - 0.5F;
	        	worldIn.spawnEntity(entity);
			}
		}
		
		this.setDamage(playerIn.getHeldItemMainhand(), 0);
	}
}
