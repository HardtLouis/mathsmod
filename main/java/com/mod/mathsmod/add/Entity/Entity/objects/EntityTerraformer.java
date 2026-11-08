package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTerraformer extends Entity implements IVehicle
{
	public float getForward;
	public int cooldown;
	
	
	public EntityTerraformer(World worldIn) 
	{
		super(worldIn);
		this.setSize(4, 3);
		this.isImmuneToFire = true;
		this.stepHeight = 1.5F;
	}
	
	public boolean isPushedByWater()
    {
        return false;
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
	
	public float getSpeed()
	{
		return 0.15F;
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		
		if(this.cooldown > 0)
		{
			--this.cooldown;
		}
		
		if(this.getControllingPassenger() != null)
		{
			if(this.getControllingPassenger() instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) this.getControllingPassenger();
				this.MoveLikeEntity(0, 0, (entity.moveForward));
				this.drill();
				
			}
			else
			{
				this.MoveLikeEntity(0, 0, 0);
			}
		}
		else
		{
			this.MoveLikeEntity(0, 0, 0);
		}
		
	}
	
	public double getMountedYOffset()
    {
        return 2D;
    }
	
	protected boolean canTriggerWalking()
    {
        return false;
    }
	
	public boolean canBePushed()
    {
        return true;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }

    /**
     * Returns the collision bounding box for this entity
     */
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage() && !this.isDead)
    	{
    		if(!world.isRemote && !this.isDead)
    		{
    			world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(RegisterItems.TERRAFORMER)));
    		}
    		this.setDead();
    	}
    	else
    	{
    	}
    	
		return true;
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
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
    
    @Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }
    
    public void MoveLikeEntity(float strafe, float up, float forward)
    {
    	if(this.getControllingPassenger() != null)
    	{
    		this.setRotation(this.getControllingPassenger().rotationYaw, this.getControllingPassenger().rotationPitch);
    	}
    	
        if (this.canPassengerSteer())
        {
        	if(forward != 0)
        	{
        		this.getForward += 0.4F;
        	}
            if (!this.isInWater())
            {
                if (!this.isInLava())
                {
                	float f8 = this.getSpeed();
                    this.moveRelative(strafe, up, forward, f8);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    
                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.1D;
                    }

                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= 0.5;
                    this.motionZ *= 0.5;
                }
                else
                {
                    double d4 = this.posY;
                    this.moveRelative(strafe, up, forward, 0.02F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.02D;
                    }

                    if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d4, this.motionZ))
                    {
                        this.motionY = 0.30000001192092896D;
                    }
                }
            }
            else
            {
                double d0 = this.posY;
                float f1 = this.getWaterSlowDown();
                float f2 = 0.02F;

                this.moveRelative(strafe, up, forward, f2);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double)f1;
                this.motionY *= 0.800000011920929D;
                this.motionZ *= (double)f1;
            }
        }

        double d5 = this.posX - this.prevPosX;
        double d7 = this.posZ - this.prevPosZ;
        double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;

        if (f10 > 1.0F)
        {
            f10 = 1.0F;
        }

    }

	protected float getWaterSlowDown() {
		return 0.5F;
	}
	
	public void fall(float distance, float damageMultiplier)
    {
    }
	
	public void setDead()
    {
		if(this.getControllingPassenger() != null)
		{
			this.world.updateEntityWithOptionalForce(getControllingPassenger(), true);
		}
        super.setDead();
        
    }
	
	public static boolean canDestroyBlock(IBlockState blockIn,World worldIn, BlockPos pos)
    {
        return blockIn.getMaterial() != Material.WATER && blockIn.getMaterial() != Material.LAVA && blockIn.getBlockHardness(worldIn, pos) >= 0 && blockIn.getBlock() != Blocks.BEDROCK && blockIn.getBlock() != Blocks.END_PORTAL && blockIn.getBlock()  != Blocks.END_PORTAL_FRAME && blockIn.getBlock() != Blocks.COMMAND_BLOCK && blockIn.getBlock() != Blocks.REPEATING_COMMAND_BLOCK && blockIn.getBlock() != Blocks.CHAIN_COMMAND_BLOCK && blockIn.getBlock() != Blocks.BARRIER && blockIn.getBlock() != Blocks.STRUCTURE_BLOCK && blockIn.getBlock() != Blocks.STRUCTURE_VOID && blockIn.getBlock() != Blocks.PISTON_EXTENSION && blockIn.getBlock() != Blocks.END_GATEWAY;
    }
	
	private void drill()
	{
		if(this.getControllingPassenger() != null && this.ticksExisted % 12 == 0 && cooldown <= 0 && !world.isRemote)
		{
			if(this.getControllingPassenger() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) this.getControllingPassenger();
				if (this.rotationPitch < 90 && this.rotationPitch > -90)
		        {
		            int i1 = MathHelper.floor(this.posY);
		            int l1 = MathHelper.floor(this.posX);
		            int i2 = MathHelper.floor(this.posZ);
		            boolean flag = false;

		            for (int k2 = -3; k2 <= 3; ++k2)
		            {
		                for (int l2 = -3; l2 <= 3; ++l2)
		                {
		                    for (int j = 0; j <= 3; ++j)
		                    {
		                    	int i3 = l1 + k2;
		                		int l = i2 + l2;
		                        int k = i1 + j;
		                        
		                        BlockPos blockpos = new BlockPos(i3, k, l);
		                        IBlockState iblockstate = this.world.getBlockState(blockpos);
		                        Block block = iblockstate.getBlock();

		                        if (block.getBlockHardness(iblockstate, world, blockpos) >= 0 && !block.isAir(iblockstate, this.world, blockpos) && player.canPlayerEdit(blockpos, player.getAdjustedHorizontalFacing(), player.getHeldItemMainhand()) && this.canDestroyBlock(iblockstate, world, blockpos))
		                        {
		                        	if(block.getMaterial(iblockstate) == Material.ROCK)
		                        	{
		                        		this.cooldown = 20;
		                        	}
		                            flag = this.world.destroyBlock(blockpos, true) || flag;
		                        }
		                    }
		                }
		                if (flag)
			            {
			                this.world.playEvent((EntityPlayer)null, 1022, new BlockPos(this), 0);
			            }
		            }
		        }
			}
		}
	}

	@Override
	public float getHealthBar() 
	{
		return 10;
	}
	
}