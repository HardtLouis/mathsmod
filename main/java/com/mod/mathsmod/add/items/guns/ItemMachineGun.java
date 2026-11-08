package com.mod.mathsmod.add.items.guns;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ISpecialRightClickItem;
import com.mod.mathsmod.add.items.guns.accessories.EnumAccessoryType;
import com.mod.mathsmod.add.items.guns.accessories.GunAccessoryBase;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public abstract class ItemMachineGun extends ItemGun
{

	public ItemMachineGun(String name) 
	{
		super(name);
	}

	@Override
	public int cooldown() 
	{
		return this.shootCadence();
	}
	
	public boolean lowBulletFar()
	{
		return true;
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

	protected abstract int shootCadence();

	@Override
	public boolean autoMod() 
	{
		return true;
	}
	
	public NonNullList<ItemStack> getModifiers(ItemStack stack)
    {
		if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
		
		/*System.out.print(stack.getTagCompound().getString("item1"));
		System.out.print(System.lineSeparator());
		System.out.print(stack.getTagCompound().getString("item2"));
		System.out.print(System.lineSeparator());
		System.out.print(stack.getTagCompound().getString("item3"));
		System.out.print(System.lineSeparator());*/
    	
    	NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    	
    	list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(stack.getTagCompound().getString("muffler")))));
    	list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(stack.getTagCompound().getString("scope")))));
    	list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(stack.getTagCompound().getString("launcher")))));
    	
    	return list;
    }
	
	public void updateChanges(ItemStack stack, NonNullList<ItemStack> list)
    {
		
    	if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
    	
    	stack.getTagCompound().setString("muffler", list.get(0).getItem().getRegistryName().toString());
    	stack.getTagCompound().setString("scope", list.get(1).getItem().getRegistryName().toString());
    	stack.getTagCompound().setString("launcher", list.get(2).getItem().getRegistryName().toString());
    }
	
	@Override
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
        {
    		if(!this.ChargerEmpty(stack) && stack.getItem() instanceof ItemGun && this.bulletUsed(stack, entityIn) != null)
    		{
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(stack, entityIn), this.soundPitch(worldIn));
				this.bulletUsed(stack, entityIn).shootEvent(worldIn, entityIn, (ItemGun) stack.getItem(), false, null);
				
				if(this.getReloading(stack))
	    		{
	    			this.setReloading(stack, false);;
	    		}
				
				if(!this.autoMod())
	    		{
	    			this.setReset(stack, false);
	    		}
				
				this.onShoot(entityIn);
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
    				if(this instanceof ItemShotGun)
    				{
    					for(ItemStack stack2 : ((EntityPlayer) entityIn).inventory.mainInventory)
    					{
    						if(stack2.getItem() instanceof ItemShotGun && stack2.getItem() != this)
    						{
    							((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack2.getItem(), 40);
    						}
    					}
    				}
    				
    				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(this, this.cooldown(stack, (EntityPlayer) entityIn));
    			}
            }
        }
    }

	@Override
	public boolean hasTravel()
	{
		return false;
	}
	
	public boolean canEquipModifier()
	{
		return false;
	}
	
	public boolean onlyMuffler()
	{
		return false;
	}
	
	public float innacuracyMultiplier(ItemStack stack, Entity entity)
    {
		float i = 1;
		if(this.canEquipModifier())
		{
			for(ItemStack s : this.getModifiers(stack))
			{
				if(!s.isEmpty() && s.getItem() instanceof GunAccessoryBase)
				{
					GunAccessoryBase item = (GunAccessoryBase) s.getItem();
					i *= item.innaccuracyMultiplier();
				}
			}
		}
		return i;
    }
    
    public int switchCooldownBonus(ItemStack stack, Entity entity)
    {
    	int i = 0;
    	for(ItemStack s : this.getModifiers(stack))
		{
			if(!s.isEmpty() && s.getItem() instanceof GunAccessoryBase)
			{
				GunAccessoryBase item = (GunAccessoryBase) s.getItem();
				i += item.switchCooldownBonus();
			}
		}
		return i;
    }
    
    public float zoomMultiplier(ItemStack stack, Entity entity)
    {
    	float i = 1;
		if(this.canEquipModifier())
		{
			for(ItemStack s : this.getModifiers(stack))
			{
				if(!s.isEmpty() && s.getItem() instanceof GunAccessoryBase)
				{
					GunAccessoryBase item = (GunAccessoryBase) s.getItem();
					i *= item.zoomMultiplier();
				}
			}
		}
    	return i;
    }
    
    protected float soundMultiplier(ItemStack stack, Entity entity)
    {
    	float i = 1;
    	if(this.canEquipModifier())
		{
			for(ItemStack s : this.getModifiers(stack))
			{
				if(!s.isEmpty() && s.getItem() instanceof GunAccessoryBase)
				{
					GunAccessoryBase item = (GunAccessoryBase) s.getItem();
					i *= item.soundMultiplier();
				}
			}
		}
    	return i;
    }
    
    @Override
	public float smoothMultiplier(ItemStack stack, Entity entity)
	{
    	float i = 1;
    	if(this.canEquipModifier())
		{
			for(ItemStack s : this.getModifiers(stack))
			{
				if(!s.isEmpty() && s.getItem() instanceof GunAccessoryBase)
				{
					GunAccessoryBase item = (GunAccessoryBase) s.getItem();
					i *= item.smoothMultiplier();
				}
			}
		}
    	return i;
	}

	@Override
	public float damageMultiplier(ItemStack stack, Entity entity, Entity target)
	{
		float i = super.damageMultiplier(stack, entity, target);
    	if(this.canEquipModifier())
		{
			for(ItemStack s : this.getModifiers(stack))
			{
				if(!s.isEmpty() && s.getItem() instanceof GunAccessoryBase)
				{
					GunAccessoryBase item = (GunAccessoryBase) s.getItem();
					i *= item.damageMultiplier();
				}
			}
		}
    	return i;
	}
	
	public boolean keepBaseScope(ItemStack stack) 
	{
		if(this.canEquipModifier() && !this.getModifiers(stack).get(2).isEmpty() && this.getModifiers(stack).get(2).getItem() instanceof GunAccessoryBase)
		{
			GunAccessoryBase item = (GunAccessoryBase) this.getModifiers(stack).get(2).getItem();
			
			if(this.getUsed(stack) != 0 && item.isScope())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}
	
}