package com.mod.mathsmod.add.items.guns;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.utils.MDamageSource;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRevolver extends ItemGun implements IItemSpecialRenderer
{

	public ItemRevolver() 
	{
		super("revolver");
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		return null;
	}

	@Override
	public int cooldown() 
	{
		return 15;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 70;
	}

	@Override
	public int getChargerSize() 
	{
		return 6;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 5;
	}

	@Override
	protected int maxInnacuracy()
	{
		return 7;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.25F;
	}

	@Override
	public float getBulletDrop() 
	{
		return 0;
	}

	@Override
	public float getPower() 
	{
		return 1;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 5 * Mathsmod.SNIPERS_MULTIPLIER;
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
	
	public boolean isPistol()
    {
    	return true;
    }

	@Override
	public int timeToUse() 
	{
		return 10;
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
		return 50;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LITTLE;
	}
	
	public void setOnUser(ItemStack stack, boolean num)
	{

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("on_user", num);
    }
    
    public static boolean isOnUser(ItemStack stack) 
    {
        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("on_user")) {

        	stack.getTagCompound().setBoolean("on_user", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("on_user");
    }

	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return this.isOnUser(stack);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		boolean flag = this.getReloading(stack);
		float progress = 0;
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/revolver/revolver.json"), "normal"));
		
		if(this.isOnUser(stack))
		{
			GlStateManager.scale(1, 1, -1);
			GlStateManager.translate(0, 0, -1);
		}
		
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);		
		
		if(this.isOnUser(stack))
		{
			GlStateManager.translate(0, 0, 1);
			GlStateManager.scale(1, 1, -1);
		}
	}
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
		Random rand = new Random();
		if(handIn == EnumHand.MAIN_HAND && this.isOnUser(entityIn.getHeldItem(handIn)) && this.getCharger(entityIn.getHeldItem(handIn)) > 0 && rand.nextInt(this.getCharger(entityIn.getHeldItem(handIn))) == 0)
		{
			worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(entityIn.getHeldItem(handIn), entityIn), this.soundPitch(worldIn));
			if(!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.isCreativeMode)
			{
				this.removeBulletInCharger(entityIn.getHeldItem(handIn));
			}
			entityIn.attackEntityFrom(MDamageSource.SUICIDE, 10000);
		}
		else if(handIn == EnumHand.MAIN_HAND)	
		{
			if(!this.isOnUser(entityIn.getHeldItem(handIn)))
			{
				super.onItemUsedByEntity(worldIn, entityIn, handIn);
			}
			else if(this.getCharger(entityIn.getHeldItem(handIn)) > 0)
			{
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(entityIn.getHeldItem(handIn), entityIn), this.soundPitch(worldIn));
				this.removeBulletInCharger(entityIn.getHeldItem(handIn));
				if(entityIn instanceof EntityPlayer)
				{
					((EntityPlayer) entityIn).getCooldownTracker().setCooldown(this, 20);
				}
			}
		}
    }
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
    	if(handIn == EnumHand.OFF_HAND)
		{
    		this.setOnUser(stack, !this.isOnUser(stack));
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}
    	
    	return super.onItemRightClick(worldIn, playerIn, handIn);
    }
	
	public boolean hasTravel()
	{
		return true;
	}

	public float HeadMultiplier()
	{
		return 1.2F;
	}
}
