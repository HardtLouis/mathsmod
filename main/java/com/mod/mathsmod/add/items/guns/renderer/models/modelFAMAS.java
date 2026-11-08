package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelFAMAS extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape4_5;
    ModelRenderer Shape5;
    ModelRenderer Chargeur;
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
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer gachette;
    ModelRenderer levier1;
    ModelRenderer levier2;
    ModelRenderer levier3;
  
  public modelFAMAS(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 28, 0);
      Shape1.addBox(0F, 0F, 0F, 4, 12, 4, scale);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 28, 0);
      Shape2.addBox(0F, 0F, 0F, 4, 8, 4, scale);
      Shape2.setRotationPoint(0F, 0F, -4F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 28, 0);
      Shape3.addBox(0F, 0.5F, -0.2F, 3, 8, 4, scale);
      Shape3.setRotationPoint(0.5F, 6F, -6F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0.8179294F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 28, 0);
      Shape4.addBox(0F, 0F, 0F, 4, 6, 12, scale);
      Shape4.setRotationPoint(0F, 2F, -16F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape4_5 = new ModelRenderer(this, 28, 0);
      Shape4_5.addBox(0F, 0F, 0F, 3, 7, 4, scale);
      Shape4_5.setRotationPoint(0.5F, 0F, -4F);
      Shape4_5.setTextureSize(64, 64);
      Shape4_5.mirror = true;
      setRotation(Shape4_5, -0.8551081F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 49, 11);
      Shape5.addBox(0F, 0F, 0F, 3, 12, 1, scale);
      Shape5.setRotationPoint(0.5F, 9F, -16F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 1.653312F, 0F, 0F);
      Chargeur = new ModelRenderer(this, 28, 24);
      Chargeur.addBox(0F, 0F, 0F, 2, 9, 5, scale);
      Chargeur.setRotationPoint(1F, 6F, -15F);
      Chargeur.setTextureSize(64, 64);
      Chargeur.mirror = true;
      setRotation(Chargeur, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 28, 0);
      Shape8.addBox(0F, 0F, 0F, 4, 5, 8, scale);
      Shape8.setRotationPoint(0F, 2F, -28F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 51, 15);
      Shape9.addBox(0F, 0F, 0F, 3, 4, 3, scale);
      Shape9.setRotationPoint(0.5F, 6F, -18.7F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0.7435722F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 2, 44);
      Shape10.addBox(0F, 0F, -2F, 4, 5, 15, scale);
      Shape10.setRotationPoint(0F, 2F, -41F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 28, 0);
      Shape11.addBox(0F, 0F, 0F, 4, 4, 4, scale);
      Shape11.setRotationPoint(0F, 2F, -20F);
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
      Shape14 = new ModelRenderer(this, 28, 0);
      Shape14.addBox(0F, 0F, 0F, 2, 4, 2, scale);
      Shape14.setRotationPoint(1F, -2F, -19F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 28, 0);
      Shape15.addBox(0F, 0.5F, -0.7F, 1, 2, 1, scale);
      Shape15.setRotationPoint(1.5F, -3F, -20F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0.7853982F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 28, 0);
      Shape16.addBox(0F, 0F, 0F, 2, 1, 12, scale);
      Shape16.setRotationPoint(1F, -3F, -31F);
      Shape16.setTextureSize(64, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 28, 0);
      Shape17.addBox(0F, 0F, 0F, 2, 1, 1, scale);
      Shape17.setRotationPoint(1F, -3F, -19F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 28, 0);
      Shape18.addBox(0F, 0F, 0F, 2, 1, 11, scale);
      Shape18.setRotationPoint(1F, -3F, -42F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, 0F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 28, 0);
      Shape19.addBox(0F, 0F, 0F, 2, 4, 2, scale);
      Shape19.setRotationPoint(1F, -2F, -43F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 28, 0);
      Shape20.addBox(0F, 0F, 0.4F, 1, 2, 1, scale);
      Shape20.setRotationPoint(1.5F, -3F, -41F);
      Shape20.setTextureSize(64, 64);
      Shape20.mirror = true;
      setRotation(Shape20, -0.7853982F, 0F, 0F);
      Shape22 = new ModelRenderer(this, 28, 24);
      Shape22.addBox(0F, 0F, 0F, 1, 1, 6, scale);
      Shape22.setRotationPoint(1.5F, 4F, -49F);
      Shape22.setTextureSize(64, 64);
      Shape22.mirror = true;
      setRotation(Shape22, 0F, 0F, 0F);
      Shape23 = new ModelRenderer(this, 28, 24);
      Shape23.addBox(0F, 0F, 0F, 2, 2, 3, scale);
      Shape23.setRotationPoint(1F, 3.5F, -52F);
      Shape23.setTextureSize(64, 64);
      Shape23.mirror = true;
      setRotation(Shape23, 0F, 0F, 0F);
      Shape24 = new ModelRenderer(this, 28, 48);
      Shape24.addBox(0F, 1F, 0F, 3, 9, 3, scale);
      Shape24.setRotationPoint(0.5F, 5F, -26F);
      Shape24.setTextureSize(64, 64);
      Shape24.mirror = true;
      setRotation(Shape24, 0.3717861F, 0F, 0F);
      Shape25 = new ModelRenderer(this, 25, 16);
      Shape25.addBox(0F, 0F, -1F, 2, 1, 5, scale);
      Shape25.setRotationPoint(1F, 9F, -28F);
      Shape25.setTextureSize(64, 64);
      Shape25.mirror = true;
      setRotation(Shape25, 0F, 0F, 0F);
      Shape26 = new ModelRenderer(this, 28, 0);
      Shape26.addBox(0F, 1F, 0F, 2, 4, 1, scale);
      Shape26.setRotationPoint(1F, 5F, -30.26667F);
      Shape26.setTextureSize(64, 64);
      Shape26.mirror = true;
      setRotation(Shape26, 0.2602503F, 0F, 0F);
      gachette = new ModelRenderer(this, 28, 0);
      gachette.addBox(0F, 0.5F, 0F, 1, 2, 1, scale);
      gachette.setRotationPoint(1.5F, 6F, -25.8F);
      gachette.setTextureSize(64, 64);
      gachette.mirror = true;
      setRotation(gachette, -0.3717861F, 0F, 0F);
      levier1 = new ModelRenderer(this, 28, 0);
      levier1.addBox(0F, 0F, -2F, 2, 1, 4, scale);
      levier1.setRotationPoint(1F, 1F, -22F);
      levier1.setTextureSize(64, 64);
      levier1.mirror = true;
      setRotation(levier1, 0F, 0F, 0F);
      levier2 = new ModelRenderer(this, 28, 0);
      levier2.addBox(0F, 0F, 0F, 1, 1, 2, scale);
      levier2.setRotationPoint(1.5F, 1.4F, -22F);
      levier2.setTextureSize(64, 64);
      levier2.mirror = true;
      setRotation(levier2, 1.152537F, 0F, 0F);
      levier3 = new ModelRenderer(this, 28, 0);
      levier3.addBox(0F, -1.5F, 2F, 1, 2, 1, scale);
      levier3.setRotationPoint(1.5F, 1.4F, -22F);
      levier3.setTextureSize(64, 64);
      levier3.mirror = true;
      setRotation(levier3, 1.152537F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape3.render(f5);
    Shape5.render(f5);
    Chargeur.render(f5);
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
    Shape19.render(f5);
    Shape20.render(f5);
    Shape22.render(f5);
    Shape23.render(f5);
    Shape24.render(f5);
    Shape25.render(f5);
    Shape26.render(f5);
    gachette.render(f5);
    levier1.render(f5);
    levier2.render(f5);
    levier3.render(f5);
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
				if(cooldown >= 0.6F)
				{
					this.Shape17.rotateAngleX = 0;
					if(cooldown >= 0.8F)
					{
						this.levier1.rotationPointZ = -22 + (13 * (cooldown - 1F) * 5);
						this.levier2.rotationPointZ = -22 + (13 * (cooldown - 1F) * 5);
						this.levier3.rotationPointZ = -22 + (13 * (cooldown - 1F) * 5);
					}
					else
					{
						this.levier1.rotationPointZ = -22 - (13 * (cooldown - 0.6F) * 5);
						this.levier2.rotationPointZ = -22 - (13 * (cooldown - 0.6F) * 5);
						this.levier3.rotationPointZ = -22 - (13 * (cooldown - 0.6F) * 5);
					}
				}
			}
			else
			{
				if(reload > 0.8 && reload != 0)
				{
					this.Chargeur.rotationPointY = 76 - (70 * ((reload - 0.8F) * 5));
				}
				else if(reload == 0.8)
				{
					this.Chargeur.isHidden = true;
				}
				else
				{
					if(reload < 0.2)
					{
						this.Chargeur.isHidden = false;
						this.Chargeur.rotationPointY = 6 + (5 * (reload * 10));
					}
					else
					{
						this.Chargeur.isHidden = true;
					}
				}
			}
		}
		
		float f5 = 0.0625F;
		
		Shape1.render(f5);
	    Shape2.render(f5);
	    Shape3.render(f5);
	    Shape4.render(f5);
	    Shape4_5.render(f5);
	    Shape5.render(f5);
	    Chargeur.render(f5);
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
	    Shape19.render(f5);
	    Shape20.render(f5);
	    Shape22.render(f5);
	    Shape23.render(f5);
	    Shape24.render(f5);
	    Shape25.render(f5);
	    Shape26.render(f5);
	    gachette.render(f5);
	    levier1.render(f5);
	    levier2.render(f5);
	    levier3.render(f5);
	}

}
