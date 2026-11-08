package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelBlackHat;
import com.mod.mathsmod.add.items.ItemArmor.models.modelChapka;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChapka extends ItemArmor
{

	public ItemChapka() 
	{
		super(RegisterItems.HATS, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName("chapka").setRegistryName("chapka");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.RED + "no stats bonus");
		tooltip.add(TextFormatting.BLUE + "x 1.75 damages with ak");
		tooltip.add("need a tfosium fishing rod");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/chapka.png";
    }
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		modelChapka chest = new modelChapka(0.5F);
		return chest;
    }
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0));
		}
	}
}