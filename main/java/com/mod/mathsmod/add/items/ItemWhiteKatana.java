package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemWhiteKatana extends ItemSword implements IReachWeapon, IBulletProtection
{
	public ItemWhiteKatana() 
	{
		super(RegisterItems.LEGENDARY);
		this.setRegistryName("white_katana").setUnlocalizedName("white_katana").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.4000000953674316D, 0));
        }

        return multimap;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "bonus reach : " + (this.bonusReach(null)));
		tooltip.add("right click to dash");
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(attacker.isSneaking())
		{
			
			
		}
        return true;
    }
    
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
		
    	if(hand == EnumHand.MAIN_HAND)
    	{
    		player.getCooldownTracker().setCooldown(this, 40);
    		Vec3d vec = UtilsVoids.laser(1.3F, player.rotationYaw, player.rotationPitch);
			player.motionX = vec.x;
			player.motionZ = vec.z;
			player.motionY = vec.y;
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 0.5F;
	}

	@Override
	public float getBulletMultiplier()
	{
		return 2.0F;
	}
}