package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSouthSword extends Item implements IReachWeapon
{

	public ItemSouthSword() 
	{
		this.setMaxDamage(0);
		this.setRegistryName("south_sword").setUnlocalizedName("south_sword");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "will give you an absurd reach when facing the south");
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		target.knockBack(attacker, 3F, (double)MathHelper.sin(attacker.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
		target.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 200000, 0));
		target.heal(1);
		
		if(target instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(target.motionX, target.motionY, target.motionZ)), (EntityPlayerMP) target);
		}
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player == null || player.getHorizontalFacing() != EnumFacing.SOUTH)
		{
			return false;
		}
		else
		{
			return true;
		}
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return player.getHorizontalFacing() == EnumFacing.SOUTH ? 6 : 0;
	}

}
