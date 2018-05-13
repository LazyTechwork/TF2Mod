package ru.ivansteklow.tf2mod.init;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class QualityList {

	public static final EnumRarity QUALITY_NORMAL = EnumHelper.addRarity("QUALITY_NORMAL", TextFormatting.DARK_GRAY, "Normal");
	public static final EnumRarity QUALITY_UNIQUE = EnumHelper.addRarity("QUALITY_UNIQUE", TextFormatting.YELLOW, "Unique");
	public static final EnumRarity QUALITY_VINTAGE = EnumHelper.addRarity("QUALITY_VINTAGE", TextFormatting.BLUE, "Vintage");
	public static final EnumRarity QUALITY_GENUINE = EnumHelper.addRarity("QUALITY_GENUINE", TextFormatting.DARK_GREEN, "Genuine");
	public static final EnumRarity QUALITY_STRANGE = EnumHelper.addRarity("QUALITY_STRANGE", TextFormatting.GOLD, "Strange");
	public static final EnumRarity QUALITY_UNUSUAL = EnumHelper.addRarity("QUALITY_UNUSUAL", TextFormatting.LIGHT_PURPLE, "Unusual");
	public static final EnumRarity QUALITY_HAUNTED = EnumHelper.addRarity("QUALITY_HAUNTED", TextFormatting.GRAY, "Haunted");
	public static final EnumRarity QUALITY_COLLECTORS = EnumHelper.addRarity("QUALITY_COLLECTORS", TextFormatting.RED, "Collector's");
	public static final EnumRarity QUALITY_DECORATED = EnumHelper.addRarity("QUALITY_DECORATED", TextFormatting.WHITE, "Decorated");
	public static final EnumRarity QUALITY_COMMUNITY = EnumHelper.addRarity("QUALITY_COMMUNITY", TextFormatting.GREEN, "Community");
	public static final EnumRarity QUALITY_SELFMADE = EnumHelper.addRarity("QUALITY_SELFMADE", TextFormatting.GREEN, "Self-Made");
	public static final EnumRarity QUALITY_VALVE = EnumHelper.addRarity("QUALITY_VALVE", TextFormatting.DARK_PURPLE, "Valve");
	
}
