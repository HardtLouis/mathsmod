package com.mod.mathsmod.add.Entity.Entity.VillagerProfessions.houses;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class VillagerHousesHandler implements VillagerRegistry.IVillageCreationHandler
{

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) 
	{
		return new PieceWeight(EnchanterHouse.class, 3, 2);
	}

	@Override
	public Class<?> getComponentClass() 
	{
		return EnchanterHouse.class;
	}

	@Override
	public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces,
			Random random, int p1, int p2, int p3, EnumFacing facing, int p5) 
	{
		MapGenStructureIO.registerStructureComponent(EnchanterHouse.class, "enchanter_house");
		return new EnchanterHouse().createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}

}
