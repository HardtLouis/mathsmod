package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.GUI.guiBasics.GuiSniperScope;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSniper extends ItemGun implements IGunScope, IItemSpecialRenderer
{

	public ItemSniper() {
		super("sniper");
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
	public int getChargerSize() {
		return 1;
	}

	@Override
	public float zoomOnUsed() {
		return 15;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LONG;
	}

	@Override
	public int cooldown() 
	{
		return 30;
	}

	@Override
	public float getBulletDrop() 
	{
		return 0;
	}

	@Override
	public float getPower()
	{
		return 2F;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 7 * Mathsmod.SNIPERS_MULTIPLIER;
	}
	
	public float HeadMultiplier()
	{
		return 3.0F;
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
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 2));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot")), 2));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block"))));
		return list;
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
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/sniper/sniper.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}
