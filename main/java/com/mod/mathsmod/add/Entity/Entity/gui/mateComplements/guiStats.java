package com.mod.mathsmod.add.Entity.Entity.gui.mateComplements;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class guiStats extends GuiScreen
{
	
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/mate_golem/mate_stats.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    public Container inventorySlots;
    protected int guiLeft;
    protected int guiTop;
    private EntityMateGolem golem;
    
    public guiStats(EntityMateGolem entity)
    {
    	golem = entity;
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
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
        float y =  124 * (this.golem.getXP() / (this.golem.getXpToLevel(golem.getLevel() + 1)));
        this.drawTexturedModalRect(i + 27, j + 64, 27, 158, (int) ((int)1 + (124 * (this.golem.getHealth() / this.golem.getMaxHealth()))), 8);
        GuiInventory.drawEntityOnScreen(i + 88, j + 46, 20, (float)(i + 51) - mouseX, (float)(j + 75 - 50) - mouseY, this.golem);
    }

	private void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
		int i = (14 + golem.getLevel() / 5);
		this.drawString(fontRenderer, golem.getMaxHealth() + "", 32, 61, new Color(255, 0, 0).getRGB());
		this.drawString(fontRenderer, i + "", 32, 71, new Color(0, 0, 255).getRGB());
		this.drawString(fontRenderer, golem.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() + "", 32, 81, new Color(100, 100, 0).getRGB());
		
		GlStateManager.scale(3, 3, 3);
		this.drawString(fontRenderer, golem.getCustomNameTag() != null ? golem.getCustomNameTag() : golem.getName(), 32, 70, new Color(0, 0, 255).getRGB());
		
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
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
        {
            this.mc.player.closeScreen();
        }
    }
}
