package com.mod.mathsmod.add.items.guns;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelAA12;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAA12 extends ItemMachineGun implements ItemShotGun, IItemSpecialRenderer
{

	public ItemAA12() 
	{
		super("AA12");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + " +150% firing speed");
		tooltip.add(TextFormatting.GREEN + " +66% clip size");
		tooltip.add(TextFormatting.RED + " -35% damages");
		tooltip.add(TextFormatting.RED + " +30% reload time");
		tooltip.add(TextFormatting.RED + " shoot 16 pellets instead of 9");
    }

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entity) ? 3F : 5 : 7.6F;
	}

	@Override
	public float getBuckshotDecal(EntityLivingBase entityIn, ItemStack stack) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entityIn) ? 5F : 8F : 10F;
	}

	@Override
	public int cooldown()
	{
		return 4;
	}

	@Override
	public float zoomOnUsed() {
		
		return 0.85F;
	}

	@Override
	public int getMaxReloadTime() {
		
		return 39;
	}

	@Override
	public int getChargerSize()
	{	
		return 10;
	}

	@Override
	protected float innacuracyOnShoot() {
		
		return 2;
	}

	@Override
	protected int maxInnacuracy() {
		
		return 5;
	}

	@Override
	protected float removeInnacuracyNumber() {
		
		return 0.5F;
	}

	@Override
	public float getBulletDrop() {
		
		return 0.5F;
	}

	@Override
	public float getPower() {
		
		return 2;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 9 * 0.65F * Mathsmod.SHOTGUN_MULTIPLIER;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		
		return new ThreeFloat(-90F * 0.017453292F, -8.5F * 0.017453292F, 0);
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

		float i = !this.getReloading(stack) ? 1 : 0.9F;
		return new ThreeFloat(-90F * 0.017453292F * y, 57.5F * 0.017453292F * y * i, 0);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.3F, -0.085F, 0.72F, -90, 0, 0);
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
		
		return this.getReloading(stack) ? new armsLoRo(-0.85F, -0.1F, 0.6F, -88 - y, -15, -i - 15) : null;
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
	public EnumBulletType getBullet() {
		
		return EnumBulletType.SHOTGUN;
	}

	@Override
	public int getBulletsSquareNumber() 
	{
		return 4;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_block"))));
		list.set(6, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_block"))));
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
	
	public float handMovementMultiplicator()
    {
    	return 0.2F;
    }
	
	@Override
	public void onShoot(EntityLivingBase entity)
	{
		entity.rotationYaw += new Random().nextInt(7) - 3;
		entity.rotationPitch += new Random().nextInt(7) - 3;
	}
	
	public float soundPitch(World worldIn)
    {
    	return (1.2F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F;
    }
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.AA12_SOUND;
	}
	
	@Override
	public int timeToUse()
	{
		return 6;
	}

	@Override
	protected int shootCadence() 
	{
		return this.cooldown();
	}

	@Override
	public int stopPower() 
	{
		return 3;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/aa12/aa12.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);
		
		float cooldown = 0;
		if(entity instanceof EntityPlayer)
		{
			cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, partialTick);
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
		
		IBakedModel bakedCanon = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/aa12/aa12_lev.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedCanon, 0, partialTick);
	}
}
