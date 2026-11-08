package com.mod.mathsmod.add.blocks.container.gui;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.GUI.elements.GuiColoredButton;
import com.mod.mathsmod.add.GUI.gunCrafting.GuiGunList;
import com.mod.mathsmod.add.blocks.container.ContainerGunCrafter;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityGunCrafter;
import com.mod.mathsmod.add.items.guns.ThreeFloat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiGunsCrafter extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/guns_crafter.png");
    private final IInventory playerInvInventory;
    private final TileEntityGunCrafter lowerChestInventory;
    /** window height is calculated with these values; the more rows, the heigher */
    private final int inventoryRows;

    public GuiGunsCrafter(IInventory playerInv, TileEntityGunCrafter container)
    {
        super(new ContainerGunCrafter(playerInv, container, Minecraft.getMinecraft().player));
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
        //this.fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 123, 2, 0);
        //this.fontRenderer.drawString(this.playerInvInventory.getDisplayName().getUnformattedText(), 220, this.ySize - 98 + 2, new Color(0, 0, 255).getRGB());
    	//GlStateManager.color(1, 0, 0);
    	int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiColoredButton(0, i + 2, j + 12, 35, 18, "Crafts", new ThreeFloat(1, 0, 0), new ThreeFloat(0, 0, 0))
		{
            public void mouseReleased(int mouseX, int mouseY)
            {
            	Minecraft.getMinecraft().displayGuiScreen(new GuiGunList());
            }
		});
        //this.drawItem(new ItemStack(lowerChestInventory.getResult()), i, j, "");
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
        if(lowerChestInventory.time > 0)
        {
        	GlStateManager.color(0, 1, 0);
        }
        this.drawTexturedModalRect(i + (4*18 + 9) - 4 * 18 - 9 + 97, j + 29 + 39, 97, 128, 71 * this.lowerChestInventory.time / this.lowerChestInventory.getTime(), 8);
        //this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
    
}