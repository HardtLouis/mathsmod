package com.mod.mathsmod.add.GUI.screen;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.container.gui.elements.guiButtonCaster;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;

public class guiForging extends GuiScreen
{
	
	protected int xSize = 178;
    protected int ySize = 78;
    protected int guiLeft;
    protected int guiTop;
    
    public int selected = -1;

    int Ydecal = 0;
    int type = 0;
    
    public guiForging()
    {
    }
	
	public void initGui()
    {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		if(this.mc != null)
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
    }
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Random rand = new Random();
        //GlStateManager.color(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/anvil.png"));
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
    }

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	this.buttonList.clear();
    	ScaledResolution scaled = new ScaledResolution(mc);
    	
    	boolean canOutputInfos = false;
    	
		GlStateManager.enableDepth();
		this.itemRender.renderItemAndEffectIntoGUI(this.mc.player, mc.player.getHeldItemOffhand(), 80, 14);
        this.itemRender.renderItemOverlayIntoGUI(this.fontRenderer, mc.player.getHeldItemOffhand(), 80, 14, mc.player.getHeldItemOffhand().getCount() + "");
        GlStateManager.disableDepth();
    	
    	guiButtonCaster button1 = new guiButtonCaster(0, scaled.getScaledWidth() / 2 - 90, 6-1 * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(RegisterItems.BASIC_BLADE), false, this.fontRenderer) {
    		public void mouseReleased(int mouseX, int mouseY)
    	    {
    			type = 0;
    			Ydecal = 0;
    	    }
    	};
    	guiButtonCaster button2 = new guiButtonCaster(1, scaled.getScaledWidth() / 2 - 72, 6-1 * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(RegisterItems.SHIELD_CHESTPLATE), false, this.fontRenderer)
    	{
    		public void mouseReleased(int mouseX, int mouseY)
    	    {
    			type = 1;
    			Ydecal = 0;
    	    }
    	};
    	guiButtonCaster button3 = new guiButtonCaster(2, scaled.getScaledWidth() / 2 - 54, 6-1 * 18 + scaled.getScaledHeight() / 2 - 45, this, new ItemStack(RegisterItems.METALIC_SHIELD), false, this.fontRenderer)
    	{
    		public void mouseReleased(int mouseX, int mouseY)
    	    {
    			type = 2;
    			Ydecal = 0;
    	    }
    	};
    	
    	Item item = mc.player.getHeldItemOffhand().getItem();
    	canOutputInfos = ((item == RegisterItems.ALLOY_INGOT || MeltingRegistry.getMeltingProperties().containsKey(item)));

    	this.buttonList.add(button1);
    	this.buttonList.add(button2);
    	this.buttonList.add(button3);
    	
    	for(int i = 0; i < listItem(type).size(); ++i)
    	{
    		Item recipe = listItem(type).get(i);
    		ItemStack stack = new ItemStack(recipe);
    		if(canOutputInfos)
    		{
    			if(mc.player.getHeldItemOffhand().getItem() == RegisterItems.ALLOY_INGOT)
				{
					alloy al = ItemAlloyIngot.getMoltenMaterial(mc.player.getHeldItemOffhand());
					ItemToolPart.setAlloy(stack, al.items[0], al.items[1]);
				}
				else
				{
					ItemToolPart.setAlloyFromString(stack, mc.player.getHeldItemOffhand().getItem().getRegistryName().toString());
				}
    		}
    		guiButtonCaster button = new guiButtonCaster(3 + i, scaled.getScaledWidth() / 2 + 90, i * 18 + scaled.getScaledHeight() / 2 - 45, this, stack, true, this.fontRenderer);
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
    			if(i == selected)
    			{
    				button.red = true;
    			}
    			button.visible = true;
    		}
    		
    		if(canOutputInfos && mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height)
    		{
    			this.renderToolTip(stack, -30, 50);
    		}
    		
        	this.buttonList.add(button);
    	}
    }
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	public void updateScreen()
    {
        super.updateScreen();
        
        if(this.mc != null && this.mc.player != null)
        {
            if (!this.mc.player.isEntityAlive() || this.mc.player.isDead)
            {
                this.mc.player.closeScreen();
            }
        }
    }
	

    
    public List<Item> listItem(int typeIn)
    {
    	List list = Lists.newArrayList();
    	for(Item item : MeltingRegistry.METALIC_ITEMS.keySet())
    	{
    		if(item instanceof ItemToolPart)
    		{
    			if(typeIn == 0)
    			{
        			list.add(item);
    			}
    		}
    		else if(item instanceof ItemArmor)
    		{
    			if(typeIn == 1)
    			{
        			list.add(item);
    			}
    		}
    		else if(typeIn == 2)
    		{
    			list.add(item);
    		}
    	}
    	return list;
    }
    
    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();

        if (i != 0)
        {
            if (i > 0)
            {
                i = 1;
            }

            if (i < 0)
            {
                i = -1;
            }
            
            int limit = listItem(type).size() * 18 + 18;
            this.Ydecal = MathHelper.clamp(this.Ydecal + i * 9, -limit, limit);
        }
    }
}