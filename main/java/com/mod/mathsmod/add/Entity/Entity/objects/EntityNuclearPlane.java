package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityNuclearPlane extends EntityPlane
{

	public EntityNuclearPlane(World worldIn)
	{
		super(worldIn);
		this.setSize(6, 4);
	}
	
	protected Item getEntityItem()
    {
    	return Item.getItemFromBlock(Blocks.DIRT);
    }
	
	protected int getCooldown()
	{
		return 2;
	}
	
	public double getMountedYOffset()
    {
        return 3.7D;
    }

	public float getSpeed()
	{
		return 1F * this.boost;
	}
	
	protected void explode()
	{
		this.setDead();
		PerfectExplosion.EntityBoom(Blocks.FIRE, this, posX, posY, posZ, 60, true, true, true);
	}
	
	public boolean canLaunchMissile(EntityPlayer player)
	{
		return true;
	}
	
	public void LaunchMissile(EntityPlayer player)
	{
		if(!world.isRemote)
		{
			EntityNuclearRocket entity = new EntityNuclearRocket(this.world, player, 40, 30);
			entity.rotationPitch = 90;
			entity.setPosition(player.posX, player.posY - 4, player.posZ);
			this.world.spawnEntity(entity);
		}
	}
}
