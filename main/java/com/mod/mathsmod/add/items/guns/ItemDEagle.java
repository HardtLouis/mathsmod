package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDEagle extends ItemGun implements IItemSpecialRenderer
{

	public ItemDEagle() {
		
		super("desert_eagle");
	}
	
	public float HeadMultiplier()
	{
		return 2.3F;
	}

	@Override
	public int cooldown() {
		
		return 25;
	}

	@Override
	public float zoomOnUsed() {
		
		return 0.85F;
	}

	@Override
	public int getMaxReloadTime() {
		
		return 50;
	}

	@Override
	public int getChargerSize() {
		
		return 6;
	}

	@Override
	protected float innacuracyOnShoot() {
		
		return 7;
	}

	@Override
	protected int maxInnacuracy() {
		
		return 10;
	}

	@Override
	protected float removeInnacuracyNumber() {
		
		return 0.5F;
	}

	@Override
	public float getBulletDrop() {
		
		return 0;
	}

	@Override
	public float getPower() {
		
		return 8;
	}

	@Override
	public float getDamagesCaused() {
		
		return 6 * Mathsmod.SNIPERS_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) != 0 && this.userNotMoving(entity) ? this.getUsed(stack) > 19 ? 0F : 2F : 4F;
	}

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
	public boolean scopeToShoot() {
		
		return true;
	}

	@Override
	public boolean lowBulletFar() {
		
		return true;
	}

	@Override
	public EnumBulletType getBullet() {
		
		return EnumBulletType.LONG;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() {
		
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(5, new ItemStack(Blocks.LEVER));
		
		return list;
	}

	@Override
	public int getReach() 
	{
		return 100;
	}

	@Override
	public boolean autoMod() 
	{
		return false;
	}

	@Override
	public boolean hasTravel()
	{
		return false;
	}

	public float handMovementMultiplicator()
    {
    	return 3F;
    }
	
	@Override
	public int timeToUse()
	{
		return 20;
	}
	
	public boolean isPistol()
    {
    	return true;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/deagle/deagle.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}
