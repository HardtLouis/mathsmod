package com.mod.mathsmod.add.blocks.stateMapper;

import com.mod.mathsmod.add.blocks.BlockUnbreakableCopy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class CopyStateMapper extends StateMapperBase
{
	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) 
	{
		Block block = state.getBlock();
		
		if (state.getBlock() instanceof BlockUnbreakableCopy) 
		{
			block = ((BlockUnbreakableCopy) block).copiedBlock();
		}
		
		return new ModelResourceLocation(Block.REGISTRY.getNameForObject(block), this.getPropertyString(state.getProperties()));
	}
}