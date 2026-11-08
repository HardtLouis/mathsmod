package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.config.MConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class ItemBazookb extends ItemGun implements IRocketLauncher
{

	public ItemBazookb() 
	{
		super("bazookb");
	}

	@Override
	public int cooldown() {
		return 40;
	}
	
	public void setLayer(ItemStack stack, ResourceLocation loc)
	{
	}
	
	public boolean rechargeOneByOne()
	{
		return true;
	}

	@Override
	public float zoomOnUsed() {
		return 1F;
	}

	@Override
	public int getMaxReloadTime() {
		return 40;
	}

	@Override
	public int getChargerSize() {
		return 4;
	}

	@Override
	protected float innacuracyOnShoot() {
		return 0;
	}

	@Override
	protected int maxInnacuracy() {
		return 0;
	}

	@Override
	protected float removeInnacuracyNumber() {
		return 0;
	}

	@Override
	public float getBulletDrop() {
		return 0;
	}

	@Override
	public float getPower() {
		return 0.1F;
	}

	@Override
	public float getDamagesCaused() {
		return 0;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return 0;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-90F * 0.017453292F, 0, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.02F, 0.7F, -90, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public boolean scopeToShoot() {
		return true;
	}

	@Override
	public boolean lowBulletFar() {
		return false;
	}

	@Override
	public EnumBulletType getBullet() {
		return EnumBulletType.LITTLE;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		return null;
	}

	@Override
	public int getReach() 
	{
		return 0;
	}

	@Override
	public boolean autoMod()
	{
		return false;
	}
	
	public boolean canBeUseByHunter()
    {
    	return false;
    }
	
	public float soundPitch(World worldIn)
    {
    	return 1.0F;
    }

	@Override
	public float explosionStrenght()
	{
		return 0F;
	}
	
	@Override
	public int timeToUse()
	{
		return 10;
	}
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
        {
    		if(this.getRecharges(stack) > 0)
    		{
    			worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(stack, entityIn), this.soundPitch(worldIn));
    			Vec3d vec = UtilsVoids.laser(3, entityIn.rotationYaw, entityIn.rotationPitch);
        		
        		//entity.knockBack(entity, 1, entity.posX + vec.x - entity.posX, entity.posZ + vec.z - entity.posZ);
        		
        		UtilsVoids.AttractEntity(entityIn.posX + vec.x, entityIn.posY + entityIn.getEyeHeight() + vec.y, entityIn.posZ + vec.z, entityIn, 10, this.getUsed(entityIn.getHeldItemMainhand()) != 0 ? -3.4 : -3);
                
        		//entity.rotationPitch -= 30;
        		
        		if(entityIn.motionY >= 0.2F)
        		{
        			entityIn.fallDistance = 0;
        		}
        		this.setRecharges(stack, this.getRecharges(stack) - 1);
        		
        		if(entityIn instanceof EntityPlayer)
    			{
    				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), this.cooldown(stack, (EntityPlayer) entityIn));
    			}
    		}
        }
    }
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
	    return (double)(this.getChargerSize() - this.getRecharges(stack)) / (double)this.getChargerSize();
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
	    return this.getRecharges(stack) < this.getChargerSize();
    }
	
	@Override
	public void onRightClickPressed(World worldIn, EntityPlayer playerIn, int ticks)
    {
    	ItemStack stack = playerIn.getHeldItemMainhand();
    	if(!worldIn.isRemote)
    	{
    		if(this.getRecharges(stack) == 0)
            {
    			this.setReloading(stack, true);
            }
            else
            {
            	this.setUsed(stack, ticks);
            	return;
            }
    	}
    }
	
	public void reloadGun(ItemStack stack, World worldIn, EntityLivingBase entityIn)
    {
    	if(this.getReloading(stack))
		{
    		if(entityIn instanceof EntityPlayer)
    		{
    			if(this.getMaxReloadTime(stack, (EntityPlayer) entityIn) <= this.getReload(stack))
    			{
    				for(int i = this.getRecharges(stack); i < this.getChargerSize(); ++i)
    				{
    					this.setRecharges(stack, this.getRecharges(stack) + 1);
    				}
    				this.setReloading(stack, false);
    				this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
    				return;
    			}
    			else
    			{
    				this.addReload(stack, (EntityPlayer) entityIn, 1);
    			}
    		}
    		else
    		{
    			if(this.getMaxReloadTime() <= this.getReload(stack))
    			{
    				for(int i = this.getRecharges(stack); i < this.getChargerSize(); ++i)
    				{
    					this.setRecharges(stack, this.getRecharges(stack) + 1);
    				}
    				this.setReloading(stack, false);
    			}
    		}
		}
	}
	
	public int getCharger(ItemStack stack) 
	{
    	return this.getRecharges(stack);
    }
	
	public NonNullList<ItemStack> getBulletList(ItemStack stack)
    {
    	NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(this.getChargerSize(), ItemStack.EMPTY);
    	return list;
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	if(!isSelected)
    	{
    		this.setUsed(stack, 0);
    		this.setTakeTime(stack, 0);
    		this.setShootDefault(stack, 0);
    	}
    	
    	if(isSelected)
    	{
    		if(entityIn instanceof EntityLivingBase)
    		{
    			if(this.getShootDefault(stack) > 0)
	        	{
	        		this.setShootDefault(stack, this.getShootDefault(stack) - removeInnacuracyNumber());
	        	}
	        	
	        	if(this.getShootDefault(stack) < 0)
	        	{
	        		this.setShootDefault(stack, 0);
	        	}
	        	
	        	if(this.getTakeTime(stack) < this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
	        	{
	        		this.addTakeTime(stack, 1);
	        	}
    		}
    	}
    	
    	if(entityIn instanceof EntityPlayer)
    	{
        	if(isSelected)
        	{
        		this.reloadGun(stack, worldIn, (EntityLivingBase) entityIn);
        		this.setCooldown(stack, ((EntityPlayer) entityIn).getCooldownTracker().getCooldown(this, 0.0F));
        		
        		if(this.getReloading(stack) && this.getRecharges(stack) >= this.getChargerSize())
        		{
        			this.setReloading(stack, false);
        		}
        	}
    	}
    }
	
	public void setRecharges(ItemStack stack, int used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("recharges", used);

    }
    
    public int getRecharges(ItemStack stack) 
    {
        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("recharges")) {

        	stack.getTagCompound().setInteger("recharges", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("recharges");
    }
	
	public float handMovementMultiplicator()
    {
    	return 4F;
    }
	
	@Override
	public boolean displayRecipe()
	{
		return false;
	}
}