package com.mod.mathsmod.add;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mod.mathsmod.add.achievements.Trigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class MathsTriggers {

	public static final Trigger FRYING_PAN = new Trigger("frying_pan");
	public static final Trigger MEATSHOT = new Trigger("meatshot");
	public static final Trigger BAZAR_SHOTGUN = new Trigger("bazar_shotgun");
	
	public static void register()
	{
		Method method;

        method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);

        method.setAccessible(true);
        
        try 
        {
			method.invoke(null , FRYING_PAN);
			method.invoke(null , MEATSHOT);
			method.invoke(null , BAZAR_SHOTGUN);
		} 
        catch (IllegalAccessException e) 
        {
			e.printStackTrace();
		}
        catch (IllegalArgumentException e)
        {
			e.printStackTrace();
		}
        catch (InvocationTargetException e)
        {
			e.printStackTrace();
		}
	}
	
	public static void Trigger(EntityPlayer player, Trigger trigger)
	{
		if(trigger.listeners != null && player instanceof EntityPlayerMP)
		{
			if(trigger.listeners.get(((EntityPlayerMP) player).getAdvancements()) != null)
			{
				trigger.listeners.get(((EntityPlayerMP) player).getAdvancements()).trigger((EntityPlayerMP) player);
			}
		}
	}
	
}
