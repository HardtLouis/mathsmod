package com.mod.mathsmod.add.World.dims.generator.structures.specialProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureCustom;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityMobGenerator;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.BlockRotationProcessor;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ProcessorPuzzleRotation extends BlockRotationProcessor
{
    int dim;
    HashMap<BlockPos, ResourceLocation> list;
    HashMap<BlockPos, String> mapRotation;

    public ProcessorPuzzleRotation(BlockPos pos, PlacementSettings settings, HashMap<BlockPos, ResourceLocation> listIn, HashMap<BlockPos, String> list2In, int dimIn)
    {
    	super(pos, settings);
        dim = dimIn;
        list = listIn;
        mapRotation = list2In;
    }

    @Nullable
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn)
    {
    	if(blockInfoIn.blockState.getBlock() == RegisterBlocks.MOB_GENERATOR)
    	{
    		if(blockInfoIn.tileentityData != null)
    		{
    			TileEntityMobGenerator.generateEntities(pos, worldIn, blockInfoIn.tileentityData, worldIn.rand);
    			
    			Template.BlockInfo info = new Template.BlockInfo(pos, Blocks.AIR.getDefaultState(), null);
    			return info;
    		}
    	}
    	
    	if(blockInfoIn.blockState.getBlock() == RegisterBlocks.BLOCK_RSP)
    	{
    		if(blockInfoIn.tileentityData != null)
    		{
    			List<String> data = Lists.newArrayList();

    	        int size = blockInfoIn.tileentityData.getInteger("data_size");
    	        for(int i = 0; i < size; i++)
    	        {
    	        	if(blockInfoIn.tileentityData.getString("structure_name_" + i) != null && !blockInfoIn.tileentityData.getString("structure_name_" + i).isEmpty())
    	        	{
    	        		data.add(blockInfoIn.tileentityData.getString("structure_name_" + i));
    	        	}
    	        }
    	        
    	        Random rand = worldIn.rand;
    	        if(data.size() > 0)
    	        {
    	        	String rec = data.get(rand.nextInt(data.size()));
    	        	String[] str1 = rec.split(":");
    	        	ResourceLocation loc = null;
    	        	String mode = "r";
    	        	
    	        	if(str1.length > 1)
    	        	{
    	        		String[] pathNP = str1[1].split("//");
    	        		if(pathNP.length > 1)
    	        		{
    	        			loc = new ResourceLocation(str1[0], pathNP[0]);
    	        			mode = pathNP[1];
    	        		}
    	        		else
    	        		{
    	        			loc = new ResourceLocation(str1[0], pathNP[0]);
    	        		}
    	        	}
    	        	else if(str1.length == 1)
    	        	{
    	        		String[] pathNP = str1[0].split("//");
    	        		if(pathNP.length > 1)
    	        		{
    	        			loc = new ResourceLocation(pathNP[0]);
    	        			mode = pathNP[1];
    	        		}
    	        		else
    	        		{
    	        			loc = new ResourceLocation(pathNP[0]);
    	        		}
    	        	}
    	        	 
    	        	mapRotation.put(pos, mode);
    	        	list.put(pos, loc);
        	        
        	        return null;
    	        }
    		}
    	}
        return super.processBlock(worldIn, pos, blockInfoIn);
    }
}