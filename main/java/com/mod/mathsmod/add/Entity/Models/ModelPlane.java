package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class ModelPlane extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Elice1;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Elice2;
    ModelRenderer Elice3;
    ModelRenderer Shape19;
    ModelRenderer Shape19_2;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
  
  public ModelPlane()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 6, 6);
      Shape1.addBox(-4F, -4F, 0F, 8, 9, 17);
      Shape1.setRotationPoint(0F, 11F, -21F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 6, 6);
      Shape2.addBox(-4F, -4F, 0F, 8, 8, 17);
      Shape2.setRotationPoint(0F, 11F, -4F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 33);
      Shape3.addBox(-2F, 0F, 0F, 4, 6, 12);
      Shape3.setRotationPoint(0F, 7F, 13F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(-3F, -3F, 0F, 6, 6, 4);
      Shape4.setRotationPoint(0F, 11F, -25F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 5, 41);
      Shape5.addBox(-3F, 0F, 0F, 6, 3, 5);
      Shape5.setRotationPoint(0F, 4F, 9F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, -0.7063936F, 0F, 0F);
      Elice1 = new ModelRenderer(this, 0, 10);
      Elice1.addBox(-1.5F, -1.5F, -1F, 3, 3, 2);
      Elice1.setRotationPoint(0F, 11F, -26F);
      Elice1.setTextureSize(64, 64);
      Elice1.mirror = true;
      setRotation(Elice1, 0F, 0F, 0.7853982F);
      Shape6 = new ModelRenderer(this, 54, 10);
      Shape6.addBox(-1F, -6F, 0F, 1, 6, 4);
      Shape6.setRotationPoint(0.5F, 7F, 25F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, -0.4089647F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 44, 0);
      Shape7.addBox(0F, 0F, -4F, 6, 1, 4);
      Shape7.setRotationPoint(0F, 9F, 28F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, -0.4089647F, 0F);
      Shape8 = new ModelRenderer(this, 44, 0);
      Shape8.addBox(-6F, 0F, -4F, 6, 1, 4);
      Shape8.setRotationPoint(0F, 9F, 28F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0.4089656F, 0F);
      Shape9 = new ModelRenderer(this, 7, 13);
      Shape9.addBox(-2F, -2F, 0F, 4, 4, 4);
      Shape9.setRotationPoint(0F, 9F, 25F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 0, 55);
      Shape10.addBox(0F, 0F, -4F, 24, 1, 8);
      Shape10.setRotationPoint(4F, 12F, -9F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 55);
      Shape11.addBox(-24F, 0F, -4F, 24, 1, 8);
      Shape11.setRotationPoint(-4F, 12F, -9F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 0, 53);
      Shape12.addBox(0F, 0F, 0F, 24, 1, 1);
      Shape12.setRotationPoint(4F, 12F, -5F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 53);
      Shape13.addBox(-24F, 0F, 0F, 24, 1, 1);
      Shape13.setRotationPoint(-4F, 12F, -5F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 20, 0);
      Shape14.addBox(0F, 0F, 0F, 2, 1, 5);
      Shape14.setRotationPoint(28F, 12F, -11F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 20, 0);
      Shape15.addBox(-2F, 0F, 0F, 2, 1, 5);
      Shape15.setRotationPoint(-28F, 12F, -11F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 35, 40);
      Shape16.addBox(-3F, 0F, -9F, 6, 4, 9);
      Shape16.setRotationPoint(0F, 4F, 4F);
      Shape16.setTextureSize(64, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0.3346075F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 18, 17);
      Shape17.addBox(-3.5F, 0F, 0F, 7, 4, 6);
      Shape17.setRotationPoint(0F, 4F, 3F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 0, 33);
      Shape18.addBox(-2.01F, 0F, 0F, 4, 4, 12);
      Shape18.setRotationPoint(0F, 4F, 9F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, -0.2557062F, 0F, 0F);
      Elice2 = new ModelRenderer(this, 0, 30);
      Elice2.addBox(-1F, -1F, -1F, 2, 2, 1);
      Elice2.setRotationPoint(0F, 11F, -27F);
      Elice2.setTextureSize(64, 64);
      Elice2.mirror = true;
      setRotation(Elice2, 0F, 0F, 0.7853982F);
      Elice3 = new ModelRenderer(this, 39, 20);
      Elice3.addBox(-6F, -1F, 0F, 12, 2, 1);
      Elice3.setRotationPoint(0F, 11F, -26.5F);
      Elice3.setTextureSize(64, 64);
      Elice3.mirror = true;
      setRotation(Elice3, 0F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 40, 9);
      Shape19.addBox(-1F, 0F, -0.5F, 2, 6, 4);
      Shape19.setRotationPoint(3F, 7.1F, 12.75F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, -0.5576792F, 0F);
      Shape19_2 = new ModelRenderer(this, 40, 9);
      Shape19_2.addBox(-1F, 0F, -0.5F, 2, 6, 4);
      Shape19_2.setRotationPoint(-3F, 7.1F, 12.75F);
      Shape19_2.setTextureSize(64, 64);
      Shape19_2.mirror = true;
      setRotation(Shape19_2, 0F, 0.5576851F, 0F);
      Shape20 = new ModelRenderer(this, 44, 32);
      Shape20.addBox(-2F, -2F, 0F, 4, 2, 6);
      Shape20.setRotationPoint(0.01F, 15F, 13F);
      Shape20.setTextureSize(64, 64);
      Shape20.mirror = true;
      setRotation(Shape20, 0.4089647F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 24, 32);
      Shape21.addBox(-1.5F, -2F, 0F, 3, 2, 4);
      Shape21.setRotationPoint(0F, 13F, 25F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0.5205006F, 0F, 0F);
      Shape22 = new ModelRenderer(this, 39, 5);
      Shape22.addBox(-3.5F, -1F, 0F, 7, 1, 2);
      Shape22.setRotationPoint(0F, 16F, -4F);
      Shape22.setTextureSize(64, 64);
      Shape22.mirror = true;
      setRotation(Shape22, 0.5948578F, 0F, 0F);
      Shape23 = new ModelRenderer(this, 37, 0);
      Shape23.addBox(0F, 0F, -2F, 3, 1, 2);
      Shape23.setRotationPoint(28F, 12.02F, -4F);
      Shape23.setTextureSize(64, 64);
      Shape23.mirror = true;
      setRotation(Shape23, 0F, 0.8551081F, 0F);
      Shape24 = new ModelRenderer(this, 37, 0);
      Shape24.addBox(-3F, 0F, -2F, 3, 1, 2);
      Shape24.setRotationPoint(-28F, 12.02F, -4F);
      Shape24.setTextureSize(64, 64);
      Shape24.mirror = true;
      setRotation(Shape24, 0F, -0.8551066F, 0F);
      Shape25 = new ModelRenderer(this, 37, 0);
      Shape25.addBox(0F, 0F, -2F, 3, 1, 2);
      Shape25.setRotationPoint(-28F, 12.02F, -13F);
      Shape25.setTextureSize(64, 64);
      Shape25.mirror = true;
      setRotation(Shape25, 0F, -2.316639F, 0F);
      Shape26 = new ModelRenderer(this, 37, 0);
      Shape26.addBox(-3F, 0F, -2F, 3, 1, 2);
      Shape26.setRotationPoint(28F, 12.02F, -13F);
      Shape26.setTextureSize(64, 64);
      Shape26.mirror = true;
      setRotation(Shape26, 0F, 2.316645F, 0F);
      Shape27 = new ModelRenderer(this, 24, 38);
      Shape27.addBox(-2.5F, -2F, -4F, 5, 2, 4);
      Shape27.setRotationPoint(0F, 16F, -21F);
      Shape27.setTextureSize(64, 64);
      Shape27.mirror = true;
      setRotation(Shape27, -0.5205006F, 0F, 0F);
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
    Elice1.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Elice2.render(f5);
    Elice3.render(f5);
    Shape19.render(f5);
    Shape19.render(f5);
    Shape20.render(f5);
    Shape21.render(f5);
    Shape22.render(f5);
    Shape23.render(f5);
    Shape24.render(f5);
    Shape25.render(f5);
    Shape26.render(f5);
    Shape27.render(f5);
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
    if(entity instanceof EntityPlane)
    {
    	float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
    	EntityPlane ent = (EntityPlane) entity;
    	if(ent.rotation - ent.prevRotation > 2F)
    	{
    		this.Elice1.rotateAngleZ = (ent.rotation + partialTicks) * 20 * 0.017453292F;
        	this.Elice2.rotateAngleZ = (ent.rotation + partialTicks) * 20 * 0.017453292F;
        	this.Elice3.rotateAngleZ = (ent.rotation + partialTicks) * 20 * 0.017453292F;
    	}
    	else
    	{
    		this.Elice1.rotateAngleZ = ent.rotation * 20 * 0.017453292F;
        	this.Elice2.rotateAngleZ = ent.rotation * 20 * 0.017453292F;
        	this.Elice3.rotateAngleZ = ent.rotation * 20 * 0.017453292F;
    	}
    }
  }

  	@Override
  	public void renderModel(ItemStack stack, Entity entity) 
	{
  		float f5 = 0.0625F;
  		Shape1.render(f5);
  	    Shape2.render(f5);
  	    Shape3.render(f5);
  	    Shape4.render(f5);
  	    Shape5.render(f5);
  	    Elice1.render(f5);
  	    Shape6.render(f5);
  	    Shape7.render(f5);
  	    Shape8.render(f5);
  	    Shape9.render(f5);
  	    Shape10.render(f5);
  	    Shape11.render(f5);
  	    Shape12.render(f5);
  	    Shape13.render(f5);
  	    Shape14.render(f5);
  	    Shape15.render(f5);
  	    Shape16.render(f5);
  	    Shape17.render(f5);
  	    Shape18.render(f5);
  	    Elice2.render(f5);
  	    Elice3.render(f5);
  	    Shape19.render(f5);
  	    Shape19.render(f5);
  	    Shape20.render(f5);
  	    Shape21.render(f5);
  	    Shape22.render(f5);
  	    Shape23.render(f5);
  	    Shape24.render(f5);
  	    Shape25.render(f5);
  	    Shape26.render(f5);
  	    Shape27.render(f5);
	}

}
