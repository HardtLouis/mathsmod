package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntitySkeledon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSirojusPortalFrame extends Block
{
	public BlockSirojusPortalFrame() {
		super(Material.IRON);
		this.setUnlocalizedName("sirojus_portal_frame");
		this.setRegistryName("sirojus_portal_frame");
		this.setResistance(100000F);
		this.setHardness(80F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
		return true;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "will allow you to open a portal if placed well and right clicked with the sirojus key");
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(playerIn.getHeldItemMainhand().getItem() == RegisterItems.SIROJUS_KEY)
		{
			RegisterBlocks.SIROPORTAL.trySpawnPortal(worldIn, pos.up());
		}
		return false;
    }
}