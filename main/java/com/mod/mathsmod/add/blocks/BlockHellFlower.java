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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHellFlower extends BlockBush
{
	public BlockHellFlower()
	{
		super();
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName("hell_flower");
		this.setTickRandomly(true);
		this.setUnlocalizedName("hell_flower");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND && rand.nextInt(10) == 0)
        {
        	for(int i = -1; i <= 1; ++i)
        	{
        		for(int j = -1; j <= 1; ++j)
            	{
        			if(i != j)
        			{
        				if(worldIn.getBlockState(pos.add(i, 0, j).down()).getBlock() == Blocks.FARMLAND && worldIn.getBlockState(pos.add(i, 0, j)).getMaterial() == Material.AIR)
                		{
                			worldIn.setBlockState(pos.add(i, 0, j), RegisterBlocks.HELL_FLOWER.getDefaultState());
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
			if(new Random().nextInt(40) == 0)
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Item.getByNameOrId("mathsmod:silver_ingot"), 1));
			}
			
			else if(new Random().nextInt(20) == 0)
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Item.getByNameOrId("mathsmod:tfosium_ingot"), 1));
			}
			else
			{
				Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.QUARTZ, 1));
			}
			
		}
    }
	
    /**
     * Return true if the block can sustain a Bush
     */
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock() == Blocks.QUARTZ_ORE || state.getBlock() == Blocks.NETHERRACK;
    }
}