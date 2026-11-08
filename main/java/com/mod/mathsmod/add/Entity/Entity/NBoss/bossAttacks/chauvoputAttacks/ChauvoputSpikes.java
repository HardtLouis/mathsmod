package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityChauvoputEruptionBlock;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChauvoputSpikes extends BaseBossAttack 
{
	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 50;
	}

	@Override
	public int cooldown(EntityACBoss entity)
	{
		return 20;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree) 
	{
		if(duree == 0)
		{
			for(int i = 0; i < 4; ++i)
			{
				BlockPos pos = new BlockPos(target).add(10 - entity.getRNG().nextDouble() * 20, 0, 10 - entity.getRNG().nextDouble() * 20);
				BlockPos pos2 = world.getHeight(pos);
				
				int tries = 0;
				
				while(entity.getDistance(pos2.getX() + 0.5F, pos2.getY(), pos2.getZ() + 0.5F) > 4 && entity.getDistance(pos2.getX() + 0.5F, pos2.getY(), pos2.getZ() + 0.5F) < 7 && tries < 5)
				{
					pos = new BlockPos(target).add(10 - entity.getRNG().nextDouble() * 20, 0, 10 - entity.getRNG().nextDouble() * 20);
					pos2 = world.getHeight(pos);
					tries += 1;
				}
				
				if(!world.isRemote)
				{
					EntityChauvoputEruptionBlock e = new EntityChauvoputEruptionBlock(world);
					e.setPosition(pos2.getX() + 0.5, pos2.getY(), pos2.getZ() + 0.5);
					e.setUser(entity);
					world.spawnEntity(e);
				}
			}
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target)
	{
	}

	@Override
	public String objectID()
	{
		return "object.chauvoput.spikes";
	}

	@Override
	public int chancesFactor() 
	{
		return 3;
	}

}
