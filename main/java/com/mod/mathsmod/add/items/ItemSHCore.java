package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityMythicalItem;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSHCore extends ItemM implements ICustomColor
{

	public ItemSHCore() 
	{
		super("super_hunter_core");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "improve a gun when placed in a crafting table with it");
    }

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		float t = ((float)(Minecraft.getMinecraft().getSystemTime()) / 1000.0F) * 20.0F;
		return RenderLaser.getColorFromAngle(t);
	}
	
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
	@Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
		EntityMythicalItem entity = new EntityMythicalItem(world, location.posX, location.posY, location.posZ, itemstack);
    	entity.motionX = location.motionX;
    	entity.motionY = location.motionY;
    	entity.motionZ = location.motionZ;
    	entity.setPickupDelay(10);
        return entity;
    }
}
