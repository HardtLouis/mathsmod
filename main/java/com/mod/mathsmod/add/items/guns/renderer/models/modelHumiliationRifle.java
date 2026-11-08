package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelHumiliationRifle extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape26;
    ModelRenderer Levier;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Charger;
    ModelRenderer Shape5;
  
  public modelHumiliationRifle(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 2, 44);
      Shape1.addBox(0F, -0.5F, 1F, 3, 5, 6, scale);
      Shape1.setRotationPoint(0.5F, -3.5F, -1F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 24, 24);
      Shape7.addBox(0F, 0F, 0F, 4, 4, 12, scale);
      Shape7.setRotationPoint(0F, -4F, -12F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 24, 24);
      Shape8.addBox(0F, 0F, -4F, 4, 4, 16, scale);
      Shape8.setRotationPoint(0F, -4F, -24F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 24, 49);
      Shape10.addBox(0F, 2F, 0F, 2, 10, 3, scale);
      Shape10.setRotationPoint(1F, -1F, -10F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0.4089647F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 24, 24);
      Shape11.addBox(0F, 0F, 0F, 3, 2, 7, scale);
      Shape11.setRotationPoint(0.5F, 0F, -20F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 24, 24);
      Shape12.addBox(0F, 0F, 0F, 3, 1, 7, scale);
      Shape12.setRotationPoint(0.5F, 0F, -13F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 41, 0);
      Shape13.addBox(0F, 0F, 0F, 1, 1, 5, scale);
      Shape13.setRotationPoint(1.5F, 3F, -11F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 41, 0);
      Shape14.addBox(0F, 0F, 0F, 1, 2, 1, scale);
      Shape14.setRotationPoint(1.5F, 1F, -12F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 41, 0);
      Shape15.addBox(0F, -1F, 0F, 1, 3, 1, scale);
      Shape15.setRotationPoint(1.5F, 0.8F, -10F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, -0.3346075F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 24, 24);
      Shape17.addBox(0F, 0F, 0F, 3, 3, 4, scale);
      Shape17.setRotationPoint(0.5F, -3.5F, -28F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 0, 23);
      Shape18.addBox(0F, 0F, 0F, 2, 8, 2, scale);
      Shape18.setRotationPoint(1F, -2F, -36F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, 1.570796F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 12, 24);
      Shape19.addBox(0F, 0F, 0F, 1, 11, 1, scale);
      Shape19.setRotationPoint(1.5F, -2.6F, -47F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 1.570796F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 24, 24);
      Shape20.addBox(0F, 0F, 0F, 2, 2, 1, scale);
      Shape20.setRotationPoint(1F, -4F, -45F);
      Shape20.setTextureSize(64, 64);
      Shape20.mirror = true;
      setRotation(Shape20, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 24, 24);
      Shape21.addBox(0F, 0F, 0.01F, 1, 3, 1, scale);
      Shape21.setRotationPoint(1.5F, -6.5F, -45F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
      Shape26 = new ModelRenderer(this, 24, 24);
      Shape26.addBox(0F, 0F, 0F, 1, 3, 1, scale);
      Shape26.setRotationPoint(1.5F, -6F, -19F);
      Shape26.setTextureSize(64, 64);
      Shape26.mirror = true;
      setRotation(Shape26, 0F, 0F, 0F);
      Levier = new ModelRenderer(this, 24, 24);
      Levier.addBox(0F, 0F, 0F, 8, 1, 1, scale);
      Levier.setRotationPoint(-2F, -3F, -15F);
      Levier.setTextureSize(64, 64);
      Levier.mirror = true;
      setRotation(Levier, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 2, 44);
      Shape2.addBox(0F, 0F, 0F, 3, 8, 3, scale);
      Shape2.setRotationPoint(0.5F, -4.5F, 9F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 2, 44);
      Shape3.addBox(0F, 0F, 0F, 3, 7, 3, scale);
      Shape3.setRotationPoint(0.4666667F, -4.5F, 6F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Charger = new ModelRenderer(this, 24, 24);
      Charger.addBox(0F, 0F, 0F, 2, 9, 5, scale);
      Charger.setRotationPoint(1F, 2F, -19F);
      Charger.setTextureSize(64, 64);
      Charger.mirror = true;
      setRotation(Charger, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 2, 44);
      Shape5.addBox(0F, 0F, 0F, 5, 2, 7, scale);
      Shape5.setRotationPoint(-0.5F, -1.8F, -27F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
    Shape20.render(f5);
    Shape21.render(f5);
    Shape26.render(f5);
    Levier.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Charger.render(f5);
    Shape5.render(f5);
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
					this.Shape15.rotateAngleX = 0;
					if(cooldown >= 0.6F)
					{
						this.Levier.rotationPointZ = -15 - (14 * (cooldown - 1F) * 2.5F);
					}
					else
					{
						this.Levier.rotationPointZ = -15 + (14 * (cooldown - 0.2F) * 2.5F);
					}
				}
			}
			else
			{
				if(reload > 0.8 && reload != 0)
				{
					this.Charger.rotationPointY = 72 - (70 * ((reload - 0.8F) * 5));
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
  	    Shape7.render(f5);
  	    Shape8.render(f5);
  	    Shape10.render(f5);
  	    Shape11.render(f5);
  	    Shape12.render(f5);
  	    Shape13.render(f5);
  	    Shape14.render(f5);
  	    Shape15.render(f5);
  	    Shape17.render(f5);
  	    Shape18.render(f5);
  	    Shape19.render(f5);
  	    Shape20.render(f5);
  	    Shape21.render(f5);
  	    Shape26.render(f5);
  	    Levier.render(f5);
  	    Shape2.render(f5);
  	    Shape3.render(f5);
  	    Charger.render(f5);
  	    Shape5.render(f5);
  	}

}
