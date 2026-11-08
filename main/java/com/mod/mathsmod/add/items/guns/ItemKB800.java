package com.mod.mathsmod.add.items.guns;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKB800 extends ItemGun implements ItemShotGun, IItemSpecialRenderer
{
	public ItemKB800() 
	{
		super("kb800");
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
		list.set(1, new ItemStack(Blocks.IRON_BLOCK));
		list.set(2, new ItemStack(Blocks.IRON_BLOCK));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(4, new ItemStack(Item.getItemFromBlock(Blocks.PLANKS)));
		return list;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + " +60% damages");
		tooltip.add(TextFormatting.RED + " -83% clip size");
		tooltip.add(TextFormatting.RED + " +100% dispersion");
		tooltip.add(TextFormatting.BLUE + " knockback on both user and target");
    }

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entity) ? 1.4F : 2 : 3.6F;
	}

	@Override
	public float getBuckshotDecal(EntityLivingBase entityIn, ItemStack stack) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entityIn) ? 6F : 10F : 16F;
	}

	@Override
	public int getBulletsSquareNumber() 
	{
		return 3;
	}

	@Override
	public int cooldown() 
	{
		return 1;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 0.9F;
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
		return 5;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 5;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 2.5F;
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
		return 9.0F * 1.6F * Mathsmod.SHOTGUN_MULTIPLIER;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.4461433F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, 0.3717861F, 0F);
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
		return false;
	}

	@Override
	public boolean lowBulletFar()
	{
		return false;
	}

	@Override
	public int getReach()
	{
		return 20;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.SHOTGUN;
	}
	
	@Override
	public boolean hasTravel()
	{
		return false;
	}
	
	@Override
	public void onShoot(EntityLivingBase entity)
	{
		if(!entity.world.isRemote)
		{
			Vec3d vec = UtilsVoids.laser(2, entity.rotationYaw, entity.rotationPitch);
			
			UtilsVoids.AttractEntity(entity.posX + vec.x, entity.posY + entity.getEyeHeight() + vec.y, entity.posZ + vec.z, entity, 10, -2.5);
	        
			if(entity.motionY >= 0.3F)
			{
				entity.fallDistance = 0;
			}
			
			if(entity instanceof EntityPlayerMP)
			{
				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
			}
		}
		//entity.rotationPitch -= 30;
	}
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.SHOTGUN_SOUND;
	}
	
	@Override
	public int timeToUse()
	{
		return 6;
	}

	@Override
	public int stopPower() 
	{
		return 40;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/kb800/kb800.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}