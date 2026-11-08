package com.mod.mathsmod.utils;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class EntityConstantSound implements ITickableSound
{
	
	private final ResourceLocation location;
	private SoundEventAccessor soundEvent;
	private Sound sound;
	private final SoundCategory category;
	Entity entity;
	
	public EntityConstantSound(ResourceLocation loc, Entity entityIn, SoundCategory categoryIn)
	{
		location = loc;
		category = categoryIn;
		entity = entityIn;
	}

	@Override
	public ResourceLocation getSoundLocation() 
	{
		return location;
	}

	@Override
	public SoundEventAccessor createAccessor(SoundHandler handler)
	{
		this.soundEvent = handler.getAccessor(this.location);

        if (this.soundEvent == null)
        {
            this.sound = SoundHandler.MISSING_SOUND;
        }
        else
        {
            this.sound = this.soundEvent.cloneEntry();
        }

        return this.soundEvent;
	}

	@Override
    public Sound getSound()
    {
        return this.sound;
    }

	@Override
	public SoundCategory getCategory()
	{
		return category;
	}

	@Override
	public boolean canRepeat() 
	{
		return false;
	}

	@Override
	public int getRepeatDelay() 
	{
		return 0;
	}

	@Override
    public float getVolume()
    {
        return this.sound.getVolume();
    }

	@Override
    public float getPitch()
    {
        return this.sound.getPitch();
    }

	@Override
	public float getXPosF() 
	{
		if(entity != null)
		{
			return (float) entity.posX;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public float getYPosF()
	{
		if(entity != null)
		{
			return (float) entity.posY + entity.getEyeHeight();
		}
		else
		{
			return -69;
		}
	}

	@Override
	public float getZPosF() 
	{
		if(entity != null)
		{
			return (float) entity.posZ;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public AttenuationType getAttenuationType() 
	{
		return AttenuationType.LINEAR;
	}

	@Override
	public void update()
	{
		
	}

	@Override
	public boolean isDonePlaying() 
	{
		return false;
	}

}
