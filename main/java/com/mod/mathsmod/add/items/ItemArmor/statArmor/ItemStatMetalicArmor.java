package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;
import com.mod.mathsmod.add.items.ItemArmor.IExplosionProtection;
import com.mod.mathsmod.add.items.metaltools.ItemMetalicArmor;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemStatMetalicArmor extends ItemMetalicArmor implements IBulletProtection
{
	
	int color;
	private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	private final double attack;
	private final double defense;
	private final float bulletMultiplier;
	private final double speed;
	private final double magic;
	private final int tier;
	private final ItemArmor.ArmorMaterial material;

	public ItemStatMetalicArmor(ItemArmor.ArmorMaterial matIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, double bonusAttack, double bonusDefense, double bonusSpeed, float bulletMultiplierIn, double bonusMagic, int tierIn, String name) {
		super(name, equipmentSlotIn);
		attack = bonusAttack;
		defense = bonusDefense;
		speed = bonusSpeed;
		magic = bonusMagic;
		tier = tierIn;
		bulletMultiplier = bulletMultiplierIn;
		material = matIn;
	}
	
	public int getMaxDamage(ItemStack stack)
    {
		if (this.getMetal(stack) != null)
        {
			float mult = 2.0F;
			if(this.getEquipmentSlot() == EntityEquipmentSlot.HEAD)
			{
				mult = 1.2F;
			}
			else if(this.getEquipmentSlot() == EntityEquipmentSlot.FEET)
			{
				mult = 0.9F;
			}
			else if(this.getEquipmentSlot() == EntityEquipmentSlot.LEGS)
			{
				mult = 1.8F;
			}
			return (int) (this.getMetal(stack).durability * mult);
        }
		else
		{
			return 0;
		}
    }
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(this.getCreativeTab() == tab)
		{
			for(Item metal : MeltingRegistry.getMeltingProperties().keySet())
			{
				ItemStack stack = new ItemStack(this);
				setMetal(stack, metal);
				items.add(stack);
			}
			
			for(String metal : MeltingRegistry.getAlloyMap().keySet())
			{
				alloy al = MeltingRegistry.getAlloyMap().get(metal);
				ItemStack stack = new ItemStack(this);
				
				setAlloyFromString(stack, metal);
				items.add(stack);
			}
		}
    }
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		if(type != null)
		{
			return Mathsmod.MODID + ":textures/models/armor/stats/trans.png";
		}
		if(material.getName().split(":").length > 1)
		{
			String str = String.format("%s:textures/models/armor/stats/%s_layer_%d%s.png", Mathsmod.MODID, material.getName().split(":")[1], (slot == EntityEquipmentSlot.LEGS ? 2 : 1), type == null ? "" : String.format("_%s", type));
			return str;
		}
		else
		{
			String str = String.format("%s:textures/models/armor/stats/%s_layer_%d%s.png", Mathsmod.MODID, material.getName(), (slot == EntityEquipmentSlot.LEGS ? 2 : 1), type == null ? "" : String.format("_%s", type));
			return str;
		}
    }
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((this.bulletMultiplier < 1 ? (TextFormatting.GREEN) : (TextFormatting.RED + "+")) + ("" + ((int) ((this.bulletMultiplier * 100.0F - 100.0F)))) + "% damages from guns");
		if(this instanceof IExplosionProtection)
		{
			float expl = this.explosionMultiplier(null, this, stack);
			tooltip.add((expl < 1 ? (TextFormatting.GREEN) : (TextFormatting.RED + "+")) + ("" + ((int) ((expl * 100.0F - 100.0F)))) + "% damages from explosions");
		}
    }
	
	/*@Override
	public int getRGBDurabilityForDisplay(ItemStack stack)
    {
        
    }*/
	
	/*protected TextFormatting getColorByTier()
	{
		if(this.Tier() == 0)
		{
			return TextFormatting.GREEN;
		}
		if(this.Tier() == 1)
		{
			return TextFormatting.BLUE;
		}
		if(this.Tier() == 2)
		{
			return TextFormatting.RED;
		}
		if(this.Tier() == 3)
		{
			return TextFormatting.DARK_PURPLE;
		}
		if(this.Tier() == 4)
		{
			return TextFormatting.GOLD;
		}
		if(this.Tier() == 10)
		{
			int i = new Random().nextInt(10);
			if(i == 0)
			{
				if(color == 15)
				{
					color = 0;
				}
				else
				{
					color += 1;
				}
			}
			return TextFormatting.fromColorIndex(color);
		}
		else
		{
			return TextFormatting.BLACK;
		}
	}*/
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		super.onArmorTick(world, player, stack);
		
		this.Tick(world, player, stack);
	}

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (slot == this.armorType && this.getMetal(stack) != null)
        {
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Armor modifier", (double)this.getMetal(stack).armor_protection[armorType.getIndex()] + defense, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Attack modifier", (double)attack * 2, 0));
            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Armor toughness", (double)this.toughness, 0));
            multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Armor bonus health", (double)this.getMetal(stack).bonusHealth(), 0));
        }
        
        if(slot == EntityEquipmentSlot.FEET && this.armorType == EntityEquipmentSlot.FEET)
        {
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(ARMOR_MODIFIERS[slot.getIndex()], "Speed modifier", 0.01F + (0.0125F * (double)speed), 0));
        }

        return multimap;
    }
	
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
		}
		
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0, false, false));
		}
		
		if (this.getMetal(itemStack) != null)
        {
			this.getMetal(itemStack).onArmorUpdate(itemStack, player, armorType);
        }
	}

	@Override
	public float getBulletMultiplier() 
	{
		return bulletMultiplier;
	}
}