package com.mod.mathsmod.add.Entity.Entity.objects.magic;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityMagicEnderman extends EntityEnderman {

	public EntityMagicEnderman(World worldIn) {
		super(worldIn);
		this.experienceValue = 0;
	}
	
	protected void initEntityAI()
    {
		super.initEntityAI();
		this.tasks.removeTask(new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		
		if(this.getAttackTarget() != null)
		{
			if(this.getAttackTarget().getHealth() <= 0)
			{
				this.setDead();
			}
		}
	}
	
	@Nullable
    protected ResourceLocation getLootTable()
    {
		return null;
    }

	protected boolean teleportRandomly()
    {
        return false;
    }
	
	protected void updateAITasks()
    {
    }
	
}
