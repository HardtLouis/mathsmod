package com.mod.mathsmod.add.items.ItemArmor;

import java.awt.Color;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemKurayumArmor extends ItemMagicArmor implements IBulletProtection
{

	protected static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	
	public ItemKurayumArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0));
		}
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
		}
		
	}
	

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType.FEET && this.armorType == EntityEquipmentSlot.FEET)
        {
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", 0.0075F, 0));
        }

        return multimap;
    }

	@Override
	public float getBulletMultiplier() 
	{
		return 0.92F;
	}
    
    /*public boolean hasOverlay(ItemStack stack)
    {
        return true;
    }
    
    public int getColor(ItemStack stack)
    {
        return new Color(255, 0, 0).getRGB();
    }*/

}
