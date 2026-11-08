package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityXGolem;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTfosiumBlock extends Block{

	private BlockPattern golemPattern;
	
	public BlockTfosiumBlock() {
		super(Material.IRON);
		this.setUnlocalizedName("tfosium_block");
		this.setRegistryName("tfosium_block");
		this.setResistance(20F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 4);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if(!worldIn.isRemote && placer instanceof EntityPlayer)
		{
        	//this.trySpawnGolem(worldIn, pos, (EntityPlayer) placer);
		}
        
    }
	
	private void trySpawnGolem(World worldIn, BlockPos pos, EntityPlayer player)
    {
		double dis = 10; 	
	        		
		BlockPattern.PatternHelper blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);

        blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);

        if (blockpattern$patternhelper != null)
        {
            for (int j = 0; j < this.getGolemPattern().getPalmLength(); ++j)
            {
                for (int k = 0; k < this.getGolemPattern().getThumbLength(); ++k)
                {
                    worldIn.setBlockState(blockpattern$patternhelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
                }
            }

            BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
            EntityXGolem entityxgolem = new EntityXGolem(worldIn);
            entityxgolem.setOwner(player);
            entityxgolem.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntity(entityxgolem);

        }
        
    }
	
	protected BlockPattern getGolemPattern()
    {
        if (this.golemPattern == null)
        {
            this.golemPattern = FactoryBlockPattern.start().aisle("~^~", "#o#", "~#~").where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.TFOSIUM_BLOCK))).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.SILVER_BLOCK))).where('o', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.SILVER_CHEST))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemPattern;
    }
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
		return true;
    }
	
}