package com.mod.mathsmod.add.items.guns;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityImprovedArrow;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShotBow extends ItemBow implements IGunCraft
{
	public ItemShotBow()
	{
		this.maxStackSize = 1;
		this.setUnlocalizedName("shotbow").setRegistryName("shotbow");
        this.setCreativeTab(Mathsmod.MATHSMOD_GUNS_TAB);
        this.setMaxDamage(0);
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}
	
	public static float getArrowVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i) * 1.5F;

                if ((double)f >= 0.5D)
                {
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));
                    for(int a = -1; a <= 1; ++a)
                    {
                    	for(int z = -1; z <= 1; ++z)
                        {
                    		if (!worldIn.isRemote)
                            {
                                EntityArrow entityarrow = new EntityImprovedArrow(worldIn, entityplayer);
                                entityarrow = this.customizeArrow(entityarrow);
                                entityarrow.shoot(entityplayer, entityplayer.rotationPitch + a * 6, entityplayer.rotationYaw + z * 6, 0.0F, f * 3.0F, 1.0F);
                                entityarrow.setDamage(entityarrow.getDamage() * 0.2);
                                entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                                
                                if (f == 1.0F)
                                {
                                    entityarrow.setIsCritical(true);
                                }

                                int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                                if (j > 0)
                                {
                                    entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.1D);
                                }

                                int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                                if (k > 0)
                                {
                                    entityarrow.setKnockbackStrength(k);
                                }

                                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                                {
                                    entityarrow.setFire(100);
                                }

                                //stack.damageItem(1, entityplayer);

                                if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                                {
                                    entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                                }
                                
                                if(i >= 30)
                                {
                                	entityarrow.setFire(1000);
                                	entityarrow.setGlowing(false);
                                	entityarrow.setDamage(entityarrow.getDamage() * 2F);
                                }

                                worldIn.spawnEntity(entityarrow);
                            }
                        }
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1 && !entityplayer.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }
	
	protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() == Items.ARROW;
    }

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		list.set(0, new ItemStack(Items.BOW));
		list.set(1, new ItemStack(RegisterItems.BASIC_SHOTGUN));
		return list;
	}

	@Override
	public int size()
	{
		return 1;
	}

	@Override
	public boolean displayRecipe() 
	{
		return true;
	}
}