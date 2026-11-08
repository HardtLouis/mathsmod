package com.mod.mathsmod.add.items.guns;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelMedic_s_shotgun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelRemington870;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMedicsShotgun extends ItemMachineGun implements ItemShotGun, IItemSpecialRenderer, IMedicWeapons
{
	
	public ItemMedicsShotgun() 
	{
		super("doctors_shotgun");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "+ reload 1 ammo on pellet hit if the charger is not full");
		tooltip.add(TextFormatting.GREEN + " -33% dispersion");
		tooltip.add(TextFormatting.RED + "+ 50% reload time");
		tooltip.add(TextFormatting.RED + "- 33% clip size");
    }

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entity) ? 1.4F : 2 : 3.6F;
	}

	@Override
	public float getBuckshotDecal(EntityLivingBase entityIn, ItemStack stack) 
	{
		return this.getUsed(stack) > 0 ? this.userNotMoving(entityIn) ? 2F : 3.2F : 5.4F;
	}

	@Override
	public int getBulletsSquareNumber() 
	{
		return 3;
	}
	
	@Override
	protected int shootCadence() 
	{
		return 10;
	}

	@Override
	public float zoomOnUsed() {
		
		return 0.85F;
	}
	
	public boolean rechargeOneByOne()
	{
		return true;
	}

	@Override
	public int getMaxReloadTime() {
		
		return 30;
	}

	@Override
	public int getChargerSize() 
	{
		return 4;
	}

	@Override
	protected float innacuracyOnShoot() {
		
		return 4;
	}

	@Override
	protected int maxInnacuracy() {
		return 6;
	}

	@Override
	protected float removeInnacuracyNumber() {
		return 0.2F;
	}

	@Override
	public float getBulletDrop() {
		
		return 0.2F;
	}

	@Override
	public float getPower() {
		
		return 2;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 9 * Mathsmod.SHOTGUN_MULTIPLIER;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-1.570796F, -0.5461433F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		float i = 0;
		float y = 1;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload <= 0.2)
			{
				if(reload >= 0.1F)
				{
					i = (reload * 25) * 0.017453292F;
				}
				else
				{
					i = (5 - (reload * 25)) * 0.017453292F;
				}
			}
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
		else
		{
			if(this.getCooldownTime(stack, partialTicks, entity) <= 0.25 && this.getCooldownTime(stack, partialTicks, entity) != 0)
			{
				if(this.getCooldownTime(stack, partialTicks, entity) >= 0.125F)
				{
					i = (this.getCooldownTime(stack, partialTicks, entity) * 0.017453292F * 20);
				}
				else
				{
					i = (5 * 0.017453292F - (this.getCooldownTime(stack, partialTicks, entity) * 0.017453292F * 20));
				}
			}
		}

		return new ThreeFloat(-1.570796F * y, 0.4717861F * y - (0.017453292F * 6) + i, 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.45F, -0.25F, 0.4F, -88, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		float i = 0;
		float y = 0;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload <= 0.2)
			{
				if(reload >= 0.1F)
				{
					i = (reload * 25);
				}
				else
				{
					i = (5 - (reload * 25));
				}
			}
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
		else
		{
			if(this.getCooldownTime(stack, partialTicks, player) <= 0.25 && this.getCooldownTime(stack, partialTicks, player) != 0)
			{
				if(this.getCooldownTime(stack, partialTicks, player) >= 0.125F)
				{
					i = (this.getCooldownTime(stack, partialTicks, player) * 20);
				}
				else
				{
					i = (5 - (this.getCooldownTime(stack, partialTicks, player) * 20));
				}
			}
		}
		
		return new armsLoRo(-0.6F, -0.24F, 0.0F, -88 + y, -11, -i - 11);
	}

	@Override
	public boolean scopeToShoot() {
		
		return false;
	}

	@Override
	public boolean lowBulletFar() {
		
		return false;
	}

	@Override
	public EnumBulletType getBullet() {
		
		return EnumBulletType.SHOTGUN;
	}
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.SHOTGUN_SOUND;
	}
	
	@Override
	public float soundPitch(World worldIn)
    {
    	return 1.5F;
    }
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(5, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		
		return null;
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
		entity.rotationPitch -= 4;
	}
	
	@Override
	public int timeToUse()
	{
		return 6;
	}
	
	@Override
	public boolean displayRecipe()
	{
		return false;
	}
	
	//TODO Infinite Shoot
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
        {
    		if(!this.ChargerEmpty(stack) && stack.getItem() instanceof ItemGun && this.bulletUsed(stack, entityIn) != null)
    		{
    			ItemBaseBullet bullet = this.bulletUsed(stack, entityIn);
    			
    			if(this.getReloading(stack))
	    		{
	    			this.setReloading(stack, false);;
	    		}
				
    			if(!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					this.removeBulletInCharger(stack);
				}
    			
    			bullet.shootEvent(worldIn, entityIn, (ItemGun) stack.getItem(), false, null);
				
				if(!this.autoMod())
	    		{
	    			this.setReset(stack, false);
	    		}
				
				this.onShoot(entityIn);
				
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(stack, entityIn), this.soundPitch(worldIn));
				
				if(entityIn instanceof EntityPlayer)
				{
	    			this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
				}
				else
				{
	    			this.substractReload(stack, null, this.getReload(stack));
				}
				
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() <= this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.getShootDefault(stack) + this.innacuracyOnShoot());
    			}
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() > this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.maxInnacuracy());
    			}
    			if(entityIn instanceof EntityPlayer)
    			{
    				if(worldIn.isRemote && this.cooldown(stack, (EntityPlayer) entityIn) <= 4)
    				{
    					((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), 3);
    				}
    				else
    				{
    					((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), this.cooldown(stack, (EntityPlayer) entityIn));
    				}
    			}
            }
        }
    }

	@Override
	public int stopPower() 
	{
		return 5;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/doctors_shotgun/doctors_shotgun.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}