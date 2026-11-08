package com.mod.mathsmod.add.items;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBounceProjectile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMythicalItem;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemPolariteFryingPan extends ItemFryingPan implements IReachWeapon, IReturnToSender
{
	public ItemPolariteFryingPan() 
	{
		super("polarite_frying_pan");
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		UtilsVoids.EasyShootEntityModifyY(target, attacker.rotationPitch, attacker.rotationYaw, 2.2F, 0);
		attacker.world.playSound((EntityPlayer) null, attacker.posX, attacker.posY, attacker.posZ, MathsSounds.FRYING_PAN, SoundCategory.MASTER, 2, 1F + (new Random().nextFloat() * 0.05F));
		target.motionY = target.motionY * 0.1 + 0.3;
		
		if(target instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(target.motionX, target.motionY, target.motionZ)), (EntityPlayerMP) target);
		}
        return true;
    }
	
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		if(ticks < 4)
		{
			if(ticks == 1)
			{
				if(worldIn.rayTraceBlocks(playerIn.getPositionEyes(1.0F), playerIn.getLook(1.0F).scale(3.0F + this.bonusReach(playerIn)).add(playerIn.getPositionEyes(1.0F)), false) != null)
				{
					worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, MathsSounds.FRYING_PAN, SoundCategory.MASTER, 2, 1F + (new Random().nextFloat() * 0.05F));
				}
			}
			for(EntityBounceProjectile entity : UtilsVoids.getEntities(EntityBounceProjectile.class, worldIn, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, 3.0 + this.bonusReach(playerIn)))
			{
				float pitchAbsolute = UtilsVoids.getPitchFromPos(playerIn.getPositionVector().addVector(0, playerIn.getEyeHeight(), 0), entity.posX, entity.posY, entity.posZ) - playerIn.rotationPitch;
				pitchAbsolute = MathHelper.sqrt(pitchAbsolute * pitchAbsolute);
				
				float yawAbsolute = UtilsVoids.getYawFromPos(playerIn.getPositionVector().addVector(0, playerIn.getEyeHeight(), 0), entity.posX, entity.posZ) - playerIn.rotationPitch;
				yawAbsolute = MathHelper.sqrt(yawAbsolute * yawAbsolute);
				
				if(pitchAbsolute < 30 && yawAbsolute < 30)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 2);
				}
			}
		}
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 1F;
	}
	
	@Override
	public float sendStrenght()
	{
		return 2F;
	}
	
	public float damages()
	{
		return 24;
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
	
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
    	EntityMythicalItem entity = new EntityMythicalItem(world, location.posX, location.posY, location.posZ, itemstack);
    	entity.motionX = location.motionX;
    	entity.motionY = location.motionY;
    	entity.motionZ = location.motionZ;
    	entity.setPickupDelay(20);
        return entity;
    }
}