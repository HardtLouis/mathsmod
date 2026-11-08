package com.mod.mathsmod.add.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.ItemBlackHoleLauncher;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBasicLootBox extends Block
{

	public BlockBasicLootBox() 
	{
		super(Material.IRON);
		this.setRegistryName("basic_loot_box").setUnlocalizedName("basic_loot_box").setResistance(1000F).setHardness(2F).setLightLevel(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add("15% chances to obtain 5 thorium ingots");
		tooltip.add("14.5% chances to obtain a silver block");
		tooltip.add("12.5% chances to obtain a A-Missile");
		tooltip.add("10% chances to obtain a random gun");
		tooltip.add("10% chances to obtain a kurayum block");
		tooltip.add("7.5% chances to obtain a banana (WOW)");
		tooltip.add("5% chances to obtain a thorium ingot");
		tooltip.add("5% chances to obtain 2 basic lootboxes");
		tooltip.add("5% chances to obtain a turret");
		tooltip.add("5% chances to obtain an absurdly cool vehicle");
		tooltip.add("5% chances to obtain 5 iron shards");
		tooltip.add("5% chances to obtain 5 golden bat");
		tooltip.add("0.5% chances to obtain a frying pan");
    }
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float x, float y, float z)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            int i = new Random().nextInt(200);
            
            Item item = RegisterItems.SILVER_BLOCK;
            
            
            if(i < 20)
            {
            	item = this.getRandomGun();
            }
            else if(i < 30)
            {
            	item = RegisterItems.THORIUM_INGOT;
            }
            else if(i < 40)
            {
            	item = Items.AIR;
            	worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.BASIC_LOOTBOX, 2)));
            }
            else if(i < 60)
            {
            	item = RegisterItems.KURAYUM_BLOCK;
            }
            else if(i < 70)
            {
            	item = RegisterItems.TURRET;
            }
            else if(i < 80)
            {
            	item = RegisterItems.ULTIMATE_VEHICLE;
            }
            else if(i < 105)
            {
            	item = RegisterItems.A_MISSILE;
            }
            else if(i < 120)
            {
            	item = RegisterItems.BANANA;
            }
            else if(i < 150)
            {
            	item = Items.AIR;
            	worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.TFOSIUM_ORE, 5)));
            }
            else if(i < 160)
            {
            	item = Items.AIR;
            	worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.IRON_SHARD, 5)));
            }
            else if(i == 160)
            {
            	item = RegisterItems.FRYING_PAN;
            }
            else if(i < 171)
            {
            	item = RegisterItems.GOLDEN_BAT;
            }
            else
            {
            	item = RegisterItems.SILVER_BLOCK;
            }
            
            worldIn.destroyBlock(pos, false);
            
            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(item)));
            return true;
        }
    }
	
	protected Item getRandomGun()
    {
    	List<Item> list = Lists.newArrayList();
    	
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemGun && !(item instanceof ItemBlackHoleLauncher) && item != RegisterItems.NUCLEAR_ROCKET_LAUNCHER)
    		{
    			list.add(item);
    		}
    	}
		return list.get(new Random().nextInt(list.size()));
    }

}