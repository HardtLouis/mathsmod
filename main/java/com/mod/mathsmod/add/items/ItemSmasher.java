package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSmasher extends Item implements ILeftClickItem
{
	public ItemSmasher()
	{
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB).setRegistryName("smasher").setUnlocalizedName("smasher").setMaxStackSize(1);
	}
	
	public ItemSmasher(int i)
	{
		
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "you have to use it to understand it");
    }
	
	@Override
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		if(!worldIn.isRemote && !playerIn.getCooldownTracker().hasCooldown(this))
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
	    			UtilsVoids.EasyShootEntityModifyY(playerIn, this.getPitch(stack), this.getYaw(stack), 4F, 0F);
	    			
	    			if(playerIn instanceof EntityPlayerMP)
	    			{
	    				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(playerIn.motionX, playerIn.motionY, playerIn.motionZ)), (EntityPlayerMP) playerIn);
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
	    			UtilsVoids.EasyShootEntityModifyY(playerIn, this.getPitch(stack), this.getYaw(stack), 4F, 0F);
	    			
	    			if(playerIn instanceof EntityPlayerMP)
	    			{
	    				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(playerIn.motionX, playerIn.motionY, playerIn.motionZ)), (EntityPlayerMP) playerIn);
	    			}
	    		}
			}
		}
		
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		
		if(entityIn instanceof EntityPlayer)
		{
			entityIn.motionX *= 0.9F;
			entityIn.motionZ *= 0.9F;
			//((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.RESISTANCE, 20, 1));
			if(worldIn.isRemote)
			{
				if(itemSlot < 9 && !((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					((EntityPlayer) entityIn).inventory.currentItem = itemSlot;
				}
			}
			if(this.getUsed(stack) > 0 && this.getUsed(stack) < 3 && isSelected)
			{
				UtilsVoids.EasyShootEntity(entityIn, this.getPitch(stack), this.getYaw(stack), 1F, 0F);
				if(this.getPitch(stack) <= 0 && this.getAir(stack))
				{
					entityIn.motionY = this.getPitch(stack) / -45;
					if(!((EntityPlayer) entityIn).getCooldownTracker().hasCooldown(this))
					{
						this.setAir(stack, false);
					}
				}
				
			}
			
			if(entityIn.onGround)
			{
				this.setUsed(stack, 0);
			}
		}
    }
	
	public void onLanding(World worldIn, EntityLivingBase entityIn, ItemStack stack)
	{
		if(this.getUsed(stack) > 0)
		{
			if(entityIn instanceof EntityPlayer)
			{
				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(this, 100);
			}
			
			this.setUsed(stack, 0);
			if(!entityIn.isSneaking() && this.getUsed(stack) < 3)
			{
				BlockPos a = new BlockPos(entityIn).down();
				
				for(int i = -5; i <= 5; ++i)
	    		{
	    			for(int j = -5; j <= 5; ++j)
	        		{
	    				World world = entityIn.world;
	    				
	    				BlockPos b = a.add(i, 0, j);
	    				BlockPos pos = b.add(0, world.getHeight(b.getX(), b.getZ()) - b.getY() - 1, 0);
	        			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5D, pos.getZ() + 0.5D, entityIn) < 6 && (i != 0 || j != 0))
	        			{
	        				IBlockState state = world.getBlockState(pos);
	        				if(state.getBlockHardness(world, pos) <= 10 && state.getBlockHardness(world, pos) >= 0
	        						&& world.getBlockState(pos.down()).isFullCube())
	        				{
	        					world.setBlockToAir(pos);
	        					EntityAbsorbedBlock entity = new EntityAbsorbedBlock(world, state, pos);
	        					entity.posY += 0.1F;
	        					entity.motionY = 0.05F * UtilsVoids.distanceIgnoreY(entity.posX, entity.posZ, entityIn);
	        					if(!world.isRemote)
	        					{
	            					world.spawnEntity(entity);
	        					}
	        				}
	        			}
	        		}
	    		}
				
				for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, worldIn, entityIn, 5))
				{
					if(entity != entityIn)
					{
						UtilsVoids.attackEntityFrom(entity, DamageSource.causePlayerDamage((EntityPlayer) entityIn), 20);
					}
				}
			}
		}
	}
	
	public float getPitch(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("Pitch")) {

            return 0;
        }

        return stack.getTagCompound().getFloat("Pitch");
    }
	
	public void setPitch(ItemStack stack, float rotationPitch) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setFloat("Pitch", rotationPitch);
    }
	
	public float getYaw(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("Yaw")) {

            return 0;
        }

        return stack.getTagCompound().getFloat("Yaw");
    }
	
	public void setYaw(ItemStack stack, float Yaw) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setFloat("Yaw", Yaw);
    }
	
	public void setUsed(ItemStack stack, int used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("used", used);

    }
    
    public static int getUsed(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("used")) {

        	stack.getTagCompound().setInteger("used", 2);
            return 0;
        }

        return stack.getTagCompound().getInteger("used");
    }
    
    public void setAir(ItemStack stack, boolean air) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("air", air);

    }
    
    public static boolean getAir(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("air")) {

        	stack.getTagCompound().setBoolean("air", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("air");
    }
    
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
        }

        return multimap;
    }
    
}
