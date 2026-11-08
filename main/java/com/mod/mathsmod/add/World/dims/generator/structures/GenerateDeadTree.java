package com.mod.mathsmod.add.World.dims.generator.structures;

import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class GenerateDeadTree extends WorldGenAbstractTree
{
    private static final Block DEFAULT_TRUNK = RegisterBlocks.BURNED_LOG;
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;
    /** The metadata value of the wood to use in tree generation. */
    private final Block metaWood;

    public GenerateDeadTree(boolean p_i2027_1_)
    {
        this(p_i2027_1_, 4, DEFAULT_TRUNK);
    }

    public GenerateDeadTree(boolean notify, int minTreeHeightIn, Block woodMeta)
    {
        super(notify);
        this.minTreeHeight = minTreeHeightIn;
        this.metaWood = woodMeta;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	if(worldIn.getBlockState(position.down()).isFullCube() && (worldIn.getBlockState(position.down()).getBlock() == Blocks.GRASS || worldIn.getBlockState(position.down()).getBlock() == RegisterBlocks.BLACK_STONE))
    	{
    		for(int i = 0 ; i < 10 + rand.nextInt(4); ++i)
    		{
    			int e = 0;
    			worldIn.setBlockState(position.up(i), metaWood.getStateFromMeta(0));
    			
    			if( i > 3 && rand.nextInt(4) == 0)
    			{
    				for(int j = 1; j < 3 + rand.nextInt(3) - (i/2); ++j)
    				{
    					if(!worldIn.getBlockState(position.up(i - 1).east(j)).isFullCube())
    					{
    						e = rand.nextInt(3) == 0 ? 1 : 0;
    						worldIn.setBlockState(position.up(i + e).east(j), metaWood.getStateFromMeta(4));
    					}
    				}
    			}
    			
    			if( i > 3 && rand.nextInt(4) == 0)
    			{
    				for(int j = 1; j < 3 + rand.nextInt(3) - (i/2); ++j)
    				{
    					if(!worldIn.getBlockState(position.up(i - 1).north(j)).isFullCube())
    					{
    						e = rand.nextInt(3) == 0 ? 1 : 0;
    						worldIn.setBlockState(position.up(i + e).north(j), metaWood.getStateFromMeta(8));
    					}
    				}
    			}
    			
    			if( i > 3 && rand.nextInt(4) == 0)
    			{
    				for(int j = -1; j > -3 - rand.nextInt(3) - (i/2); --j)
    				{
    					if(!worldIn.getBlockState(position.up(i - 1).east(j)).isFullCube())
    					{
    						e = rand.nextInt(3) == 0 ? 1 : 0;
    						worldIn.setBlockState(position.up(i + e).east(j), metaWood.getStateFromMeta(4));
    					}
    				}
    			}
    			
    			if( i > 3 && rand.nextInt(4) == 0)
    			{
    				for(int j = -1; j > -3 - rand.nextInt(3) - (i/2); --j)
    				{
    					if(!worldIn.getBlockState(position.up(i - 1).north(j)).isFullCube())
    					{
    						e = rand.nextInt(3) == 0 ? 1 : 0;
    						worldIn.setBlockState(position.up(i + e).north(j), metaWood.getStateFromMeta(8));
    					}
    				}
    			}
    			
    			e = 0;
    		}
    		return true;
    	}
		return false;
    }
        
}