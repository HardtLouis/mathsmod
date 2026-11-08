package com.mod.mathsmod.add.items.guns;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelThompson;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemThompson extends ItemMachineGun implements IItemSpecialRenderer
{

	public ItemThompson() 
	{
		super("thompson");
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(6, new ItemStack(Item.getItemFromBlock(Blocks.IRON_BLOCK)));
		list.set(7, new ItemStack(Items.STICK));
		return list;
	}

	@Override
	protected int shootCadence() 
	{
		return 2;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 50;
	}

	@Override
	public int getChargerSize() 
	{
		return 50;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 1.7F;
	}

	@Override
	protected int maxInnacuracy()
	{
		return 5;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.6F;
	}

	@Override
	public float getBulletDrop()
	{
		return 0;
	}

	@Override
	public float getPower() 
	{
		return 3;
	}

	@Override
	public float getDamagesCaused()
	{
		return 2.625F * 0.7F * Mathsmod.SMG_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entity) ? 1 : 3 : 6;
	}

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

		float i = !this.getReloading(stack) ? 1 : 0.6F;
		return new ThreeFloat(-1.770796F * y, 0.4717861F * y * i, 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, 0.09F, 0.9F, -90, 0, 0);
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
		
		return this.getReloading(stack) ? null : this.Animated(stack, player) ? new armsLoRo(-0.6F, -0.12F, 0.0F, -88 - y, -11, -i - 11) : null;
	}

	@Override
	public boolean scopeToShoot() 
	{
		return true;
	}

	@Override
	public int getReach() 
	{
		return 40;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.NORMAL;
	}

	public float handMovementMultiplicator()
    {
    	return 0.8F;
    }
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.THOMPSON_SOUND;
	}
	
	@Override
	public float soundPitch(World worldIn)
    {
    	return 1F;
    }
	
	@Override
	public void onShoot(EntityLivingBase entity)
	{
    	entity.rotationPitch -= new Random().nextFloat() * 2 - 1F;
    	entity.rotationYaw -= new Random().nextFloat() * 2 - 1F;
	}
	
	@Override
	public int timeToUse()
	{
		return 3;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTicks) 
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		boolean flag = this.getReloading(stack);
		float progress = 0;
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/thompson/thompson.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTicks);
		
		IBakedModel bakedClip = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/thompson/thompson_mag.json"), "normal"));
		
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
