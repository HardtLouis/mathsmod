package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.Entity.Entity.DBossDPPhases.PhaseList;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackHole;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityDBossPigPhase extends EntityPig
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_6)).setDarkenSky(false);
	
	public EntityDBossPigPhase(World worldIn) 
	{
		super(worldIn);
	}
	
	public boolean getSaddled()
    {
        return false;
    }
	
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        return false;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	this.bossInfo.setPercent(0.25F + (this.getHealth() / this.getMaxHealth()) * 0.25F);
    }
    
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
	
	@Override
    protected void onDeathUpdate()
    {
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		
    	this.deathTime += 1;
        if(this.deathTime >= 161)
        {
            if(!world.isRemote)
            {
            	//world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.BLACK_HOLE)));
            	//world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.STABILIZER, 4)));
            	//world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(RegisterItems.CRUSHER)));
            	EntityDBossDragonPhase entity = new EntityDBossDragonPhase(world);
    			entity.setPosition(this.posX, this.posY, this.posZ);
    			entity.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
    			entity.setSpawnVec(this.posX, this.posY, this.posZ);
    			world.spawnEntity(entity);
            }
            
            this.setDead();
        }
        if(this.deathTime == 1 && !world.isRemote)
        {
        	EntityBlackHole entity = new EntityBlackHole(world);
			entity.setPosition(this.posX, this.posY, this.posZ);
			entity.setSize(0F);
			entity.setMaxSize(8F);
			entity.attractMultiplier = -2F;
			world.spawnEntity(entity);
        }
        
    }
	
	protected void despawnEntity()
    {
        this.idleTime = 0;
    }

}
