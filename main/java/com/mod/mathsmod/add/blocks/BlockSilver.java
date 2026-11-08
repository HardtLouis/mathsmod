package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSilver extends Block{
	
	public BlockSilver() {
		super(Material.IRON);
		this.setUnlocalizedName("silver_block");
		this.setRegistryName("silver_block");
		this.setResistance(10F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
		return true;
    }
}
