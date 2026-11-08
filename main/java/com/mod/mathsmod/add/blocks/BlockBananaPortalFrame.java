package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBananaPortalFrame extends Block
{
	public BlockBananaPortalFrame() {
		super(Material.IRON);
		this.setUnlocalizedName("banana_portal_frame");
		this.setRegistryName("banana_portal_frame");
		this.setResistance(100000F);
		this.setHardness(80F);
		this.setHarvestLevel("pickaxe", 2);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
		return true;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		RegisterBlocks.BANANA_PORTAL.trySpawnPortal(worldIn, pos.up());
		return false;
    }
}