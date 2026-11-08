package com.mod.mathsmod.add.GUI.gunCrafting;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiGunCraftingHelper extends GuiScreen
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/gun_recipe.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    private Item item;
    protected int guiLeft;
    protected int guiTop;
    
    private int pageIndex;

	private float currentScroll;
    
    public GuiGunCraftingHelper(Item gun, int index)
    {
    	pageIndex = index;
    	item = gun;
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
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        RenderItem render = this.mc.getRenderItem();
        if(item instanceof IGunCraft)
        {
        	render.renderItemIntoGUI(new ItemStack(item, ((IGunCraft) item).size()), 134, 15);
        	render.renderItemOverlays(this.fontRenderer, new ItemStack(item, ((IGunCraft) item).size()), 134, 15);
        	for(int i = 0; i < 4; ++i)
            {
            	for(int j = 0; j < 2; ++j)
                {
                	if(((IGunCraft) item).Craft().size() > i + j)
                	{
                		render.renderItemIntoGUI(((IGunCraft) item).Craft().get(i + j * 4), 8 + i * 18, 6 + j * 18);
                		render.renderItemOverlays(this.fontRenderer, ((IGunCraft) item).Craft().get(i + j * 4), 8 + i * 18, 6 + j * 18);
                	}
                }
            }
        }
        
        this.buttonList.add(new GuiButton(this.buttonList.size(), x + 2, y - 17, 18, 18, "<-")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				mc.displayGuiScreen(new GuiGunList(pageIndex));
			}
		});
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

}
