package com.mod.mathsmod.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import paulscode.sound.SoundSystem;

public class SoundHelper {

	
	public static void play(SoundEvent event, float pitch, float volume)
	{
		SoundManager sm = ObfuscationReflectionHelper.getPrivateValue(SoundHandler.class, Minecraft.getMinecraft().getSoundHandler(), 5);
		SoundSystem sndSystem = ObfuscationReflectionHelper.getPrivateValue(SoundManager.class, sm, 5);
		
		//Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(event, pitch));
		ISound p_sound = PositionedSoundRecord.getMasterRecord(event, pitch);
		SoundCategory soundcategory = p_sound.getCategory();
        float f1 = volume;
        float f2 = pitch;
        p_sound.createAccessor(Minecraft.getMinecraft().getSoundHandler());
        Sound sound = p_sound.getSound();

        if (f1 == 0.0F)
        {
        }
        else
        {

            float f3 = p_sound.getVolume();
            float f = 16.0F;

            if (f3 > 1.0F)
            {
                f *= f3;
            }
            
            boolean flag = p_sound.canRepeat() && p_sound.getRepeatDelay() == 0;
            String s = MathHelper.getRandomUUID(ThreadLocalRandom.current()).toString();
            ResourceLocation resourcelocation1 = sound.getSoundAsOggLocation();

            if (sound.isStreaming())
            {
                sndSystem.newStreamingSource(false, s, getURLForSoundResource(resourcelocation1), resourcelocation1.toString(), flag, p_sound.getXPosF(), p_sound.getYPosF(), p_sound.getZPosF(), p_sound.getAttenuationType().getTypeInt(), f);
            }
            else
            {
                sndSystem.newSource(false, s, getURLForSoundResource(resourcelocation1), resourcelocation1.toString(), flag, p_sound.getXPosF(), p_sound.getYPosF(), p_sound.getZPosF(), p_sound.getAttenuationType().getTypeInt(), f);
            }
            
            sndSystem.setPitch(s, f2);
            sndSystem.setVolume(s, f1);
            sndSystem.play(s);
        }
	}
	
	private static URL getURLForSoundResource(final ResourceLocation p_148612_0_)
    {
        String s = String.format("%s:%s:%s", "mcsounddomain", p_148612_0_.getResourceDomain(), p_148612_0_.getResourcePath());
        URLStreamHandler urlstreamhandler = new URLStreamHandler()
        {
            protected URLConnection openConnection(URL p_openConnection_1_)
            {
                return new URLConnection(p_openConnection_1_)
                {
                    public void connect() throws IOException
                    {
                    }
                    public InputStream getInputStream() throws IOException
                    {
                        return Minecraft.getMinecraft().getResourceManager().getResource(p_148612_0_).getInputStream();
                    }
                };
            }
        };

        try
        {
            return new URL((URL)null, s, urlstreamhandler);
        }
        catch (MalformedURLException var4)
        {
            throw new Error("TODO: Sanely handle url exception! :D");
        }
    }
}
