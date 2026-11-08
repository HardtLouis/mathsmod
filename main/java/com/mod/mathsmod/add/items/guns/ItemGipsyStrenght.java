package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityThrowableStone;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGipsyStrenght extends ItemGun implements IItemSpecialRenderer
{

	public ItemGipsyStrenght()
	{
		super("gipsy_strength");
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		return null;
	}

	@Override
	public int cooldown()
	{
		return 12;
	}

	@Override
	public float zoomOnUsed()
	{
		return 1;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 1;
	}

	@Override
	public int getChargerSize()
	{
		return 0;
	}

	@Override
	protected float innacuracyOnShoot()
	{
		return 6;
	}

	@Override
	protected int maxInnacuracy()
	{
		return 6;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 2;
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
		return 4;
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
	public int timeToUse() 
	{
		return 4;
	}

	@Override
	public boolean autoMod()
	{
		return true;
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
		return EnumBulletType.LITTLE;
	}

	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		if(entityIn instanceof EntityPlayer)
		{
			if(!worldIn.isRemote && (((EntityPlayer) entityIn).capabilities.isCreativeMode || !UtilsVoids.getItemMain((EntityPlayer) entityIn, Item.getItemFromBlock(Blocks.COBBLESTONE)).isEmpty()))
			{
				if(!((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					UtilsVoids.getItemMain((EntityPlayer) entityIn, Item.getItemFromBlock(Blocks.COBBLESTONE)).shrink(1);
				}
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.MASTER, 3, 1.2F);
				EntityThrowableStone entity = new EntityThrowableStone(worldIn, entityIn);
				entity.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0, 2, 0);
				entity.setDamages(6);
				worldIn.spawnEntity(entity);
				
				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(this, this.cooldown(entityIn.getHeldItemMainhand(), (EntityPlayer) entityIn));
			}
		}
		else
		{
			if(!worldIn.isRemote)
			{
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.MASTER, 3, 1.2F);
				EntityThrowableStone entity = new EntityThrowableStone(worldIn, entityIn);
				entity.shoot(entityIn, entityIn.rotationPitch, entityIn.rotationYaw, 0, 2, 0);
				entity.setDamages(6);
				worldIn.spawnEntity(entity);
			}
		}
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/gipsy_strength/gipsy_strength.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}
