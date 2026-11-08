package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackHole;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackPubShoot extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity)
	{
		return 30;
	}

	@Override
	public int cooldown(EntityACBoss entity) 
	{
		if(entity.getHealth() < entity.getMaxHealth() / 2)
		{
			return 0;
		}
		return 10;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
		entity.motionX *= 0.0001;
		entity.motionZ *= 0.0001;
		if(duree == 20)
		{
			EntityBlackHole bh = new EntityBlackHole(world);
			bh.setMaxSize(10.0F);
			bh.setSize(1.0F);
			bh.setPosition(entity.posX, entity.posY + 2, entity.posZ);
			world.spawnEntity(bh);
		}
		
		if(entity instanceof EntityPub)
		{
			((EntityPub) entity).setCoolTime(60);
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		if(entity instanceof EntityPub)
		{
			((EntityPub) entity).setShootTime(20);
		}
	}

	@Override
	public String objectID() 
	{
		return "attack.pub.shoot";
	}

	@Override
	public int chancesFactor()
	{
		return 1;
	}

}