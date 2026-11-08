package com.mod.mathsmod.add.items;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityThrowableStone;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.EnumBulletType;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ThreeFloat;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChickenLauncher extends ItemGun implements IItemSpecialRenderer
{

	public ItemChickenLauncher() {
		
		super("chicken_launcher");
	}
	
	public float HeadMultiplier()
	{
		return 2F;
	}

	@Override
	public int cooldown() {
		
		return 6;
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
		
		return 0;
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
		
		return 0;
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
	public boolean scopeToShoot() 
	{
		
		return true;
	}

	@Override
	public boolean lowBulletFar() 
	{
		
		return true;
	}

	@Override
	public EnumBulletType getBullet() {
		
		return EnumBulletType.LONG;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		return null;
	}

	@Override
	public int getReach() 
	{
		return 0;
	}

	@Override
	public boolean autoMod() 
	{
		return false;
	}

	@Override
	public boolean hasTravel()
	{
		return true;
	}

	public float handMovementMultiplicator()
    {
    	return 4F;
    }
	
	@Override
	public int timeToUse()
	{
		return 10;
	}
	
	public boolean isPistol()
    {
    	return true;
    }
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		if(entityIn instanceof EntityPlayer)
		{
			if(!worldIn.isRemote && (((EntityPlayer) entityIn).capabilities.isCreativeMode || !UtilsVoids.getItemMain((EntityPlayer) entityIn, Items.EGG).isEmpty()))
			{
				if(!((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					UtilsVoids.getItemMain((EntityPlayer) entityIn, Items.EGG).shrink(1);
				}
				
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.MASTER, 3, 1.2F);
				EntityChicken entity = new EntityChicken(worldIn);
				entity.setPosition(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
				UtilsVoids.EasyShootEntityModifyY(entity, entityIn.rotationPitch, entityIn.rotationYaw, 5, 0);
				worldIn.spawnEntity(entity);
				
				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(this, this.cooldown(entityIn.getHeldItemMainhand(), (EntityPlayer) entityIn));
			}
		}
		else
		{
			if(!worldIn.isRemote)
			{
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.MASTER, 3, 1.2F);
				EntityChicken entity = new EntityChicken(worldIn);
				entity.setPosition(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
				UtilsVoids.EasyShootEntityModifyY(entity, entityIn.rotationPitch, entityIn.rotationYaw, 5, 0);
				worldIn.spawnEntity(entity);
			}
		}
    }
	
	public void setReloading(ItemStack stack, boolean used) {
    }
    
    public static boolean getReloading(ItemStack stack) {

        return false;
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/chicken_launcher/chicken_launcher.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}
