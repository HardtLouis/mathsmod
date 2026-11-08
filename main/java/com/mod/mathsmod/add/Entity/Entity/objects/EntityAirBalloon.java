package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityAirBalloon extends Entity implements IEntityMultiPart
{
	protected static final DataParameter<Float> MONTEE = EntityDataManager.<Float>createKey(EntityAirBalloon.class, DataSerializers.FLOAT);

    public EntityAirBalloon(World worldIn) 
    {
		super(worldIn);
		this.setSize(1.5F, 3F);
	}
	@Override
	public World getWorld() 
	{
		return world;
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) 
	{
		return this.attackEntityFrom(source, damage * 10);
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if(source.getTrueSource() instanceof EntityPlayer && this.getPassengers().isEmpty() && !source.isProjectile() && !source.isMagicDamage() && !world.isRemote && !this.isDead)
    	{
    		((EntityPlayer)source.getTrueSource()).dropItem(new ItemStack(RegisterItems.AIR_BALLOON), true);
    		this.setDead();
    	}
		else if(source.isProjectile())
		{
			this.setMontee(-1);
			if(this.getControllingPassenger() != null)
			{
				this.getControllingPassenger().attackEntityFrom(source, amount * 0.4F);
			}
		}
		else
		{
			if(this.getControllingPassenger() != null)
			{
				this.getControllingPassenger().attackEntityFrom(source, amount * 0.4F);
			}
		}
		return false;
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		if(this.onGround || this.inWater || this.isInLava())
		{
			this.motionX *= 0.9;
			this.motionZ *= 0.9;
			
			if(this.motionY < 0)
			{
				this.motionY = 0.0001;
			}
			
			if(this.getMontee() < 0)
			{
				this.setMontee(0);
			}
		}
		
		this.move(MoverType.SELF, this.motionX, this.motionY + this.dataManager.get(MONTEE), this.motionZ);
		
		if(this.getPassengers().isEmpty())
		{
			this.setMontee(this.getMontee() * 0.99F);
		}
		
		this.motionX *= 0.99;
		this.motionZ *= 0.99;
		
		this.motionY *= 0.9;
		this.motionY -= 0.002;
	}
	
	public void onCollideWithPlayer(EntityPlayer entityIn)
    {
		super.onCollideWithPlayer(entityIn);
    }
	
	public float getMontee()
	{
		return this.dataManager.get(MONTEE);
	}
	
	public void setMontee(float montee)
	{
		this.dataManager.set(MONTEE, montee);
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(MONTEE, 0.0F);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
	}
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }
	
	public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
	
    protected boolean canFitPassenger(Entity passenger)
    {
        return this.getPassengers().size() < 4;
    }
	
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
    	if (!this.world.isRemote && this.canFitPassenger(player) && !this.isPassenger(player))
        {
    		player.startRiding(this);
        }
    	
    	if(this.isPassenger(player) && player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.ANVIL))
    	{
    		EntityFallingBlock anvil = new EntityFallingBlock(this.world, player.posX, player.posY - 1, player.posZ, Blocks.ANVIL.getDefaultState());
    		if(!world.isRemote)
    		{
    			anvil.fallTime = -1000;
    			anvil.setHurtEntities(true);
    			world.spawnEntity(anvil);
    			if(!player.capabilities.isCreativeMode)
    			{
        			player.getHeldItemMainhand().shrink(1);
    			}
    		}
    	}
        return false;
    }
	
    @Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }
    
    public double getMountedYOffset()
    {
        return 0.5F;
    }
	
	public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
            float f = 0.0F;
            float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());
            
            int i = 1 + this.getPassengers().indexOf(passenger);
            f = 0.5F;

            Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - (((float)Math.PI / 2F) * i));
            passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
        }
    }
	
	public float manSpeed() 
	{
		return 1F;
	}
}
