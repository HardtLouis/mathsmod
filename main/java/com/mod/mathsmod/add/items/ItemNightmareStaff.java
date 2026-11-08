package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.World.dims.teleporter.CopravegTeleporterTest;
import com.mod.mathsmod.add.World.dims.teleporter.SirojusTeleporter;
import com.mod.mathsmod.add.World.dims.teleporter.UselessTp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemNightmareStaff extends ItemM
{

	public ItemNightmareStaff() 
	{
		super("nightmare_staff");
		this.setMaxStackSize(1);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		UselessTp teleporter;
    	
		if (!worldIn.isRemote)
		{
			if (playerIn.getRidingEntity() == null && !playerIn.isBeingRidden() && playerIn instanceof EntityPlayerMP) 
			{
				EntityPlayerMP player = (EntityPlayerMP) playerIn;
								
				
				if (player.timeUntilPortal > 0) 
				{
					player.timeUntilPortal = 100;
					
				}
				else if (player.dimension != MathsDims.nightmareID) 
				{
					player.timeUntilPortal = 100;
					player.isInvulnerableDimensionChange();
					teleporter = new UselessTp(new BlockPos(8, 2, 8), MathsDims.nightmareID);
					player.mcServer.getPlayerList().transferPlayerToDimension(player, MathsDims.nightmareID, teleporter);			
				} 
				else 
				{
					player.timeUntilPortal = 100;
					player.isInvulnerableDimensionChange();
					teleporter = new UselessTp(worldIn.getSpawnPoint(), 0);
					player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, teleporter);
					
				}
				
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
