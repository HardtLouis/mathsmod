package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelPlatypusPaw;
import com.mod.mathsmod.add.items.ItemArmor.models.model_jetpack;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPlatypusPaw extends ItemArmor
{

	public ItemPlatypusPaw()
	{
		super(RegisterItems.PLATIPUS, 0, EntityEquipmentSlot.FEET);
		this.setRegistryName("platypus_paw").setUnlocalizedName("platypus_paw").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		super.onArmorTick(world, player, stack);
		if(EnchantmentHelper.getEnchantmentLevel(Enchantments.DEPTH_STRIDER, stack) != 5)
		{
			ItemStackUtils.addEnchantment(stack, Enchantments.DEPTH_STRIDER, "5");
		}
	}
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelPlatypusPaw model = new ModelPlatypusPaw(_default, 0.5F);
		
		model.rightleg.showModel = true;
		model.leftleg.showModel = true;
		
        return model;
    }
}
