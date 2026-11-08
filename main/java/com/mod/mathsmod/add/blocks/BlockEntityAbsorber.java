package com.mod.mathsmod.add.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityEAbsorber;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityEProjector;
import com.mod.mathsmod.add.items.ItemProjectorChanger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEntityAbsorber extends BlockContainer
{

	public BlockEntityAbsorber() {
		super(Material.IRON);
		this.setRegistryName("entity_absorber").setUnlocalizedName("entity_absorber").setResistance(0.2F).setHardness(0.2F).setHarvestLevel("none", 0);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "once placed, will attract weak entities in a 7 blocs radius to it");
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
	
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
       return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityEAbsorber();
	}
	
}
	