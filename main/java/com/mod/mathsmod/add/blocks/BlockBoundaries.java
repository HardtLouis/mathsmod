package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntitySBossCrab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBoundaries extends Block
{

	public BlockBoundaries() {
		super(Material.IRON);
		this.setRegistryName("arena_limit").setUnlocalizedName("arena_limit").setResistance(100000F).setHardness(-1F).setLightLevel(1);
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
        if (worldIn.isRemote)
        {
            return false;
        }
        else if(playerIn.getHeldItemMainhand().getItem() == RegisterItems.CRAB_ARENA_KEY)
        {
        	playerIn.getHeldItemMainhand().setCount(0);
        	this.destroyNeigbour(worldIn, pos, state);
            return true;
        }
        else
        {
        	return false;
        }
    }
	
	public void destroyNeigbour(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.destroyBlock(pos, false);
		
		for(int i = -1; i <= 1; i += 1)
        {
        	for(int j = -1; j <= 1; j += 1)
            {
        		for(int k = -1; k <= 1; k += 1)
                {
        			BlockPos pos2 = pos.north(i).east(j).up(k);
        			if(worldIn.getBlockState(pos2) != null && worldIn.getBlockState(pos2).getBlock() != null && 
        					worldIn.getBlockState(pos2).getBlock() instanceof BlockBoundaries)
        			{
        				((BlockBoundaries) worldIn.getBlockState(pos2).getBlock()).destroyNeigbour(worldIn, pos2, worldIn.getBlockState(pos2));
        			}
                }
            }
        }
	}
}
