package com.mod.mathsmod.add.messages;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateUserSpells implements IMessage
{
	
	String name1;
	String name2;
	String name3;
	String name4;
	
	public MessageUpdateUserSpells()
	{
	}
	
	public MessageUpdateUserSpells(String spellName1, String spellName2, String spellName3, String spellName4)
	{
		name1 = spellName1;
		name2 = spellName2;
		name3 = spellName3;
		name4 = spellName4;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		name1 = ByteBufUtils.readUTF8String(buf);
		name2 = ByteBufUtils.readUTF8String(buf);
		name3 = ByteBufUtils.readUTF8String(buf);
		name4 = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, name1);
		ByteBufUtils.writeUTF8String(buf, name2);
		ByteBufUtils.writeUTF8String(buf, name3);
		ByteBufUtils.writeUTF8String(buf, name4);
	}
	
	public static class SpellSetHandler implements IMessageHandler <MessageUpdateUserSpells, IMessage>
	{
        @Override
        public IMessage onMessage(MessageUpdateUserSpells msg, MessageContext ctx) 
        {
        	List list = Lists.newArrayList();
        	if(SpellRegistry.REGISTRY.containsKey(msg.name1))
        	{
            	SpellBase spell = SpellRegistry.REGISTRY.get(msg.name1);
            	list.add(spell);
        	}
        	
        	if(SpellRegistry.REGISTRY.containsKey(msg.name2))
        	{
            	SpellBase spell = SpellRegistry.REGISTRY.get(msg.name2);
            	list.add(spell);
        	}
        	
        	if(SpellRegistry.REGISTRY.containsKey(msg.name3))
        	{
            	SpellBase spell = SpellRegistry.REGISTRY.get(msg.name3);
            	list.add(spell);
        	}
        	
        	if(SpellRegistry.REGISTRY.containsKey(msg.name4))
        	{
            	SpellBase spell = SpellRegistry.REGISTRY.get(msg.name4);
            	list.add(spell);
        	}
        	
        	client_Proxy.userSpells = list;
            return null;
        }
    }
}