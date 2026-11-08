package com.mod.mathsmod.add.items.metaltools;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.items.ICustomColor;
import com.mod.mathsmod.add.items.ItemArmor.IExplosionProtection;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class ItemMetalicArmor extends ItemArmor implements ICustomColor, IExplosionProtection
{
	private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	
	public ItemMetalicArmor(String name, EntityEquipmentSlot slot) 
	{
		super(ItemArmor.ArmorMaterial.LEATHER, 0, slot);
		this.setCreativeTab(Mathsmod.MATHSMOD_MET_TAB);
		this.setUnlocalizedName(name).setRegistryName(name);
	}
	
	@Override
	public int getColor(ItemStack stack)
    {
         return this.getColorMultiplier(stack, 0);
    }

	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		if(this.getMetal(stack) != null)
		{
			return this.getMetal(stack).color;
		}
		return -1;
	}
	
	public static void setAlloy(ItemStack stack, Item mat, Item mat2)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", mat.getRegistryName().toString() + "%" + mat2.getRegistryName().toString());
	}
	
	public static void setAlloyFromString(ItemStack stack, String str)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", str);
	}
	
	public static void setMetal(ItemStack stack, Item mat)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", mat.getRegistryName().toString());
	}
	
	public static String getMetalString(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
			return null;
		}
		
		return stack.getTagCompound().getString("metal");
	}
	
	public static Molten_metal getMetal(ItemStack stack)
	{
		return MeltingRegistry.getMetal(getMetalString(stack));
	}

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (slot == this.armorType && this.getMetal(stack) != null)
        {
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Armor modifier", (double)this.getMetal(stack).armor_protection[armorType.getIndex()], 0));
            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Armor toughness", 2.0F, 0));
            multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Armor bonus health", (double)this.getMetal(stack).bonusHealth(), 0));
        }

        return multimap;
    }

	@Override
	public float explosionMultiplier(EntityLivingBase entity, Item item, ItemStack stack) 
	{
		if(this.getMetal(stack) != null)
		{
			return this.getMetal(stack).explosionMultiplier();
		}

		return 1.0F;
	}
}
