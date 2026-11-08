package com.mod.mathsmod.add.items.guns;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsTriggers;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageAddsCSE;
import com.mod.mathsmod.add.messages.MessageMiss;
import com.mod.mathsmod.add.messages.MessageOptimisedShoot;
import com.mod.mathsmod.add.messages.MessagePelletsConnecter;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class ItemBuckshot extends ItemBaseBullet implements IGunCraft
{

	public ItemBuckshot() {
		super("buckshot");
	}

	@Override
	public EnumBulletType type() 
	{
		return EnumBulletType.SHOTGUN;
	}
	
	public float bulletMultiplier(ItemStack stack, ItemGun item, Entity shooter)
	{
		if(item instanceof ItemShotGun)
		{
			return 1.0F / (((ItemShotGun) item).getBulletsSquareNumber() * ((ItemShotGun) item).getBulletsSquareNumber());
		}
		else
		{
			return 0.0625F;
		}
	}
	
	
	@Override
	public void shoot(World world, EntityLivingBase entityIn, ItemGun gun, boolean lchs) 
	{
		ItemStack stack = entityIn.getHeldItemMainhand();
		int bulletMiss = 0;
		int total;
		Entity target = null;
		HashMap<Entity, Integer> map = Maps.newHashMap();
		
		if(gun instanceof ItemShotGun)
		{
			total = ((ItemShotGun) gun).getBulletsSquareNumber() * ((ItemShotGun) gun).getBulletsSquareNumber();
			for(float i = 0; i < ((ItemShotGun) gun).getBulletsSquareNumber(); ++i)
			{
				for(float j = 0; j < ((ItemShotGun) gun).getBulletsSquareNumber(); ++j)
				{
					if(((ItemShotGun) gun).getBulletsSquareNumber() % 2 == 1)
					{
						float f = i - ((ItemShotGun) gun).getBulletsSquareNumber() / 2;
						f *= ((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand());
						
						float g = j - ((ItemShotGun) gun).getBulletsSquareNumber() / 2;
						g *= ((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand());
						
						
						if(gun.hasTravel())
						{
							if(entityIn instanceof EntityPlayer)
							{
								if(world.isRemote)
								{
									this.spawnCSE(world, (EntityPlayer) entityIn, entityIn.rotationPitch + f, entityIn.rotationYaw + g, gun.getDamagesCaused() / (((ItemShotGun) gun).getBulletsSquareNumber()), stack, gun);
								}
								else
								{
									Mathsmod.network.sendToAllAround(new MessageAddsCSE((EntityPlayer) entityIn, entityIn.rotationPitch + f, entityIn.rotationYaw + g, gun.getPower(stack, entityIn), 1, 0, 0, 0), new TargetPoint(entityIn.dimension, entityIn.posX, entityIn.posY, entityIn.posZ, 100));
								}
								
							}
							else
							{
								target = this.preSpawnInt(world, entityIn, gun, lchs, entityIn.rotationYaw + g, entityIn.rotationPitch + f, entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
								bulletMiss += target == null ? 1 : 0;
							}
						}
						else
						{
							target = this.preSpawnInt(world, entityIn, gun, lchs, entityIn.rotationYaw + g, entityIn.rotationPitch + f, entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
							bulletMiss += target == null ? 1 : 0;
						}
					}
					else
					{
						float dam = gun.getDamagesCaused() / (((ItemShotGun) gun).getBulletsSquareNumber() * ((ItemShotGun) gun).getBulletsSquareNumber());
						//dam *= (0.75 / dam);
						
						float f = i - ((ItemShotGun) gun).getBulletsSquareNumber() / 2;
						f *= ((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand());
						
						float g = j - ((ItemShotGun) gun).getBulletsSquareNumber() / 2;
						g *= ((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand());
						
						
						if(gun.hasTravel())
						{
							if(entityIn instanceof EntityPlayer)
							{
								if(world.isRemote)
								{
									this.spawnCSE(world, (EntityPlayer) entityIn, entityIn.rotationPitch + f + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), entityIn.rotationYaw + g + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), gun.getDamagesCaused() / (((ItemShotGun) gun).getBulletsSquareNumber()), stack, gun);
								}
								else
								{
									Mathsmod.network.sendToAllAround(new MessageAddsCSE((EntityPlayer) entityIn, entityIn.rotationPitch + f + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), entityIn.rotationYaw + g + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), gun.getPower(stack, entityIn), 1, 0, 0, 0), new TargetPoint(entityIn.dimension, entityIn.posX, entityIn.posY, entityIn.posZ, 100));
								}
							}
							else
							{
								target = this.preSpawnInt(world, entityIn, gun, lchs, entityIn.rotationPitch + f + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), entityIn.rotationYaw + g + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
								bulletMiss += target == null ? 1 : 0;
							}
						}
						else
						{
							target = this.preSpawnInt(world, entityIn, gun, lchs, entityIn.rotationYaw + g + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), entityIn.rotationPitch + f + (((ItemShotGun) gun).getBuckshotDecal(entityIn, entityIn.getHeldItemMainhand()) / 2), entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
							bulletMiss += target == null ? 1 : 0;
						}
					}
				}
			}
		}
		else
		{
			total = 16;
			for(float i = -1.5F * 15; i <= 1.5F * 0.5F * 15; i += 15)
			{
				for(float j = -1.5F * 15; j <= 1.5F * 0.5F * 15; j += 15)
				{
					if(gun.hasTravel())
					{
						if(entityIn instanceof EntityPlayer)
						{
							if(world.isRemote)
							{
								this.spawnCSE(world, (EntityPlayer) entityIn, entityIn.rotationPitch + i + 7.5F, entityIn.rotationYaw + j + 7.5F, gun.getDamagesCaused() / (((ItemShotGun) gun).getBulletsSquareNumber()), stack, gun);
							}
							else
							{
								Mathsmod.network.sendToAllAround(new MessageAddsCSE((EntityPlayer) entityIn, entityIn.rotationPitch + i + 7.5F, entityIn.rotationYaw + j + 7.5F, gun.getPower(stack, entityIn), 1, 0, 0, 0), new TargetPoint(entityIn.dimension, entityIn.posX, entityIn.posY, entityIn.posZ, 100));
							}
							
						}
						else
						{
							target = this.preSpawnInt(world, entityIn, gun, lchs, entityIn.rotationPitch + i + 7.5F, entityIn.rotationYaw + j + 7.5F, entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
							bulletMiss += target == null ? 1 : 0;
						}
					}
					else
					{
						target = this.preSpawnInt(world, entityIn, gun, lchs, entityIn.rotationYaw + j, entityIn.rotationPitch + i, entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
						bulletMiss += target == null ? 1 : 0;
					}
				}
			}
		}
		
		if(world.isRemote && bulletMiss > total / 3)
		{
			//System.out.print(bulletMiss + "/" + total);
			//System.out.print(System.lineSeparator());
        	Mathsmod.network.sendToServer(new MessageMiss());
		}
		
		if(world.isRemote && gun instanceof IPelletConnecter)
		{
			((IPelletConnecter) gun).onPelletsHit(stack, entityIn, target, bulletMiss);
			Mathsmod.network.sendToServer(new MessagePelletsConnecter(bulletMiss, target));
		}
	}
	
	public Entity preSpawnInt(World worldIn, EntityLivingBase entityIn, ItemGun gun, boolean HunterLeftClick, float rotationYaw, float rotationPitch, double posX, double posY, double posZ, boolean antiVehicles)
    {
		float reachIn = gun.getReach();
		float inaccuracyIn = gun.getInnacuracy(entityIn.getHeldItemMainhand(), entityIn) + gun.getShootDefault(entityIn.getHeldItemMainhand());
		
    	if(!gun.hasTravel() && entityIn instanceof EntityPlayer)
        {
    		if(worldIn.isRemote)
    		{
    			Random rand = new Random();
            	float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
            	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
            	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
            	
            	for(double i = 0; i <= reachIn; i += 0.25)
            	{
            		Vec3d vec = UtilsVoids.laserWithInnacuracy(i, rotationYaw, rotationPitch, x, y, z);
            		worldIn.spawnAlwaysVisibleParticle(60, posX + vec.x, posY + vec.y, posZ + vec.z, 0, 0, 0);
            		
            		Vec3d posVec = new Vec3d(posX + vec.x, posY + vec.y, posZ + vec.z);
        			BlockPos pos = new BlockPos(posX + vec.x, posY + vec.y, posZ + vec.z);
        			
        			if(UtilsVoids.collideBlock(worldIn, posVec) != null)
        			{
        				pos = UtilsVoids.collideBlock(worldIn, posVec);
        				Mathsmod.network.sendToServer(new MessageOptimisedShoot(gun.getDamagesCaused() * gun.damageMultiplier(entityIn.getHeldItemMainhand(), entityIn, null) * this.bulletMultiplier(entityIn.getHeldItemMainhand(), gun, entityIn), x, y, z, rotationYaw, rotationPitch, null, pos, (float) (posY + vec.y)));
            			return null;
        			}
            		
        			Entity target = null;
        			if(this != null)
        			{
        				CopyOnWriteArrayList<Entity> entities = UtilsVoids.getHitEntities(worldIn, null, posVec, (float) reachIn);
            			for(Entity entity : entities)
            			{
            				if(entityIn == null)
            				{
            					target = entity;
            				}
            				else if(entity != entityIn)
            				{
            					if(entityIn.getRidingEntity() != null && entity == entityIn.getRidingEntity())
            					{
            					}
            					else if(entityIn.getRidingEntity() != null && entity == entityIn.getRidingEntity())
            					{
            					}
            					else
            					{
                					target = entity;
            					}
            				}
            			}
            			
            			if(target != null)
            			{
                			Mathsmod.network.sendToServer(new MessageOptimisedShoot(gun.getDamagesCaused() * gun.damageMultiplier(entityIn.getHeldItemMainhand(), entityIn, target) * this.bulletMultiplier(entityIn.getHeldItemMainhand(), gun, entityIn), x, y, z, rotationYaw, rotationPitch, target, null, (float) (posY + vec.y)));
            				return target;
            			}
        			}
            	}
            	return null;
    		}
        }
    	else if(!gun.hasTravel())
    	{
    		Random rand = new Random();
        	float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
        	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
        	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
        	
        	for(double i = 0; i <= reachIn; i += 0.25)
        	{
        		Vec3d vec = UtilsVoids.laserWithInnacuracy(i, rotationYaw, rotationPitch, x, y, z);
        		worldIn.spawnAlwaysVisibleParticle(60, posX + vec.x, posY + vec.y, posZ + vec.z, 0, 0, 0);
        		
        		if(!worldIn.isRemote)
        		{
        			Vec3d posVec = new Vec3d(posX + vec.x, posY + vec.y, posZ + vec.z);
        			BlockPos pos;
        			
        			if(UtilsVoids.collideBlock(worldIn, posVec) != null)
        			{
        				pos = UtilsVoids.collideBlock(worldIn, posVec);
            			this.onImpact(new RayTraceResult(posVec, UtilsVoids.getDirectionFromEntity(pos, entityIn), pos), worldIn, entityIn, null, rotationYaw, rotationPitch, posX, posY, posZ, entityIn.getHeldItemMainhand(), gun, posX, posY, posZ, antiVehicles);
            			return null;
        			}
            		
        			Entity target = null;
        			if(this != null)
        			{
        				CopyOnWriteArrayList<Entity> entities = UtilsVoids.getHitEntities(worldIn, null, posVec, (float) reachIn);
            			for(Entity entity : entities)
            			{
            				if(entityIn == null)
            				{
            					target = entity;
            				}
            				else if(entity != entityIn)
            				{
            					if(entityIn.getRidingEntity() != null && entity == entityIn.getRidingEntity())
            					{
            					}
            					else if(entityIn.getRidingEntity() != null && entity == entityIn.getRidingEntity())
            					{
            					}
            					else
            					{
                					target = entity;
            					}
            				}
            			}
            			
            			if(target != null)
            			{
            				this.onImpact(new RayTraceResult(target), worldIn, entityIn, target, rotationYaw, rotationPitch, vec.x, vec.y, vec.z, entityIn.getHeldItemMainhand(), gun, posX, posY, posZ, antiVehicles);
            				return target;
            			}
        			}
        		}
        	}
    	}
    	return null;
    }
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.IRON_INGOT));
		list.set(4, new ItemStack(Items.IRON_INGOT));
		list.set(1, new ItemStack(Items.GUNPOWDER));
		list.set(5, new ItemStack(Items.GUNPOWDER));
		list.set(2, new ItemStack(Items.IRON_NUGGET));
		list.set(6, new ItemStack(Items.IRON_NUGGET));
		list.set(3, new ItemStack(Items.IRON_NUGGET));
		list.set(7, new ItemStack(Items.IRON_NUGGET));
		
		return list;
	}

	@Override
	public int size() 
	{
		return 12;
	}

	@Override
	public boolean displayRecipe()
	{
		return true;
	}

}
