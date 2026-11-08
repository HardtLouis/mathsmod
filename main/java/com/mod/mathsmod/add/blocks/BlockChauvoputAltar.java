package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChauvoputAltar extends Block
{

	public BlockChauvoputAltar()
	{
		super(Material.ROCK);
		this.setRegistryName("chauvoput_altar").setUnlocalizedName("chauvoput_altar").setCreativeTab(Mathsmod.MATHSMOD_TAB).setHardness(2F).setHarvestLevel("", 0);;
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)

	{
		if(!worldIn.isRemote)
		{
			worldIn.setBlockToAir(pos);
			EntityNChauvoput entity = new EntityNChauvoput(worldIn);
			entity.rotationYaw = UtilsVoids.getYawFromPos(playerIn.getLookVec(), entity.posX, entity.posZ);
			entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 100, pos.getZ() + 0.5, 0, 0);
			worldIn.spawnEntity(entity);
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "right click on this block to summon the rainbow lava creature");
    }
	
	/**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}