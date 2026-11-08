package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.google.common.base.MoreObjects;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.ICustomAnimation;
import com.mod.mathsmod.add.items.IHeavyHitterItem;
import com.mod.mathsmod.add.items.guns.ItemFlameThrower;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemShotGun;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class HandAnimator 
{
	public static void renderItemInFirstPerson(float partialTicks)
    {
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().player;
        EnumHand enumhand = (EnumHand)MoreObjects.firstNonNull(abstractclientplayer.swingingHand, EnumHand.MAIN_HAND);
        float f1 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
        float f2 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
        boolean flag = true;
        boolean flag1 = true;

        if (abstractclientplayer.isHandActive())
        {
            ItemStack itemstack = abstractclientplayer.getActiveItemStack();

            if (itemstack.getItem() instanceof net.minecraft.item.ItemBow)
            {
                EnumHand enumhand1 = abstractclientplayer.getActiveHand();
                flag = enumhand1 == EnumHand.MAIN_HAND;
                flag1 = !flag;
            }
        }

        rotateArroundXAndY(f1, f2);
        setLightmap();
        rotateArm(partialTicks);
        GlStateManager.enableRescaleNormal();

        if (flag)
        {
            
            float f3 = 0.0F;
            renderItemInFirstPerson(abstractclientplayer, partialTicks, f1, EnumHand.MAIN_HAND, f3, abstractclientplayer.getHeldItemMainhand(), 0F);
            
        }

        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
    }
    
    private static void rotateArroundXAndY(float angle, float angleY)
    {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(angle, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(angleY, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }
    
    private static void setLightmap()
    {
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().player;
        int i = Minecraft.getMinecraft().world.getCombinedLight(new BlockPos(abstractclientplayer.posX, abstractclientplayer.posY + (double)abstractclientplayer.getEyeHeight(), abstractclientplayer.posZ), 0);
        float f = (float)(i & 65535);
        float f1 = (float)(i >> 16);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f, f1);
    }
    
    private static void rotateArm(float p_187458_1_)
    {
        EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
        if(!(entityplayersp.getHeldItemMainhand().getItem() instanceof ItemGun) || !((ItemGun) entityplayersp.getHeldItemMainhand().getItem()).Animated(entityplayersp.getHeldItemMainhand(), entityplayersp))
        {
        	float f = entityplayersp.prevRenderArmPitch + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * p_187458_1_;
            float f1 = entityplayersp.prevRenderArmYaw + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * p_187458_1_;
            GlStateManager.rotate((entityplayersp.rotationPitch - f) * 0.1F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate((entityplayersp.rotationYaw - f1) * 0.1F, 0.0F, 1.0F, 0.0F);
        }
        
        if(entityplayersp.getHeldItemMainhand().getItem() instanceof ItemGun)
        {
        	ItemGun item = (ItemGun) entityplayersp.getHeldItemMainhand().getItem();
        	
        	if(entityplayersp.getCooldownTracker().hasCooldown(entityplayersp.getHeldItemMainhand().getItem()))
        	{
        		float cooldown = entityplayersp.getCooldownTracker().getCooldown(entityplayersp.getHeldItemMainhand().getItem(), p_187458_1_);
        		if(cooldown > item.cooldown(entityplayersp.getHeldItemMainhand(), entityplayersp) / 2)
        		{
        			cooldown = item.cooldown(entityplayersp.getHeldItemMainhand(), entityplayersp) - cooldown;
        		}
        		
        		GlStateManager.rotate(cooldown * 6F * item.handMovementMultiplicator(), 1.0F, 0.0F, 0.0F);
        	}
        }
    }
    
    private static void renderArmFirstPerson(EnumHandSide p_187456_3_)
    {
        boolean flag = p_187456_3_ != EnumHandSide.LEFT;
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().player;
        Minecraft.getMinecraft().getTextureManager().bindTexture(abstractclientplayer.getLocationSkin());
        //GlStateManager.translate(0, 0, -1);
        RenderPlayer renderplayer = (RenderPlayer)Minecraft.getMinecraft().getRenderManager().<AbstractClientPlayer>getEntityRenderObject(abstractclientplayer);
        GlStateManager.disableCull();

        if (flag)
        {
            renderplayer.renderRightArm(abstractclientplayer);
        }
        else
        {
            renderplayer.renderLeftArm(abstractclientplayer);
        }

        GlStateManager.enableCull();
    }
    
    public static void renderItemInFirstPerson(AbstractClientPlayer player, float partialTicks, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_)
    {
        boolean flag = hand == EnumHand.MAIN_HAND;
        EnumHandSide enumhandside = flag ? player.getPrimaryHand() : player.getPrimaryHand().opposite();
        GlStateManager.pushMatrix();

        boolean flag1 = enumhandside == EnumHandSide.RIGHT;

        if (player.isHandActive() && player.getItemInUseCount() > 0 && player.getActiveHand() == hand)
        {
            int j = flag1 ? 1 : -1;
            transformSideFirstPerson(enumhandside, p_187457_7_, player.getHeldItemMainhand().getItem() instanceof ItemGun ? ((ItemGun) player.getHeldItemMainhand().getItem()).Animated(player.getHeldItemMainhand(), player) : false);
        }
        else
        {
            float f = -0.4F * MathHelper.sin(MathHelper.sqrt(p_187457_5_) * (float)Math.PI);
            float f1 = 0.2F * MathHelper.sin(MathHelper.sqrt(p_187457_5_) * ((float)Math.PI * 2F));
            float f2 = -0.2F * MathHelper.sin(p_187457_5_ * (float)Math.PI);
            int i = flag1 ? 1 : -1;
            GlStateManager.translate((float)i * f, f1, f2);

            transformSideFirstPerson(enumhandside, p_187457_7_, player.getHeldItemMainhand().getItem() instanceof ItemGun ? ((ItemGun) player.getHeldItemMainhand().getItem()).Animated(player.getHeldItemMainhand(), player) : false);
            transformFirstPerson(enumhandside, partialTicks, p_187457_5_, player.getHeldItemMainhand().getItem() instanceof ItemGun ? ((ItemGun) player.getHeldItemMainhand().getItem()).Animated(player.getHeldItemMainhand(), player) : false);
        }
        
        if(player.getHeldItemMainhand().getItem() instanceof ICustomAnimation)
        {
        	float adv = 0;
        	if(player.getHeldItemMainhand().getItem() instanceof IHeavyHitterItem && KeyEvent.hhAttackTick > 0)
        	{
        		float hitTime = ((IHeavyHitterItem) player.getHeldItemMainhand().getItem()).hitTime();
            	adv = (hitTime - KeyEvent.hhAttackTick + partialTicks) / hitTime;
        	}
        	((ICustomAnimation) player.getHeldItemMainhand().getItem()).renderFirstPersonPre(player.getHeldItemMainhand(), EnumHand.MAIN_HAND, player, partialTicks, adv);
        }

        Minecraft.getMinecraft().getItemRenderer().renderItemSide(player, stack, flag1 ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !flag1);
        EntityPlayer playerentity = Minecraft.getMinecraft().player;
        
        if(playerentity.getHeldItemMainhand().getItem() instanceof ItemGun)
        {
        	ItemGun gun = (ItemGun) playerentity.getHeldItemMainhand().getItem();
        	ItemStack s = playerentity.getHeldItemMainhand();
        	RenderPlayer renderplayer = (RenderPlayer)Minecraft.getMinecraft().getRenderManager().<AbstractClientPlayer>getEntityRenderObject(player);
        	if(gun.rightArmFirstPersonRotation(s, playerentity, partialTicks) != null)
        	{
        		armsLoRo loRo = gun.rightArmFirstPersonRotation(s, playerentity, partialTicks);
        		GlStateManager.translate(loRo.armX, loRo.armY, loRo.armZ);
        		GlStateManager.rotate(loRo.armRotateX, 1, 0, 0);
        		GlStateManager.rotate(loRo.armRotateY, 0, 1, 0);
        		GlStateManager.rotate(loRo.armRotateZ, 0, 0, 1);
        		HandAnimator.renderArmFirstPerson(EnumHandSide.RIGHT);
        		GlStateManager.rotate(loRo.armRotateZ, 0, 0, -1);
        		GlStateManager.rotate(loRo.armRotateY, 0, -1, 0);
        		GlStateManager.rotate(loRo.armRotateX, -1, 0, 0);
        		GlStateManager.translate(-loRo.armX, -loRo.armY, -loRo.armZ);
        	}
        	if(gun.leftArmFirstPersonRotation(s, playerentity, partialTicks) != null)
        	{
        		armsLoRo loRo = gun.leftArmFirstPersonRotation(s, playerentity, partialTicks);
        		GlStateManager.translate(loRo.armX, loRo.armY, loRo.armZ);
        		GlStateManager.rotate(loRo.armRotateX, 1, 0, 0);
        		GlStateManager.rotate(loRo.armRotateY, 0, 1, 0);
        		GlStateManager.rotate(loRo.armRotateZ, 0, 0, 1);
        		HandAnimator.renderArmFirstPerson(EnumHandSide.LEFT);
        	}
        }
        GlStateManager.popMatrix();
    }
    
    private static void transformFirstPerson(EnumHandSide hand, float partialTicks, float p_187453_2_, boolean flag)
    {
        float i = hand == EnumHandSide.RIGHT ? 0.5F : -1;
        float f = MathHelper.sin(p_187453_2_ * p_187453_2_ * (float)Math.PI);
        GlStateManager.rotate((float)i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
        float f1 = MathHelper.sin(MathHelper.sqrt(p_187453_2_) * (float)Math.PI);
        
        if(flag)
        {
        	GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        	if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemShotGun)
        	{
        		GlStateManager.rotate(-25F, 0.0F, 1.0F, 0.0F);
        	}
        	else
        	{
        		GlStateManager.rotate((float)i * -45.0F, 0.0F, 1.0F, 0.0F);
        	}
            GlStateManager.rotate((float)i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        }
        if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemGun)
        {
        	ItemGun item = ((ItemGun) Minecraft.getMinecraft().player.getHeldItemMainhand().getItem());
        	float timer = (item.getTakeTime(Minecraft.getMinecraft().player.getHeldItemMainhand()) * 1.0F) / (item.timeToUse() + item.switchCooldownBonus(Minecraft.getMinecraft().player.getHeldItemMainhand(), Minecraft.getMinecraft().player) * 1.0F);
        	GlStateManager.rotate(-45F + timer * 45F, 1.0F, 0.0F, 0.0F);
        	GlStateManager.translate(0, -0.35F + 0.35F * timer, 0);
        	
        	
        	if(item.getReloading(Minecraft.getMinecraft().player.getHeldItemMainhand()) && item.getMaxReloadTime() != 0)
        	{
        		float u = item.getReload(Minecraft.getMinecraft().player.getHeldItemMainhand()) / item.getMaxReloadTime();
        		if(u >= 0.5F)
        		{
        			u -= u - 0.5F;
        		}
        		
        		GlStateManager.translate(0.4F, -0.1, 0);
        		GlStateManager.rotate(75, 1.0F, 0.0F, 0.0F);
        	}
        }
        
    }
    
    private static void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, boolean flag)
    {
        int i = hand == EnumHandSide.RIGHT ? 1 : -1;
        float j = 0;
        float e = (float)i * 0.56F;
        float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
        
        if(flag)
        {
        	if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemGun && ((ItemGun) Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).leftArmRotation(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.getHeldItemMainhand(), partialTicks) != null &&
        	((ItemGun) Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getTakeTime(Minecraft.getMinecraft().player.getHeldItemMainhand()) == ((ItemGun) Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).timeToUse() + ((ItemGun) Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).switchCooldownBonus(Minecraft.getMinecraft().player.getHeldItemMainhand(), Minecraft.getMinecraft().player))
        	{
        		e = 0;
        	}
        	if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemFlameThrower)
        	{
        		e = 0;
        	}
        	if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemShotGun)
        	{
        		e = i * 0.3F;
        		j = 0.2F;
        	}
        	
        	GlStateManager.translate(e, -0.52F + j, -0.72F);
        }
        else
        {
        	GlStateManager.translate((float)i * 0.56F, -0.52F + p_187459_2_ * -0.6F, -0.72F);
        }
    }
}
