package com.mod.mathsmod.add.Entity.Entity;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntitySuperHunterDeathOrb;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAIAttackMeleeWater;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAINearestAttackableTargetWater;
import com.mod.mathsmod.add.Entity.EntityAi.EntityAiHurtByTargetWatermob;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityMegalodon extends EntityShark
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	
	public EntityMegalodon(World worldIn) 
	{
		super(worldIn);
        this.setSize(6F, 4F);
        this.experienceValue = 400;
        this.setGlowing(true);
	}
	

    protected boolean canDespawn()
    {
        return false;
    }

	protected void initEntityAI()
    {
        this.tasks.addTask(2, new EntityAIAttackMeleeWater(this, 1.0D, false));
        this.tasks.addTask(1, new EntityShark.AIRandomSwim(this));
        this.targetTasks.addTask(1, new EntityAiHurtByTargetWatermob(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTargetWater(this, EntityLiving.class, 0, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityMegalodon) && p_apply_1_.isInWater();
            }
        }));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTargetWater(this, EntityPlayer.class, 0, false, true, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_.isInWater();
            }
        }));
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}
	
	public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }
    
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityShark)
    	{
    		return false;
    	}
    	else
    	{
    		return super.attackEntityFrom(source, amount);
    	}
    }
    
    @Override
    protected void onDeathUpdate()
    {
    	this.deathTime += 1;
    	
    	this.setGlowing(true);
    	
        if(this.deathTime >= 40)
        {
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")))
            {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }
            
            EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(RegisterItems.SEA_SWORD));

            if (entityitem != null)
            {
            	entityitem.setGlowing(true);
                entityitem.setNoDespawn();
            }
            
            if(!world.isRemote)
            {
            	world.spawnEntity(entityitem);
            }
            
            this.setDead();
        }
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(340.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0D);
    }
}
