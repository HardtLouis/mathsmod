package com.mod.mathsmod.add.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.teleporter.CopravegTeleporter;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBlueFire extends Block 
{
	public BlockBlueFire()
	{
		super(Material.FIRE);
		this.setTickRandomly(true);
		this.setUnlocalizedName("fire");
		this.setRegistryName("fire");
	}
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return 30;
    }
    
    public boolean isCollidable()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	if(!this.hasFurnace(worldIn, pos))
    	{
    		worldIn.setBlockToAir(pos);
    	}
    }
    
    /*public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	entityIn.setFire(100);
    }*/
    
    private boolean hasFurnace(World worldIn, BlockPos pos)
    {
    	if(worldIn.getBlockState(pos.east(1).north(1)).getBlock() == RegisterBlocks.FURNACE ||
    			worldIn.getBlockState(pos.east(-1).north(-1)).getBlock() == RegisterBlocks.FURNACE ||
    			worldIn.getBlockState(pos.east(-1).north(1)).getBlock() == RegisterBlocks.FURNACE ||
    			worldIn.getBlockState(pos.east(1).north(-1)).getBlock() == RegisterBlocks.FURNACE)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
    	worldIn.setBlockToAir(pos);
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).isTopSolid();
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.getBlockState(pos.down()).isTopSolid())
        {
            worldIn.setBlockToAir(pos);
        }
    }
    
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	if (!worldIn.getBlockState(pos.down()).isTopSolid())
        {
            worldIn.setBlockToAir(pos);
        }
    }
	
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.TNT;
    }
}
