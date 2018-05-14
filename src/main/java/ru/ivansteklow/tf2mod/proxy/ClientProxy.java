package ru.ivansteklow.tf2mod.proxy;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.ivansteklow.tf2mod.init.References;

public class ClientProxy extends ServerProxy {

	public static Logger logger;

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		OBJLoader.INSTANCE.addDomain(References.MODID);
	}

	@Override
	public void Init(FMLInitializationEvent e) {
		super.Init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}
