package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelBasicShotgun extends modelBaseGun
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
    ModelRenderer TCHTCH;
    ModelRenderer TCHTCH2;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer gachette;
  
  public modelBasicShotgun(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 8, 44);
      Shape1.addBox(0F, 0F, 0F, 5, 14, 4, scale);
      Shape1.setRotationPoint(0F, -1F, 0F);
      Shape1.setTextureSize(64, 64);
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 8, 44);
      Shape2.addBox(0F, 0F, 0F, 5, 12, 8, scale);
      Shape2.setRotationPoint(0F, -1F, -8F);
      Shape2.setTextureSize(64, 64);
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 8, 44);
      Shape3.addBox(0F, 0F, 0F, 5, 10, 8, scale);
      Shape3.setRotationPoint(0F, -1F, -16F);
      Shape3.setTextureSize(64, 64);
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 24, 4);
      Shape4.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape4.setRotationPoint(0.5F, 0F, -32F);
      Shape4.setTextureSize(64, 64);
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 24, 0);
      Shape5.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape5.setRotationPoint(0.5F, 0F, -48F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 24, 0);
      Shape6.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape6.setRotationPoint(0.5F, 0F, -64F);
      Shape6.setTextureSize(64, 64);
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 4);
      Shape7.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape7.setRotationPoint(0.5F, 0F, -80F);
      Shape7.setTextureSize(64, 64);
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 24, 0);
      Shape8.addBox(0F, 4F, 0F, 4, 4, 16, scale);
      Shape8.setRotationPoint(0.5F, 0F, -32F);
      Shape8.setTextureSize(64, 64);
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 24, 4);
      Shape9.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape9.setRotationPoint(0.5F, 4F, -48F);
      Shape9.setTextureSize(64, 64);
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 24, 4);
      Shape10.addBox(0F, 0F, 0F, 4, 4, 16, scale);
      Shape10.setRotationPoint(0.5F, 4F, -64F);
      Shape10.setTextureSize(64, 64);
      setRotation(Shape10, 0F, 0F, 0F);
      TCHTCH = new ModelRenderer(this, 27, 24);
      TCHTCH.addBox(0F, 0F, 0F, 8, 8, 10, scale);
      TCHTCH.setRotationPoint(-1.5F, 3F, -50F);
      TCHTCH.setTextureSize(64, 64);
      setRotation(TCHTCH, 0F, 0F, 0F);
      TCHTCH2 = new ModelRenderer(this, 24, 24);
      TCHTCH2.addBox(0F, 0F, -10F, 8, 8, 10, scale);
      TCHTCH2.setRotationPoint(-1.5F, 3F, -50F);
      TCHTCH2.setTextureSize(64, 64);
      setRotation(TCHTCH2, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 35, 24);
      Shape11.addBox(0F, 0F, 0F, 3, 16, 4, scale);
      Shape11.setRotationPoint(0.75F, 8F, -28F);
      Shape11.setTextureSize(64, 64);
      setRotation(Shape11, 0.5205006F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 27, 24);
      Shape12.addBox(0F, 0F, 0F, 2, 1, 7, scale);
      Shape12.setRotationPoint(1F, 11F, -32F);
      Shape12.setTextureSize(64, 64);
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 27, 24);
      Shape13.addBox(0F, 0F, 0F, 2, 6, 1, scale);
      Shape13.setRotationPoint(1F, 6F, -33F);
      Shape13.setTextureSize(64, 64);
      setRotation(Shape13, 0F, 0F, 0F);
      gachette = new ModelRenderer(this, 27, 24);
      gachette.addBox(0F, 0F, 0F, 2, 3, 1, scale);
      gachette.setRotationPoint(1F, 7F, -28F);
      gachette.setTextureSize(64, 64);
      setRotation(gachette, -0.3717861F, 0F, 0F);
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
    TCHTCH.render(f5);
    TCHTCH2.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    gachette.render(f5);
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
				if(cooldown <= 0.5 && cooldown != 0)
				{
					if(cooldown >= 0.25F)
					{
						this.TCHTCH.rotationPointZ = -50 + ((cooldown - 0.5F) * -4 * 7);
						this.TCHTCH2.rotationPointZ = -50 + ((cooldown - 0.5F) * -4 * 7);
					}
					else
					{
						this.TCHTCH.rotationPointZ = -50 + (7 * (cooldown * 4));
						this.TCHTCH2.rotationPointZ = -50 + (7 * (cooldown * 4));
					}
					
				}
				else
				{
					this.TCHTCH.rotationPointZ = -50;
					this.TCHTCH2.rotationPointZ = -50;
				}
			}
			else
			{
				if(reload <= 0.4 && reload != 0)
				{
					if(reload >= 0.2F)
					{
						this.TCHTCH.rotationPointZ = -50 - (7 * (reload - 0.4F * 5));
						this.TCHTCH2.rotationPointZ = -50 - (7 * (reload - 0.4F * 5));
					}
					else
					{
						this.TCHTCH.rotationPointZ = -50 + (reload * 35);
						this.TCHTCH2.rotationPointZ = -50 + (reload * 35);
					}
				}
				else
				{
					this.TCHTCH.rotationPointZ = -50;
					this.TCHTCH2.rotationPointZ = -50;
				}
			}
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
  	    Shape10.render(f5);
  	    TCHTCH.render(f5);
  	    TCHTCH2.render(f5);
  	    Shape11.render(f5);
  	    Shape12.render(f5);
  	    Shape13.render(f5);
  	    gachette.render(f5);
  	}

}
