package ru.ivansteklow.tf2mod.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import ru.ivansteklow.isdev.handlers.ItemHandler;
import ru.ivansteklow.tf2mod.items.ItemKey;

public class ItemList {

	public static Item mannco_key = new ItemKey("mannco_key", References.CREATIVE_TAB);

	@Mod.EventBusSubscriber(modid = References.MODID)
	public static class RegistrationHandler {

		final static Item[] items = { mannco_key };

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
