package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSacrificeDagger extends ItemSword implements IReachWeapon
{

	public ItemSacrificeDagger() 
	{
		super(ToolMaterial.DIAMOND);
		this.setRegistryName("sacrifice_dagger").setUnlocalizedName("sacrifice_dagger").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.RED + "- reach : " + (3F + this.bonusReach(null)));
		tooltip.add(TextFormatting.GREEN + "- dealth 5 more hearths on backstab");
    }
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 10, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.4000000953674316D, 0));
        }

        return multimap;
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if(UtilsVoids.isBackstabing(attacker, target) && attacker instanceof EntityPlayer)
		{
			target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker).setDamageBypassesArmor(), 10F);
		}
        return true;
    }

	@Override
	public float bonusReach(EntityPlayer player)
	{
		return -0.5F;
	}
}
