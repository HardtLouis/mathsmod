package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGlock17 extends ItemGun implements IItemSpecialRenderer
{

	public ItemGlock17() 
	{
		super("glock17");
	}

	@Override
	public NonNullList<ItemStack> Craft() {
		
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_block"))));
		list.set(5, new ItemStack(Blocks.LEVER));
		
		return list;
	}

	@Override
	public int cooldown() 
	{
		return 2;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1.2F;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 20;
	}

	@Override
	public int getChargerSize() 
	{
		return 22;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 1.2F;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 3;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.2F;
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
		return 1.6F * 0.75F;
	}
	
	public float HeadMultiplier()
	{
		return 1.2F;
	}
	
	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) != 0 ? this.userNotMoving(entity) ? 0F : 1F : 5F;
	}

	//TODO Anim

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		
		return new ThreeFloat(-90 * 0.017453292F, -21 * 0.017453292F, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		
		return new ThreeFloat(-90 * 0.017453292F, 47 * 0.017453292F, 0);
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
		return EnumBulletType.LITTLE;
	}
	
	@Override
	public int timeToUse()
	{
		return 1;
	}
	
	public boolean isPistol()
    {
    	return true;
    }
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}
	
	@Override
	public boolean autoMod() 
	{
		return true;
	}
	
	public SoundEvent getShotSound()
    {
    	return MathsSounds.GLOCK_SOUND;
    }

	@Override
	public boolean lowBulletFar() 
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		boolean flag = this.getReloading(stack);
		float progress = 0;
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/pistol/pistol.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);
		
		float cooldown = 0;
		if(entity instanceof EntityPlayer)
		{
			cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, partialTick);
		}
		
		if(cooldown > 0.5F)
		{
			GlStateManager.translate(0, 0, 0 + (1F - cooldown) * 0.42 + 0.01);
		}
		else
		{
			GlStateManager.translate(0, 0, 0 + (cooldown) * 0.42 + 0.01);
		}
		
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/pistol/pistol_mec.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 3, partialTick);
	}

}
