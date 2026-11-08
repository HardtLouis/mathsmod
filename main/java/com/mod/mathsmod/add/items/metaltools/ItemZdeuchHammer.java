package com.mod.mathsmod.add.items.metaltools;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.ICustomAnimation;
import com.mod.mathsmod.add.items.IHeavyHitterItem;
import com.mod.mathsmod.add.messages.MessageTempAnim;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemZdeuchHammer extends ItemMetalicTool implements IHeavyHitterItem, ICustomAnimation
{
	protected static final UUID SPEED_MODIFIER = UUID.fromString("EA433E1B-8362-2834-B01B-BDDEA785FDE3");

	public ItemZdeuchHammer()
	{
		super("zdeuch_hammer");
	}

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.BLUE.toString() + "on attack key pressed, will deal damages");
		tooltip.add(TextFormatting.BLUE.toString() + "some time after the key was pressed");
		tooltip.add(TextFormatting.BLUE.toString() + "but will automatically make the player attack");
		tooltip.add(TextFormatting.BLUE.toString() + "when he can");
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
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (this.getMetal(stack).attack_damages) * 1.25F, 0));
            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(SPEED_MODIFIER, "Weapon modifier", -0.03, 0));
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
		GlStateManager.translate(Math.sin(animAdv * Math.PI) * -1, 0.2 + Math.sin(animAdv * Math.PI) * -0.3, 0);
		GlStateManager.rotate((float) (Math.sin(adv * Math.PI) * 90.0F), 0, 1, 0);
		GlStateManager.rotate((float) (-Math.sin(animAdv * Math.PI) * 90.0F), 0, 0, 1);
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
    		model.bipedRightArm.rotateAngleX = -(float) (Math.sin(adv * Math.PI) * Math.PI * 0.7);
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
		return 4;
	}

	@Override
	public int hitTime() {
		return 16;
	}
	
	@Override
	public boolean shouldUnpressAttackKey()
	{
		return false;
	}
}
