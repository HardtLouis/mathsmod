package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelAK47 extends modelBaseGun
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
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer chargeur;
    ModelRenderer chargeurP2;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Gachette;
    ModelRenderer Shape19;
  
  public modelAK47(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 44);
      Shape1.addBox(0F, 0F, 0F, 4, 8, 8, scale);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, -0.3490659F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 44);
      Shape2.addBox(0F, 0F, -8F, 4, 8, 8, scale);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, -0.3490659F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 20, 44);
      Shape3.addBox(0F, 3F, -16F, 4, 5, 8, scale);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, -0.3490659F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 24, 4);
      Shape4.addBox(0F, 0F, 0F, 5, 8, 12, scale);
      Shape4.setRotationPoint(-0.5F, -5F, -28F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 24, 4);
      Shape5.addBox(0F, 0F, 0F, 5, 8, 12, scale);
      Shape5.setRotationPoint(-0.5F, -5F, -40F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 24, 4);
      Shape6.addBox(0F, 0F, 0F, 5, 4, 2, scale);
      Shape6.setRotationPoint(-0.5F, -5F, -42F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 53);
      Shape7.addBox(0F, 0F, 0F, 3, 3, 8, scale);
      Shape7.setRotationPoint(0.5F, -4.5F, -48F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 52);
      Shape8.addBox(0F, 0F, 0F, 3, 3, 8, scale);
      Shape8.setRotationPoint(0.5F, -0.5F, -48F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 52);
      Shape9.addBox(0F, 0F, 0F, 2, 2, 2, scale);
      Shape9.setRotationPoint(1F, -2F, -44F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 0, 52);
      Shape10.addBox(0F, 0F, -1.9F, 2, 2, 3, scale);
      Shape10.setRotationPoint(1F, -2F, -46F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 24, 26);
      Shape11.addBox(0F, 0F, 0F, 2, 2, 10, scale);
      Shape11.setRotationPoint(1F, -4F, -58F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 24, 24);
      Shape12.addBox(0F, 0F, 0F, 2, 2, 12, scale);
      Shape12.setRotationPoint(1F, 0F, -59F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 24, 24);
      Shape13.addBox(0F, 0F, 0F, 2, 2, 10, scale);
      Shape13.setRotationPoint(1F, 0F, -69F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 24, 26);
      Shape14.addBox(0F, 0F, 0F, 1, 2, 5, scale);
      Shape14.setRotationPoint(1.5F, 0.5F, -60F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 1.152537F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 24, 26);
      Shape15.addBox(0F, 0F, 0F, 1, 7, 1, scale);
      Shape15.setRotationPoint(1.5F, -7F, -66F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 24, 26);
      Shape16.addBox(0F, 0F, 0F, 1, 5, 1, scale);
      Shape16.setRotationPoint(1.5F, -5F, -65F);
      Shape16.setTextureSize(64, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 24, 26);
      Shape17.addBox(0F, 0F, 0F, 1, 3, 1, scale);
      Shape17.setRotationPoint(1.5F, -3F, -64F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 0, 44);
      Shape18.addBox(0F, 0F, 0F, 3, 14, 4, scale);
      Shape18.setRotationPoint(0F, 0F, -26F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, 0.4089647F, 0F, 0F);
      chargeur = new ModelRenderer(this, 24, 3);
      chargeur.addBox(0F, 0F, 0F, 4, 10, 5, scale);
      chargeur.setRotationPoint(0F, 0F, -35F);
      chargeur.setTextureSize(64, 64);
      chargeur.mirror = true;
      setRotation(chargeur, -0.2602503F, 0F, 0F);
      chargeurP2 = new ModelRenderer(this, 24, 3);
      chargeurP2.addBox(0.5F, 6.2F, 4.3F, 3, 10, 5, scale);
      chargeurP2.setRotationPoint(0F, 0F, -35F);
      chargeurP2.setTextureSize(64, 64);
      chargeurP2.mirror = true;
      setRotation(chargeurP2, -0.7807508F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 24, 4);
      Shape21.addBox(0.5F, 0F, 0F, 2, 1, 5, scale);
      Shape21.setRotationPoint(1F, 5F, -28F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
      Shape22 = new ModelRenderer(this, 24, 4);
      Shape22.addBox(0.5F, 0F, 0F, 2, 4, 1, scale);
      Shape22.setRotationPoint(1F, 1F, -28F);
      Shape22.setTextureSize(64, 64);
      Shape22.mirror = true;
      setRotation(Shape22, 0F, 0F, 0F);
      Gachette = new ModelRenderer(this, 48, 29);
      Gachette.addBox(0.5F, 0F, 0F, 2, 3, 1, scale);
      Gachette.setRotationPoint(1F, 2F, -25F);
      Gachette.setTextureSize(64, 64);
      Gachette.mirror = true;
      setRotation(Gachette, -0.5948578F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 30, 39);
      Shape19.addBox(0F, 0F, 0F, 7, 1, 1, scale);
      Shape19.setRotationPoint(0F, -4F, -32F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, 0F, 0F);
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
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    chargeur.render(f5);
    chargeurP2.render(f5);
    Shape21.render(f5);
    Shape22.render(f5);
    Gachette.render(f5);
    Shape19.render(f5);
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
						this.Shape19.rotationPointZ = -32 + (9 * (cooldown - 1F) * 5);
					}
					else
					{
						this.Shape19.rotationPointZ = -32 - (9 * (cooldown - 0.6F) * 5);
					}
				}
			}
			else
			{
				if(reload > 0.8 && reload != 0)
				{
					this.chargeur.rotationPointY = 70 - (70 * ((reload - 0.8F) * 5));
					this.chargeurP2.rotationPointY = 70 - (70 * ((reload - 0.8F) * 5));
				}
				else if(reload == 0.8)
				{
					this.chargeur.isHidden = true;
					this.chargeurP2.isHidden = true;
				}
				else
				{
					if(reload < 0.2)
					{
						this.chargeur.isHidden = false;
						this.chargeur.rotationPointY = (5 * (reload * 10));
						this.chargeurP2.isHidden = false;
						this.chargeurP2.rotationPointY = (5 * (reload * 10));
					}
					else
					{
						this.chargeur.isHidden = true;
						this.chargeurP2.isHidden = true;
					}
				}
			}
		}
	  
	  float f5 = 0.065F;
	  Shape1.render(f5);
	  Shape2.render(f5);
	  Shape3.render(f5);
	  Shape4.render(f5);
	  Shape5.render(f5);
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
	  chargeur.render(f5);
	  chargeurP2.render(f5);
	  Shape21.render(f5);
	  Shape22.render(f5);
	  Gachette.render(f5);
	  Shape19.render(f5);
  }

}
