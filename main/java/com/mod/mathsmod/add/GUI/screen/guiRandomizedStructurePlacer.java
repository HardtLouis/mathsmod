package com.mod.mathsmod.add.GUI.screen;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerNull;
import com.mod.mathsmod.add.messages.LaunchDroneMessage;
import com.mod.mathsmod.add.messages.MessageAddStructure;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class guiRandomizedStructurePlacer extends GuiContainer
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/drone_launcher_gui.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    protected int guiLeft;
    protected int guiTop;
    
    BlockPos pos;
	
	private GuiTextField x;
    
	public guiRandomizedStructurePlacer(IInventory playerInv, BlockPos posIn)
    {
		super(new ContainerNull(playerInv, Minecraft.getMinecraft().player));
		pos = posIn;
    }
	
	@Override
	public void initGui()
    {
        super.initGui();
        
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        this.x = new GuiTextField(0, this.fontRenderer, x, y + 19, 178, 18);
        this.x.setMaxStringLength(400);
        this.x.setVisible(true);
        this.x.setTextColor(16777215);
    }
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
        this.x.drawTextBox();
    }
	
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
    {
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
    	this.buttonList.add(new GuiButton(0, x + 61, y + 40, 100, 18, "add structure to block")
    			{
    				@Override
    				public void mouseReleased(int mouseX, int mouseY)
    				{
    					Mathsmod.network.sendToServer(new MessageAddStructure(guiRandomizedStructurePlacer.this.pos, guiRandomizedStructurePlacer.this.x.getText()));
    				}
    			});
	}
    
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == Keyboard.KEY_ESCAPE)
        {
            super.keyTyped(typedChar, keyCode);
        }
        else
        {
            if (x != null && this.x.isFocused())
            {
                if (this.x.textboxKeyTyped(typedChar, keyCode))
                {
                }
            }
        }
    }
	
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state == 0)
        {
            int i = mouseX - this.guiLeft;
            int j = mouseY - this.guiTop;
            if(x != null && this.isOver(x, mouseX, mouseY))
            {
            	x.setFocused(true);
            }
            else
            {
            	x.setFocused(false);
            	super.mouseReleased(mouseX, mouseY, state);
            }
            return;
        }

        super.mouseReleased(mouseX, mouseY, state);
    }
    
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	public void updateScreen()
    {
        super.updateScreen();

        if (!this.mc.player.isEntityAlive() || this.mc.player.isDead)
        {
            this.mc.player.closeScreen();
        }
    }
	
	private void initSearchField(GuiTextField sf)
	{
        sf.setMaxStringLength(9);
        sf.setTextColor(16777215);
        sf.setVisible(true);
        sf.setCanLoseFocus(false);
        sf.setText("");
	}
	
	private boolean isOver(GuiTextField sf, int mouseX, int mouseY)
	{
		return mouseX >= sf.x && mouseY >= sf.y && mouseX < sf.x + sf.width && mouseY < sf.y + sf.height;
	}
}