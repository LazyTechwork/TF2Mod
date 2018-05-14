package ru.ivansteklow.tf2mod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.ivansteklow.tf2mod.init.References;

public class ModNetworkWrapper {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);

	public static void regMessages() {
		INSTANCE.registerMessage(PacketMetalRefinery.PacketMetalRefineryHandler.class, PacketMetalRefinery.class, 0,
				Side.SERVER);
	}

}
