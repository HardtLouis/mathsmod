package com.mod.mathsmod.add.GUI.elements;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ThreeFloat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiColoredButton extends GuiButton
{
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/gui/colored_button.png");
	
	float red;
	float green;
	float blue;
	
	float red2;
	float green2;
	float blue2;

	public GuiColoredButton(int buttonId, int x, int y, String buttonText, ThreeFloat color, ThreeFloat overedColor)
    {
		super(buttonId, x, y, buttonText);
		red = color.x;
		green = color.y;
		blue = color.z;
		
		red2 = overedColor.x;
		green2 = overedColor.y;
		blue2 = overedColor.z;
    }
	
	public GuiColoredButton(int buttonId, int x, int y, String buttonText, ThreeFloat color)
    {
		super(buttonId, x, y, buttonText);
		red = color.x;
		green = color.y;
		blue = color.z;
		
		red2 = MathHelper.clamp(color.x - 30, 0, 255);
		green2 = MathHelper.clamp(color.y - 30, 0, 255);
		blue2 = MathHelper.clamp(color.z - 30, 0, 255);
    }
	
	public GuiColoredButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ThreeFloat color, ThreeFloat overedColor) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		red = color.x;
		green = color.y;
		blue = color.z;
		
		red2 = overedColor.x;
		green2 = overedColor.y;
		blue2 = overedColor.z;
	}
	
	public GuiColoredButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ThreeFloat color) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		red = color.x;
		green = color.y;
		blue = color.z;
		
		red2 = MathHelper.clamp(color.x - 30, 0, 255);
		green2 = MathHelper.clamp(color.y - 30, 0, 255);
		blue2 = MathHelper.clamp(color.z - 30, 0, 255);
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            if(!this.hovered)
            {
            	GlStateManager.color(red, green, blue);
            }
            else
            {
            	GlStateManager.color(red2, green2, blue2);
            }
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }
    }
}
