package com.mod.mathsmod.add.crafting;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemCustomPickaxe;
import com.mod.mathsmod.add.items.ItemCustomSword;
import com.mod.mathsmod.add.items.toolparts.EnumParts;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeCustomSword extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	public static final IRecipe CUSTOM_SWORD = new RecipeCustomSword().setRegistryName("custom_sword");
    /**
     * Used to check if a recipe matches current crafting inventory
     */
	@Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        int num = 0;

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = inv.getStackInSlot(i);

            if(itemstack1.getItem() instanceof ItemToolPart)
            {
            	ItemToolPart item = (ItemToolPart) itemstack1.getItem();
            	num += 1;
            }
            else if(!itemstack1.isEmpty())
            {
            	return false;
            }
        }
        return num == 3 || num == 2;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
    	HashMap<Integer, ItemStack> map = Maps.newHashMap();
    	int j = 0;

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = inv.getStackInSlot(i);

            if(itemstack1.getItem() instanceof ItemToolPart)
            {
            	ItemToolPart item = (ItemToolPart) itemstack1.getItem();
            	if(item.part() == EnumParts.BLADE)
            	{
            		map.put(0, itemstack1);
            		j += 1;
            	}
            	else if(item.part() == EnumParts.GUARD)
            	{
            		map.put(1, itemstack1);
            		j += 1;
            	}
            	else if(item.part() == EnumParts.HANDLE)
            	{
            		map.put(2, itemstack1);
            		j += 1;
            	}
            	else if(item.part() == EnumParts.PICKAXE)
            	{
            		map.put(3, itemstack1);
            		j += 1;
            	}
            	else if(item.part() == EnumParts.HANDLE_TOOL)
            	{
            		map.put(4, itemstack1);
            		j += 1;
            	}
            }
        }
        
        if(j != 3 || map.values().size() != 3)
        {
        	if(j != 2 || map.values().size() != 2)
        	{
        		return ItemStack.EMPTY;
        	}
        	
        	ItemStack stack = new ItemStack(RegisterItems.CUSTOM_PICKAXE);
            ItemCustomPickaxe.setComponentsItems(stack, map.get(3), map.get(4));
            return stack;
        }
        ItemStack stack = new ItemStack(RegisterItems.CUSTOM_SWORD);
        ItemCustomSword.setComponentsItems(stack, map.get(0), map.get(1), map.get(2));
        return stack;
    }

	@Override
	public boolean canFit(int width, int height) 
	{
		return width >= 2 && height >= 2;
	}

	@Override
	public ItemStack getRecipeOutput() 
	{
		return ItemStack.EMPTY;
	}
}
