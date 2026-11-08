package com.mod.mathsmod.add.items.toolparts;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemHammerBlade extends ItemToolPart
{

	public ItemHammerBlade() 
	{
		super("hammer_blade");
	}

	@Override
	public EnumParts part() 
	{
		return EnumParts.BLADE;
	}

	@Override
	public float damageMultiplier()
	{
		return 1.25F;
	}
	
	public void hitEntity(EntityLivingBase attacker, EntityLivingBase target, World world, ItemStack stack, int part, int type)
	{
		if(this.getMetal(stack) != null)
		{
			this.getMetal(stack).effectOnHit(part, type, world, attacker, target, stack);
		}
		
		Vec3d vec = attacker.getLook(1.0F).scale(1.8F);
		target.addVelocity(vec.x, vec.y, vec.z);
		if(target instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageModifyVelocity(vec), (EntityPlayerMP) target);
		}
	}

	@Override
	public float efficiencyMultiplier() 
	{
		return 1.0F;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		if(this.getMetal(itemStack) != null)
		{
			return this.getMetal(itemStack).bonusReach - 0.2F;
		}
		return 0;
	}

	@Override
	public float protectionBonus() 
	{
		return 0;
	}

}