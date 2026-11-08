package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackPubSpin extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 60;
	}

	@Override
	public int cooldown(EntityACBoss entity) 
	{
		return 50;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
		entity.motionX *= 0.00001;
		entity.motionZ *= 0.00001;
		
		for(Entity ent : world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().grow(2, 0, 2)))
		{
			ent.attackEntityFrom(DamageSource.causeMobDamage(entity), 10);
		}
		
		int i1 = MathHelper.floor(entity.posY);
        int l1 = MathHelper.floor(entity.posX);
        int i2 = MathHelper.floor(entity.posZ);
        boolean flag = false;

        for (int k2 = -1; k2 <= 1; ++k2)
        {
            for (int l2 = -1; l2 <= 1; ++l2)
            {
                for (int j = 0; j <= 3; ++j)
                {
                	int i3 = l1 + k2;
            		int l = i2 + l2;
                    int k = i1 + j;
                    
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = world.getBlockState(blockpos);
                    Block block = iblockstate.getBlock();

                    if (block.getBlockHardness(iblockstate, world, blockpos) >= 0 && !block.isAir(iblockstate, world, blockpos))
                    {
                        flag = world.destroyBlock(blockpos, true) || flag;
                    }
                }
            }
        }
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		if(entity instanceof EntityPub)
		{
			((EntityPub) entity).setCoolTime(80);
		}
	}

	@Override
	public String objectID() 
	{
		return "attack.pub.spin";
	}

	@Override
	public int chancesFactor() 
	{
		return 2;
	}

}