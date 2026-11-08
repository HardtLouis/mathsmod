package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.event.KeyEvent;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDebuffSeringe extends ItemM implements IHeavyHitterItem, ICustomAnimation
{
	protected static final UUID SPEED_MODIFIER = UUID.fromString("EA433E1B-8362-2834-B01B-BDDEA796FDE3");

	public ItemDebuffSeringe()
	{
		super("debuff_seringe");
	}

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN.toString() + "allow the user to inject a debuffing");
		tooltip.add(TextFormatting.GREEN.toString() + "poison to ennemies");
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
        
    	if(slot == EntityEquipmentSlot.MAINHAND)
    	{
            //multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6.0F, 0));
            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(SPEED_MODIFIER, "Weapon modifier", 0.01F, 0));
    	}

        return multimap;
    }
    
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
    	if(attacker instanceof EntityPlayer && !((EntityPlayer) attacker).getCooldownTracker().hasCooldown(this))
    	{
    		target.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
    		target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 1));
        	((EntityPlayer) attacker).getCooldownTracker().setCooldown(this, 600);
    	}
		return super.hitEntity(stack, target, attacker);
	}
    
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return true;
    }
    
    //f doit être entre 0 et 1, espèce d'énorme fdp
    private float rampMaisApplati(float f)
    {
    	if(f < 0.3F)
    	{
    		return f / 0.3F;
    	}
    	else if(f < 0.7)
    	{
    		return 1.0F;
    	}
    	else
    	{
    		return (1.0F - f) / 0.3F;
    	}
    }

    @SideOnly(Side.CLIENT)
	@Override
	public void renderFirstPersonPre(ItemStack stack, EnumHand hand, EntityLivingBase entity, float partialTick, float adv) 
	{
		if(adv < 0.33)
		{
			GlStateManager.translate(0, 0.2 + Math.sin(adv / 0.33 * Math.PI/2) * -0.5, 0);
			GlStateManager.rotate((float) (-Math.sin(adv / 0.33 * Math.PI/2) * 90.0F), 1, 0, 0);
		}
		else
		{
			float subAdv = (adv - 0.33F) / 0.67F;
			GlStateManager.translate(0, -0.3, rampMaisApplati(subAdv) * -0.6);
			GlStateManager.rotate(-90, 1, 0, 0);
			
			if(subAdv > 0.7F)
			{
				//GlStateManager.translate(0, 0.2 + (1 - subAdv) / 0.3F * -0.5, 0);
				//GlStateManager.rotate((1 - subAdv) / 0.3F * 90.0F, 1, 0, 0);
			}
		}
	}

    @SideOnly(Side.CLIENT)
	@Override
	public void renderThirdPersonPre(ItemStack stack, EntityLivingBase entity, float partialTick, float limbSwing,
			float limbSwingAmount, ModelBiped model) 
	{	
	}

    @SideOnly(Side.CLIENT)
	@Override
	public void renderThirdPerson(ItemStack stack, EntityLivingBase entity, float partialTick) 
	{
	}

	@Override
	public int hitFrame() 
	{
		return 20;
	}

	@Override
	public int hitTime()
	{
		return 30;
	}
}