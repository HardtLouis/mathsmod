package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemShieldLeggings extends ItemStatMetalicArmor
{

	public ItemShieldLeggings() 
	{
		super(RegisterItems.SHIELD, 0, EntityEquipmentSlot.LEGS, 0.5, 1, 0, 0.974F, 1, 4, "shield_leggings");
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		player.fallDistance = 0;
		player.stepHeight = 1;
		/*if(player.onGround)
		{
			this.setUsed(itemStack, 0);
		}
		if(this.getUsed(itemStack) < 2)
		{
			boolean jumping = ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, player, 49);
			int jumpTicks = ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, player, 66);
			
			if(jumping && jumpTicks < 0)
			{
				player.motionY = 0.42F;
				this.setUsed(itemStack, this.getUsed(itemStack) + 1);
			}
			if(jumpTicks > 0)
			{
				ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, player, 20, 66);
			}
			else
			{
				player.fallDistance = 0;
			}
		}*/
	}
	
	/*public void setUsed(ItemStack stack, int jump) {

        if (!stack.hasTagCompound()) 
        {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("double_jump", jump);
        }
        if (!stack.getTagCompound().hasKey("double_jump")) 
        {
        	stack.getTagCompound().setInteger("double_jump", jump);
        }

        stack.getTagCompound().setInteger("double_jump", jump);

    }
    
    public static int getUsed(ItemStack stack) {

        if (!stack.hasTagCompound()) 
        {
            return 0;
        }

        if (!stack.getTagCompound().hasKey("double_jump")) 
        {
        	stack.getTagCompound().setInteger("double_jump", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("double_jump");
    }*/
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "you can step over a whole block");
    }
}
