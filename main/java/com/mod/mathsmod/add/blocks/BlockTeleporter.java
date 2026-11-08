package com.mod.mathsmod.add.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityEProjector;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityTeleporter;
import com.mod.mathsmod.add.items.ItemProjectorChanger;
import com.mod.mathsmod.add.items.ItemTeleportersBinder;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTeleporter extends BlockContainer
{
	
	 public static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

	public BlockTeleporter() {
		super(Material.IRON);
		this.setRegistryName("teleporter").setUnlocalizedName("teleporter").setResistance(0.2F).setHardness(0.2F).setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean isOpaqueCube(IBlockState state)
   {
       return false;
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer getBlockLayer()
   {
       return BlockRenderLayer.CUTOUT;
   }

   public boolean isFullCube(IBlockState state)
   {
       return false;
   }
   
   @Deprecated
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
   {
       return BLOCK_AABB;
   }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTeleporter();
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
		if(worldIn.getTileEntity(pos) instanceof TileEntityTeleporter)
		{
			TileEntityTeleporter te = (TileEntityTeleporter) worldIn.getTileEntity(pos);
			if(te.getTpPos() != null && te.getTpPos() != new BlockPos(0,0,0))
			{
				if(worldIn.getBlockState(te.getTpPos()).getBlock() == RegisterBlocks.TELEPORTER)
				{
					Ticket ticket = ForgeChunkManager.requestTicket(Mathsmod.instance, worldIn, Type.NORMAL);
					ForgeChunkManager.forceChunk(ticket, new ChunkPos(te.getTpPos()));
					
					if(worldIn.getTileEntity(te.getTpPos()) instanceof TileEntityTeleporter)
					{
						TileEntityTeleporter te2 = (TileEntityTeleporter) worldIn.getTileEntity(te.getTpPos());
						te2.setPos(null);
					}
					
					ForgeChunkManager.unforceChunk(ticket, new ChunkPos(te.getTpPos()));
					ForgeChunkManager.releaseTicket(ticket);
				}
			}
		}
    }
	
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion)
    {
		if(world.getTileEntity(pos) instanceof TileEntityTeleporter)
		{
			TileEntityTeleporter te = (TileEntityTeleporter) world.getTileEntity(pos);
			if(te.getTpPos() != null && te.getTpPos() != new BlockPos(0,0,0))
			{
				if(world.getBlockState(te.getTpPos()).getBlock() == RegisterBlocks.TELEPORTER)
				{
					Ticket ticket = ForgeChunkManager.requestTicket(Mathsmod.instance, world, Type.NORMAL);
					ForgeChunkManager.forceChunk(ticket, new ChunkPos(te.getTpPos()));
					
					if(world.getTileEntity(te.getTpPos()) instanceof TileEntityTeleporter)
					{
						TileEntityTeleporter te2 = (TileEntityTeleporter) world.getTileEntity(te.getTpPos());
						te2.setPos(null);
					}
					
					ForgeChunkManager.unforceChunk(ticket, new ChunkPos(te.getTpPos()));
					ForgeChunkManager.releaseTicket(ticket);
				}
			}
		}
    }
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
    {
    	if(entity instanceof EntityPlayer && entity.isSneaking() && !((EntityPlayer)entity).getCooldownTracker().hasCooldown(RegisterItems.TELEPORTER))
    	{
    		if(worldIn.getTileEntity(pos) instanceof TileEntityTeleporter)
    		{
    			TileEntityTeleporter te = (TileEntityTeleporter) worldIn.getTileEntity(pos);
    			if(te.getTpPos() != null && te.getTpPos() != new BlockPos(0,0,0))
    			{
    				if(worldIn.getBlockState(te.getTpPos()).getBlock() == RegisterBlocks.TELEPORTER)
    				{
    					Ticket ticket = ForgeChunkManager.requestTicket(Mathsmod.instance, worldIn, Type.NORMAL);
    					ForgeChunkManager.forceChunk(ticket, new ChunkPos(te.getTpPos()));
    					
    					if(worldIn.getTileEntity(te.getTpPos()) instanceof TileEntityTeleporter)
    					{
    						TileEntityTeleporter te2 = (TileEntityTeleporter) worldIn.getTileEntity(te.getTpPos());
    						if(!worldIn.isRemote)
    						{
    							entity.setPosition(te.getTpPos().getX() + 0.5, te.getTpPos().getY() + 0.6, te.getTpPos().getZ() + 0.5);
    							worldIn.updateEntityWithOptionalForce(entity, false);
    							entity.setPositionAndUpdate(te.getTpPos().getX() + 0.5, te.getTpPos().getY() + 0.6, te.getTpPos().getZ() + 0.5);
    							((EntityPlayer)entity).getCooldownTracker().setCooldown(RegisterItems.TELEPORTER, 10);
    						}
    					}
    					
    					ForgeChunkManager.unforceChunk(ticket, new ChunkPos(te.getTpPos()));
    					ForgeChunkManager.releaseTicket(ticket);
    				}
    			}
    		}
    	}
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
	{
		if(worldIn.getTileEntity(pos) instanceof TileEntityTeleporter)
		{
			if(playerIn.getHeldItemMainhand().getItem() == RegisterItems.TELEPORTERS_BINDER)
			{
				TileEntityTeleporter te = (TileEntityTeleporter) worldIn.getTileEntity(pos);
				ItemTeleportersBinder item = (ItemTeleportersBinder) playerIn.getHeldItemMainhand().getItem();
				if(item.getPos(playerIn.getHeldItemMainhand()) != null && worldIn.getBlockState(item.getPos(playerIn.getHeldItemMainhand())).getBlock() == this)
				{
					te.setTpPos(item.getPos(playerIn.getHeldItemMainhand()));
					
					Ticket ticket = ForgeChunkManager.requestTicket(Mathsmod.instance, worldIn, Type.NORMAL);
					ForgeChunkManager.forceChunk(ticket, new ChunkPos(te.getTpPos()));
					
					if(worldIn.getTileEntity(item.getPos(playerIn.getHeldItemMainhand())) instanceof TileEntityTeleporter && pos != item.getPos(playerIn.getHeldItemMainhand()))
					{
						TileEntityTeleporter te2 = (TileEntityTeleporter) worldIn.getTileEntity(item.getPos(playerIn.getHeldItemMainhand()));
						te2.setTpPos(pos);
						playerIn.sendStatusMessage(new TextComponentString("successfully bound"), true);
						item.setPos(playerIn.getHeldItemMainhand(), null);
					}
					
					ForgeChunkManager.forceChunk(ticket, new ChunkPos(te.getTpPos()));
					ForgeChunkManager.releaseTicket(ticket);
				}
				else
				{
					item.setPos(playerIn.getHeldItemMainhand(), pos);
					playerIn.sendStatusMessage(new TextComponentString("set first tp"), true);
				}
			}
		}
		return true;
   }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add(TextFormatting.YELLOW + "on right click with a bound teleporter binders, will be bound to the bound teleporter");
		tooltip.add(TextFormatting.YELLOW + "on right click with a not bound teleporter binders, will bind it to this teleporter");
		tooltip.add(TextFormatting.YELLOW + "on sneak, will teleport you to the bound teleporter");
    }
}