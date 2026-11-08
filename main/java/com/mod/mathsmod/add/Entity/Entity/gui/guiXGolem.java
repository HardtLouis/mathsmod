package com.mod.mathsmod.add.Entity.Entity.gui;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityXGolem;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerXGolem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiXGolem extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/chest/encite_gui.png");
    private final IInventory playerInvInventory;
    private final IInventory lowerChestInventory;
    /** window height is calculated with these values; the more rows, the heigher */
    private final int inventoryRows;

    public guiXGolem(IInventory playerInv, EntityXGolem container)
    {
        super(new ContainerXGolem(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.lowerChestInventory = container.getInv();
        this.allowUserInput = false;
        this.inventoryRows = container.getSizeInventory() / 18;
        this.ySize = 256;
        this.xSize = 512;
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
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 123, 2, 0);
        this.fontRenderer.drawString(this.playerInvInventory.getDisplayName().getUnformattedText(), 220, this.ySize - 98 + 2, new Color(0, 0, 255).getRGB());
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
        this.drawModalRectWithCustomSizedTexture(i + (4*18 + 9), j, 0, 0, this.xSize, this.ySize, 512, 256);
        //this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}