package com.mod.mathsmod.add.items.guns;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelHunterShotgun;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHunterShotGun extends ItemGun implements ItemShotGun, IItemSpecialRenderer
{
	public ItemHunterShotGun() 
	{
		super("hunter_shotgun");
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
		list.set(6, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(7, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		return list;
	}

	@Override
	public int getBulletsSquareNumber() 
	{
		return 3;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + " +30% damages");
		tooltip.add(TextFormatting.GREEN + " -33% dispersion");
		tooltip.add(TextFormatting.GREEN + " +100% faster firing speed");
		tooltip.add(TextFormatting.GREEN + " -30% reload time");
		tooltip.add(TextFormatting.GREEN + " +reload its clip at once");
		tooltip.add(TextFormatting.RED + " -66% clip size");
		tooltip.add(TextFormatting.RED + " +50% cooldown");
    }

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entity) ? 1.4F : 2 : 3.6F;
	}

	@Override
	public float getBuckshotDecal(EntityLivingBase entityIn, ItemStack stack) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entityIn) ? 2F : 3.2F : 5.4F;
	}

	@Override
	public int cooldown() 
	{
		return 5;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 0.9F;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 28;
	}

	@Override
	public int getChargerSize() 
	{
		return 2;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 20;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 20;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 4F;
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
		return 9 * 1.3F * Mathsmod.SHOTGUN_MULTIPLIER;
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
	public armsLoRo rightArmFirstPersonRotation(ItemStack s, EntityPlayer playerentity, float partialTicks)
	{
		return new armsLoRo(0.45F, -0.25F, 0.55F, -88, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack s, EntityPlayer playerentity, float partialTicks) 
	{
		float i = 0;
		float y = 0;
		if(this.getReload(s) != 0)
		{
			float reload = MathHelper.clamp((((float)this.getMaxReloadTime()) - ((float)this.getReload(s)) - partialTicks) / ((float)this.getMaxReloadTime()), 0, 1);
			
			if(reload <= 0.2)
			{
				if(reload >= 0.1F)
				{
					i = (reload * 25);
				}
				else
				{
					i = (5 - (reload * 25));
				}
			}
			if(reload >= 0.5)
			{
				if(reload >= 0.75F)
				{
					y = reload * 88 * 4F;
				}
				else
				{
					y = 88 - (reload * 88 * 4F);
				}
			}
		}
		
		return this.getReloading(s) ? null : new armsLoRo(-0.6F, -0.24F, 0.2F, -88 + y, -11, -i - 11);
	}

	@Override
	public boolean autoMod()
	{
		return false;
	}

	@Override
	public boolean scopeToShoot() 
	{
		return false;
	}

	@Override
	public boolean lowBulletFar()
	{
		return false;
	}

	@Override
	public int getReach()
	{
		return 20;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.SHOTGUN;
	}
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}
	
	@Override
	public void onShoot(EntityLivingBase entity)
	{
		entity.rotationPitch -= 5;
	}
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.HUNTER_SHOTGUN_SOUND;
	}
	
	@Override
	public int timeToUse()
	{
		return 6;
	}

	@Override
	public int stopPower()
	{
		return 15;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/hunter_shotgun/hunter_shotgun.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);
		
		float rel = 0;
		
		if(this.getReloading(stack))
		{
			rel = (((float) (this.getReload(stack)))) / this.getMaxReloadTime();
			if(rel >= 0.3F && rel <= 0.6F)
			{
				GlStateManager.rotate((rel - 0.6F) / 0.6F * 2 * 80, 1, 0, 0);
			}
			else if(rel <= 0.6F)
			{
				GlStateManager.rotate((rel) / 0.6F * 2 * -80, 1, 0, 0);
			}
		}
		
		IBakedModel bakedCanon = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/hunter_shotgun/hunter_shotgun_bal.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedCanon, 0, partialTick);
	}
}
