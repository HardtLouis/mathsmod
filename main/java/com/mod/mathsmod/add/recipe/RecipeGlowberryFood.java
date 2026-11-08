package com.mod.mathsmod.add.recipe;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeGlowberryFood extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		ItemStack stack1 = ItemStack.EMPTY;
		ItemStack stack2 = ItemStack.EMPTY;
        List<ItemStack> list = Lists.<ItemStack>newArrayList();
		
        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
        	if(stack1.isEmpty() && !inv.getStackInSlot(i).isEmpty() && inv.getStackInSlot(i).getItem() != RegisterItems.GLOWBERRY && inv.getStackInSlot(i).getItem() instanceof ItemFood)
        	{
        		list.add(inv.getStackInSlot(i));
        		stack1 = inv.getStackInSlot(i);
        	}
        	
        	else if(stack2.isEmpty() && !inv.getStackInSlot(i).isEmpty() && inv.getStackInSlot(i).getItem() == RegisterItems.GLOWBERRY)
        	{
        		list.add(inv.getStackInSlot(i));
        		stack2 = inv.getStackInSlot(i);
        		
        	}
        	
        	else if(!inv.getStackInSlot(i).isEmpty())
        	{
        		list.add(inv.getStackInSlot(i));
        	}
        }
        
        if(list.size() == 2 && !stack1.isEmpty() && !stack2.isEmpty())
        {
        	return true;
        }
        return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack stack = ItemStack.EMPTY;
		for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
			if(stack.isEmpty() && !inv.getStackInSlot(i).isEmpty() && inv.getStackInSlot(i).getItem() != RegisterItems.GLOWBERRY)
        	{
        		stack = inv.getStackInSlot(i).copy();
        	}
        }
		
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.setCount(1);
		stack.getTagCompound().setBoolean("is_glowberry_poisonned", true);
		return stack;
	}

	@Override
	public boolean canFit(int width, int height) 
	{
		return width * height > 2;
	}

    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }
}