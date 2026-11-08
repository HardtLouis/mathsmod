package com.mod.mathsmod.add.Entity.Entity;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityHZombie extends EntityZombie
{
	public EntityHZombie(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.9F, 1.75F);
	}

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.13000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0D);
        this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(0.4F);
    }
	
	@Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
		if(this.getRNG().nextInt(50) == 0)
		{
			this.dropItem(RegisterItems.DIRECT_TIT, 1);
		}
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }
	
}
