package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelBurstShotgun extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Chargeur;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape22;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer gachette;
    ModelRenderer levier1;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer levier32;
    ModelRenderer Shape33;
    ModelRenderer Shape2;
  
  public modelBurstShotgun(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 28, 24);
      Shape1.addBox(0F, 0F, 0F, 3, 2, 10, scale);
      Shape1.setRotationPoint(0.5F, 2F, -4F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 28, 0);
      Shape4.addBox(0F, 0F, 0F, 4, 7, 12, scale);
      Shape4.setRotationPoint(0F, 1F, -16F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 49, 11);
      Shape5.addBox(0F, 0F, 0F, 3, 12, 1, scale);
      Shape5.setRotationPoint(0.5F, 9F, -16F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 1.653312F, 0F, 0F);
      Chargeur = new ModelRenderer(this, 28, 24);
      Chargeur.addBox(0F, 0F, 0F, 6, 4, 12, scale);
      Chargeur.setRotationPoint(-1F, 4F, -42F);
      Chargeur.setTextureSize(64, 64);
      Chargeur.mirror = true;
      setRotation(Chargeur, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 28, 0);
      Shape8.addBox(0F, 0F, 0F, 4, 6, 8, scale);
      Shape8.setRotationPoint(0F, 1F, -28F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 51, 15);
      Shape9.addBox(0F, 0F, 0F, 3, 4, 3, scale);
      Shape9.setRotationPoint(0.51F, 6F, -18.7F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0.7435722F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 1, 43);
      Shape10.addBox(0F, 0F, -2F, 4, 6, 15, scale);
      Shape10.setRotationPoint(0F, 1F, -41F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 28, 0);
      Shape11.addBox(0F, 0F, 0F, 4, 5, 4, scale);
      Shape11.setRotationPoint(0F, 1F, -20F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 28, 0);
      Shape12.addBox(0F, 0F, 0F, 3, 3, 1, scale);
      Shape12.setRotationPoint(0.5F, 7F, -20F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 2.193538F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 28, 0);
      Shape13.addBox(0F, 0F, 0F, 3, 1, 2, scale);
      Shape13.setRotationPoint(0.5F, 5.5F, -20F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape22 = new ModelRenderer(this, 28, 24);
      Shape22.addBox(0F, 0F, 0F, 1, 1, 7, scale);
      Shape22.setRotationPoint(1.5F, 4F, -54F);
      Shape22.setTextureSize(64, 64);
      Shape22.mirror = true;
      setRotation(Shape22, 0F, 0F, 0F);
      Shape24 = new ModelRenderer(this, 28, 48);
      Shape24.addBox(0F, 1F, 0F, 3, 11, 3, scale);
      Shape24.setRotationPoint(0.51F, 5F, -16F);
      Shape24.setTextureSize(64, 64);
      Shape24.mirror = true;
      setRotation(Shape24, 0.3717861F, 0F, 0F);
      Shape25 = new ModelRenderer(this, 25, 16);
      Shape25.addBox(0F, 0F, -1F, 2, 1, 5, scale);
      Shape25.setRotationPoint(1F, 11F, -17F);
      Shape25.setTextureSize(64, 64);
      Shape25.mirror = true;
      setRotation(Shape25, 0F, 0F, 0F);
      Shape26 = new ModelRenderer(this, 28, 0);
      Shape26.addBox(0F, 1F, 0F, 2, 7, 1, scale);
      Shape26.setRotationPoint(1F, 4F, -20.26667F);
      Shape26.setTextureSize(64, 64);
      Shape26.mirror = true;
      setRotation(Shape26, 0.2974289F, 0F, 0F);
      gachette = new ModelRenderer(this, 28, 0);
      gachette.addBox(0F, 0.5F, 0F, 1, 2, 1, scale);
      gachette.setRotationPoint(1.5F, 8F, -15.8F);
      gachette.setTextureSize(64, 64);
      gachette.mirror = true;
      setRotation(gachette, -0.3717861F, 0F, 0F);
      levier1 = new ModelRenderer(this, 28, 0);
      levier1.addBox(0F, 0F, -2F, 2, 1, 16, scale);
      levier1.setRotationPoint(1F, 0F, -38F);
      levier1.setTextureSize(64, 64);
      levier1.mirror = true;
      setRotation(levier1, 0F, 0F, 0F);
      Shape27 = new ModelRenderer(this, 24, 0);
      Shape27.addBox(0F, 0F, 1F, 3, 4, 3, scale);
      Shape27.setRotationPoint(0.51F, 1.8F, -5F);
      Shape27.setTextureSize(64, 64);
      Shape27.mirror = true;
      setRotation(Shape27, -0.5948578F, 0F, 0F);
      Shape28 = new ModelRenderer(this, 28, 24);
      Shape28.addBox(0F, 0F, 0F, 3, 9, 2, scale);
      Shape28.setRotationPoint(0.5F, 2.01F, 6F);
      Shape28.setTextureSize(64, 64);
      Shape28.mirror = true;
      setRotation(Shape28, 0F, 0F, 0F);
      Shape29 = new ModelRenderer(this, 2, 44);
      Shape29.addBox(0F, 0F, 0F, 4, 4, 4, scale);
      Shape29.setRotationPoint(0F, 2F, -47F);
      Shape29.setTextureSize(64, 64);
      Shape29.mirror = true;
      setRotation(Shape29, 0F, 0F, 0F);
      Shape30 = new ModelRenderer(this, 51, 15);
      Shape30.addBox(0F, 0F, 0F, 3, 4, 2, scale);
      Shape30.setRotationPoint(0.5F, 6F, -46F);
      Shape30.setTextureSize(64, 64);
      Shape30.mirror = true;
      setRotation(Shape30, 1.33843F, 0F, 0F);
      Shape31 = new ModelRenderer(this, 51, 15);
      Shape31.addBox(0F, 0F, 0F, 3, 4, 2, scale);
      Shape31.setRotationPoint(0.5F, 4F, -46F);
      Shape31.setTextureSize(64, 64);
      Shape31.mirror = true;
      setRotation(Shape31, 1.858931F, 0F, 0F);
      levier32 = new ModelRenderer(this, 28, 0);
      levier32.addBox(0F, 0F, 0F, 2, 1, 16, scale);
      levier32.setRotationPoint(1F, 0F, -24F);
      levier32.setTextureSize(64, 64);
      levier32.mirror = true;
      setRotation(levier32, 0F, 0F, 0F);
      Shape33 = new ModelRenderer(this, 28, 24);
      Shape33.addBox(0F, 0F, 0F, 3, 14, 2, scale);
      Shape33.setRotationPoint(0.49F, 9.2F, 7F);
      Shape33.setTextureSize(64, 64);
      Shape33.mirror = true;
      setRotation(Shape33, -2.007645F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 26, 26);
      Shape2.addBox(0F, 0F, 0F, 6, 1, 1, scale);
      Shape2.setRotationPoint(-1F, 2F, -28F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Chargeur.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape22.render(f5);
    Shape24.render(f5);
    Shape25.render(f5);
    Shape26.render(f5);
    gachette.render(f5);
    levier1.render(f5);
    Shape27.render(f5);
    Shape28.render(f5);
    Shape29.render(f5);
    Shape30.render(f5);
    Shape31.render(f5);
    levier32.render(f5);
    Shape33.render(f5);
    Shape2.render(f5);
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
					this.gachette.rotateAngleX = 0;
					if(cooldown >= 0.125F)
					{
						this.Chargeur.rotationPointZ = -42 + (cooldown - 0.25F) * -56;
					}
					else
					{
						this.Chargeur.rotationPointZ = -42 + 7 * (cooldown * 8);
					}
					
				}
				else
				{
					this.Chargeur.rotationPointZ = -42;
				}
				
				if(cooldown >= 0.6F)
				{
					if(cooldown >= 0.8F)
					{
						this.Shape2.rotationPointZ = -28 + (8 * (cooldown - 1F) * 5);
					}
					else
					{
						this.Shape2.rotationPointZ = -28 - (8 * (cooldown - 0.6F) * 5);
					}
				}
			}
			else
			{
				if(reload <= 0.2 && reload != 0)
				{
					if(reload >= 0.1F)
					{
						this.Chargeur.rotationPointZ = -42 + 7 - (reload * 40);
					}
					else
					{
						this.Chargeur.rotationPointZ = -42 + (reload * 70);
					}
				}
				else
				{
					this.Chargeur.rotationPointZ = -42;
				}
			}
		}
  		float f5 = 0.0625F;
  		Shape1.render(f5);
  	    Shape4.render(f5);
  	    Shape5.render(f5);
  	    Chargeur.render(f5);
  	    Shape8.render(f5);
  	    Shape9.render(f5);
  	    Shape10.render(f5);
  	    Shape11.render(f5);
  	    Shape12.render(f5);
  	    Shape13.render(f5);
  	    Shape22.render(f5);
  	    Shape24.render(f5);
  	    Shape25.render(f5);
  	    Shape26.render(f5);
  	    gachette.render(f5);
  	    levier1.render(f5);
  	    Shape27.render(f5);
  	    Shape28.render(f5);
  	    Shape29.render(f5);
  	    Shape30.render(f5);
  	    Shape31.render(f5);
  	    levier32.render(f5);
  	    Shape33.render(f5);
  	    Shape2.render(f5);
  	}

}
