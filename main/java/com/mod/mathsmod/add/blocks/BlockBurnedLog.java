package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

public class BlockBurnedLog extends BlockRotatedPillar
{
	
	public BlockBurnedLog() 
	{
		super(Material.WOOD);
		this.setRegistryName("burnt_log").setUnlocalizedName("burnt_log");
		this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setLightLevel(0.5F);
	}

}
