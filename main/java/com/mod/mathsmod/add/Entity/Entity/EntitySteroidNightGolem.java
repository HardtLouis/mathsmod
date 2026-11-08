package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySteroidNightGolem extends EntityNightGolem
{
	public EntitySteroidNightGolem(World worldIn) 
	{
		super(worldIn);
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.255D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
    }
	
	public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            Vec3d vec = UtilsVoids.laser(5F, this.rotationYaw, this.rotationPitch);
            entityIn.addVelocity(vec.x, vec.y, vec.z);
        }

        return flag;
    }
	
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	if(this.getRNG().nextInt(3) < 1)
    	{
            this.dropItem(RegisterItems.NIGHT_SHARD, 1 + lootingModifier);
            this.dropItem(Items.DIAMOND, 1);
    	}
    }
}
