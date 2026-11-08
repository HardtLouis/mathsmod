package com.mod.mathsmod.add.items.guns;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.event.MEvents.onBlockImpactEvent;
import com.mod.mathsmod.add.event.MEvents.onImpactEvent;
import com.mod.mathsmod.utils.IGunCraft;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDiamondSniperBullet extends ItemBaseBullet implements IGunCraft
{

	public ItemDiamondSniperBullet() {
		super("diamond_sniper_bullet");
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.RED + "-10% damages on body");
        tooltip.add(TextFormatting.GREEN + "+30% damages on headshot");
    }

	@Override
	public EnumBulletType type() {
		return EnumBulletType.LONG;
	}

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Items.GUNPOWDER));
		list.set(1, new ItemStack(Items.DIAMOND));
		list.set(2, new ItemStack(Items.DIAMOND));
		list.set(3, new ItemStack(Items.DIAMOND));
		list.set(5, new ItemStack(Items.DIAMOND));
		list.set(6, new ItemStack(Items.DIAMOND));
		
		return list;
	}

	@Override
	public int size() 
	{
		return 6;
	}

	@Override
	public boolean displayRecipe() 
	{
		return true;
	}
	
	@Override
	public void onImpact(RayTraceResult result, World worldIn, Entity entityIn, Entity shotEntity, float rotationYaw, float rotationPitch, double posX, double posY, double posZ, ItemStack stack, ItemGun item, double basePosX, double basePosY, double basePosZ, boolean antiVehicles)
	{
		if(entityIn instanceof EntityLivingBase)
		{
			stack = ((EntityLivingBase) entityIn).getHeldItemMainhand();
		}
		
		float damagesIn = item.getDamagesCaused() * item.damageMultiplier(stack, entityIn, null) * 0.9F;
		
		if(onImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.entityHit, damagesIn) != 0)
		{
			damagesIn = onImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.entityHit, damagesIn);
			
			if(result.entityHit != null && result.entityHit != entityIn && !worldIn.isRemote)
			{
				float i = damagesIn;
				if(entityIn != null && UtilsVoids.isBackstabing(entityIn, result.entityHit) && stack.getItem() instanceof ItemGun)
				{
					i *= ((ItemGun) stack.getItem()).backMultiplier();
				}
				if(posY >= result.entityHit.getEyeHeight() + result.entityHit.posY && stack.getItem() instanceof ItemGun)
				{
					i *= ((ItemGun) stack.getItem()).HeadMultiplier() * 1.3;
					if(((ItemGun) stack.getItem()).HeadMultiplier() > 1.0F)
					{
						ItemGun.makeSuperHit(result.entityHit, 0, entityIn);
					}
				}
				if(entityIn != null && entityIn.getRidingEntity() != null && result.entityHit == entityIn.getRidingEntity())
				{
					return;
				}
				if(result.entityHit instanceof MultiPartEntityPart)
				{
					MultiPartEntityPart entity = (MultiPartEntityPart) result.entityHit;
					if(entity.parent instanceof EntityLivingBase)
					{
						UtilsVoids.attackEntityFrom((EntityLivingBase) ((EntityLivingBase) entity.parent), MDamageSource.causeThrownDamage(entityIn), i);
					}
					if(entity.parent instanceof EntityTank)
					{
						entity.parent.attackEntityFromPart(entity, MDamageSource.causeThrownDamage(entityIn), i);
					}
					if(entity.parent instanceof EntityPlane && antiVehicles)
					{
						entity.parent.attackEntityFromPart(entity, MDamageSource.causeThrownDamage(entityIn), ((EntityPlane) entity.parent).maxDamages() / 20);
					}
				}
				else if(result.entityHit instanceof EntityLivingBase)
				{
					UtilsVoids.attackEntityFrom((EntityLivingBase) result.entityHit, MDamageSource.causeThrownDamage(entityIn), i);
				}
				else
				{
					if(result.entityHit instanceof EntityTank)
					{
						result.entityHit.attackEntityFrom(MDamageSource.causeThrownDamage(entityIn), i);
					}
					else if(result.entityHit instanceof EntityPlane && antiVehicles)
					{
						result.entityHit.attackEntityFrom(MDamageSource.causeThrownDamage(entityIn), ((EntityPlane) result.entityHit).maxDamages() / 20);
					}
					else
					{
						result.entityHit.attackEntityFrom(MDamageSource.causeThrownDamage(entityIn), i);
					}
					
				}
				return;
			}
			if(result.typeOfHit.BLOCK == result.typeOfHit && worldIn.getBlockState(result.getBlockPos()).getCollisionBoundingBox(worldIn, result.getBlockPos()) != null && worldIn.getBlockState(result.getBlockPos()).getCollisionBoundingBox(worldIn, result.getBlockPos()) != Block.NULL_AABB)
			{
				if(onBlockImpactEvent.onBulletImpactEvent(entityIn, worldIn, stack, result.getBlockPos(), damagesIn))
				{
					if(!worldIn.isRemote)
					{
						BlockPos pos = result.getBlockPos();
						Block block = worldIn.getBlockState(pos).getBlock();
						if(this.CanBreakMaterials().contains(worldIn.getBlockState(pos).getMaterial()) && worldIn.getBlockState(pos).getBlock().getExplosionResistance(entityIn) <= 1)
						{
							worldIn.destroyBlock(pos, false);
						}
						if(block instanceof BlockTNT)
						{
							block.onBlockDestroyedByPlayer(worldIn, pos, worldIn.getBlockState(pos).withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
							worldIn.setBlockToAir(pos);
						}
					}
				}
			}
		}
	}
}
