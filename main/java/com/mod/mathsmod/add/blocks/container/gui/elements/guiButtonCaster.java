package com.mod.mathsmod.add.blocks.container.gui.elements;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.GUI.screen.guiForging;
import com.mod.mathsmod.add.blocks.container.gui.guiCaster;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityCaster;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;
import com.mod.mathsmod.add.messages.MessageCasterChangeMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class guiButtonCaster extends GuiButton
{
	ItemStack stack;
	String name;
	
	int decalX;
	public int decalY;
	private guiForging gui;
	public boolean red = false;
	public boolean displayText;
	FontRenderer fr;
	
	public guiButtonCaster(int buttonId, int x, int y, guiForging guiIn, ItemStack stackIn, boolean displayCost, FontRenderer fontRenderer) 
	{
		super(buttonId, x, y, 18, 18, "");
		stack = stackIn;
		gui = guiIn;
		this.fr = fontRenderer;
		this.displayText = displayCost;
	}
	
	public void setDecal(int decX, int decY)
	{
		decalX = decX;
		decalY = decY;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            if(red)
            {
            	GlStateManager.color(1.0F, 0.0F, 0.0F);
            }
            
            this.hovered = mouseX >= this.x + decalX && mouseY >= this.y + decalY && mouseX < this.x + decalX + this.width && mouseY < this.y + decalY + this.height;
            int i = this.getHoverState(this.hovered);
            
            
            this.drawTexturedModalRect(this.x + decalX, this.y + decalY, 0, 46 + i * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + decalX + this.width / 2, this.y + decalY, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            GlStateManager.enableDepth();
            mc.getRenderItem().renderItemIntoGUI(stack, this.x + decalX + 1, this.y + decalY + 1);
            if(displayText && MeltingRegistry.METALIC_ITEMS != null && MeltingRegistry.METALIC_ITEMS.containsKey(stack.getItem()))
            {
                GlStateManager.disableDepth();
                GlStateManager.disableBlend();
                fr.drawStringWithShadow("cost : " + MeltingRegistry.METALIC_ITEMS.get(stack.getItem()), (float)(this.x + decalX + 19 - 4), (float)(this.y + decalY + 6 + 3), 16777215);
                GlStateManager.enableDepth();
                // Fixes opaque cooldown overlay a bit lower
                // TODO: check if enabled blending still screws things up down the line.
                GlStateManager.enableBlend();

            }
            GlStateManager.disableDepth();
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;
            
            
            if(this.hovered)
            {
            }
            
            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else if (!this.enabled)
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
		Minecraft.getMinecraft().displayGuiScreen(null);
		
		EntityPlayer player = Minecraft.getMinecraft().player;
		Item item = player.getHeldItemOffhand().getItem();
		
		if(MeltingRegistry.METALIC_ITEMS.containsKey(stack.getItem()) && (item == RegisterItems.ALLOY_INGOT || MeltingRegistry.getMeltingProperties().containsKey(item)))
		{
			int price = MeltingRegistry.METALIC_ITEMS.get(stack.getItem());
			
			if(player.getHeldItemOffhand().getCount() >= price)
			{
				Mathsmod.network.sendToServer(new MessageCasterChangeMod(stack.getItem().getRegistryName().toString()));
				player.world.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F, false);
			}
			else
			{
				player.sendStatusMessage(new TextComponentString("you need more ingots on your left hand to make this item"), true);
			}
		}
		else
		{
			player.sendStatusMessage(new TextComponentString("you need an ingot on your left hand to craft this"), true);
		}
    }
}