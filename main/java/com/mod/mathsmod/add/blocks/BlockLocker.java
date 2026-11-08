package com.mod.mathsmod.add.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;

import akka.actor.FSM.State;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLocker extends Block
{
	boolean activated;
	public static final PropertyInteger POWER = PropertyInteger.create("opened", 0, 1);
	private final Set<BlockPos> blocksNeedingUpdate = Sets.<BlockPos>newHashSet();
	
	public BlockLocker() 
	{
		super(Material.IRON);
		this.setRegistryName("locker");
		this.setUnlocalizedName("locker");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setHardness(3F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 0));
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return true;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(POWER)).intValue();
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWER});
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
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return ((Integer)blockState.getValue(POWER)).intValue() > 0 ? Block.NULL_AABB : Block.FULL_BLOCK_AABB;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RegisterItems.LOCKER;
    }
    
    public void openOrCloseNeighbour(boolean action, BlockPos pos, World world, int num, HashMap<BlockPos, Integer> map)
    {
    	if(action)
    	{
			
    		for(EnumFacing facing : EnumFacing.values())
    		{
    			BlockPos bpos = pos.offset(facing);
    			IBlockState state = world.getBlockState(bpos);
    			
    			if(!map.containsKey(pos))
    			{
    				map.put(pos, num);
    			}
    			else if(map.get(pos) < num)
    			{
    				map.replace(pos, num);
    			}
    			
    			if(num > 0 && state.getBlock() == this && state.getValue(POWER).intValue() == 0)
    			{
    				this.openOrCloseNeighbour(action, bpos, world, num - 1, map);
    			}
    		}
    	}
    	else
    	{
			
    		for(EnumFacing facing : EnumFacing.values())
    		{
    			
    			BlockPos bpos = pos.offset(facing);
    			IBlockState state = world.getBlockState(bpos);
    			
    			if(!map.containsKey(pos))
    			{
    				map.put(pos, num);
    			}
    			else if(map.get(pos) < num)
    			{
    				map.replace(pos, num);
    			}
    			
    			if(num > 0 && state.getBlock() == this && state.getValue(POWER).intValue() != 0)
    			{
    				this.openOrCloseNeighbour(action, bpos, world, num - 1, map);
    			}
    		}
    	}
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(!worldIn.isRemote)
    	{
    		HashMap<BlockPos, Integer> map = Maps.newHashMap();
    		this.openOrCloseNeighbour(((Integer)state.getValue(POWER)).intValue() == 0, pos, worldIn, 10, map);
    		
    		for(BlockPos npos : map.keySet())
    		{
    			worldIn.setBlockState(npos, ((Integer)state.getValue(POWER)).intValue() == 0 ? worldIn.getBlockState(pos).withProperty(POWER, 1) : worldIn.getBlockState(pos).withProperty(POWER, 0), 2);
    		}
    	}
        return true;
    }

}
