package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityDevHand;
import com.mod.mathsmod.add.Entity.Entity.EntitySuperHunter;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityDisenchanter;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSuperHunterSpawner extends Block
{

	public BlockSuperHunterSpawner() {
		super(Material.IRON);
		this.setRegistryName("super_hunter_spawner").setUnlocalizedName("super_hunter_spawner").setResistance(5F).setHardness(3F);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
        	if(worldIn.getBlockState(pos.down()).getBlock() == RegisterBlocks.POLARITE_BLOCK && worldIn.getBlockState(pos.down(2)).getBlock() == RegisterBlocks.THORIUM_BLOCK)
        	{
        		EntityDevHand entity = new EntityDevHand(worldIn);
                entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
                entity.onInitialSpawn(worldIn.getDifficultyForLocation(pos), (IEntityLivingData)null);
                worldIn.spawnEntity(entity);
                worldIn.setBlockToAir(pos);
                worldIn.setBlockToAir(pos.down());
                worldIn.setBlockToAir(pos.down(2));
                return true;
        	}
        	else
        	{
        		EntitySuperHunter entity = new EntitySuperHunter(worldIn);
                entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
                entity.onInitialSpawn(worldIn.getDifficultyForLocation(pos), (IEntityLivingData)null);
                worldIn.spawnEntity(entity);
                worldIn.setBlockToAir(pos);
                return true;
        	}
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "will summon a boss if right clicked");
		tooltip.add(TextFormatting.YELLOW + "summon something else on certain conditions");
    }
	

}