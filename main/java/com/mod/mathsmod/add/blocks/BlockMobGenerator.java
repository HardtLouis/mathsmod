package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityMobGenerator;
import com.mod.mathsmod.add.items.ItemProjectorChanger;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMobGenerator extends BlockContainer
{
	
	 public static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockMobGenerator() {
		super(Material.IRON);
		this.setRegistryName("mob_generator").setUnlocalizedName("mob_generator").setResistance(0F).setHardness(0F).setHarvestLevel("none", 0);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
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
   
   @Deprecated
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
   {
       return BLOCK_AABB;
   }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityMobGenerator();
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
   {
       return EnumBlockRenderType.MODEL;
   }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
   {
		if(worldIn.getTileEntity(pos) instanceof TileEntityMobGenerator)
		{
			TileEntityMobGenerator te = (TileEntityMobGenerator) worldIn.getTileEntity(pos);
			Item item = playerIn.getHeldItemMainhand().getItem();
			if(item instanceof ItemProjectorChanger && te.entityName == null && ((ItemProjectorChanger) item).getEntity(playerIn.getHeldItemMainhand()) != null)
			{
				te.entityName = ((ItemProjectorChanger) item).getEntity(playerIn.getHeldItemMainhand());
			}
			if(item instanceof ItemProjectorChanger && ((ItemProjectorChanger) item).getEntity(playerIn.getHeldItemMainhand()) != te.entityName)
			{
				te.entityName = ((ItemProjectorChanger) item).getEntity(playerIn.getHeldItemMainhand());
			}
			else
			{
				if(te.number > 19)
				{
					te.number = 1;
				}
				else
				{
					te.number += 1;
				}
				
				playerIn.sendStatusMessage(new TextComponentString("set " + te.entityName + " number to " + te.number), true);
			}
		}
		return true;
   }
}
	