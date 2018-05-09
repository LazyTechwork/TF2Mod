package ru.ivansteklow.tf2mod.recipes;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.ivansteklow.tf2mod.init.ItemList;

public class RefineryRecipes {

	private Map<ItemStack, ItemStack[]> refineRecipesList = Maps.<ItemStack, ItemStack[]>newHashMap();
	private static final RefineryRecipes INSTANCE = new RefineryRecipes();
	
	public static RefineryRecipes instance() {
		return INSTANCE;
	}
	
	public RefineryRecipes() {
		addRecipeToList(new ItemStack(Items.IRON_INGOT, 2), new ItemStack(ItemList.scrap_metal, 1), new ItemStack(Items.IRON_INGOT, 1));
		addRecipeToList(new ItemStack(ItemList.scrap_metal, 2), new ItemStack(ItemList.reclaimed_metal, 1), new ItemStack(ItemList.scrap_metal, 1));
		addRecipeToList(new ItemStack(ItemList.reclaimed_metal, 2), new ItemStack(ItemList.refined_metal, 1), new ItemStack(ItemList.scrap_metal, 1));
	}
	
	public void addRecipeToList(ItemStack input, ItemStack output1, ItemStack output2) {
		ItemStack[] output = { output1, output2 };
		refineRecipesList.put(input, output);
	}
	
	public void addRecipeToList(ItemStack input, ItemStack output) {
		addRecipeToList(input, output, null);
	}
	
	@Nullable
	public ItemStack[] getResult(ItemStack input) {
		return refineRecipesList.get(input);
	}
	
	public boolean isHasRecipe(ItemStack input) {
		return getResult(input)!=null;
	}
	
}
