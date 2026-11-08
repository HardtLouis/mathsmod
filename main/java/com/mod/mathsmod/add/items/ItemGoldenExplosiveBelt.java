package com.mod.mathsmod.add.items;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;
import com.mod.mathsmod.add.items.ItemArmor.ItemUseableArmors;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelExplosiveBelt;
import com.mod.mathsmod.add.messages.MessageTempAnim;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGoldenExplosiveBelt extends ItemExplosiveBelt implements IGunCraft
{
	private static final UUID[] ARMOR_MODIFIERS = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

	public ItemGoldenExplosiveBelt() 
	{
		this.setRegistryName("golden_explosive_belt");
		this.setUnlocalizedName("golden_explosive_belt");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if(this.getUsed(itemStack) > 0)
		{
			this.setUsed(itemStack, this.getUsed(itemStack) - 1);
		}
	}
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelExplosiveBelt model = new ModelExplosiveBelt(_default, 0.1F);
		
		model.Shape1.showModel = true;
		model.Shape2.showModel = true;
		model.Shape3.showModel = true;
		model.Shape4.showModel = true;
		model.Shape5.showModel = true;
		model.Shape6.showModel = true;
		model.Shape7.showModel = true;
		model.Shape8.showModel = true;
		model.bipedBody.showModel = true;
		
        return model;
    }

	@Override
	public void onExplUsed(ItemStack stack, World world, EntityLivingBase player)
	{
		player.addPotionEffect(new PotionEffect(RegisterPotions.EXPLOSIVE_MAN, 1000000));

		if(!world.isRemote)
		{
			Mathsmod.network.sendToAll(new MessageTempAnim(player, 1));
		}
	}
	
	public void setUsed(ItemStack stack, int used) 
	{
        if (!stack.hasTagCompound()) 
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("usingTicks", used);
    }
    
    public static int getUsed(ItemStack stack) 
    {
        if (!stack.hasTagCompound())
        {
            return 0;
        }

        if (!stack.getTagCompound().hasKey("usingTicks")) {

        	stack.getTagCompound().setInteger("usingTicks", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("usingTicks");
    }
    
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType)
        {
            //multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", (double)this.damageReduceAmount, 0));
            //multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", (double)this.toughness, 0));
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", 0.07, 0));
        	multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor modifier", -1, 0));
        }

        return multimap;
    }
    
    @SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
    	return Mathsmod.MODID + ":textures/models/armor/golden_explosive_belt.png";
    }

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(RegisterItems.EXPLOSIVE_BELT));
		list.set(1, new ItemStack(RegisterItems.THORIUM_INGOT, 2));
		return list;
	}

	@Override
	public int size()
	{
		return 1;
	}

	@Override
	public boolean displayRecipe() 
	{
		return true;
	}

}
