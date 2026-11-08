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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCarbine extends ItemGun implements IItemSpecialRenderer
{

	public ItemCarbine() 
	{
		super("carbine");
	}
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
		list.set(1, new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		return list;
	}

	@Override
	public int cooldown()
	{
		return 15;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 2;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 30;
	}

	@Override
	public int getChargerSize()
	{
		return 1;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 4;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 20;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.5F;
	}

	@Override
	public float getBulletDrop()
	{
		return 0.1F;
	}

	@Override
	public float getPower() 
	{
		return 2F;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 6F * Mathsmod.SNIPERS_MULTIPLIER;
	}
	
	public float HeadMultiplier()
	{
		return 3F;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return 15F - MathHelper.clamp(this.getUsed(stack), 0, 4) * 3.75F + (this.userNotMoving(entity) ? 0F : 1F);
	}

	//TODO ANIM

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-1.570796F, -0.3141593F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-1.570796F, 0.7330383F, 0F);
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
	public boolean autoMod()
	{
		return false;
	}

	@Override
	public boolean scopeToShoot() 
	{
		return true;
	}

	@Override
	public boolean lowBulletFar()
	{
		return false;
	}

	@Override
	public int getReach()
	{
		return 200;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.NORMAL;
	}
	
	public float soundPitch(World worldIn)
    {
    	return 4F;
    }
	
	public boolean smoothScope()
	{
		return true;
	}
	
	public float smoothMultiplier()
	{
		return 2.0F;
	}
	
	@Override
	public int timeToUse()
	{
		return 10;
	}

	public float getSoundPower()
    {
    	return 1F;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/carbine/carbine.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}
