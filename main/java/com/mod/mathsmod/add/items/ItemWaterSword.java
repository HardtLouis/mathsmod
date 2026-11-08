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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemWaterSword extends ItemSword implements IReachWeapon
{
	public ItemWaterSword() 
	{
		super(RegisterItems.LEGENDARY);
		this.setRegistryName("sea_sword").setUnlocalizedName("sea_sword").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 15, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.4000000953674316D, 0));
        }

        return multimap;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "bonus reach : " + (this.bonusReach(null)));
		tooltip.add(TextFormatting.YELLOW + "right click to dash when in water");
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(new Random().nextInt(5) == 0)
		{
			target.attackEntityFrom(DamageSource.DROWN, 4);
		}
        return true;
    }
    
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
		
    	if(hand == EnumHand.MAIN_HAND)
    	{
    		player.getCooldownTracker().setCooldown(this, 60);
    		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, worldIn, player, 4))
    		{
    			if(entity != player)
    			{
    				entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 3));
        			UtilsVoids.attackEntityFrom(entity, DamageSource.causePlayerDamage(player), 1);
    			}
    		}
    		if(player.isInWater())
    		{
    			UtilsVoids.EasyShootEntityModifyY(player, player.rotationPitch, player.rotationYaw, 3, 0);
    			player.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 10000));
    			player.getCooldownTracker().setCooldown(this, 20);
    		}
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 1.0F;
	}
}
