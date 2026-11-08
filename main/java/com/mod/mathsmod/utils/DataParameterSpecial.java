package com.mod.mathsmod.utils;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;

public class DataParameterSpecial extends DataParameter
{
	
	private final String str;

	public DataParameterSpecial(int idIn, DataSerializer serializerIn, String type)
	{
		super(idIn, serializerIn);
		str = type;
	}

	public String getType()
	{
		return str;
	}
}
