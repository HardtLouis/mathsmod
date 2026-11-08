package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMythicalItem;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;
import com.mod.mathsmod.add.items.guns.stats.ICustomGunStats;
import com.mod.mathsmod.add.items.guns.stats.statBase;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.MDamageSource;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemInfinityGauntlet extends ItemSmasher implements ICustomGunStats, IBulletProtection
{
	public ItemInfinityGauntlet()
	{
		super(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB).setRegistryName("infinity_gauntlet").setUnlocalizedName("infinity_gauntlet").setMaxStackSize(1);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "yeah, it is bugged");
    }
	
	@Override
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		if(!worldIn.isRemote)
		{
			ItemStack stack = playerIn.getHeldItemMainhand();
			if(this.getUsed(stack) < 3 && ticks == 1)
			{
				if(!playerIn.onGround)
	    		{
	    			this.setPitch(stack, playerIn.rotationPitch);
	    			this.setYaw(stack, playerIn.rotationYaw);
	    			this.setUsed(stack, this.getUsed(stack) + 1);
	    			this.setAir(stack, true);
	    			playerIn.getCooldownTracker().setCooldown(this, 10);
	    			UtilsVoids.EasyShootEntityModifyY(playerIn, this.getPitch(stack), this.getYaw(stack), 5F, 0F);
	    			
	    			if(playerIn instanceof EntityPlayerMP)
	    			{
	    				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(playerIn.motionX * 3, playerIn.motionY, playerIn.motionZ * 3)), (EntityPlayerMP) playerIn);
	    			}
	    		}
	    		else
	    		{
	    			this.setPitch(stack, playerIn.rotationPitch);
	    			this.setYaw(stack, playerIn.rotationYaw);
	    			this.setUsed(stack, this.getUsed(stack) + 1);
	    			playerIn.onGround = false;
	    			this.setAir(stack, true);
	    			playerIn.getCooldownTracker().setCooldown(this, 10);
	    			UtilsVoids.EasyShootEntityModifyY(playerIn, this.getPitch(stack), this.getYaw(stack), 5F, 0F);
	    			
	    			if(playerIn instanceof EntityPlayerMP)
	    			{
	    				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(playerIn.motionX * 3, playerIn.motionY, playerIn.motionZ * 3)), (EntityPlayerMP) playerIn);
	    			}
	    		}
			}
		}
		
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(handIn == EnumHand.MAIN_HAND)
		{
			if(!playerIn.isSneaking())
			{
				for(double i = 0; i <= 20 * 2; ++i)
				{
					for(int x = -1; x <= 1; ++x)
					{
						for(int z = -1; z <= 1; ++z)
						{
							double t = i / 2;
							worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.HOSTILE, 2, (1.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F);
							Vec3d vec3d = UtilsVoids.laser(new Vec3d(t, x, z), playerIn.rotationYaw, playerIn.rotationPitch);
							
							BlockPos pos = new BlockPos(playerIn.posX + vec3d.x, playerIn.posY + playerIn.getEyeHeight() + vec3d.y, playerIn.posZ + vec3d.z);
							
							for(int g = 0; g < 3; ++g)
							{
								if(x == 0 && z == 0)
								{
									worldIn.spawnParticle(EnumParticleTypes.DRAGON_BREATH, playerIn.posX + vec3d.x, playerIn.posY + playerIn.getEyeHeight() + vec3d.y - 0.3D, playerIn.posZ + vec3d.z, 0, 0, 0);
								}
								
							}
							
							if(!worldIn.isRemote)
							{
								for(Entity entity : UtilsVoids.getEntities(worldIn, playerIn, 15))
			    				{
			    					if(entity != playerIn && entity instanceof EntityLivingBase && new BlockPos(entity).distanceSq(pos) < 2)
			    					{
			    						if(((EntityLivingBase)entity).hurtTime <= 0)
			    						{
			    							entity.attackEntityFrom(MDamageSource.causeInfinityDamage(playerIn), 10);
			    						}
			    					}
			    				}
								IBlockState state = worldIn.getBlockState(pos);
								/*if(state.getBlockHardness(worldIn, pos) < 4 && state.getBlockHardness(worldIn, pos) >= 0)
								{
									worldIn.destroyBlock(pos, false);
								}*/
							}
						}
					}
					
				}
				if(this.getUsed(stack) > 0 && !playerIn.onGround)
				{
					this.setUsed(stack, 3);
				}
				if(!playerIn.onGround)
				{
					playerIn.motionY = 0.1;
				}
				
			}
			if(playerIn.isSneaking())
			{
				if(!worldIn.isRemote && (this.getCooldown(stack) <= 0 || playerIn.capabilities.isCreativeMode))
				{
					for(Entity entity : UtilsVoids.getEntities(EntityLivingBase.class, worldIn, playerIn, 200))
					{
						if(entity != playerIn)
						{
							if(new Random().nextBoolean())
							{
								this.setCooldown(72000, stack);
								UtilsVoids.attackEntityFrom((EntityLivingBase) entity, MDamageSource.causeInfinityDamage(playerIn), Float.POSITIVE_INFINITY);
							}
						}
					}
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		
		if(entityIn instanceof EntityPlayer)
		{
			if(entityIn.onGround)
			{
				this.setUsed(stack, 0);
			}
			
			if(this.getCooldown(stack) > 0)
			{
				this.setCooldown(this.getCooldown(stack) - 1, stack);
			}
			if(isSelected)
			{
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.RESISTANCE, 20, 3));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.HASTE, 20, 1));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.FIRE_RESISTANCE, 20, 0));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.NIGHT_VISION, 400, 1));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.REGENERATION, 20, 4));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.FIRE_RESISTANCE, 20, 0));
				if(!((EntityPlayer) entityIn).isPotionActive(MobEffects.ABSORPTION))
				{
					((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.ABSORPTION, 600, 19));
				}
				
			}
			
			if(worldIn.isRemote)
			{
				if(itemSlot < 9 && !((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					((EntityPlayer) entityIn).inventory.currentItem = itemSlot;
				}
			}
			if(this.getUsed(stack) > 0 && this.getUsed(stack) < 3 && isSelected)
			{
				
			}
		}
    }
    
    public static void setSize(float height, Entity entity)
    {
         entity.height = height;

         AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox();
         entity.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, 0, axisalignedbb.minZ, axisalignedbb.minX + (double)entity.width, height, axisalignedbb.minZ + (double)entity.width));
    }
    
    
    
    public static void setCooldown(int cooldown, ItemStack stack)
    {
    	if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("cooldown", cooldown);
    }
    
    public int getCooldown(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("cooldown")) {

            return 0;
        }

        return stack.getTagCompound().getInteger("cooldown");
    }

	@Override
	public List<statBase> stats(ItemStack stack) 
	{
		List<statBase> list = Lists.newArrayList();
		
		list.add(new statBase("damages", 10));
		list.add(new statBase("snap cooldown", this.getCooldown(stack) / 20));
		
		return list;
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", Float.POSITIVE_INFINITY, 0));
        }

        return multimap;
    }
	
	public void onLanding(World worldIn, EntityLivingBase entityIn, ItemStack stack)
	{
		if(this.getUsed(stack) > 0)
		{
			this.setUsed(stack, 0);
			PerfectExplosion.EntityBoom(Blocks.AIR, entityIn, 4, true, true, false);
			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, worldIn, entityIn, 8))
			{
				if(entity != entityIn)
				{
					UtilsVoids.attackEntityFrom(entity, MDamageSource.causeInfinityDamage((EntityPlayer) entityIn), 30);
				}
			}
		}
	}
	
	public boolean hasCustomEntity(ItemStack stack)
    {
        return true;
    }
	
    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
    	EntityMythicalItem entity = new EntityMythicalItem(world, location.posX, location.posY, location.posZ, itemstack);
    	entity.motionX = location.motionX;
    	entity.motionY = location.motionY;
    	entity.motionZ = location.motionZ;
    	entity.setPickupDelay(20);
        return entity;
    }

	@Override
	public float getBulletMultiplier()
	{
		return 2.5F;
	}
}
