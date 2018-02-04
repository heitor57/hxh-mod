package com.heitor57.hunterxhunter;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

class MyMessage implements IMessage {
	
	private String text;
	
	public MyMessage() { }
	
	public MyMessage(String text) {
		this.text = text;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class Handler implements IMessageHandler<MyMessage, IMessage> {
		@Override
        public IMessage onMessage(MyMessage message, MessageContext ctx) {
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world; // or Minecraft.getMinecraft() on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().player.getDisplayName()));
                }
            });
            return null; // no response in this case
        }
		
		
	}
}
