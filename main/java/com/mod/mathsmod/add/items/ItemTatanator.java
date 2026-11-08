package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemTatanator extends Item implements IReachWeapon
{
	public ItemTatanator() 
	{
		this.setRegistryName("tatanator").setUnlocalizedName("tatanator").setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		attacker.world.playSound((EntityPlayer)null, attacker.posX, attacker.posY + 1, attacker.posZ, MathsSounds.TATANATOR, SoundCategory.AMBIENT, 1.0F, 1.0F);
        return true;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "bonus reach : " + this.bonusReach(null));
    }
	
	public boolean getShareTag()
    {
        return true;
    }
	
	public float damages()
	{
		return 1F;
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damages(), 0));
        }

        return multimap;
    }

	@Override
	public float bonusReach(EntityPlayer entity) 
	{
		return 4;
	}
}