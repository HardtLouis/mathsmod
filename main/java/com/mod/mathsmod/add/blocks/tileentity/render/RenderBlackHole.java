package com.mod.mathsmod.add.blocks.tileentity.render;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.blocks.BlockBlackHoleStabilizer;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityBlackHole;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderBlackHole  extends TileEntitySpecialRenderer<TileEntityBlackHole>
{

	public static final ResourceLocation RES = new ResourceLocation(Mathsmod.MODID, "textures/entity/black_hole_beam.png");
	
	@Override
	public void render(TileEntityBlackHole te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
		if(te.getWorld().getBlockState(te.getPos().add(-3, 0, 0)).getBlock() == RegisterBlocks.STABILIZER && te.getWorld().getBlockState(te.getPos().add(-3, 0, 0)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.EAST)
		{
			if(te.getWorld().getBlockState(te.getPos().add(3, 0, 0)).getBlock() == RegisterBlocks.STABILIZER && te.getWorld().getBlockState(te.getPos().add(3, 0, 0)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.WEST)
			{
				if(te.getWorld().getBlockState(te.getPos().add(0, 0, -3)).getBlock() == RegisterBlocks.STABILIZER && te.getWorld().getBlockState(te.getPos().add(0, 0, -3)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.SOUTH)
				{
					if(te.getWorld().getBlockState(te.getPos().add(0, 0, 3)).getBlock() == RegisterBlocks.STABILIZER && te.getWorld().getBlockState(te.getPos().add(0, 0, 3)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.NORTH)
					{
						int pX = te.getPos().getX();
						int pY = te.getPos().getY();
						int pZ = te.getPos().getZ();
						RenderLaser.renderLaser(new Vec3d(pX + 0.5, pY + 0.5, pZ + 0.5), te.getWorld(), 0, new Vec3d(pX + 0.5 + 2, pY + 0.5, pZ + 0.5), (int) (te.getWorld().getTotalWorldTime() % 200), 200, RES);
						RenderLaser.renderLaser(new Vec3d(pX + 0.5, pY + 0.5, pZ + 0.5), te.getWorld(), 0, new Vec3d(pX + 0.5 - 2, pY + 0.5, pZ + 0.5), (int) (te.getWorld().getTotalWorldTime() % 200), 200, RES);
						RenderLaser.renderLaser(new Vec3d(pX + 0.5, pY + 0.5, pZ + 0.5), te.getWorld(), 0, new Vec3d(pX + 0.5, pY + 0.5, pZ + 0.5 + 2), (int) (te.getWorld().getTotalWorldTime() % 200), 200, RES);
						RenderLaser.renderLaser(new Vec3d(pX + 0.5, pY + 0.5, pZ + 0.5), te.getWorld(), 0, new Vec3d(pX + 0.5, pY + 0.5, pZ + 0.5 - 2), (int) (te.getWorld().getTotalWorldTime() % 200), 200, RES);
					}
				}
			}
		}
    }
		
}
