package ru.ivansteklow.tf2mod.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import ru.ivansteklow.isdev.handlers.ItemHandler;
import ru.ivansteklow.tf2mod.items.ItemBase;

public class ItemList {

	public static Item mannco_key = new ItemBase("mannco_key", References.CREATIVE_TAB, QualityList.QUALITY_UNIQUE, true),
			scrap_metal = new ItemBase("scrap_metal", References.CREATIVE_TAB, QualityList.QUALITY_NORMAL, false),
			reclaimed_metal = new ItemBase("reclaimed_metal", References.CREATIVE_TAB, QualityList.QUALITY_NORMAL, false),
			refined_metal = new ItemBase("refined_metal", References.CREATIVE_TAB, QualityList.QUALITY_NORMAL, false);

	@Mod.EventBusSubscriber(modid = References.MODID)
	public static class RegistrationHandler {

		final static Item[] items = { mannco_key, scrap_metal, reclaimed_metal, refined_metal };

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			for (final Item item : items) {
				registry.register(item);
				ItemHandler.regItemRender(item, References.MODID);
			}
		}
	}
}
