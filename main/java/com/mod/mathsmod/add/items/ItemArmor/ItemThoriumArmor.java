package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelThoriumArmor;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemThoriumArmor extends ItemStatArmor implements IExplosionProtection
{

	public ItemThoriumArmor(EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(RegisterItems.THORIUM, 0, equipmentSlotIn, 1, 1, 0.1F, 5, 0.96F, 1, 6, name);
	}
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemThoriumArmor && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemThoriumArmor && 
			player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemThoriumArmor && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemThoriumArmor)
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
		
		if(player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemThoriumArmor)
		{
			player.fallDistance = 0;
		}
		
	}
	
	@SuppressWarnings("incomplete-switch")
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelThoriumArmor chest = new ModelThoriumArmor(0.5F, _default);
		ModelThoriumArmor legs = new ModelThoriumArmor(0.25F, _default);
		
		switch(armorSlot)
		{
			case CHEST:
				chest.slot = armorSlot;
				return chest;
			case FEET:
				chest.slot = armorSlot;
				return chest;
			case HEAD:
				chest.slot = armorSlot;
				return chest;
			case LEGS:
				legs.slot = armorSlot;
				return legs;
		}
		
		return chest;
    }

	@Override
	public float explosionMultiplier(EntityLivingBase entity, Item item, ItemStack stack) 
	{
		return 0.95F;
	}
}
