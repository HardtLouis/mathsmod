package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.bulletproof_vest_model;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBulletproofVest extends ItemArmor implements IBulletProtection {

	public ItemBulletproofVest() 
	{
		super(RegisterItems.BULLETPROOF, 0, EntityEquipmentSlot.CHEST);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxDamage(0);
	}

	@Override
	public float getBulletMultiplier() 
	{
		return 0.3F;
	}
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		bulletproof_vest_model model = new bulletproof_vest_model(0.5F, _default);
		
		model.body1.showModel = true;
		model.body2.showModel = true;
		model.bipedBody.showModel = true;
		
        return model;
    }

}
