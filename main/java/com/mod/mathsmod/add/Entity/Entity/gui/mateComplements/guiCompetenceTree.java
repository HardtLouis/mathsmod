package com.mod.mathsmod.add.Entity.Entity.gui.mateComplements;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;
import com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree.Competence;
import com.mod.mathsmod.add.GUI.elements.GuiColoredButton;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ThreeFloat;
import com.mod.mathsmod.add.messages.EntityMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class guiCompetenceTree extends GuiScreen
{
	
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/mate_golem/competence_tree.png");
	
	protected int xSize = 178;
    protected int ySize = 78;
    public Container inventorySlots;
    protected int guiLeft;
    protected int guiTop;
    private EntityMateGolem golem;
    
    private Competence competence;

	private int pageIndex;
	
	private int entityID;
	
	private int neededPoints;
    
    public guiCompetenceTree(EntityMateGolem entity, int id)
    {
    	golem = entity;
    	entityID = id;
    	
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
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 158);
    }

	private void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        
        int gggg = this.nextCompetences(competence).size() > 5 ? 5 : this.nextCompetences(competence).size();
        
        ThreeFloat red = new ThreeFloat(255, 0, 0);
        ThreeFloat green = new ThreeFloat(150, 255, 150);
        ThreeFloat blue = new ThreeFloat(150, 150, 255);
        
    	for(int i = 0; i < gggg; i++)
    	{
    		if(this.nextCompetences(competence).size() > i + pageIndex * 5)
    		{
    			int lol = MathHelper.clamp(i + pageIndex * 5, 0, this.nextCompetences(competence).size() - 1);
    			
    			ThreeFloat color = null;
    			Competence compo = nextCompetences(competence).get(lol);
    			if(this.golem.canHaveCompetence(compo))
    			{
    				color = green;
    			}
    			if(this.golem.getCompetences().contains(compo))
    			{
    				color = blue;
    			}
    			else
    			{
    				color = red;
    			}
    			
    			this.buttonList.add(new GuiColoredButton(i,5 + x, 8 + y + i * 18, 18 * 5, 18, nextCompetences(competence).get(lol).name, color)
				{
					public void mouseReleased(int mouseX, int mouseY)
					{
						if(guiCompetenceTree.this.competence != null)
						{
							if(guiCompetenceTree.this.nextCompetences(guiCompetenceTree.this.competence).size() > lol)
							{
								guiCompetenceTree.this.competence = guiCompetenceTree.this.nextCompetences(guiCompetenceTree.this.competence).get(lol);
							}
						}
						else
						{
							guiCompetenceTree.this.competence = guiCompetenceTree.this.nextCompetences(null).get(lol);
						}
						Mathsmod.network.sendToServer(new EntityMessage(guiCompetenceTree.this.golem.getUniqueID().toString(), Competence.getIntIDFromStringID(guiCompetenceTree.this.competence.ID)));
						guiCompetenceTree.this.buttonList.clear();
					}
					
					public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
				    {
						super.drawButton(mc, mouseX, mouseY, partialTicks);
						if(this.hovered)
						{
							guiCompetenceTree.this.neededPoints = nextCompetences(competence).get(lol).points;
						}
				    }
				});
    		}
    	}
    	
    	this.buttonList.add(new GuiButton(this.buttonList.size(), x + 2, y - 17, 18, 18, "<")
    			{
    				public void mouseReleased(int mouseX, int mouseY)
    				{
    					pageIndex = MathHelper.clamp(pageIndex - 1, 0, guiCompetenceTree.this.nextCompetences(competence).size() / 5);
    					guiCompetenceTree.this.buttonList.clear();
    				}
    			});
    	this.buttonList.add(new GuiButton(this.buttonList.size(), x + 155, y - 17, 18, 18, ">")
		{
			public void mouseReleased(int mouseX, int mouseY)
			{
				pageIndex = MathHelper.clamp(pageIndex + 1, 0, guiCompetenceTree.this.nextCompetences(competence).size() / 5);
				guiCompetenceTree.this.buttonList.clear();
			}
		});
    	if(this.nextCompetences(competence).size() < 1)
    	{
    		competence = null;
    	}
    	
    	this.drawString(fontRenderer, "Points : " + this.golem.getPoints(), 16, 112, new Color(255, 255, 0).getRGB());
    	this.drawString(fontRenderer, "Needed Points : " + this.neededPoints, 83, 112, new Color(255, 255, 0).getRGB());
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
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
        {
            this.mc.player.closeScreen();
        }
    }
	
	private List<Competence> nextCompetences(Competence comp)
    {
    	List<Competence> list = Lists.newArrayList();
    	if(comp != null)
    	{
    		for(Competence competence : Competence.COMPETENCES)
        	{
        		if(competence.competenceToHave == comp)
        		{
        			list.add(competence);
        		}
        	}
    	}
    	else
    	{
    		for(Competence competence : Competence.COMPETENCES)
        	{
        		if(competence.competenceToHave == null)
        		{
        			list.add(competence);
        		}
        	}
    	}
    	return list;
    }
	
}