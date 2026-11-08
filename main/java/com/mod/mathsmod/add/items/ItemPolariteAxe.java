package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.utils.KillsCounter;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemPolariteAxe extends ItemMAxe implements IReachWeapon
{
	public ItemPolariteAxe() 
	{
		super(RegisterItems.LEGENDARY);
		this.setRegistryName("polarite_axe").setUnlocalizedName("polarite_axe");
		this.setMaxStackSize(1);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add("reach : " + (3F + this.bonusReach(null)));
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 1.3F;
	}
	
	public float damages()
	{
		return 19.5F;
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damages(), 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", -1.4, 0));
        }

        return multimap;
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	if(isSelected && entityIn instanceof EntityPlayer)
    	{
    		EntityPlayer entity = (EntityPlayer) entityIn;
    		int kills = KillsCounter.getKills(entity);
    		
    		entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 1, 4));
    		if(kills > 0)
    		{
        		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, MathHelper.clamp(kills - 1, 0, 1)));
    		}
    	}
    }
	
}