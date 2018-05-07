package ru.ivansteklow.tf2mod.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.ivansteklow.tf2mod.handlers.EventHandler;

public class ServerProxy {

	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public void Init(FMLInitializationEvent e) {
		//NetworkRegistry.INSTANCE.registerGuiHandler(ModCore.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

	public void registerModelBakeryVariants() {

}
	
}
