package com.mod.mathsmod.add.items.guns;

public class ThreeFloat
{
	public float x;
	public float y;
	public float z;
	
	public ThreeFloat(float xIn, float yIn, float zIn)
	{
		x = xIn;
		z = zIn;
		y = yIn;
	}
	
	public float getHighNumber()
	{
		if(x > y && x > z)
		{
			return x;
		}
		if(y > x && y > z)
		{
			return y;
		}
		if(z > x && z > y)
		{
			return z;
		}
		else
		{
			return x;
		}
	}
}
