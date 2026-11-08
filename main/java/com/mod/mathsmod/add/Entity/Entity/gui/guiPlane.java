package com.mod.mathsmod.add.Entity.Entity.gui;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerPlane;
import com.mod.mathsmod.add.Entity.Entity.container.easyInv.EasyInventoryEntity;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.GUI.elements.GuiColoredButton;
import com.mod.mathsmod.add.GUI.guiBasics.guiPlaneTexture;
import com.mod.mathsmod.add.items.guns.ThreeFloat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiPlane extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/plane.png");
    private final IInventory playerInvInventory;
    private final IInventory lowerChestInventory;
    private final EntityPlane plane;
    /** window height is calculated with these values; the more rows, the heigher */
    private final int inventoryRows;

    public guiPlane(IInventory playerInv, EntityPlane container)
    {
        super(new ContainerPlane(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.plane = container;
        this.lowerChestInventory = new EasyInventoryEntity(container, container.inventory, container.getSizeInventory());;
        this.allowUserInput = false;
        int i = 222;
        int j = 114;
        this.inventoryRows = container.getSizeInventory() / 9;
        this.ySize = 114 + this.inventoryRows * 18;
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
        this.fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.playerInvInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
        
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        this.buttonList.add(new GuiButton(this.buttonList.size(), x + 2, y - 17, 18, 18, "+")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				Minecraft.getMinecraft().displayGuiScreen(new guiPlaneTexture(guiPlane.this.plane.getEntityId()));
			}
		});
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
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17 + 18);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17 + 18, 0, 126, this.xSize, 96);
    }
}