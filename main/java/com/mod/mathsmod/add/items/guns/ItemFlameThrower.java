package com.mod.mathsmod.add.items.guns;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.CSE.ClientSideEnt;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ILeftClickItem;
import com.mod.mathsmod.add.items.ItemArmor.IBulletProtection;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;
import com.mod.mathsmod.add.messages.MessageAddsCSE;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.IGunCraft;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFlameThrower extends ItemGun implements IGunCraft, IBulletProtection
{
	
	protected static final UUID HEAVY = UUID.fromString("845DB27C-C624-495F-8C9F-6020A5A58B6F");

	public ItemFlameThrower(String name) 
	{
		super(name);
		//this.setRegistryName(name).setUnlocalizedName(name).setCreativeTab(Mathsmod.MATHSMOD_GUNS_TAB).setMaxStackSize(1);
	}

	public int cooldown() {
		return 50;
	}

	protected int getCapacity() {
		return 160;
	}

	protected int lockedReach()
	{
		return 10;
	}
	
	protected int airblastCost()
	{
		return 25;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
        return (double)(this.getCapacity() - this.getC(stack)) / (double)this.getCapacity();
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }
	
	public int getRGBDurabilityForDisplay(ItemStack stack)
    {
        return new Color(255, 255, 255).getRGB();
    }
	
	protected List<AxisAlignedBB> getBoundingBoxes(Entity entityIn, World world)
	{
		List<AxisAlignedBB> list = Lists.newArrayList();
		
		double size = 0.2D;
		Vec3d vec = UtilsVoids.laser(0.3, entityIn.rotationYaw, entityIn.rotationPitch).addVector(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
		AxisAlignedBB box1 = new AxisAlignedBB(vec.x - size, vec.y - size, vec.z - size, vec.x + size, vec.y + size, vec.z + size);
		list.add(box1);
		
		size = 0.5D;
		vec = UtilsVoids.laser(1, entityIn.rotationYaw, entityIn.rotationPitch).addVector(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
		AxisAlignedBB box2 = new AxisAlignedBB(vec.x - size, vec.y - size, vec.z - size, vec.x + size, vec.y + size, vec.z + size);
		list.add(box2);
		
		size = 0.8D;
		vec = UtilsVoids.laser(2.3D, entityIn.rotationYaw, entityIn.rotationPitch).addVector(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
		AxisAlignedBB box3 = new AxisAlignedBB(vec.x - size, vec.y - size, vec.z - size, vec.x + size, vec.y + size, vec.z + size);
		list.add(box3);
		
		size = 1.1D;
		vec = UtilsVoids.laser(4.2D, entityIn.rotationYaw, entityIn.rotationPitch).addVector(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
		AxisAlignedBB box4 = new AxisAlignedBB(vec.x - size, vec.y - size, vec.z - size, vec.x + size, vec.y + size, vec.z + size);
		list.add(box4);
		
		size = 1.3D;
		vec = UtilsVoids.laser(6.6D, entityIn.rotationYaw, entityIn.rotationPitch).addVector(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
		AxisAlignedBB box5 = new AxisAlignedBB(vec.x - size, vec.y - size, vec.z - size, vec.x + size, vec.y + size, vec.z + size);
		list.add(box5);
		
		size = 1.5D;
		vec = UtilsVoids.laser(8.5D, entityIn.rotationYaw, entityIn.rotationPitch).addVector(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
		AxisAlignedBB box6 = new AxisAlignedBB(vec.x - size, vec.y - size, vec.z - size, vec.x + size, vec.y + size, vec.z + size);
		list.add(box6);
		
		return list;
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    	
    	if(entityIn instanceof EntityPlayer)
    	{
        	if(isSelected)
        	{
        		//((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE));
        	}
        	else
        	{
        		this.setUsedB(stack, false);
        	}
    	}
    }
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack stack = playerIn.getHeldItem(handIn);
    	if (!worldIn.isRemote && handIn == EnumHand.MAIN_HAND && playerIn.isSneaking())
        {
    		playerIn.getCooldownTracker().setCooldown(this, this.cooldown());
    		this.setC(stack, this.getCapacity());
        }
    	else if(handIn == EnumHand.MAIN_HAND && this.airblastCost() >= 0 && this.getC(stack) >= this.airblastCost())
    	{
    		playerIn.getCooldownTracker().setCooldown(this, 10);
    		this.setC(stack, this.getC(stack) - this.airblastCost());
    		
    		for(Entity entity : UtilsVoids.getEntities(worldIn, playerIn, this.lockedReach() * 0.6F))
    		{
    			if(this.hitDetection(entity, worldIn, stack, playerIn))
    			{
    				if(entity instanceof EntityRocket)
    				{
    					entity.rotationPitch = playerIn.rotationPitch;
    					entity.rotationYaw = playerIn.rotationYaw;
    					((EntityRocket) entity).multiplyDamagesAndSpeed(1.5F, 1.1F);
    				}
    				else if(entity instanceof EntityGrenade)
    				{
    					((EntityGrenade) entity).multiplyDamagesAndPower(1.5F, 1.00F);
    				}
    				else if(entity instanceof EntityLivingBase)
    				{
    					if(((EntityLivingBase) entity).isPotionActive(RegisterPotions.CHARGE))
    					{
    						((EntityLivingBase) entity).removeActivePotionEffect(RegisterPotions.CHARGE);
    					}
    					
    					UtilsVoids.EasyShootEntityModifyY(entity, playerIn.rotationPitch, playerIn.rotationYaw, 2F, 2);
    					
    					if(entity instanceof EntityPlayerMP)
    					{
    						Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
    					}
    				}
    			}
    		}
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }
	
	@SideOnly(Side.CLIENT)
	public void onClientLeftClick(World world, EntityPlayer player, int ticks)
	{
		if(ticks > 0)
		{
			if(this.getC(player.getHeldItemMainhand()) > 0 && !player.getCooldownTracker().hasCooldown(this))
			{
				for(int i = 0; i < 10; ++i)
        		{
        			Random rand = new Random();
                	float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)3.0F);
                	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)3.0F);
                	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)3.0F);
                	
        			Vec3d vec = UtilsVoids.laserWithInnacuracy(1F, player.rotationYaw, player.rotationPitch, x, y, z);
        			ClientSideEnt cse = new ClientSideEnt(player.getPositionEyes(1.0F).add(vec.scale(0.4)).addVector(0, -0.7, 0), vec, vec.scale(1F), player.getName(), 0, null, player.rotationPitch, player.rotationYaw, 10, player.dimension);
            		cse.type = 1;
            		cse.damages = 1;
        			client_Proxy.CSEL.add(cse);
        		}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
    public boolean Animated(ItemStack stack, EntityPlayer player)
    {
    	return true;
    }
    		
	
	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		if(ticks > 0)
		{
			if(this.getC(playerIn.getHeldItemMainhand()) > 0 && !playerIn.getCooldownTracker().hasCooldown(this))
			{
				if(!playerIn.capabilities.isCreativeMode)
				{
					this.removeC(playerIn.getHeldItemMainhand());
				}
				
				worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.AMBIENT, 1, 1);
				
				Mathsmod.network.sendToAllAround(new MessageAddsCSE((EntityPlayer) playerIn, 1F, 10, 3.0F, 1, -0.4F), new TargetPoint(playerIn.dimension, playerIn.posX, playerIn.posY, playerIn.posZ, 40));
				
				this.setUsedB(playerIn.getHeldItemMainhand(), true);
			}
			else if(!playerIn.getCooldownTracker().hasCooldown(this))
			{
				this.setUsedB(playerIn.getHeldItemMainhand(), false);
				playerIn.getCooldownTracker().setCooldown(this, this.cooldown());
				this.setC(playerIn.getHeldItemMainhand(), this.getCapacity());
			}
			else
			{
				this.setUsedB(playerIn.getHeldItemMainhand(), false);
			}
		}
		else
		{
			this.setUsedB(playerIn.getHeldItemMainhand(), false);
		}
    }
	
	public void removeC(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        int i = stack.getTagCompound().getInteger("capacity") - 1;
        if(i < 0)
        {
        	stack.getTagCompound().setInteger("capacity", getCapacity());
        }
        else
        {
        	stack.getTagCompound().setInteger("capacity", i);
        }
    }
	
	public void setC(ItemStack stack, int c) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("capacity", c);

    }
	
    public int getC(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return this.getCapacity();
        }

        if (!stack.getTagCompound().hasKey("capacity")) {

        	return this.getCapacity();
        }

        return stack.getTagCompound().getInteger("capacity");
    }
    
    public void setUsedB(ItemStack stack, boolean used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("used", used);

    }
	
    public boolean getUsedB(ItemStack stack) 
    {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("used")) {

        	return false;
        }

        return stack.getTagCompound().getBoolean("used");
    }

	@Override
	public NonNullList<ItemStack> Craft() 
	{
		NonNullList<ItemStack> list = NonNullList.withSize(8, ItemStack.EMPTY);
		
		list.set(0, new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:silver_ingot"))));
		list.set(1, new ItemStack(Items.LAVA_BUCKET));
		list.set(2, new ItemStack(RegisterBlocks.TFOSIUM_BLOCK));
		list.set(3, new ItemStack(RegisterBlocks.TFOSIUM_BLOCK));
		list.set(5, new ItemStack(Items.FLINT_AND_STEEL));
		
		return list;
	}
	
	protected boolean hitDetection(Entity entity, World world, ItemStack stack, EntityPlayer player)
	{
		for(AxisAlignedBB boxes : this.getBoundingBoxes(player, world))
		{
			if(world.getEntitiesInAABBexcluding(player, boxes, EntitySelectors.getTeamCollisionPredicate(player)).contains(entity))
			{
				return true;
			}
		}
		return false;
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

	protected void damageEntity(Entity entity, World worldIn, ItemStack itemStack, EntityPlayer playerIn)
	{
		entity.setFire(10);
		if(entity instanceof EntityLivingBase)
		{
			UtilsVoids.attackEntityFrom((EntityLivingBase)entity, MDamageSource.causeThrownDamage(playerIn).setFlameThrower(), 0.35F);
		}
		else
		{
			entity.attackEntityFrom(MDamageSource.causeThrownDamage(playerIn), 0.35F);
		}
	}

	@Override
	public float getBulletMultiplier() 
	{
		return 1.3F;
	}

	@Override
	public float zoomOnUsed()
	{
		return 1;
	}

	@Override
	public int getMaxReloadTime()
	{
		return 0;
	}

	@Override
	public int getChargerSize() 
	{
		return 0;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 0;
	}

	@Override
	protected int maxInnacuracy() {
		return 0;
	}

	@Override
	protected float removeInnacuracyNumber() {
		return 0;
	}

	@Override
	public float getBulletDrop() {
		return 0;
	}

	@Override
	public float getPower() {
		return 0;
	}

	@Override
	public float getDamagesCaused()
	{
		return 0.5F;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity)
	{
		return 0;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity player, ItemStack stack, float partialTicks) {
		return null;
	}

	@Override
	public ThreeFloat leftArmRotation(Entity player, ItemStack stack, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) {
		return null;
	}

	@Override
	public int timeToUse() 
	{
		return 8;
	}

	@Override
	public boolean autoMod() {
		return false;
	}

	@Override
	public boolean scopeToShoot() {
		return false;
	}

	@Override
	public boolean lowBulletFar() {
		return false;
	}

	@Override
	public int getReach() {
		return 0;
	}

	@Override
	public EnumBulletType getBullet()
	{
		return EnumBulletType.NONE;
	}

	public void damageEntity(Entity entityByID, ItemStack heldItemMainhand, ItemFlameThrower item,
			EntityPlayer player) 
	{
		if(entityByID instanceof EntityLivingBase)
		{
			entityByID.setFire(3);
			UtilsVoids.attackEntityFrom((EntityLivingBase) entityByID, MDamageSource.FLAME_THROWER, 0.024F * (this.getEhanced(heldItemMainhand) ? 1.5F : 1 ));
		}
	}
}
