package com.mod.mathsmod.add.World;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.IOnShot;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBounceProjectile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRemoteGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTargetDrone;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurretAuto;
import com.mod.mathsmod.add.Entity.Entity.objects.magic.EntityReincarnedPlayer;
import com.mod.mathsmod.add.World.dims.teleporter.CopravegTeleporter;
import com.mod.mathsmod.add.items.IHeavyHitterItem;
import com.mod.mathsmod.add.items.IReachWeapon;
import com.mod.mathsmod.add.items.ItemAbstractProtectionShield;
import com.mod.mathsmod.add.items.ItemAmmoBox;
import com.mod.mathsmod.add.items.ItemSmasher;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;
import com.mod.mathsmod.add.items.ItemArmor.IExplosionProtection;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.messages.MessageShowDamages;
import com.mod.mathsmod.utils.DamageSourceGun;
import com.mod.mathsmod.utils.ItemStackUtils;
import com.mod.mathsmod.utils.KillsCounter;
import com.mod.mathsmod.utils.MDamageSource;
import com.mod.mathsmod.utils.playerNbtManager;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

public class UtilsVoids {
	
	public static UtilsVoids Instance()
	{
		return new UtilsVoids();
	}
	
	public static <T extends Entity> List<T> getEntitiesWU(Class <? extends T > entityType, World world, Entity base, float dis)
    {
        List<T> list = Lists.<T>newArrayList();
        
        for (Entity entity : world.loadedEntityList)
        {
            if (entityType.isAssignableFrom(entity.getClass()) && (base.getDistance(entity) <= dis || dis < 0))
            {
                list.add((T)entity);
            }
        }

        return list;
    }
	
	public static <T extends Entity> List<T> getEntities(Class <? extends T > entityType, World world, Entity base, float dis)
    {
        List<T> list = Lists.<T>newArrayList();
        
        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
            if (entityType.isAssignableFrom(entity.getClass()) && (base.getDistance(entity) <= dis || dis < 0))
            {
                list.add((T)entity);
            }
        }

