package com.mod.mathsmod.add.items.guns.renderer;

import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class GunSpecialRenderer extends TileEntityItemStackRenderer
{
	
	public static GunSpecialRenderer instance;
	
	public void renderByItem(ItemStack itemStackIn)
    {
        if(itemStackIn.getItem() instanceof IGoldenWeapon)
        {
        	TESRGuns.instance.renderSpecial(itemStackIn, (IGoldenWeapon) itemStackIn.getItem(), 0, 0, 0, 1.0F);
        	//Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(((IGoldenWeapon) itemStackIn.getItem()).model()), ItemCameraTransforms.TransformType.NONE);
        }
        else if(itemStackIn.getItem() instanceof IAnimatedGun)
        {
        	TESRFamasRendererSystem.instance.renderSpecial(itemStackIn, (IAnimatedGun) itemStackIn.getItem(), 1.0F);
        }
        else if(itemStackIn.getItem() instanceof IItemSpecialRenderer)
        {
        	((IItemSpecialRenderer) itemStackIn.getItem()).render(itemStackIn, Minecraft.getMinecraft().player, Minecraft.getMinecraft().getRenderPartialTicks());
        	
        }
    }
	
	static 
	{
		instance = new GunSpecialRenderer();
	}
}
