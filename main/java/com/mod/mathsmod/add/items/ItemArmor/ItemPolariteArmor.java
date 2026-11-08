package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelPolariteArmor;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelThoriumArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPolariteArmor extends ItemStatArmor implements IExplosionProtection
{

	public ItemPolariteArmor(EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(RegisterItems.POLARITE, 0, equipmentSlotIn, 1, 1, 0.1F, 5, 0.96F, 2, 7, name);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if(this.getEquipmentSlot() == EntityEquipmentSlot.FEET)
		{
			tooltip.add(TextFormatting.GREEN + "can stomp ennemies (high fall)");
		}
    }
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemPolariteArmor && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemPolariteArmor && 
			player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemPolariteArmor && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemPolariteArmor)
		{
			
			if(!(player.getHeldItemMainhand().getItem() instanceof ItemAxe))
			{
				player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3, 1));
			}
			
			if(player.isInWater())
			{
				player.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 0)));
			}
			
			if(player.getFoodStats().getFoodLevel() <= 6)
			{
				player.getFoodStats().setFoodLevel(40);
			}
			
		}
		
		if(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemPolariteArmor)
		{
		}
		
	}
	
	@SuppressWarnings("incomplete-switch")
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelPolariteArmor chest = new ModelPolariteArmor(0.7F, _default);
		ModelPolariteArmor rest = new ModelPolariteArmor(0.5F, _default);
		ModelPolariteArmor legs = new ModelPolariteArmor(0.25F, _default);
		
		switch(armorSlot)
		{
			case CHEST:
				chest.slot = armorSlot;
				return chest;
			case FEET:
				rest.slot = armorSlot;
				return rest;
			case HEAD:
				legs.slot = armorSlot;
				return legs;
			case LEGS:
				legs.slot = armorSlot;
				return legs;
		}
		
		return chest;
    }

	@Override
	public float explosionMultiplier(EntityLivingBase entity, Item item, ItemStack stack) 
	{
		return 0.90F;
	}
}
