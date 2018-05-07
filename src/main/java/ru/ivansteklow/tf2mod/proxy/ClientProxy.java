package ru.ivansteklow.tf2mod.proxy;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy {

	public static Logger logger;

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void Init(FMLInitializationEvent e) {
		super.Init(e);
		// NetworkRegistry.INSTANCE.registerGuiHandler(ModCore.instance, new GuiHandler());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	@Override
	public void registerModelBakeryVariants() {
		/*ModelBakery.registerItemVariants(ModItems.itemChip, new ResourceLocation(Refs.MOD_ID, "chip_basic"),
				new ResourceLocation(Refs.MOD_ID, "chip_advanced"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blockBreaker),
				new ResourceLocation(Refs.MOD_ID, "blockBreaker_basic"),
				new ResourceLocation(Refs.MOD_ID, "blockBreaker_advanced"));*/
	}

}
