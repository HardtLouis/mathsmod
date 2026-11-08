package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityMacerator;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockMacerator extends BlockContainer
{

	public BlockMacerator()
	{
		super(Material.WOOD);
		this.setRegistryName("macerator").setUnlocalizedName("macerator").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityMacerator();
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
	{
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityMacerator && !worldIn.isRemote)
		{
			TileEntityMacerator te = (TileEntityMacerator) worldIn.getTileEntity(pos);
			if(playerIn.isSneaking())
			{
				playerIn.sendStatusMessage(new TextComponentString("seeds : " + te.seeds + "| potatoes : " + te.potatoes + "| water : " + te.water), true);
			}
			else
			{
				if(playerIn.getHeldItem(hand).getItem() == Items.WHEAT_SEEDS)
				{
					if(te.seeds < 100)
					{
						te.seeds += 1;
						playerIn.getHeldItem(hand).shrink(1);
					}
				}
				
				if(playerIn.getHeldItem(hand).getItem() == Items.POTATO)
				{
					if(te.potatoes < 100)
					{
						te.potatoes += 1;
						playerIn.getHeldItem(hand).shrink(1);
					}
				}
				
				if(playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET)
				{
					if(te.water < 100)
					{
						te.water += 1;
						playerIn.getHeldItem(hand).shrink(1);
						playerIn.addItemStackToInventory(new ItemStack(Items.BUCKET));
					}
				}
				
				if(playerIn.getHeldItem(hand).getItem() == RegisterItems.VODKA)
				{
					if(playerIn.getHeldItem(hand).getItemDamage() > 0)
					{
						if(te.seeds > 0 && te.potatoes > 0 && te.water > 0)
						{
							te.seeds -= 1;
							te.potatoes -= 1;
							te.water -= 1;
							
							playerIn.getHeldItem(hand).setItemDamage(0);
						}
					}
				}
			}
		}
		return true;
	}

}
