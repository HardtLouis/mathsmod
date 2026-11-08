package com.mod.mathsmod.add.items.guns;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.superHitRenderer;
import com.mod.mathsmod.utils.BadExplosionS;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAntivehicleRocketLauncher extends ItemGun implements IRocketLauncher, IItemSpecialRenderer
{

	public ItemAntivehicleRocketLauncher() {
		super("antivehicle_rocket_launcher");
	}
	
	public float baseRockedSpeed()
	{
		return 2.1F;
	}

	@Override
	public int cooldown() {
		return 20;
	}

	@Override
	public float zoomOnUsed() {
		return 1F;
	}

	@Override
	public int getMaxReloadTime() {
		return 40;
	}

	@Override
	public int getChargerSize() 
	{
		return 1;
	}

	@Override
	protected float innacuracyOnShoot() {
		return 0;
	}

	@Override
	protected int maxInnacuracy() {
		return 0;
	}

	@Override
	protected float removeInnacuracyNumber() {
		return 0;
	}

	@Override
	public float getBulletDrop() {
		return 0;
	}
	
	public boolean rechargeOneByOne()
	{
		return true;
	}

	@Override
	public float getPower() {
		return 0.1F;
	}

	@Override
	public float getDamagesCaused() {
		return 20F * Mathsmod.RL_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return 0;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-90F * 0.017453292F, 0, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.02F, 0.7F, -90, 0, 0);
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
		return false;
	}

	@Override
	public EnumBulletType getBullet() {
		return EnumBulletType.ROCKET;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_block"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		
		return list;
	}

	@Override
	public int getReach() 
	{
		return 1000;
	}

	@Override
	public boolean autoMod()
	{
		return true;
	}
	
	public boolean canBeUseByHunter()
    {
    	return false;
    }
	
	public float soundPitch(World worldIn)
    {
    	return 1.0F;
    }
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		ItemStack stack = entityIn.getHeldItem(handIn);
		
		if(this.bulletUsed(stack, entityIn) != null && this.getUsed(stack) != 0)
		{
			super.onItemUsedByEntity(worldIn, entityIn, handIn);
		}
    }

	@Override
	public float explosionStrenght()
	{
		return 2F;
	}
	
	@Override
	public int timeToUse()
	{
		return 30;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/antivehicle_rocket_launcher/antivehicle_rocket_launcher.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
	
	@Override
	public float onRocketHit(EntityLivingBase entity, Entity target, ItemStack stack, ItemGun item)
	{
		if(!target.onGround)
		{
			makeSuperHit(target, 0, entity);
			return 1.5F;
		}
		return 1.0F;
	}
}
