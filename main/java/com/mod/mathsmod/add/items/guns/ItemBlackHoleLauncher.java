package com.mod.mathsmod.add.items.guns;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPultherSkull;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlackHoleLauncher extends ItemGun implements IItemSpecialRenderer
{

	public ItemBlackHoleLauncher() 
	{
		super("black_hole_launcher");
	}

	@Override
	public int cooldown() 
	{
		return 20;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1F;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 50;
	}

	@Override
	public int getChargerSize()
	{
		return 1;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 2;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 2;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.2F;
	}

	@Override
	public float getBulletDrop() 
	{
		return 0;
	}

	@Override
	public float getPower()
	{
		return 0.1F;
	}

	@Override
	public float getDamagesCaused()
	{
		return 20;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return 0;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-90 * 0.017453292F, -23 * 0.017453292F, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-90 * 0.017453292F, 23 * 0.017453292F, 0);
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
	public boolean lowBulletFar() {
		return false;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.GRENADE;
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
	
	public boolean canBeUseByHunter()
    {
    	return false;
    }
	
	public float soundPitch(World worldIn)
    {
    	return 1.4F;
    }
	
	@Override
	public int timeToUse()
	{
		return 10;
	}
	
	public SoundEvent getShotSound()
    {
    	return MathsSounds.GL_SOUND;
    }
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
        {
    		if(this.getRecharges(stack) > 0)
    		{
    			worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(stack, entityIn), this.soundPitch(worldIn));
    			Vec3d vec = UtilsVoids.laser(20, entityIn.rotationYaw, entityIn.rotationPitch);
        		
    			EntityPultherSkull entity = new EntityPultherSkull(worldIn, entityIn, vec.x, vec.y, vec.z);
    			entity.setPosition(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
    			entity.setFromPulgon(true);
    			UtilsVoids.EasyShootEntityModifyY(entity, entityIn.rotationPitch, entityIn.rotationYaw, 2, 0);
    			
    			if(!worldIn.isRemote)
    			{
    				worldIn.spawnEntity(entity);
    			}
    			
        		this.setRecharges(stack, this.getRecharges(stack) - 1);
        		
        		if(entityIn instanceof EntityPlayer)
    			{
    				((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), this.cooldown(stack, (EntityPlayer) entityIn));
    			}
    		}
        }
    }
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
	    return (double)(this.getChargerSize() - this.getRecharges(stack)) / (double)this.getChargerSize();
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
	    return this.getRecharges(stack) < this.getChargerSize();
    }
	
	@Override
    public void onRightClickPressed(World worldIn, EntityPlayer playerIn, int ticks)
    {
    	ItemStack stack = playerIn.getHeldItemMainhand();
    	if(!worldIn.isRemote)
    	{
    		if(this.getRecharges(stack) == 0)
            {
    			this.setReloading(stack, true);
            }
            else
            {
            	this.setUsed(stack, ticks);
            	return;
            }
    	}
    	
    }
	
	public void reloadGun(ItemStack stack, World worldIn, EntityLivingBase entityIn)
    {
    	if(this.getReloading(stack))
		{
    		if(entityIn instanceof EntityPlayer)
    		{
    			if(this.getMaxReloadTime(stack, (EntityPlayer) entityIn) <= this.getReload(stack))
    			{
    				for(int i = this.getRecharges(stack); i < this.getChargerSize(); ++i)
    				{
    					this.setRecharges(stack, this.getRecharges(stack) + 1);
    				}
    				this.setReloading(stack, false);
    				this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
    				return;
    			}
    			else
    			{
    				this.addReload(stack, (EntityPlayer) entityIn, 1);
    			}
    		}
    		else
    		{
    			for(int i = this.getRecharges(stack); i < this.getChargerSize(); ++i)
				{
					this.setRecharges(stack, this.getRecharges(stack) + 1);
				}
				this.setReloading(stack, false);
				return;
    		}
		}
	}
	
	public int getCharger(ItemStack stack) 
	{
    	return this.getRecharges(stack);
    }
	
	public NonNullList<ItemStack> getBulletList(ItemStack stack)
    {
    	NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(this.getChargerSize(), ItemStack.EMPTY);
    	return list;
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	if(!isSelected)
    	{
    		this.setUsed(stack, 0);
    		this.setTakeTime(stack, 0);
    		this.setShootDefault(stack, 0);
    	}
    	
    	if(isSelected)
    	{
    		if(entityIn instanceof EntityLivingBase)
    		{
    			if(this.getShootDefault(stack) > 0)
	        	{
	        		this.setShootDefault(stack, this.getShootDefault(stack) - removeInnacuracyNumber());
	        	}
	        	
	        	if(this.getShootDefault(stack) < 0)
	        	{
	        		this.setShootDefault(stack, 0);
	        	}
	        	
	        	if(this.getTakeTime(stack) < this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
	        	{
	        		this.addTakeTime(stack, 1);
	        	}
    		}
    	}
    	
    	if(entityIn instanceof EntityPlayer)
    	{
        	if(isSelected)
        	{
        		this.reloadGun(stack, worldIn, (EntityLivingBase) entityIn);
        		this.setCooldown(stack, ((EntityPlayer) entityIn).getCooldownTracker().getCooldown(this, 0.0F));
        		
        		if(this.getReloading(stack) && this.getRecharges(stack) >= this.getChargerSize())
        		{
        			this.setReloading(stack, false);
        		}
        	}
    	}
    }
	
	public void setRecharges(ItemStack stack, int used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("recharges", used);

    }
    
    public int getRecharges(ItemStack stack) 
    {
        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("recharges")) {

        	stack.getTagCompound().setInteger("recharges", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("recharges");
    }
	
	public float handMovementMultiplicator()
    {
    	return 2F;
    }
	
	@Override
	public boolean displayRecipe()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/black_hole_launcher/black_hole_launcher.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 0, partialTick);
	}
}