package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelPolariteArmor;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelThoriumArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRedCloth extends ItemStatArmor
{

	public ItemRedCloth(String name, ArmorMaterial mat, double bonusAttack, double bonusDef, double bonusSpeed, double bonusHealth, float bulletMultiplier) 
	{
		super(mat, 0, EntityEquipmentSlot.CHEST, bonusAttack, bonusDef, bonusSpeed, bonusHealth, bulletMultiplier, 2, 7, name);
		this.setMaxDamage(0);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "let you regenerate your spell power faster");
    }
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/cloth.png";
    }
	
	public int rate()
	{
		return 3;
	}
	
	public int regenPower()
	{
		return 2;
	}
	
	@SuppressWarnings("incomplete-switch")
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelThoriumArmor chest = new ModelThoriumArmor(0.7F, _default);
		
		switch(armorSlot)
		{
			case CHEST:
				chest.allRendered = true;
				return chest;
		}
		
		return chest;
    }
}
