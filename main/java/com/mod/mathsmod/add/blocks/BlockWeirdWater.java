package com.mod.mathsmod.add.blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.mod.mathsmod.add.RegisterFluid;
import com.mod.mathsmod.add.Entity.Entity.FrostResistance;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ICustomColor;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWeirdWater extends BlockFluidClassic implements ICustomColor
{

	public BlockWeirdWater()
	{
		super(RegisterFluid.WEIRD_WATER, Material.WATER);
		this.setRegistryName("weird_water").setUnlocalizedName("weird_water");
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
    {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entity);
    }
	
	@Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
    {
		super.updateTick(world, pos, state, rand);
    }
	
	@SideOnly (Side.CLIENT)
    public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks)
    {
        return new Vec3d(1.0F, 0.0F, 1.0F);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) 
	{
		double x = (1.0 + client_Proxy.biomeVectX.getValue(pos.getX() * 0.001, pos.getZ() * 0.001)) / 2.0;
		int red = (int) (x * 255 * 256 * 256);
		int blue = (int) (x * 255);
        return red + blue;
	}

	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		return -1;
	}
}
