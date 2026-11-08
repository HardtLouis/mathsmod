package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageTempAnim;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityJarate extends EntityThrowable
{
    public EntityJarate(World worldIn)
    {
        super(worldIn);
    }

    public EntityJarate(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityJarate(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, Block.getStateId(Blocks.WOOL.getStateFromMeta(4)));
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
    	if(ticksExisted > 3 && !world.isRemote)
    	{
    		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, result.hitVec, 2))
        	{
                if (entity instanceof EntityBlaze)
                {
                    entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)20);
                }
                
                if (entity instanceof EntityEnderman && this.getThrower() instanceof EntityPlayer)
                {
                    entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) this.getThrower()), (float)20);
                }
                
                if(entity instanceof EntityLiving)
                {
                	((EntityLiving) entity).setAttackTarget(this.getThrower());
                }
                
                for (int j = 0; j < 20; ++j)
                {
                	this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, this.rand.nextDouble() - 0.5F, this.rand.nextDouble() - 0.5F, this.rand.nextDouble() - 0.5F, Block.getStateId(Blocks.WOOL.getStateFromMeta(4)));
                }
                
                Mathsmod.network.sendToAll(new MessageTempAnim(entity, 2));

        	}
    		
            this.world.setEntityState(this, (byte)3);
            this.setDead();
    	}
        
        world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.AMBIENT, 1.0F, 1.2F);
    }
}