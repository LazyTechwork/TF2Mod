package ru.ivansteklow.tf2mod.init;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import ru.ivansteklow.isdev.handlers.BlockHandler;
import ru.ivansteklow.tf2mod.blocks.BlockCrate;
import ru.ivansteklow.tf2mod.blocks.BlockPickup;
import ru.ivansteklow.tf2mod.enums.PickupTypeEnum;

public class BlockList {

	public static Block
			mannco_case = new BlockCrate("mannco_case", ImmutableList.copyOf(Item.REGISTRY)),
			medkit = new BlockPickup("medkit", PickupTypeEnum.HEALTH, 4F),
			foodkit = new BlockPickup("foodkit", PickupTypeEnum.FOOD, 2F);

	@Mod.EventBusSubscriber(modid = References.MODID)
	public static class RegistrationHandler {

		final static Block[] BLOCKS = { mannco_case, medkit, foodkit };

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			registry.registerAll(BLOCKS);
		}

		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			for (final Block block : BLOCKS) {
				final ItemBlock item = new ItemBlock(block);
				final ResourceLocation registryName = block.getRegistryName();
				registry.register(item.setRegistryName(registryName));
				BlockHandler.regItemBlockRender(block, References.MODID);
			}
		}
	}

}
