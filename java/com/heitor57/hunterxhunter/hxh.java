package com.heitor57.hunterxhunter;



import javax.swing.text.html.parser.Entity;

import org.omg.CORBA.IRObjectOperations;
import com.heitor57.hunterxhunter.proxy.CommonProxy;
import com.heitor57.hunterxhunter.tab.FirstTab;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.monster.*;
import net.minecraftforge.event.RegistryEvent;

@Mod(modid=Reference.MOD_ID,name=Reference.NAME, version= Reference.VERSION,acceptedMinecraftVersions=Reference.ACCEPTED_VERSIONS)
public class hxh {
	@Instance
	public static hxh instance;
	public static SimpleNetworkWrapper network;
	public static FirstTab huntertab;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		network =NetworkRegistry.INSTANCE.newSimpleChannel("meucanal");
		network.registerMessage(MyMessage.Handler.class, MyMessage.class, 0, Side.SERVER);
		
		network.sendToServer(new MyMessage("mensagem criada"));
		
		huntertab = new FirstTab(CreativeTabs.getNextID(),"Hunter x Hunter");
	 	proxy.preInit(event);
	}
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		
		proxy.Init(event);
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		proxy.PostInit(event);
	}

}
