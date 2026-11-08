package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class modelAA12 extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Charger;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer lever;
  
  public modelAA12(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 24, 0);
      Shape1.addBox(0F, 0F, 0F, 4, 11, 1, scale);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 24, 0);
      Shape2.addBox(0F, 0F, 0F, 4, 5, 16, scale);
      Shape2.setRotationPoint(0F, 0F, -16F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 24, 0);
      Shape3.addBox(0F, 0F, 1F, 3, 5, 16, scale);
      Shape3.setRotationPoint(0.5F, 0F, -15F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, -0.3520608F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 24, 0);
      Shape4.addBox(0F, 1.7F, 17.2F, 3, 2, 1, scale);
      Shape4.setRotationPoint(0.5F, 0F, -15F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, -0.426418F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 24, 0);
      Shape5.addBox(0F, 3F, 17F, 3, 2, 1, scale);
      Shape5.setRotationPoint(0.5F, 0F, -15F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, -0.3346075F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 24, 0);
      Shape7.addBox(0F, 0F, 0F, 4, 2, 4, scale);
      Shape7.setRotationPoint(0F, 5F, -4F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 24, 4);
      Shape8.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape8.setRotationPoint(0F, 0F, -32F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 24, 4);
      Shape9.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape9.setRotationPoint(0F, 0F, -48F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 24, 4);
      Shape10.addBox(0F, 0F, 0F, 4, 3, 9, scale);
      Shape10.setRotationPoint(0F, 4F, -28F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 24, 4);
      Shape11.addBox(0F, 0F, -1F, 3, 4, 4, scale);
      Shape11.setRotationPoint(0.5F, 2F, -19F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0.5576792F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 24, 4);
      Shape12.addBox(0F, 0F, 0F, 5, 2, 4, scale);
      Shape12.setRotationPoint(-0.5F, 7.466667F, -20.23333F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 1.264073F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 24, 4);
      Shape13.addBox(0F, 0F, 0F, 3, 4, 14, scale);
      Shape13.setRotationPoint(0.5F, 4F, -20F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, -1.041001F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 24, 26);
      Shape14.addBox(0F, 0F, 0F, 2, 1, 8, scale);
      Shape14.setRotationPoint(1F, 9F, -27F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 24, 26);
      Shape15.addBox(0F, 1F, 0.1F, 2, 6, 1, scale);
      Shape15.setRotationPoint(1F, 2F, -28F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Charger = new ModelRenderer(this, 0, 44);
      Charger.addBox(0F, 0F, 0F, 12, 12, 4, scale);
      Charger.setRotationPoint(2F, 2F, -32F);
      Charger.setTextureSize(64, 64);
      Charger.mirror = true;
      setRotation(Charger, 0F, 0F, 0.7853982F);
      Shape17 = new ModelRenderer(this, 24, 4);
      Shape17.addBox(0F, 0F, 0F, 3, 4, 16, scale);
      Shape17.setRotationPoint(0.5F, 0F, -47F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, -0.1487144F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 24, 34);
      Shape18.addBox(1F, 1F, 0F, 1, 1, 5, scale);
      Shape18.setRotationPoint(0.5F, 0.5F, -57F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, 0F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 24, 4);
      Shape19.addBox(0F, 0F, 0F, 4, 4, 4, scale);
      Shape19.setRotationPoint(0F, 0F, -52F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 24, 34);
      Shape20.addBox(0F, -3F, 0F, 1, 4, 2, scale);
      Shape20.setRotationPoint(1.5F, 1F, -51F);
      Shape20.setTextureSize(64, 64);
      Shape20.mirror = true;
      setRotation(Shape20, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 24, 34);
      Shape21.addBox(0F, 0F, 0F, 1, 4, 2, scale);
      Shape21.setRotationPoint(1.5F, -2F, -19.33333F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
      lever = new ModelRenderer(this, 24, 34);
      lever.addBox(0F, 0F, 0F, 1, 4, 1, scale);
      lever.setRotationPoint(1.5F, -1F, -34F);
      lever.setTextureSize(64, 64);
      lever.mirror = true;
      setRotation(lever, 0F, 0F, 0F);
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
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Charger.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
    Shape20.render(f5);
    Shape21.render(f5);
    lever.render(f5);
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
			
			if(!item.getReloading(stack))
			{
				if(cooldown != 0)
				{
					//this.Shape15.rotateAngleX = 0;
					if(cooldown > 0.5F)
					{
						this.lever.rotationPointZ = -34 - ((cooldown - 1F) * 20);
					}
					else
					{
						this.lever.rotationPointZ = -34 + (cooldown * 20);
					}
				}
			}
			else
			{
				if(reload > 0.8 && reload != 0)
				{
					this.Charger.rotationPointY = 80 - (78 * ((reload - 0.8F) * 5));
				}
				else if(reload == 0.8)
				{
					this.Charger.isHidden = true;
				}
				else
				{
					if(reload < 0.2)
					{
						this.Charger.isHidden = false;
						this.Charger.rotationPointY = 2 + (5 * (reload * 10));
					}
					else
					{
						this.Charger.isHidden = true;
					}
				}
			}
		}
  		
  		float f5 = 0.0625F;
  		Shape1.render(f5);
  	    Shape2.render(f5);
  	    Shape3.render(f5);
  	    Shape4.render(f5);
  	    Shape5.render(f5);
  	    Shape7.render(f5);
  	    Shape8.render(f5);
  	    Shape9.render(f5);
  	    Shape10.render(f5);
  	    Shape11.render(f5);
  	    Shape12.render(f5);
  	    Shape13.render(f5);
  	    Shape14.render(f5);
  	    Shape15.render(f5);
  	    Charger.render(f5);
  	    Shape17.render(f5);
  	    Shape18.render(f5);
  	    Shape19.render(f5);
  	    Shape20.render(f5);
  	    Shape21.render(f5);
  	    lever.render(f5);
  	}

}
