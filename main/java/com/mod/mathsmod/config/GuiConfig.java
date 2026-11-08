package com.mod.mathsmod.config;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.ConfigGuiType;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiConfig  implements IModGuiFactory
{
 
    @Override
    public void initialize(Minecraft minecraftInstance)
    {}

	@Override
	public boolean hasConfigGui() 
	{
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) 
	{
		return new GuiConfig.bruh(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() 
	{
		return null;
	}
	
	public static class bruh extends net.minecraftforge.fml.client.config.GuiConfig
    {
        public bruh(GuiScreen parent)
        {
            super(parent, getConfigElements(), Mathsmod.MODID, false, false, I18n.format("mathsmod.config_gui.name", new Object[] {}));
        }
 
        private static List<IConfigElement> getConfigElements()
        {
        	List<IConfigElement> list = Lists.newArrayList();
        	//list.add(new DummyCategoryElement("modtutoConfig", "modtuto.config.gui.generalTitle", new ConfigElement(ModTutorielConfig.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()));
        	list.add(new DummyConfigElement.DummyCategoryElement("client", "mathsmod.config", new ConfigElement(MConfig.getConfig().getCategory(Configuration.CATEGORY_CLIENT)).getChildElements()));
        	
        	list.add(new DummyConfigElement.DummyCategoryElement("general", "mathsmod.config", new ConfigElement(MConfig.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()));
            //list.add(new ConfigElement(MConfig.Config().getCategory(Configuration.CATEGORY_CLIENT)));
        	return list;
        }
    }
 
}