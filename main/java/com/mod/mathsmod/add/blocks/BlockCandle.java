package com.mod.mathsmod.add.blocks;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandle extends Block
{
	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625 * 7, 0.0625 * 0, 0.0625* 7, 0.0625*9, 0.0625*8, 0.0625*9);

	public BlockCandle()
    {
        super(Material.CLAY);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setHardness(10.0F).setResistance(4.0F).setUnlocalizedName("candle").setRegistryName("candle");
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return AABB;
    }
    
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB;
    }

}
