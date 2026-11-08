package com.mod.mathsmod.add.items.guns;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.accessories.IRenderAccessories;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelP90;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Itemp90 extends ItemMachineGun implements IItemSpecialRenderer, IRenderAccessories
{

	public Itemp90() 
	{
		super("P90");
	}
	
	@Override
	public SoundEvent getShotSound()
	{
		return MathsSounds.P90_SOUND;
	}

	@Override
	protected int shootCadence() 
	{
		return 1;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1.1F;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 30;
	}

	@Override
	public int getChargerSize() 
	{
		return 50;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 0F;
	}

	@Override
	protected int maxInnacuracy() 
	{
		return 0;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0.0F;
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
		return 0.9325F * Mathsmod.SMG_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) != 0 ? 6 : 12;
	}
	
	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks) 
	{
		return new ThreeFloat(-1.570796F, this.getReloading(stack) ? -14 * 0.017453292F : 0, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		float i = 0;
		if(this.getReloading(stack))
		{
			float reload = (((float)this.getMaxReloadTime()) - ((float)this.getReload(stack))) / ((float)this.getMaxReloadTime());
			if(reload > 0.5F)
			{
				if(reload > 0.75F)
				{
					i = (reload - 1) * -4;
				}
				else
				{
					i = (reload - 0.6F) * 4;
				}
			}
			else
			{
				if(reload > 0.25F)
				{
					i = (reload - 0.4F) * -4; 
				}
				else
				{
					i = reload * 4;
				}
			}
		}
		return this.getReloading(stack) ? new ThreeFloat(-115 * i * 0.017453292F, this.getReloading(stack) ? 61 * i * 0.017453292F : 0, 0) : null;
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return new armsLoRo(0.3F, -0.02F, 0.55F, -90, 0, 0);
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
	public int getReach() 
	{
		return 29;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LITTLE;
	}
    
    @Override
	public NonNullList<ItemStack> Craft() 
    {
    	NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(1, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(2, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_ingot"))));
		list.set(3, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:kurayum_ingot"))));
		list.set(4, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(5, new ItemStack(Items.IRON_INGOT));
		list.set(6, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		
		return list;
	}
    
    public float handMovementMultiplicator()
    {
    	return 0F;
    }
    
    @Override
	public void onShoot(EntityLivingBase entity)
	{
    	entity.rotationPitch -= new Random().nextFloat() * 1.5 - 0.75;
    	entity.rotationYaw -= new Random().nextFloat() * 1.5 - 0.75;
	}
    
    @Override
	public int timeToUse()
	{
		return 3;
	}
	
	public boolean canEquipModifier()
	{
		return true;
	}
	
	public boolean onlyMuffler()
	{
		return false;
	}

	@Override
	public Vec3d scopeLocation(ItemStack stack)
	{
		return new Vec3d(0, 3.2, -1.5);
	}

	@Override
	public Vec3d mufflerLocation(ItemStack stack) 
	{
		return new Vec3d(0, 2.0, -2.8);
	}

	@Override
	public Vec3d lampLocation(ItemStack stack)
	{
		return new Vec3d(0, 1.85, -2);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
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
		
		
		IBakedModel bakedC = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/p90/p90_core.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedC, 0, partialTick);
		
		IBakedModel bakedMag = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/p90/p90_mag.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedMag, 0, partialTick);
	}
}
