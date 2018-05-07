package ru.ivansteklow.tf2mod.init;

import net.minecraft.creativetab.CreativeTabs;
import ru.ivansteklow.tf2mod.creativetabs.TF2Tab;

public class References {

	public static final String MODID = "tf2mod";
	public static final String NAME = "Team Fortress 2 Mod";
	public static final String VERSION = "1.0.0";
	public static final String DESCRIPTION = "That mod adds TF2 items to Minecraft World";
	public static final String CREDITS = "Thank you, Forge Team, for Creating Modding API";
	public static final String UPDATE_JSON = "https://raw.githubusercontent.com/IvanSteklow/TF2Mod/master/updateCheck.json";

	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String MOD_DEPENDENCIES = "required-after:forge@[14.23.3.2655,);"
			+ "required-after:isdev@[2.0.0,);";

	public static final String CLIENT_PROXY_CLASS = "ru.ivansteklow.tf2mod.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "ru.ivansteklow.tf2mod.proxy.ServerProxy";

	public static final CreativeTabs CREATIVE_TAB = new TF2Tab();

}
