package com.mod.mathsmod.add.items.guns.renderer;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemGunLayer;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeGunLayered extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.<ItemStack>newArrayList();

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = inv.getStackInSlot(i);

            if (itemstack1.getItem() instanceof ItemGun)
            {
            	ItemGun itemgun = (ItemGun)itemstack1.getItem();

                if (!itemstack.isEmpty())
                {
                    return false;
                }

                itemstack = itemstack1;
            }
            else if (itemstack1.getItem() instanceof IGoldenWeapon)
            {
            	IGoldenWeapon itemgun = (IGoldenWeapon)itemstack1.getItem();

                if (!itemstack.isEmpty())
                {
                    return false;
                }

                itemstack = itemstack1;
            }
            else
            {
                if(itemstack1.getItem() instanceof ItemGunLayer || itemstack1.getItem() == RegisterItems.SUPER_HUNTER_CORE || itemstack1.isEmpty())
                {
                	list.add(itemstack1);
                }
                else
                {
                	return false;
                }
            }
        }

        return !itemstack.isEmpty() && !list.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        int[] aint = new int[3];
        ResourceLocation text = null;
        boolean anim = false;
        ItemGun itemarmor = null;
        IGoldenWeapon item2 = null;
        boolean ehance = false;

        for (int k = 0; k < inv.getSizeInventory(); ++k)
        {
            ItemStack itemstack1 = inv.getStackInSlot(k);

            if (!itemstack1.isEmpty())
            {
                if (itemstack1.getItem() instanceof ItemGun)
                {
                    itemarmor = (ItemGun)itemstack1.getItem();

                    if (!itemstack.isEmpty())
                    {
                        return ItemStack.EMPTY;
                    }
                    
                    itemstack = itemstack1.copy();
                }
                else if (itemstack1.getItem() instanceof IGoldenWeapon)
                {
                    item2 = (IGoldenWeapon)itemstack1.getItem();

                    if (!itemstack.isEmpty())
                    {
                        return ItemStack.EMPTY;
                    }
                    
                    itemstack = itemstack1.copy();
                }
                else if(text == null)
                {
                    if (itemstack1.getItem() instanceof ItemGunLayer)
                    {
                    	text = ((ItemGunLayer) itemstack1.getItem()).getLayer();
                    }
                    if(itemstack1.isEmpty())
                    {
                    	text = null;
                    	anim = false;
                    }
                    
                    if (itemstack1.getItem() == RegisterItems.SUPER_HUNTER_CORE && itemarmor != null && !itemarmor.getEhanced(itemstack))
                    {
                    	ehance = true;
                    }
                    
                    if(!(itemstack1.getItem() instanceof ItemGunLayer) && itemstack1.getItem() != RegisterItems.SUPER_HUNTER_CORE && !itemstack1.isEmpty())
                    {
                    	return ItemStack.EMPTY;
                    }
                }
                else
                {
                	return ItemStack.EMPTY;
                }
            }
        }
        if (itemarmor != null)
        {
            itemarmor.setLayer(itemstack, text);
            itemarmor.setEhanced(itemstack, itemarmor.getEhanced(itemstack) || ehance);
            return itemstack;
        }
        else if (item2 != null)
        {
        	item2.setLayer(itemstack, text);
            return itemstack;
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nonnulllist.size(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
        }

        return nonnulllist;
    }

    public boolean isDynamic()
    {
        return true;
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canFit(int width, int height)
    {
        return width * height >= 2;
    }
}