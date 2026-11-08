package com.mod.mathsmod.add.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDiamonaFlower extends BlockBush
{
	public BlockDiamonaFlower()
	{
		super();
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName("diamona");
		this.setTickRandomly(true);
		this.setUnlocalizedName("diamona");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND && rand.nextInt(5) == 0)
        {
        	for(int i = -1; i <= 1; ++i)
        	{
        		for(int j = -1; j <= 1; ++j)
            	{
        			if(i != j)
        			{
        				if(worldIn.getBlockState(pos.add(i, 0, j).down()).getBlock() == Blocks.FARMLAND && worldIn.getBlockState(pos.add(i, 0, j)).getMaterial() == Material.AIR)
                		{
                			worldIn.setBlockState(pos.add(i, 0, j), RegisterBlocks.DIAMONA.getDefaultState());
                		}
        			}
            	}
        	}
        }
    }
	
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
		if(player.getHeldItemMainhand().getItem() instanceof ItemShears)
		{
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
		else
		{
			if(new Random().nextInt(25) == 0)
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.DIAMOND, 1));
			}
			else if(new Random().nextInt(10) == 0)
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.GOLD_INGOT, 1));
			}
			else if(new Random().nextInt(10) == 0)
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.IRON_INGOT, 1));
			}
			else
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.COAL, 1 + new Random().nextInt(3)));
			}
			
		}
    }
}