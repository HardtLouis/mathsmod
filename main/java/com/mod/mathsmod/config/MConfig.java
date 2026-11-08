package com.mod.mathsmod.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsKeys;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@net.minecraftforge.common.config.Config(modid = Mathsmod.MODID)
@net.minecraftforge.common.config.Config.LangKey("mathsmod.config")
public class MConfig 
{
	private static Configuration config;
	 
    public static Configuration getConfig()
    {
        return config;
    }
    
    public static void InitConfig(FMLPreInitializationEvent event)
    {
    	config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        save();
    }

	public static void save() 
	{
		Property prop;
		 
        List<String>propOrderClient = new ArrayList<String>();
 
        prop = config.get(Configuration.CATEGORY_CLIENT, "showGunStats", true);
        prop.setComment("show stat's of gun in player's hand");
        prop.setLanguageKey("mathsmod.config.show_stats");
        propOrderClient.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_CLIENT, "showSpell", true);
        prop.setComment("show selected spells");
        prop.setLanguageKey("mathsmod.config.show_spell");
        propOrderClient.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_CLIENT, "lgbt mod", false);
        prop.setComment("activate lgbt mod");
        prop.setLanguageKey("mathsmod.config.lgbt_mod");
        propOrderClient.add(prop.getName());
        
        prop = config.get("none", "spell1", "");
        prop.setLanguageKey("mathsmod.config.spell1");
        
        prop = config.get("none", "spell2", "");
        prop.setLanguageKey("mathsmod.config.spell1");
 
        config.setCategoryPropertyOrder(Configuration.CATEGORY_CLIENT, propOrderClient);
        
        List<String>propOrderGeneral = new ArrayList<String>();
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "silver in chunks bonus", 0);
        prop.setComment("generate more silver");
        prop.setLanguageKey("mathsmod.config.world_gen");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "silver vein size bonus", 0);
        prop.setComment("silver vein are bigger");
        prop.setLanguageKey("mathsmod.config.world_gen");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "tfosium in chunks bonus", 0);
        prop.setComment("generate more tfosium");
        prop.setLanguageKey("mathsmod.config.world_gen");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "tfosium vein size bonus", 0);
        prop.setComment("tfosium vein are bigger");
        prop.setLanguageKey("mathsmod.config.world_gen");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "kurayum in chunks bonus", 0);
        prop.setComment("generate more kurayum");
        prop.setLanguageKey("mathsmod.config.world_gen");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "kurayum vein size bonus", 0);
        prop.setComment("kurayum vein are bigger");
        prop.setLanguageKey("mathsmod.config.world_gen");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "infinite bullets", false);
        prop.setComment("players does'nt need bullet in inventory to reload guns");
        prop.setLanguageKey("mathsmod.config.pvp");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "clear lag", false);
        prop.setComment("there are clear lags when there is a lot of items on the ground");
        prop.setLanguageKey("mathsmod.config.server");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "clear lag time", 300);
        prop.setComment("time to activate clear lag");
        prop.setLanguageKey("mathsmod.config.server");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "entity limit", 300);
        prop.setComment("number of entities to activate clear lag");
        prop.setLanguageKey("mathsmod.config.server");
        propOrderGeneral.add(prop.getName());
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "enable uber armor", true);
        prop.setComment("players can craft uber armor");
        prop.setLanguageKey("mathsmod.config.server");
        propOrderGeneral.add(prop.getName());
        
        config.setCategoryPropertyOrder(Configuration.CATEGORY_GENERAL, propOrderGeneral);
        
        if(config.hasChanged())
        {
            config.save();
        }
    }
	
	public static class ConfigEvent 
	{
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) 
		{
			if(event.getModID().equals(Mathsmod.MODID)) 
			{
				save();
			}	
		}
	}
	
	public static void changeSpell1(String name) 
	{
		config.get("none", "spell1", "").set(name);
        if(config.hasChanged())
        {
            config.save();
        }
	}
	
	public static void changeSpell2(String name) 
	{
		config.get("none", "spell2", "").set(name);
        if(config.hasChanged())
        {
            config.save();
        }
	}
}
