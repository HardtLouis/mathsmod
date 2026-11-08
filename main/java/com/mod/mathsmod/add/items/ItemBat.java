package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBounceProjectile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRebondiGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityStickyGrenade;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBat extends Item implements IReachWeapon, IReturnToSender, ILeftClickItem
{
	public ItemBat() 
	{
		this.setRegistryName("baseball_bat").setUnlocalizedName("baseball_bat").setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		UtilsVoids.EasyShootEntityModifyY(target, attacker.rotationPitch, attacker.rotationYaw, this.sendStrenght() * 5F, 0);
		target.motionY = target.motionY + 0.1;
		
		if(target instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(target.motionX, target.motionY, target.motionZ)), (EntityPlayerMP) target);
		}
        return true;
    }

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "can send back grenades if swung at the right time");
    }
	
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		if(ticks == 1)
		{
			for(EntityRebondiGrenade entity : UtilsVoids.getEntities(EntityRebondiGrenade.class, worldIn, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, 3.0 + this.bonusReach(playerIn)))
			{
				float pitchAbsolute = UtilsVoids.getPitchFromPos(playerIn.getPositionVector().addVector(0, playerIn.getEyeHeight(), 0), entity.posX, entity.posY, entity.posZ) - playerIn.rotationPitch;
				pitchAbsolute = MathHelper.sqrt(pitchAbsolute * pitchAbsolute);
				
				float yawAbsolute = UtilsVoids.getYawFromPos(playerIn.getPositionVector().addVector(0, playerIn.getEyeHeight(), 0), entity.posX, entity.posZ) - playerIn.rotationYaw;
				yawAbsolute = MathHelper.sqrt(yawAbsolute * yawAbsolute);
				
				if(pitchAbsolute < 15 && yawAbsolute < 15)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 2);
				}
			}
		}
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 0.25F;
	}
	
	@Override
	public float sendStrenght()
	{
		return 0.6F;
	}
	
	public float damages()
	{
		return 6F;
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damages(), 0));
        }

        return multimap;
    }
}
