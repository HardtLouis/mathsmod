package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHarpoon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHarpoon extends Item
{
	public ItemHarpoon()
	{
		this.setRegistryName("harpoon");
		this.setUnlocalizedName("harpoon");
		this.setMaxStackSize(1);
		this.setMaxDamage(5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
		
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		EntityHarpoon entity = new EntityHarpoon(worldIn, playerIn);
		entity.setID(playerIn.getEntityId());
		
		playerIn.getCooldownTracker().setCooldown(this, 25);
		
		if(!worldIn.isRemote && this.getDamage(itemstack) < 5)
		{
			worldIn.spawnEntity(entity);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(this.isDamaged(stack))
		{
			if(this.getTimer(stack) == 60)
			{
				this.setTimer(stack, 0);
				this.setDamage(stack, this.getDamage(stack) - 1);
			}
			else
			{
				this.setTimer(stack, this.getTimer(stack) + 1);
			}
		}
    }
	
	public int getTimer(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("timer")) {

            return 0;
        }

        return stack.getTagCompound().getInteger("timer");
    }
	
	public void setTimer(ItemStack stack, int timer) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("timer", timer);
    }
	
	public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }
}