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

public class ItemHunterGasMask extends ItemUnknownHelmet
{
	public ItemHunterGasMask() 
	{
		super(RegisterItems.KURAYUM_ARMOR);
		this.setUnlocalizedName("gas_mask").setRegistryName("gas_mask");
		this.setMaxDamage(0);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "+ you cannot be poisoned or withered");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/gas_mask.png";
    }
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this)
		{
			if(player.isPotionActive(MobEffects.POISON))
			{
				player.removePotionEffect(MobEffects.POISON);
			}
			
			if(player.isPotionActive(MobEffects.WITHER))
			{
				player.removePotionEffect(MobEffects.WITHER);
			}
			
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0));
		}
	}
}