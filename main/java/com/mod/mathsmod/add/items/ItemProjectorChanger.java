package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemProjectorChanger extends Item
{
	public ItemProjectorChanger()
	{
		this.setRegistryName("projector_changer").setUnlocalizedName("projector_changer").setMaxStackSize(1).setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "hit an entity to be able to project it");
		tooltip.add(this.getEntity(stack));
    }
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if(entity instanceof MultiPartEntityPart)
		{
			if(((MultiPartEntityPart) entity).parent instanceof Entity)
			{
				entity = (Entity) ((MultiPartEntityPart) entity).parent;
			}
		}
		
		if(entity instanceof EntityLivingBase)
		{
			this.setEntity(stack, EntityList.getKey(entity) + "");	
		}
        return false;
    }
	
	public void setEntity(ItemStack stack, String used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setString("entity", used);

    }
    
    public static String getEntity(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return "";
        }

        if (!stack.getTagCompound().hasKey("entity")) {

        	stack.getTagCompound().setString("entity", "null");
            return "";
        }

        return stack.getTagCompound().getString("entity");
    }
	
}
