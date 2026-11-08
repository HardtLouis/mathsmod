package com.mod.mathsmod.add.items.guns;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.accessories.IRenderAccessories;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelFAMAS;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFamas extends ItemMachineGun implements IItemSpecialRenderer, IRenderAccessories
{

	public ItemFamas(String name) {
		super(name);
	}
	
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    	
    	if(this.getShoot(stack))
    	{
    		if(this.getNumberShot(stack) < this.getBurst() * timeBetweenShots())
    		{
    			this.setNumberShot(stack, this.getNumberShot(stack) + 1);
    			
    			if(isSelected && this.getNumberShot(stack) % timeBetweenShots() == 0)
    			{
    				super.onItemUsedByEntity(worldIn, (EntityLivingBase) entityIn, EnumHand.MAIN_HAND);
    			}
    			
    			if(this.getNumberShot(stack) >= this.getBurst() * timeBetweenShots())
    			{
    				this.setShoot(stack, false);
    				this.setNumberShot(stack, 0);
    			}
    		}
		
    		if(this.getNumberShot(stack) >= this.getBurst() * timeBetweenShots() && !this.getShoot(stack))
			{
				this.setNumberShot(stack, 0);
			}
    	}
    	
    	if(entityIn instanceof EntityPlayer)
    	{
    		if(!this.getShoot(stack) && ((EntityPlayer) entityIn).getCooldownTracker().hasCooldown(this))
    		{
    			this.setNumberShot(stack, 0);
    		}
    	}
    }
    
    @Override
    public float multiplierPerDistances(ItemStack stack, EntityPlayer player, float distance)
	{
    	if(this instanceof ItemHeavyFamas)
    	{
    		float mult = (float) ((1.0 - Math.tanh(- 4.0 + ((distance / this.getReach()) * 6.0))) / 2);
    		return mult;
    	}
    	else
    	{
    		return super.multiplierPerDistances(stack, player, distance);
    	}
	}

	@Override
	public float zoomOnUsed() {
		
		return 1.5F;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 30;
	}

	@Override
	public int getChargerSize()
	{
		return 30;
	}

	@Override
	protected float innacuracyOnShoot() {
		
		return 1.25F;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 2;
	}

	@Override
	protected float removeInnacuracyNumber() {
		
		return 0.4F;
	}

	@Override
	public float getBulletDrop() {
		
		return 0;
	}

	@Override
	public float getPower() {
		
		return 1F;
	}

	@Override
	public float getDamagesCaused() {
		
		return 2.5F * Mathsmod.AR_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) != 0 ? this.userNotMoving(entity) ? 0F : 1F : 5F;
	}


	//TODO Anim
	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) {
		return new ThreeFloat(-1.570796F, -0.4461433F, 0F);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		float y = 0;
		float z = 1;
		if(this.getReload(stack) != 0)
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			
			if(reload != 0.0F)
			{
				float yee = reload - 0.5F;

				if(reload >= 0.5F)
				{
					y = (yee - 0.5F) * 2;
					z = (yee - 0.5F) * 2;
				}
				if(reload <= 0.5)
				{
					y = 1 - (reload * 2);
					z = 1 - (reload * 2);
					
					if(reload > 0.2F)
					{
						y *= 0.8F;
						z *= 0.8F;
					}
				}
			}
		}
		else
		{
			if(this.getCooldownTime(stack, partialTicks, entity) <= 0.25 && this.getCooldownTime(stack, partialTicks, entity) != 0)
			{
			}
		}

		return new ThreeFloat(-1.770796F * z, 0.4717861F + (61 * y * 0.017453292F), 0F);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.01F, 0.72F, -90, 0, 0);
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		float i = 0;
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
		else
		{
		}
		
		return this.Animated(stack, player) ? new armsLoRo(-0.65F, -0.15F, 0.4F, -88 + y, -i -11, - 11) : null;
	}

	@Override
	public boolean scopeToShoot() {
		
		return true;
	}

	@Override
	public EnumBulletType getBullet() {
		
		return EnumBulletType.NORMAL;
	}
	
	protected int getBurst()
	{
		return 3;
	}
	
	protected int timeBetweenShots()
	{
		return 2;
	}
	
	public void setNumberShot(ItemStack stack, int d) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("ns", d);

    }
    
    public static int getNumberShot(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("ns")) {

        	stack.getTagCompound().setBoolean("ns", false);
            return 0;
        }

        return stack.getTagCompound().getInteger("ns");
    }
    
    public void setShoot(ItemStack stack, boolean used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("shoot", used);

    }
    
    public static boolean getShoot(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("shoot")) {

        	stack.getTagCompound().setBoolean("shoot", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("shoot");
    }
    
    public void onRightClickPressed(World worldIn, EntityPlayer playerIn)
	{
		if(!playerIn.getCooldownTracker().hasCooldown(this))
		{
			if(this.autoMod() || this.getReset(playerIn.getHeldItemMainhand()))
			{
				this.onItemUsedByEntity(worldIn, playerIn, EnumHand.MAIN_HAND);
			}
		}
	}
    
    public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	if (handIn == EnumHand.MAIN_HAND)
        {
    		if(!this.ChargerEmpty(stack) && stack.getItem() instanceof ItemGun && this.bulletUsed(stack, entityIn) != null)
    		{
    			if(!this.getShoot(stack))
	    		{
	    			this.setShoot(stack, true);
	    		}
    		}
        }
    }
    
    @Override
    protected String getDPS(ItemStack stack)
	{
    	float ticksTotal = (this.timeBetweenShots() * this.getBurst() + this.cooldown()) * ((int)(this.getChargerSize() / this.getBurst())) + this.getMaxReloadTime();
		
		return (this.getDamagesCaused() * this.damageMultiplier(stack, null, null) * this.getChargerSize() * 20.0F / ticksTotal) + "";
	}
    
    public SoundEvent getShotSound()
    {
    	return MathsSounds.FAMAS_SOUND;
    }
    
    public float getSoundPower()
    {
    	return 2F;
    }
    
    @Override
    public float soundPitch(World worldIn) 
    {
    	return 1;
    }

	@Override
	public boolean lowBulletFar() {
		
		return true;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() {
		
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(RegisterBlocks.SILVER_BLOCK));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(2, new ItemStack(RegisterBlocks.SILVER_BLOCK));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		
		return list;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.BLUE + " shoot a burst of 3 bullets");
    }

	@Override
	public int getReach() 
	{
		return 90;
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
		entity.rotationPitch -= 0.4F;
	}

	@Override
	protected int shootCadence()
	{
		return 6;
	}

	@Override
	public int timeToUse()
	{
		return 8;
	}
	
	@Override
	public boolean canEquipModifier()
	{
		return true;
	}

	@Override
	public Vec3d scopeLocation(ItemStack stack)
	{
		return new Vec3d(0, 3.1, 0);
	}

	@Override
	public Vec3d mufflerLocation(ItemStack stack) 
	{
		return new Vec3d(0, 2.07, -2.05);
	}

	@Override
	public Vec3d lampLocation(ItemStack stack)
	{
		return new Vec3d(0, 1.86, -1.4);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		RenderItem ir = Minecraft.getMinecraft().getRenderItem();
		
		for(int i = 0; i < 3; ++i)
		{
			if(this.getModifiers(stack).get(i) != null && !this.getModifiers(stack).get(i).isEmpty())
			{
				Vec3d trans = this.mufflerLocation(stack);
				if(i == 1)
				{
					trans = this.scopeLocation(stack);
				}
				else if(i == 2)
				{
					trans = this.lampLocation(stack);
				}
				GlStateManager.translate(0.5, 0, 0.5);
				GlStateManager.scale(0.2, 0.2, 0.2);
				GlStateManager.translate(-0.5, 0, -0.5);
				GlStateManager.translate(trans.x, trans.y, trans.z);
				RenderLaser.renderModelM(stack, ir.getItemModelMesher().getItemModel(this.getModifiers(stack).get(i)));
				GlStateManager.translate(-trans.x, -trans.y, -trans.z);
				GlStateManager.translate(0.5, 0, 0.5);
				GlStateManager.scale(5, 5, 5);
				GlStateManager.translate(-0.5, 0, -0.5);
			}
		}
		
		boolean flag = this.getReloading(stack);
		float progress = 0;
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/famas/famas_core.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);
		
		float cooldown = 0;
		if(entity instanceof EntityPlayer)
		{
			cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, partialTick);
		}
		
		if(cooldown > 0)
		{
			if(cooldown > 0.5F)
			{
				GlStateManager.translate(0, 0, 0 + (1F - cooldown) * 0.22);
			}
			else
			{
				GlStateManager.translate(0, 0, 0 + (cooldown) * 0.22);
			}
		}
		
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/famas/famas_poul.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedPump, 3, partialTick);
		
		if(cooldown > 0)
		{
			if(cooldown > 0.5F)
			{
				GlStateManager.translate(0, 0, 0 - (1F - cooldown) * 0.22);
			}
			else
			{
				GlStateManager.translate(0, 0, 0 - (cooldown) * 0.22);
			}
		}
		
		IBakedModel bakedMag = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/famas/famas_mag.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedMag, 1, partialTick);
	}
}
