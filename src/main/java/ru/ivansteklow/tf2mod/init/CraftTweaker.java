package ru.ivansteklow.tf2mod.init;

public class CraftTweaker {
	public static void init() {
		registerShapelessRecipes();
		registerShapedRecipes();
		registerFurnaceRecipes();
		registerCustomRecipes();
	}

	public static void registerShapelessRecipes() {
		/*GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN), Items.WATER_BUCKET, Items.WATER_BUCKET,
				Items.LAVA_BUCKET);*/
	}

	public static void registerShapedRecipes() {
		//GameRegistry.addShapedRecipe(new ItemStack(Blocks.CHEST, 4), "XXX", "X X", "XXX", 'X', Blocks.LOG);
	}

	public static void registerFurnaceRecipes() {
		/*GameRegistry.addSmelting(new ItemStack(Blocks.SANDSTONE, 1, 0), new ItemStack(ModBlocks.blockRawSandbrick),
				10.0F);*/
	}

	public static void registerCustomRecipes() {
}
}
