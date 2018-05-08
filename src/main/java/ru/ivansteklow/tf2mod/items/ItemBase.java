package ru.ivansteklow.tf2mod.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBase extends Item {

	private boolean hasTooltip = true;
	private String name;
	
	public ItemBase(String name, CreativeTabs tab, boolean hasTooltip) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		this.name = name;
		this.hasTooltip = hasTooltip;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		if(hasTooltip)
			tooltip.add(I18n.format("tooltip.items." + name));
		else
			super.addInformation(stack, player, tooltip, advanced);
	}
	
}
