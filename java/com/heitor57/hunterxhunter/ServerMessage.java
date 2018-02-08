package com.heitor57.hunterxhunter;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ServerMessage implements IMessage {
	
	private Double x,y,z;
	private float size;
	
	public ServerMessage() { }

	public ServerMessage(double x,double y,double z, float size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		//explosion = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects

		x=buf.readDouble();
		y=buf.readDouble();
		z=buf.readDouble();
		size=buf.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeFloat(size);
		
		
		//ByteBufUtils.writeUTF8String(buf, text);
	}
	
	public static class Handler implements IMessageHandler<ServerMessage, IMessage> {
		@Override
        public IMessage onMessage(ServerMessage message, MessageContext ctx) {
            IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world; // or Minecraft.getMinecraft() on the client

            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                	
	                	System.out.println("metodo run mymessage");
	                	Explosion explosion = new Explosion(
	                			ctx.getServerHandler().player.world,
	                			null, 
	                			message.x,
	                			message.y,
	                			message.z,
	                			message.size,
	                			false,
	                			true);
	                	explosion.doExplosionA();
	            		explosion.doExplosionB(true);
                	
                }
            });
            return null; // no response in this case
        }
		
	}
}
