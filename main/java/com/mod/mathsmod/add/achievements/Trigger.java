package com.mod.mathsmod.add.achievements;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class Trigger implements ICriterionTrigger<ICriterionInstance>{

    public final Map<PlayerAdvancements, Trigger.Listeners> listeners = Maps.newHashMap();
    ResourceLocation loc;
	
    public Trigger(String str)
    {
    	loc = new ResourceLocation(str);
    }
    
    
	@Override
	public ResourceLocation getId()
	{
		return loc;
	}
	
	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<ICriterionInstance> listener)
	{
		Trigger.Listeners listeners = this.listeners.get(playerAdvancementsIn);

        if (listeners == null)
        {
            listeners = new Trigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, listeners);
        }

        listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<ICriterionInstance> listener) 
	{
		Trigger.Listeners listeners = this.listeners.get(playerAdvancementsIn);

        if (listeners != null)
        {
            listeners.remove(listener);

            if (listeners.isEmpty())
            {
            	this.listeners.remove(playerAdvancementsIn);
            }
        }
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) 
	{
		listeners.remove(playerAdvancementsIn);
	}

	@Override
	public ICriterionInstance deserializeInstance(JsonObject json, JsonDeserializationContext context) 
	{
		return new Trigger.Instance(getId());
	}
	
	public static class Instance extends AbstractCriterionInstance
    {
        
        /**
         * Instantiates a new instance.
         */
        public Instance(ResourceLocation resourceLocation)
        {
            super(resourceLocation);
        }
        
    }

    public static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<ICriterionTrigger.Listener> listeners = Sets.newHashSet();

        /**
         * Instantiates a new listeners.
         *
         * @param playerAdvancementsIn the player advancements in
         */
        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            playerAdvancements = playerAdvancementsIn;
        }

        /**
         * Checks if is empty.
         *
         * @return true, if is empty
         */
        public boolean isEmpty()
        {
            return listeners.isEmpty();
        }

        /**
         * Adds the listener.
         *
         * @param listener the listener
         */
        public void add(ICriterionTrigger.Listener listener)
        {
            listeners.add(listener);
        }

        /**
         * Removes the listener.
         *
         * @param listener the listener
         */
        public void remove(ICriterionTrigger.Listener listener)
        {
            listeners.remove(listener);
        }

        /**
         * Trigger.
         *
         * @param player the player
         */
        public void trigger(EntityPlayerMP player)
        {
            ArrayList<ICriterionTrigger.Listener> list = null;

            for (ICriterionTrigger.Listener listener : listeners)
            {
            	if (list == null)
                {
                    list = Lists.newArrayList();
                }

                list.add(listener);
            }

            if (list != null)
            {
                for (ICriterionTrigger.Listener listener1 : list)
                {
                    listener1.grantCriterion(playerAdvancements);
                }
            }
        }
    }
}
