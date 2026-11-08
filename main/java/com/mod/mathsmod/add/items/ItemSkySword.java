package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSkySword extends ItemSword implements IReachWeapon
{
	public ItemSkySword() 
	{
		super(RegisterItems.LEGENDARY);
		this.setRegistryName("sky_sword").setUnlocalizedName("sky_sword").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 14, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.4000000953674316D, 0));
        }

        return multimap;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "bonus reach : " + (this.bonusReach(null)));
		tooltip.add(TextFormatting.BLUE + "will randomly push hit entities");
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(new Random().nextInt(3) == 0)
		{
			UtilsVoids.AttractEntity(attacker, target, 20, 1D);
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
    		for(net.minecraft.entity.Entity entity : UtilsVoids.getEntities(worldIn, player, 4))
    		{
    			if(entity != player && (entity instanceof EntityLivingBase || entity instanceof EntityThrowable || entity instanceof EntityItem || entity instanceof EntityArrow))
    			{
    				entity.posY += 0.5F;
    				UtilsVoids.AttractEntity(player.posX, player.posY, player.posZ, entity, 20, -6D);
        			entity.attackEntityFrom(DamageSource.causePlayerDamage(player).setDamageBypassesArmor(), 4);
    			}
    			else if(entity instanceof EntityMissile)
    			{
    				UtilsVoids.AttractEntity(player.posX, player.posY, player.posZ, entity, 20, -12D);
    				((EntityMissile) entity).explode();
    			}
    		}
    	}
    	
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 1.2F;
	}

}