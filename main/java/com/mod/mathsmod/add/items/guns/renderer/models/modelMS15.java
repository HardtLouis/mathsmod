package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelMS15 extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer chargeur;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape10;
    ModelRenderer Shape21;
  
  public modelMS15(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 24, 0);
      Shape1.addBox(0F, 0F, 0F, 4, 11, 2, scale);
      Shape1.setRotationPoint(0F, -1F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 24, 0);
      Shape3.addBox(0F, 0F, 0F, 4, 4, 8, scale);
      Shape3.setRotationPoint(0F, -1F, -8F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 24, 0);
      Shape4.addBox(0F, 0F, 0F, 4, 5, 16, scale);
      Shape4.setRotationPoint(0F, -1F, -24F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 24, 4);
      Shape5.addBox(0F, 0F, 0F, 4, 5, 8, scale);
      Shape5.setRotationPoint(0F, -1F, -32F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 24, 4);
      Shape6.addBox(-2.5F, -2.5F, 0F, 5, 5, 10, scale);
      Shape6.setRotationPoint(2F, 1F, -42F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0.7853982F);
      Shape8 = new ModelRenderer(this, 24, 0);
      Shape8.addBox(0F, 0F, 0F, 2, 2, 13, scale);
      Shape8.setRotationPoint(1F, -1F, -55F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 24, 0);
      Shape9.addBox(0F, 0F, 0F, 3, 2, 15, scale);
      Shape9.setRotationPoint(0.5F, -3F, -32F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      chargeur = new ModelRenderer(this, 24, 27);
      chargeur.addBox(0F, 0F, 0F, 6, 6, 4, scale);
      chargeur.setRotationPoint(2F, 1F, -27F);
      chargeur.setTextureSize(64, 64);
      chargeur.mirror = true;
      setRotation(chargeur, 0F, 0F, 0.7853982F);
      Shape14 = new ModelRenderer(this, 24, 0);
      Shape14.addBox(0F, 0F, 0F, 3, 11, 3, scale);
      Shape14.setRotationPoint(0.5F, 3F, -17F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0.4089647F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 43, 24);
      Shape15.addBox(0F, 0F, 0F, 2, 3, 1, scale);
      Shape15.setRotationPoint(1F, 3.5F, -22F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 43, 24);
      Shape16.addBox(0F, 0F, 0F, 2, 1, 6, scale);
      Shape16.setRotationPoint(1F, 6F, -21F);
      Shape16.setTextureSize(64, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 43, 24);
      Shape17.addBox(0F, 0F, 0F, 2, 3, 1, scale);
      Shape17.setRotationPoint(1F, 3F, -18F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, -0.4833219F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 24, 0);
      Shape18.addBox(0F, -4F, 0F, 1, 5, 1, scale);
      Shape18.setRotationPoint(1.5F, 0F, -52F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, 0.2602503F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 24, 0);
      Shape19.addBox(0F, 0F, 0F, 1, 5, 1, scale);
      Shape19.setRotationPoint(1.5F, -4F, -53F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 43, 24);
      Shape20.addBox(-2F, 0F, 0F, 8, 1, 1, scale);
      Shape20.setRotationPoint(0F, 0F, -16F);
      Shape20.setTextureSize(64, 64);
      Shape20.mirror = true;
      setRotation(Shape20, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 24, 0);
      Shape10.addBox(0F, 0F, 0F, 2, 2, 11, scale);
      Shape10.setRotationPoint(1F, 1.5F, -53F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 24, 0);
      Shape21.addBox(1.5F, 0F, 0F, 1, 4, 1, scale);
      Shape21.setRotationPoint(0F, -1F, -50F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    chargeur.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
    Shape20.render(f5);
    Shape10.render(f5);
    Shape21.render(f5);
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
				if(cooldown >= 0.2F)
				{
					this.Shape17.rotateAngleX = 0;
					if(cooldown >= 0.6F)
					{
						this.Shape20.rotationPointZ = -14 + (14 * (cooldown - 1F) * 2.5F);
					}
					else
					{
						this.Shape20.rotationPointZ = -14 - (14 * (cooldown - 0.2F) * 2.5F);
					}
				}
			}
			else
			{
				if(reload > 0.8 && reload != 0)
				{
					this.chargeur.rotationPointY = 84 - (70 * ((reload - 0.8F) * 5));
				}
				else if(reload == 0.8)
				{
					this.chargeur.isHidden = true;
				}
				else
				{
					if(reload < 0.2)
					{
						this.chargeur.isHidden = false;
						this.chargeur.rotationPointY = 1 + (5 * (reload * 10));
					}
					else
					{
						this.chargeur.isHidden = true;
					}
				}
			}
		}
		
		float f5 = 0.0625F;
		Shape1.render(f5);
	    Shape3.render(f5);
	    Shape4.render(f5);
	    Shape5.render(f5);
	    Shape6.render(f5);
	    Shape8.render(f5);
	    Shape9.render(f5);
	    chargeur.render(f5);
	    Shape14.render(f5);
	    Shape15.render(f5);
	    Shape16.render(f5);
	    Shape17.render(f5);
	    Shape18.render(f5);
	    Shape19.render(f5);
	    Shape20.render(f5);
	    Shape10.render(f5);
	    Shape21.render(f5);
	}

}