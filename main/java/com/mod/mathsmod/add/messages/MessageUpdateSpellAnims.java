package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.TwoIntAndString;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateSpellAnims implements IMessage
{
	
	String name;
	String userName;
	int progress;
	int phase;
	
	public MessageUpdateSpellAnims()
	{
		name = "null";
	}
	
	public MessageUpdateSpellAnims(String spellName, String userNameIn, int progressIn, int phaseIn)
	{
		name = spellName;
		userName = userNameIn;
		progress = progressIn;
		phase = phaseIn;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		progress = buf.readInt();
		phase = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
		userName = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(progress);
		buf.writeInt(phase);
		ByteBufUtils.writeUTF8String(buf, name);
		ByteBufUtils.writeUTF8String(buf, userName);
	}
	
	public static class SpellSyncHandler implements IMessageHandler <MessageUpdateSpellAnims, IMessage>
	{
        @Override
        public IMessage onMessage(MessageUpdateSpellAnims msg, MessageContext ctx) 
        {
        	if(SpellRegistry.REGISTRY.containsKey(msg.name))
        	{
            	SpellBase spell = SpellRegistry.REGISTRY.get(msg.name);
            	if(client_Proxy.usedSpells.containsKey(msg.userName))
            	{
            		client_Proxy.usedSpells.replace(msg.userName, new TwoIntAndString(msg.phase, msg.progress, msg.name));
            	}
            	else
            	{
            		client_Proxy.usedSpells.put(msg.userName, new TwoIntAndString(msg.phase, msg.progress, msg.name));
            	}
        	}
        	else
        	{
        		if(client_Proxy.usedSpells.containsKey(msg.userName))
        		{
            		client_Proxy.usedSpells.remove(msg.userName);
        		}
        	}
            return null;
        }
    }
}