package com.mod.mathsmod.add.GUI.handler;

import com.mod.mathsmod.add.Entity.Entity.*;
import com.mod.mathsmod.add.Entity.Entity.container.*;
import com.mod.mathsmod.add.Entity.Entity.gui.*;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.GUI.guiBasics.guiTrackPlayer;
import com.mod.mathsmod.add.GUI.screen.guiForging;
import com.mod.mathsmod.add.GUI.screen.guiInstructionBook;
import com.mod.mathsmod.add.GUI.screen.guiLaunchDrone;
import com.mod.mathsmod.add.GUI.screen.guiRandomizedStructurePlacer;
import com.mod.mathsmod.add.blocks.container.*;
import com.mod.mathsmod.add.blocks.container.gui.*;
import com.mod.mathsmod.add.blocks.tileentity.*;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;
import com.mod.mathsmod.add.items.guns.accessories.GunContainer;
import com.mod.mathsmod.add.items.guns.accessories.guiGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class guiHandler implements IGuiHandler{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity TE = world.getTileEntity(new BlockPos(x, y, z));
		switch(ID)
		{
		case 0:
			return this.EntityContains(player, world, x);
		case 1:
			return new InventorySilverChest(player.inventory, (TileEntitySilverChest) TE, player);
		case 2:
			return new ContainerMultiFurnace(player.inventory, (TileEntityMultiFurnace) TE);
		case 3:
			return new ContainerScrollableChest(player.inventory, (TileEntityScrollableChest) TE, player);
		case 4:
			return this.EntityContains(player, world, x);
		case 5:
			return this.EntityContains(player, world, x);
		case 6:
			return new ContainerGunCrafter(player.inventory, (TileEntityGunCrafter) TE, player);
		case 7:
			return new ContainerRecuperator(player.inventory, (TileEntityRecuperator) TE, player);
		case 8:
			return this.EntityContains(player, world, x);
		case 9:
			return new ContainerDisenchanter(player.inventory, (TileEntityDisenchanter) TE, player);
		case 10:
			return new ContainerWolfArmorCrafter(player.inventory, (TileEntityWolfArmorCrafter) TE, player);
		case 11:
			return null;//new ContainerNull(player.inventory, player);
		case 12:
			return null;//new ContainerNull(player.inventory, player);
		case 13:
			if(player.getHeldItemMainhand().getItem() instanceof ItemMachineGun)
			{
				return new GunContainer(player.inventory, (ItemMachineGun) player.getHeldItemMainhand().getItem(), player.getHeldItemMainhand());
			}
			else
			{
				return null;
			}
		case 14:
			return new ContainerMissileLauncher(player.inventory, (TileEntityMissileLauncher) TE, player);
		case 15:
			return null;//new ContainerNull(player.inventory, player);
		case 16:
			return this.EntityContains(player, world, x);
		case 17:
			return new ContainerMeltingFurnace(player.inventory, (IInventory) TE);
		case 18:
			return new ContainerCaster(player.inventory, (IInventory) TE);
		case 19:
			return this.EntityContains(player, world, x);
		case 20:
			return null;
		case 21:
			return null;
		}
		
			
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity TE = world.getTileEntity(new BlockPos(x, y, z));
		
		Container container1 = EntityContains(player, world, x);
		
		final Entity entity = world.getEntityByID(x);
		
		switch(ID)
		{
		case 0:
			return container1 != null ? new guiXGolem(player.inventory, (EntityXGolem) entity) : null;
		case 1:
			return new guiSilverChest(player.inventory, (TileEntitySilverChest) TE);
		case 2:
			return new guiMultiFurnace(player.inventory, (TileEntityMultiFurnace) TE);
		case 3:
			return new guiScrollableChest(player.inventory, (TileEntityScrollableChest) TE);
		case 4:
			return container1 != null ? new guiPlane(player.inventory, (EntityPlane) entity) : null;
		case 5:
			return container1 != null ? new guiMateGolem(player.inventory, (EntityMateGolem) entity) : null;
		case 6:
			return new GuiGunsCrafter(player.inventory, (TileEntityGunCrafter) TE);
		case 7:
			return new GuiRecuperator(player.inventory, (TileEntityRecuperator) TE);
		case 8:
			if(entity instanceof EntityWolf)
			{
				return container1 != null ? new guiWolf(player.inventory, (EntityWolf) entity) : null;
			}
			else if(entity instanceof EntityLiving)
			{
				return container1 != null ? new guiWolf(player.inventory, (EntityLiving) entity) : null;
			}
		case 9:
			return new guiDisenchanter(player.inventory, (TileEntityDisenchanter) TE);
		case 10:
			return new guiWolfArmorCrafter(player.inventory, (TileEntityWolfArmorCrafter) TE);
		case 11:
			return new guiLaunchDrone(player.inventory);
		case 12:
			return new guiInstructionBook(0);
		case 13:
			if(player.getHeldItemMainhand().getItem() instanceof ItemMachineGun)
			{
				return new guiGun(player, player.getHeldItemMainhand(), (ItemMachineGun) player.getHeldItemMainhand().getItem());
			}
			else
			{
				return null;
			}
		case 14:
			return new guiMissileLauncher(player.inventory, (TileEntityMissileLauncher) TE);
		case 15:
			return new guiTrackPlayer();
		case 16:
			return new guiBot(player.inventory, (EntityBot) entity, x);
		case 17:
			return new guiMeltingFurnace(player.inventory, (IInventory) TE);
		case 18:
			return new guiCaster(player.inventory, (TileEntityCaster) TE);
		case 19:
			return container1 != null ? new guiTradeWithHunter(player.inventory, (EntityHunter) entity, player) : null;
		case 20:
			return new guiForging();
		case 21:
			return new guiRandomizedStructurePlacer(player.inventory, new BlockPos(x, y, z));
		}

		return null;
	}
	
	
	private static Container EntityContains(EntityPlayer player, World world, int entityId) {

		Entity entity = world.getEntityByID(entityId);

		if (entity instanceof EntityXGolem && !entity.isDead) 
		{
			return new ContainerXGolem(player.inventory, (EntityXGolem) entity, player);
		}
		if (entity instanceof EntityPlane && !entity.isDead) 
		{
			return new ContainerPlane(player.inventory,  (EntityPlane) entity, player);
		}
		if (entity instanceof EntityMateGolem && !entity.isDead) 
		{
			return new ContainerMateGolem(player.inventory,  (EntityMateGolem) entity, player);
		}
		if (entity instanceof EntityWolf && !entity.isDead) 
		{
			return new ContainerWolf(player.inventory,  (EntityWolf) entity, player);
		}
		if (entity instanceof EntitySkeleton && !entity.isDead) 
		{
			return new ContainerSkeleton(player.inventory,  (EntitySkeleton) entity, player);
		}
		if (entity instanceof EntityBot && !entity.isDead) 
		{
			return new ContainerBot(player.inventory,  (EntityBot) entity, player);
		}
		if (entity instanceof EntityHunter && !entity.isDead)
		{
			return new ContainerHunter(player.inventory, (EntityHunter) entity);
		}

		return null;
	}

}
