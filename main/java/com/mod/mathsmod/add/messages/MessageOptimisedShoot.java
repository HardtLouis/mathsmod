package com.mod.mathsmod.add.messages;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.MathsTriggers;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemJetpack;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemShotGun;
import com.mod.mathsmod.utils.NumsHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageOptimisedShoot implements IMessage
{
	
	float damages;
	int randomX;
	int randomY;
	int randomZ;
	int rotationYaw;
	int rotationPitch;
	boolean bool;
	int entityID;
	BlockPos pos;
	float y;
	
	public MessageOptimisedShoot()
	{
	}
	
	public MessageOptimisedShoot(float damagesIn, float rX, float rY, float rZ, float yaw, float pitch, @Nullable Entity entityIn, @Nullable BlockPos blockPos, float posY)
	{
		damages = damagesIn;
		randomX = (int) rX;
		randomY = (int) rY;
		randomZ = (int) rZ;
		rotationYaw = (int) yaw;
		rotationPitch = (int) pitch;
		y = posY;
		if(entityIn != null)
		{
			entityID = entityIn.getEntityId();
		}
		else
		{
			entityID = -1;
		}
		pos = blockPos;
	}
	
	public MessageOptimisedShoot(float damagesIn, float rX, float rY, float rZ, float yaw, float pitch, boolean leftC, @Nullable Entity entityIn, @Nullable BlockPos blockPos, float posY)
	{
		damages = damagesIn;
		randomX = (int) rX;
		randomY = (int) rY;
		randomZ = (int) rZ;
		rotationYaw = (int) yaw;
		rotationPitch = (int) pitch;
		bool = leftC;
		y = posY;
		if(entityIn != null)
		{
			entityID = entityIn.getEntityId();
		}
		else
		{
			entityID = -1;
		}
		pos = blockPos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		String i = ByteBufUtils.readUTF8String(buf);
		String[] e = i.split("tt");
		damages = NumsHelper.getFloatFromString(e[0]);
		randomX = NumsHelper.getIntFromString(e[1]);
		randomY = NumsHelper.getIntFromString(e[2]);
		randomZ = NumsHelper.getIntFromString(e[3]);
		rotationYaw = NumsHelper.getIntFromString(e[4]);
		rotationPitch = NumsHelper.getIntFromString(e[5]);
		y = NumsHelper.getFloatFromString(e[6]);
		entityID = NumsHelper.getIntFromString(e[7]);
		if(e.length > 8 && (e[8] == "ui" || !NumsHelper.isInteger(e[8])))
		{
			bool = true;
			if(e.length > 9)
			{
				pos = new BlockPos(NumsHelper.getIntFromString(e[9]), NumsHelper.getIntFromString(e[10]), NumsHelper.getIntFromString(e[11]));
			}
		}
		else
		{
			if(e.length > 8)
			{
				pos = new BlockPos(NumsHelper.getIntFromString(e[8]), NumsHelper.getIntFromString(e[9]), NumsHelper.getIntFromString(e[10]));
			}
		}
		//System.out.print(i);
		//System.out.print(System.lineSeparator());
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		String i = "";
		
		i += damages + "tt";
		i += randomX + "tt";
		i += randomY + "tt";
		i += randomZ + "tt";
		i += rotationYaw + "tt";
		i += rotationPitch + "tt";
		i += y + "tt";
		
		if(entityID > 0)
		{
			i += entityID;
		}
		else
		{
			i += "-1";
		}
		
		if(bool)
		{
			i += "ui";
		}
		
		if(pos != null)
		{
			i += "tt";
			i += pos.getX() + "tt";
			i += pos.getY() + "tt";
			i += pos.getZ();
		}
		
		ByteBufUtils.writeUTF8String(buf, i);
	}
	
	public static class MessageShootHandler implements IMessageHandler <MessageOptimisedShoot, IMessage> {

        @Override
        public IMessage onMessage(MessageOptimisedShoot msg, MessageContext ctx)
        {
        	EntityPlayer player = ctx.getServerHandler().player;
        	World world = player.world;
        	ItemStack stack = player.getHeldItemMainhand();
        	
        	if(player.isRiding() && player.getRidingEntity() instanceof IVehicle)
        	{
        		for(double i = 0; i <= 30; i += 0.25)
	        	{
	        		Vec3d vec = UtilsVoids.laserWithInnacuracy(i, msg.rotationYaw, msg.rotationPitch, msg.randomX, msg.randomY, msg.randomZ);
	        		world.spawnAlwaysVisibleParticle(60, player.posX + vec.x, player.posY + vec.y, player.posZ + vec.z, 0, 0, 0);
	        	}
        		
        		if(msg.entityID >= 0 && world.getEntityByID(msg.entityID) != null)
    			{
    				Entity entity = world.getEntityByID(msg.entityID);
    				RegisterItems.SNIPER_BULLET.onImpact(new RayTraceResult(entity), world, player, entity, msg.rotationYaw, msg.rotationPitch, entity.posX, msg.y, entity.posZ, new ItemStack(RegisterItems.SCAR_H), (ItemGun) RegisterItems.SCAR_H, player.posX, player.posX + player.getEyeHeight(), player.posZ, false);
    			}
    			else if(msg.pos != null)
    			{
    				BlockPos pos = msg.pos;
    				RegisterItems.SNIPER_BULLET.onImpact(new RayTraceResult(new Vec3d(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5), UtilsVoids.getDirectionFromEntity(pos, player), pos), world, player, null, msg.rotationYaw, msg.rotationPitch, player.posX, msg.y, player.posZ, new ItemStack(RegisterItems.SCAR_H), (ItemGun) RegisterItems.SCAR_H, player.posX, player.posX + player.getEyeHeight(), player.posZ, false);
    			}
        	}
        	else
        	{
        		if(stack.getItem() instanceof ItemGun)
            	{
            		ItemGun gun = (ItemGun) stack.getItem();
            		float damages = msg.damages;
            		float power = gun.getPower(stack, player);
            		float inaccuracy = (gun.getInnacuracy(stack, player) + ItemGun.getShootDefault(stack)) * gun.innacuracyMultiplier(stack, player);
            		int reach = gun.getReach();
            		boolean far = gun.lowBulletFar();
            		
            		for(double i = 0; i <= reach; i += 0.25)
    	        	{
    	        		Vec3d vec = UtilsVoids.laserWithInnacuracy(i, msg.rotationYaw, msg.rotationPitch, msg.randomX, msg.randomY, msg.randomZ);
    	        		world.spawnAlwaysVisibleParticle(60, player.posX + vec.x, player.posY + vec.y, player.posZ + vec.z, 0, 0, 0);
    	        	}
            		
            		if(gun.hasTravel())
            		{
            			if(gun.lastBullet(stack, player) != null)
                		{
                			ItemBaseBullet item = gun.lastBullet(stack, player);
                			if(msg.entityID >= 0 && world.getEntityByID(msg.entityID) != null)
                			{
                				Entity entity = world.getEntityByID(msg.entityID);
                				item.onImpact(new RayTraceResult(entity), world, player, entity, msg.rotationYaw, msg.rotationPitch, entity.posX, msg.y, entity.posZ, stack, gun, player.posX, player.posX + player.getEyeHeight(), player.posZ, false);
                				if(gun instanceof ItemShotGun)
                				{
                					MathsTriggers.Trigger(player, MathsTriggers.MEATSHOT);
                				}
                			}
                			else if(msg.pos != null && item != null)
                			{
                				BlockPos pos = msg.pos;
                				item.onImpact(new RayTraceResult(new Vec3d(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5), UtilsVoids.getDirectionFromEntity(pos, player), pos), world, player, null, msg.rotationYaw, msg.rotationPitch, player.posX, msg.y, player.posZ, stack, gun, player.posX, player.posX + player.getEyeHeight(), player.posZ, false);
                			}
                		}
            		}
            		else
            		{
            			if(gun.bulletUsed(stack, player) != null)
                		{
                			ItemBaseBullet item = gun.bulletUsed(stack, player);
                			if(msg.entityID >= 0 && world.getEntityByID(msg.entityID) != null)
                			{
                				Entity entity = world.getEntityByID(msg.entityID);
                				item.onImpact(new RayTraceResult(entity), world, player, entity, msg.rotationYaw, msg.rotationPitch, entity.posX, msg.y, entity.posZ, stack, gun, player.posX, player.posX + player.getEyeHeight(), player.posZ, false);
                				if(gun instanceof ItemShotGun)
                				{
                					MathsTriggers.Trigger(player, MathsTriggers.MEATSHOT);
                				}
                			}
                			else if(msg.pos != null && item != null)
                			{
                				BlockPos pos = msg.pos;
                				item.onImpact(new RayTraceResult(new Vec3d(pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5), UtilsVoids.getDirectionFromEntity(pos, player), pos), world, player, null, msg.rotationYaw, msg.rotationPitch, player.posX, msg.y, player.posZ, stack, gun, player.posX, player.posX + player.getEyeHeight(), player.posZ, false);
                			}
                		}
            		}
            	}
        	}
        		
        	return null;
        }
    }
}