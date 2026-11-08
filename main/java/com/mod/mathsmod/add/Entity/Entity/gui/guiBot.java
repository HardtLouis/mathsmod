package com.mod.mathsmod.add.Entity.Entity.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerBot;
import com.mod.mathsmod.add.messages.EntityMessage;
import com.mod.mathsmod.add.messages.MessageBotChanges;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiBot extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/bot_inventory2.png");
    private final IInventory playerInvInventory;
    private final EntityBot bot;
    private final int ID;
    private GuiTextField text;

    public guiBot(IInventory playerInv, EntityBot container, int entityID)
    {
        super(new ContainerBot(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.allowUserInput = false;
        bot = container;
        ID = entityID;
        int i = 222;
        int j = 114;
    }
    
    public void initGui()
    {
        super.initGui();
        this.text = new GuiTextField(0, this.fontRenderer, this.guiLeft, this.guiTop - 19, 160, 18);
        this.text.setMaxStringLength(400);
        this.text.setVisible(true);
        this.text.setTextColor(16777215);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	this.addButton(new GuiButton(0, this.guiLeft, this.guiTop, 42, 18, "skin")
    	{
    		public void mouseReleased(int mouseX, int mouseY)
    		{
    			if(guiBot.this.text.getText() != null && !guiBot.this.text.getText().isEmpty())
    			{
        			Mathsmod.network.sendToServer(new EntityMessage(guiBot.this.bot.getUniqueID().toString(), guiBot.this.text.getText()));
    			}
    		}
    	});
    	
    	this.addButton(new GuiButton(1, this.guiLeft, this.guiTop + 18, 42, 18, "change mod")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				Mathsmod.network.sendToServer(new EntityMessage(guiBot.this.bot.getUniqueID().toString(), true));
			}
		});
    	
    	this.addButton(new GuiButton(2, this.guiLeft, this.guiTop + 36, 42, 18, "set S-pos")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				Mathsmod.network.sendToServer(new MessageBotChanges(guiBot.this.bot.getUniqueID().toString(), 0, 0));
			}
		});
    	
    	this.addButton(new GuiButton(3, this.guiLeft, this.guiTop + 54, 42, 18, "friendly mod")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				Mathsmod.network.sendToServer(new MessageBotChanges(guiBot.this.bot.getUniqueID().toString(), 1, 0));
			}
		});
        /*this.fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInvInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);*/
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j + 17 + 18 - 40, 0, 0, 176, 163);
        GuiInventory.drawEntityOnScreen(i + 88, j + 56, 22, (float)(i + 51) - mouseX, (float)(j + 75 - 50) - mouseY, this.bot);
        this.text.drawTextBox();
    }
    
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
		if(text.isFocused())
        {
			this.text.textboxKeyTyped(typedChar, keyCode);
			return;
        }
		if(keyCode == Keyboard.KEY_ESCAPE)
        {
            super.keyTyped(typedChar, keyCode);
        }
        else
        {
            super.keyTyped(typedChar, keyCode);
        }
    }
    
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state == 0)
        {
            if(text != null && this.isOver(text, mouseX, mouseY))
            {
            	text.setFocused(true);
            	return;
            }
            else
            {
            	if(text.isFocused())
            	{
                	text.setFocused(false);
            	}
            	super.mouseReleased(mouseX, mouseY, state);
            }
        }
        else
        {
        	super.mouseReleased(mouseX, mouseY, state);
        }

        
    }
    
    private boolean isOver(GuiTextField sf, int mouseX, int mouseY)
	{
		return mouseX >= sf.x && mouseY >= sf.y && mouseX < sf.x + sf.width && mouseY < sf.y + sf.height;
	}
}