        return list;
    }
	
	public static <T extends Entity> List<T> getEntities(Class <? extends T > entityType, World world, Vec3d base, float dis)
    {
        List<T> list = Lists.<T>newArrayList();
        
        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
            if (entityType.isAssignableFrom(entity.getClass()) && (base.distanceTo(entity.getPositionVector()) <= dis || dis < 0))
            {
                list.add((T)entity);
            }
        }

        return list;
    }
	
	public static List<Entity> getEntities(World world, Entity base, float dis)
    {
        List<Entity> list = Lists.<Entity>newArrayList();
        
        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
            if (entity != null && base.getDistance(entity) <= dis && !entity.isDead)
            {
                list.add(entity);
            }
        }

        return list;
    }
	
	public static <T extends Entity> List<T> getEntities(Class <? extends T> entityType, World world, double x, double y, double z, double d)
    {
        List<T> list = Lists.<T>newArrayList();
        
        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
            if (entity.getDistance(x, y, z) <= d && !entity.isDead && entityType.isAssignableFrom(entity.getClass()))
            {
                list.add((T) entity);
            }
        }

        return list;
    }
	
	public static List<Entity> getEntities(World world, double x, double y, double z, double d)
    {
        List<Entity> list = Lists.<Entity>newArrayList();
        
        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
            if (entity.getDistance(x, y, z) <= d && !entity.isDead)
            {
                list.add(entity);
            }
        }

        return list;
    }
	
	public static <T extends Entity> List<T> getEntitiesPos(Class <? extends T > entityType, World world, BlockPos base, float dis)
    {
        List<T> list = Lists.<T>newArrayList();
        
        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
            if (entityType.isAssignableFrom(entity.getClass()) && base.distanceSq(entity.posX, entity.posY, entity.posZ) <= dis * dis)
            {
                list.add((T)entity);
            }
        }

        return list;
    }
	
	public <T extends Entity> List<T> getAllEntities(Class <? extends T > entityType, World world)
    {
        List<T> list = Lists.<T>newArrayList();
        
        for (Entity entity : world.loadedEntityList)
        {
            if (entityType.isAssignableFrom(entity.getClass()))
            {
                list.add((T)entity);
            }
        }

        return list;
    }
	
	public static Entity getLookedEntity(World worldIn, EntityLivingBase entityIn, float rotationYaw, float rotationPitch, float reachIn)
    {
    	for(double i = 0; i <= reachIn; i += 0.25)
    	{
    		Vec3d vec = UtilsVoids.laser(i, rotationYaw, rotationPitch);
    		worldIn.spawnAlwaysVisibleParticle(60, entityIn.posX + vec.x, entityIn.posY + entityIn.getEyeHeight() + vec.y, entityIn.posZ + vec.z, 0, 0, 0);
    		
    		if(!worldIn.isRemote)
    		{
    			Vec3d posVec = new Vec3d(entityIn.posX + vec.x, entityIn.posY + entityIn.getEyeHeight() + vec.y, entityIn.posZ + vec.z);
    			
        		
    			Entity target = null;
    			if(entityIn != null)
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
        				return target;
        			}
    			}
    		}
    	}
    	
    	return null;
    }
	
	@Nullable
    public Entity getEntityByUUID(UUID uuid, World world)
    {
        for (int i = 0; i < this.getAllEntities(Entity.class, world).size(); ++i)
        {
            Entity entity = this.getAllEntities(Entity.class, world).get(i);

            if (uuid.equals(entity.getUniqueID()))
            {
                return entity;
            }
        }

        return null;
    }
	
	public static <T extends Entity> List<T> getEntitiesTurret(World world, EntityTurretAuto turret, float dis)
    {
        List<T> list = Lists.<T>newArrayList();

        CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
        
        for (Entity entity : entities)
        {
        	if(entity.posY + entity.height > turret.posY)
        	{
        		if (UtilsVoids.distanceIgnoreY(entity.posX, entity.posZ, turret) <= dis)
                {
                	if(entity instanceof EntityMissile)
                	{
                		list.add((T)entity);
                	}
                	else if(entity instanceof EntityTargetDrone && !turret.getOwnersEntities().contains(((EntityTargetDrone) entity).getThrower()))
                	{
                		list.add((T)entity);
                	}
                	else if(entity instanceof EntityLivingBase && entity instanceof IMob)
            		{
                		list.add((T)entity);
            		}
                	else if(entity instanceof EntityPlayer && !turret.getOwnersEntities().contains(entity))
                	{
                		list.add((T)entity);
                	}
                	else if(entity instanceof EntityTurretAuto && ((EntityTurretAuto) entity).getHealth() > 0.0F && entity != turret)
            		{
                		list.add((T)entity);
            		}
                }
        	}
        }

        return list;
    }
	
	public static void grapple(Entity base, Entity attracted, double strenght)
	{
		if (base != null && base != attracted)
        {
            double d1 = (base.posX - attracted.posX);
            double d2 = (base.posY + (double)base.getEyeHeight() / 2.0D - attracted.posY);
            double d3 = (base.posZ - attracted.posZ);
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            
            attracted.motionX = d1 / d4 * strenght;
            attracted.motionY = d2 / d4 / 2 + 0.5D;
            attracted.motionZ = d3 / d4 * strenght;
            
            if(attracted.motionY >= 0)
            {
            	attracted.fallDistance = 0;
            }
        }
	}
	
	public static void grapple(Vec3d base, Entity attracted, double strenght)
	{
		if (base != null)
        {
            double d1 = (base.x - attracted.posX);
            double d2 = (base.y + 0.5D - attracted.posY);
            double d3 = (base.z - attracted.posZ);
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            
            attracted.motionX = d1 / d4 * strenght;
            attracted.motionY = d2 / d4 / 2 + 0.5D;
            attracted.motionZ = d3 / d4 * strenght;
            
            if(attracted.motionY >= 0)
            {
            	attracted.fallDistance = 0;
            }
        }
	}
	
	public static void AttractEntity(Entity base, Entity attracted, double distance, double strenght)
	{
		if (base != null && base != attracted)
        {
            double d1 = (base.posX - attracted.posX) / distance;
            double d2 = (base.posY + (double)base.getEyeHeight() / 2.0D - attracted.posY) / distance;
            double d3 = (base.posZ - attracted.posZ) / distance;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;

            if (d5 > 0.0D)
            {
                d5 = d5 * d5;
                attracted.motionX += d1 / d4 * d5 * strenght;
                attracted.motionY += d2 / d4 * d5 * strenght;
                attracted.motionZ += d3 / d4 * d5 * strenght;
            }
        }
	}
	
	public static List<EntityRemoteGrenade> getRemoteGrenadesForEntity(World world, EntityLivingBase entity, float dis)
	{
		List<EntityRemoteGrenade> list = Lists.newArrayList();
		if(!UtilsVoids.getEntities(EntityRemoteGrenade.class, world, entity, dis).isEmpty())
		{
			for(EntityRemoteGrenade gre : UtilsVoids.getEntities(EntityRemoteGrenade.class, world, entity, dis))
			{
				if(gre.getUser() == entity)
				{
					list.add(gre);
				}
			}
		}
		return list;
	}
	
	public static void AttractEntity(double posX, double posY, double posZ, Entity attracted, double distance, double strenght)
	{
		double d1 = (posX - attracted.posX) / distance;
        double d2 = (posY - attracted.posY) / distance;
        double d3 = (posZ - attracted.posZ) / distance;
        double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
        double d5 = 1.0D - d4;

        if (d5 > 0.0D)
        {
            d5 = d5 * d5;
            attracted.motionX += d1 / d4 * d5 * strenght;
            attracted.motionY += d2 / d4 * d5 * strenght;
            attracted.motionZ += d3 / d4 * d5 * strenght;
        }
	}
	
	public static void AttractEntityPos(BlockPos base, Entity attracted, double distance, double strenght)
	{
		if (base != null)
        {
            double d1 = (base.getX() + 0.5D - attracted.posX) / distance;
            double d2 = (base.getY() - attracted.posY) / distance;
            double d3 = (base.getZ() + 0.5D - attracted.posZ) / distance;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;

            if (d5 > 0.0D)
            {
                d5 = d5 * d5;
                attracted.motionX += d1 / d4 * d5 * strenght;
                attracted.motionY += d2 / d4 * d5 * strenght;
                attracted.motionZ += d3 / d4 * d5 * strenght;
            }
        }
	}
	
	public static void AttractEntityAbsorber(BlockPos base, Entity attracted, double distance, double strenght)
	{
		if (base != null)
        {
            double d1 = (base.getX() + 0.5D - attracted.posX) / distance;
            double d2 = (base.getY() - attracted.posY) / distance;
            double d3 = (base.getZ() + 0.5D - attracted.posZ) / distance;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;

            if (d5 > 0.0D)
            {
                d5 = d5 * d5;
                attracted.motionX += d1 / d4 * d5 * strenght;
                attracted.motionZ += d3 / d4 * d5 * strenght;
            }
        }
	}
	
	public static double distanceIgnoreY(double toX, double toZ, Entity entity)
    {
        double d0 = (double)entity.posX - toX;
        double d2 = (double)entity.posZ - toZ;
        return MathHelper.sqrt(d0 * d0 + d2 * d2);
    }
	
	public static double distanceIgnoreY(double toX, double toZ, Vec3d pos) 
	{
		double d0 = pos.x - toX;
        double d2 = pos.z - toZ;
        return MathHelper.sqrt(d0 * d0 + d2 * d2);
	}
	
	public static void ChangDim(EntityPlayer player, int dimID, ITeleporter teleport)
	{
		
		if ((player.getRidingEntity() == null) && ((player instanceof EntityPlayerMP)))
		{
			
			EntityPlayerMP player1 = (EntityPlayerMP)player;
			MinecraftServer mcServer = player1.getServer();
			player1.timeUntilPortal += 1;
			if(player1.dimension != dimID)
			{
				mcServer.getPlayerList().transferPlayerToDimension(player1, dimID, teleport);		
			}
			else if(player1.dimension == dimID)
			{
				mcServer.getPlayerList().transferPlayerToDimension(player1, 0, teleport);
			}
		
		}
	}

	public static List<ItemStack> getBullets(EntityPlayer player)
    {
		List<ItemStack> list = Lists.newArrayList();
		
        for (ItemStack stack : player.inventory.mainInventory)
        {
        	if(stack.getItem() instanceof ItemBaseBullet)
        	{
        		list.add(stack);
        	}
        }

        return list;
    }
	
	public static boolean attackEntityFrom(EntityLivingBase entity, DamageSource source, float amount)
    {
        if(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.disableDamage || entity.getIsInvulnerable() || entity.isPotionActive(RegisterPotions.INVUL) || entity.isPotionActive(RegisterPotions.UBER))
        {
        	return false;
        }
        if(entity instanceof EntityArmorStand)
        {
        	return false;
        }
        if(entity instanceof EntityPlayer && source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer)
        {
        	EntityPlayer target = (EntityPlayer) entity;
        	EntityPlayer cause = (EntityPlayer) source.getTrueSource();
        	if(target.isOnSameTeam(cause))
        	{
        		return false;
        	}
        }
    	
        if(source.isExplosion())
        {
        	for(ItemStack stack : entity.getArmorInventoryList())
        	{
        		if(stack.getItem() instanceof IExplosionProtection)
        		{
        			amount *= ((IExplosionProtection) stack.getItem()).explosionMultiplier(entity, stack.getItem(), stack);
        		}
        	}
        }
        
        if(source instanceof DamageSourceGun)
        {
        	for(ItemStack stack : entity.getArmorInventoryList())
        	{
        		if(stack.getItem() instanceof IBulletProtection)
        		{
        			amount *= ((IBulletProtection) stack.getItem()).getBulletMultiplier(stack, entity);
        		}
        	}
        	
        	if(entity instanceof EntityPlayer)
        	{
            	for(ItemStack stack : ((EntityPlayer) entity).inventory.mainInventory)
            	{
            		if(stack.getItem() instanceof IBulletProtection && ((IBulletProtection) stack.getItem()).isMultiplierPassive() && stack != entity.getHeldItemMainhand())
            		{
            			amount *= ((IBulletProtection) stack.getItem()).getBulletMultiplier(stack, entity);
            		}
            	}
        	}
        	
        	if(entity.getHeldItemMainhand().getItem() instanceof IBulletProtection && !(entity.getHeldItemMainhand().getItem() instanceof ItemArmor))
        	{
        		amount *= ((IBulletProtection) entity.getHeldItemMainhand().getItem()).getBulletMultiplier(entity.getHeldItemMainhand(), entity);
        	}
        }
        if(source instanceof DamageSourceGun && ((DamageSourceGun) source).isFlameThrower() && (entity.getHeldItemMainhand().getItem() == RegisterItems.PYRITE_SHIELD || entity.getHeldItemOffhand().getItem() == RegisterItems.PYRITE_SHIELD))
        {
        	amount *= 0.5F;
        }
        if (entity.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (entity.world.isRemote)
        {
            return false;
        }
        else
        {
            if (entity.getHealth() <= 0.0F)
            {
                return false;
            }
            else if (source.isFireDamage() && entity.isPotionActive(MobEffects.FIRE_RESISTANCE) && !entity.isPotionActive(RegisterPotions.FIRE_VULNERABILITY))
            {
                return false;
            }
            else
            {
                float f = amount;

                if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) && !entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
                {
                	entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).damageItem((int)(amount * 4.0F + new Random().nextFloat() * amount * 2.0F), entity);
                    amount *= 0.75F;
                }

                boolean flag = false;

                entity.limbSwingAmount = 1.5F;
                boolean flag1 = true;

                UtilsVoids.damageEntity(entity, source, amount);

                entity.attackedAtYaw = 0.0F;
                Entity entity1 = source.getTrueSource();

                if (entity1 != null)
                {
                    if (entity1 instanceof EntityLivingBase)
                    {
                    	entity.setRevengeTarget((EntityLivingBase)entity1);
                    }

                    else if (entity instanceof net.minecraft.entity.passive.EntityTameable)
                    {
                        net.minecraft.entity.passive.EntityTameable entitywolf = (net.minecraft.entity.passive.EntityTameable)entity;

                        if (entitywolf.isTamed() && entitywolf.getOwner() == entity1)
                        {
                        	return false;
                        }
                    }
                }

                if (flag1)
                {
                    if (flag)
                    {
                    	entity.world.setEntityState(entity, (byte)29);
                    }
                    else if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage())
                    {
                    	entity.world.setEntityState(entity, (byte)33);
                    }
                    else
                    {
                        byte b0;

                        if (source == DamageSource.DROWN)
                        {
                            b0 = 36;
                        }
                        else if (source.isFireDamage())
                        {
                            b0 = 37;
                        }
                        else
                        {
                            b0 = 2;
                        }

                        entity.world.setEntityState(entity, b0);
                    }

                    /*if (source != DamageSource.DROWN && (!flag || amount > 0.0F))
                    {
                    	UtilsVoids.setBeenAttacked(entity);
                    }*/

                    if (entity1 != null)
                    {
                        double d1 = entity1.posX - entity.posX;
                        double d0;

                        for (d0 = entity1.posZ - entity.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
                        {
                            d1 = (Math.random() - Math.random()) * 0.01D;
                        }

                        entity.attackedAtYaw = (float)(MathHelper.atan2(d0, d1) * (180D / Math.PI) - (double)entity.rotationYaw);
                        //entity.knockBack(entity1, 0.4F, d1, d0);
                    }
                    else
                    {
                    	entity.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
                    }
                }

                boolean flag2 = !flag || amount > 0.0F;

                
                if (flag2)
                {
                	
                }
                
                if (entity instanceof EntityPlayerMP)
                {
                    CriteriaTriggers.ENTITY_HURT_PLAYER.trigger((EntityPlayerMP)entity, source, f, amount, flag);
                }

                if (entity1 instanceof EntityPlayerMP)
                {
                    CriteriaTriggers.PLAYER_HURT_ENTITY.trigger((EntityPlayerMP)entity1, entity, source, f, amount, flag);
                }

                return flag2;
            }
        }
    }
	
	protected static void damageEntity(EntityLivingBase entity, DamageSource damageSrc, float damageAmount)
    {
        if (!entity.isEntityInvulnerable(damageSrc))
        {
        	if(damageSrc instanceof DamageSourceGun && damageSrc.isProjectile())
            {
            	for(ItemStack stack : entity.getArmorInventoryList())
            	{
            		if(stack.getItem() instanceof IBulletProtection)
            		{
            			stack.damageItem(((IBulletProtection) stack.getItem()).damageItemOnShoot(), entity);
            		}
            	}
            }
        	
        	if(entity instanceof IOnShot && damageSrc instanceof DamageSourceGun && !((DamageSourceGun) damageSrc).isFlameThrower())
        	{
        		if(damageSrc.getTrueSource() instanceof EntityLivingBase)
        		{
            		((IOnShot) entity).onShot((EntityLivingBase) damageSrc.getTrueSource(), entity.getHeldItemMainhand(), damageAmount);
        		}
        		else
        		{
            		((IOnShot) entity).onShot(null, entity.getHeldItemMainhand(), damageAmount);
        		}
        	}
        	else
        	{
        		damageAmount = net.minecraftforge.common.ForgeHooks.onLivingHurt(entity, damageSrc, damageAmount);
                if (damageAmount <= 0) return;
                float f = damageAmount;
                damageAmount = Math.max(damageAmount - entity.getAbsorptionAmount(), 0.0F);
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (f - damageAmount));

                if (damageAmount != 0.0F)
                {
                    float f1 = entity.getHealth();
                    if(entity instanceof EntityPlayerMP && damageSrc.getTrueSource() != null)
                    {
                    	Mathsmod.network.sendTo(new MessageShowDamages(damageSrc.getTrueSource().getPositionVector()), (EntityPlayerMP) entity);
                    }
                    entity.setHealth(f1 - damageAmount);
                    entity.getCombatTracker().trackDamage(damageSrc, f1, damageAmount);
                    entity.setAbsorptionAmount(entity.getAbsorptionAmount() - damageAmount);
                    
                    /*if(damageSrc.getTrueSource() != null && damageSrc.getTrueSource() instanceof EntityPlayer)
                    {
                    	EntityPlayer player = (EntityPlayer) damageSrc.getTrueSource();
                    	player.sendMessage(new TextComponentString(damageAmount / 2 + "<3"));
                    }*/
                    /*System.out.print("attacked:" + damageAmount);
                    System.out.print(System.lineSeparator());*/
                    if(f1 - damageAmount <= 0)
                    {
                    	
                    	entity.onDeath(damageSrc);
                    	
                		if(entity instanceof EntityPlayer)
                		{

                        	
                        	if(entity instanceof EntityPlayer && damageSrc != null && !((EntityPlayer) entity).getCooldownTracker().hasCooldown(RegisterItems.FROZEN_GEM) && damageSrc != DamageSource.OUT_OF_WORLD && !UtilsVoids.getItemMain((EntityPlayer) entity, RegisterItems.FROZEN_GEM).isEmpty())
                    		{
                    			entity.setHealth(1F);
                    			entity.clearActivePotions();
                    			entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 2, true, false));
                    			entity.addPotionEffect(new PotionEffect(RegisterPotions.INVUL, 60, 1, true, false));
                    			entity.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 60, 1, true, false));
                    			entity.addPotionEffect(new PotionEffect(RegisterPotions.DISAPPEAR, 60, 1, true, false));
                    			((EntityPlayer) entity).getCooldownTracker().setCooldown(RegisterItems.FROZEN_GEM, 12000);
                    			KillsCounter.setKills((EntityPlayer) entity, 0);
                    			ItemStackUtils.dropPlayerInventory((EntityPlayer) entity);
                    		}
                        	else
                        	{
                        		if(entity instanceof EntityPlayerMP)
                        		{
                        			boolean flag = entity.world.getGameRules().getBoolean("showDeathMessages");
                        			((EntityPlayerMP)entity).mcServer.getPlayerList().sendMessage(entity.getCombatTracker().getDeathMessage());
                        			((EntityPlayerMP)entity).connection.sendPacket(new SPacketCombatEvent(entity.getCombatTracker(), SPacketCombatEvent.Event.ENTITY_DIED, flag));
                        		}
                        		
                        		if (!entity.world.getGameRules().getBoolean("keepInventory") && !((EntityPlayer) entity).isSpectator())
                    	        {
                    				for (int i = 0; i < ((EntityPlayer) entity).inventory.getSizeInventory(); ++i)
                    		        {
                    		            ItemStack itemstack = ((EntityPlayer) entity).inventory.getStackInSlot(i);

                    		            if (!itemstack.isEmpty() && EnchantmentHelper.hasVanishingCurse(itemstack))
                    		            {
                    		            	((EntityPlayer) entity).inventory.removeStackFromSlot(i);
                    		            }
                    		        }
                    				((EntityPlayer) entity).inventory.dropAllItems();
                    	        }
                        	}
                			
                		}
                		
                		if(damageSrc.getTrueSource() != null && damageSrc.getTrueSource() instanceof EntityPlayer && entity instanceof EntityPlayer)
                        {
                        	EntityPlayer player = (EntityPlayer) damageSrc.getTrueSource();
                        	KillsCounter.setKills(player, KillsCounter.getKills(player) + 1);
                        	if(KillsCounter.getKills(player) > 3)
            				{
            		        	player.sendMessage(new TextComponentString(player.getName() + " : " + KillsCounter.getKills(player) + " kills"));
            				}
                        }
                    }
                }
        	}
        }
    }
	
	protected static void setBeenAttacked(EntityLivingBase entity)
    {
		entity.velocityChanged = new Random().nextDouble() >= entity.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
    }
	
	public static ItemBaseBullet getFirstBullet(ItemGun gun)
    {
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemBaseBullet && gun.goodBulletType((ItemBaseBullet) item))
    		{
    			return (ItemBaseBullet) item;
    		}
    	}
		return null;
    }
	
	public static void EasyShootEntitySoft(Entity entity, float rotationPitch, float rotationYaw, float velocity, float inaccuracy)
	{
		float f = -MathHelper.sin(rotationYaw * 0.017453292F) * MathHelper.cos(rotationPitch * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitch) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYaw * 0.017453292F) * MathHelper.cos(rotationPitch * 0.017453292F);
        UtilsVoids.shootEntitySoft(entity, (double)f, (double)f1, (double)f2, velocity, inaccuracy, false);
	}
	
	public static void EasyShootEntity(Entity entity, float rotationPitch, float rotationYaw, float velocity, float inaccuracy)
	{
		float f = -MathHelper.sin(rotationYaw * 0.017453292F) * MathHelper.cos(rotationPitch * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitch) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYaw * 0.017453292F) * MathHelper.cos(rotationPitch * 0.017453292F);
        UtilsVoids.shootEntity(entity, (double)f, (double)f1, (double)f2, velocity, inaccuracy, false);
	}
	
	public static void EasyShootEntityModifyY(Entity entity, float rotationPitch, float rotationYaw, float velocity, float inaccuracy)
	{
		float f = -MathHelper.sin(rotationYaw * 0.017453292F) * MathHelper.cos(rotationPitch * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitch) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYaw * 0.017453292F) * MathHelper.cos(rotationPitch * 0.017453292F);
        UtilsVoids.shootEntity(entity, (double)f, (double)f1, (double)f2, velocity, inaccuracy, true);
	}
	
	public static void shootEntitySoft(Entity entity, double x, double y, double z, float velocity, float inaccuracy, boolean modifyMotionY)
	{
		float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + new Random().nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + new Random().nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + new Random().nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        entity.motionX += x;
        if(modifyMotionY)
        {
        	entity.motionY += y;
        }
        entity.motionZ += z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        entity.prevRotationYaw = entity.rotationYaw;
        entity.prevRotationPitch = entity.rotationPitch;
	}
	
	public static void shootEntity(Entity entity, double x, double y, double z, float velocity, float inaccuracy, boolean modifyMotionY)
	{
		float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + new Random().nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + new Random().nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + new Random().nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        entity.motionX = x;
        if(modifyMotionY)
        {
        	entity.motionY = y;
        }
        entity.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        entity.prevRotationYaw = entity.rotationYaw;
        entity.prevRotationPitch = entity.rotationPitch;
	}
	
	public static boolean playerHasItemMain(EntityPlayer player, Item item)
	{
		for(ItemStack stack : player.inventory.mainInventory)
		{
			if(stack.getItem() == item)
			{
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack getItemMain(EntityPlayer player, Item item)
	{
		for(ItemStack stack : player.inventory.mainInventory)
		{
			if(stack.getItem() == item)
			{
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}
	
	public static boolean hasSmasher(EntityPlayer player)
	{
		for(ItemStack stack : player.inventory.mainInventory)
		{
			if(stack.getItem() instanceof ItemSmasher)
			{
				return true;
			}
		}
		return false;
	}
	
	public static List<ItemStack> getAmmoBoxes(EntityPlayer player, ItemGun gun)
	{
		List<ItemStack> list = Lists.newArrayList();
		
		for(ItemStack stack : player.inventory.mainInventory)
		{
			if(stack.getItem() instanceof ItemAmmoBox)
			{
				ItemAmmoBox item = (ItemAmmoBox) stack.getItem();
				
				if(item.getItemCount(stack) > 0 && item.getAmmo(stack).getItem() instanceof ItemBaseBullet && gun.goodBulletType(((ItemBaseBullet) item.getAmmo(stack).getItem())))
				{
					list.add(stack);
				}
			}
		}
		return list;
	}
	
	public static Vec3d laser(Vec3d vec, float rotationYawIn, float rotationPitchIn)
    {
		double x = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
    	double y = -MathHelper.sin((rotationPitchIn) * 0.017453292F);
    	double z = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x * (double)vec.x;
        y = y * (double)vec.x;
        z = z * (double)vec.x;
        
        return new Vec3d(x, y, z);
    }
	
	public static Vec3d laser(double i, float rotationYawIn, float rotationPitchIn)
    {
		double x = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
    	double y = -MathHelper.sin((rotationPitchIn) * 0.017453292F);
    	double z = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x * i;
        y = y * i;
        z = z * i;
        
        return new Vec3d(x, y, z);
    }
	
	public static Vec3d laserWithInnacuracy(double i, float rotationYawIn, float rotationPitchIn, float inaccuracyX, float inaccuracyY, float inaccuracyZ)
    {
		Random rand = new Random();
		double x = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
    	double y = -MathHelper.sin((rotationPitchIn) * 0.017453292F);
    	double z = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + inaccuracyX;
        y = y + inaccuracyY;
        z = z + inaccuracyZ;
        x = x * i;
        y = y * i;
        z = z * i;
        
        return new Vec3d(x, y, z);
    }
	
	public static List<Vec3d> doublelaserWithInnacuracy(double dis1, double dis2, float rotationYawIn, float rotationPitchIn, float inaccuracyX, float inaccuracyY, float inaccuracyZ)
    {
		List<Vec3d> list = Lists.newArrayList();
		Random rand = new Random();
		double x = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
    	double y = -MathHelper.sin((rotationPitchIn) * 0.017453292F);
    	double z = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + inaccuracyX;
        y = y + inaccuracyY;
        z = z + inaccuracyZ;
        double f1 = x * dis1;
        double f2 = y * dis1;
        double f3 = z * dis1;
        list.add(new Vec3d(f1, f2, f3));
        double f4 = x * dis2;
        double f5 = y * dis2;
        double f6 = z * dis2;
        list.add(new Vec3d(f4, f5, f6));
        
        return list;
    }
	
	public static <T extends Entity> List<T> getHitEntities(Class <? extends T > entityType, World world, EntityThrowable base, double x, double y, double z)
	{
		List<T> list = Lists.<T>newArrayList();
		
		CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
		
		for(Entity entity : entities)
		{
			if(entity != null && entityType.isAssignableFrom(entity.getClass()) && (entity != base && entity != base.getThrower() || base == null))
			{
				for(float i = 0; i <= entity.height; i += 0.2F)
				{
					if(new Vec3d(x, y, z).distanceTo(new Vec3d(entity.posX, entity.posY + i, entity.posZ)) <= entity.width)
					{
						list.add((T) entity);
					}
				}
			}
		}
		
		return list;
	}
	
	public static <T extends Entity> List<T> getHitEntities(Class <? extends T > entityType, World world, EntityLivingBase base, double x, double y, double z)
	{
		List<T> list = Lists.<T>newArrayList();
		
		CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
		
		for(Entity entity : entities)
		{
			if(entity != null && entityType.isAssignableFrom(entity.getClass()) && entity != base)
			{
				for(float i = 0; i <= entity.height; i += 0.2F)
				{
					if(new Vec3d(x, y, z).distanceTo(new Vec3d(entity.posX, entity.posY + i, entity.posZ)) <= entity.width)
					{
						list.add((T) entity);
					}
				}
			}
		}
		
		return list;
	}
	
	public static void setLookEntity(Entity target, Entity look)
	{
		double LookposX = target.posX + target.motionX;
		double LookposY = 0;
        LookposY = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0D;
		double LookposZ = target.posZ + target.motionZ;
		
		double d0 = LookposX - look.posX;
        double d1 = LookposY - (look.posY + look.getEyeHeight());
        double d2 = LookposZ - look.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        look.rotationPitch = UtilsVoids.updateRotation(look.rotationPitch, f1);
        look.rotationYaw = UtilsVoids.updateRotation(look.rotationYaw, f);
	}
	
	//vec3d(rotationPitch, rotationYaw)
	public static Vec3d getRotationObjectives(Entity target, Entity look)
	{
		double LookposX = target.posX + target.motionX;
		double LookposY = 0;
        LookposY = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0D;
		double LookposZ = target.posZ + target.motionZ;
		
		double d0 = LookposX - look.posX;
        double d1 = LookposY - (look.posY + look.getEyeHeight());
        double d2 = LookposZ - look.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        return new Vec3d(UtilsVoids.updateRotation(look.rotationPitch, f1), UtilsVoids.updateRotation(look.rotationYaw, f), 0);
	}
	
	public static float updateRotation(float p_75652_1_, float p_75652_2_)
    {
        float f = MathHelper.wrapDegrees(p_75652_2_ - p_75652_1_);
        return p_75652_1_ + f;
    }
	
	public static Vec3d fromPitchYaw(Vec3d vec3d, float pitch, float yaw)
    {
		vec3d = vec3d.rotatePitch(-pitch * 0.017453292F);
        vec3d = vec3d.rotateYaw(-yaw * 0.017453292F);
        return vec3d;
    }
	
	public static Vec3d rotateAngleZ(Vec3d vec, float rotateZ)
	{
		float f = MathHelper.cos(rotateZ);
        float f1 = MathHelper.sin(rotateZ);
        double d0 = vec.y * (double)f - vec.x * (double)f1;
        double d1 = vec.x * (double)f + vec.y * (double)f1;
        double d2 = vec.z;
        return new Vec3d(d0, d1, d2);
	}
	
	public static Vec3d rotate3d(Vec3d vec3d, float x, float y, float z)
    {
		vec3d = vec3d.rotatePitch(-x);
        vec3d = vec3d.rotateYaw(-y);
        vec3d = UtilsVoids.rotateAngleZ(vec3d, -z);
        return vec3d;
    }
	
	public static NBTTagCompound saveAllItemsInf(NBTTagCompound tag, NonNullList<ItemStack> list, boolean saveEmpty)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < list.size(); ++i)
        {
            ItemStack itemstack = list.get(i);

            if (!itemstack.isEmpty())
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setInteger("Slot", i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        if (!nbttaglist.hasNoTags() || saveEmpty)
        {
            tag.setTag("Items", nbttaglist);
        }

        return tag;
    }
	
	public static void loadAllItemsInf(NBTTagCompound tag, NonNullList<ItemStack> list)
    {
        NBTTagList nbttaglist = tag.getTagList("Items", 11);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getInteger("Slot");

            if (j >= 0 && j < list.size())
            {
                list.set(j, new ItemStack(nbttagcompound));
            }
        }
    }
	
	public static void setYawEntity(Entity target, Entity look)
	{
		double LookposX = target.posX;
		double LookposZ = target.posZ;
		
		double d0 = LookposX - look.posX;
        double d2 = LookposZ - look.posZ;
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        look.rotationYaw = UtilsVoids.updateRotation(look.rotationYaw, f);
	}
	
	public static float getYawFromEntity(Vec3d vec, Entity look)
	{
		double LookposX = vec.x;
		double LookposZ = vec.z;
		
		double d0 = LookposX - look.posX;
        double d2 = LookposZ - look.posZ;
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float a = 0;
        a = UtilsVoids.updateRotation(a, f);
        return a;
	}
	
	public static float getYawFromPos(Vec3d vec, double posX, double posZ)
	{
		double LookposX = vec.x;
		double LookposZ = vec.z;
		
		double d0 = LookposX - posX;
        double d2 = LookposZ - posZ;
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float a = 0;
        a = UtilsVoids.updateRotation(a, f);
        return a;
	}
	
	public static float getPitchFromPos(Vec3d vec, double posX, double posY, double posZ)
	{
		double LookposX = vec.x;
		double LookposY = vec.y;
		double LookposZ = vec.z;
		
		double d0 = LookposX - posX;
        double d1 = LookposY - posY;
        double d2 = LookposZ - posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        float a = 0;
        a = UtilsVoids.updateRotation(a, f1);
        return a;
	}
	
	public static void setLookPos(double x, double y, double z, Entity look)
	{
		double LookposX = x;
		double LookposY = y;
		double LookposZ = z;
		
		double d0 = LookposX - look.posX;
        double d1 = LookposY - (look.posY + look.getEyeHeight());
        double d2 = LookposZ - look.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        look.rotationPitch = UtilsVoids.updateRotation(look.rotationPitch, f1);
        look.rotationYaw = UtilsVoids.updateRotation(look.rotationYaw, f);
	}
	
	public static EnumFacing getDirectionFromEntity(BlockPos pos, Entity entity)
    {
		if(entity != null)
		{
			if (Math.abs(entity.posX - (double)((float)pos.getX() + 0.5F)) < 2.0D && Math.abs(entity.posZ - (double)((float)pos.getZ() + 0.5F)) < 2.0D)
	        {
	            double d0 = entity.posY + (double)entity.getEyeHeight();

	            if (d0 - (double)pos.getY() > 2.0D)
	            {
	                return EnumFacing.UP;
	            }

	            if ((double)pos.getY() - d0 > 0.0D)
	            {
	                return EnumFacing.DOWN;
	            }
	        }

	        return entity.getHorizontalFacing().getOpposite();
		}
		else
		{
			return EnumFacing.UP;
		}
        
    }
	
	public static EntityLivingBase getNearestEntity(Entity base, float ray)
	{
		EntityLivingBase entityIn = null;
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, base.world, base, ray))
		{
			if(entity != base.getRidingEntity() && entity != base.getControllingPassenger())
			{
				if(!(base.getControllingPassenger() instanceof EntityLivingBase) || !((EntityLivingBase)base.getControllingPassenger()).isOnSameTeam(entity))
				{
					if(entityIn == null || entity.getDistance(base) < entityIn.getDistance(base) || entity instanceof EntityPlayer)
					{
						if(entityIn instanceof EntityPlayer && entity instanceof EntityPlayer)
						{
							entityIn = entity;
						}
						else if(!(entityIn instanceof EntityPlayer))
						{
							entityIn = entity;
						}
					}
				}
			}
		}
		return entityIn;
	}
	
	public static RayTraceResult getResult(Entity base, float dis)
	{
		Vec3d vec3d = base.getPositionEyes(1.0F);
		Vec3d vec3d1 = base.getLook(1.0F);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * dis, vec3d1.y * dis, vec3d1.z * dis);
        float f = 1.0F;
        List<Entity> list = base.world.getEntitiesInAABBexcluding(base, base.getEntityBoundingBox().expand(vec3d1.x * dis, vec3d1.y * dis, vec3d1.z * dis).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
        {
            public boolean apply(@Nullable Entity p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
            }
        }));

        for (int j = 0; j < list.size(); ++j)
        {
            Entity entity1 = list.get(j);
            AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

            if (axisalignedbb.contains(vec3d))
            {
                    return new RayTraceResult(entity1);
            }
            else if (raytraceresult != null)
            {
                double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                if (entity1.getLowestRidingEntity() == base.getLowestRidingEntity() && !entity1.canRiderInteract())
                {
                	return new RayTraceResult(entity1);
                }
                else
                {
                	return new RayTraceResult(entity1);
                }
            }
        }
        
        Vec3d vec = base.getPositionEyes(0.0F);
        Vec3d vec1 = base.getLook(0.0F);
        Vec3d vec2 = vec3d.addVector(vec3d1.x * dis, vec1.y * dis, vec1.z * dis);
        return base.world.rayTraceBlocks(vec, vec2, false, false, true);
	}
    
    public static CopyOnWriteArrayList<Entity> getHitEntities(World world, Entity entityIn, Vec3d vec, float reach)
    {
    	CopyOnWriteArrayList<Entity> list = new CopyOnWriteArrayList<Entity>();
    	CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>(world.loadedEntityList);
    	Predicate <? super Entity > filter = EntitySelectors.NOT_SPECTATING;
    	
        if(!entities.isEmpty())
        {
            for (Entity entity : entities)
            {
            	if(entity != null)
            	{
            		if((entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getHealth() > 0) || !(entity instanceof EntityLivingBase))
            		{
            			if (UtilsVoids.intersects(entity.getEntityBoundingBox(), vec) && entity != entityIn)
                        {
                            if (filter == null || filter.apply(entity))
                            {
                            	list.add(entity);
                            }

                            Entity[] aentity = entity.getParts();

                            if (aentity != null)
                            {
                                for (Entity entity1 : aentity)
                                {
                                    if (entity1 != entityIn && UtilsVoids.intersects(entity1.getEntityBoundingBox(), vec) && (filter == null || filter.apply(entity1)))
                                    {
                                    	list.add(entity1);
                                    }
                                }
                            }
                        }
            		}
            	}
            }
        }
        
        return list;
    }
    
    
    
    public static boolean intersects(AxisAlignedBB hit, Vec3d vec)
    {
        return hit.minX < vec.x + 0.1 && hit.maxX > vec.x - 0.1 && hit.minY < vec.y + 0.1 && hit.maxY > vec.y - 0.1 && hit.minZ < vec.z + 0.1 && hit.maxZ > vec.z - 0.1;
    }
    
	public static BlockPos collideBlock(World world, Vec3d vec)
    {
		int x = MathHelper.floor(vec.x);
		int y = MathHelper.floor(vec.y);
		int z = MathHelper.floor(vec.z);
		BlockPos pos = new BlockPos(x, y, z);
		AxisAlignedBB hit = world.getBlockState(pos).getBlock().getCollisionBoundingBox(world.getBlockState(pos), world, pos);
		if(hit != null && hit != Block.NULL_AABB)
		{
			AxisAlignedBB e = hit.offset(x, y, z);
			if(e.minX < vec.x && e.maxX > vec.x && e.minY < vec.y && e.maxY > vec.y && e.minZ < vec.z && e.maxZ > vec.z)
			{
				return pos;
			}
		}
        return null;
    }
	
	public static void attackTargetEntityWithCurrentReachItem(Entity targetEntity, EntityPlayer player, IReachWeapon item)
    {
		if(item instanceof IHeavyHitterItem || item == null)
		{
			//Maybe there will be a special event here later if i dont have a giga flemme
		}
		else
		{
			if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(player, targetEntity)) return;
		}
		
        if (targetEntity.canBeAttackedWithItem())
        {
            if (!targetEntity.hitByEntity(player))
            {
                float f = (float)player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
                float f1;

                if (targetEntity instanceof EntityLivingBase)
                {
                    f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((EntityLivingBase)targetEntity).getCreatureAttribute());
                }
                else
                {
                    f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
                }

                float f2 = player.getCooledAttackStrength(0.5F);
                f = f * (0.2F + f2 * f2 * 0.8F);
                f1 = f1 * f2;
                player.resetCooldown();

                if (f > 0.0F || f1 > 0.0F)
                {
                    boolean flag = f2 > 0.9F;
                    boolean flag1 = false;
                    int i = 0;
                    i = i + EnchantmentHelper.getKnockbackModifier(player);

                    if (player.isSprinting() && flag)
                    {
                    	player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                        ++i;
                        flag1 = true;
                    }

                    boolean flag2 = flag && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && targetEntity instanceof EntityLivingBase;
                    flag2 = flag2 && !player.isSprinting();

                    net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks.getCriticalHit(player, targetEntity, flag2, flag2 ? 1.5F : 1.0F);
                    flag2 = hitResult != null;
                    if (flag2)
                    {
                        f *= hitResult.getDamageModifier();
                    }

                    f = f + f1;
                    boolean flag3 = false;
                    double d0 = (double)(player.distanceWalkedModified - player.prevDistanceWalkedModified);

                    if (flag && !flag2 && !flag1 && player.onGround && d0 < (double)player.getAIMoveSpeed())
                    {
                        ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);

                        if (itemstack.getItem() instanceof ItemSword)
                        {
                            flag3 = true;
                        }
                    }

                    float f4 = 0.0F;
                    boolean flag4 = false;
                    int j = EnchantmentHelper.getFireAspectModifier(player);

                    if (targetEntity instanceof EntityLivingBase)
                    {
                        f4 = ((EntityLivingBase)targetEntity).getHealth();

                        if (j > 0 && !targetEntity.isBurning())
                        {
                            flag4 = true;
                            targetEntity.setFire(1);
                        }
                    }

                    double d1 = targetEntity.motionX;
                    double d2 = targetEntity.motionY;
                    double d3 = targetEntity.motionZ;
                    boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);

                    if (flag5)
                    {
                        if (i > 0)
                        {
                            if (targetEntity instanceof EntityLivingBase)
                            {
                                ((EntityLivingBase)targetEntity).knockBack(player, (float)i * 0.5F, (double)MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                            }
                            else
                            {
                                targetEntity.addVelocity((double)(-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)i * 0.5F));
                            }

                            player.motionX *= 0.6D;
                            player.motionZ *= 0.6D;
                            player.setSprinting(false);
                        }

                        if (flag3)
                        {
                            float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

                            for (EntityLivingBase entitylivingbase : player.world.getEntitiesWithinAABB(EntityLivingBase.class, targetEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D)))
                            {
                                if (entitylivingbase != player && entitylivingbase != targetEntity && !player.isOnSameTeam(entitylivingbase) && (item == null || item instanceof IHeavyHitterItem || player.getDistance(entitylivingbase) > 3.0D) && player.getDistance(entitylivingbase) < (item != null ? 3.0D + item.bonusReach(player, player.getHeldItemMainhand()) : 3.0D))
                                {
                                    entitylivingbase.knockBack(player, 0.4F, (double)MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                                    entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player), f3);
                                }
                            }

                            player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                            player.spawnSweepParticles();
                        }

                        if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged)
                        {
                            ((EntityPlayerMP)targetEntity).connection.sendPacket(new SPacketEntityVelocity(targetEntity));
                            targetEntity.velocityChanged = false;
                            targetEntity.motionX = d1;
                            targetEntity.motionY = d2;
                            targetEntity.motionZ = d3;
                        }

                        if (flag2)
                        {
                        	player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
                        	player.onCriticalHit(targetEntity);
                        }

                        if (!flag2 && !flag3)
                        {
                            if (flag)
                            {
                            	player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                            else
                            {
                            	player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                        }

                        if (f1 > 0.0F)
                        {
                        	player.onEnchantmentCritical(targetEntity);
                        }

                        player.setLastAttackedEntity(targetEntity);

                        if (targetEntity instanceof EntityLivingBase)
                        {
                            EnchantmentHelper.applyThornEnchantments((EntityLivingBase)targetEntity, player);
                        }

                        EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
                        ItemStack itemstack1 = player.getHeldItemMainhand();
                        Entity entity = targetEntity;

                        if (targetEntity instanceof MultiPartEntityPart)
                        {
                            IEntityMultiPart ientitymultipart = ((MultiPartEntityPart)targetEntity).parent;

                            if (ientitymultipart instanceof EntityLivingBase)
                            {
                                entity = (EntityLivingBase)ientitymultipart;
                            }
                        }

                        if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase)
                        {
                            ItemStack beforeHitCopy = itemstack1.copy();
                            itemstack1.hitEntity((EntityLivingBase)entity, player);

                            if (itemstack1.isEmpty())
                            {
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, beforeHitCopy, EnumHand.MAIN_HAND);
                                player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }

                        if (targetEntity instanceof EntityLivingBase)
                        {
                            float f5 = f4 - ((EntityLivingBase)targetEntity).getHealth();
                            player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

                            if (j > 0)
                            {
                                targetEntity.setFire(j * 4);
                            }

                            if (player.world instanceof WorldServer && f5 > 2.0F)
                            {
                                int k = (int)((double)f5 * 0.5D);
                                ((WorldServer)player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.posX, targetEntity.posY + (double)(targetEntity.height * 0.5F), targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }

                        player.addExhaustion(0.1F);
                    }
                    else
                    {
                    	player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);

                        if (flag4)
                        {
                            targetEntity.extinguish();
                        }
                    }
                }
            }
        }
    }
	
	public static BlockPos getCollidedBlockpos(World world, AxisAlignedBB aabb)
    {
        int i = MathHelper.floor(aabb.minX) - 1;
        int j = MathHelper.ceil(aabb.maxX) + 1;
        int k = MathHelper.floor(aabb.minY) - 1;
        int l = MathHelper.ceil(aabb.maxY) + 1;
        int i1 = MathHelper.floor(aabb.minZ) - 1;
        int j1 = MathHelper.ceil(aabb.maxZ) + 1;
        WorldBorder worldborder = world.getWorldBorder();
        IBlockState iblockstate = Blocks.STONE.getDefaultState();
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        try
        {
            for (int k1 = i; k1 < j; ++k1)
            {
                for (int l1 = i1; l1 < j1; ++l1)
                {
                    boolean flag2 = k1 == i || k1 == j - 1;
                    boolean flag3 = l1 == i1 || l1 == j1 - 1;

                    if ((!flag2 || !flag3) && world.isBlockLoaded(blockpos$pooledmutableblockpos.setPos(k1, 64, l1)))
                    {
                        for (int i2 = k; i2 < l; ++i2)
                        {
                            if (!flag2 && !flag3 || i2 != l - 1)
                            {

                                blockpos$pooledmutableblockpos.setPos(k1, i2, l1);
                                IBlockState iblockstate1;

                                iblockstate1 = world.getBlockState(blockpos$pooledmutableblockpos);

                                return blockpos$pooledmutableblockpos;
                            }
                        }
                    }
                }
            }
        }
        finally
        {
            blockpos$pooledmutableblockpos.release();
        }

        return null;
    }

	public static boolean isBackstabing(Entity shooter, Entity target)
	{
		return shooter.getLook(1.0F).dotProduct(target.getLook(1.0F)) > 0.25;
	}
	
	public static boolean checkBlockCollisionAdv(AxisAlignedBB bb, World world)
    {
        int j2 = MathHelper.floor(bb.minX);
        int k2 = MathHelper.ceil(bb.maxX);
        int l2 = MathHelper.floor(bb.minY);
        int i3 = MathHelper.ceil(bb.maxY);
        int j3 = MathHelper.floor(bb.minZ);
        int k3 = MathHelper.ceil(bb.maxZ);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();

        for (int l3 = j2; l3 < k2; ++l3)
        {
            for (int i4 = l2; i4 < i3; ++i4)
            {
                for (int j4 = j3; j4 < k3; ++j4)
                {
                    IBlockState iblockstate1 = world.getBlockState(blockpos$pooledmutableblockpos.setPos(l3, i4, j4));
                    BlockPos pos = new BlockPos(l3, i4, j4);

                    if (iblockstate1 != null && iblockstate1.getCollisionBoundingBox(world, pos) != null && iblockstate1.getCollisionBoundingBox(world, pos).offset(pos).intersects(bb))
                    {
                        blockpos$pooledmutableblockpos.release();
                        return true;
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();
        return false;
    }
	
	@Nullable
    public static Entity getEntityByUUID(World world, UUID uuid)
    {
        for (int j2 = 0; j2 < world.playerEntities.size(); ++j2)
        {
            EntityPlayer entityplayer = world.playerEntities.get(j2);

            if (uuid.equals(entityplayer.getUniqueID()))
            {
                return entityplayer;
            }
        }
        
        for (int j2 = 0; j2 < world.loadedEntityList.size(); ++j2)
        {
            Entity entity = world.loadedEntityList.get(j2);

            if (uuid.equals(entity.getUniqueID()))
            {
                return entity;
            }
        }

        return null;
    }
	
	public static Vec3d rotateVec(Vec3d vec, double rx, double ry, double rz)
	{
		vec = vec.rotatePitch(-(float) rx);
		vec = vec.rotateYaw((float) ry);
		vec = rotateZ(vec, (float) rz);
		return vec;
	}
	
	private static Vec3d rotateZ(Vec3d vec, float z)
    {
        float f = MathHelper.cos(z);
        float f1 = MathHelper.sin(z);
        double d0 = vec.x * (double)f - vec.y * (double)f1;
        double d1 = vec.y * (double)f + vec.x * (double)f1;
        double d2 = vec.z;
        return new Vec3d(d0, d1, d2);
    }
	
	public static double dotProduct(Vec3d vec1, Vec3d vec2)
	{
		Vec3d vec = vec1.normalize();
		Vec3d wec = vec2.normalize();
		return vec.x * wec.x + vec.z * wec.z + vec.y * wec.y;
	}
	
	public static double dotProductProjXZ(Vec3d vec1, Vec3d vec2)
	{
		Vec3d vec = vec1.addVector(0, -vec1.y, 0).normalize();
		Vec3d wec = vec2.addVector(0, -vec2.y, 0).normalize();
		return vec.x * wec.x + vec.z * wec.z;
	}

	public static float getFloatFromString(String string) 
	{
		try
		{
			return Float.parseFloat(string);
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	
	public static void decreaseShield(EntityLivingBase entity)
	{
		if(entity.isActiveItemStackBlocking() && entity.getActiveItemStack() != null && !entity.getActiveItemStack().isEmpty() && entity.getActiveItemStack().getItem() instanceof ItemAbstractProtectionShield)
		{
			ItemAbstractProtectionShield item = (ItemAbstractProtectionShield) entity.getActiveItemStack().getItem();
			item.setCharge(entity.getActiveItemStack(), item.getCharge(entity.getActiveItemStack()) - 1);
			item.setCooldown(entity.getActiveItemStack(), item.getMaxCooldown());
		}
	}
	
	public static BlockPos rotate(EnumFacing facing, BlockPos pos)
    {
        switch (facing)
        {
            case NORTH:
            default:
                return pos;
            case WEST:
                return new BlockPos(-pos.getZ(), pos.getY(), pos.getX());
            case EAST:
                return new BlockPos(-pos.getZ(), pos.getY(), -pos.getX());
            case SOUTH:
                return new BlockPos(-pos.getX(), pos.getY(), -pos.getZ());
            case UP:
            	return new BlockPos(pos.getX(), pos.getZ(), -pos.getY());
            case DOWN:
            	return new BlockPos(pos.getX(), pos.getZ(), pos.getY());
        }
    }
	
	public static void placeSelfDestroyingWall(World world, EntityPlayer player)
	{
		Vec3d vec = UtilsVoids.laser(3, player.rotationYaw, player.rotationPitch);
		
		EnumFacing facing = EnumFacing.getDirectionFromEntityLiving(new BlockPos(vec.add(player.getPositionVector())), player);
		for(int i = -2; i <= 2; ++i)
		{
			for(int j = -2; j < 5; ++j)
			{
				BlockPos p = new BlockPos(vec.add(player.getPositionVector()));
				BlockPos pos = rotate(facing, new BlockPos(i, j, 0));
				
				if(world.getBlockState(pos.add(p)).getMaterial() == Material.AIR)
				{
					world.setBlockState(pos.add(p), RegisterBlocks.DESTRUCTIVE_WALL.getDefaultState());
                    world.scheduleUpdate(pos.add(p).toImmutable(), RegisterBlocks.DESTRUCTIVE_WALL, MathHelper.getInt(new Random(), 60, 120));
				}
			}
		}
	}
	
	public static void oldSpellMethod(World world, EntityPlayer player)
	{
		if(playerNbtManager.getMathsmodTag(player).hasKey("reincarned_0") && playerNbtManager.getMathsmodTag(player).getString("reincarned_0").length() > 0)
		{
			String name = playerNbtManager.getMathsmodTag(player).getString("reincarned_0");
			EntityReincarnedPlayer entity = new EntityReincarnedPlayer(world);
			entity.setOwner(player);
			entity.setPlayer(name);
			entity.setPosition(player.posX, player.posY, player.posZ);
			world.spawnEntity(entity);
			playerNbtManager.getMathsmodTag(player).setString("reincarned_0", "");
		}
		if(playerNbtManager.getMathsmodTag(player).hasKey("reincarned_1") && playerNbtManager.getMathsmodTag(player).getString("reincarned_1").length() > 0)
		{
			String name = playerNbtManager.getMathsmodTag(player).getString("reincarned_1");
			EntityReincarnedPlayer entity = new EntityReincarnedPlayer(world);
			entity.setOwner(player);
			entity.setPlayer(name);
			entity.setPosition(player.posX, player.posY, player.posZ);
			world.spawnEntity(entity);
			playerNbtManager.getMathsmodTag(player).setString("reincarned_1", "");
		}
	}
	
	public static BlockPos getHeightIgnoreBlocks(int x, int z, World world, List<Block> ignoredBlocks)
	{
		int y = 255;
		while(y > 0)
		{
			if(world.getBlockState(new BlockPos(x, y, z)).getMaterial() != Material.AIR && !ignoredBlocks.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock()))
			{
				break;
			}
			y--;
		}
		
		return new BlockPos(x, y, z);
	}
}
