package com.mod.mathsmod.add.World.dims.generator.structures;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.World.dims.generator.structures.specialProcessor.ProcessorPuzzleRotation;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class StructureCustom extends WorldGenerator
{
	private static final PlacementSettings SETTINGS = new PlacementSettings().setChunk(null).setIgnoreEntities(true).setIgnoreStructureBlock(false).setMirror(Mirror.NONE);
	private final int dim;
	private final ResourceLocation loc;
	private Random random = new Random();
	
	public StructureCustom(ResourceLocation path, int dimension)
	{
		dim = dimension;
		loc = path;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) 
	{
		WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
		MinecraftServer serv = worldIn.getMinecraftServer();
		TemplateManager tm = worldserver.getStructureTemplateManager();
		Template temp = tm.getTemplate(serv, loc);
		
		if(temp != null)
		{
			IBlockState state = worldIn.getBlockState(position);
			temp.addBlocksToWorld(worldIn, position, new ProcessorPuzzleRotation(position, SETTINGS, Maps.newHashMap(), Maps.newHashMap(), dim), SETTINGS, 2);
			return true;
		}
		
		return false;
	}

	public boolean generateSpecial(World worldIn, Random rand, BlockPos position, String rotationMode) 
	{
		WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
		MinecraftServer serv = worldIn.getMinecraftServer();
		TemplateManager tm = worldserver.getStructureTemplateManager();
		Template temp = tm.get(serv, loc);

		HashMap<BlockPos, ResourceLocation> map = Maps.newHashMap();
		HashMap<BlockPos, String> mapRotation = Maps.newHashMap();
		
		int rotationIndex = NumsHelper.getIntFromString(rotationMode);
		if(rotationMode == "r")
		{
			rotationIndex = rand.nextInt(4);
		}
		
		if(temp != null)
		{
			IBlockState state = worldIn.getBlockState(position);
			temp.addBlocksToWorld(worldIn, position, new ProcessorPuzzleRotation(position, SETTINGS.setRotation(Rotation.values()[rotationIndex]), map, mapRotation, dim), SETTINGS.setRotation(Rotation.values()[rotationIndex]), 2);
		}
		else
		{
			Mathsmod.LOGGER.info("structure:" + loc.toString() + " not found");
			return false;
		}
        
        while(!map.isEmpty())
        {
        	BlockPos posK = (BlockPos) map.keySet().toArray()[0];
        	this.generateSpecial(worldIn, rand, posK, map.get(posK), rotationIndex, mapRotation.get(posK), 50);
        	map.remove(posK);
        }
		return true;
	}
	
	public boolean generateSpecial(World worldIn, Random rand, BlockPos position, ResourceLocation locIn, int rotationIndex, String newRotationMode, int controlRecursion) 
	{
		WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
		MinecraftServer serv = worldIn.getMinecraftServer();
		TemplateManager tm = worldserver.getStructureTemplateManager();
		Template temp = tm.get(serv, locIn);
		
		HashMap<BlockPos, ResourceLocation> map = Maps.newHashMap();
		HashMap<BlockPos, String> mapRotation = Maps.newHashMap();
		
		int rotationIndex2 = NumsHelper.getIntFromString(newRotationMode);
		if(newRotationMode == "r")
		{
			rotationIndex2 = rand.nextInt(4);
		}
		
		rotationIndex2 = (rotationIndex2 + rotationIndex) % 4;
		
		if(temp != null)
		{
			IBlockState state = worldIn.getBlockState(position);
			temp.addBlocksToWorld(worldIn, position, new ProcessorPuzzleRotation(position, SETTINGS.setRotation(Rotation.values()[rotationIndex2]), map, mapRotation, dim), SETTINGS.setRotation(Rotation.values()[rotationIndex2]), 2);
		}
		else
		{
			Mathsmod.LOGGER.info("structure:" + locIn.toString() + " not found");
			return false;
		}
		
		while(!map.isEmpty())
        {
        	BlockPos posK = (BlockPos) map.keySet().toArray()[0];
        	this.generateSpecial(worldIn, rand, posK, map.get(posK), rotationIndex2, mapRotation.get(posK), controlRecursion-1);
        	map.remove(posK);
        }
		return true;
	}

	public boolean generateRandomRotation(World worldIn, Random rand, BlockPos position) 
	{
		WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
		MinecraftServer serv = worldIn.getMinecraftServer();
		TemplateManager tm = worldserver.getStructureTemplateManager();
		Template temp = tm.getTemplate(serv, loc);
		
		if(temp != null)
		{
			IBlockState state = worldIn.getBlockState(position);
			temp.addBlocksToWorld(worldIn, position, new ProcessorPuzzleRotation(position, SETTINGS.setRotation(Rotation.values()[rand.nextInt(4)]), Maps.newHashMap(), Maps.newHashMap(), dim), SETTINGS.setRotation(Rotation.values()[rand.nextInt(4)]), 2);
			return true;
		}
		else
		{
			System.out.print("sa mer" + System.lineSeparator());
		}
		return false;
	}
}
