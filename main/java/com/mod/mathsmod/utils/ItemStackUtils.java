package com.mod.mathsmod.utils;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;

public class ItemStackUtils 
{
	
	public static final HashMap<Item, NonNullList<ItemStack>> craftsMap = Maps.newHashMap();

	public static NBTTagCompound saveAllItems(NBTTagCompound tag, NonNullList<ItemStack> list, String invName, boolean saveEmpty)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < list.size(); ++i)
        {
            ItemStack itemstack = list.get(i);

            if (!itemstack.isEmpty())
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        if (!nbttaglist.hasNoTags() || saveEmpty)
        {
            tag.setTag(invName, nbttaglist);
        }

        return tag;
    }

    public static void loadAllItems(NBTTagCompound tag, NonNullList<ItemStack> list, String invName)
    {
        NBTTagList nbttaglist = tag.getTagList(invName, 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < list.size())
            {
                list.set(j, new ItemStack(nbttagcompound));
            }
        }
    }
    
    
    public static void addEnchantment(ItemStack stack, Enchantment ench, String level)
    {
    	short e = NumsHelper.getShortFromString(level);
        if (stack.getTagCompound() == null)
        {
        	stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.getTagCompound().hasKey("ench", 9))
        {
        	stack.getTagCompound().setTag("ench", new NBTTagList());
        }

        NBTTagList nbttaglist = stack.getTagCompound().getTagList("ench", 10);
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", (short)Enchantment.getEnchantmentID(ench));
        nbttagcompound.setShort("lvl", e);
        nbttaglist.appendTag(nbttagcompound);
    }
    
    public static void removeEnchantment(ItemStack stack, Enchantment ench)
    {
    	if (!stack.isEmpty() && stack.hasTagCompound())
        {
            NBTTagList nbttaglist = stack.getEnchantmentTagList();

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                Enchantment enchantment = Enchantment.getEnchantmentByID(nbttagcompound.getShort("id"));

                if (enchantment == ench)
                {
                	nbttaglist.removeTag(i);
                	nbttagcompound.removeTag("id");
                	nbttagcompound.removeTag("lvl");
                }
            }
        }
    }
    
    public static void dropPlayerInventory(EntityPlayer player)
    {
    	if (!player.world.getGameRules().getBoolean("keepInventory") && !player.isSpectator())
        {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
	        {
	            ItemStack itemstack = player.inventory.getStackInSlot(i);

	            if (!itemstack.isEmpty() && EnchantmentHelper.hasVanishingCurse(itemstack))
	            {
	            	player.inventory.removeStackFromSlot(i);
	            }
	        }
			player.inventory.dropAllItems();
        }
    }
    
    public static void registerGunsCrafts()
    {
    	for(Item item : Item.REGISTRY)
		{
			if(item instanceof IGunCraft)
			{
				if(((IGunCraft) item).Craft() != null)
				{
					craftsMap.put(item, ((IGunCraft) item).Craft());
					if(craftsMap.values().contains(((IGunCraft) item).Craft()))
					{
						System.out.print("duplicate craft for " + item.getRegistryName() + "\n");
					}
				}
			}
		}
    }
}
