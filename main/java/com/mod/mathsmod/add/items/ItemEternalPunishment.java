package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEternalPunishment extends ItemM implements IHeavyHitterItem, ICustomAnimation
{
	protected static final UUID SPEED_MODIFIER = UUID.fromString("EA433E1B-8362-2834-B01B-BDDEA796FDE3");

	public ItemEternalPunishment()
	{
		super("eternal_punishment");
		this.setMaxStackSize(1);
	}

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN.toString() + "will burn even targets with fire resistance potion effect");
		tooltip.add(TextFormatting.RED.toString() + "can only swing one time per second");
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
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6.0F, 0));
            //multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(SPEED_MODIFIER, "Weapon modifier", 0));
    	}

        return multimap;
    }
    
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
    	target.addPotionEffect(new PotionEffect(RegisterPotions.FIRE_VULNERABILITY, 3000));
    	target.setFire(5);
		return super.hitEntity(stack, target, attacker);
	}
    
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
	@Override
	public void renderFirstPersonPre(ItemStack stack, EnumHand hand, EntityLivingBase entity, float partialTick, float adv) 
	{
		float animAdv = 0;
		float pente = 1.7F;
		float subdivAdv = 0.5F;
		//pente * subdivAdv should always be inferior to one, or else, Steph Pass will come for you
		if(adv < subdivAdv)
		{
			animAdv = pente * adv;
		}
		else
		{
			animAdv = pente * subdivAdv + (1.0F - pente * subdivAdv) * (adv - (1.0F - subdivAdv)); 
		}
		
		GlStateManager.translate(0, 0.2 + Math.sin(animAdv * Math.PI) * -0.5, 0);
		GlStateManager.rotate((float) (-Math.sin(adv * Math.PI) * 90.0F), 1, 0, 0);
	}

    @SideOnly(Side.CLIENT)
	@Override
	public void renderThirdPersonPre(ItemStack stack, EntityLivingBase entity, float partialTick, float limbSwing,
			float limbSwingAmount, ModelBiped model) 
	{	
    	float hitTime = this.hitTime();
    	float adv = (hitTime - KeyEvent.hhAttackTick + partialTick) / hitTime;
    	
    	if(KeyEvent.hhAttackTick > 0)
    	{
    		model.bipedRightArm.rotateAngleX = -(float) (Math.sin(adv * Math.PI) * Math.PI * 0.5);
    		model.bipedRightArm.rotateAngleY = 0;
    	}
	}

    @SideOnly(Side.CLIENT)
	@Override
	public void renderThirdPerson(ItemStack stack, EntityLivingBase entity, float partialTick) 
	{
	}

	@Override
	public int hitFrame() 
	{
		return 0;
	}

	@Override
	public int hitTime() {
		return 20;
	}
}