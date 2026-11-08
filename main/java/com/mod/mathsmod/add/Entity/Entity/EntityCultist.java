package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPoulopopRoot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCultist extends EntityMob
{

    public EntityCultist(World worldIn)
    {
        super(worldIn);
        this.setSize(0.65F, 1.8F);
    }

    protected void initEntityAI()
    {
    	this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, null)
        		{
        	public boolean shouldExecute()
            {
        		return this.targetEntity != null && this.targetEntity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != RegisterItems.CLOTH && super.shouldExecute();
            }
        		});
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.245D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return -0.6D;
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            //this.motionY = 1.2D;
            //this.addPotionEffect(new PotionEffect(RegisterPotions.CHARGE, 60));
        	if(!world.isRemote)
        	{
        		EntityPoulopopRoot ent = new EntityPoulopopRoot(world);
				double posX = (this.getRNG().nextFloat() * 3) - 1.5F + entityIn.posX + entityIn.motionX * 6;
				double posZ = (this.getRNG().nextFloat() * 3) - 1.5F + entityIn.posZ + entityIn.motionZ * 6;
				ent.setBasePos((float)posX, (float)posZ);
				double posY = world.getHeight((int)posX, (int)posZ);
				ent.setPosition(posX, posY, posZ);
				world.spawnEntity(ent);
        	}
        }

        return flag;
    }

    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	if(this.getRNG().nextInt(3) == 0)
    	{
            this.dropItem(RegisterItems.RED_CRYSTAL, 1);
    	}
    }
}