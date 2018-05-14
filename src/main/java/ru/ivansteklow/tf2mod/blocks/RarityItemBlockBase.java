package ru.ivansteklow.tf2mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RarityItemBlockBase extends ItemBlock{
	
	public RarityItemBlockBase(Block block) {
		super(block);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if(this.block instanceof BlockBase)
			if(((BlockBase) this.block).getQuality() != null)
				return ((BlockBase) this.block).getQuality();
		return super.getRarity(stack);
	}

}
