package com.mod.mathsmod.add.spell;

import java.util.List;
import java.util.Random;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class SpellBase 
{
	protected Random rand = new Random();
	
	public abstract String spellName();
	
	public abstract EnumSpellType type();
	
	public abstract EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player);
	
	public abstract EnumSpellMovement movementType(int phase, int progress, EntityLivingBase player);
	
	public abstract int level();
	
	public abstract int phases(EntityLivingBase player);
	
	public abstract int duration(int phase, EntityLivingBase player);
	
	public abstract void spellUseCommon(int phase, int progress, EntityLivingBase player);
	
	@SideOnly(Side.CLIENT)
	public abstract void renderUse(int phase, int progress, EntityLivingBase player);
	
	@SideOnly(Side.CLIENT)
	public abstract void renderFirstPerson(int phase, int progress, EntityPlayer player);
	
	@SideOnly(Side.CLIENT)
	public abstract void spellUseClient(int phase, int progress, EntityLivingBase player);
	
	public abstract int cost(EntityPlayer player);
	
	public abstract int cooldown(EntityPlayer player);
	
	public abstract int spellCooldown(EntityPlayer player);
	
	public String IconName(EntityPlayer player)
	{
		return this.spellName();
	}

	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
	}
	
	public void spellUseMelee(int phase, int progress, EntityLivingBase player, Entity entity, ItemStack stack)
	{
		
	}
	
	public abstract List<String> description();
}
