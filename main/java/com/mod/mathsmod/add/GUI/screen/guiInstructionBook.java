package com.mod.mathsmod.add.GUI.screen;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;

public class guiInstructionBook extends GuiScreen
{
	
	protected int xSize = 178;
    protected int ySize = 78;
    private Item item;
    protected int guiLeft;
    protected int guiTop;
    
    private int pageIndex;

	private float currentScroll;
    
    public guiInstructionBook(int index)
    {
    	pageIndex = index;
    }
	
	public void initGui()
    {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
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
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/ib/instruction_book_page_" + this.pageIndex + ".png"));
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
    {
		
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        this.buttonList.add(new GuiButton(this.buttonList.size(), x + 2, y - 17, 18, 18, "<-")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				mc.displayGuiScreen(new guiInstructionBook(MathHelper.clamp(guiInstructionBook.this.pageIndex - 1, 0, guiInstructionBook.this.getSize())));
			}
		});
        
        this.buttonList.add(new GuiButton(this.buttonList.size(), x + 20, y - 17, 18, 18, "->")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				mc.displayGuiScreen(new guiInstructionBook(MathHelper.clamp(guiInstructionBook.this.pageIndex + 1, 0, guiInstructionBook.this.getSize())));
			}
		});
        
        I18n.translateToLocal("ib.line2.p" + this.pageIndex);
        I18n.translateToLocal("ib.line3.p" + this.pageIndex);
        I18n.translateToLocal("ib.line4.p" + this.pageIndex);
        I18n.translateToLocal("ib.line5.p" + this.pageIndex);
        
        this.drawCenteredString(fontRenderer, I18n.translateToLocal("ib.page" + this.pageIndex + ".title"), 88, 3, new Color(255, 0, 0).getRGB());
        
        for(int i = 1; i <= 5; ++i)
        {
        	this.drawCenteredString(fontRenderer, I18n.translateToLocal("ib.line" + i + ".p" + this.pageIndex), 88, 9 + i * 18, new Color(120, 120, 120).getRGB());
        }
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

	private int getSize()
	{
		return 9;
	}
}
