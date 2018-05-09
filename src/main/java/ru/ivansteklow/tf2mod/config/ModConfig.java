package ru.ivansteklow.tf2mod.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.ivansteklow.tf2mod.init.References;

public class ModConfig {
	
	private static Configuration config;

	public static final String CATEGORY_NAME_BLOCKS = "blocks";

	public static int metalRefineryWorkTime;

	public static void preInit() {
		File configFile = new File(Loader.instance().getConfigDir(), "TeamFortress2.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void clientPreInit() {
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}

	public static void syncFromFiles() {
		syncConfig(true, true);
	}

	public static void syncFromGui() {
		syncConfig(false, true);
	}

	public static void syncFromFields() {
		syncConfig(false, false);
	}

	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
		if (loadFromConfigFile)
			config.load();

		Property propertyMetalRefineryWorkTime = config.get(CATEGORY_NAME_BLOCKS, "metal_refinery_worktime", 100);
		propertyMetalRefineryWorkTime.setLanguageKey("gui.config.blocks.metal_refinery_worktime.name");
		propertyMetalRefineryWorkTime.setComment(I18n.format("gui.config.blocks.metal_refinery_worktime.comment"));
		propertyMetalRefineryWorkTime.setMinValue(50);
		propertyMetalRefineryWorkTime.setMaxValue(500);

		List<String> propertyOrderBlocks = new ArrayList<String>();
		propertyOrderBlocks.add(propertyMetalRefineryWorkTime.getName());
		config.setCategoryPropertyOrder(CATEGORY_NAME_BLOCKS, propertyOrderBlocks);

		if (readFieldsFromConfig) {
			metalRefineryWorkTime = propertyMetalRefineryWorkTime.getInt();
		}

		propertyMetalRefineryWorkTime.set(metalRefineryWorkTime);

		if (config.hasChanged())
			config.save();
	}

	public static class ConfigEventHandler {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(References.MODID)) {
				syncFromGui();
			}
		}

}
	
}
