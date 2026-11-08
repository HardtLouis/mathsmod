package com.mod.mathsmod.add.items;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;
import com.mod.mathsmod.utils.SpecialTexture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPancarte extends Item implements IItemSpecialRenderer
{
	public ItemPancarte() 
	{
		this.setRegistryName("pancarte").setUnlocalizedName("pancarte").setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
	}
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
        return true;
    }
	
	public boolean getShareTag()
    {
        return true;
    }

    public void setDrawing(ItemStack stack, String used) {

        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setString("drawing", used);

    }
    
    public static String getDrawing(ItemStack stack) {

        if (!stack.hasTagCompound()) 
        {
            return "";
        }

        if (!stack.getTagCompound().hasKey("drawing")) 
        {
        	stack.getTagCompound().setString("drawing", "");
            return "";
        }

        return stack.getTagCompound().getString("drawing");
    }
	
	public float damages()
	{
		return 6F;
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damages(), 0));
        }

        return multimap;
    }

	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTicks) 
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel bakedPump = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/pancarte/pancarte.json"), "normal"));
		RenderLaser.renderModel(bakedPump);
		
		IBakedModel baked2 = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/pancarte/drawing.json"), "normal"));
		
		String playerName = "";
		
		if(!this.getDrawing(stack).isEmpty())
		{
			String str = this.getDrawing(stack);
			
			TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
			String name = str.replaceAll("/", "_").replaceAll(":", "_");
			RenderLaser.retexturePancarte(stack, baked2, new ResourceLocation(Mathsmod.MODID, name), partialTicks);
		}
		else
		{
			RenderLaser.retexturePancarte(stack, baked2, new ResourceLocation(Mathsmod.MODID, "textures/items/pancarte.png"), partialTicks);
		}
	}
	

	@SideOnly(Side.CLIENT)
	public static AbstractTexture getDownloadImageSkin(String link)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
    	String name = link.replaceAll("/", "a").replaceAll(":", "c");
    	
        ITextureObject itextureobject = texturemanager.getTexture(new ResourceLocation(Mathsmod.MODID, "loaded_" + name));
        
    	File file = new File(client_Proxy.PANCARTE_FOLDER, name);
        
        if (itextureobject == null)
        {
        	
        	ITextureObject itextureobject2 = new ThreadDownloadImageData(file, link, new ResourceLocation(Mathsmod.MODID, "textures/items/pancarte.png"), new ImageBufferDownload());
            texturemanager.loadTexture(new ResourceLocation(Mathsmod.MODID, "loaded_" + name), itextureobject2);
            texturemanager.deleteTexture(new ResourceLocation(Mathsmod.MODID, "loaded_" + name));

            itextureobject = TextureUtil.MISSING_TEXTURE;
            client_Proxy.loadingList.put(new ResourceLocation(Mathsmod.MODID, "loaded_" + name), 40);
            texturemanager.loadTexture(new ResourceLocation(Mathsmod.MODID, "loaded_" + name), itextureobject);
        }
        else if(!(itextureobject instanceof SpecialTexture) && client_Proxy.loadingList.containsKey(new ResourceLocation(Mathsmod.MODID, "loaded_" + name)) && client_Proxy.loadingList.get(new ResourceLocation(Mathsmod.MODID, "loaded_" + name)) == 0)
        {
        	client_Proxy.loadingList.remove(new ResourceLocation(Mathsmod.MODID, "loaded_" + name));
    		
        	try 
            {
				itextureobject = new SpecialTexture(ImageIO.read(file));
			} 
            catch (IOException e) 
            {
            	itextureobject = TextureUtil.MISSING_TEXTURE;
			}
            
            texturemanager.loadTexture(new ResourceLocation(Mathsmod.MODID, "loaded_" + name), itextureobject);
        }
        else if(client_Proxy.loadingList.containsKey(new ResourceLocation(Mathsmod.MODID, "loaded_" + name)))
        {
        	int i = client_Proxy.loadingList.get(new ResourceLocation(Mathsmod.MODID, "loaded_" + name));
        	
        	client_Proxy.loadingList.replace(new ResourceLocation(Mathsmod.MODID, "loaded_" + name), i, i-1);
        }

        if(itextureobject instanceof SpecialTexture)
        {
        	 return (SpecialTexture)itextureobject;
        }
        else
        {
        	return null;
        }
    }
}