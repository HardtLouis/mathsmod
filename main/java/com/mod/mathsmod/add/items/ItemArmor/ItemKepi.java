package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKepi extends ItemMagicArmor
{
	public ItemKepi() 
	{
		super(RegisterItems.SHIELD, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName("kepi").setRegistryName("kepi");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "drinking vodka make me strong");
		tooltip.add(TextFormatting.BLUE + "+ night vision");
		tooltip.add("need a tfosium fishing rod");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/kepi.png";
    }

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0));
		}
	}
}