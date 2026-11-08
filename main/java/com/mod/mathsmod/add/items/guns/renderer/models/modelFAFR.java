package com.mod.mathsmod.add.items.guns.renderer.models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class modelFAFR extends modelBaseGun
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape3;
    ModelRenderer Shape3p2;
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
    ModelRenderer gachette;
    ModelRenderer chargeur;
    ModelRenderer chargeurp2;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape19;
    ModelRenderer Shape21;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer levier;
    ModelRenderer Shape23p2;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
  
  public modelFAFR(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 10, 49);
      Shape1.addBox(0F, 0F, 0F, 4, 11, 2, scale);
      Shape1.setRotationPoint(0F, -2.1F, 4F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, -0.2602503F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 8, 45);
      Shape3.addBox(0F, 0F, -6F, 3, 2, 10, scale);
      Shape3.setRotationPoint(0.5F, -2F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape3p2 = new ModelRenderer(this, 13, 51);
      Shape3p2.addBox(0F, 0F, 0F, 3, 2, 10, scale);
      Shape3p2.setRotationPoint(0.5F, 3.9F, -6F);
      Shape3p2.setTextureSize(64, 64);
      Shape3p2.mirror = true;
      setRotation(Shape3p2, -0.3346075F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 22, 45);
      Shape4.addBox(0F, 0F, 0F, 4, 8, 4, scale);
      Shape4.setRotationPoint(0F, -2F, -10F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 22, 45);
      Shape5.addBox(0F, 0F, 0F, 4, 8, 4, scale);
      Shape5.setRotationPoint(0F, -2F, -14F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 27, 24);
      Shape6.addBox(0F, 0F, 0F, 6, 8, 12, scale);
      Shape6.setRotationPoint(-1F, -2F, -26F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 27, 24);
      Shape7.addBox(0F, 0F, 0F, 6, 8, 12, scale);
      Shape7.setRotationPoint(-1F, -2F, -38F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 27, 24);
      Shape8.addBox(0F, 0F, 0F, 6, 4, 10, scale);
      Shape8.setRotationPoint(-1F, 6F, -28F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 27, 24);
      Shape9.addBox(0F, 0F, 0F, 6, 4, 10, scale);
      Shape9.setRotationPoint(-1F, 6F, -38F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 27, 24);
      Shape10.addBox(0F, 0F, 0F, 6, 8, 4, scale);
      Shape10.setRotationPoint(-1F, -2F, -42F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 13, 47);
      Shape11.addBox(0F, 2F, 0F, 4, 12, 4, scale);
      Shape11.setRotationPoint(0F, 6F, -25F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0.4089647F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 2, 45);
      Shape12.addBox(0F, 0F, 0F, 3, 1, 6, scale);
      Shape12.setRotationPoint(0.5F, 12F, -27F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 2, 45);
      Shape13.addBox(0F, 0F, 0F, 3, 6, 1, scale);
      Shape13.setRotationPoint(0.5F, 7F, -28F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      gachette = new ModelRenderer(this, 2, 45);
      gachette.addBox(0F, 0F, 0F, 3, 3, 1, scale);
      gachette.setRotationPoint(0.5F, 8.5F, -24F);
      gachette.setTextureSize(64, 64);
      gachette.mirror = true;
      setRotation(gachette, -0.4461433F, 0F, 0F);
      chargeur = new ModelRenderer(this, 7, 44);
      chargeur.addBox(0F, 0F, 0F, 4, 8, 6, scale);
      chargeur.setRotationPoint(0F, 10F, -35F);
      chargeur.setTextureSize(64, 64);
      chargeur.mirror = true;
      setRotation(chargeur, 0F, 0F, 0F);
      chargeurp2 = new ModelRenderer(this, 7, 44);
      chargeurp2.addBox(0F, 0F, 0F, 3, 8, 6, scale);
      chargeurp2.setRotationPoint(0.5F, 15F, -34.4F);
      chargeurp2.setTextureSize(64, 64);
      chargeurp2.mirror = true;
      setRotation(chargeurp2, -0.4461433F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 24, 24);
      Shape16.addBox(0F, 0F, 0F, 4, 4, 14, scale);
      Shape16.setRotationPoint(0F, -1F, -56F);
      Shape16.setTextureSize(64, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 24, 24);
      Shape17.addBox(0F, 0F, 0F, 2, 2, 11, scale);
      Shape17.setRotationPoint(1F, 0F, -66F);
      Shape17.setTextureSize(64, 64);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 24, 2);
      Shape19.addBox(0F, 0F, 0F, 4, 1, 16, scale);
      Shape19.setRotationPoint(0F, -3F, -35F);
      Shape19.setTextureSize(64, 64);
      Shape19.mirror = true;
      setRotation(Shape19, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 22, 45);
      Shape21.addBox(0F, 0F, 0F, 3, 3, 2, scale);
      Shape21.setRotationPoint(0.5F, -0.5F, -57.5F);
      Shape21.setTextureSize(64, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
      Shape23 = new ModelRenderer(this, 22, 45);
      Shape23.addBox(0F, 0F, 0F, 1, 8, 1, scale);
      Shape23.setRotationPoint(1.5F, -7F, -54F);
      Shape23.setTextureSize(64, 64);
      Shape23.mirror = true;
      setRotation(Shape23, 0F, 0F, 0F);
      Shape24 = new ModelRenderer(this, 22, 45);
      Shape24.addBox(0F, 0F, 0F, 1, 4, 1, scale);
      Shape24.setRotationPoint(1.5F, -7F, -26F);
      Shape24.setTextureSize(64, 64);
      Shape24.mirror = true;
      setRotation(Shape24, 0F, 0F, 0F);
      levier = new ModelRenderer(this, 13, 45);
      levier.addBox(0F, 0F, 0F, 10, 1, 1, scale);
      levier.setRotationPoint(-3F, 0.5F, -40F);
      levier.setTextureSize(64, 64);
      levier.mirror = true;
      setRotation(levier, 0F, 0F, 0F);
      Shape23p2 = new ModelRenderer(this, 22, 51);
      Shape23p2.addBox(0F, 0F, 0F, 1, 5, 1, scale);
      Shape23p2.setRotationPoint(1.533333F, -5F, -53F);
      Shape23p2.setTextureSize(64, 64);
      Shape23p2.mirror = true;
      setRotation(Shape23p2, 0F, 0F, 0F);
      Shape25 = new ModelRenderer(this, 24, 2);
      Shape25.addBox(0F, 0F, 0F, 2, 1, 11, scale);
      Shape25.setRotationPoint(1F, -2F, -53F);
      Shape25.setTextureSize(64, 64);
      Shape25.mirror = true;
      setRotation(Shape25, 0F, 0F, 0F);
      Shape26 = new ModelRenderer(this, 24, 2);
      Shape26.addBox(0F, 0F, 0F, 2, 1, 11, scale);
      Shape26.setRotationPoint(1F, 3F, -53F);
      Shape26.setTextureSize(64, 64);
      Shape26.mirror = true;
      setRotation(Shape26, 0F, 0F, 0F);
      Shape27 = new ModelRenderer(this, 24, 2);
      Shape27.addBox(0F, 0F, 0F, 1, 2, 11, scale);
      Shape27.setRotationPoint(-1F, 0F, -53F);
      Shape27.setTextureSize(64, 64);
      Shape27.mirror = true;
      setRotation(Shape27, 0F, 0F, 0F);
      Shape28 = new ModelRenderer(this, 24, 2);
      Shape28.addBox(0F, 0F, 0F, 1, 2, 11, scale);
      Shape28.setRotationPoint(4F, 0F, -53F);
      Shape28.setTextureSize(64, 64);
      Shape28.mirror = true;
      setRotation(Shape28, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape3.render(f5);
    Shape3p2.render(f5);
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
    gachette.render(f5);
    chargeur.render(f5);
    chargeurp2.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape19.render(f5);
    Shape21.render(f5);
    Shape23.render(f5);
    Shape24.render(f5);
    levier.render(f5);
    Shape23p2.render(f5);
    Shape25.render(f5);
    Shape26.render(f5);
    Shape27.render(f5);
    Shape28.render(f5);
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
				if(cooldown != 0)
				{
					this.gachette.rotateAngleX = 0;
					if(cooldown > 0.5F)
					{
						this.levier.rotationPointZ = -40 - (40 * (cooldown - 1));
					}
					else
					{
						this.levier.rotationPointZ = -40 + (40 * cooldown);
					}
				}
			}
			else
			{
				if(reload > 0.8 && reload != 0)
				{
					this.chargeur.rotationPointY = 80 - (70 * ((reload - 0.8F) * 5));
					this.chargeurp2.rotationPointY = 85 - (70 * ((reload - 0.8F) * 5));
				}
				else if(reload == 0.8)
				{
					this.chargeur.isHidden = true;
					this.chargeurp2.isHidden = true;
				}
				else
				{
					if(reload < 0.2)
					{
						this.chargeur.isHidden = false;
						this.chargeur.rotationPointY = 10 + (5 * (reload * 10));
						this.chargeurp2.isHidden = false;
						this.chargeurp2.rotationPointY = 15 + (5 * (reload * 10));
					}
					else
					{
						this.chargeur.isHidden = true;
						this.chargeurp2.isHidden = true;
					}
				}
			}
		}
		
		Shape1.render(0.0625F);
	    Shape3.render(0.0625F);
	    Shape3p2.render(0.0625F);
	    Shape4.render(0.0625F);
	    Shape5.render(0.0625F);
	    Shape6.render(0.0625F);
	    Shape7.render(0.0625F);
	    Shape8.render(0.0625F);
	    Shape9.render(0.0625F);
	    Shape10.render(0.0625F);
	    Shape11.render(0.0625F);
	    Shape12.render(0.0625F);
	    Shape13.render(0.0625F);
	    gachette.render(0.0625F);
	    chargeur.render(0.0625F);
	    chargeurp2.render(0.0625F);
	    Shape16.render(0.0625F);
	    Shape17.render(0.0625F);
	    Shape19.render(0.0625F);
	    Shape21.render(0.0625F);
	    Shape23.render(0.0625F);
	    Shape24.render(0.0625F);
	    levier.render(0.0625F);
	    Shape23p2.render(0.0625F);
	    Shape25.render(0.0625F);
	    Shape26.render(0.0625F);
	    Shape27.render(0.0625F);
	    Shape28.render(0.0625F);
	}

}
