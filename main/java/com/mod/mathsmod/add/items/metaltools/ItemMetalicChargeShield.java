package com.mod.mathsmod.add.items.metaltools;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.items.ItemChargeShield;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemMetalicChargeShield extends ItemMetalicTool implements IShieldHit
{
	public ItemMetalicChargeShield()
	{
		super("metalic_charge_shield");
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.getCooldownTracker().setCooldown(this, 200);
        playerIn.addPotionEffect(new PotionEffect(RegisterPotions.CHARGE, 60, 200));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
	public static void destroyBlocks(World world, EntityPlayer player)
	{
		if (player.isPotionActive(RegisterPotions.CHARGE) && player.getActivePotionEffect(RegisterPotions.CHARGE).getDuration() % 4 == 0)
        {
            int i1 = MathHelper.floor(player.posY);
            int l1 = MathHelper.floor(player.posX);
            int i2 = MathHelper.floor(player.posZ);
            boolean flag = false;

            for (int k2 = -1; k2 <= 1; ++k2)
            {
                for (int l2 = -1; l2 <= 1; ++l2)
                {
                    for (int j = 0; j <= 1; ++j)
                    {
                    	float i3 = l1 + k2;
                    	float l = i2 + l2;
                    	float k = i1 + j;
                        
                        BlockPos blockpos = new BlockPos(i3, k, l);
                        IBlockState iblockstate = world.getBlockState(blockpos);
                        Block block = iblockstate.getBlock();

                        if (block.getBlockHardness(iblockstate, world, blockpos) >= 0 && !block.isAir(iblockstate, world, blockpos) && player.canPlayerEdit(blockpos, player.getAdjustedHorizontalFacing(), player.getHeldItemMainhand()) && ItemMetalicChargeShield.canDestroyBlock(iblockstate, world, blockpos))
                        {
                            flag = world.destroyBlock(blockpos, true) || flag;
                        }
                    }
                }
                if (flag)
	            {
	                world.playEvent((EntityPlayer)null, 1022, new BlockPos(player), 0);
	            }
            }
		}
	}

	private static boolean canDestroyBlock(IBlockState iblockstate, World world, BlockPos blockpos) 
	{
		return iblockstate.getBlock() != Blocks.CHEST && iblockstate.getBlock() != Blocks.TRAPPED_CHEST && world.getBlockState(blockpos).getBlockHardness(world, blockpos) >= 0 && world.getBlockState(blockpos).getBlockHardness(world, blockpos) < 50;
	}

	@Override
	public void onShieldHit(EntityLivingBase attacker, EntityLivingBase target, ItemStack stack) 
	{
		if(this.getMetal(stack) != null)
		{
			this.getMetal(stack).effectOnHit(0, 0, attacker.world, attacker, target, stack);
			if(attacker instanceof EntityPlayer)
			{
				((EntityPlayer) attacker).getCooldownTracker().setCooldown(this, 100);
			}
		}
	}

	@Override
	public void onShieldCharge(EntityLivingBase ent, ItemStack heldItemOffhand)
	{
		if(ent instanceof EntityPlayer)
		{
			//this.destroyBlocks(ent.world, (EntityPlayer) ent);
		}
	}
}
