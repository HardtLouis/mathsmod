package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRedstoneJukebox extends BlockJukebox
{

    public BlockRedstoneJukebox()
    {
        super();
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setRegistryName("redstone_jukebox").setUnlocalizedName("redstone_jukebox").setHardness(2.0F).setResistance(10.0F);
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());

        if (flag)
        {
        	if (worldIn.getTileEntity(pos) instanceof BlockJukebox.TileEntityJukebox)
            {
            	BlockJukebox.TileEntityJukebox te = (TileEntityJukebox) worldIn.getTileEntity(pos);
                
                if(te.getRecord().getItem() instanceof ItemRecord)
                {
                	ItemRecord item = (ItemRecord) te.getRecord().getItem();
                	ItemStack itemstack = te.getRecord();
                    ((BlockJukebox)Blocks.JUKEBOX).insertRecord(worldIn, pos, state, itemstack);
                    worldIn.playEvent((EntityPlayer)null, 1010, pos, Item.getIdFromItem(item));
                    //worldIn.playRecord(pos, item.getSound());
                }
                
            }
        }
        else
        {
        	worldIn.playEvent(1010, pos, 0);
        }
        
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (((Boolean)state.getValue(HAS_RECORD)).booleanValue())
        {
            this.dropRecord(worldIn, pos, state);
            state = state.withProperty(HAS_RECORD, Boolean.valueOf(false));
            worldIn.setBlockState(pos, state, 2);
            return true;
        }
        else
        {
        	if (!worldIn.isRemote && playerIn.getHeldItem(hand).getItem() instanceof ItemRecord)
            {
                ItemStack itemstack = playerIn.getHeldItem(hand);
        		ItemRecord record = (ItemRecord) itemstack.getItem();
        		
                this.insertRecord(worldIn, pos, state, itemstack);
                worldIn.playEvent((EntityPlayer)null, 1010, pos, Item.getIdFromItem(record));
                itemstack.shrink(1);
                playerIn.addStat(StatList.RECORD_PLAYED);
            }
            return true;
        }
    }
    
    private void dropRecord(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof BlockJukebox.TileEntityJukebox)
            {
                BlockJukebox.TileEntityJukebox blockjukebox$tileentityjukebox = (BlockJukebox.TileEntityJukebox)tileentity;
                ItemStack itemstack = blockjukebox$tileentityjukebox.getRecord();

                if (!itemstack.isEmpty())
                {
                    worldIn.playEvent(1010, pos, 0);
                    worldIn.playRecord(pos, (SoundEvent)null);
                    blockjukebox$tileentityjukebox.setRecord(ItemStack.EMPTY);
                    float f = 0.7F;
                    double d0 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
                    double d1 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
                    double d2 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
                    ItemStack itemstack1 = itemstack.copy();
                    EntityItem entityitem = new EntityItem(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemstack1);
                    entityitem.setDefaultPickupDelay();
                    worldIn.spawnEntity(entityitem);
                }
            }
        }
    }
}