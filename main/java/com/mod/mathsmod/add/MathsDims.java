package com.mod.mathsmod.add;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.dims.BananaProvider;
import com.mod.mathsmod.add.World.dims.CopravegProvider;
import com.mod.mathsmod.add.World.dims.NightmareProvider;
import com.mod.mathsmod.add.World.dims.SirojusProvider;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class MathsDims {

	public static DimensionType Copraveg;
	public static DimensionType Sirojus;
	public static DimensionType Nightmare;
	public static DimensionType Banana;
	
	public static int copraID;
	public static int siroID;
	public static int nightmareID;
	public static int bananaID;

    public static void init() {
        registerDimensionTypes();
    }

    private static void registerDimensionTypes() {
    	
    	copraID = MathsDims.getDimensionIdUnused();
    	Copraveg = DimensionType.register("copraveg", "copraveg", copraID , CopravegProvider.class, false);
    	DimensionManager.registerDimension(copraID, Copraveg);

    	siroID = MathsDims.getDimensionIdUnused();
    	Sirojus = DimensionType.register("sirojus", "sirojus", siroID, SirojusProvider.class, false);
        DimensionManager.registerDimension(siroID, Sirojus);
        
        nightmareID = MathsDims.getDimensionIdUnused();
    	Nightmare = DimensionType.register("nightmare", "nightmare", nightmareID , NightmareProvider.class, false);
    	DimensionManager.registerDimension(nightmareID, Nightmare);
        
    	bananaID = MathsDims.getDimensionIdUnused();
    	Banana = DimensionType.register("banana", "banana", bananaID , BananaProvider.class, false);
    	DimensionManager.registerDimension(bananaID, Banana);
    }
    
    private static int getDimensionIdUnused()
    {
    	return DimensionManager.getNextFreeDimId();
    }
	
}
