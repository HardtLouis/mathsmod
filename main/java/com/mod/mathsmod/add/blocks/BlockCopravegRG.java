package com.mod.mathsmod.add.blocks;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCopravegRG extends Block
{

	public BlockCopravegRG()
	{
		super(Material.ROCK);
		this.setUnlocalizedName("copraveg_rg");
		this.setRegistryName("copraveg_rg");
		this.setResistance(10F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 6);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        
        Item[] boots = new Item[] {RegisterItems.FUGITIVE_BOOTS, RegisterItems.WARRIOR_BOOTS, RegisterItems.ATTACKER_BOOTS, RegisterItems.SHIELD_BOOTS, RegisterItems.TANK_BOOTS, RegisterItems.AGGRESSIVE_BOOTS};
        
        Item[] leggings = new Item[] {RegisterItems.FUGITIVE_LEGGINGS, RegisterItems.WARRIOR_LEGGINGS, RegisterItems.ATTACKER_LEGGINGS, RegisterItems.SHIELD_LEGGINGS, RegisterItems.TANK_LEGGINGS, RegisterItems.AGGRESSIVE_LEGGINGS};

        Item[] chestplate = new Item[] {RegisterItems.FUGITIVE_CHESTPLATE, RegisterItems.WARRIOR_CHESTPLATE, RegisterItems.ATTACKER_CHESTPLATE, RegisterItems.SHIELD_CHESTPLATE, RegisterItems.TANK_CHESTPLATE, RegisterItems.AGGRESSIVE_CHESTPLATE};
        
        Item[] helmet = new Item[] {RegisterItems.FUGITIVE_HELMET, RegisterItems.WARRIOR_HELMET, RegisterItems.ATTACKER_HELMET, RegisterItems.SHIELD_HELMET, RegisterItems.TANK_HELMET, RegisterItems.AGGRESSIVE_HELMET};
        
        for(int i = 0; i < 2; ++i)
        {
        	int r = rand.nextInt(4);
        	if(r == 0)
        	{
        		drops.add(new ItemStack(boots[rand.nextInt(boots.length)]));
        	}
        	if(r == 1)
        	{
        		drops.add(new ItemStack(leggings[rand.nextInt(leggings.length)]));
        	}
        	if(r == 2)
        	{
        		drops.add(new ItemStack(chestplate[rand.nextInt(chestplate.length)]));
        	}
        	if(r == 3)
        	{
        		drops.add(new ItemStack(helmet[rand.nextInt(helmet.length)]));
        	}
        }
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.APPLE;
    }
}
