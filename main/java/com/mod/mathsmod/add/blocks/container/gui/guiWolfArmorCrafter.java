package com.mod.mathsmod.add.blocks.container.gui;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.blocks.container.ContainerWolfArmorCrafter;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityWolfArmorCrafter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiWolfArmorCrafter extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/wolf_armor_crafter.png");
    private final IInventory playerInvInventory;
    private final TileEntityWolfArmorCrafter lowerChestInventory;
    /** window height is calculated with these values; the more rows, the heigher */
    private final int inventoryRows;

    public guiWolfArmorCrafter(IInventory playerInv, TileEntityWolfArmorCrafter container)
    {
        super(new ContainerWolfArmorCrafter(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.lowerChestInventory = container;
        this.allowUserInput = false;
        this.inventoryRows = container.getSizeInventory() / 18;
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
        //this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
    
}