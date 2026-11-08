package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurret;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurretAuto;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAutoTurret extends BlockTurret
{

	public BlockAutoTurret() 
	{
		super("auto_turret");
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    	if(!worldIn.isRemote)
    	{
    		EntityTurretAuto entity = this.turretAuto(worldIn);
    		entity.setPosition(pos.getX() + 0.5F, pos.getY() + 0.125F, pos.getZ() + 0.5F);
    		entity.setFirstOwner(placer.getName());
    		worldIn.spawnEntity(entity);
    	}
    }
    
    private EntityTurretAuto turretAuto(World world)
    {
    	return new EntityTurretAuto(world);
    }
	
}