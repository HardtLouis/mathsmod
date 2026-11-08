package com.mod.mathsmod.add.GUI.screen;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerNull;
import com.mod.mathsmod.add.GUI.gunCrafting.GuiGunCraftButton;
import com.mod.mathsmod.add.GUI.gunCrafting.GuiGunCraftingHelper;
import com.mod.mathsmod.add.GUI.gunCrafting.GuiGunList;
import com.mod.mathsmod.add.items.ItemDroneLauncher;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.add.messages.LaunchDroneMessage;
import com.mod.mathsmod.utils.IGunCraft;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class guiLaunchDrone extends GuiContainer
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/drone_launcher_gui.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    protected int guiLeft;
    protected int guiTop;
    private int page;
    
    private int pageIndex;

	private float currentScroll;
	
	private GuiTextField x;
	private GuiTextField y;
	private GuiTextField z;
    
	public guiLaunchDrone(IInventory playerInv)
    {
		super(new ContainerNull(playerInv, Minecraft.getMinecraft().player));
    }
	
	@Override
	public void initGui()
    {
        super.initGui();
        
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        this.x = new GuiTextField(1, this.fontRenderer, x + 9, y + 19, 80, 18);
        this.initSearchField(this.x);
        this.y = new GuiTextField(2, this.fontRenderer, x + 9, y + 40, 80, 18);
        this.initSearchField(this.y);
        this.z = new GuiTextField(3, this.fontRenderer, x + 9, y + 61, 80, 18);
        this.initSearchField(this.z);
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
        this.x.drawTextBox();
        this.y.drawTextBox();
        this.z.drawTextBox();
    }
	
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
    {
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
    	this.buttonList.add(new GuiButton(0, x + 107, y + 40, 54, 18, "launch")
    			{
    				@Override
    				public void mouseReleased(int mouseX, int mouseY)
    				{
    					if(guiLaunchDrone.this.x.getText().length() > 0 && guiLaunchDrone.this.z.getText().length() > 0)
    					{
        					int x = NumsHelper.getIntFromString(guiLaunchDrone.this.x.getText());
        					int y = NumsHelper.getIntFromString(guiLaunchDrone.this.y.getText());
    						int z = NumsHelper.getIntFromString(guiLaunchDrone.this.z.getText());
    						Mathsmod.network.sendToServer(new LaunchDroneMessage(x, y, z));
    					}
    				}
    			});
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
            if (y != null && this.y.isFocused())
            {
                if (this.y.textboxKeyTyped(typedChar, keyCode))
                {
                }
            }
            if (z != null && this.z.isFocused())
            {
                if (this.z.textboxKeyTyped(typedChar, keyCode))
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
            if(y != null && this.isOver(y, mouseX, mouseY))
            {
            	y.setFocused(true);
            }
            else
            {
            	y.setFocused(false);
            	super.mouseReleased(mouseX, mouseY, state);
            }
            if(z != null && this.isOver(z, mouseX, mouseY))
            {
            	z.setFocused(true);
            }
            else
            {
            	z.setFocused(false);
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