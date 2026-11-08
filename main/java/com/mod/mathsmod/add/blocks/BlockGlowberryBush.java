package com.mod.mathsmod.add.blocks;

import java.util.List;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlowberryBush extends Block implements net.minecraftforge.common.IShearable
{
	public static final PropertyInteger UNREADY = PropertyInteger.create("unready", 0, 1);
	
	public BlockGlowberryBush() 
	{
		super(Material.LEAVES);
		this.setRegistryName("glowberry_bush").setUnlocalizedName("glowberry_bush");
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setSoundType(SoundType.PLANT);
        this.setLightLevel(0.8F);
        this.setResistance(0.2F).setHardness(1.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(UNREADY, Integer.valueOf(0)));
		this.setTickRandomly(true);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		if(this.getMetaFromState(state) == 0 && rand.nextBoolean())
		{
			worldIn.setBlockState(pos, this.getDefaultState());
		}
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {UNREADY});
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(UNREADY)).intValue();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(UNREADY, Integer.valueOf(MathHelper.clamp(meta, 0, 1)));
    }

	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RegisterItems.GLOWBERRY;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		return NonNullList.withSize(1, new ItemStack(this));
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
		if(!worldIn.isRemote && this.getMetaFromState(state) == 0)
		{
			EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.GLOWBERRY, 1 + worldIn.rand.nextInt(3)));
			worldIn.setBlockState(pos, this.getStateFromMeta(1));
			worldIn.spawnEntity(item);
			return true;
		}
		return false;
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) 
	{
		return true;
	}
}
