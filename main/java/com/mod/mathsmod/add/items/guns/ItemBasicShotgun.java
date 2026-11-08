package com.mod.mathsmod.add.items.guns;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBasicShotgun;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBasicShotgun extends ItemGun implements ItemShotGun, IItemSpecialRenderer
{

	public ItemBasicShotgun() {
		super("shotgun");
	}

	@Override
	public int getBulletsSquareNumber() {
		
		return 3;
	}

	@Override
	public int cooldown() 
	{
		return 10;
	}
	
	public float soundPitch(World worldIn)
    {
    	return 1.3F;
    }
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.SHOTGUN_SOUND;
	}

	@Override
	public float zoomOnUsed() {
		return 1;
	}
	
	public boolean rechargeOneByOne()
	{
		return true;
	}

	@Override
	public int getMaxReloadTime() {
		return 20;
	}

	@Override
	public int getChargerSize() {
		return 6;
	}

	@Override
	public float getBulletDrop() {
		return 0;
	}

	@Override
	public float getPower() {
		return 1;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 9 * Mathsmod.SHOTGUN_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entity) ? 1.4F : 2 : 3.6F;
	}

	@Override
	public float getBuckshotDecal(EntityLivingBase entityIn, ItemStack stack) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entityIn) ? 3F : 5F : 8F;
	}

	//TODO ANIM
	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-1.570796F, -0.4461433F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		float i = 0;
		float y = 1;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload <= 0.4 && reload != 0)
			{
				if(reload >= 0.2F)
				{
					i = -25 * (reload - 0.4F) * 0.017453292F;
				}
				else
				{
					i = 25 * reload * 0.017453292F;
				}
			}
			else if(reload >= 0.5)
			{
				float yee = reload - 0.5F;
				if(yee < 0.3 && yee > 0.2)
				{
					y = 0;
				}
				if(yee >= 0.3F)
				{
					y = (yee - 0.2F) * 5;
				}
				if(yee <= 0.2)
				{
					y = 1 - (yee * 5);
				}
			}
		}
		else
		{
			if(this.getCooldownTime(stack, partialTicks, entity) <= 0.25 && this.getCooldownTime(stack, partialTicks, entity) != 0)
			{
				if(this.getCooldownTime(stack, partialTicks, entity) >= 0.125F)
				{
					i = (this.getCooldownTime(stack, partialTicks, entity) * 0.017453292F * 20);
				}
				else
				{
					i = (5 * 0.017453292F - (this.getCooldownTime(stack, partialTicks, entity) * 0.017453292F * 20));
				}
			}
		}
		
		return new ThreeFloat(-1.570796F * y, 0.3717861F * y - (0.017453292F * 6) + i, 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.45F, -0.4F, 0.4F, -88, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		float i = 0;
		float y = 0;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload <= 0.4 && reload != 0)
			{
				if(reload >= 0.2F)
				{
					i = -25 * (reload - 0.4F);
				}
				else
				{
					i = 25 * reload;
				}
			}
			else if(reload >= 0.5)
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
		else
		{
			if(this.getCooldownTime(stack, partialTicks, player) <= 0.5 && this.getCooldownTime(stack, partialTicks, player) != 0)
			{
				if(this.getCooldownTime(stack, partialTicks, player) >= 0.25)
				{
					i = (this.getCooldownTime(stack, partialTicks, player) * 10);
				}
				else
				{
					i = (5 - (this.getCooldownTime(stack, partialTicks, player) * 10));
				}
			}
		}
		
		return new armsLoRo(-0.55F, -0.345F, 0.0F, -88 + y, -11, -i - 11);
	}

	@Override
	public boolean scopeToShoot() {
		return false;
	}

	@Override
	public EnumBulletType getBullet() {
		
		return EnumBulletType.SHOTGUN;
	}
	
	@Override
	protected float innacuracyOnShoot() {
		
		return 6;
	}

	@Override
	protected int maxInnacuracy() {
		
		return 8;
	}

	@Override
	protected float removeInnacuracyNumber() {
		
		return 0.7F;
	}

	@Override
	public boolean lowBulletFar() {
		return false;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		
		return list;
	}

	@Override
	public int getReach() 
	{
		return 20;
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
		entity.rotationPitch -= 10;
	}
	
	@Override
	public int timeToUse()
	{
		return 6;
	}

	@Override
	public int stopPower()
	{
		return 7;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedCanon = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/basic_shotgun/basic_shotgun.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedCanon, 0, partialTick);

		float cooldown = 0;
		if(entity instanceof EntityPlayer)
		{
			cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, partialTick);
		}
		
		if(cooldown <= 0.5 && cooldown != 0)
		{
			if(cooldown >= 0.25F)
			{
				GlStateManager.translate(0, 0, 0 + (0.5F - cooldown) * 8 * 0.1);
			}
			else
			{
				GlStateManager.translate(0, 0, 0 + (cooldown * 8) * 0.1);
			}
			
		}
		
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/basic_shotgun/basic_shotgun_pump.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 2, partialTick);
	}
}
