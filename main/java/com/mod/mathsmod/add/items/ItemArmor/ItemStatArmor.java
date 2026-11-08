package com.mod.mathsmod.add.items.ItemArmor;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemStatArmor extends ItemMagicArmor implements IBulletProtection
{

	private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
	private final double attack;
	private final double defense;
	private final float bulletMultiplier;
	private final double speed;
	private final double magic;
	private final double health;
	private final int tier;
	
	public ItemStatArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, double bonusAttack, double bonusDefense, double bonusSpeed, double bonusHealth, float bulletMultiplierIn, double bonusMagic, int tierIn, String name) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		attack = bonusAttack * 0.5;
		defense = bonusDefense * 0.5;
		speed = bonusSpeed * 1;
		magic = bonusMagic;
		tier = tierIn;
		health = bonusHealth;
		bulletMultiplier = bulletMultiplierIn;
		this.setName(name);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void setName(String name)
	{
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

        if (equipmentSlot == this.armorType)
        {
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double)this.damageReduceAmount + defense * 2, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Attack modifier", (double)attack * 2, 0));
            multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Health modifier", (double)health, 0));
            multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double)this.toughness, 0));
        }
        
        if(equipmentSlot == EntityEquipmentSlot.FEET)
        {
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Speed modifier", 0.0005F + (0.001F * (double)speed), 0));
        }

        return multimap;
    }

	@Override
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
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add((this.bulletMultiplier < 1 ? (TextFormatting.GREEN) : (TextFormatting.RED + "+")) + ("" + ((int) ((this.bulletMultiplier * 100.0F - 100.0F)))) + "% damages from guns");
		if(this instanceof IExplosionProtection)
		{
			float expl = ((IExplosionProtection)this).explosionMultiplier(null, this, stack);
			tooltip.add((expl < 1 ? (TextFormatting.GREEN) : (TextFormatting.RED + "+")) + ("" + ((int) ((expl * 100.0F - 100.0F)))) + "% damages from explosions");
		}
    }

	@Override
	public float getBulletMultiplier() 
	{
		return bulletMultiplier;
	}
}
