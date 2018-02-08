package com.heitor57.hunterxhunter.items;

import com.google.common.base.Objects;
import com.heitor57.hunterxhunter.Reference;
import com.heitor57.hunterxhunter.hxh;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ModItemBase extends Item {
	public ModItemBase(final String itemName) {
		setItemName(this, itemName);
		setCreativeTab(hxh.huntertab);
	}
	public static void setItemName(final Item item, final String itemName) {
		item.setRegistryName(Reference.MOD_ID, itemName);
		final ResourceLocation registryName = item.getRegistryName();
		item.setUnlocalizedName(item.getRegistryName().toString());
	}
}