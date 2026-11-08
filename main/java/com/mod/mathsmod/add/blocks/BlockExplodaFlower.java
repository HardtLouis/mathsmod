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
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockExplodaFlower extends BlockBush
{
	public BlockExplodaFlower()
	{
		super();
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName("exploda");
		this.setTickRandomly(true);
		this.setUnlocalizedName("exploda");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND)
        {
        	for(int i = -1; i <= 1; ++i)
        	{
        		for(int j = -1; j <= 1; ++j)
            	{
        			if(i != j)
        			{
        				if(worldIn.getBlockState(pos.add(i, 0, j).down()).getBlock() == Blocks.FARMLAND && worldIn.getBlockState(pos.add(i, 0, j)).getMaterial() == Material.AIR)
                		{
                			worldIn.setBlockState(pos.add(i, 0, j), RegisterBlocks.EXPLODA.getDefaultState());
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
			Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.GUNPOWDER, 1 + new Random().nextInt(3)));
		}
    }
}
