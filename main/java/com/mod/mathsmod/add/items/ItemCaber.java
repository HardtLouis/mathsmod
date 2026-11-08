package com.mod.mathsmod.add.items;

import java.util.List;
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

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCaber extends ItemSword implements IReachWeapon
{
	public ItemCaber() 
	{
		super(RegisterItems.LEGENDARY);
		this.setUnlocalizedName("caber").setRegistryName("caber");
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if(attacker instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) attacker;
			if(!player.getCooldownTracker().hasCooldown(this))
			{
				if(!player.world.isRemote)
				{
					for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, player.world, player, 6))
					{
						if(entity != null && entity != player)
						{
							float damages = (45F - entity.getDistance(player) * 7.5F) * 1.4F;
							UtilsVoids.attackEntityFrom(entity, DamageSource.causeExplosionDamage(player), damages);
						}
					}
					UtilsVoids.attackEntityFrom(player, DamageSource.causeExplosionDamage(player), 15);
				}
				player.world.createExplosion(player, player.posX, player.posY, player.posZ, 4, true);
				player.getCooldownTracker().setCooldown(this, 600);
			}
		}
		return true;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.BLUE + "bonus reach : " + this.bonusReach(null, stack));
		tooltip.add(TextFormatting.YELLOW + "explode on hit");
	}

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 0.5F;
	}
	
	public float damages()
	{
		return 20;
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