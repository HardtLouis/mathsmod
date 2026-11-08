package com.mod.mathsmod.add.items.guns;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBasicShotgun;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSCS7 extends ItemMachineGun implements ItemShotGun, IBulletProtection, IItemSpecialRenderer
{
	
	protected static final UUID HEAVY = UUID.fromString("845DB27C-C624-495F-8C9F-6020A5A58B6F");

	public ItemSCS7() {
		super("scs7");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "+player can double jump");
		tooltip.add(TextFormatting.GREEN + "+speed when held");
		tooltip.add(TextFormatting.GREEN + "+15% damages");
		tooltip.add(TextFormatting.RED + " +30% damages taken by bullets");
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
    	return 1.4F;
    }
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.SHOTGUN_SOUND;
	}

	@Override
	public float zoomOnUsed() {
		return 0.9F;
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
		return 9 * 1.15F * Mathsmod.SHOTGUN_MULTIPLIER;
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
		float y = 1;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload >= 0.5)
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
		
		return new ThreeFloat(-1.570796F * y, 0.3717861F * y - (0.017453292F * 6), 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.45F, -0.25F, 0.8F, -88, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		float y = 0;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
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
		
		return new armsLoRo(-0.7F, -0.245F, 0.3F, -88 + y, -21, 0 - 21);
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
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		
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
		entity.rotationPitch -= 12;
	}
	
	@Override
	public int timeToUse()
	{
		return 6;
	}

	@Override
	public int stopPower()
	{
		return 5;
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(HEAVY, "Heavy", 0.03F, 0));
        }

        return multimap;
    }

	@Override
	protected int shootCadence() {
		return this.cooldown();
	}

	@Override
	public float getBulletMultiplier()
	{
		return 1.3F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/scs7/scs7.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}
