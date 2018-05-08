package ru.ivansteklow.tf2mod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockBase extends Block{

	public boolean isEffectsTurnedOff = false;
	private boolean hasTooltip = true;
	public String name;
	
	public BlockBase(Material materialIn, String name, CreativeTabs tab, String tool, int toolLevel, float hardness, boolean hasTooltip) {
		super(materialIn);
		setHardness(hardness);
		setHarvestLevel(tool, toolLevel);
		setCreativeTab(tab);
		setName(name);
		this.hasTooltip = hasTooltip;
		this.name = name;
	}
	
	public BlockBase(Material materialIn, String name, CreativeTabs tab, float hardness, boolean hasTooltip) {
		super(materialIn);
		setHardness(hardness);
		setCreativeTab(tab);
		setName(name);
		this.hasTooltip = hasTooltip;
		this.name = name;
	}
	
	public BlockBase(Material materialIn, String name, CreativeTabs tab, String tool, int toolLevel, float hardness, boolean isEffectsTurnedOff, boolean hasTooltip) {
		super(materialIn);
		setHardness(hardness);
		setHarvestLevel(tool, toolLevel);
		setCreativeTab(tab);
		setName(name);
		this.isEffectsTurnedOff = isEffectsTurnedOff;
		this.hasTooltip = hasTooltip;
		this.name = name;
	}
	
	public BlockBase(Material materialIn, String name, CreativeTabs tab, float hardness, boolean isEffectsTurnedOff, boolean hasTooltip) {
		super(materialIn);
		setHardness(hardness);
		setCreativeTab(tab);
		setName(name);
		this.isEffectsTurnedOff = isEffectsTurnedOff;
		this.hasTooltip = hasTooltip;
		this.name = name;
	}
	
	public void setName(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	@Override
	public boolean addLandingEffects(IBlockState state, WorldServer worldObj, BlockPos blockPosition,
			IBlockState iblockstate, EntityLivingBase entity, int numberOfParticles) {
		return this.isEffectsTurnedOff;
	}
	
	@Override
	public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
		return this.isEffectsTurnedOff;
	}
	
	@Override
	public boolean addRunningEffects(IBlockState state, World world, BlockPos pos, Entity entity) {
		return this.isEffectsTurnedOff;
	}
	
	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		return this.isEffectsTurnedOff;
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		if(hasTooltip)
			tooltip.add(I18n.format("tooltip.blocks." + name));
		else
			super.addInformation(stack, player, tooltip, advanced);
	}

}
