package com.mod.mathsmod.add.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockUnbreakableCopy extends Block
{
	
	public final Block block;
	boolean op;

	public BlockUnbreakableCopy(Block copied) 
	{
		super(copied.getMaterial(copied.getDefaultState()));
		block = copied;
        this.setDefaultState(this.blockState.getBaseState());
		this.setHardness(-1F);
		this.setResistance(Float.MAX_VALUE);
		this.setUnlocalizedName(copied.getUnlocalizedName());
		this.setRegistryName("unbreakable_" + copied.getRegistryName().getResourcePath());
		op = copied.isOpaqueCube(copied.getDefaultState());
	}
	
	@Deprecated
    public boolean isTopSolid(IBlockState state)
    {
        return block.isTopSolid(state);
    }
	
	public String getUnlocalizedName()
    {
        return block.getUnlocalizedName();
    }

    /**
     * @return true if the state occupies all of its 1x1x1 cube
     */
    @Deprecated
    public boolean isFullBlock(IBlockState state)
    {
        return block.isFullBlock(state);
    }

    @Deprecated
    public boolean canEntitySpawn(IBlockState state, Entity entityIn)
    {
        return block.canEntitySpawn(state, entityIn);
    }

    @Deprecated
    public int getLightOpacity(IBlockState state)
    {
        return block.getLightOpacity(state);
    }

    /**
     * Used in the renderer to apply ambient occlusion
     */
    @Deprecated
    @SideOnly(Side.CLIENT)
    public boolean isTranslucent(IBlockState state)
    {
        return block.isTranslucent(state);
    }
    
    public SoundType getSoundType()
    {
        return block.getSoundType();
    }

    @Deprecated
    public int getLightValue(IBlockState state)
    {
        return block.getLightValue(state);
    }
    
    @Deprecated
    public boolean isFullCube(IBlockState state)
    {
        return block.isFullCube(state);
    }
    
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return block.getLightValue(state, world, pos);
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return op;
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	return block.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    
    @Deprecated
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return block.getCollisionBoundingBox(blockState, worldIn, pos);
    }
    
    @Deprecated
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return block.getSelectedBoundingBox(state, worldIn, pos);
    }

    public Block copiedBlock()
    {
    	return block;
    }
    
}
