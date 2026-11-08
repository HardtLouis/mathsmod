package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemChainsaw extends ItemAxe
{

	public ItemChainsaw() 
	{
		super(RegisterItems.KURAYUM, 4, -2.0F);
		this.setRegistryName("chainsaw").setUnlocalizedName("chainsaw");
		
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return true;
    }
	
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
            this.destroyAdjLog(stack, worldIn, state, pos);
        }

        return true;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.YELLOW + "cut a whole trunk instead of one log");
	}
	
	private boolean isWood(IBlockState state)
	{
		
		ItemStack stack = new ItemStack(state.getBlock());
		
		if (stack.isEmpty())
		{
			return false;
		}
		
		for (int id : OreDictionary.getOreIDs(stack))
		{
			String name = OreDictionary.getOreName(id);
			if (name != null && name.toLowerCase().startsWith("log"))
			{
				return true;
			}
		}

		if (state.getBlock().getRegistryName().getResourcePath().endsWith("_log")) 
		{
			return true;
		}
		
		return false;
	}
	
	public void destroyAdjLog(ItemStack stack, World worldIn, IBlockState state, BlockPos pos)
	{
		if (!worldIn.isRemote && (this.isWood(state)))
        {
			for(EnumFacing face : EnumFacing.VALUES)
			{
				worldIn.destroyBlock(pos, true);
				this.destroyAdjLog(stack, worldIn, worldIn.getBlockState(pos.offset(face)), pos.offset(face));
			}
        }
	}
}
