package com.mod.mathsmod.utils;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Logger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsKeys;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.IReachWeapon;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.messages.MessageReachSword;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class reachHelper {
	
	public static Entity pointedEntity;
	public static RayTraceResult result;

	public static void getMouseOver(float partialTicks, EntityPlayer player, IReachWeapon item)
    {
        Entity entity = player;
        Minecraft mc = Minecraft.getMinecraft();
        
        if (entity != null)
        {
            if (mc.world != null)
            {
                mc.mcProfiler.startSection("pick");
                reachHelper.pointedEntity = null;
                double d0 = item != null ? (double)mc.playerController.getBlockReachDistance() + item.bonusReach(player, player.getHeldItemMainhand()) : (double)mc.playerController.getBlockReachDistance();
                result = entity.rayTrace(d0, partialTicks);
                Vec3d vec3d = entity.getPositionEyes(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1 = d0;

                if (mc.playerController.extendedReach())
                {
                    d1 = 6.0D + (item != null ? item.bonusReach(player) : 0);
                    d0 = d1;
                }
                else
                {
                    if (d0 > 3.0D + (item != null ? item.bonusReach(player) : 0))
                    {
                        flag = true;
                    }
                }

                if (result != null)
                {
                    d1 = result.hitVec.distanceTo(vec3d);
                }

                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                Entity pointedEntity = null;
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                        return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
                    }
                }));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

                    if (axisalignedbb.contains(vec3d))
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null)
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > 3.0D + (item != null ? item.bonusReach(player) : 0))
                {
                    pointedEntity = null;
                    result = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
                }

                if (pointedEntity != null && (d2 < d1 || result == null))
                {
                	result = new RayTraceResult(pointedEntity, vec3d3);

                    if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame)
                    {
                    	reachHelper.pointedEntity = pointedEntity;
                    	result = new RayTraceResult(pointedEntity);
                    }
                }

                mc.mcProfiler.endSection();
            }
        }
    }
	
	public static void clickMouse()
    {
		Minecraft mc = Minecraft.getMinecraft();
		int leftClickCounter = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, 41);
		Logger LOGGER = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, 0);
		
        if (leftClickCounter <= 0)
        {
            if (result == null)
            {
            	LOGGER.error("Null returned as 'hitResult', this shouldn't happen!");

                if (mc.playerController.isNotCreative())
                {
                	ObfuscationReflectionHelper.setPrivateValue(Minecraft.class, mc, 41, 10);
                }
            }
            else if (!mc.player.isRowingBoat())
            {
                switch (result.typeOfHit)
                {
                    case ENTITY:
                    	reachHelper.attackEntity(mc.player, pointedEntity, false);
                        break;
                    case MISS:
                    	break;
                    case BLOCK:
                    	break;
                }

                mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
	
	public static void attackEntity(EntityPlayer playerIn, Entity targetEntity, boolean ignoreDist)
    {
		if(targetEntity != null && (playerIn.getDistance(targetEntity) > 3 || ignoreDist))
		{
			Mathsmod.network.sendToServer(new MessageReachSword(targetEntity));

	        if (Minecraft.getMinecraft().playerController.getCurrentGameType() != GameType.SPECTATOR && playerIn.getHeldItemMainhand().getItem() instanceof IReachWeapon)
	        {
	            UtilsVoids.attackTargetEntityWithCurrentReachItem(targetEntity, playerIn, (IReachWeapon) playerIn.getHeldItemMainhand().getItem());
	            playerIn.resetCooldown();
	        }
		}
    }
	
	
	public static void getShoot(float partialTicks, EntityPlayer player, ItemGun item)
    {
        Entity entity = player;
        Minecraft mc = Minecraft.getMinecraft();
        
        if (entity != null)
        {
            if (mc.world != null)
            {
                mc.mcProfiler.startSection("pick");
                reachHelper.pointedEntity = null;
                double d0 = item.getReach();
                result = entity.rayTrace(d0, partialTicks);
                Vec3d vec3d = entity.getPositionEyes(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1 = d0;

                if (mc.playerController.extendedReach())
                {
                    d1 = item.getReach();
                    d0 = d1;
                }
                else
                {
                    if (d0 > item.getReach())
                    {
                        flag = true;
                    }
                }

                if (result != null)
                {
                    d1 = result.hitVec.distanceTo(vec3d);
                }

                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                Entity pointedEntity = null;
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                        return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
                    }
                }));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

                    if (axisalignedbb.contains(vec3d))
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null)
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > item.getReach())
                {
                    pointedEntity = null;
                    result = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
                }

                if (pointedEntity != null && (d2 < d1 || result == null))
                {
                	result = new RayTraceResult(pointedEntity, vec3d3);

                    if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame)
                    {
                    	reachHelper.pointedEntity = pointedEntity;
                    	result = new RayTraceResult(pointedEntity);
                    }
                }

                mc.mcProfiler.endSection();
            }
        }
    }
	
}
