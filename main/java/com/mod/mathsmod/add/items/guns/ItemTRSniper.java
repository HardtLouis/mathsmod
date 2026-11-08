package com.mod.mathsmod.add.items.guns;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTRSniper extends ItemGun implements IGunScope, IItemSpecialRenderer
{

	public ItemTRSniper() {
		super("tr_sniper");
	}
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 40;
	}

	@Override
	public int getChargerSize()
	{
		return 1;
	}

	@Override
	public float zoomOnUsed() {
		return 15;
	}

	@Override
	public EnumBulletType getBullet() {
		return EnumBulletType.LONG;
	}

	@Override
	public int cooldown() {
		
		return 20;
	}

	@Override
	public float getBulletDrop() {
		return 0;
	}

	@Override
	public float getPower() {
		return 2F;
	}

	@Override
	public float getDamagesCaused()
	{
		return 2.3F * Mathsmod.SNIPERS_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) >= this.timeToFullScope() ? 0 : 45;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.3141593F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
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
	public boolean scopeToShoot() {
		return true;
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
	public boolean lowBulletFar() {
		return false;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		return null;
	}

	@Override
	public int getReach() 
	{
		return 500;
	}

	@Override
	public boolean autoMod() 
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getScope() 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/gui/sniper_scope.png");
	}

	@Override
	public boolean hadCustomGuiScope() 
	{
		return false;
	}
	
	public boolean changeMouseSensibilityOnScope()
    {
    	return true;
    }

	public boolean smoothScope()
	{
		return true;
	}
	
	public float smoothMultiplier()
	{
		return 1.3F;
	}
	
	@Override
	public int timeToUse()
	{
		return 15;
	}
	
	public boolean displayRecipe()
	{
		return false;
	}
	
	@Override
	public boolean keepBaseScope(ItemStack stack) 
	{
		return false;
	}

	@Override
	public int timeToFullScope() 
	{
		return 3;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/tr_sniper/tr_sniper.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
	
	public float damageMultiplier(ItemStack stack, Entity entity, @Nullable Entity target)
    {
		if(entity != null && entity.onGround)
		{
			entity.motionY = 2.0F;
			this.chargeCharger(stack, this.lastBullet(stack, entity), 1);
			if(entity instanceof EntityPlayerMP)
			{
				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
			}
			return 0.04F;
		}
		
		return super.damageMultiplier(stack, entity, target);
    }
	
	public float HeadMultiplier()
	{
		return 10F;
	}
}