package com.mod.mathsmod.add;

import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class MathsKeys {


	public static final KeyBinding UseSpell1 = new KeyBinding("key.mathsmod.spellUse1", org.lwjgl.input.Keyboard.KEY_B, "key.categories.spells");
	public static final KeyBinding UseSpell2 = new KeyBinding("key.mathsmod.toggle_armor", org.lwjgl.input.Keyboard.KEY_N, "key.categories.gameplay");
	public static final KeyBinding SpellGui = new KeyBinding("key.mathsmod.spellGui", org.lwjgl.input.Keyboard.KEY_U, "key.categories.spells");
	public static final KeyBinding reload = new KeyBinding("key.mathsmod.reload", org.lwjgl.input.Keyboard.KEY_R, "key.categories.gameplay");
	public static final KeyBinding dab = new KeyBinding("key.mathsmod.dab", org.lwjgl.input.Keyboard.KEY_F, "key.categories.gameplay");
	public static final KeyBinding contracts = new KeyBinding("key.mathsmod.contracts", org.lwjgl.input.Keyboard.KEY_F, "key.categories.gameplay");
	
	public static void registerKeys()
	{
		ClientRegistry.registerKeyBinding(UseSpell1);
		ClientRegistry.registerKeyBinding(UseSpell2);
		ClientRegistry.registerKeyBinding(SpellGui);
		ClientRegistry.registerKeyBinding(reload);
		ClientRegistry.registerKeyBinding(dab);
		ClientRegistry.registerKeyBinding(contracts);
	}
}
