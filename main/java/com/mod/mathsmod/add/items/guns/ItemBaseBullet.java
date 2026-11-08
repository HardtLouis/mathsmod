package com.mod.mathsmod.add.items.guns;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.CSE.ClientSideEnt;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.blocks.BlockBetterTNT;
import com.mod.mathsmod.add.event.MEvents.onBlockImpactEvent;
import com.mod.mathsmod.add.event.MEvents.onImpactEvent;
import com.mod.mathsmod.add.messages.MessageAddsCSE;
import com.mod.mathsmod.add.messages.MessageMiss;
import com.mod.mathsmod.add.messages.MessageOptimisedShoot;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public abstract class ItemBaseBullet extends Item
{
	
	public ItemBaseBullet(String name) {
		this.setMaxStackSize(64);
		this.setCreativeTab(Mathsmod.MATHSMOD_GUNS_TAB).setRegistryName(name).setUnlocalizedName(name);
	}
	
	public float bulletMultiplier(ItemStack stack, ItemGun item, Entity shooter)
	{
		return 1.0F;
	}
	
	public abstract EnumBulletType type();
	
	public void shoot(World world, EntityLivingBase entityIn, ItemGun gun, boolean HunterLeftCanon) {
		
		ItemStack stack = entityIn.getHeldItemMainhand();
		gun.setLastBulletBeforeShot(stack, entityIn);
		if(gun.hasTravel())
		{
			if(entityIn instanceof EntityPlayer && world.isRemote && this.type() != EnumBulletType.GRENADE && this.type() != EnumBulletType.ROCKET)
			{
				this.spawnCSE(world, (EntityPlayer) entityIn, stack, gun);
			}
			else if(!world.isRemote)
			{
				if(entityIn instanceof EntityPlayer && this.type() != EnumBulletType.GRENADE && this.type() != EnumBulletType.ROCKET)
				{
					Mathsmod.network.sendToAllAround(new MessageAddsCSE((EntityPlayer) entityIn, gun.getPower(stack, entityIn), 1, 0, 0, 0), new TargetPoint(entityIn.dimension, entityIn.posX, entityIn.posY, entityIn.posZ, 100));
				}
				else
				{
					this.preSpawn(world, entityIn, stack, gun, HunterLeftCanon, entityIn.rotationYaw, entityIn.rotationPitch, entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
				}
			}
			
		}
		else
		{
			this.preSpawn(world, entityIn, stack, gun, HunterLeftCanon, entityIn.rotationYaw, entityIn.rotationPitch, entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ, false);
		}
	}
	
	protected void spawnCSE(World world, EntityPlayer player, ItemStack stack, ItemGun item) 
	{
		float inaccuracyIn = 1.4F;
		inaccuracyIn = item.getInnacuracy(stack, player) + item.getShootDefault(stack);
		float damagesIn = item.getDamagesCaused() * item.damageMultiplier(stack, player, null);
		if(item.bulletUsed(stack, player) != null)
		{
			damagesIn *= item.bulletUsed(stack, player).bulletMultiplier(stack, item, player);
		}
		
		Random rand = new Random();
		float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
    	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
    	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
    	
    	Vec3d vec = UtilsVoids.laserWithInnacuracy(item.getPower(stack, player), player.rotationYaw, player.rotationPitch, x, y, z);
    	
		ClientSideEnt cls = new ClientSideEnt(player.getPositionEyes(1.0F), vec, vec, player.getName(), damagesIn, this, player.rotationPitch, player.rotationYaw, (int) (item.getReach() / item.getPower()), player.dimension);
		client_Proxy.CSEL.add(cls);
	}
	
	protected void spawnCSE(World world, EntityPlayer player, float rotationPitch, float rotationYaw, float default_damages, ItemStack stack, ItemGun item) 
	{
		float inaccuracyIn = 1.4F;
		inaccuracyIn = item.getInnacuracy(stack, player) + item.getShootDefault(stack);
		float damagesIn = default_damages * item.damageMultiplier(stack, player, null);
		if(item.bulletUsed(stack, player) != null)
		{
			damagesIn *= item.bulletUsed(stack, player).bulletMultiplier(stack, item, player);
		}
		
		Random rand = new Random();
		float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
    	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
    	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracyIn);
    	
    	Vec3d vec = UtilsVoids.laserWithInnacuracy(item.getPower(stack, player), rotationYaw, rotationPitch, x, y, z);
    	
		ClientSideEnt cls = new ClientSideEnt(player.getPositionEyes(1.0F), vec, vec, player.getName(), damagesIn, this, rotationPitch, rotationYaw, (int) (item.getReach() / item.getPower()), player.dimension);
		client_Proxy.CSEL.add(cls);
	}

	public boolean isDestructive()
	{
		return true;
	}
	
	public void shootEvent(World world, EntityLivingBase entityIn, ItemGun gun, boolean HunterLeftCanon, Entity bullet) 
	{
		ItemStack stack = entityIn.getHeldItemMainhand();
		if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new com.mod.mathsmod.add.event.MEvents.onShotEvent(entityIn, world, stack, gun, bullet))) return;
		this.shoot(world, entityIn, gun, HunterLeftCanon);
	}
	
	public void preSpawn(World worldIn, Entity entityIn, ItemStack stack, ItemGun gun, boolean HunterLeftClick, float rotationYaw, float rotationPitch, double posX, double posY, double posZ, boolean antiVehicles)
    {
		boolean travel = gun.hasTravel();
		float reachIn = gun.getReach();
		float inaccuracyIn = 1.4F;
		
		if(entityIn instanceof EntityLivingBase)
		{
			inaccuracyIn = gun.getInnacuracy(stack, entityIn) + gun.getShootDefault(stack);
		}
		
    	if(!travel && entityIn instanceof EntityPlayer)
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
        				float damagesIn = gun.getDamagesCaused() * gun.damageMultiplier(stack, entityIn, null);
            			if(gun.bulletUsed(stack, entityIn) != null)
            			{
            				damagesIn *= gun.bulletUsed(stack, entityIn).bulletMultiplier(stack, gun, entityIn);
            			}
            			
        				pos = UtilsVoids.collideBlock(worldIn, posVec);
        				Mathsmod.network.sendToServer(new MessageOptimisedShoot(damagesIn, x, y, z, rotationYaw, rotationPitch, null, pos, (float) (posY + vec.y)));
            			return;
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
            				float damagesIn = gun.getDamagesCaused() * gun.damageMultiplier(stack, entityIn, null);
                			if(gun.bulletUsed(stack, entityIn) != null)
                			{
                				damagesIn *= gun.bulletUsed(stack, entityIn).bulletMultiplier(stack, gun, entityIn);
                			}
                			Mathsmod.network.sendToServer(new MessageOptimisedShoot(damagesIn, x, y, z, rotationYaw, rotationPitch, target, null, (float) (posY + vec.y)));
            				return;
            			}
        			}
            	}
            	Mathsmod.network.sendToServer(new MessageMiss());
    		}
        }
    	else if(!travel)
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
            			this.onImpact(new RayTraceResult(posVec, UtilsVoids.getDirectionFromEntity(pos, entityIn), pos), worldIn, entityIn, null, rotationYaw, rotationPitch, posX, posY, posZ, stack, gun, posX, posY, posZ, antiVehicles);
            			return;
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
            				this.onImpact(new RayTraceResult(target), worldIn, entityIn, target, rotationYaw, rotationPitch, vec.x, vec.y, vec.z, stack, gun, posX, posY, posZ, antiVehicles);
            				return;
            			}
        			}
        		}
        	}
    	}
    }
	
	public void onImpact(RayTraceResult result, World worldIn, Entity entityIn, Entity shotEntity, float rotationYaw, float rotationPitch, double posX, double posY, double posZ, ItemStack stack, ItemGun item, double basePosX, double basePosY, double basePosZ, boolean antiVehicles)
	{
		if(entityIn instanceof EntityLivingBase)
		{
			stack = ((EntityLivingBase) entityIn).getHeldItemMainhand();
		}
		
		float damagesIn = item.getDamagesCaused() * item.damageMultiplier(stack, entityIn, null);
		if(item.bulletUsed(stack, entityIn) != null)
		{
			damagesIn *= item.bulletUsed(stack, entityIn).bulletMultiplier(stack, item, entityIn);
		}
		
		if(onImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.entityHit, damagesIn) != 0)
		{
			damagesIn = onImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.entityHit, damagesIn);
			
			if(result.entityHit != null && result.entityHit != entityIn && !worldIn.isRemote)
			{
				float i = damagesIn;
				if(entityIn != null && UtilsVoids.isBackstabing(entityIn, result.entityHit) && stack.getItem() instanceof ItemGun)
				{
					i *= ((ItemGun) stack.getItem()).backMultiplier();
				}
				if(posY >= result.entityHit.getEyeHeight() + result.entityHit.posY && stack.getItem() instanceof ItemGun && result.entityHit instanceof EntityLivingBase)
				{
					i *= ((ItemGun) stack.getItem()).HeadMultiplier();
					if(((ItemGun) stack.getItem()).HeadMultiplier() > 1.0F)
					{
						ItemGun.makeSuperHit(result.entityHit, 0, entityIn);
					}
				}
				if(!item.lowBulletFar() && item.hasTravel() && entityIn != null && new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(basePosX, basePosY, basePosZ)) > item.getReach())
				{
					i = damagesIn / 4;
				}
				if(entityIn != null && entityIn.getRidingEntity() != null && result.entityHit == entityIn.getRidingEntity())
				{
					return;
				}
				if(result.entityHit instanceof MultiPartEntityPart)
				{
					MultiPartEntityPart entity = (MultiPartEntityPart) result.entityHit;
					if(entity.parent instanceof EntityLivingBase)
					{
						UtilsVoids.attackEntityFrom((EntityLivingBase) ((EntityLivingBase) entity.parent), MDamageSource.causeThrownDamage(entityIn), i);
					}
					if(entity.parent instanceof EntityTank)
					{
						entity.parent.attackEntityFromPart(entity, MDamageSource.causeThrownDamage(entityIn), i);
					}
					if(entity.parent instanceof EntityPlane && antiVehicles)
					{
						entity.parent.attackEntityFromPart(entity, MDamageSource.causeThrownDamage(entityIn), ((EntityPlane) entity.parent).maxDamages() / 20);
					}
				}
				else if(result.entityHit instanceof EntityLivingBase)
				{
					UtilsVoids.attackEntityFrom((EntityLivingBase) result.entityHit, MDamageSource.causeThrownDamage(entityIn), i);
				}
				else
				{
					if(result.entityHit instanceof EntityTank)
					{
						result.entityHit.attackEntityFrom(MDamageSource.causeThrownDamage(entityIn), i);
					}
					else if(result.entityHit instanceof EntityPlane && antiVehicles)
					{
						result.entityHit.attackEntityFrom(MDamageSource.causeThrownDamage(entityIn), ((EntityPlane) result.entityHit).maxDamages() / 20);
					}
					else
					{
						result.entityHit.attackEntityFrom(MDamageSource.causeThrownDamage(entityIn), i);
					}
					
				}
				return;
			}
			if(result.typeOfHit.BLOCK == result.typeOfHit && worldIn.getBlockState(result.getBlockPos()).getCollisionBoundingBox(worldIn, result.getBlockPos()) != null && worldIn.getBlockState(result.getBlockPos()).getCollisionBoundingBox(worldIn, result.getBlockPos()) != Block.NULL_AABB)
			{
				if(onBlockImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.getBlockPos(), damagesIn))
				{
					if(!worldIn.isRemote)
					{
						BlockPos pos = result.getBlockPos();
						Block block = worldIn.getBlockState(pos).getBlock();
						if(this.CanBreakMaterials().contains(worldIn.getBlockState(pos).getMaterial()) && worldIn.getBlockState(pos).getBlock().getExplosionResistance(entityIn) <= 1)
						{
							worldIn.destroyBlock(pos, false);
						}
						if(block instanceof BlockTNT)
						{
							block.onBlockDestroyedByPlayer(worldIn, pos, worldIn.getBlockState(pos).withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
							worldIn.setBlockToAir(pos);
						}
					}
				}
			}
		}
	}
	
	protected List<Material> CanBreakMaterials()
	{
		List<Material> list = Lists.newArrayList();
		list.add(Material.ROCK);
		list.add(Material.ICE);
		list.add(Material.GLASS);
		return list;
	}
	
}
