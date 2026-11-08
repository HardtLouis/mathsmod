package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.blocks.BlockTurret;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTurret extends Entity implements IVehicle
{
	public int cooldown;
	
	public EntityTurret(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.8F, 0.8F);
	}

	@Override
	protected void entityInit() 
	{
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		
	}
	
	protected int maxCooldown()
	{
		return 2;
	}
	
	@Override
	public void onUpdate() 
	{
		if(cooldown < maxCooldown())
		{
			cooldown +=1;
		}
		if(this.getControllingPassenger() != null)
		{
			this.rotationPitch = this.getControllingPassenger().rotationPitch;
			this.rotationYaw = this.getControllingPassenger().rotationYaw;
		}
		
		if(!(world.getBlockState(new BlockPos(this)).getBlock() instanceof BlockTurret))
		{
			this.setDead();
		}
	}
	
	@Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }
	
	public boolean canShoot()
	{
		return cooldown == maxCooldown();
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage())
    	{
    	}
    	else
    	{
    	}
    	
		return false;
    }
	
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote)
            {
                player.startRiding(this);
            }

            return true;
        }
    }
    
    public void shoot()
    {
    	if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase)
		{
			RegisterItems.SNIPER_BULLET.preSpawn(world, (EntityLivingBase) this.getControllingPassenger(), new ItemStack(RegisterItems.MACHINE_PISTOL), (ItemGun) RegisterItems.MACHINE_PISTOL, false, this.rotationYaw, this.rotationPitch, this.posX, this.posY + 1, this.posZ, true);
		}
		else
		{
			RegisterItems.SNIPER_BULLET.preSpawn(world, this, new ItemStack(RegisterItems.MACHINE_PISTOL), (ItemGun) RegisterItems.MACHINE_PISTOL, false, this.rotationYaw, this.rotationPitch, this.posX, this.posY + 1, this.posZ, true);
		}
		
		cooldown = 0;
		world.playSound(null, this.posX, this.posY + 1, this.posZ, MathsSounds.AA12_SOUND, SoundCategory.AMBIENT, 10.5F, 1.5F + world.rand.nextFloat() * 0.2F);
    }

	@Override
	public float getHealthBar() 
	{
		return ((float) this.cooldown) / ((float) this.maxCooldown());
	}
}
