package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIronSkull extends ItemArmor
{

	public ItemIronSkull()
	{
		super(RegisterItems.THORIUM, 0, EntityEquipmentSlot.HEAD);
		this.setRegistryName("iron_skull").setUnlocalizedName("iron_skull");
	}

	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/iron_skull.png";
    }
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		super.onArmorTick(world, player, stack);
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0));
		
		for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox()))
		{
			if(entity instanceof EntityLivingBase && (player.motionX != 0 || player.motionZ != 0) && !player.getCooldownTracker().hasCooldown(this))
			{
				if(UtilsVoids.attackEntityFrom((EntityLivingBase) entity, MDamageSource.causeHead(player), 4))
				{
					((EntityLivingBase) entity).knockBack(player, 1, player.posX - entity.posX, player.posZ - entity.posZ);
					player.getCooldownTracker().setCooldown(this, 10);
				}
				
				if(entity instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
				}
			}
		}
	}
}
