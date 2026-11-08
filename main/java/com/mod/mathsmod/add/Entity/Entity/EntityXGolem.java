package com.mod.mathsmod.add.Entity.Entity;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityXGolem extends AbstractGolem{

	public EntityXGolem(World worldIn) {
		super(worldIn);
	}

	@Override
	public float getSizeMultiplier() {
		return 1.3F;
	}

	@Override
	public int getSizeInventory() {
		return 144;
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0D);
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if(this.getOwner() == player)
    	{
    		if(player.isSneaking())
    		{
    			return false;
    		}
    		else
    		{
    			player.openGui(Mathsmod.instance, 0, player.world, this.getEntityId(), 0, 0);
    			return true;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }
    
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	for(int i = 0; i <= this.getSizeInventory(); ++i)
    	{
    		EntityItem entityitem = this.entityDropItem(this.getInv().getStackInSlot(i), 1F);
    		if (entityitem != null)
            {
                entityitem.setNoDespawn();
            }
    	}
    }

}
