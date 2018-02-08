package com.heitor57.hunterxhunter;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientMessage implements IMessage {

	public ClientMessage() { 
		
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		//text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		//ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class Handler implements IMessageHandler<ClientMessage, IMessage> {
		@Override
        public IMessage onMessage(ClientMessage message, MessageContext ctx) {
            IThreadListener mainThread = Minecraft.getMinecraft(); // or Minecraft.getMinecraft() on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                	Minecraft.getMinecraft().player.getAttributeMap().registerAttribute(PlayerEventHandler.MAX_AURA);
                	Minecraft.getMinecraft().player.getAttributeMap().registerAttribute(PlayerEventHandler.STRENGTH);
                }
            });
            return null; // no response in this case
        }
		
		
	}
}

