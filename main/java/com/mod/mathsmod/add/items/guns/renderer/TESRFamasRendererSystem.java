package com.mod.mathsmod.add.items.guns.renderer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.Matrix4f;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.IMedicWeapons;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;
import com.mod.mathsmod.add.items.guns.accessories.IRenderAccessories;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class TESRFamasRendererSystem extends TileEntitySpecialRenderer<TileEntity>
{
	
	public static TESRFamasRendererSystem instance;
	private TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
	private RenderItem render = Minecraft.getMinecraft().getRenderItem();
	private TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
	boolean rendered;
	
	public static HashMap<Item, ResourceLocation> iconMap = Maps.newHashMap();
	
	public void renderSpecial(ItemStack s, IAnimatedGun gun, float partialTicks)
    {
		//GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate(0.5, 0.5, 0.9);
        GlStateManager.scale(0.25, 0.25, 0.25);
        
		
		GlStateManager.scale(-1F, -1F, 1F);
		
		if(Minecraft.getMinecraft().player != null)
		{
			EntityPlayer entity = Minecraft.getMinecraft().player;
			
			List<ItemStack> list = Lists.newArrayList();
			
			for(EntityLivingBase entity1 : UtilsVoids.getEntities(EntityLivingBase.class, entity.world, entity, 40))
			{
				if(entity1.getHeldItemMainhand() == s)
				{
					list.add(entity1.getHeldItemMainhand());
				}
				if(entity1.getHeldItemOffhand() == s)
				{
					list.add(entity1.getHeldItemOffhand());
				}
			}
			
			if(list.contains(s))
			{
				this.renderModel(gun, s);
			}
			else if(entity.inventory.mainInventory.contains(s) && (entity.getHeldItemMainhand() == s || entity.getHeldItemOffhand() == s))
			{
				this.renderModel(gun, s);
			}
			else if(this.iconMap.containsKey(s.getItem()))
			{
				this.renderIconItem(s);
			}
			else
			{
				this.renderModel(gun, s);
			}
		}
		else
		{
			this.renderIconItem(s);
		}
		
		GlStateManager.enableRescaleNormal();
		/*if(s.hasEffect())
		{
			this.renderEffect(gun, s);
		}*/
    }
	
	public void renderModel(IAnimatedGun gun, ItemStack s)
	{
		GlStateManager.pushMatrix();
		
		if(gun instanceof ItemMachineGun && ((ItemMachineGun) gun).canEquipModifier() && gun instanceof IRenderAccessories)
		{
			ItemMachineGun item = (ItemMachineGun) gun;
			
			if(!item.getModifiers(s).get(0).isEmpty() || !item.getModifiers(s).get(1).isEmpty() || !item.getModifiers(s).get(2).isEmpty())
			{
		        GlStateManager.disableCull();
		       
				GlStateManager.translate(0.25, 0, 0);
				GlStateManager.scale(1, -1, 1);
				IRenderAccessories changes = (IRenderAccessories) gun;
				
				if(!item.getModifiers(s).get(0).isEmpty())
				{
					GlStateManager.translate(changes.lampLocation(s).x, changes.lampLocation(s).y, changes.lampLocation(s).z);
					this.renderItemSide(item.getModifiers(s).get(0), TransformType.NONE, false);
					GlStateManager.translate(-changes.lampLocation(s).x, -changes.lampLocation(s).y, -changes.lampLocation(s).z);
				}
				if(!item.getModifiers(s).get(1).isEmpty())
				{
					GlStateManager.translate(changes.mufflerLocation(s).x, changes.mufflerLocation(s).y, changes.mufflerLocation(s).z);
					this.renderItemSide(item.getModifiers(s).get(1), TransformType.NONE, false);
					GlStateManager.translate(-changes.mufflerLocation(s).x, -changes.mufflerLocation(s).y, -changes.mufflerLocation(s).z);
				}
				if(!item.getModifiers(s).get(2).isEmpty())
				{
					GlStateManager.translate(changes.scopeLocation(s).x, changes.scopeLocation(s).y, changes.scopeLocation(s).z);
					this.renderItemSide(item.getModifiers(s).get(2), TransformType.NONE, false);
					GlStateManager.translate(-changes.scopeLocation(s).x, -changes.scopeLocation(s).y, -changes.scopeLocation(s).z);
				}
				GlStateManager.scale(1, -1, 1);
				GlStateManager.translate(-0.25, 0, 0);
				
		        GlStateManager.enableCull();
			}
			
		}
		if(gun.texture(s) != null)
		{
			this.bindTexture(gun.texture(s));
		}
		else
		{
			this.bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/misc/guns_gold.png"));
		}
		gun.model(0.0625F).renderModel(s, this.rendererDispatcher.entity);
		if(gun instanceof IGoldenAnimatedGun && ((IGoldenAnimatedGun) gun).getAnimated(s))
		{
			this.renderLayer((IGoldenAnimatedGun) gun, s);
		}
		
		if(gun instanceof IMedicWeapons)
		{
			IMedicWeapons wep = (IMedicWeapons) gun;
			if(wep.hasAnimatedLayer() != null)
			{
				this.renderLayer(wep.hasAnimatedLayer(), gun, s);
			}
		}

		GlStateManager.popMatrix();
	}
	
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super.setRendererDispatcher(rendererDispatcherIn);
        instance = this;
    }
	
	private void renderLayer(IGoldenAnimatedGun gun, ItemStack s)
    {
		GlStateManager.depthMask(false);
		ResourceLocation loc = new ResourceLocation(Mathsmod.MODID, "textures/misc/guns_gold.png");
		
        if(gun.getLayer(s) != null)
        {
        	loc = gun.texture(s);
        }
        this.bindTexture(loc);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        
        float i = 1;
        
        GlStateManager.scale(2, 2, 0);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / i;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        float f3 = 0.5F;
        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
        GlStateManager.disableLighting();
        if(s.getItem() instanceof ItemGun)
		{
			ItemGun item = (ItemGun) s.getItem();
			gun.model(0.0635F).renderModel(s, this.rendererDispatcher.entity);
		}
		else
		{
			gun.model(0.0635F).renderModel(s, this.rendererDispatcher.entity);
		}
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }
	
	private void renderEffect(IAnimatedGun gun, ItemStack s)
    {
		GlStateManager.depthMask(false);
        this.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        GlStateManager.scale(8.0, 8.0, 8.0);
        GlStateManager.color(0.5F, 0.5F, 0.5F, 0.3F);
        GlStateManager.color(1F, 0, 0, 0.4F);
        GlStateManager.disableLighting();
        if(s.getItem() instanceof ItemGun)
		{
			ItemGun item = (ItemGun) s.getItem();
			gun.model(0.0629F).renderModel(s, (EntityLivingBase) null);
		}
		else
		{
			gun.model(0.0629F).renderModel(s, (EntityLivingBase) null);
		}
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }
	
	private void renderLayer(ResourceLocation loc, IAnimatedGun gun, ItemStack s)
    {
		GlStateManager.depthMask(false);
		
        this.bindTexture(loc);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        
        float i = 1;
        
        GlStateManager.scale(1, 1, 0);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / i;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        float f3 = 0.5F;
        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
        GlStateManager.disableLighting();
        if(s.getItem() instanceof ItemGun)
		{
			ItemGun item = (ItemGun) s.getItem();
			gun.model(0.0640F).renderModel(s, this.rendererDispatcher.entity);
		}
		else
		{
			gun.model(0.0640F).renderModel(s, this.rendererDispatcher.entity);
		}
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }
	
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
    }
	
	public static void setGunIcon(Item item, ResourceLocation loc)
	{
		if(!iconMap.containsKey(item))
		{
			iconMap.put(item, loc);
		}
		else
		{
			iconMap.replace(item, loc);
		}
	}
	
	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), 0).tex((double)((float)(textureX + 0) * 0.0625F), (double)((float)(textureY + height) * 0.0625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0).tex((double)((float)(textureX + width) * 0.0625F), (double)((float)(textureY + height) * 0.0625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), 0).tex((double)((float)(textureX + width) * 0.0625F), (double)((float)(textureY + 0) * 0.0625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), 0).tex((double)((float)(textureX + 0) * 0.0625F), (double)((float)(textureY + 0) * 0.0625F)).endVertex();
        tessellator.draw();
    }
	
	public void renderItemSide(ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded)
    {
        if (!heldStack.isEmpty())
        {
            Item item = heldStack.getItem();
            Block block = Block.getBlockFromItem(item);
            GlStateManager.pushMatrix();
            boolean flag = Minecraft.getMinecraft().getRenderItem().shouldRenderItemIn3D(heldStack) && block.getBlockLayer() == BlockRenderLayer.TRANSLUCENT;

            if (flag)
            {
                GlStateManager.depthMask(false);
            }

            Minecraft.getMinecraft().getRenderItem().renderItem(heldStack, transform);

            if (flag)
            {
                GlStateManager.depthMask(true);
            }

            GlStateManager.popMatrix();
        }
    }
	
	public void renderIconItem(ItemStack s)
	{
		this.bindTexture(this.iconMap.get(s.getItem()));
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotate(-90, 0, 1, 0);
		GlStateManager.translate(-3.75, -1, 0);
		ItemTransformVec3f vec = this.render.getItemModelMesher().getItemModel(s).getItemCameraTransforms().gui;
		
		GlStateManager.translate(-vec.translation.x * 8, vec.translation.y * 8, -vec.translation.z * 8);
		GlStateManager.scale(1 / vec.scale.x, 1 / vec.scale.y, 1 / vec.scale.z);
		GlStateManager.rotate(-vec.rotation.z, 0, 0, 1);
		GlStateManager.rotate(-vec.rotation.y, 0, 1, 0);
		GlStateManager.rotate(-vec.rotation.x, 1, 0, 0);
		
		GlStateManager.scale(0.25, 0.25, 0.25);
		this.drawTexturedModalRect(0, 0, 0, 0, 16, 16);
	}
}
