package com.mod.mathsmod.add.items.guns.accessories;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.BadExplosionS;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class GunAccessoryBase extends Item
{
	
	private final EnumAccessoryType acc; 
	
	public GunAccessoryBase(String name, EnumAccessoryType type)
	{
		acc = type;
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setMaxStackSize(1);
	}
	
	public EnumAccessoryType modifierType()
	{
		return acc;
	}
	
	public abstract float innaccuracyMultiplier();
	
	public abstract int switchCooldownBonus();
	
	public abstract float zoomMultiplier();
	
	public abstract float soundMultiplier();
	
	public abstract float smoothMultiplier();
	
	public abstract float damageMultiplier();
	
	public boolean isScope()
	{
		return this.zoomMultiplier() > 1.0F && this.modifierType() == EnumAccessoryType.SCOPE;
	}
	
	public void middleClick(World world, ItemGun item, ItemStack stack, EntityPlayer entity)
	{
		if(this.modifierType() == EnumAccessoryType.GRENADE_LAUNCHER)
		{
			if(!entity.getCooldownTracker().hasCooldown(this))
			{
				for(int i = 0; i <= 20; ++i)
				{
					Vec3d vec = UtilsVoids.laser(i, entity.rotationYaw, entity.rotationPitch).add(entity.getPositionVector().addVector(0, entity.getEyeHeight(), 0));
					if(world.getBlockState(new BlockPos(vec)).getMaterial() != Material.AIR || i == 20)
					{
						BadExplosionS.newExplosion(world, entity, vec.x, vec.y, vec.z, 3, 3, true);
						for(EntityLivingBase entityIn : UtilsVoids.getEntities(EntityLivingBase.class, world, vec.x, vec.y, vec.z, 3))
						{
							UtilsVoids.AttractEntity(vec.x, vec.y, vec.z, entityIn, 10, -2);
							entityIn.attackEntityFrom(DamageSource.causeExplosionDamage(entity), (float) (12F - 4F * entityIn.getDistance(vec.x, vec.y, vec.z)));
						}
						
						if(!entity.capabilities.isCreativeMode)
						{
							entity.getCooldownTracker().setCooldown(this, 300);
						}
						else
						{
							entity.getCooldownTracker().setCooldown(this, 20);
						}
						entity.getCooldownTracker().setCooldown(item, 10);
						item.setShootDefault(stack, 8);
						return;
					}
					
				}
			}
		}
	}
}
