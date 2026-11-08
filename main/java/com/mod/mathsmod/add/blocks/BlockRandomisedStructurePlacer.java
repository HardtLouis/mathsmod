package com.mod.mathsmod.add.blocks;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityRandomizedStructurePlacer;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockRandomisedStructurePlacer extends BlockContainer
{

    public BlockRandomisedStructurePlacer()
    {
        super(Material.IRON);
        this.setRegistryName("randomized_structure_placer").setUnlocalizedName("randomized_structure_placer").setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setBlockUnbreakable().setResistance(1000000F);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(playerIn.getHeldItemMainhand().getItem() == Items.STICK)
    	{
    		if(worldIn.getTileEntity(pos) instanceof TileEntityRandomizedStructurePlacer)
    		{
    			TileEntityRandomizedStructurePlacer te = (TileEntityRandomizedStructurePlacer) worldIn.getTileEntity(pos);
    			for(String str : te.data)
    			{
    				playerIn.sendMessage(new TextComponentString(str));
    			}
    		}
    		return true;
    	}
        playerIn.openGui(Mathsmod.instance, 21, worldIn, pos.getX(), pos.getY(), pos.getZ());

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityRandomizedStructurePlacer();
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}