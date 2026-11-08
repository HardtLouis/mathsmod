package com.mod.mathsmod.utils;

public class NumsHelper {

	public static int getIntFromString(String str)
	{
		if(str.length() == 0)
		{
			return 0;
		}
		else
		{
			try
			{
				return Integer.parseInt(str);
			}
			catch(NumberFormatException e)
			{
				return 0;
			}
		}
	}
	
	public static boolean isInteger(String str)
	{
		if(str.length() == 0)
		{
			return false;
		}
		else
		{
			try
			{
				int i = Integer.parseInt(str);
				return true;
			}
			catch(NumberFormatException e)
			{
				return false;
			}
		}
	}
	
	public static float getFloatFromString(String str)
	{
		if(str.length() == 0)
		{
			return 0;
		}
		else
		{
			try
			{
				return Float.parseFloat(str);
			}
			catch(NumberFormatException e)
			{
				return 0;
			}
		}
	}
	
	public static double getDoubleFromString(String str)
	{
		if(str.length() == 0)
		{
			return 0;
		}
		else
		{
			try
			{
				return Double.parseDouble(str);
			}
			catch(NumberFormatException e)
			{
				return 0;
			}
		}
	}
	
	public static int getIntFromStringFailNegative(String str)
	{
		if(str.length() == 0)
		{
			return -1;
		}
		else
		{
			try
			{
				return Integer.parseInt(str);
			}
			catch(NumberFormatException e)
			{
				return -1;
			}
		}
	}
	
	public static short getShortFromString(String str)
	{
		if(str.length() == 0)
		{
			return 0;
		}
		else
		{
			try
			{
				return Short.parseShort(str);
			}
			catch(NumberFormatException e)
			{
				return 0;
			}
		}
	}
	
}
