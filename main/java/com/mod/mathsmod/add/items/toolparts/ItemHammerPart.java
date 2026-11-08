package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHammerPart extends ItemToolPart
{

	public ItemHammerPart()
	{
		super("hammer_part");
	}

	@Override
	public EnumParts part() {
		return EnumParts.PICKAXE;
	}

	@Override
	public float damageMultiplier() {
		return 1;
	}

	@Override
	public float efficiencyMultiplier() {
		return 0.15F;
	}
	
	public void onBlockDestroyed(EntityLivingBase user, BlockPos pos, World world, ItemStack stack, int part, int type)
	{
		if(this.getMetal(stack) != null)
		{
			this.getMetal(stack).effectOnBreakBlock(part, type, world, user, pos, stack);
		}
	}

	@Override
	public float protectionBonus()
	{
		return 0;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		return 0;
	}
}
