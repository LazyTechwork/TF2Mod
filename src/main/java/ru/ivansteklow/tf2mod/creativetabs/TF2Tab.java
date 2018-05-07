package ru.ivansteklow.tf2mod.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TF2Tab extends CreativeTabs{

	public TF2Tab() {
		super("tf2tab");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.BED);
	}
	
}
