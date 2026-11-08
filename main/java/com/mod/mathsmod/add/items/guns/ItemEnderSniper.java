package com.mod.mathsmod.add.items.guns;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnderSniper extends ItemGun implements IGunScope
{

	public ItemEnderSniper() {
		super("ender_sniper");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN.toString() + "allow player to switch position with shot entity");
		tooltip.add(TextFormatting.RED.toString() + "+150% slower reload");
		tooltip.add(TextFormatting.RED.toString() + "-10% damages");
    }

	@Override
	public int getMaxReloadTime() {
		return 50;
	}

	@Override
	public int getChargerSize() {
		return 1;
	}

	@Override
	public float zoomOnUsed() {
		return 13;
	}

	@Override
	public EnumBulletType getBullet() {
		return EnumBulletType.LONG;
	}

	@Override
	public int cooldown() {
		
		return 14;
	}

	@Override
	public float getBulletDrop()
	{
		return 0F;
	}

	@Override
	public float getPower() 
	{
		return 2F;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 7 * 0.9F * Mathsmod.SNIPERS_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) >= this.timeToFullScope() ? 0 : 45;
	}

	//TODO ANim

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.3141593F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, 0.7330383F, 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public boolean scopeToShoot() 
	{
		return true;
	}
	
	@Override
	protected float innacuracyOnShoot() 
	{
		return 0;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 0;
	}

	@Override
	protected float removeInnacuracyNumber()
	{
		return 0;
	}

	@Override
	public boolean lowBulletFar() 
	{
		return false;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 2));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 2));
		list.set(3, new ItemStack(Items.ENDER_PEARL));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_block"))));
		return list;
	}

	@Override
	public int getReach() 
	{
		return 100;
	}

	@Override
	public boolean autoMod() 
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getScope() 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/gui/sniper_scope.png");
	}

	@Override
	public boolean hadCustomGuiScope() 
	{
		return false;
	}
	
	/*public SoundEvent getShotSound()
    {
    	return SoundEvents.ENTITY_FIREWORK_LARGE_BLAST;
    }*/
    
    public float getSoundPower()
    {
    	return 2F;
    }
    
    public float soundPitch(World worldIn)
    {
    	return 1.5F;
    }

    public boolean changeMouseSensibilityOnScope()
    {
    	return true;
    }
    
    public boolean smoothScope()
	{
		return true;
	}
    
    public float smoothMultiplier()
	{
    	return 1.3F;
	}
    
    @Override
	public int timeToUse()
	{
		return 20;
	}
    
    @Override
    public boolean keepBaseScope(ItemStack stack) 
	{
		return false;
	}
    
	@Override
	public int timeToFullScope() 
	{
		return 3;
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
        {
    		if(!this.ChargerEmpty(stack) && stack.getItem() instanceof ItemGun && this.bulletUsed(stack, entityIn) != null && stack.getItemDamage() != this.getMaxDamage())
    		{
				this.bulletUsed(stack, entityIn).shootEvent(worldIn, entityIn, (ItemGun) stack.getItem(), false, null);
				
				if(!this.autoMod())
	    		{
	    			this.setReset(stack, false);
	    		}
				
				this.onShoot(entityIn);
				
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 40 * this.soundMultiplier(stack, entityIn), this.soundPitch(worldIn));
				if(!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					this.removeBulletInCharger(stack);
				}
				
				if(entityIn instanceof EntityPlayer)
				{
	    			this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
				}
				else
				{
	    			this.substractReload(stack, null, this.getReload(stack));
				}
				
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() <= this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.getShootDefault(stack) + this.innacuracyOnShoot());
    			}
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() > this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.maxInnacuracy());
    			}
    			if(entityIn instanceof EntityPlayer)
    			{
    				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), this.cooldown(stack, (EntityPlayer) entityIn));
    			}
            }
        }
    }
    
	public float HeadMultiplier()
	{
		return 3.0F;
	}
}
