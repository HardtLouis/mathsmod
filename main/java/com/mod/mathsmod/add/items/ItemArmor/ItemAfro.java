package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelAfro;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelBlackHat;

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

public class ItemAfro extends ItemArmor implements IBulletProtection
{

	public ItemAfro() 
	{
		super(RegisterItems.HATS, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName("ghali_aircut").setRegistryName("ghali_aircut");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.RED + "no stats bonus");
		tooltip.add(TextFormatting.GREEN + "speed on wearer");
		tooltip.add(TextFormatting.GREEN + "+20% damages on shot wow !");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/ghali_aircut.png";
    }
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, 1));
		player.stepHeight = 1.0F;
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0));
	}
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelAfro chest = new ModelAfro();
		return chest;
    }

	@Override
	public float getBulletMultiplier()
	{
		return 1.2F;
	}
}