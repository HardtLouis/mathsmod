package com.mod.mathsmod.add.event;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.blocks.BlockUnbreakableCopy;
import com.mod.mathsmod.add.blocks.stateMapper.CopyStateMapper;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelsEvent {

	/*@SubscribeEvent
	public void registerBlocksModel(ModelRegistryEvent event)
	{
		for(BlockUnbreakableCopy block : RegisterBlocks.copies)
		{
			ModelLoader.setCustomStateMapper(block, new CopyStateMapper());
		}
	}*/
	
	@SubscribeEvent
	public void modelRegisterEvent(ModelBakeEvent event)
	{
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/pistol/pistol.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/pistol/pistol_mec.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/aa12/aa12.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/aa12/aa12_lev.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/p90/p90_core.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/p90/p90_mag.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/basic_shotgun/basic_shotgun.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/basic_shotgun/basic_shotgun_pump.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/ar15/ar15.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/ar15/ar15_mag.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/r870/shotgun.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/r870/shotgun_p2.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/scar/ar_core.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/scar/ar_lev.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/scar/ar_mag.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/fafr/fafr.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/fafr/fafr_mag.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/thompson/thompson.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/thompson/thompson_mag.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/hunter_shotgun/hunter_shotgun.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/hunter_shotgun/hunter_shotgun_bal.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/famas/famas_core.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/famas/famas_mag.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/famas/famas_poul.json"));

		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/heavy_famas/heavy_famas_mag.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/heavy_famas/heavy_famas_core.json"));

		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/carbine/carbine.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/gipsy_strength/gipsy_strength.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/sniper/sniper.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/grenade_launcher/grenade_launcher.json"));

		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/doctors_shotgun/doctors_shotgun.json"));
	
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/deagle/deagle.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/rocket_launcher/rocket_launcher.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/scs7/scs7.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/chicken_launcher/chicken_launcher.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/kb800/kb800.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/antivehicle_rocket_launcher/antivehicle_rocket_launcher.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/black_hole_launcher/black_hole_launcher.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/minigun/minigun.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/minigun/minigun_canons.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/ak47/ak47.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/ak47/ak47_lev.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/ak47/ak47_mag.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/canardier/canardier.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/revolver/revolver.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/aerian_monstruosity/aerian_monstruosity.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/tr_sniper/tr_sniper.json"));
		
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/block/fruit_punch_clown.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown.json"));
		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/block/broken_fruit_punch_clown/fruit_punch_clown_head.json"));

		RenderLaser.registerJsonModel(event.getModelRegistry(), new ResourceLocation(Mathsmod.MODID, "models/item/pancarte/pancarte.json"));
	}
	
	@SubscribeEvent
    public void textureStitchEvent(TextureStitchEvent.Pre event)
    {
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/medic"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/guns_modern"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/guns"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/guns_ender"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/guns_tech"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/bazookb"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/guns_gipsy"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "items/guns_deagle"));
		
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/boots"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/face"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/face_side"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/fp"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/fp_up"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/glove"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/hat"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/short"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/fpc/spring_thing"));
		
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/boots"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/face"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/face_side"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/fp"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/fp_up"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/glove"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/hat"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/short"));
		event.getMap().registerSprite(new ResourceLocation(Mathsmod.MODID, "blocks/broken_fpc/spring_thing"));
    }
	
	@SubscribeEvent
	public void colorGrass(BiomeEvent.BiomeColor event)
	{
		if(MConfig.getConfig().get(Configuration.CATEGORY_CLIENT, "lgbt mod", false).getBoolean() && Minecraft.getMinecraft().player != null)
		{
	        event.setNewColor(RenderLaser.getColorFromAngleQuarter(Minecraft.getMinecraft().getSystemTime() / 1000 * 20));
		}
	}
}
