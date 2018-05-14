package ru.ivansteklow.tf2mod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import ru.ivansteklow.tf2mod.init.References;

public class BlockBase extends Block {

	public boolean hasParticles = false;
	private boolean hasTooltip = true;
	public String name;
	private EnumRarity quality;

	public BlockBase(Material materialIn, String name, EnumRarity quality, float hardness, boolean hasTooltip) {
		super(materialIn);
		setHardness(hardness);
		setCreativeTab(References.CREATIVE_TAB);
		setName(name);
		this.hasTooltip = hasTooltip;
		this.name = name;
		this.quality = quality;
	}

	public BlockBase(Material materialIn, String name, EnumRarity quality, float hardness, boolean hasParticles,
			boolean hasTooltip) {
		this(materialIn, name, quality, hardness, hasTooltip);
		this.hasParticles = hasParticles;
	}

	public void setName(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@Override
	public boolean addLandingEffects(IBlockState state, WorldServer worldObj, BlockPos blockPosition,
			IBlockState iblockstate, EntityLivingBase entity, int numberOfParticles) {
		return this.hasParticles;
	}

	@Override
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
		return this.hasParticles;
	}

	@Override
	public boolean addRunningEffects(IBlockState state, World world, BlockPos pos, Entity entity) {
		return this.hasParticles;
	}

	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		return this.hasParticles;
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		if (hasTooltip) {
			tooltip.add(I18n.format("tooltip.quality") + ": " + this.quality.rarityColor + TextFormatting.BOLD + this.quality.rarityName);
			tooltip.add(I18n.format("tooltip.blocks." + name));
		}
		else
			super.addInformation(stack, player, tooltip, advanced);
	}
	
	public EnumRarity getQuality() {
		return quality;
	}

}
