package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockElevator extends Block
{

	public BlockElevator() 
	{
		super(Material.IRON);
		this.setHardness(1);
		this.setResistance(1F);
		this.setUnlocalizedName("elevator").setRegistryName("elevator");
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "jump to teleport to an upward elevator block");
		tooltip.add(TextFormatting.YELLOW + "jump to teleport to a downward elevator block");
    }

}
