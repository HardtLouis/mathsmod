package com.mod.mathsmod.add.items.guns;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.accessories.IRenderAccessories;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelAK47;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.stats.ICustomGunStats;
import com.mod.mathsmod.add.items.guns.stats.statBase;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAK47 extends ItemMachineGun implements IItemSpecialRenderer, IRenderAccessories
{

	public ItemAK47()
	{
		super("AK47");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + " +25% damages");
		tooltip.add(TextFormatting.GREEN + " -25% base inaccuracy");
		tooltip.add(TextFormatting.GREEN + " can headshot");
		tooltip.add(TextFormatting.RED + " -17% clip size");
		tooltip.add(TextFormatting.RED + " -66% firing speed");
		tooltip.add(TextFormatting.RED + " +33% recoil");
    }

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(6, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		return list;
	}

	@Override
	public float zoomOnUsed() {
		return 1.2F;
	}

	@Override
	public int getMaxReloadTime() 
	{
		
		return 50;
	}

	@Override
	public int getChargerSize() {
		
		return 25;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 3F;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 4;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.4F;
	}

	@Override
	public float getBulletDrop() 
	{
		return 0.3F;
	}

	@Override
	public float getPower() 
	{
		return 4.5F;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 2F * 1.25F * Mathsmod.AR_MULTIPLIER;
	}
	
	public float HeadMultiplier()
	{
		return 1.3F;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) != 0 ? this.userNotMoving(entity) ? 0F : 1.5F : 6F;
	}

	//TODO  ANIM
	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.4461433F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		float y = 1;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload != 0.0F)
			{
				float yee = reload - 0.5F;

				if(reload >= 0.5F)
				{
					y = (yee - 0.5F) * 2;
				}
				if(reload <= 0.5)
				{
					y = 1 - (reload * 2);
					
					if(reload > 0.2F)
					{
						y *= 0.8F;
					}
				}
			}
		}
		else
		{
			if(this.getCooldownTime(stack, partialTicks, entity) <= 0.25 && this.getCooldownTime(stack, partialTicks, entity) != 0)
			{
			}
		}

		return new ThreeFloat(-1.770796F * y, 0.4717861F * y, 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.08F, 0.6F, -90, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		float i = 0;
		float y = 0;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload >= 0.5F)
			{
				y = (reload - 1F) * -2F;
			}
			if(reload <= 0.5F)
			{
				y = reload * 2F;
			}
			
			y *= 88 * 1.5F;
		}
		else
		{
		}
		
		return this.Animated(stack, player) ? new armsLoRo(-0.6F, -0.12F, 0.0F, -88 - y, -11, -i - 11) : null;
	}

	@Override
	public boolean scopeToShoot() {
		
		return true;
	}

	@Override
	public boolean lowBulletFar() {
		
		return true;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.NORMAL;
	}
	
	public SoundEvent getShotSound()
    {
    	return MathsSounds.AR15;
    }
    
	@Override
	public float soundPitch(World worldIn)
    {
    	return 0.8F;
    }
	
    public float getSoundPower()
    {
    	return 1F;
    }

	@Override
	public int getReach() 
	{
		return 90;
	}

	@Override
	public boolean autoMod() 
	{
		return true;
	}
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}
	
	@Override
	public void onShoot(EntityLivingBase entity)
	{
		entity.rotationPitch -= 2;
	}

	@Override
	protected int shootCadence()
	{
		return 5;
	}
	
	@Override
	public int timeToUse()
	{
		return 8;
	}
	
	public float handMovementMultiplicator()
    {
    	return 0.3F;
    }
	
	public boolean canEquipModifier()
	{
		return true;
	}
	
	public boolean onlyMuffler()
	{
		return false;
	}

	@Override
	public Vec3d scopeLocation(ItemStack stack)
	{
		return new Vec3d(0, 2.9, 0);
	}

	@Override
	public Vec3d mufflerLocation(ItemStack stack) 
	{
		return new Vec3d(0, 2, -2.95);
	}

	@Override
	public Vec3d lampLocation(ItemStack stack)
	{
		return new Vec3d(0, 1.9, -1);
	}

	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTicks)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		RenderItem ir = Minecraft.getMinecraft().getRenderItem();
		
		boolean flag = this.getReloading(stack);
		float progress = 0;
		
		for(int i = 0; i < 3; ++i)
		{
			if(this.getModifiers(stack).get(i) != null && !this.getModifiers(stack).get(i).isEmpty())
			{
				Vec3d trans = this.mufflerLocation(stack);
				if(i == 1)
				{
					trans = this.scopeLocation(stack);
				}
				else if(i == 2)
				{
					trans = this.lampLocation(stack);
				}
				GlStateManager.translate(0.5, 0, 0.5);
				GlStateManager.scale(0.2, 0.2, 0.2);
				GlStateManager.translate(-0.5, 0, -0.5);
				GlStateManager.translate(trans.x, trans.y, trans.z);
				RenderLaser.renderModelM(stack, ir.getItemModelMesher().getItemModel(this.getModifiers(stack).get(i)));
				GlStateManager.translate(-trans.x, -trans.y, -trans.z);
				GlStateManager.translate(0.5, 0, 0.5);
				GlStateManager.scale(5, 5, 5);
				GlStateManager.translate(-0.5, 0, -0.5);
			}
		}
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/ak47/ak47.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTicks);
		
		float cooldown = 0;
		if(entity instanceof EntityPlayer)
		{
			cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, partialTicks);
		}
		
		if(cooldown != 0)
		{
			if(cooldown > 0.5F)
			{
				GlStateManager.translate(0, 0, 0 - (1F - cooldown) * -0.42);
			}
			else
			{
				GlStateManager.translate(0, 0, 0 - (cooldown) * -0.42);
			}
		}
		
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/ak47/ak47_lev.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTicks);
		
		if(cooldown != 0)
		{
			if(cooldown > 0.5F)
			{
				GlStateManager.translate(0, 0, 0 + (1F - cooldown) * -0.42);
			}
			else
			{
				GlStateManager.translate(0, 0, 0 + (cooldown) * -0.42);
			}
		}
		
		IBakedModel bakedClip = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/ak47/ak47_mag.json"), "normal"));
		
		if(flag)
		{
			progress = MathHelper.clamp(((float)this.getReload(stack) + partialTicks) / this.getMaxReloadTime(), 0, 1);
			
			if(progress > 0.5F)
			{
				GlStateManager.translate(0, (0.5F - (progress - 0.5F)) * -33, 0);
			}
			else
			{
				GlStateManager.translate(0, 0 - (progress) * 33, 0);
			}
		}
		
		if(progress < 0.15F || progress > 0.85F || !flag)
		{
			RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedClip, 0, partialTicks);
		}
	}
}
