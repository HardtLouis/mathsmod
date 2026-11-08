package com.mod.mathsmod.utils;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.items.guns.ItemFlameThrower;
import com.mod.mathsmod.add.items.guns.ItemGipsyStrenght;
import com.mod.mathsmod.add.items.guns.ItemSquidLauncher;
import com.mod.mathsmod.add.items.guns.ItemZip22;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class DamageSourceGun extends EntityDamageSource
{
	
	private boolean isFlameThrower = false;

    public DamageSourceGun(String damageTypeIn,@Nullable Entity source)
    {
        super(damageTypeIn, source);
    }
    
    public boolean isFlameThrower()
    {
		return isFlameThrower;
	}
    
    public DamageSourceGun setFlameThrower()
    {
    	isFlameThrower = true;
    	return this;
    }

    /**
     * Retrieves the immediate causer of the damage, e.g. the arrow entity, not its shooter
     */
    @Nullable
    public Entity getImmediateSource()
    {
        return this.damageSourceEntity;
    }

    /**
     * Gets the death message that is displayed when the player dies
     */
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
    {
        ITextComponent itextcomponent = this.damageSourceEntity != null ? this.damageSourceEntity.getDisplayName() : new TextComponentString(" ? ");
        ItemStack itemstack = this.damageSourceEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.damageSourceEntity).getHeldItemMainhand() : ItemStack.EMPTY;
        String s = "death.attack." + this.damageType;
        String s1 = s + ".item";
        if(itemstack.getItem() instanceof ItemFlameThrower)
        {
        	s = "death.attack.burn";
            s1 = s + ".item";
        	return !itemstack.isEmpty() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1, new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent()}) : new TextComponentTranslation(s, new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent});
        }
        if(itemstack.getItem() instanceof ItemZip22 || itemstack.getItem() instanceof ItemGipsyStrenght || itemstack.getItem() instanceof ItemSquidLauncher)
        {
        	return !itemstack.isEmpty() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1 + ".noob", new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent()}) : new TextComponentTranslation(s + ".noob", new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent});
        }
        else
        {
        	return !itemstack.isEmpty() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1, new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent()}) : new TextComponentTranslation(s, new Object[] {entityLivingBaseIn.getDisplayName(), itextcomponent});
        }
        
    }
}