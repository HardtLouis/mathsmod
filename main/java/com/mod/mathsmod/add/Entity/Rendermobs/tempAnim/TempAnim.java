package com.mod.mathsmod.add.Entity.Rendermobs.tempAnim;

public class TempAnim 
{
	public String entityId;
	public int animIndex;
	public int time;
	
	public TempAnim(String entityUUIDIn, int animIndexIn, int timeIn)
	{
		entityId = entityUUIDIn;
		animIndex = animIndexIn;
		time = timeIn;
	}
}
