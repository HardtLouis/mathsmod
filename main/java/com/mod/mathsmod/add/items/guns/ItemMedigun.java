package com.mod.mathsmod.add.items.guns;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemMedigun extends ItemGun
{
	protected static final UUID MEEEEDIC = UUID.fromString("845DB27C-C624-495F-8C9F-4022B5A58B6F");
	
	public ItemMedigun()
	{
		super("medigun");
		this.setCreativeTab(Mathsmod.MATHSMOD_GUNS_TAB);
	}
	
	public ItemMedigun(String name)
	{
		super(name);
		this.setCreativeTab(Mathsmod.MATHSMOD_GUNS_TAB);
	}
	
	public int getTargetID(ItemStack stack)
	{
		
        if (!stack.hasTagCompound()) 
        {
            return -1;
        }

        if (!stack.getTagCompound().hasKey("tid"))
        {
        	stack.getTagCompound().setFloat("tid", 0);
            return -1;
        }

        return stack.getTagCompound().getInteger("tid");
	}
	
	public void setTargetID(ItemStack stack, int d) 
	{
		if (!stack.hasTagCompound()) 
		{
            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("tid", d);
    }
	
	public int getUber(ItemStack stack)
	{
		
        if (!stack.hasTagCompound()) 
        {
            return 0;
        }

        if (!stack.getTagCompound().hasKey("uber"))
        {
        	stack.getTagCompound().setFloat("uber", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("uber");
	}
	
	public void setUber(ItemStack stack, int d) 
	{
		if (!stack.hasTagCompound()) 
		{
            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("uber", d);
    }
	
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		this.setUsed(playerIn.getHeldItemMainhand(), ticks);
    }
	
	public int uberTime()
	{
		return 800;
	}
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		if(this.getUber(entityIn.getHeldItemMainhand()) >= this.uberTime())
		{
			this.setUber(entityIn.getHeldItemMainhand(), 0);
			entityIn.addPotionEffect(new PotionEffect(RegisterPotions.UBER, 160));
			entityIn.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 160));
			
			if(this.getTargetID(entityIn.getHeldItemMainhand()) >= 0 && worldIn.getEntityByID(this.getTargetID(entityIn.getHeldItemMainhand())) != null)
			{
				Entity ent = worldIn.getEntityByID(this.getTargetID(entityIn.getHeldItemMainhand()));
				if(ent instanceof EntityLivingBase)
				{
					((EntityLivingBase) ent).addPotionEffect(new PotionEffect(RegisterPotions.UBER, 160));
					((EntityLivingBase) ent).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 160));
				}
				
			}
		}
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	if(isSelected)
    	{
    		if(entityIn instanceof EntityLivingBase)
        	{
        		((EntityLivingBase) entityIn).heal(0.2F / 2F);
        	}
        	
        	if(this.getTargetID(stack) >= 0 && this.getUsed(stack) > 0)
        	{
        		if(this.getUsed(stack) >= 200)
        		{
        			this.setUsed(stack, 1);
        		}
        		
        		if(worldIn.getEntityByID(this.getTargetID(stack)) != null && worldIn.getEntityByID(this.getTargetID(stack)) instanceof EntityLivingBase)
        		{
        			EntityLivingBase entity = (EntityLivingBase) worldIn.getEntityByID(this.getTargetID(stack));
        			if(entity.getDistance(entityIn) < 10)
        			{
        				entity.heal(this.getEhanced(stack) ? 0.3F : 0.45F);
        				
        				if(this.getUber(stack) < this.uberTime())
        				{
        					this.setUber(stack, this.getUber(stack) + 1);
        				}
        				worldIn.spawnParticle(EnumParticleTypes.HEART, entity.posX, entity.posY + entity.height + 1, entity.posZ, 0, 0.1F, 0);
        				
        				/*if(new Random().nextInt(6) == 0)
        				{
        					for(float i = 0.2F; i < 1; i += 0.1F)
            				{
            					double x = entity.posX - entityIn.posX;
            					double y = entity.posY + entity.getEyeHeight() - entityIn.posY - entityIn.getEyeHeight();
            					double z = entity.posZ - entityIn.posZ;
            					
            					worldIn.spawnParticle(EnumParticleTypes.HEART, entityIn.posX + (x * i), entityIn.posY + (y * i), entityIn.posZ + (z * i), 0, 0, 0);
            				}
        				}*/
        			}
        		}
        	}
        	else if(this.getUsed(stack) > 0)
        	{
        		for(double a = 0; a < 10; a += 0.25)
        		{
        			Vec3d vec = UtilsVoids.laser(a, entityIn.rotationYaw, entityIn.rotationPitch).add(entityIn.getPositionVector()).addVector(0, entityIn.getEyeHeight(), 0);
        			
        			if(UtilsVoids.getHitEntities(worldIn, entityIn, vec, 10).size() > 0)
        			{
        				Entity entity = UtilsVoids.getHitEntities(worldIn, entityIn, vec, 10).get(0);
        				if(a % 1 == 0 && entity != entityIn)
        				{
        					worldIn.spawnParticle(EnumParticleTypes.HEART, vec.x, vec.y, vec.z, 0, 0, 0);
        				}
        				if(entity instanceof EntityLivingBase && entity != entityIn)
        				{
        					this.setTargetID(((EntityLivingBase) entityIn).getHeldItemMainhand(), entity.getEntityId());
        				}
        			}
        		}
        	}
        	else
        	{
        		this.setTargetID(stack, -1);
        	}
    	}
    	else
    	{
    		if(entityIn instanceof EntityPlayer)
    		{
    			if(((EntityPlayer) entityIn).isPotionActive(RegisterPotions.UBER) || (((EntityPlayer) entityIn).getHeldItemMainhand().getItem() instanceof ItemGun && !(((EntityPlayer) entityIn).getHeldItemMainhand().getItem() instanceof IMedicWeapons)))
    			{
    				this.setUsed(stack, 0);
        			EntityPlayer player = (EntityPlayer) entityIn;
        			if(player.inventory.isHotbar(itemSlot))
        			{
        				player.inventory.currentItem = itemSlot;
        			}
        			else
        			{
        				if(((EntityPlayer) entityIn).isPotionActive(RegisterPotions.UBER) && !(player.getHeldItemMainhand().getItem() instanceof ItemMedigun))
        				{
        					((EntityPlayer) entityIn).removeActivePotionEffect(RegisterPotions.UBER);
        				}
        			}
    			}
    		}
    		
    		this.setTargetID(stack, -1);
    	}
    }
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(MEEEEDIC, "Medigun", -2F, 0));
        multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(MEEEEDIC, "Medigun", 0.004F, 0));
        return multimap;
    }

	@Override
	public NonNullList<ItemStack> Craft() {
		return null;
	}

	@Override
	public int cooldown() {
		return 0;
	}

	@Override
	public float zoomOnUsed() {
		return 0;
	}

	@Override
	public int getMaxReloadTime() {
		return 0;
	}

	@Override
	public int getChargerSize() {
		return 0;
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
		return 0;
	}

	@Override
	public float getDamagesCaused() {
		return 0;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) {
		return 0;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.4461433F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, 0.3717861F, 0F);
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
	public int timeToUse() {
		return 3;
	}

	@Override
	public boolean autoMod() {
		return false;
	}

	@Override
	public boolean scopeToShoot() {
		return false;
	}

	@Override
	public boolean lowBulletFar() {
		return false;
	}

	@Override
	public int getReach() {
		return 10;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LONG;
	}
	
	@Override
	public boolean displayRecipe()
	{
		return false;
	}
}
