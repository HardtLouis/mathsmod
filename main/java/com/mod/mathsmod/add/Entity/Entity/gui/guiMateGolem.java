package com.mod.mathsmod.add.Entity.Entity.gui;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerMateGolem;
import com.mod.mathsmod.add.Entity.Entity.container.easyInv.EasyInventoryEntity;
import com.mod.mathsmod.add.Entity.Entity.gui.mateComplements.guiCompetenceTree;
import com.mod.mathsmod.add.Entity.Entity.gui.mateComplements.guiStats;
import com.mod.mathsmod.add.GUI.elements.GuiColoredButton;
import com.mod.mathsmod.add.items.guns.ThreeFloat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class guiMateGolem extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/mate_golem/mate_golem_inventory.png");
    private final IInventory playerInvInventory;
    private final IInventory lowerChestInventory;
    private EntityMateGolem golem;
    /** window height is calculated with these values; the more rows, the heigher */

    public guiMateGolem(IInventory playerInv, EntityMateGolem container)
    {
        super(new ContainerMateGolem(playerInv, container, Minecraft.getMinecraft().player));
        this.playerInvInventory = playerInv;
        this.lowerChestInventory = container.getInv();
        this.allowUserInput = false;
        this.golem = container;
        this.ySize = 174;
        this.xSize = 178;
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
        //this.fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        //this.fontRenderer.drawString(this.playerInvInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(this.golem.getLevel() + "", 13, 53, new Color(0, 0, 0).getRGB());
        int a = this.golem.getProgress();
        this.fontRenderer.drawString(a + "", 13, 43, new Color(255, 0, 255).getRGB());
        int t = (int) (this.golem.getHealth() / this.golem.getMaxHealth() * 100);
        this.fontRenderer.drawString(t + "%", 143, 53, new Color(255, 0, 0).getRGB());
        
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        
        this.addButton(new GuiColoredButton(0, i, j - 15, 59, 15, "stats", new ThreeFloat(255, 0, 0),  new ThreeFloat(100, 0, 0))
        		{
        			public void mouseReleased(int mouseX, int mouseY)
        			{
        				Minecraft.getMinecraft().displayGuiScreen(new guiStats(golem));
        			}
        		});
        
        this.addButton(new GuiColoredButton(0, i + 58, j - 15, 59, 15, "tree", new ThreeFloat(0, 0, 255),  new ThreeFloat(0, 0, 0))
		{
        	@Override
			public void mouseReleased(int mouseX, int mouseY)
			{
        		Minecraft.getMinecraft().displayGuiScreen(new guiCompetenceTree(guiMateGolem.this.golem, guiMateGolem.this.golem.getEntityId()));
			}
		});
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
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
}