package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHammer extends ItemPickaxe
{

	private int ray;
	
	public ItemHammer(ToolMaterial material, String name, int rayIn)
	{
		super(material);
		ray = rayIn;
		this.setRegistryName(name).setUnlocalizedName(name);
		this.efficiency = material.getEfficiency() / 3;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "mine in " + (2 * ray + 1) + " by " + (2 * ray + 1));
    }
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
        Block block = blockIn.getBlock();

        if (block == Blocks.OBSIDIAN)
        {
            return this.toolMaterial.getHarvestLevel() >= 3;
        }
        
        return super.canHarvestBlock(blockIn);
    }

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
        	for(int i = -ray; i <= ray; ++i)
        	{
        		for(int j = -ray; j <= ray; ++j)
            	{
        			EnumFacing enumfacing = EnumFacing.getDirectionFromEntityLiving(pos, entityLiving);
        			BlockPos bPos = this.rotate(enumfacing, new BlockPos(i, j, 0)).add(pos);
        			if(worldIn.getBlockState(bPos).getBlock().getBlockHardness(worldIn.getBlockState(bPos), worldIn, bPos) >= 0 && this.canHarvestBlock(worldIn.getBlockState(bPos)))
    				{
        				if(entityLiving instanceof EntityPlayer)
        				{
        					worldIn.getBlockState(bPos).getBlock().onBlockDestroyedByPlayer(worldIn, bPos, worldIn.getBlockState(bPos));
            				worldIn.getBlockState(bPos).getBlock().harvestBlock(worldIn, (EntityPlayer) entityLiving, bPos, worldIn.getBlockState(bPos), worldIn.getTileEntity(bPos), stack);
        					worldIn.destroyBlock(bPos, false);
        				}
        				else
            			{
            				worldIn.destroyBlock(bPos, true);
            			}
        			}
        			
            	}
        	}
            stack.damageItem(1, entityLiving);
        }

        return true;
    }
	
	private BlockPos rotate(EnumFacing facing, BlockPos pos)
    {
        switch (facing)
        {
            case NORTH:
            default:
                return pos;
            case WEST:
                return new BlockPos(-pos.getZ(), pos.getY(), pos.getX());
            case EAST:
                return new BlockPos(-pos.getZ(), pos.getY(), -pos.getX());
            case SOUTH:
                return new BlockPos(-pos.getX(), pos.getY(), -pos.getZ());
            case UP:
            	return new BlockPos(pos.getX(), pos.getZ(), -pos.getY());
            case DOWN:
            	return new BlockPos(pos.getX(), pos.getZ(), pos.getY());
        }
    }
}
