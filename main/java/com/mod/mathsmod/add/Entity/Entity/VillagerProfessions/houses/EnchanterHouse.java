package com.mod.mathsmod.add.Entity.Entity.VillagerProfessions.houses;

import java.util.List;
import java.util.Random;

import com.mod.mathsmod.add.EntitiesInit;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class EnchanterHouse extends StructureVillagePieces.Village
{
	public EnchanterHouse()
    {
    }
	
	public EnchanterHouse(StructureVillagePieces.Start start, int type, Random rand, StructureBoundingBox p_i45564_4_, EnumFacing facing)
    {
        super(start, type);
        this.setCoordBaseMode(facing);
        this.boundingBox = p_i45564_4_;
    }
	
	protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
    {
        return VillagerRegistry.getId(EntitiesInit.ENCHANTER);
    }

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{
		if (this.averageGroundLvl < 0)
        {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
        }
		this.generateHouse(worldIn, structureBoundingBoxIn);
		return true;
	}
	
	public static EnchanterHouse createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175854_1_, Random rand, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing facing, int p_175854_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 8, 8, 8, facing);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new EnchanterHouse(start, p_175854_7_, rand, structureboundingbox, facing) : null;
    }
	
	private void generateHouse(World world, StructureBoundingBox structureBoundingBoxIn)
	{
		
		
		for(int x = 0; x <= 7; ++x)
		{
			for(int y = 0; y <= 5; ++y)
			{
				for(int z = 0; z <= 7; ++z)
				{
					if(x == 0 || x == 7 || y == 0 || y == 5 || z == 0 || z == 7)
					{
						this.setBlockState(world, Blocks.STONEBRICK.getDefaultState(), x, y, z + 1, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.SPRUCE_DOOR.getDefaultState(), 2, 1, 0 + 1, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.SPRUCE_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2, 2, 0 + 1, structureBoundingBoxIn);
					}
					if(y == 0 && (x == 0 || x == 7 || z == 0 || z == 7))
					{
						this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), x, 6, z + 1, structureBoundingBoxIn);
					}
					if(z > 0 && z < 7 && y == 2 && x == 0)
					{
						this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, z + 1, structureBoundingBoxIn);
					}
					if(z > 0 && z < 7 && y > 0 && y < 5)
					{
						this.setBlockState(world, Blocks.BOOKSHELF.getDefaultState(), 6, y, z + 1, structureBoundingBoxIn);
					}
					
				}
			}
			
		}
		
		//this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 4, 1, 2 + 1, structureBoundingBoxIn);
		//this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 4, 2, 2 + 1, structureBoundingBoxIn);
		this.setBlockState(world, Blocks.ENCHANTING_TABLE.getDefaultState(), 3, 1, 6 + 1, structureBoundingBoxIn);
        this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 1, 2, 1 + 1, structureBoundingBoxIn);
        this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 3, 2, 1 + 1, structureBoundingBoxIn);
		
		this.spawnVillagers(world, structureBoundingBoxIn, 4, 1, 4 + 1, 1);
	}
	
}