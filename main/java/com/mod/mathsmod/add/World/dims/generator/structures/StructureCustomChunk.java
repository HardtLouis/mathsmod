package com.mod.mathsmod.add.World.dims.generator.structures;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.utils.GenerationHelper;

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
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.BlockRotationProcessor;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class StructureCustomChunk
{
	private static final PlacementSettings SETTINGS = new PlacementSettings().setChunk(null).setIgnoreEntities(true).setIgnoreStructureBlock(false).setMirror(Mirror.NONE);
	private final int dim;
	private final ResourceLocation loc;
	private Random random = new Random();
	List<Template.BlockInfo> blocks;
	
	public StructureCustomChunk(ResourceLocation path, int dimension, World worldIn)
	{
		dim = dimension;
		loc = path;
	}
	
	public boolean generateInChunk(World worldIn, Random rand, BlockPos position, Chunk chunk, int rot) 
	{
		WorldServer worldserver = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
		MinecraftServer serv = worldIn.getMinecraftServer();
		TemplateManager tm = worldserver.getStructureTemplateManager();
		Template temp = tm.getTemplate(serv, loc);
		List<Template.BlockInfo> blocksIn = ObfuscationReflectionHelper.getPrivateValue(Template.class, tm.getTemplate(serv, loc), 0);
		
		if(blocks == null)
		{
			blocks = blocksIn;
			System.out.print(blocks.toString() + System.lineSeparator());
		}
		
		if(temp != null)
		{
			GenerationHelper.addBlocksToWorld(worldIn, position, temp.getSize(), chunk, blocks, null, SETTINGS, 2, rot);
			return true;
		}
		else
		{
			System.out.print("sa mer" + System.lineSeparator());
		}
		return false;
	}
}
