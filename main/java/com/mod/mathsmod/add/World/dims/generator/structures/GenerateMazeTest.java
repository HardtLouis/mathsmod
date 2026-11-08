package com.mod.mathsmod.add.World.dims.generator.structures;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.Tuple;

public class GenerateMazeTest 
{
	public static List<List<Integer>> genererLab(Random rand)
	{
		List<List<Integer>> list = Lists.newArrayList();
		for(int i = 0; i < 20; ++i)
		{
			List<Integer> l2 = Lists.newArrayList();
			for(int j = 0; j < 20; ++j)
			{
				l2.add(0);
			}
			list.add(l2);
		}
		
		for(int i = 0; i < 9; ++i)
		{
			for(int j = 0; j < 9; ++j)
			{
				list.get(1 + 2 * i).set(1 + 2 * j, 1 + i + j * 9);
			}
		}
		
		int failSafe = 0;
		
		while(list.get(1).get(1) != list.get(17).get(17) && failSafe < 10000)
		{
			int i = 1 + 2 * rand.nextInt(9);
			int j = 1 + 2 * rand.nextInt(9);

			int direction = rand.nextInt(4);
			
			if(direction == 0 && j >= 3)
			{
				if(list.get(i).get(j-2) != list.get(i).get(j))
				{
					list.get(i).set(j - 1, list.get(i).get(j));
					changerValeursAlentours(list.get(i).get(j-2), list.get(i).get(j), list);
				}
			}
			else if(direction == 1 && j < 17)
			{
				if(list.get(i).get(j+2) != list.get(i).get(j))
				{
					list.get(i).set(j + 1, list.get(i).get(j));
					changerValeursAlentours(list.get(i).get(j+2), list.get(i).get(j), list);
				}
			}
			else if(direction == 2 && i >= 3)
			{
				if(list.get(i-2).get(j) != list.get(i).get(j))
				{
					list.get(i-1).set(j, list.get(i).get(j));
					changerValeursAlentours(list.get(i-2).get(j), list.get(i).get(j), list);
				}
			}
			else if(direction == 3 && i < 17)
			{
				if(list.get(i+2).get(j) != list.get(i).get(j))
				{
					list.get(i+1).set(j, list.get(i).get(j));
					changerValeursAlentours(list.get(i+2).get(j), list.get(i).get(j), list);
				}
			}
			failSafe += 1;
			
		}
	
		return list;
		
	}
	
	public static void changerValeursAlentours(int valARemplacer, int val, List<List<Integer>> list)
	{
		for(int i = 0; i < list.size(); ++i)
		{
			for(int j = 0; j < list.get(0).size(); ++j)
			{
				if(list.get(i).get(j) == valARemplacer)
				{
					list.get(i).set(j, val); 
				}
			}
		}
	}
	
}
