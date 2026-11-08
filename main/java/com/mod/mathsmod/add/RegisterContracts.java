package com.mod.mathsmod.add;

import com.mod.mathsmod.add.contracts.ContractBasicLootbox;
import com.mod.mathsmod.add.contracts.ContractFurnace;
import com.mod.mathsmod.add.contracts.ContractFurnaceUpgrade;
import com.mod.mathsmod.add.contracts.ContractKurayumIngot;
import com.mod.mathsmod.add.contracts.ContractKurayumJetpack;
import com.mod.mathsmod.add.contracts.ContractKurayumRod;
import com.mod.mathsmod.add.contracts.ContractLootBox;
import com.mod.mathsmod.add.contracts.ContractNightShard;
import com.mod.mathsmod.add.contracts.ContractOreLootbox;
import com.mod.mathsmod.add.contracts.ContractRebondiBoots;
import com.mod.mathsmod.add.contracts.ContractRevolver;
import com.mod.mathsmod.add.contracts.ContractShotbow;
import com.mod.mathsmod.add.contracts.ContractSilverIngot;
import com.mod.mathsmod.add.contracts.ContractTatanator;
import com.mod.mathsmod.add.contracts.ContractTechShield;
import com.mod.mathsmod.add.contracts.ContractTutu;
import com.mod.mathsmod.add.contracts.ContractsBase;

public class RegisterContracts 
{
	public static ContractsBase FIRST_STEP = new ContractFurnace();
	public static ContractsBase REVOLVER = new ContractRevolver();
	public static ContractsBase REBONDI_BOOTS = new ContractRebondiBoots();
	public static ContractsBase SILVER_INGOT = new ContractSilverIngot();
	public static ContractsBase NIGHT_SHARD = new ContractNightShard();
	public static ContractsBase FURNACE_UPGRADE = new ContractFurnaceUpgrade();
	public static ContractsBase TATANATOR = new ContractTatanator();
	public static ContractsBase SHOTBOW = new ContractShotbow();
	public static ContractsBase KURAYUM_ROD = new ContractKurayumRod();
	public static ContractsBase KURAYUM_INGOT = new ContractKurayumIngot();
	public static ContractsBase TECH_SHIELD = new ContractTechShield();
	public static ContractsBase KURAYUM_JETPACK = new ContractKurayumJetpack();
	public static ContractsBase TUTU = new ContractTutu();
	
	public static ContractsBase POLARITE_LOOT_BOX = new ContractLootBox();
	public static ContractsBase LOOT_BOX = new ContractBasicLootbox();
	public static ContractsBase ORE_LOOT_BOX = new ContractOreLootbox();
	
	public static void register()
	{
		FIRST_STEP.register();
		REVOLVER.register();
		REBONDI_BOOTS.register();
		SILVER_INGOT.register();
		NIGHT_SHARD.register();
		FURNACE_UPGRADE.register();
		TATANATOR.register();
		SHOTBOW.register();
		KURAYUM_ROD.register();
		KURAYUM_INGOT.register();
		TECH_SHIELD.register();
		KURAYUM_JETPACK.register();
		TUTU.register();
		POLARITE_LOOT_BOX.register();
		LOOT_BOX.register();
		ORE_LOOT_BOX.register();
	}
}
