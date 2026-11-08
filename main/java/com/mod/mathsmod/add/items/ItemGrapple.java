package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHarpoon;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGrapple extends Item
{
	public ItemGrapple()
	{
		this.setRegistryName("grapple");
		this.setUnlocalizedName("grapple");
		this.setMaxStackSize(1);
		this.setMaxDamage(5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
		
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(handIn == EnumHand.MAIN_HAND && itemstack.getItemDamage() != this.getMaxDamage())
		{
			playerIn.getCooldownTracker().setCooldown(this, 10);
			Vec3d pos = playerIn.getPositionVector().addVector(0, playerIn.getEyeHeight(), 0);
			Vec3d vec = UtilsVoids.laser(10, playerIn.rotationYaw, playerIn.rotationPitch).add(pos);
			
			if(worldIn.rayTraceBlocks(pos, vec, false, true, false) != null && worldIn.rayTraceBlocks(pos, vec, false, true, false).typeOfHit != null && worldIn.rayTraceBlocks(pos, vec, false, true, false).typeOfHit != RayTraceResult.Type.MISS)
			{
				RayTraceResult result = worldIn.rayTraceBlocks(pos, vec, false, true, false);
				if(result.getBlockPos() != null)
				{
					Vec3d hit = new Vec3d(result.getBlockPos().getX() + 0.5, result.getBlockPos().getY(), result.getBlockPos().getZ() + 0.5);
					UtilsVoids.grapple(hit, playerIn, 1.5);
					itemstack.damageItem(1, playerIn);
					
					EntityHarpoon entity = new EntityHarpoon(worldIn, playerIn);
					entity.setID(playerIn.getEntityId());
					entity.doesDamages = false;
					
					if(!worldIn.isRemote)
					{
						worldIn.spawnEntity(entity);
					}
				}
			}
			
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(this.isDamaged(stack))
		{
			if(this.getTimer(stack) == 80)
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
	
	/*@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }*/
}
