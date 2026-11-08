package com.mod.mathsmod.add.World.dims.generator.structures;

import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenSirojusCaves extends MapGenCaves
{
	
	protected static final IBlockState BLK_FR_WATER = RegisterBlocks.FROZEN_WATER.getDefaultState();

	protected boolean canReplaceBlock(IBlockState p_175793_1_, IBlockState p_175793_2_)
    {
        if (p_175793_1_.getBlock() == Blocks.STONE)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.DIRT)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == RegisterBlocks.FROZEN_STONE)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == RegisterBlocks.PERMICE_STONE)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.GRASS)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.HARDENED_CLAY)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.STAINED_HARDENED_CLAY)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.SANDSTONE)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.RED_SANDSTONE)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.MYCELIUM)
        {
            return true;
        }
        else if (p_175793_1_.getBlock() == Blocks.SNOW_LAYER)
        {
            return true;
        }
        else
        {
            return (p_175793_1_.getBlock() == Blocks.SAND || p_175793_1_.getBlock() == Blocks.GRAVEL) && p_175793_2_.getMaterial() != Material.WATER;
        }
    }
	
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up)
    {
        net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
        {
            if (y - 1 < 10)
            {
                data.setBlockState(x, y, z, BLK_FR_WATER);
            }
            else
            {
                data.setBlockState(x, y, z, BLK_AIR);

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
                {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }
}
