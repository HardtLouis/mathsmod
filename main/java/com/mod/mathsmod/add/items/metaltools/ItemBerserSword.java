package com.mod.mathsmod.add.items.metaltools;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;
import com.mod.mathsmod.add.messages.MessageTempAnim;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBerserSword extends ItemMetalicTool implements IBulletProtection
{
	
	protected static final UUID DEFENCE_MODIFIER = UUID.fromString("EA433E1B-8362-2834-B01B-BDCE9285ACA3");
	protected static final UUID SPEED_MODIFIER = UUID.fromString("EA433E1B-8362-2834-B01B-BCCE9785FDE4");

	public ItemBerserSword()
	{
		super("bersersword");
	}
	
	public boolean isMultiplierPassive()
	{
		return true;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + " +20% damages");
		tooltip.add(TextFormatting.GREEN + " +bonus speed when held");
		tooltip.add(TextFormatting.RED + " -6 armor when held");
		tooltip.add(TextFormatting.RED + " +30% damage from guns (passive)");
		tooltip.add(TextFormatting.BLUE + "FIGHT OR DIE");
		tooltip.add(TextFormatting.BLUE + " +speed and strength when health below 25% but the user keeps losing health");
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
        
    	if(slot == EntityEquipmentSlot.MAINHAND && this.getMetal(stack) != null)
    	{
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.getMetal(stack).attack_damages * 1.2F, 0)); 
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", -6, 0)); 
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", Double.POSITIVE_INFINITY, 0));
            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(SPEED_MODIFIER, "Weapon modifier", 0.03F, 0));
    	}

        return multimap;
    }
    
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
    	if(this.getMetal(stack) != null)
    	{
    		this.getMetal(stack).effectOnHit(0, 0, attacker.world, attacker, target, stack);
    	}
    	
		return super.hitEntity(stack, target, attacker);
	}
    
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	if(entityIn instanceof EntityLivingBase)
    	{
    		if(this.getMetal(stack) != null && ((EntityLivingBase) entityIn).getHealth() < (((EntityLivingBase) entityIn).getMaxHealth() + this.getMetal(stack).bonusHealth()) * 0.25F)
    		{
    			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 3));
    			if(!((EntityLivingBase) entityIn).isPotionActive(MobEffects.STRENGTH))
    			{
        			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10, 2));
    			}
    			((EntityLivingBase) entityIn).setHealth(((EntityLivingBase) entityIn).getHealth() - 0.1F);
    		}
    	}
    }

	@Override
	public float getBulletMultiplier() 
	{
		return 1.3F;
	}
}
