package com.mod.mathsmod.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureHelper 
{
	
	static HashMap<String, GameProfile> list = Maps.newHashMap();

	public static ResourceLocation getPlayerSkin(String playerName)
	{
		
		if(playerName == null || playerName.length() == 0)
		{
			return new ResourceLocation("textures/entity/steve.png");
		}
		
		ResourceLocation resourcelocation = new ResourceLocation("skins/" + playerName);
		GameProfile profile = new GameProfile((UUID)null, playerName);
		
		if(!list.containsValue(profile) && !list.containsKey(playerName))
		{
			profile = TileEntitySkull.updateGameprofile(profile);
			list.put(playerName, profile);
		}
		else
		{
			profile = list.get(playerName);
		}

        if (profile != null)
        {
        	if(profile.isComplete())
        	{
        		Minecraft minecraft = Minecraft.getMinecraft();
                Map<Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(profile);

                boolean littleArm = DefaultPlayerSkin.getSkinType(profile.getId()) == "slim";
                
                if (map.containsKey(Type.SKIN))
                {
                    resourcelocation = minecraft.getSkinManager().loadSkin(map.get(Type.SKIN), Type.SKIN);
                }
                else
                {
                    UUID uuid = EntityPlayer.getOfflineUUID(playerName);
                    resourcelocation = DefaultPlayerSkin.getDefaultSkin(uuid);
                }
        	}
            else
            {
                UUID uuid = EntityPlayer.getOfflineUUID(playerName);
                resourcelocation = DefaultPlayerSkin.getDefaultSkin(uuid);
            }
        }
		return resourcelocation;
	}
	
	static HashMap<String, NetworkPlayerInfo> list2 = Maps.newHashMap();
	
	public static boolean smallArms(String playerName)
	{
		if(playerName == null || playerName.length() == 0)
		{
			return false;
		}
		
		ResourceLocation resourcelocation = new ResourceLocation("skins/" + playerName);
		GameProfile profile = new GameProfile((UUID)null, playerName);
		
		if(!list.containsValue(profile) && !list.containsKey(playerName))
		{
			profile = TileEntitySkull.updateGameprofile(profile);
			list.put(playerName, profile);
		}
		else
		{
			profile = list.get(playerName);
		}

        if (profile != null)
        {
        	if(profile.isComplete())
        	{
        		NetworkPlayerInfo info = null;
        		if((!list2.containsKey(playerName)))
        		{
        			info = new NetworkPlayerInfo(profile);
        			list2.put(playerName, info);
        			info.getLocationSkin();
        			return info.getSkinType() != "default";
        		}
        		else
        		{
        			info = list2.get(playerName);
        			return info.getSkinType() != "default";
        		}
               
        	}
            else
            {
                UUID uuid = EntityPlayer.getOfflineUUID(playerName);
                return DefaultPlayerSkin.getSkinType(uuid) == "slim";
            }
        }
        else
        {
            UUID uuid = EntityPlayer.getOfflineUUID(playerName);
            return DefaultPlayerSkin.getSkinType(uuid) == "slim";
        }
	}
	
}
