package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.toolparts.EnumParts;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCustomSword extends ItemSword implements IItemSpecialRenderer, IReachWeapon
{
    protected static final UUID DEFENCE_MODIFIER = UUID.fromString("FA233E1C-8362-2834-B01B-BCCE9785ACA3");

    public ItemCustomSword()
    {
    	super(Item.ToolMaterial.IRON);
        this.setMaxDamage(0);
        this.setRegistryName("custom_sword").setUnlocalizedName("custom_sword");
    }
    
    public static NonNullList<ItemStack> getComponents(ItemStack stack)
    {
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
        	NonNullList<ItemStack> l = NonNullList.withSize(3, ItemStack.EMPTY);
        	return l;
    	}
    	else
    	{
        	NonNullList<ItemStack> l = NonNullList.withSize(3, ItemStack.EMPTY);
    		ItemStackHelper.loadAllItems(stack.getTagCompound(), l);
        	return l;
    	}
    }
    
    public static void setComponentsItems(ItemStack stack, ItemStack blade, ItemStack guard, ItemStack handle)
    {
    	NonNullList<ItemStack> l = NonNullList.withSize(3, ItemStack.EMPTY);
    	l.set(0, blade);
    	l.set(1, guard);
    	l.set(2, handle);
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	ItemStackHelper.saveAllItems(stack.getTagCompound(), l);
    }
    
    public int getMaxDamage(ItemStack stack)
    {
    	int total = 0;
    	NonNullList<ItemStack> list = this.getComponents(stack);
    	
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart && list.get(2).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		ItemToolPart item3 = (ItemToolPart) list.get(2).getItem();
    		
    		if(item1.part() == EnumParts.BLADE && item2.part() == EnumParts.GUARD && item3.part() == EnumParts.HANDLE)
    		{
    			total += item1.getMetal(list.get(0)).durability;
        		total += item2.getMetal(list.get(1)).durability;
        		total += item3.getMetal(list.get(2)).durability;
    		}
    	}
        return total / 3;
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
    	NonNullList<ItemStack> list = this.getComponents(stack);
    	float total = 1.0F;
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart && list.get(2).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		ItemToolPart item3 = (ItemToolPart) list.get(2).getItem();
    			total *= item1.efficiencyMultiplier();
        		total *= item2.efficiencyMultiplier();
        		total *= item3.efficiencyMultiplier();
    	}
        return super.getDestroySpeed(stack, state) * total;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	NonNullList<ItemStack> list = this.getComponents(stack);
    	float total = 1.0F;
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart && list.get(2).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		ItemToolPart item3 = (ItemToolPart) list.get(2).getItem();
    		item1.hitEntity(attacker, target, attacker.world, list.get(0), 0, 0);
    		item2.hitEntity(attacker, target, attacker.world, list.get(1), 1, 0);
    		item3.hitEntity(attacker, target, attacker.world, list.get(2), 2, 0);
    	}
    	
        stack.damageItem(1, attacker);
        return true;
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.WEB;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 4;
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName()
    {
        return "";
    }

    /**
     * Return whether this item is repairable in an anvil.
     *  
     * @param toRepair the {@code ItemStack} being repaired
     * @param repair the {@code ItemStack} being used to perform the repair
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
        
        float totalDef = 0.0F;
        float att = 0;
        
        NonNullList<ItemStack> list = this.getComponents(stack);
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart && list.get(2).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		ItemToolPart item3 = (ItemToolPart) list.get(2).getItem();
    		totalDef += item1.protectionBonus();
    		totalDef += item2.protectionBonus();
    		totalDef += item3.protectionBonus();
    		att = item1.damageMultiplier() * item2.damageMultiplier() * item3.damageMultiplier() * item1.getMetal(list.get(0)).attack_damages;
    	}
    	
    	if(slot == EntityEquipmentSlot.MAINHAND)
    	{
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 3 + att, 0));
            multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(DEFENCE_MODIFIER, "Weapon modifier", totalDef, 0));    
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", Double.POSITIVE_INFINITY, 0));
    	}

        return multimap;
    }

	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick) 
	{
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		for(ItemStack stack2 : this.getComponents(stack))
		{
			Minecraft.getMinecraft().getRenderItem().renderItem(stack2, TransformType.NONE);
		}
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
	}

	@Override
	public float bonusReach(EntityPlayer entity, ItemStack stack) 
	{
		float br = 0;
		NonNullList<ItemStack> list = this.getComponents(stack);
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart && list.get(2).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		ItemToolPart item3 = (ItemToolPart) list.get(2).getItem();
    		br += item1.reachBonus(list.get(0));
    		br += item2.reachBonus(list.get(1));
    		br += item3.reachBonus(list.get(2));
    	}
    	
		return br;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		NonNullList<ItemStack> l = this.getComponents(stack);
		tooltip.add("reach bonus : " + (this.bonusReach(Minecraft.getMinecraft().player, stack)));
		for(int i = 0; i < 3; ++i)
		{
			if(l.get(i).getItem() instanceof ItemToolPart)
			{
				ItemToolPart tp = (ItemToolPart) l.get(i).getItem();
				String part = "Blade";
				if(i == 1)
				{
					part = "guard";
				}
				else if(i == 2)
				{
					part = "handle";
				}
				tooltip.add(part + " : " + tp.getMetalString(l.get(i)).replace("%", " + "));
			}
		}
    }

	@Override
	public float bonusReach(EntityPlayer entity) 
	{
		return 0;
	}
}