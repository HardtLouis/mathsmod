package com.mod.mathsmod.add.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOreLootbox extends Block
{

	public BlockOreLootbox() 
	{
		super(Material.IRON);
		this.setRegistryName("ore_loot_box").setUnlocalizedName("ore_loot_box").setResistance(10F).setHardness(1F).setLightLevel(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add("20% chances to obtain iron ores");
		tooltip.add("20% chances to obtain gold ores");
		tooltip.add("20% chances to obtain lapis ores");
		tooltip.add("15% chances to obtain diamond ores");
		tooltip.add("15% chances to obtain night shard");
		tooltip.add("10% chances to obtain silver ores");
		tooltip.add("10% chances to obtain tfosium ores");
		tooltip.add("5% chances to obtain kurayum ores");
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            int i = new Random().nextInt(100);
            
            Item item = Item.getItemFromBlock(Blocks.IRON_ORE);
            int count = 23;
            
            if(i >= 20)
            {
            	if(i < 40)
                {
                	item = Item.getItemFromBlock(Blocks.GOLD_ORE);
                }
            	else if(i < 60)
                {
            		item = Item.getItemFromBlock(Blocks.LAPIS_ORE);
            		count = 21;
                }
            	else if(i < 75)
                {
                	item = Item.getItemFromBlock(Blocks.DIAMOND_ORE);
                	count = 5;
                }
            	else if(i < 75)
                {
                	item = RegisterItems.NIGHT_SHARD;
                	count = 4;
                }
            	else if(i < 85)
            	{
            		item = RegisterItems.SILVER_ORE;
                	count = 8;
            	}
            	else if(i < 95)
            	{
            		item = RegisterItems.TFOSIUM_ORE;
                	count = 8;
            	}
            	else
            	{
            		item = RegisterItems.KURAYUM_ORE;
                	count = 3;
            	}
            }
            
            worldIn.destroyBlock(pos, false);
            
            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(item, count)));
            return true;
        }
    }

}