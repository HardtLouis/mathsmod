package com.mod.mathsmod.utils;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class GenerationHelper 
{
	public static void addBlocksToWorld(World worldIn, BlockPos position, BlockPos size, Chunk chunk, List<Template.BlockInfo> blocks, @Nullable ITemplateProcessor templateProcessor, PlacementSettings placementIn, int flags)
    {
        if (!blocks.isEmpty() && size.getX() >= 1 && size.getY() >= 1 && size.getZ() >= 1)
        {
            Block block = placementIn.getReplacedBlock();
            StructureBoundingBox structureboundingbox = placementIn.getBoundingBox();

            for (Template.BlockInfo template$blockinfo : blocks)
            {
                BlockPos blockpos = template$blockinfo.pos.add(position);

                if (template$blockinfo != null)
                {
                    Block block1 = template$blockinfo.blockState.getBlock();

                    IBlockState iblockstate = template$blockinfo.blockState;
                    chunk.setBlockState(blockpos, iblockstate);
                    
                    if (template$blockinfo.tileentityData != null)
                    {
                        TileEntity tileentity2 = worldIn.getTileEntity(blockpos);

                        if (tileentity2 != null)
                        {
                            template$blockinfo.tileentityData.setInteger("x", blockpos.getX());
                            template$blockinfo.tileentityData.setInteger("y", blockpos.getY());
                            template$blockinfo.tileentityData.setInteger("z", blockpos.getZ());
                            tileentity2.readFromNBT(template$blockinfo.tileentityData);
                        }
                    }
                }
            }
        }
    }
	
	public static void addBlocksToWorld(World worldIn, BlockPos position, BlockPos size, Chunk chunk, List<Template.BlockInfo> blocks, @Nullable ITemplateProcessor templateProcessor, PlacementSettings placementIn, int flags, int rot)
    {
        if (!blocks.isEmpty() && size.getX() >= 1 && size.getY() >= 1 && size.getZ() >= 1)
        {
            Block block = placementIn.getReplacedBlock();
            StructureBoundingBox structureboundingbox = placementIn.getBoundingBox();

            for (Template.BlockInfo template$blockinfo : blocks)
            {
                BlockPos blockpos = template$blockinfo.pos;

                if (template$blockinfo != null)
                {
                    Block block1 = template$blockinfo.blockState.getBlock();
                    BlockPos pos = blockpos;

                    IBlockState iblockstate = template$blockinfo.blockState;
                    if(rot % 4 == 1)
                	{
                    	pos = new BlockPos(15 - blockpos.getZ(), blockpos.getY(), blockpos.getX());
                	}
                	else if(rot % 4 == 2)
                	{
                		pos = new BlockPos(15 - blockpos.getX(), blockpos.getY(), 15 - blockpos.getZ());
                	}
                	else if(rot % 4 == 2)
                	{
                		pos = new BlockPos(blockpos.getZ(), blockpos.getY(), 15 - blockpos.getX());
                	}
                    
                    pos = pos.add(position);

            		chunk.setBlockState(pos, iblockstate);
                    
                    if (template$blockinfo.tileentityData != null)
                    {
                        TileEntity tileentity2 = worldIn.getTileEntity(pos);

                        if (tileentity2 != null)
                        {
                            template$blockinfo.tileentityData.setInteger("x", blockpos.getX());
                            template$blockinfo.tileentityData.setInteger("y", blockpos.getY());
                            template$blockinfo.tileentityData.setInteger("z", blockpos.getZ());
                            tileentity2.readFromNBT(template$blockinfo.tileentityData);
                        }
                    }
                }
            }
        }
    }

	public static BlockPos rotateBlockPos(BlockPos pos, int rot)
	{
		BlockPos offset = pos;
		if(rot == 1)
		{
			offset = new BlockPos(-pos.getZ(), pos.getY(), pos.getX());
		}
		else if(rot == 2)
		{
			offset = new BlockPos(-pos.getX(), pos.getY(), -pos.getZ());
		}
		else if(rot == 3)
		{
			offset = new BlockPos(pos.getZ(), pos.getY(), -pos.getX());
		}
		
		return offset;
	}
}
