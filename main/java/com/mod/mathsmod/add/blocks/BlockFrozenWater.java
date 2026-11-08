package com.mod.mathsmod.add.blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.mod.mathsmod.add.RegisterFluid;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.FrostResistance;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFrozenWater extends BlockFluidClassic
{

	public BlockFrozenWater()
	{
		super(RegisterFluid.FROZEN_WATER, Material.WATER);
		this.setRegistryName("frozen_water").setUnlocalizedName("frozen_water");
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
    {
		if(entity instanceof EntityLivingBase && !(entity instanceof FrostResistance) && !(entity instanceof EntityWaterMob) && ((EntityLivingBase) entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != RegisterItems.POLARITE_CHESTPLATE)
		{
			((EntityLivingBase) entity).attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), 1F);
		}
    }
	
	@SideOnly (Side.CLIENT)
    public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks)
    {
        return new Vec3d(0.3F, 0.3F, 1F);
    }
}
