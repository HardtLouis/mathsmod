package com.mod.mathsmod.add.contracts.gui;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.messages.MessageContract;
import com.mod.mathsmod.utils.ContractsUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class GuiContractButton extends GuiButton
{
	ItemStack stack;
	public String name;
	public ContractsBase contract;
	boolean rm;
	
	int decalX;
	int decalY;
	
	public GuiContractButton(int buttonId, int x, int y, String buttonText, ContractsBase contr) 
	{
		super(buttonId, x, y, 18, 18, "");
		stack = contr.recompense();
		name = buttonText;
		contract = contr;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY, int decalXIn, int decalYIn, float partialTicks, boolean resetMode)
    {
		
        if (this.visible)
        {
        	decalX = decalXIn;
        	decalY = decalYIn;
        	rm = resetMode;
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getRenderItem().renderItemIntoGUI(stack, this.x + decalX + 1, this.y + decalY + 1);
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.hovered = mouseX >= this.x + decalX && mouseY >= this.y + decalY && mouseX < this.x + decalX + this.width && mouseY < this.y + decalY + this.height;
            int i = this.getHoverState(this.hovered);
            
            if(ContractsUtils.alreadyDone(mc.player, contract))
            {
            	GlStateManager.color(0.0F, 0.0F, 1.0F);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                this.drawTexturedModalRect(this.x + decalX, this.y + decalY, 0, 46 + i * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + decalX + this.width / 2, this.y + decalY, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            }
            else if(contract.full(mc.player))
            {
            	float adv = contract.getAdvancment(mc.player);
            	
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.color(0.0F, 0.0F, 1.0F);
                
                this.drawTexturedModalRect(this.x + decalX, this.y + decalY, 0, 46 + i * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + decalX + this.width / 2, this.y + decalY, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
                GlStateManager.color(1.0F, 1.0F, 1.0F);
                GlStateManager.color(0.0F, 1.0F, 1.0F);
                
                this.drawTexturedModalRect(this.x + decalX, this.y + decalY, 0, 46 + i * 20, (int) (this.width / 2 * adv), (this.height));
                this.drawTexturedModalRect(this.x + decalX + this.width / 2, this.y + decalY, 200 - this.width / 2, 46 + i * 20, (int) (this.width / 2 * adv), (this.height));
                
            }
            else if(ContractsUtils.isUnlocked(mc.player, contract))
            {
            	GlStateManager.color(0.0F, 1.0F, 1.0F);
                
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                this.drawTexturedModalRect(this.x + decalX, this.y + decalY, 0, 46 + i * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + decalX + this.width / 2, this.y + decalY, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            }
            else
            {
            	GlStateManager.color(1.0F, 0.0F, 0.0F);
                
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                this.drawTexturedModalRect(this.x + decalX, this.y + decalY, 0, 46 + i * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.x + decalX + this.width / 2, this.y + decalY, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            }
            
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;
            
            this.enabled = ContractsUtils.isUnlocked(mc.player, contract);
            
            if(this.hovered)
            {
            }
            
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
            
        }
    }
	
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.x + decalX && mouseY >= this.y + decalY && mouseX < this.x + decalX + this.width && mouseY < this.y + decalY + this.height;
    }
	
	public void mouseReleased(int mouseX, int mouseY)
    {
		Minecraft mc = Minecraft.getMinecraft();
		if(rm)
		{
			contract.setProgress(mc.player, contract, 0);
			Mathsmod.network.sendToServer(new MessageContract(contract.id, rm));
		}
		else if(!contract.full(mc.player) && contract.getAdvancment(mc.player) != 1.0F)
		{
			mc.player.sendMessage(new TextComponentString("==============================="));
			mc.player.sendMessage(new TextComponentString(contract.description()));
			if(!contract.full(mc.player) && contract.getAdvancment(mc.player) != 1.0F)
			{
				mc.player.sendMessage(new TextComponentString("your advancment : " + contract.getAdv(mc.player, contract) + "/" + contract.maxProgress() + ""));
			}
			else
			{
				mc.player.sendMessage(new TextComponentString("complete"));
			}
			mc.player.sendMessage(new TextComponentString("==============================="));
		}
		else
		{
			contract.setProgress(mc.player, contract, -1);
			Mathsmod.network.sendToServer(new MessageContract(contract.id, rm));
		}
    }
}
