package ru.ivansteklow.tf2mod.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.ivansteklow.tf2mod.Core;
import ru.ivansteklow.tf2mod.init.References;
import ru.ivansteklow.tf2mod.tileentities.MetalRefineryTileEntity;

public class BlockMetalRefinery extends BlockBase implements ITileEntityProvider {

	public static final int GUI_ID = 1;

	public BlockMetalRefinery() {
		super(Material.ANVIL, "metal_refinery", References.CREATIVE_TAB, "pickaxe", 2, 8F, true);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new MetalRefineryTileEntity();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		}
		TileEntity te = worldIn.getTileEntity(pos);
		if (!(te instanceof MetalRefineryTileEntity)) {
			return false;
		}
		playerIn.openGui(Core.instance, GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 500F, true);
		return true;
	}

}
