package com.mod.mathsmod.add.contractsManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


//Useless now
@SideOnly(Side.CLIENT)
public class ContractManager 
{
	
	public static HashMap<ContractsBase, Integer> contracts = Maps.newHashMap();
    
	/*public static void saveContracts(World world, EntityPlayer player) 
	{
		tryDeleteFile(world, player);
		tryCreateFile(world, player);
		
		List list = Lists.newArrayList();
		
		for(ContractsBase contract : contracts.keySet())
		{
			list.add(contract.id + "//" + contracts.get(contract) + "//fin");
		}
		
		try 
		{
			writeLineToFile(list, world, player);
		} 
		catch (IOException e)
		{
		}
	}

	public static void loadContracts(World world, EntityPlayer player)
	{
		contracts = Maps.newHashMap();
		
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new File("mathsmod/mathsmod_datas_" + world.getSeed() + player.getName() + ".tkt_frr"));
		}
		catch (FileNotFoundException e)
		{
			
		}
		if(scanner != null)
		{
			while (scanner.hasNextLine()) 
			{
				String line = scanner.nextLine();
				String[] donnes = line.split("//");
				if(donnes.length == 3)
				{
					contracts.put(ContractsBase.getByName(donnes[0]), NumsHelper.getIntFromString(donnes[1]));
				}
			}
		}
	}
	
	public static HashMap<ContractsBase, Integer> getContracts()
	{
		return contracts;
	}
	
	public static void setContracts(HashMap<ContractsBase, Integer> list)
	{
		contracts = list;
	}
	
	public static void resetContracts()
	{
		contracts.clear();
		
		for(ContractsBase ctr : contracts.keySet())
		{
			contracts.put(ctr, 0);
		}
	}
	
	public static String getContract(int id)
	{
		return (String) contracts.keySet().toArray()[id];
	}
	
	public static boolean setContract(ContractsBase capabilitiesID, int advancment, int oldAdvancment)
	{
		return contracts.replace(capabilitiesID, oldAdvancment, advancment);
	}
	
	public static boolean addContracts(ContractsBase capabilityID, int advancment)
	{
		if(!contracts.containsKey(capabilityID))
		{
			return contracts.put(capabilityID, advancment) != null;
		}
		else
		{
			int old = contracts.get(capabilityID);
			return contracts.replace(capabilityID, old + advancment) != null;
		}
	}
	
	public static void setContracts(ContractsBase byName, int n) 
	{
		contracts.replace(byName, n);
	}
	
	public static void tryDeleteFile(World world, EntityPlayer player)
	{
		Path path = Paths.get("mathsmod/mathsmod_datas_" + world.getSeed() + player.getName() + ".tkt_frr");
        try 
        {
            Files.delete(path);
            System.out.println("File deleted successfully");
        } 
        catch (IOException e) 
        {
        }
	}
	
	public static void tryCreateFile(World world, EntityPlayer player)
	{
		try 
		{
			File file = new File("mathsmod/mathsmod_datas_" + world.getSeed() + player.getName() + ".tkt_frr");
			
			if (file.getParentFile() != null)
			{
			    file.getParentFile().mkdirs();
			}
			
            if (file.createNewFile()) 
            {
                System.out.println("File created: " + file.getName());
            } 
            else 
            {
                System.out.println("File already exists.");
            }
        } 
		catch (IOException e) 
		{
            System.out.println("An error occurred.");
        }
	}
	
	public static void writeLineToFile(List<String> lst, World world, EntityPlayer player) throws IOException 
	{
	    Path path = Paths.get("mathsmod/mathsmod_datas_" + world.getSeed() + player.getName() + ".tkt_frr");
	    try (BufferedWriter bw = Files.newBufferedWriter(path))
	    {
	    	for(String str : lst)
	    	{
		        bw.write(str);
		        bw.newLine();
	    	}
	    }
	}*/
}
