package com.mod.mathsmod.add;

import com.mod.mathsmod.add.particles.ParticleNGFlames;
import com.mod.mathsmod.add.particles.ParticlesGunShoot;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;

public class MathsParticles 
{
	public static void registerParticles()
	{
		Minecraft.getMinecraft().effectRenderer.registerParticle(60, new ParticlesGunShoot.Factory());
		Minecraft.getMinecraft().effectRenderer.registerParticle(61, new ParticleNGFlames.Factory());
	}
}
