package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityCaster;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockCaster extends BlockContainer
{

	public BlockCaster()
	{
		super(Material.IRON);
		this.setRegistryName("caster").setUnlocalizedName("caster").setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setHardness(1.0F).setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityCaster();
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityCaster)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityCaster)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
	{
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityCaster && !worldIn.isRemote)
		{
			TileEntityCaster te = (TileEntityCaster) worldIn.getTileEntity(pos);
			te.lastInt = playerIn.getName();
			playerIn.openGui(Mathsmod.instance, 18, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

}