package com.heitor57.hunterxhunter;



import com.heitor57.hunterxhunter.items.ModItems;
import com.heitor57.hunterxhunter.proxy.CommonProxy;
import com.heitor57.hunterxhunter.tab.FirstTab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

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
		//OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
		//ModelLoader.setCustomModelResourceLocation(item, metadata, model);
		network =NetworkRegistry.INSTANCE.newSimpleChannel("channel");
		network.registerMessage(ServerMessage.Handler.class, ServerMessage.class, 0, Side.SERVER);
		network.registerMessage(ClientMessage.Handler.class, ClientMessage.class, 1, Side.CLIENT);
		
		
		
		huntertab = new FirstTab(CreativeTabs.getNextID(),"hunterxhunter");
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
