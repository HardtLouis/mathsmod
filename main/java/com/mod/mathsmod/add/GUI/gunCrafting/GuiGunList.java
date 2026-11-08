package com.mod.mathsmod.add.GUI.gunCrafting;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemBaseBullet;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class GuiGunList extends GuiScreen
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/gun_craft.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    protected int guiLeft;
    protected int guiTop;
    private int page;
    
    private int pageIndex;

	private float currentScroll;
    
	public GuiGunList()
    {
		
    }
	
    public GuiGunList(int index)
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
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
    }
    
    private List<Item> CraftableGuns()
    {
    	List<Item> list = Lists.newArrayList();
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof IGunCraft && !(item instanceof ItemBaseBullet) && ((IGunCraft) item).displayRecipe())
    		{
    			list.add(item);
    		}
    	}
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof IGunCraft && item instanceof ItemBaseBullet && ((IGunCraft) item).displayRecipe())
    		{
    			list.add(item);
    		}
    	}
    	
    	return list;
    }

    
    /**
     * 
     *   Fuck this shit , I'm going to play hide and seek
     * 
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
    	int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        int gggg = this.CraftableGuns().size() > 10 ? 10 : this.CraftableGuns().size();
        
    	for(int i = 0; i < gggg; i += 2)
    	{
    		if(this.CraftableGuns().size() > i + pageIndex * 10)
    		{
    			int lol = MathHelper.clamp(i + pageIndex * 10, 0, this.CraftableGuns().size() - 1);
    			this.buttonList.add(new GuiGunCraftButton(i,5 + x, 8 + y + (int) ((this.currentScroll * 10 + i / 2) * 18), 18, 18, (Item) this.CraftableGuns().get(lol))
				{
					public void mouseReleased(int mouseX, int mouseY)
					{
						if(this.rendererItem instanceof IGunCraft)
						{
							if(((IGunCraft) this.rendererItem).Craft() != null)
							{
								GuiGunList.this.mc.displayGuiScreen(new GuiGunCraftingHelper(this.rendererItem, pageIndex));
							}
						}
					}
				});
    			if(lol + 1 < this.CraftableGuns().size())
    			{
    				this.buttonList.add(new GuiGunCraftButton(i + 1, 5 + x + 5 * 18, 8 + y + (int) ((this.currentScroll * 10 + i / 2) * 18), 18, 18, (Item) this.CraftableGuns().get(lol + 1))
					{
    					public void mouseReleased(int mouseX, int mouseY)
    					{
    						if(this.rendererItem instanceof IGunCraft)
    						{
    							if(((IGunCraft) this.rendererItem).Craft() != null)
    							{
    								GuiGunList.this.mc.displayGuiScreen(new GuiGunCraftingHelper(this.rendererItem, pageIndex));
    							}
    						}
    					}
					});
				}
    		}
    	}
    	
    	this.buttonList.add(new GuiButton(this.buttonList.size(), x + 2, y - 17, 18, 18, "<")
    			{
    				public void mouseReleased(int mouseX, int mouseY)
    				{
    					pageIndex = MathHelper.clamp(pageIndex - 1, 0, GuiGunList.this.CraftableGuns().size() / 10);
    					GuiGunList.this.buttonList.clear();
    				}
    			});
    	this.buttonList.add(new GuiButton(this.buttonList.size(), x + 155, y - 17, 18, 18, ">")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				pageIndex = MathHelper.clamp(pageIndex + 1, 0, GuiGunList.this.CraftableGuns().size() / 10);
				GuiGunList.this.buttonList.clear();
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
