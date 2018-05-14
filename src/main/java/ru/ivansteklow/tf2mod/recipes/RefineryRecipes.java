package ru.ivansteklow.tf2mod.recipes;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import ru.ivansteklow.tf2mod.init.ItemList;

public class RefineryRecipes {

	public Map<Item, RefineryRecipe> refineRecipesList = Maps.<Item, RefineryRecipe>newHashMap();
	private static final RefineryRecipes INSTANCE = new RefineryRecipes();

	public static RefineryRecipes instance() {
		return INSTANCE;
	}

	public RefineryRecipes() {
		addRecipeToList(new ItemStack(Blocks.IRON_ORE, 1), new ItemStack(Items.IRON_INGOT, 1),
				new ItemStack(Items.IRON_INGOT, 1));
		addRecipeToList(new ItemStack(Items.IRON_INGOT, 1), new ItemStack(ItemList.scrap_metal, 2),
				new ItemStack(Items.IRON_INGOT, 1));
		addRecipeToList(new ItemStack(ItemList.scrap_metal, 3), new ItemStack(ItemList.reclaimed_metal, 1),
				new ItemStack(ItemList.scrap_metal, 1));
		addRecipeToList(new ItemStack(ItemList.reclaimed_metal, 3), new ItemStack(ItemList.refined_metal, 1),
				new ItemStack(ItemList.scrap_metal, 1));
		addRecipeToList(new ItemStack(ItemList.refined_metal, 28), new ItemStack(ItemList.mannco_key, 1),
				new ItemStack(ItemList.scrap_metal, 1));
	}

	public void addRecipeToList(ItemStack input, ItemStack output1, ItemStack output2) {
		refineRecipesList.put(input.getItem(), new RefineryRecipe(input, output1, output2));
	}

	@Nullable
	public RefineryRecipe getResult(ItemStack input) {
		if (refineRecipesList.get(input.getItem()) != null
				&& refineRecipesList.get(input.getItem()).input.getCount() <= input.getCount())
			return refineRecipesList.get((input.getItem()));
		else
			return null;
	}
	
	public NonNullList<RefineryRecipe> getRefineryRecipesList() {
		NonNullList<RefineryRecipe> recipes = NonNullList.create();
		for(Map.Entry<Item, RefineryRecipe> entry : refineRecipesList.entrySet()) {
			recipes.add(entry.getValue());
		}
		return recipes;
	}

	public class RefineryRecipe {

		public ItemStack input, output1, output2;

		public RefineryRecipe(ItemStack input, ItemStack output1, ItemStack output2) {
			this.input = input;
			this.output1 = output1;
			this.output2 = output2;
		}
	}

}
