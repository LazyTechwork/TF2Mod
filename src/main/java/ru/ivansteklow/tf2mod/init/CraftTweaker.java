package ru.ivansteklow.tf2mod.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;

public class CraftTweaker {
	public static void init() {
		CraftingHelper.register(new ResourceLocation(References.MODID, "shapeless_recipes"), (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
		CraftingHelper.register(new ResourceLocation(References.MODID, "shaped_recipes"), (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
	}
}
