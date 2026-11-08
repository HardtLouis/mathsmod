package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemKurayumJetpack;
import com.mod.mathsmod.add.spell.EnumSpellReaction;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.add.spell.utils.SpellManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SpellMessage implements IMessage
{
	
	String name;
	int progress;
	int phase;
	
	public SpellMessage()
	{
		name = "null";
	}
	
	public SpellMessage(String spellName, int progressIn, int phaseIn)
	{
		if(spellName != null && !spellName.isEmpty())
		{
			name = spellName;
		}
		else
		{
			name = "null";
		}
		progress = progressIn;
		phase = phaseIn;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		progress = buf.readInt();
		phase = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(progress);
		buf.writeInt(phase);
		ByteBufUtils.writeUTF8String(buf, name);
	}
	
	public static class SpellHandler implements IMessageHandler <SpellMessage, IMessage>
	{
        @Override
        public IMessage onMessage(SpellMessage msg, MessageContext ctx) 
        {
        	if(SpellRegistry.REGISTRY.containsKey(msg.name))
        	{
            	SpellBase spell = SpellRegistry.REGISTRY.get(msg.name);
            	
            	if(ctx.getServerHandler().player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemKurayumJetpack)
            	{
            		ItemKurayumJetpack.setUsing(ctx.getServerHandler().player.getItemStackFromSlot(EntityEquipmentSlot.CHEST), false);
            	}
            	
            	if((spell.reaction(msg.phase, msg.progress, ctx.getServerHandler().player) == EnumSpellReaction.CANT || spell.reaction(msg.phase, msg.progress, ctx.getServerHandler().player) == EnumSpellReaction.CAN_SHOOT) && ctx.getServerHandler().player.isPotionActive(RegisterPotions.CHARGE))
            	{
            		ctx.getServerHandler().player.removeActivePotionEffect(RegisterPotions.CHARGE);
            	}
            	
            	if((spell.reaction(msg.phase, msg.progress, ctx.getServerHandler().player) == EnumSpellReaction.CANT || spell.reaction(msg.phase, msg.progress, ctx.getServerHandler().player) == EnumSpellReaction.CAN_SHOOT) && ctx.getServerHandler().player.isPotionActive(RegisterPotions.EXPLOSIVE_MAN))
            	{
            		ctx.getServerHandler().player.removeActivePotionEffect(RegisterPotions.EXPLOSIVE_MAN);
            	}
            	
            	SpellManager.setActiveSpell(ctx.getServerHandler().player, spell, msg.phase);
            	
            	spell.spellUseCommon(msg.phase, msg.progress, ctx.getServerHandler().player);
            	Mathsmod.network.sendToAll(new MessageUpdateSpellAnims(msg.name, ctx.getServerHandler().player.getName(), msg.progress, msg.phase));
        	}
        	else
        	{
        		SpellManager.setActiveSpell(ctx.getServerHandler().player, null, 0);
        		Mathsmod.network.sendToAll(new MessageUpdateSpellAnims("null", ctx.getServerHandler().player.getName(), msg.progress, msg.phase));
        	}
            return null;
        }
    }
}