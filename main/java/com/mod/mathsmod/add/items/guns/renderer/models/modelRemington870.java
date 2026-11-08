package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelRemington870 extends modelBaseGun
{
	  //fields
	    ModelRenderer Shape1;
	    ModelRenderer Shape2;
	    ModelRenderer Shape3;
	    ModelRenderer Shape4;
	    ModelRenderer Shape5;
	    ModelRenderer Shape14;
	    ModelRenderer Shape15;
	    ModelRenderer Shape16;
	    ModelRenderer Shape17;
	    ModelRenderer Shape18;
	    ModelRenderer Shape19;
	    ModelRenderer Shape20;
	    ModelRenderer Shape21;
	    ModelRenderer Shape22;
	    ModelRenderer Shape23;
	    ModelRenderer Shape24;
	    ModelRenderer Shape25;
	    ModelRenderer TCHTCH;
	  
	  public modelRemington870(float scale)
	  {
	    textureWidth = 64;
	    textureHeight = 64;
	    
	      Shape1 = new ModelRenderer(this, 24, 0);
	      Shape1.addBox(0F, 0F, 0F, 4, 8, 16, scale);
	      Shape1.setRotationPoint(0F, -4F, -16F);
	      Shape1.setTextureSize(64, 64);
	      Shape1.mirror = true;
	      setRotation(Shape1, 0F, 3.141593F, 0F);
	      Shape2 = new ModelRenderer(this, 30, 10);
	      Shape2.addBox(0F, 0F, 0F, 4, 4, 8, scale);
	      Shape2.setRotationPoint(0F, 4F, 0F);
	      Shape2.setTextureSize(64, 64);
	      Shape2.mirror = true;
	      setRotation(Shape2, 0F, 3.141593F, 0F);
	      Shape3 = new ModelRenderer(this, 30, 10);
	      Shape3.addBox(0F, 0F, 0F, 4, 2, 4, scale);
	      Shape3.setRotationPoint(0F, 8F, 0F);
	      Shape3.setTextureSize(64, 64);
	      Shape3.mirror = true;
	      setRotation(Shape3, 0F, 3.141593F, 0F);
	      Shape4 = new ModelRenderer(this, 24, 0);
	      Shape4.addBox(0F, 0F, 0F, 4, 4, 16, scale);
	      Shape4.setRotationPoint(0F, 0F, 0F);
	      Shape4.setTextureSize(64, 64);
	      Shape4.mirror = true;
	      setRotation(Shape4, 0F, 3.141593F, 0F);
	      Shape5 = new ModelRenderer(this, 24, 0);
	      Shape5.addBox(0F, 0F, 0F, 4, 8, 4, scale);
	      Shape5.setRotationPoint(0F, -4F, -32F);
	      Shape5.setTextureSize(64, 64);
	      Shape5.mirror = true;
	      setRotation(Shape5, 0F, 3.141593F, 0F);
	      Shape14 = new ModelRenderer(this, 26, 26);
	      Shape14.addBox(0.5F, -1F, 0F, 3, 3, 11, scale);
	      Shape14.setRotationPoint(0F, 2F, -20F);
	      Shape14.setTextureSize(64, 64);
	      Shape14.mirror = true;
	      setRotation(Shape14, -2.082002F, 3.141593F, 0F);
	      Shape15 = new ModelRenderer(this, 26, 26);
	      Shape15.addBox(0F, 0F, 0F, 2, 8, 1, scale);
	      Shape15.setRotationPoint(-1F, -1F, -20F);
	      Shape15.setTextureSize(64, 64);
	      Shape15.mirror = true;
	      setRotation(Shape15, 0F, 3.141593F, 0F);
	      Shape16 = new ModelRenderer(this, 26, 26);
	      Shape16.addBox(0F, 0F, 0F, 2, 8, 1, scale);
	      Shape16.setRotationPoint(-1F, -1F, -25F);
	      Shape16.setTextureSize(64, 64);
	      Shape16.mirror = true;
	      setRotation(Shape16, 0F, 3.141593F, 0F);
	      Shape17 = new ModelRenderer(this, 26, 26);
	      Shape17.addBox(0F, 0F, 0F, 2, 1, 5, scale);
	      Shape17.setRotationPoint(-1F, 7F, -20F);
	      Shape17.setTextureSize(64, 64);
	      Shape17.mirror = true;
	      setRotation(Shape17, 0F, 3.141593F, 0F);
	      Shape18 = new ModelRenderer(this, 26, 0);
	      Shape18.addBox(0F, 0F, 0F, 2, 8, 1, scale);
	      Shape18.setRotationPoint(-1F, -1F, -19F);
	      Shape18.setTextureSize(64, 64);
	      Shape18.mirror = true;
	      setRotation(Shape18, 0.3717861F, 3.141593F, 0F);
	      Shape19 = new ModelRenderer(this, 0, 45);
	      Shape19.addBox(-4F, 0F, -9F, 3, 3, 16, scale);
	      Shape19.setRotationPoint(0.5F, 0.5F, -43F);
	      Shape19.setTextureSize(64, 64);
	      Shape19.mirror = true;
	      setRotation(Shape19, 0F, 0F, 0F);
	      Shape20 = new ModelRenderer(this, 0, 45);
	      Shape20.addBox(-4F, -3F, 0F, 3, 3, 16, scale);
	      Shape20.setRotationPoint(0.5F, -0.5F, -52F);
	      Shape20.setTextureSize(64, 64);
	      Shape20.mirror = true;
	      setRotation(Shape20, 0F, 0F, 0F);
	      Shape21 = new ModelRenderer(this, 0, 45);
	      Shape21.addBox(-4F, 0F, 0F, 3, 3, 14, scale);
	      Shape21.setRotationPoint(0.5F, 0.5F, -66F);
	      Shape21.setTextureSize(64, 64);
	      Shape21.mirror = true;
	      setRotation(Shape21, 0F, 0F, 0F);
	      Shape22 = new ModelRenderer(this, 0, 45);
	      Shape22.addBox(-4F, -3F, 0F, 3, 3, 16, scale);
	      Shape22.setRotationPoint(0.5F, -0.5F, -68F);
	      Shape22.setTextureSize(64, 64);
	      Shape22.mirror = true;
	      setRotation(Shape22, 0F, 0F, 0F);
	      Shape23 = new ModelRenderer(this, 24, 0);
	      Shape23.addBox(-3F, 0F, 0F, 2, 6, 4, scale);
	      Shape23.setRotationPoint(0F, -3F, -64F);
	      Shape23.setTextureSize(64, 64);
	      Shape23.mirror = true;
	      setRotation(Shape23, 0F, 0F, 0F);
	      Shape24 = new ModelRenderer(this, 24, 0);
	      Shape24.addBox(-3F, 0F, 0F, 2, 6, 4, scale);
	      Shape24.setRotationPoint(0F, -3F, -57F);
	      Shape24.setTextureSize(64, 64);
	      Shape24.mirror = true;
	      setRotation(Shape24, 0F, 0F, 0F);
	      Shape25 = new ModelRenderer(this, 24, 0);
	      Shape25.addBox(-3F, 0F, 0F, 2, 6, 8, scale);
	      Shape25.setRotationPoint(0F, -3F, -44F);
	      Shape25.setTextureSize(64, 64);
	      Shape25.mirror = true;
	      setRotation(Shape25, 0F, 0F, 0F);
	      TCHTCH = new ModelRenderer(this, 24, 0);
	      TCHTCH.addBox(0F, 0F, 0F, 6, 6, 10, scale);
	      TCHTCH.setRotationPoint(1F, -1F, -43F);
	      TCHTCH.setTextureSize(64, 64);
	      TCHTCH.mirror = true;
	      setRotation(TCHTCH, 0F, 3.141593F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    Shape1.render(f5);
	    Shape2.render(f5);
	    Shape3.render(f5);
	    Shape4.render(f5);
	    Shape5.render(f5);
	    Shape14.render(f5);
	    Shape15.render(f5);
	    Shape16.render(f5);
	    Shape17.render(f5);
	    Shape18.render(f5);
	    Shape19.render(f5);
	    Shape20.render(f5);
	    Shape21.render(f5);
	    Shape22.render(f5);
	    Shape23.render(f5);
	    Shape24.render(f5);
	    Shape25.render(f5);
	    TCHTCH.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  }
  
	@Override
	public void renderModel(ItemStack stack, Entity entity)
	{
		if(stack.getItem() instanceof ItemGun)
		{
			ItemGun item = (ItemGun) stack.getItem();
			float reload = ((float)item.getMaxReloadTime() - (float)item.getReload(stack)) / (float)item.getMaxReloadTime();
			float cooldown = item.getCooldownTime(stack, Minecraft.getMinecraft().getRenderPartialTicks(), entity);
			
			if(entity instanceof EntityPlayer)
			{
				cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(item, Minecraft.getMinecraft().getRenderPartialTicks());
			}
			
			if(!item.getReloading(stack))
			{
				if(cooldown <= 0.25 && cooldown != 0)
				{
					if(cooldown >= 0.125F)
					{
						this.TCHTCH.rotationPointZ = -43 + (cooldown - 0.25F) * -64;
					}
					else
					{
						this.TCHTCH.rotationPointZ = -43 + 8 * (cooldown * 8);
					}
					
				}
				else
				{
					this.TCHTCH.rotationPointZ = -43;
				}
			}
			else
			{
				if(reload < 0.2 && reload != 0)
				{
					if(reload >= 0.1F)
					{
						this.TCHTCH.rotationPointZ = -43 + 8 - (reload * 40);
					}
					else
					{
						this.TCHTCH.rotationPointZ = -43 + (reload * 80);
					}
				}
				else
				{
					this.TCHTCH.rotationPointZ = -43;
				}
			}
		}
		
		
	  	Shape1.render(0.0625F);
	    Shape2.render(0.0625F);
	    Shape3.render(0.0625F);
	    Shape4.render(0.0625F);
	    Shape5.render(0.0625F);
	    Shape14.render(0.0625F);
	    Shape15.render(0.0625F);
	    Shape16.render(0.0625F);
	    Shape17.render(0.0625F);
	    Shape18.render(0.0625F);
	    Shape19.render(0.0625F);
	    Shape20.render(0.0625F);
	    Shape21.render(0.0625F);
	    Shape22.render(0.0625F);
	    Shape23.render(0.0625F);
	    Shape24.render(0.0625F);
	    Shape25.render(0.0625F);
	    TCHTCH.render(0.0625F);
	}
}
