package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntitySBossCrab;
import com.mod.mathsmod.add.Entity.Entity.EntitySuperHunter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockPrison extends Block
{

	public BlockPrison() {
		super(Material.IRON);
		this.setRegistryName("crab_prison").setUnlocalizedName("crab_prison").setResistance(100000F).setHardness(-1F).setLightLevel(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            EntitySBossCrab entity = new EntitySBossCrab(worldIn);
            entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            entity.onInitialSpawn(worldIn.getDifficultyForLocation(pos), (IEntityLivingData)null);
            worldIn.spawnEntity(entity);
            worldIn.setBlockToAir(pos);
            for(int i = -10; i <= 10; ++i)
            {
            	for(int j = -10; j <= 10; ++j)
                {
            		worldIn.setBlockToAir(pos.north(MathHelper.clamp(i, -5, 5)).east(MathHelper.clamp(j, -5, 5)));
            		worldIn.setBlockToAir(pos.up().north(MathHelper.clamp(i, -5, 5)).east(MathHelper.clamp(j, -5, 5)));
            		worldIn.setBlockToAir(pos.up(2).north(MathHelper.clamp(i, -5, 5)).east(MathHelper.clamp(j, -5, 5)));
            		
                	worldIn.setBlockState(pos.down().north(i).east(j), RegisterBlocks.CRAB_LIMITS.getDefaultState());
                	worldIn.setBlockState(pos.north(-10).east(j).up((int) MathHelper.absMax(i, -1) - 1), RegisterBlocks.CRAB_LIMITS.getDefaultState());
                	worldIn.setBlockState(pos.north(10).east(j).up((int) MathHelper.absMax(i, -1) - 1), RegisterBlocks.CRAB_LIMITS.getDefaultState());
                	worldIn.setBlockState(pos.north(j).east(10).up((int) MathHelper.absMax(i, -1) - 1), RegisterBlocks.CRAB_LIMITS.getDefaultState());
                	worldIn.setBlockState(pos.north(j).east(-10).up((int) MathHelper.absMax(i, -1) - 1), RegisterBlocks.CRAB_LIMITS.getDefaultState());
                	worldIn.setBlockState(pos.up(9).north(i).east(j), RegisterBlocks.CRAB_LIMITS.getDefaultState());
                }

                	
            }
            return true;
        }
    }

}