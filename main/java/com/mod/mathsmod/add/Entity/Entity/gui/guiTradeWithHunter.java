package com.mod.mathsmod.add.Entity.Entity.gui;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerHunter;
import com.mod.mathsmod.add.GUI.guiBasics.guiPlaneTexture;
import com.mod.mathsmod.add.messages.MessagePerformTrade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class guiTradeWithHunter extends GuiContainer
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/gun_craft.png");
	private int num;
	private int tid = -1;
	int entityId;
	EntityHunter ent;
	EntityPlayer player;
	
	public guiTradeWithHunter(InventoryPlayer inventorySlotsIn, EntityHunter entity, EntityPlayer p)
	{
		super(new ContainerHunter(inventorySlotsIn, entity));
		num = entity.tradesNum();
		entityId = entity.getEntityId();
		ent = entity;
		player = p;
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
		
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        for(int i = 0; i < num; ++i)
        {

            this.buttonList.add(new GuiButton(i, x + 2, y + 2 + i * 18, 72, 18, "")
    		{
    			public void mouseReleased(int mouseX, int mouseY)
    			{
    				tid = this.id;
    			}
    		});
            
            for(GuiButton button : this.buttonList)
            {
            	button.enabled = tid != button.id;
            }
            dessineToutFdp(this.inventorySlots.getSlot(i * 3).getStack(), 4, 2 + i * 18);
            dessineToutFdp(this.inventorySlots.getSlot(i * 3 + 1).getStack(), 22, 2 + i * 18);

            dessineToutFdp(this.inventorySlots.getSlot(i * 3 + 2).getStack(), 58, 2 + i * 18);
                
            this.buttonList.add(new GuiButton(num, x + 2, y + 11 + num * 18, 72, 18, "trade")
    		{
    			public void mouseReleased(int mouseX, int mouseY)
    			{
    				ent.performTrade(tid, player);
    				Mathsmod.network.sendToServer(new MessagePerformTrade(tid, entityId));
    			}
    		});
        }
    }
	
	private void dessineToutFdp(ItemStack stack, int x, int y)
	{
		Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
		Minecraft.getMinecraft().getRenderItem().renderItemOverlayIntoGUI(fontRenderer, stack, x, y, null);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
	}

}
