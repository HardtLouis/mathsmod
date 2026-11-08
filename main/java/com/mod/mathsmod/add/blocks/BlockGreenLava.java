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
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGreenLava extends BlockFluidClassic implements ICustomColor
{

	public BlockGreenLava()
	{
		super(RegisterFluid.GREEN_LAVA, Material.LAVA);
		this.setRegistryName("green_lava").setUnlocalizedName("green_lava");
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		if(worldIn.getBlockState(fromPos).getMaterial() == Material.WATER)
		{
			worldIn.setBlockToAir(fromPos);
			worldIn.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
		}
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
    {
		if(entity instanceof EntityLivingBase && !(entity instanceof FrostResistance))
		{
			if(((EntityLivingBase) entity).getMaxHealth() <= 100)
			{
				UtilsVoids.attackEntityFrom((EntityLivingBase) entity, DamageSource.LAVA, 0.25F);
			}
		}
    }
	
	@Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
    {
		super.updateTick(world, pos, state, rand);
		
		for(int i = -1; i <= 1; i += 2)
		{
			BlockPos fromPos = pos.north(i);
			if(world.getBlockState(fromPos).getMaterial() == Material.WATER)
			{
				world.setBlockToAir(fromPos);
				world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
			}
		}
		
		for(int i = -1; i <= 1; i += 2)
		{
			BlockPos fromPos = pos.east(i);
			if(world.getBlockState(fromPos).getMaterial() == Material.WATER)
			{
				world.setBlockToAir(fromPos);
				world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
			}
		}
		
		for(int i = -1; i <= 1; i += 2)
		{
			BlockPos fromPos = pos.up(i);
			if(world.getBlockState(fromPos).getMaterial() == Material.WATER)
			{
				world.setBlockToAir(fromPos);
				world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
			}
		}
    }
	
	@SideOnly (Side.CLIENT)
    public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks)
    {
        return new Vec3d(0.0F, 0.6F, 0.0F);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) 
	{
		double x = 1 + client_Proxy.biomeVectX.getValue(pos.getX() * 0.05, pos.getZ() * 0.05);
    	double z = 1 + client_Proxy.biomeVectZ.getValue(pos.getX() * 0.05, pos.getZ() * 0.05);
    	float rot = UtilsVoids.getYawFromPos(Vec3d.ZERO, x, z);
        return RenderLaser.getColorFromAngleQuarter(rot);
	}

	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		return -1;
	}
}
