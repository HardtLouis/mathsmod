package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelP90 extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer chargeur1;
    ModelRenderer chargeur2;
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
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
  
  public modelP90(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 24, 0);
      Shape1.addBox(0F, 0F, 0F, 4, 12, 12, scale);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 24, 0);
      Shape2.addBox(0F, 0F, 0F, 4, 12, 6, scale);
      Shape2.setRotationPoint(0F, 0F, -6F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 28, 0);
      Shape3.addBox(0F, 0F, 0F, 4, 4, 14, scale);
      Shape3.setRotationPoint(0F, 4F, -20F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 28, 0);
      Shape4.addBox(0F, 0F, 0F, 4, 4, 14, scale);
      Shape4.setRotationPoint(0F, 4F, -34F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      chargeur1 = new ModelRenderer(this, 24, 27);
      chargeur1.addBox(0F, 0F, 14F, 3, 3, 14, scale);
      chargeur1.setRotationPoint(0.5F, 1F, -34F);
      chargeur1.setTextureSize(64, 64);
      chargeur1.mirror = true;
      setRotation(chargeur1, 0F, 0F, 0F);
      chargeur2 = new ModelRenderer(this, 24, 27);
      chargeur2.addBox(0F, 0F, 0F, 3, 3, 14, scale);
      chargeur2.setRotationPoint(0.5F, 1F, -34F);
      chargeur2.setTextureSize(64, 64);
      chargeur2.mirror = true;
      setRotation(chargeur2, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 48, 0);
      Shape5.addBox(0F, 0F, 0F, 4, 10, 4, scale);
      Shape5.setRotationPoint(0F, 0F, -38F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 48, 0);
      Shape7.addBox(0F, 0F, 0F, 1, 11, 2, scale);
      Shape7.setRotationPoint(-0.5F, -4F, -19.6F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 48, 0);
      Shape8.addBox(0F, 0F, 0F, 1, 11, 2, scale);
      Shape8.setRotationPoint(3.533333F, -4F, -19.6F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 24, 0);
      Shape9.addBox(0F, 0F, 0F, 4, 2, 16, scale);
      Shape9.setRotationPoint(0F, -4F, -33.5F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0.0371786F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 48, 0);
      Shape10.addBox(0F, -5F, 0F, 3, 5, 3, scale);
      Shape10.setRotationPoint(0.5F, 0F, -38F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, -0.5205006F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 24, 0);
      Shape11.addBox(0F, 0F, 0F, 4, 1, 16, scale);
      Shape11.setRotationPoint(0F, -5F, -33.5F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 48, 0);
      Shape12.addBox(0F, 0F, 0F, 4, 3, 2, scale);
      Shape12.setRotationPoint(0F, -5F, -35.5F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 48, 0);
      Shape13.addBox(0F, 0F, 0F, 2, 2, 1, scale);
      Shape13.setRotationPoint(1F, 10F, -38F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 48, 0);
      Shape14.addBox(0F, 0F, 0F, 3, 8, 4, scale);
      Shape14.setRotationPoint(0.5F, 9F, -32F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0.6320364F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 48, 0);
      Shape15.addBox(0F, 0F, 0F, 4, 2, 4, scale);
      Shape15.setRotationPoint(0F, 8F, -34F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 28, 0);
      Shape16.addBox(0F, 0F, 0F, 4, 4, 7, scale);
      Shape16.setRotationPoint(0F, 11.5F, -27.5F);
      Shape16.setTextureSize(64, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 28, 0);
      Shape17.addBox(0F, 0F, 0F, 3, 4, 6, scale);
      Shape17.setRotationPoint(0.5F, 8F, -22F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 48, 0);
      Shape18.addBox(0F, 0F, 0F, 4, 8, 3, scale);
      Shape18.setRotationPoint(0F, 11.4F, -18F);
      Shape18.setTextureSize(64, 64);
      Shape18.mirror = true;
      setRotation(Shape18, 1.07818F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 48, 0);
      Shape19.addBox(0F, 0F, 1F, 3, 8, 3, scale);
      Shape19.setRotationPoint(0.5F, 8F, -6F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, -1.07818F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 24, 0);
      Shape20.addBox(0F, 0F, 0F, 4, 3, 3, scale);
      Shape20.setRotationPoint(0F, 8F, -9F);
      Shape20.setTextureSize(64, 64);
      Shape20.mirror = true;
      setRotation(Shape20, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 24, 0);
      Shape21.addBox(0F, 0F, 0F, 3, 3, 2, scale);
      Shape21.setRotationPoint(0.5F, 8F, -16F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
      Shape22 = new ModelRenderer(this, 6, 50);
      Shape22.addBox(0F, 0F, 0F, 2, 2, 4, scale);
      Shape22.setRotationPoint(1F, 5F, -42F);
      Shape22.setTextureSize(64, 64);
      Shape22.mirror = true;
      setRotation(Shape22, 0F, 0F, 0F);
      Shape23 = new ModelRenderer(this, 28, 0);
      Shape23.addBox(0F, 0F, 0F, 2, 4, 1, scale);
      Shape23.setRotationPoint(1F, 7F, -24F);
      Shape23.setTextureSize(64, 64);
      Shape23.mirror = true;
      setRotation(Shape23, -0.2974289F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    chargeur1.render(f5);
    chargeur2.render(f5);
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
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
    Shape20.render(f5);
    Shape21.render(f5);
    Shape22.render(f5);
    Shape23.render(f5);
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
			
			if(item.getReloading(stack))
			{
				if(reload < 0.6F)
				{
					if(reload <= 0.1F)
					{
						this.chargeur1.rotateAngleX = reload * 80 * 0.017453292F;
						this.chargeur2.rotateAngleX = reload * 80 * 0.017453292F;
					}
					else if (reload < 0.4)
					{
						chargeur1.offsetZ = reload - 0.1F * 589;
						chargeur2.offsetZ = reload - 0.1F * 589 + 14;
					}
					else
					{
						chargeur1.showModel = false;
						chargeur2.showModel = false;
					}
				}
				else
				{
					if(reload >= 0.8F)
					{
						float u = reload - 0.8F;
						this.chargeur1.rotateAngleX = 8 * 0.017453292F - u * 40 * 0.017453292F;
						this.chargeur2.rotateAngleX = 8 * 0.017453292F - u * 40 * 0.017453292F;
					}
					else
					{
						chargeur1.offsetZ = 6 - (reload - 0.6F * 20);
						chargeur2.offsetZ = 6 - (reload - 0.6F * 20) + 14;
					}
				}
			}
		}
  		float f5 = 0.0625F;
  		Shape1.render(f5);
  		Shape2.render(f5);
  		Shape3.render(f5);
  		Shape4.render(f5);
  		chargeur1.render(f5);
  		chargeur2.render(f5);
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
  		Shape16.render(f5);
  		Shape17.render(f5);
  		Shape18.render(f5);
	    Shape19.render(f5);
		Shape20.render(f5);
	    Shape21.render(f5);
	    Shape22.render(f5);
	    Shape23.render(f5);
  	}

}
