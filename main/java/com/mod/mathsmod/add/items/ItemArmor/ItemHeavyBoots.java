package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelBlackHat;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHeavyBoots extends ItemArmor
{

	public ItemHeavyBoots() 
	{
		super(RegisterItems.HATS, 0, EntityEquipmentSlot.FEET);
		this.setUnlocalizedName("heavy_boots").setRegistryName("heavy_boots");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.RED + "no bonus stats");
		tooltip.add(TextFormatting.GREEN + "-75% self damage on rocket launch");
		tooltip.add(TextFormatting.BLUE + "you can stomp your enemies");
		tooltip.add(TextFormatting.BLUE + "-85% fall damages");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/heavy_boots.png";
    }
}