package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.TwoIntAndString;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateClientSpell implements IMessage
{
	
	String name;
	int progress;
	int phase;
	boolean cooldownPrevious;
	
	public MessageUpdateClientSpell()
	{
		name = "null";
	}
	
	public MessageUpdateClientSpell(String spellName, int progressIn, int phaseIn, boolean cooldownPreviousIn)
	{
		name = spellName;
		progress = progressIn;
		phase = phaseIn;
		cooldownPrevious = cooldownPreviousIn;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		progress = buf.readInt();
		phase = buf.readInt();
		cooldownPrevious = buf.readBoolean();
		name = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(progress);
		buf.writeInt(phase);
		buf.writeBoolean(cooldownPrevious);
		ByteBufUtils.writeUTF8String(buf, name);
	}
	
	public static class SpellSetHandler implements IMessageHandler <MessageUpdateClientSpell, IMessage>
	{
        @Override
        public IMessage onMessage(MessageUpdateClientSpell msg, MessageContext ctx) 
        {
        	if(msg.name.equals("null") && msg.cooldownPrevious && client_Proxy.spellUsed != null && SpellRegistry.REGISTRY.containsKey(client_Proxy.spellUsed))
        	{
        		client_Proxy.spellCooldowns.put(client_Proxy.spellUsed, SpellRegistry.REGISTRY.get(client_Proxy.spellUsed).spellCooldown(null));
        		client_Proxy.spellCooldown = SpellRegistry.REGISTRY.get(client_Proxy.spellUsed).cooldown(null);
        	}
        	client_Proxy.spellUsed = msg.name.equals("null") ? null : msg.name;
        	client_Proxy.spellPhase = msg.phase;
        	client_Proxy.spellProgress = msg.progress;
            return null;
        }
    }
}