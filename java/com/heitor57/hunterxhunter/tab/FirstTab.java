package com.heitor57.hunterxhunter.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FirstTab extends CreativeTabs{

	public FirstTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.EMERALD);
	}
	
}
