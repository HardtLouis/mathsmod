package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMinigun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class modelMinigun extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer canon1;
    ModelRenderer canon1p2;
    ModelRenderer canon2;
    ModelRenderer canon2p2;
    ModelRenderer canon3;
    ModelRenderer canon3p2;
    ModelRenderer canon4;
    ModelRenderer canon4p2;
    ModelRenderer canonplate;
    ModelRenderer canonplate2;
  
  public modelMinigun(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 24, 0);
      Shape1.addBox(0F, 0F, 0F, 4, 4, 12, scale);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 24, 0);
      Shape2.addBox(0F, 0F, 0F, 4, 4, 4, scale);
      Shape2.setRotationPoint(0F, 4F, -2F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 24, 0);
      Shape3.addBox(0F, 0F, 0F, 4, 4, 4, scale);
      Shape3.setRotationPoint(0F, 4F, 10F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 24, 0);
      Shape4.addBox(0F, 0F, 0F, 12, 8, 8, scale);
      Shape4.setRotationPoint(-4F, 8F, -2F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 24, 8);
      Shape5.addBox(0F, 0F, 0F, 12, 8, 8, scale);
      Shape5.setRotationPoint(-4F, 8F, 6F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 24, 8);
      Shape6.addBox(0F, 0F, 0F, 12, 8, 8, scale);
      Shape6.setRotationPoint(-4F, 16F, -2F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 24, 8);
      Shape7.addBox(0F, -1F, 0F, 12, 8, 8, scale);
      Shape7.setRotationPoint(-4F, 17F, 6F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 24, 0);
      Shape8.addBox(-8F, 12F, 2F, 4, 8, 8, scale);
      Shape8.setRotationPoint(0F, 0F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 24, 0);
      Shape9.addBox(0F, 0F, 0F, 4, 4, 12, scale);
      Shape9.setRotationPoint(0F, 11F, 11F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0.5205006F, 0F, 0F);
      canon1 = new ModelRenderer(this, 24, 23);
      canon1.addBox(0F, 3F, 2F, 18, 2, 2, scale);
      canon1.setRotationPoint(8F, 16F, 7F);
      canon1.setTextureSize(64, 64);
      canon1.mirror = true;
      setRotation(canon1, 0F, 0F, 0F);
      canon1p2 = new ModelRenderer(this, 24, 23);
      canon1p2.addBox(18F, 3F, 3F, 18, 2, 2, scale);
      canon1p2.setRotationPoint(8F, 16F, 6F);
      canon1p2.setTextureSize(64, 64);
      canon1p2.mirror = true;
      setRotation(canon1p2, 0F, 0F, 0F);
      canon2 = new ModelRenderer(this, 24, 23);
      canon2.addBox(0F, -5F, 3F, 18, 2, 2, scale);
      canon2.setRotationPoint(8F, 16F, 6F);
      canon2.setTextureSize(64, 64);
      canon2.mirror = true;
      setRotation(canon2, 0F, 0F, 0F);
      canon2p2 = new ModelRenderer(this, 24, 23);
      canon2p2.addBox(18F, -5F, 3F, 18, 2, 2, scale);
      canon2p2.setRotationPoint(8F, 16F, 6F);
      canon2p2.setTextureSize(64, 64);
      canon2p2.mirror = true;
      setRotation(canon2p2, 0F, 0F, 0F);
      canon3 = new ModelRenderer(this, 24, 23);
      canon3.addBox(0F, 3F, -5F, 18, 2, 2, scale);
      canon3.setRotationPoint(8F, 16F, 6F);
      canon3.setTextureSize(64, 64);
      canon3.mirror = true;
      setRotation(canon3, 0F, 0F, 0F);
      canon3p2 = new ModelRenderer(this, 24, 23);
      canon3p2.addBox(17F, 3F, -5F, 18, 2, 2, scale);
      canon3p2.setRotationPoint(9F, 16F, 6F);
      canon3p2.setTextureSize(64, 64);
      canon3p2.mirror = true;
      setRotation(canon3p2, 0F, 0F, 0F);
      canon4 = new ModelRenderer(this, 24, 23);
      canon4.addBox(0F, -5F, -5F, 18, 2, 2, scale);
      canon4.setRotationPoint(8F, 16F, 6F);
      canon4.setTextureSize(64, 64);
      canon4.mirror = true;
      setRotation(canon4, 0F, 0F, 0F);
      canon4p2 = new ModelRenderer(this, 24, 23);
      canon4p2.addBox(18F, -5F, -5F, 18, 2, 2, scale);
      canon4p2.setRotationPoint(8F, 16F, 6F);
      canon4p2.setTextureSize(64, 64);
      canon4p2.mirror = true;
      setRotation(canon4p2, 0F, 0F, 0F);
      canonplate = new ModelRenderer(this, 24, 0);
      canonplate.addBox(13F, -6F, -6F, 1, 12, 12, scale);
      canonplate.setRotationPoint(8F, 16F, 6F);
      canonplate.setTextureSize(64, 64);
      canonplate.mirror = true;
      setRotation(canonplate, 0F, 0F, 0F);
      canonplate2 = new ModelRenderer(this, 24, 0);
      canonplate2.addBox(0F, -6F, -6F, 1, 12, 12, scale);
      canonplate2.setRotationPoint(38F, 16F, 6F);
      canonplate2.setTextureSize(64, 64);
      canonplate2.mirror = true;
      setRotation(canonplate2, 0F, 0F, 0F);
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
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    canon1.render(f5);
    canon1p2.render(f5);
    canon2.render(f5);
    canon2p2.render(f5);
    canon3.render(f5);
    canon3p2.render(f5);
    canon4.render(f5);
    canon4p2.render(f5);
    canonplate.render(f5);
    canonplate2.render(f5);
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
  		GlStateManager.rotate(90, 0, 1, 0);
  		GlStateManager.translate(1F, 0, -0.5);
  		if(stack.getItem() instanceof ItemMinigun)
		{
  			ItemMinigun item = (ItemMinigun) stack.getItem();
  			float e = ((float)item.getShootTicks(stack)) / 400F;
  			
  			GlStateManager.color(1, 1 - e, 1 - e);
  			float i = (item.getShootTicks(stack) - Minecraft.getMinecraft().getRenderPartialTicks()) * 12.5F * 0.017453292F;
  			if(item.getShootTicks(stack) == 0)
  			{
  				i = 0;
  			}
  			this.canon1.rotateAngleX = i;
  			this.canon2.rotateAngleX = i;
  			this.canon3.rotateAngleX = i;
  			this.canon4.rotateAngleX = i;
  			this.canon1p2.rotateAngleX = i;
  			this.canon2p2.rotateAngleX = i;
  			this.canon3p2.rotateAngleX = i;
  			this.canon4p2.rotateAngleX = i;
  			this.canonplate.rotateAngleX = i;
  			this.canonplate2.rotateAngleX = i;
  			
		}
  		
  		float f5 = 0.0625F;
  		Shape1.render(f5);
  	    Shape2.render(f5);
  	    Shape3.render(f5);
  	    Shape4.render(f5);
  	    Shape5.render(f5);
  	    Shape6.render(f5);
  	    Shape7.render(f5);
  	    Shape8.render(f5);
  	    Shape9.render(f5);
  	    canon1.render(f5);
  	    canon1p2.render(f5);
  	    canon2.render(f5);
  	    canon2p2.render(f5);
  	    canon3.render(f5);
  	    canon3p2.render(f5);
  	    canon4.render(f5);
  	    canon4p2.render(f5);
  	    canonplate.render(f5);
  	    canonplate2.render(f5);
  	    GlStateManager.translate(-1F, 0, 0.5);
  	    GlStateManager.rotate(-90, 0, 1, 0);
  	    GlStateManager.color(1, 1, 1);
  	}

}
