package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntitySkeledon;
import com.mod.mathsmod.add.Entity.Entity.EntityXGolem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockThoriumBlock extends Block
{
	public BlockThoriumBlock() {
		super(Material.IRON);
		this.setUnlocalizedName("thorium_block");
		this.setRegistryName("thorium_block");
		this.setResistance(100000F);
		this.setHardness(80F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
		return true;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "necessary to build a new portal frame to a cooler dimension");
		tooltip.add(TextFormatting.YELLOW + "or if placed on top of a bone block and right clicked with an ender fish");
		tooltip.add(TextFormatting.YELLOW + "will summon a boss");
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(playerIn.getHeldItemMainhand().getItem() == RegisterItems.ENDER_FISH && worldIn.getBlockState(pos.down()).getBlock() == Blocks.BONE_BLOCK)
		{
			if(!worldIn.isRemote)
			{
				EntitySkeledon entity = new EntitySkeledon(worldIn);
				entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ());
				worldIn.spawnEntity(entity);
			}
			
			worldIn.setBlockToAir(pos.down());
			worldIn.setBlockToAir(pos);
			
			for(int i = -3; i <= 3; ++i)
			{
				for(int j = -3; j <= 3; ++j)
				{
					for(int k = 0; k <= 2; ++k)
					{
						BlockPos pos2 = pos.add(i, j, k);
						if(worldIn.getBlockState(pos2).getBlockHardness(worldIn, pos2) < 0)
						{
							worldIn.setBlockToAir(pos2);
						}
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
    }
}