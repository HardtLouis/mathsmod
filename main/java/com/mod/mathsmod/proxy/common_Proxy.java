package com.mod.mathsmod.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.EntitiesInit;
import com.mod.mathsmod.add.MathsTriggers;
import com.mod.mathsmod.add.achievements.Trigger;
import com.mod.mathsmod.add.event.WorldEvents;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelEnderman;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class common_Proxy
{
	
	public common_Proxy() {
		
	}
	
	public ModelBiped getArmorModel(int i) {
		return null;
	}
	

	public void preInit()
	{
		EntitiesInit.registerEntities();
	}
	
	public void Init()
	{
		MathsTriggers.register();
	}
	
	public void postInit()
	{
		
	}

}
