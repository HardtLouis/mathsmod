package com.mod.mathsmod.add.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntitySBossCrab;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLootBox extends Block
{

	public BlockLootBox() 
	{
		super(Material.IRON);
		this.setRegistryName("loot_box").setUnlocalizedName("loot_box").setResistance(1000F).setHardness(2F).setLightLevel(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
		tooltip.add("15% chances to obtain a B-Missile");
		tooltip.add("15% chances to obtain 5 thorium ingots");
		tooltip.add("14.5% chances to obtain a borealite ingot");
		tooltip.add("10% chances to obtain a random gun");
		tooltip.add("10% chances to obtain a thorium block");
		tooltip.add("7.5% chances to obtain a frozen sword");
		tooltip.add("5% chances to obtain a frying pan");
		tooltip.add("5% chances to obtain a 2 lootboxes");
		tooltip.add("5% chances to obtain an auto turret");
		tooltip.add("5% chances to obtain 5 polarite ingots");
		tooltip.add("5% chances to obtain a polarite sword");
		tooltip.add("2.5% chances to obtain a B.O.T");
		tooltip.add(TextFormatting.GOLD + "0.5% chances to obtain either a polarite frying pan or in a rare case a golden frying pan");
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
            
            Item item = RegisterItems.KURAYUM_BLOCK;
            
            
            if(i < 20)
            {
            	item = this.getRandomGun();
            }
            else if(i < 30)
            {
            	item = RegisterItems.FRYING_PAN;
            }
            else if(i < 40)
            {
            	item = Items.AIR;
            	worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.LOOTBOX, 2)));
            }
            else if(i < 60)
            {
            	item = RegisterItems.THORIUM_BLOCK;
            }
            else if(i < 70)
            {
            	item = RegisterItems.AUTO_TURRET;
            }
            else if(i < 75)
            {
            	item = RegisterItems.BOT;
            }
            else if(i < 105)
            {
            	item = RegisterItems.B_MISSILE;
            }
            else if(i < 120)
            {
            	item = RegisterItems.FROZEN_SWORD;
            }
            else if(i < 150)
            {
            	item = Items.AIR;
            	worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.THORIUM_INGOT, 5)));
            }
            else if(i < 160)
            {
            	item = Items.AIR;
            	worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RegisterItems.POLARITE_INGOT, 5)));
            }
            else if(i == 160)
            {
            	if(new Random().nextInt(4) == 0)
            	{
            		item = RegisterItems.GOLDEN_FRYING_PAN;
            	}
            	else
            	{
            		item = RegisterItems.POLARITE_FRYING_PAN;
            	}
            }
            else if(i < 171)
            {
            	item = RegisterItems.POLARITE_SWORD;
            }
            else
            {
            	item = RegisterItems.BOREALITE_INGOT;
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
    		if(item instanceof IGoldenWeapon || item instanceof IGoldenAnimatedGun)
    		{
    			list.add(item);
    		}
    	}
		return list.get(new Random().nextInt(list.size()));
    }

}