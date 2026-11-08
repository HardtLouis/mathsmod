package com.mod.mathsmod.add.GUI.guiBasics;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.GUI.gunCrafting.GuiGunList;
import com.mod.mathsmod.add.messages.TextureManagerMessage;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class guiPlaneTexture extends GuiScreen
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/gun_craft.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    protected int guiLeft;
    protected int guiTop;
    private GuiTextField text;
    private final int id;
    
    public guiPlaneTexture(int entityID)
    {
    	id = entityID;
    }
	
	public void initGui()
    {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.text = new GuiTextField(0, this.fontRenderer, this.guiLeft + 6, this.guiTop + 29, 160, 18);
        this.text.setMaxStringLength(400);
        this.text.setVisible(true);
        this.text.setTextColor(16777215);
    }

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        super.drawScreen(mouseX, mouseY, partialTicks);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)i, (float)j, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        int k = 240;
        int l = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        RenderHelper.disableStandardItemLighting();
        this.drawGuiContainerForegroundLayer(mouseX, mouseY);
        RenderHelper.enableGUIStandardItemLighting();
        InventoryPlayer inventoryplayer = this.mc.player.inventory;


        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
    }
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Random rand = new Random();
        //GlStateManager.color(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, 200, 200);
        this.text.drawTextBox();
        
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        this.buttonList.add(new GuiButton(this.buttonList.size(), x + 2, y - 17, 18, 18, "set link")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				Mathsmod.network.sendToServer(new TextureManagerMessage(text.getText(), id));
			}
		});
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
		if(keyCode == Keyboard.KEY_ADD)
		{
			text.setFocused(!text.isFocused());
		}
		if(text.isFocused())
        {
			this.text.textboxKeyTyped(typedChar, keyCode);
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

}
