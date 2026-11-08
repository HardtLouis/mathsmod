package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityTrapHarpoon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemLauchTrap extends ItemM
{

	public ItemLauchTrap()
	{
		super("launch_trap");
		this.setMaxStackSize(1);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(!worldIn.isRemote)
        {
        	EntityTrapHarpoon ent = new EntityTrapHarpoon(worldIn, playerIn);
        	worldIn.spawnEntity(ent);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
