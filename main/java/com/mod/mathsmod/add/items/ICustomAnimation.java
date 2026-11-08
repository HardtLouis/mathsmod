package com.mod.mathsmod.add.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomAnimation 
{
	@SideOnly(Side.CLIENT)
	public void renderFirstPersonPre(ItemStack stack, EnumHand hand, EntityLivingBase entity, float partialTick, float adv);
	

	@SideOnly(Side.CLIENT)
	public void renderThirdPersonPre(ItemStack stack, EntityLivingBase entity, float partialTick, float limbSwing, float limbSwingAmount, ModelBiped model);

	@SideOnly(Side.CLIENT)
	public void renderThirdPerson(ItemStack stack, EntityLivingBase entity, float partialTick);
}
