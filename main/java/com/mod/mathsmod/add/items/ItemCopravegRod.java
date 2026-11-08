package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.teleporter.CopravegTeleporter;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCopravegRod extends Item
{
	public ItemCopravegRod()
	{
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("copraveg_rod");
		this.setRegistryName("copraveg_rod");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
    {
		
		ItemStack stack = player.getHeldItem(hand);
		
		if(!worldIn.isRemote)
		{
			player.setPortal(new BlockPos(player));
			if (player.getRidingEntity() == null && (player instanceof EntityPlayerMP))
			{
				EntityPlayerMP player1 = (EntityPlayerMP)player;
				UtilsVoids.ChangDim(player1, MathsDims.Copraveg.getId(), new CopravegTeleporter(new BlockPos(player.getBedLocation(0) == null ? worldIn.getSpawnPoint() : player.getBedLocation(0)), player.dimension));
			}
			else
			{
				if(player.dimension != MathsDims.Copraveg.getId())
				{
					player.changeDimension(MathsDims.Copraveg.getId());
					player.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 100000));
				}
				else
				{
					player.changeDimension(0);
					player.setPosition(worldIn.getSpawnPoint().getX() + 0.5, worldIn.getSpawnPoint().getY(), worldIn.getSpawnPoint().getZ() + 0.5);
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }
	
}