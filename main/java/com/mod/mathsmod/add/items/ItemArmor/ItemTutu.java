package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelAfro;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelTutu;

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

public class ItemTutu extends ItemArmor implements IBulletProtection
{

	public ItemTutu() 
	{
		super(RegisterItems.HATS, 0, EntityEquipmentSlot.CHEST);
		this.setUnlocalizedName("tutu").setRegistryName("tutu");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.GREEN + "slowfall + jump boost");
		tooltip.add(TextFormatting.RED + "immune to getting chased by a giant pumkin");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/tutu.png";
    }
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1, 1));
		player.addPotionEffect(new PotionEffect(RegisterPotions.SLOWFALL, 1, 1));
	}
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelTutu chest = new ModelTutu();
		return chest;
    }

	@Override
	public float getBulletMultiplier()
	{
		return 1.0F;
	}
}