package com.mod.mathsmod.add.contracts.gui;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.contracts.ContractsBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiContracts extends GuiScreen
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/competence_tree.png");
	int decalX;
	int decalY;
	EntityPlayer player = Minecraft.getMinecraft().player;
	private boolean resetMode = false;
	
	public GuiContracts()
	{
		super();
	}
	
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
    {
		if (clickedMouseButton == 0)
        {
			int i = Mouse.getEventDX() / 2;
	        int j = -Mouse.getEventDY() / 2;
	        
	        int limit = 200;
	        
	        if(decalX > -limit && decalX < limit)
	        {
	        	decalX += i;
	        	
	        	if(decalX + i > limit)
	        	{
	        		decalX = limit - 1;
	        	}
	        	else if(decalX + i < -limit)
	        	{
	        		decalX = -limit + 1;
	        	}
	        }
	        
	        if(decalY > -limit && decalY < limit)
	        {
	        	decalY += j;
	        	if(decalY + j > limit)
	        	{
	        		decalY = limit - 1;
	        	}
	        	else if(decalY + j < -limit)
	        	{
	        		decalY = -limit + 1;
	        	}
	        }
		}
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		this.drawBackGround(mouseX, mouseY, partialTicks);
		
		//this.drawString(fontRenderer, "welcome to the contracts interface !", 1, 1, new Color(255, 0, 255).getRGB());
		
        for (int i = 0; i < this.buttonList.size(); ++i)
        {
        	if(this.buttonList.get(i) instanceof GuiContractButton)
        	{
        		((GuiContractButton)this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY, decalX, decalY, partialTicks, resetMode);
        		
        		if(this.buttonList.get(i).isMouseOver())
        		{
        			ContractsBase contract = ((GuiContractButton) this.buttonList.get(i)).contract;
        			List<String> list = Lists.newArrayList();
        			list.add(TextFormatting.RED + ((GuiContractButton) this.buttonList.get(i)).name);
        			list.add("=================");
        			for(String str : contract.description().split(":"))
        			{
        				list.add(str);
        			}
        			if(!contract.full(player))
        			{
            			list.add(contract.getAdv(mc.player, contract) + "/" + contract.maxProgress());
        			}
        			else
        			{
        				list.add("complete");
        			}
        			this.drawHoveringText(list, mouseX, mouseY);
        			list.add("=================");
        		}
        	}
        	else
        	{
        		((GuiButton)this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY, partialTicks);
        	}
        }

        for (int j = 0; j < this.labelList.size(); ++j)
        {
            ((GuiLabel)this.labelList.get(j)).drawLabel(this.mc, mouseX, mouseY);
        }
    }
	
	public void initGui()
    {
		int id = 0;
		
		this.addButton(new GuiButton(++id, 0, 0, "reset : false")
				{
					public void mouseReleased(int mouseX, int mouseY)
				    {
						GuiContracts.this.resetMode = !GuiContracts.this.resetMode;
						this.displayString = "reset : " + GuiContracts.this.resetMode;
				    }
				});
		
		for(ContractsBase contract : ContractsBase.REGISTRY)
		{
			this.addButton(new GuiContractButton(++id, this.width / 2 - 9 + contract.getPosInGui().getX(), this.height / 2 - 9 + contract.getPosInGui().getY(), contract.nameInGui(), contract));
		}
		
    }
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	public void drawBackGround(int mouseX, int mouseY, float partialTicks)
    {
		/*GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - 480) / 2;
        int j = (this.height - 254) / 2;
        this.drawModalRectWithCustomSizedTexture(i - 16 + decalX, j + decalY, 0, 0, 512, 256, 512, 256);*/
    }
	
	public void drawHoveringText(List<String> textLines, int x, int y)
    {
        drawHoveringText(textLines, x, y, fontRenderer);
    }

    protected void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font)
    {
        net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(textLines, x, y, width, height, -1, font);
        if (!textLines.isEmpty())
        {
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            int i = 0;

            for (String s : textLines)
            {
                int j = this.fontRenderer.getStringWidth(s);

                if (j > i)
                {
                    i = j;
                }
            }

            int l1 = x + 12;
            int i2 = y - 12;
            int k = 8;

            if (textLines.size() > 1)
            {
                k += 2 + (textLines.size() - 1) * 10;
            }

            if (l1 + i > this.width)
            {
                l1 -= 28 + i;
            }

            if (i2 + k + 6 > this.height)
            {
                i2 = this.height - k - 6;
            }

            this.zLevel = 300.0F;
            this.itemRender.zLevel = 300.0F;
            int l = -267386864;
            //this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
            //this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
            //this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
            //this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
            //this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
            int i1 = 1347420415;
            int j1 = 1344798847;
            /*this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
            this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
            this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);*/

            for (int k1 = 0; k1 < textLines.size(); ++k1)
            {
                String s1 = textLines.get(k1);
                this.fontRenderer.drawStringWithShadow(s1, (float)l1, (float)i2, -1);

                if (k1 == 0)
                {
                    i2 += 2;
                }

                i2 += 10;
            }

            this.zLevel = 0.0F;
            this.itemRender.zLevel = 0.0F;
            GlStateManager.enableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }
}