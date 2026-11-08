package com.mod.mathsmod.add.items;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemThermoresistantBucket extends Item implements ICustomColor
{
	public ItemThermoresistantBucket()
	{
		this.setMaxStackSize(1).setRegistryName("thermoresistant_bucket").setUnlocalizedName("thermoresistant_bucket").setCreativeTab(Mathsmod.MATHSMOD_MET_TAB);
		this.addPropertyOverride(new ResourceLocation("empty"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (ItemThermoresistantBucket.getMoltenStack(stack) == null || ItemThermoresistantBucket.getMoltenStack(stack).isEmpty())
                {
                    return 1.0F;
                }
                else
                {
                	return 0.0F;
                }
            }
        });
	}
	
	public String getItemStackDisplayName(ItemStack stack)
    {
		if(this.getMoltenStack(stack) == null || this.getMoltenStack(stack).isEmpty())
		{
	        return super.getItemStackDisplayName(stack);
		}
		else
		{
			String str = I18n.translateToLocal(this.getMoltenStack(stack).getItem().getUnlocalizedName() + ".name");
			return I18n.translateToLocal("prefix.molten") + " " + str;
		}
    }
	
	public boolean hasColor(ItemStack stack)
    {
		return !ItemThermoresistantBucket.getMoltenStack(stack).isEmpty();
    }
	
	public static ItemStack getMoltenStack(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		else
		{
			NonNullList<ItemStack> list = NonNullList.withSize(1, ItemStack.EMPTY);
			ItemStackUtils.loadAllItems(stack.getTagCompound(), list, "molten_stack");
			return list.get(0);
		}
		return ItemStack.EMPTY;
	}
	
	public static void setMoltenMaterial(ItemStack stack, ItemStack ingot)
	{
		if(!stack.hasTagCompound())
		{
			NBTTagCompound tag = new NBTTagCompound();
			NonNullList<ItemStack> list = NonNullList.withSize(1, ingot);
			ItemStackUtils.saveAllItems(tag, list, "molten_stack", true);
			stack.setTagCompound(tag);
		}
		else
		{
			NonNullList<ItemStack> list = NonNullList.withSize(1, ingot);
			ItemStackUtils.saveAllItems(stack.getTagCompound(), list, "molten_stack", true);
		}
		
	}
	
	public static Molten_metal material(ItemStack stack)
	{
		if(getMoltenStack(stack) == null || getMoltenStack(stack).isEmpty())
		{
			return null;
		}
		else
		{
			if(MeltingRegistry.getMeltingProperties().containsKey(getMoltenStack(stack).getItem()))
			{
				return MeltingRegistry.getMeltingProperties().get(getMoltenStack(stack).getItem());
			}
			else if(getMoltenStack(stack).getItem() == RegisterItems.ALLOY_INGOT)
			{
				return ItemAlloyIngot.getMoltenMaterial(getMoltenStack(stack)).result;
			}
			else
			{
				return null;
			}
		}
	}
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(tab == this.getCreativeTab())
		{
			ItemStack stackEmpty = new ItemStack(this);
			items.add(stackEmpty);
			
			for(Item metal : MeltingRegistry.getMeltingProperties().keySet())
			{
				ItemStack stack = new ItemStack(this);
				setMoltenMaterial(stack, new ItemStack(metal));
				items.add(stack);
			}
			
			for(String metal : MeltingRegistry.getAlloyMap().keySet())
			{
				alloy al = MeltingRegistry.getAlloyMap().get(metal);
				ItemStack stack = new ItemStack(this);
				ItemStack stack2 = new ItemStack(RegisterItems.ALLOY_INGOT);
				
				ItemAlloyIngot.setMoltenMaterials(stack2, metal.split("%")[0], metal.split("%")[1]);
				
				setMoltenMaterial(stack, stack2);
				items.add(stack);
			}
		}
    }
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(this.getMoltenStack(stack).getItem() == RegisterItems.ALLOY_INGOT && ItemAlloyIngot.getMoltenMaterial(this.getMoltenStack(stack)) != null)
		{
			Item[] item = ItemAlloyIngot.getMoltenMaterial(this.getMoltenStack(stack)).items;
			String str = I18n.translateToLocal(item[0].getUnlocalizedName() + ".name");
			String str2 = I18n.translateToLocal(item[1].getUnlocalizedName() + ".name");
			tooltip.add("alloy : " + str + " + " + str2);
		}
    }
	
	public int getColorMultiplier(ItemStack stack, int tintIndex)
    {
		if(this.getMoltenStack(stack).isEmpty())
		{
			return -1;
		}
		else
		{
			if(MeltingRegistry.getMeltingProperties().containsKey(this.getMoltenStack(stack).getItem()))
			{
				return MeltingRegistry.getMeltingProperties().get(this.getMoltenStack(stack).getItem()).color;
			}
			else if(this.getMoltenStack(stack).getItem() == RegisterItems.ALLOY_INGOT)
			{
				return ((ItemAlloyIngot) this.getMoltenStack(stack).getItem()).getColorMultiplier(this.getMoltenStack(stack), tintIndex);
			}
			else
			{
				return -1;
			}
		}
    }
}
