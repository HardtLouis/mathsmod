package com.mod.mathsmod.add.Entity.Entity.gui;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerSkeleton;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerWolf;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiSkeleton extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/wolf_inventory.png");
    private final IInventory playerInvInventory;
    private final EntityLiving wolf;

    public guiSkeleton(IInventory playerInv, EntityLiving container)
    {
        super(new ContainerSkeleton(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.allowUserInput = false;
        wolf = container;
        int i = 222;
        int j = 114;
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
        GuiInventory.drawEntityOnScreen(i + 88, j + 46, 22, (float)(i + 51) - mouseX, (float)(j + 75 - 50) - mouseY, this.wolf);
    }
}