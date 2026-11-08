package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityHappyMazeFoe extends EntityLiving implements IOnShot
{
	
	private static final DataParameter<Integer> USED_COMPORTMENT_ID = EntityDataManager.<Integer>createKey(EntityHappyMazeFoe.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TIMER = EntityDataManager.<Integer>createKey(EntityHappyMazeFoe.class, DataSerializers.VARINT);
	private static final DataParameter<Float> YAW = EntityDataManager.<Float>createKey(EntityHappyMazeFoe.class, DataSerializers.FLOAT);
	private String targetName;
	int lookTime = 0;
	float baseYaw;
	float lastTargetYaw;
	Vec3d posToInvestigate;
	Vec3d lastPredict;
	int seenTargetTick;
	int escapeCounter;
	int succeededEscapes;
	List<Vec3d> zones = Lists.newArrayList();
	
	
	public EntityHappyMazeFoe(World worldIn) 
	{
		super(worldIn);
		this.stepHeight = 1.0F;
	}
	
	protected void entityInit()
    {
		super.entityInit();
        this.dataManager.register(USED_COMPORTMENT_ID, 0);
        this.dataManager.register(TIMER, 0);
        this.dataManager.register(YAW, 0.0F);
    }

	public void onUpdate()
	{
		super.onUpdate();
		
		EntityPlayer tar = null;
		
		if(!world.isRemote)
		{
			for(EntityPlayer ent : UtilsVoids.getEntities(EntityPlayer.class, world, this, 50))
			{
				if(!ent.capabilities.isCreativeMode)
				{
					Vec3d vec1 = ent.getPositionVector().add(UtilsVoids.rotateVec(new Vec3d(0.6, 0, 0), 0, ent.rotationYaw * this.rotationYaw * 0.01745329251994329, 0));
					Vec3d vec2 = ent.getPositionVector().add(UtilsVoids.rotateVec(new Vec3d(0.6, 0, 0), 0, ent.rotationYaw * this.rotationYaw * 0.01745329251994329, 0));
					Vec3d vec3 = ent.getPositionVector();
					Vec3d vec4 = ent.getPositionEyes(1.0F).addVector(0, 0.2, 0);
					
					if(world.rayTraceBlocks(this.getPositionEyes(1.0F), vec1, false, false, false) == null
							|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec2, false, false, false) == null
							|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec3, false, false, false) == null
							|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec4, false, false, false) == null)
					{
						if(UtilsVoids.dotProductProjXZ(vec3.subtract(this.getPositionVector()), this.getLookVec()) > 0.1)
						{
							if(tar == null || tar.getDistance(this) > ent.getDistance(this))
							{
								tar = ent;
							}
						}
						else if(ent.getDistance(this) < 10 && (ent.motionX != 0 || ent.motionZ != 0 || (ent.motionY == 0 && ent.prevPosY != ent.posY)))
						{
							this.goInvestigate(ent);
						}
					}
				}
			}
			
			if(tar == null)
			{
				this.seenTargetTick = 0;
			}
			else
			{
				if(this.seenTargetTick < 10)
				{
					++this.seenTargetTick;
					this.goInvestigate(tar);
				}
				else
				{
					if(this.succeededEscapes < 5 || this.getRNG().nextInt(3) == 0)
					{
						this.targetName = tar.getName();
						this.lastPredict = tar.getPositionVector().addVector(tar.motionX * 60, 0, tar.motionZ * 60);
						this.escapeCounter = 10;
					}
					else
					{
						this.targetName = tar.getName();
						this.lastPredict = tar.getPositionVector().addVector(tar.motionX, 0, tar.motionZ);
						this.escapeCounter = 10;
						this.setComportmentTo(2);
					}
				}
			}
			
			if(this.escapeCounter > 0)
			{
				--this.escapeCounter;
			}
			
			if(this.targetName != null && world.getPlayerEntityByName(this.targetName) != null)
			{
				BlockPos pos = this.getPosition().add(this.getRNG().nextInt(301) - 150, 0, (this.getRNG().nextInt(301) - 150));
				Vec3d vec1 = world.getPlayerEntityByName(this.targetName).getPositionVector().add(UtilsVoids.rotateVec(new Vec3d(0.6, 0, 0), 0, world.getPlayerEntityByName(this.targetName).rotationYaw * this.rotationYaw * 0.01745329251994329, 0));
				Vec3d vec2 = world.getPlayerEntityByName(this.targetName).getPositionVector().add(UtilsVoids.rotateVec(new Vec3d(0.6, 0, 0), 0, world.getPlayerEntityByName(this.targetName).rotationYaw * this.rotationYaw * 0.01745329251994329, 0));
				Vec3d vec3 = world.getPlayerEntityByName(this.targetName).getPositionVector();
				Vec3d vec4 = world.getPlayerEntityByName(this.targetName).getPositionEyes(1.0F).addVector(0, 0.2, 0);
				
				if(escapeCounter > 0 || world.rayTraceBlocks(this.getPositionEyes(1.0F), vec1, false, false, false) == null
				|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec2, false, false, false) == null
				|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec3, false, false, false) == null
				|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec4, false, false, false) == null)
				{
					Path path = this.getNavigator().getPathToEntityLiving(world.getPlayerEntityByName(this.targetName));
					this.navigator.setPath(path, 2.5F);
				}
				else
				{
					Path path = this.getNavigator().getPathToXYZ(this.lastPredict.x, this.lastPredict.y, this.lastPredict.z);
					this.navigator.setPath(path, 2.5F);
					
					if(this.getNavigator().getPath() == null || this.getNavigator().getPath().isFinished())
					{
						this.succeededEscapes += 1;
						this.targetName = null;
					}
				}
			}
			else if(this.getUsedComportment() == 0)
			{
				EntityPlayer ent = world.getNearestAttackablePlayer(this, 100, 100);
				if(this.zones.isEmpty() && ent != null)
				{
					Vec3d origine = ent.getPositionVector();
					float tetaInit = this.getRNG().nextFloat() * 360 - 180;
					int dir = 1 - this.getRNG().nextInt(2) * 2;
					for(int i = 0; i < 6; ++i)
					{
						this.zones.add(origine.add(UtilsVoids.laser(10 + 20 * this.getRNG().nextFloat(), tetaInit + i * dir * 60 + this.getRNG().nextFloat() * 30 - 15.0F, 0)));
					}
				}
				else if(!this.zones.isEmpty())
				{
					Vec3d vec = this.zones.get(0);
					this.zones.remove(0);
					this.goInvestigatePos(vec);
				}
				else
				{
					Vec3d origine = this.getPositionVector();
					float tetaInit = this.getRNG().nextFloat() * 360 - 180;
					int dir = 1 - this.getRNG().nextInt(2) * 2;
					for(int i = 0; i < 6; ++i)
					{
						this.zones.add(origine.add(UtilsVoids.laser(10 + 20 * this.getRNG().nextFloat(), tetaInit + i * dir * 60 + this.getRNG().nextFloat() * 30 - 15.0F, 0)));
					}
				}
			}
			else if(this.getUsedComportment() == 1)
			{
				if(this.getNavigator() != null && (this.getNavigator().getPath() == null || this.getNavigator().getPath().isFinished()))
				{
					this.lookTime = 40;
					this.setComportmentTo(2);
				}
			}
			else if(this.getUsedComportment() == 2)
			{
				if(this.lookTime > 0)
				{
					--this.lookTime;
					this.listenCarefully();
					this.rotationYaw = this.baseYaw + MathHelper.sin(((float) lookTime) / 40.0F * 3.1415F) * 45.0F;
				}
				else
				{
					this.lookTime = 0;
					this.setComportmentTo(0);
				}
			}
			

			this.setCommonYaw(this.rotationYaw);
		}
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.205D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(30D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }
	
	public void goInvestigate(Entity detected)
	{
		if(detected != null)
		{
			Path path = this.getNavigator().getPathToEntityLiving(detected);
			this.navigator.setPath(path, 1.2F);
			this.setComportmentTo(1);
		}
	}
	
	public void goInvestigatePos(Vec3d vec)
	{
		Path path = this.getNavigator().getPathToXYZ(vec.x, vec.y, vec.z);
		this.navigator.setPath(path, 1.2F);
		this.setComportmentTo(1);
	}
	
	public void setComportmentTo(Integer id)
	{
		if(id <= 1)
		{
			this.navigator.setPath(this.navigator.getPath(), 1.2);
		}
		else if(id == 2)
		{
			this.navigator.setPath(this.navigator.getPath(), 0);
			this.baseYaw = this.rotationYaw + (this.getRNG().nextBoolean() ? 0 : 180);
			this.lookTime = 80;
		}
		this.dataManager.set(USED_COMPORTMENT_ID, id);
	}
	
	public int getUsedComportment()
	{
		return this.dataManager.get(USED_COMPORTMENT_ID);
	}
	
	public void setCommonYaw(float f)
	{
		this.dataManager.set(YAW, f);
	}
	
	public float getCommonYaw()
	{
		return this.dataManager.get(YAW);
	}
	
	public void setTimer(int i)
	{
		this.dataManager.set(TIMER, i);
	}
	
	public int getTimer()
	{
		return this.dataManager.get(TIMER);
	}
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
        compound.setInteger("usedComportment", this.getUsedComportment());
        compound.setInteger("timer", this.getTimer());
        if(this.targetName != null && !this.targetName.isEmpty())
        {
        	compound.setString("target_name", this.targetName);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
        this.setComportmentTo(compound.getInteger("usedComportment"));
        this.setTimer(compound.getInteger("timer"));
        if(compound.hasKey("target_name"))
        {
        	this.targetName = compound.getString("target_name");
        }
    }
    
    public static class PathNavigateMob extends PathNavigateGround
    {
    	public PathNavigateMob(EntityLiving entitylivingIn, World worldIn)
	    {
	        super(entitylivingIn, worldIn);
	        this.setBreakDoors(true);
	        this.setCanSwim(true);
	        this.setEnterDoors(true);
	    }
    }
    
    public void listenCarefully()
    {
    	for(EntityPlayer ent : UtilsVoids.getEntities(EntityPlayer.class, world, this, 40))
		{
    		if(!ent.capabilities.isCreativeMode)
    		{
    			Vec3d vec1 = ent.getPositionVector().add(UtilsVoids.rotateVec(new Vec3d(0.6, 0, 0), 0, ent.rotationYaw * this.rotationYaw * 0.01745329251994329, 0));
    			Vec3d vec2 = ent.getPositionVector().add(UtilsVoids.rotateVec(new Vec3d(0.6, 0, 0), 0, ent.rotationYaw * this.rotationYaw * 0.01745329251994329, 0));
    			Vec3d vec3 = ent.getPositionVector();
    			Vec3d vec4 = ent.getPositionEyes(1.0F).addVector(0, 0.2, 0);
    			
    			if(world.rayTraceBlocks(this.getPositionEyes(1.0F), vec1, false, false, false) == null
    					|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec2, false, false, false) == null
    					|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec3, false, false, false) == null
    					|| world.rayTraceBlocks(this.getPositionEyes(1.0F), vec4, false, false, false) == null)
    			{
    				if((ent.getDistance(this) < 12))
    				{
    					this.goInvestigate(ent);
    				}
    			}
    			else if(!ent.isSneaking() && (ent.motionX != 0 || ent.motionZ != 0 || (ent.motionY == 0 && ent.prevPosY != ent.posY)))
				{
					this.goInvestigate(ent);
				}
    			else if(this.getRNG().nextFloat() > 0.998 && ent.getDistance(this) < 30)
    			{
					this.goInvestigate(ent);
    			}
    		}
		}
    }

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
		this.goInvestigate(shooter);
	}
}