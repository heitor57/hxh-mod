package com.heitor57.hunterxhunter.items;

import java.util.HashSet;
import java.util.Set;

import com.heitor57.hunterxhunter.Reference;
import com.heitor57.hunterxhunter.hxh;
import com.heitor57.hunterxhunter.tab.FirstTab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MOD_ID)
public class ModItems {
	// items
	public static final ItemEntityKiller ENTITY_KILLER = null;
	public static final ItemHunterSword HUNTER_SWORD = null;
	
	
	// Handler
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandler {
		public static final Set<Item> ITEMS = new HashSet<>();
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			
			final Item[] items = {
					new ItemEntityKiller(),
					new ItemHunterSword(Item.ToolMaterial.GOLD, "hunter_sword")
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);
				ITEMS.add(item);
			}
			
		}
		@SubscribeEvent
		public static void modelregister(ModelRegistryEvent event) {
			for (final Item item : ITEMS) {
				ModelLoader.setCustomModelResourceLocation(
						item,
						0,
						new ModelResourceLocation(item.getRegistryName(), "hunterxhunter")
						);
			}
			
		}
	}
	
}


