package com.mod.mathsmod.add.items.guns;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelFAMAS;
import com.mod.mathsmod.add.items.guns.renderer.models.modelHeavyFamas;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHeavyFamas extends ItemFamas
{

	public ItemHeavyFamas(String name) 
	{
		super(name);
	}

	@Override
	public void onShoot(EntityLivingBase entity)
	{
		entity.rotationPitch -= 1F;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() {
		
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(RegisterBlocks.SILVER_BLOCK));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(2, new ItemStack(RegisterBlocks.SILVER_BLOCK));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		
		return list;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.BLUE + " shoot a burst of 3 bullets");
		tooltip.add(TextFormatting.GREEN + " +20% reach");
		tooltip.add(TextFormatting.GREEN + " decreased distance damage reduction");
		tooltip.add(TextFormatting.GREEN + " near perfect precision");
		tooltip.add(TextFormatting.GREEN + " +40% damage");
		tooltip.add(TextFormatting.RED + " -30% clip size");
		tooltip.add(TextFormatting.RED + " +100% cooldown between bursts");
    }
	
	@Override
	public int getReach() 
	{
		return 117;
	}
	
	protected int getRifle()
	{
		return 3;
	}
	
	protected int timeBetweenShots()
	{
		return 2;
	}
	
	@Override
	public float getDamagesCaused() 
	{
		return 2.5F * 1.4F * Mathsmod.AR_MULTIPLIER;
	}
	
	@Override
	public int getChargerSize()
	{
		return 21;
	}
	
	@Override
	protected int shootCadence()
	{
		return 12;
	}
	
	@Override
	public int timeToUse()
	{
		return 8;
	}
	
	@Override
	public boolean autoMod()
	{
		return false;
	}
	
	@Override
	protected float innacuracyOnShoot()
	{
		return 1F;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 6;
	}

	@Override
	protected float removeInnacuracyNumber() {
		
		return 0.5F;
	}
	
	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return this.getUsed(stack) != 0 ? this.userNotMoving(entity) ? 0F : 4F : 7F;
	}
	
	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.4F, -0.06F, 0.62F, -90, 0, 0);
	}

	@Override
	public Vec3d scopeLocation(ItemStack stack)
	{
		return new Vec3d(0, 2.75, 0.5);
	}

	@Override
	public Vec3d mufflerLocation(ItemStack stack) 
	{
		return new Vec3d(0, 2.08, -2.1);
	}

	@Override
	public Vec3d lampLocation(ItemStack stack)
	{
		return new Vec3d(0, 1.9, -1.2);
	}
	
	public SoundEvent getShotSound()
    {
    	return MathsSounds.GLOCK_SOUND;
    }
	
	@Override
	public float soundPitch(World worldIn)
    {
    	return 1.4F;
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
		
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/heavy_famas/heavy_famas_core.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);
		
		float cooldown = 0;
		if(entity instanceof EntityPlayer)
		{
			cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, partialTick);
		}
		
		if(cooldown > 0.5)
		{
			if(cooldown > 0.75F)
			{
				GlStateManager.translate(0, 0, 0 + (1F - cooldown) * 2 * 0.22);
			}
			else
			{
				GlStateManager.translate(0, 0, 0 + (cooldown - 0.5F) * 2 * 0.22);
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
		
		IBakedModel bakedMag = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/heavy_famas/heavy_famas_mag.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedMag, 1, partialTick);
	}
}
