package com.mod.mathsmod.add.items.metaltools;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;
import com.mod.mathsmod.add.messages.MessageActivGrenadeLauncher;
import com.mod.mathsmod.add.messages.MessageTempAnim;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemGourdin extends ItemMetalicTool
{
	
	protected static final UUID SPEED_MODIFIER = UUID.fromString("EA433E1B-8362-2834-B01B-BCCE9785FDE3");

	public ItemGourdin()
	{
		super("gourdin");
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
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (this.getMetal(stack).attack_damages) * 1.15F, 0));
            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(SPEED_MODIFIER, "Weapon modifier", -0.04, 0));
    	}

        return multimap;
    }
    
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
    	if(this.getMetal(stack) != null)
    	{
    		this.getMetal(stack).effectOnHit(0, 0, attacker.world, attacker, target, stack);
    	}
    	
    	if(!attacker.world.isRemote)
    	{
    		Mathsmod.network.sendToAll(new MessageTempAnim(target, 0));
    	}
    	
		return super.hitEntity(stack, target, attacker);
	}
}
