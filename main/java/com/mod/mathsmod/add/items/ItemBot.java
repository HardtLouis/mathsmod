package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Models.ModelPlane;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBot extends Item
{

	public ItemBot() {
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setUnlocalizedName("bot");
		this.setRegistryName("bot");
		this.setMaxDamage(40);
	}
	
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return toRepair.isItemDamaged() && repair.getItem() == RegisterItems.THORIUM_INGOT;
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.YELLOW + "right click to spawn a bot, right click on the bot to");
		tooltip.add(TextFormatting.YELLOW + "change its setting, can be repaired with thorium ingots");
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (facing == EnumFacing.DOWN)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
            BlockPos blockpos = flag ? pos : pos.offset(facing);
            ItemStack itemstack = player.getHeldItem(hand);

            if (!player.canPlayerEdit(blockpos, facing, itemstack))
            {
                return EnumActionResult.FAIL;
            }
            else
            {
                boolean flag1 = !worldIn.isAirBlock(blockpos) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);

                if (flag1)
                {
                    return EnumActionResult.FAIL;
                }
                else
                {
                    double d0 = (double)blockpos.getX();
                    double d1 = (double)blockpos.getY();
                    double d2 = (double)blockpos.getZ();
                    List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));

                    if (!list.isEmpty())
                    {
                        return EnumActionResult.FAIL;
                    }
                    else
                    {
                        if (!worldIn.isRemote && this.getDamage(itemstack) != this.getMaxDamage())
                        {
                            EntityBot entity = new EntityBot(worldIn);
                            entity.isSentry = true;
                            float f = (float)MathHelper.floor((MathHelper.wrapDegrees(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                            entity.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f, 0.0F);
                            entity.setBasePos(new Vec3d(d0 + 0.5D, d1, d2 + 0.5D));
                            entity.setOwner(player);
                            entity.setHealth(this.getMaxDamage() - this.getDamage(itemstack));
                            worldIn.spawnEntity(entity);
                            itemstack.shrink(1);
                        }
                        else if(!worldIn.isRemote)
                        {
                        	player.sendStatusMessage(new TextComponentString("need thorium energy (anvil)"), true);
                        }
                        
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
		
    }
	
}