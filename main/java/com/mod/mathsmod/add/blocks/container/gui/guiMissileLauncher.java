package com.mod.mathsmod.add.blocks.container.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.GUI.screen.guiLaunchDrone;
import com.mod.mathsmod.add.blocks.container.ContainerMissileLauncher;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityMissileLauncher;
import com.mod.mathsmod.add.messages.LaunchDroneMessage;
import com.mod.mathsmod.add.messages.LaunchMissileMessage;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiMissileLauncher extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/missile_launcher.png");
    private final IInventory playerInvInventory;
    private final TileEntityMissileLauncher lowerChestInventory;
    /** window height is calculated with these values; the more rows, the heigher */
    private final int inventoryRows;
    
    private GuiTextField x;
	private GuiTextField y;
	private GuiTextField z;
	
	boolean bool;

    public guiMissileLauncher(IInventory playerInv, TileEntityMissileLauncher container)
    {
        super(new ContainerMissileLauncher(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.lowerChestInventory = container;
        this.allowUserInput = false;
        this.inventoryRows = container.getSizeInventory() / 18;
    }
    
    public void initGui()
    {
        super.initGui();
        
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        this.x = new GuiTextField(1, this.fontRenderer, x - 20, y, 80, 18);
        this.initSearchField(this.x);
        this.y = new GuiTextField(2, this.fontRenderer, x - 20, y + 24, 80, 18);
        this.initSearchField(this.y);
        this.z = new GuiTextField(3, this.fontRenderer, x - 20, y + 48, 80, 18);
        this.initSearchField(this.z);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
    	this.buttonList.add(new GuiButton(0, x, y + 150, 54, 18, "launch")
    			{
    				@Override
    				public void mouseReleased(int mouseX, int mouseY)
    				{
    					int x = NumsHelper.getIntFromString(guiMissileLauncher.this.x.getText());
    					int y = NumsHelper.getIntFromString(guiMissileLauncher.this.y.getText());
						int z = NumsHelper.getIntFromString(guiMissileLauncher.this.z.getText());
						Mathsmod.network.sendToServer(new LaunchMissileMessage(x, y, z, guiMissileLauncher.this.lowerChestInventory.getPos(), false));
    				}
    			});
    	
    	this.buttonList.add(new GuiButton(0, x + 55, y + 150, 54, 18, "save")
		{
			@Override
			public void mouseReleased(int mouseX, int mouseY)
			{
				int x = NumsHelper.getIntFromString(guiMissileLauncher.this.x.getText());
				int y = NumsHelper.getIntFromString(guiMissileLauncher.this.y.getText());
				int z = NumsHelper.getIntFromString(guiMissileLauncher.this.z.getText());
				Mathsmod.network.sendToServer(new LaunchMissileMessage(x, y, z, guiMissileLauncher.this.lowerChestInventory.getPos(), true));
			}
		});
    }
    
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state == 0)
        {
            int i = mouseX - this.guiLeft;
            int j = mouseY - this.guiTop;
            if(x != null && this.isOver(x, mouseX, mouseY))
            {
            	x.setFocused(true);
            	return;
            }
            else
            {
            	x.setFocused(false);
            	super.mouseReleased(mouseX, mouseY, state);
            }
            if(y != null && this.isOver(y, mouseX, mouseY))
            {
            	y.setFocused(true);
            	return;
            }
            else
            {
            	y.setFocused(false);
            	super.mouseReleased(mouseX, mouseY, state);
            }
            if(z != null && this.isOver(z, mouseX, mouseY))
            {
            	z.setFocused(true);
            	return;
            }
            else
            {
            	z.setFocused(false);
            	super.mouseReleased(mouseX, mouseY, state);
            }
            
        }

        super.mouseReleased(mouseX, mouseY, state);
    }
    
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == Keyboard.KEY_E || keyCode == Keyboard.KEY_ESCAPE)
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
            else if (y != null && this.y.isFocused())
            {
                if (this.y.textboxKeyTyped(typedChar, keyCode))
                {
                }
            }
            else if (z != null && this.z.isFocused())
            {
                if (this.z.textboxKeyTyped(typedChar, keyCode))
                {
                }
            }
        }
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
        this.drawTexturedModalRect(i + (4*18 + 9) - 4 * 18 - 9, j + 29, 0, 0, this.xSize, 128);
        this.x.drawTextBox();
        this.y.drawTextBox();
        this.z.drawTextBox();
        //this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
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