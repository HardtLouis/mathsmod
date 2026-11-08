package com.mod.mathsmod.add.blocks.container.gui;

import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.container.ContainerCaster;
import com.mod.mathsmod.add.blocks.container.gui.elements.guiButtonCaster;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityCaster;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class guiCaster extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/caster.png");
    private final IInventory playerInvInventory;
    public final TileEntityCaster lowerChestInventory;
    /** window height is calculated with these values; the more rows, the heigher */
    private final int inventoryRows;

    public guiCaster(InventoryPlayer playerInv, TileEntityCaster container)
    {
        super(new ContainerCaster(playerInv, container));
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
    	this.buttonList.clear();
    	ScaledResolution scaled = new ScaledResolution(mc);
    	
    	/*guiButtonCaster button1 = new guiButtonCaster(0, scaled.getScaledWidth() / 2 - 90, -2 * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(RegisterItems.BASIC_BLADE), this.lowerChestInventory.getPos()
    			) {
    		public void mouseReleased(int mouseX, int mouseY)
    	    {
    			type = 0;
    			Ydecal = 0;
    	    }
    	};
    	guiButtonCaster button2 = new guiButtonCaster(1, scaled.getScaledWidth() / 2 - 72, -2 * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(RegisterItems.SHIELD_CHESTPLATE), this.lowerChestInventory.getPos())
    	{
    		public void mouseReleased(int mouseX, int mouseY)
    	    {
    			type = 1;
    			Ydecal = 0;
    	    }
    	};
    	guiButtonCaster button3 = new guiButtonCaster(2, scaled.getScaledWidth() / 2 - 54, -2 * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(RegisterItems.METALIC_SHIELD), this.lowerChestInventory.getPos())
    	{
    		public void mouseReleased(int mouseX, int mouseY)
    	    {
    			type = 2;
    			Ydecal = 0;
    	    }
    	};

    	this.buttonList.add(button1);
    	this.buttonList.add(button2);
    	this.buttonList.add(button3);
    	
    	for(int i = 0; i < listItem(type).size(); ++i)
    	{
    		Item recipe = listItem(type).get(i);
    		guiButtonCaster button = new guiButtonCaster(3 + i, scaled.getScaledWidth() / 2 + 90, i * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(recipe), this.lowerChestInventory.getPos());
    		button.setDecal(0, Ydecal);
    		
    		if(button.decalY + button.y < scaled.getScaledHeight() / 2 - 45)
    		{
    			button.visible = false;
    		}
    		else if(button.decalY + button.y > scaled.getScaledHeight() / 2 + 130)
    		{
    			button.visible = false;
    		}
    		else
    		{
    			if(i == this.lowerChestInventory.getField(0))
    			{
    				button.red = true;
    			}
    			button.visible = true;
    		}
    		
        	this.buttonList.add(button);
    	}*/
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
        
        if(this.lowerChestInventory.progress > 0)
        {
            GlStateManager.color(1, (float)(this.lowerChestInventory.progress) / 600, (float)(this.lowerChestInventory.progress) / 600);
        }
        this.drawTexturedModalRect(i + (4*18 + 9) - 4 * 18 - 9 + 79, j + 29 + 17, 176, 0, (int)(16 * (float)(this.lowerChestInventory.progress) / 600), 16);
    }
    
}