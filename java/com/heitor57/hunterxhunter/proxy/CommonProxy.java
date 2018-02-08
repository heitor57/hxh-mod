package com.heitor57.hunterxhunter.proxy;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface CommonProxy {
	@EventHandler
	public void preInit(FMLPreInitializationEvent event);
	@EventHandler
	public void Init(FMLInitializationEvent event);
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event);
}
