package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemEarthSword extends ItemSword implements IReachWeapon
{

	public ItemEarthSword() 
	{
		super(RegisterItems.LEGENDARY);
		this.setRegistryName("earth_sword").setUnlocalizedName("earth_sword").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + "bonus reach : " + (this.bonusReach(null)));
		tooltip.add(TextFormatting.YELLOW + "create a seismic wave that damage nearby entities on right click");
    }
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();;

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 18, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1.4000000953674316D, 0));
        }

        return multimap;
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(new Random().nextInt(5) == 0)
		{
			target.motionY = 1.5D;
		}
        return true;
    }
    
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(hand == EnumHand.MAIN_HAND)
    	{
    		player.getCooldownTracker().setCooldown(this, 60);
    		for(EntityLivingBase entity : UtilsVoids.getEntitiesPos(EntityLivingBase.class, worldIn, p, 4))
    		{
    			if(entity != player && entity.onGround)
    			{
    				entity.motionY = 2;
        			UtilsVoids.attackEntityFrom(entity, DamageSource.causePlayerDamage(player), 3);
    			}
    		}
    		
    		for(int i = -4; i <= 4; ++i)
    		{
    			for(int j = -4; j <= 4; ++j)
        		{
    				BlockPos b = new BlockPos(player).add(i, 0, j);
    				BlockPos pos = b.add(0, worldIn.getHeight(b.getX(), b.getZ()) - b.getY() - 1, 0);
        			if(UtilsVoids.distanceIgnoreY(pos.getX() + 0.5D, pos.getZ() + 0.5D, player) < 5 && (i != 0 || j != 0))
        			{
        				IBlockState state = worldIn.getBlockState(pos);
        				if(state.getBlockHardness(worldIn, pos) <= 10 && state.getBlockHardness(worldIn, pos) >= 0
        						&& worldIn.getBlockState(pos.down()).isFullCube())
        				{
        					worldIn.setBlockToAir(pos);
        					EntityAbsorbedBlock entity = new EntityAbsorbedBlock(worldIn, state, pos);
        					entity.posY += 0.2F;
        					entity.motionY = 0.05F * UtilsVoids.distanceIgnoreY(entity.posX, entity.posZ, player);
        					if(!worldIn.isRemote)
        					{
            					worldIn.spawnEntity(entity);
        					}
        				}
        			}
        		}
    		}
    	}
    	
		return EnumActionResult.PASS;
    }

	@Override
	public float bonusReach(EntityPlayer player) 
	{
		return 0.8F;
	}

}
