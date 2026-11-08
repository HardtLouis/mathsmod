package com.mod.mathsmod.add.Entity.Rendermobs.layer.playerCosmetics;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelThoriumArmor;
import com.mod.mathsmod.utils.DisguiseHelper;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class LayerGod implements LayerRenderer<AbstractClientPlayer>
{
	private static final ResourceLocation JESUS = new ResourceLocation(Mathsmod.MODID, "textures/entity/player_cosmetics/jesus.png");
	private static final ResourceLocation COMMUNIST_CAPE = new ResourceLocation(Mathsmod.MODID, "textures/entity/player_cosmetics/communist_cape.png");
	private static final ResourceLocation FRENCH_CAPE = new ResourceLocation(Mathsmod.MODID, "textures/entity/player_cosmetics/french_cape.png");
	private static final ResourceLocation TRUMP_CAPE = new ResourceLocation(Mathsmod.MODID, "textures/entity/player_cosmetics/j_cape.png");
	private static final ResourceLocation P_CAPE = new ResourceLocation(Mathsmod.MODID, "textures/entity/player_cosmetics/a_cape.png");
	
	private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/wither/wither_armor.png");
	private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");
	
    private final RenderPlayer hunterRenderer;

    public LayerGod(RenderPlayer hunterRendererIn)
    {
        this.hunterRenderer = hunterRendererIn;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.isInvisible())
        {
        	String name = entitylivingbaseIn.getName();
        	
        	if(DisguiseHelper.getDisguisment(entitylivingbaseIn) != null && DisguiseHelper.getDisguisment(entitylivingbaseIn).toString().startsWith("a:"))
	    	{
	    		String s = DisguiseHelper.getDisguisment(entitylivingbaseIn).toString().substring(2);
	    		name = s;
	    	}
        	
        	name = name.toLowerCase();
        	
        	if(this.playersWhoHadJesus().contains(name))
        	{
        		this.hunterRenderer.bindTexture(JESUS);
                new modelJesus().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        	}
        	if(name.equals("jeanrobertperez"))
            {
            	this.renderCape(entitylivingbaseIn, partialTicks, FRENCH_CAPE);
            }
        	if(name.equals("darkwraith004"))
            {
            	this.renderCape(entitylivingbaseIn, partialTicks, P_CAPE);
            }
        	if(name.equals("djocaillou"))
            {
            	this.renderCape(entitylivingbaseIn, partialTicks, TRUMP_CAPE);
            }
        }
        
        if(entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
           entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
        {
        	ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        	ItemUberArmor item = (ItemUberArmor) entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
        	
            if(entitylivingbaseIn.capabilities.isFlying)
            {
            	for(int i = 0; i <= 16; i += 16)
            	{
            		ModelThoriumArmor modelPlayer = new ModelThoriumArmor(2F, this.hunterRenderer.getMainModel());
                	modelPlayer.allRendered = true;
                    this.hunterRenderer.bindTexture(LIGHTNING_TEXTURE);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
                    GlStateManager.translate(f * 0.01F + i * 0.01F, f * 0.01F + i * 0.01F, 0.0F);
                    GlStateManager.matrixMode(5888);
                    GlStateManager.enableBlend();
                    float f1 = 0.5F;
                    GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
                    GlStateManager.disableLighting();
                    GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
                    GlStateManager.color(1, 0, 0);
                    modelPlayer.setModelAttributes(this.hunterRenderer.getMainModel());
                    Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
                    modelPlayer.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                    Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    GlStateManager.matrixMode(5888);
                    GlStateManager.enableLighting();
                    GlStateManager.disableBlend();
            	}
            }
        }
    }

	@Override
	public boolean shouldCombineTextures() 
	{
		return true;
	}
	
	public List<String> playersWhoHadJesus()
	{
		List<String> list = Lists.newArrayList();
		list.add("jeanrobertperez");
		list.add("ankulator");
		list.add("math654");
		return list;
	}
	
	private void renderCape(AbstractClientPlayer entitylivingbaseIn, float partialTicks, ResourceLocation loc)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.hunterRenderer.bindTexture(loc);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 0.125F);
        double d0 = entitylivingbaseIn.prevChasingPosX + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * (double)partialTicks - (entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * (double)partialTicks);
        double d1 = entitylivingbaseIn.prevChasingPosY + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * (double)partialTicks - (entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * (double)partialTicks);
        double d2 = entitylivingbaseIn.prevChasingPosZ + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * (double)partialTicks - (entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * (double)partialTicks);
        float f = entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
        double d3 = (double)MathHelper.sin(f * 0.017453292F);
        double d4 = (double)(-MathHelper.cos(f * 0.017453292F));
        float f1 = (float)d1 * 10.0F;
        f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
        float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        float f4 = entitylivingbaseIn.prevCameraYaw + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
        f1 = f1 + 0.01F + MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

        if (entitylivingbaseIn.isSneaking())
        {
            f1 += 25.0F;
            GlStateManager.translate(0.0F, 0.1F, 0.0F);
        }

        GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.hunterRenderer.getMainModel().renderCape(0.0625F);
        GlStateManager.popMatrix();
	}

}
