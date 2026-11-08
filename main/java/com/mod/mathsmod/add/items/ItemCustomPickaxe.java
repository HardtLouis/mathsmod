package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.toolparts.EnumParts;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCustomPickaxe extends ItemPickaxe implements IItemSpecialRenderer
{

	public ItemCustomPickaxe()
	{
		super(Item.ToolMaterial.WOOD);
		this.setRegistryName("custom_pickaxe").setUnlocalizedName("custom_pickaxe");
	}
	
	public int getMaxDamage(ItemStack stack)
    {
    	int total = 0;
    	NonNullList<ItemStack> list = this.getComponents(stack);
    	
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		
    		if(item1.part() == EnumParts.PICKAXE && item2.part() == EnumParts.HANDLE_TOOL)
    		{
    			total += item1.getMetal(list.get(0)).durability;
        		total += item2.getMetal(list.get(1)).durability;
    		}
    	}
        return total / 2;
    }
	
	public static NonNullList<ItemStack> getComponents(ItemStack stack)
    {
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
        	NonNullList<ItemStack> l = NonNullList.withSize(2, ItemStack.EMPTY);
        	return l;
    	}
    	else
    	{
        	NonNullList<ItemStack> l = NonNullList.withSize(2, ItemStack.EMPTY);
    		ItemStackHelper.loadAllItems(stack.getTagCompound(), l);
        	return l;
    	}
    }
    
    public static void setComponentsItems(ItemStack stack, ItemStack pick, ItemStack handle)
    {
    	NonNullList<ItemStack> l = NonNullList.withSize(2, ItemStack.EMPTY);
    	l.set(0, pick);
    	l.set(1, handle);
    	if(!stack.hasTagCompound())
    	{
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	ItemStackHelper.saveAllItems(stack.getTagCompound(), l);
    }
	
    @Override
    public boolean canHarvestBlock(IBlockState blockIn, ItemStack stack)
    {
        Block block = blockIn.getBlock();
        ItemStack itemstack = this.getComponents(stack).get(0);
        
        if(itemstack != null && itemstack.getItem() instanceof ItemToolPart)
        {
        	ItemToolPart item = (ItemToolPart) itemstack.getItem();
        	if(ItemToolPart.getMetal(itemstack) != null)
        	{
            	int harvestLvl = item.getMetal(itemstack).level;
	        	if(blockIn.getMaterial() == Material.ROCK || blockIn.getMaterial() == Material.IRON || blockIn.getMaterial() == Material.ROCK)
	        	{
	        		return blockIn.getBlock().getHarvestLevel(blockIn) <= harvestLvl;
	            }
	            else
	            {
	                return blockIn.getBlock().getHarvestLevel(blockIn) <= 1;
	            }
        	}
        }
        return super.canHarvestBlock(blockIn, itemstack);
    }
    
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
    {
        if (toolClass.equals("pickaxe"))
        {
            ItemStack itemstack = this.getComponents(stack).get(0);
        	ItemToolPart item = (ItemToolPart) itemstack.getItem();
        	if(ItemToolPart.getMetal(itemstack) != null)
        	{
            	int harvestLvl = item.getMetal(itemstack).level;
            	return harvestLvl;
        	}
            return this.toolMaterial.getHarvestLevel();
        }
        else
        {
            return -1;
        }
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        ItemStack itemstack = this.getComponents(stack).get(0);
        if(itemstack != null && itemstack.getItem() instanceof ItemToolPart)
        {
        	ItemToolPart item = (ItemToolPart) itemstack.getItem();
        	if(ItemToolPart.getMetal(itemstack) != null)
        	{
        		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : item.efficiencyMultiplier() * ItemToolPart.getMetal(itemstack).efficiency;
        	}
        }
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : 0.4F;
    }
    


    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
        
        float totalDef = 0.0F;
        float att = 0;
        
        NonNullList<ItemStack> list = this.getComponents(stack);
    	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart)
    	{
    		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
    		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
    		att = item1.damageMultiplier() * item2.damageMultiplier() * item1.getMetal(list.get(0)).attack_damages;
    	}
    	
    	if(slot == EntityEquipmentSlot.MAINHAND)
    	{
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", att, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", Double.POSITIVE_INFINITY, 0));
    	}

        return multimap;
    }
    
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }
    
    private BlockPos rotate(EnumFacing facing, BlockPos pos)
    {
        switch (facing)
        {
            case NORTH:
            default:
                return pos;
            case WEST:
                return new BlockPos(-pos.getZ(), pos.getY(), pos.getX());
            case EAST:
                return new BlockPos(-pos.getZ(), pos.getY(), -pos.getX());
            case SOUTH:
                return new BlockPos(-pos.getX(), pos.getY(), -pos.getZ());
            case UP:
            	return new BlockPos(pos.getX(), pos.getZ(), -pos.getY());
            case DOWN:
            	return new BlockPos(pos.getX(), pos.getZ(), pos.getY());
        }
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!worldIn.isRemote && (double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
        	NonNullList<ItemStack> list = this.getComponents(stack);
        	if(list.get(0).getItem() instanceof ItemToolPart && list.get(1).getItem() instanceof ItemToolPart)
        	{
        		ItemToolPart item1 = (ItemToolPart) list.get(0).getItem();
        		ItemToolPart item2 = (ItemToolPart) list.get(1).getItem();
        		item1.onBlockDestroyed(entityLiving, pos, worldIn, list.get(0), 1, 0);
        		item2.onBlockDestroyed(entityLiving, pos, worldIn, list.get(1), 1, 1);
        		
        		int ray = ItemToolPart.getMetal(list.get(0)).level > 5 ? 2 : 1;
        		
        		if(item1 == RegisterItems.HAMMER_PART)
        		{
        			for(int i = -ray; i <= ray; ++i)
                	{
                		for(int j = -ray; j <= ray; ++j)
                    	{
                			EnumFacing enumfacing = EnumFacing.getDirectionFromEntityLiving(pos, entityLiving);
                			BlockPos bPos = this.rotate(enumfacing, new BlockPos(i, j, 0)).add(pos);
                			if(worldIn.getBlockState(bPos).getBlock().getBlockHardness(worldIn.getBlockState(bPos), worldIn, bPos) >= 0)
            				{
                				if(entityLiving instanceof EntityPlayer)
                				{
                					worldIn.getBlockState(bPos).getBlock().onBlockDestroyedByPlayer(worldIn, bPos, worldIn.getBlockState(bPos));
                    				worldIn.getBlockState(bPos).getBlock().harvestBlock(worldIn, (EntityPlayer) entityLiving, bPos, worldIn.getBlockState(bPos), worldIn.getTileEntity(bPos), stack);
                					worldIn.destroyBlock(bPos, false);
                				}
                				else
                    			{
                    				worldIn.destroyBlock(bPos, true);
                    			}
                			}
                			
                    	}
                	}
        		}
        	}
            stack.damageItem(1, entityLiving);
        }

        return true;
    }

	@Override
	public void render(ItemStack stack, EntityPlayer entity, float partialTick) 
	{
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		for(ItemStack stack2 : this.getComponents(stack))
		{
			Minecraft.getMinecraft().getRenderItem().renderItem(stack2, TransformType.NONE);
		}
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		NonNullList<ItemStack> l = this.getComponents(stack);
		for(int i = 0; i < 2; ++i)
		{
			if(l.get(i).getItem() instanceof ItemToolPart)
			{
				ItemToolPart tp = (ItemToolPart) l.get(i).getItem();
				String part = "Pickaxe part";
				if(i == 1)
				{
					part = "handle";
				}
				tooltip.add(part + " : " + tp.getMetalString(l.get(i)).replace("%", " + "));
			}
		}
    }

}
