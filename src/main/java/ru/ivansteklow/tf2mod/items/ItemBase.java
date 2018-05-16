package ru.ivansteklow.tf2mod.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBase extends Item {

	private boolean hasTooltip = true;
	private String name;
	private EnumRarity quality;

	public ItemBase(String name, CreativeTabs tab, EnumRarity quality, boolean hasTooltip) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		this.name = name;
		this.hasTooltip = hasTooltip;
		this.quality = quality;
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(I18n.format("tooltip.quality") + ": " + this.quality.rarityColor + TextFormatting.BOLD + this.quality.rarityName);
		if (hasTooltip) {
			tooltip.add(I18n.format("tooltip.items." + name));
		}
		else
			super.addInformation(stack, player, tooltip, advanced);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if(quality != null)
			return quality;
		else return super.getRarity(stack);
	}

}
