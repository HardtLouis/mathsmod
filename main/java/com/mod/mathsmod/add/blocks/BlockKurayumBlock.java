package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityDestroyer;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
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

public class BlockKurayumBlock extends Block
{

	private BlockPattern destroyerPattern;
	private BlockPattern matePattern;

	public BlockKurayumBlock() {
		super(Material.ROCK);
		this.setUnlocalizedName("kurayum_block");
		this.setRegistryName("kurayum_block");
		this.setResistance(50F);
		this.setHardness(15F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if(!worldIn.isRemote && placer instanceof EntityPlayer)
		{
        	this.trySpawnDestroyer(worldIn, pos);
        	//this.trySpawnMGolem(worldIn, pos, (EntityPlayer) placer);
		}
    }
	
	private void trySpawnDestroyer(World worldIn, BlockPos pos)
    {
		BlockPattern.PatternHelper blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);

        blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);

        if (blockpattern$patternhelper != null)
        {
            for (int j = 0; j < this.getGolemPattern().getPalmLength(); ++j)
            {
                for (int k = 0; k < this.getGolemPattern().getThumbLength(); ++k)
                {
                    worldIn.setBlockState(blockpattern$patternhelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
                }
            }

            BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
            EntityDestroyer destroyer = new EntityDestroyer(worldIn);
            destroyer.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntity(destroyer);

        }
    }
	
	protected BlockPattern getGolemPattern()
    {
        if (this.destroyerPattern == null)
        {
            this.destroyerPattern = FactoryBlockPattern.start().aisle("~#~", "o^o", "~^~").where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.KURAYUM_ORE))).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.KURAYUM_BLOCK))).where('o', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.LEVER))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.destroyerPattern;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "can make a portal (like the nether portal) to another dimension if you have the key");
		tooltip.add(TextFormatting.YELLOW + "to obtain the key, you have to fight the destroyer");
		tooltip.add(TextFormatting.YELLOW + "to fight it, place this block on too of 2 kurayum ore with 2 lever on opposite sides of the upper kurayum ore");
    }
	
	private void trySpawnMGolem(World worldIn, BlockPos pos, EntityPlayer player)
    {
		BlockPattern.PatternHelper blockpattern$patternhelper = this.getMatePattern().match(worldIn, pos);

        blockpattern$patternhelper = this.getMatePattern().match(worldIn, pos);

        if (blockpattern$patternhelper != null)
        {
            for (int j = 0; j < this.getMatePattern().getPalmLength(); ++j)
            {
                for (int k = 0; k < this.getMatePattern().getThumbLength(); ++k)
                {
                    worldIn.setBlockState(blockpattern$patternhelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 2);
                }
            }

            BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
            EntityMateGolem golem = new EntityMateGolem(worldIn);
            golem.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
            golem.setOwner(player);
            worldIn.spawnEntity(golem);

        }
    }
	
	protected BlockPattern getMatePattern()
    {
        if (this.matePattern == null)
        {
            this.matePattern = FactoryBlockPattern.start().aisle("~^~", "^o^", "~^~").where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.KURAYUM_BLOCK))).where('o', BlockWorldState.hasState(BlockStateMatcher.forBlock(RegisterBlocks.FURNACE))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.matePattern;
    }
	
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
		
		return true;
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
		if(playerIn.getHeldItemMainhand().getItem() == RegisterItems.COPRAVEG_KEY)
		{
			RegisterBlocks.PORTAL.trySpawnPortal(worldIn, pos.up());
		}
		return false;
    }
}
