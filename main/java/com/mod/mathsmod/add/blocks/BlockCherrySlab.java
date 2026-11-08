package com.mod.mathsmod.add.blocks;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCherrySlab extends BlockSlab
{
	public static final PropertyEnum<BlockCherrySlab.Variant> VARIANT = PropertyEnum.<BlockCherrySlab.Variant>create("variant", BlockCherrySlab.Variant.class);
	boolean isDouble;

    public BlockCherrySlab(boolean isdoubleIn)
    {
        super(Material.WOOD);
        
        this.isDouble = isdoubleIn;
        IBlockState iblockstate = this.blockState.getBaseState();

        if (!isdoubleIn)
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, BlockCherrySlab.Variant.DEFAULT));
        this.setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F).setUnlocalizedName(isdoubleIn ? "double_cherry_slab" : "cherry_slab").setRegistryName(isdoubleIn ? "double_cherry_slab" : "cherry_slab");
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
    }
    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(RegisterBlocks.CHERRY_SLAB);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(RegisterBlocks.CHERRY_SLAB);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockCherrySlab.Variant.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

	@Override
	public boolean isDouble()
	{
		return isDouble;
	}

	@Override
	public String getUnlocalizedName(int meta) 
	{
		return this.getUnlocalizedName();
	}

	@Override
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

	@Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return BlockCherrySlab.Variant.DEFAULT;
    }
	
	public static enum Variant implements IStringSerializable
    {
        DEFAULT;

        public String getName()
        {
            return "default";
        }
    }

    public boolean isFullCube(IBlockState state)
    {
        return isDouble;
    }
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return isDouble;
    }
}