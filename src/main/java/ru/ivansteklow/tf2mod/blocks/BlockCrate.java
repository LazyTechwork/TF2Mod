package ru.ivansteklow.tf2mod.blocks;

import java.util.Random;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.ivansteklow.tf2mod.init.ItemList;
import ru.ivansteklow.tf2mod.init.References;

public class BlockCrate extends BlockBase {

	private Random rand = new Random();
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	private ImmutableList<Item> list;

	public BlockCrate(String name, ImmutableList<Item> itemList) {
		super(Material.CLOTH, name, References.CREATIVE_TAB, 1F);
		setBlockUnbreakable();
		list = itemList;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItemMainhand().getItem() == ItemList.mannco_key) {
			worldIn.destroyBlock(pos, false);
			double motion = rand.nextGaussian() * 0.001D;
			worldIn.playSound(playerIn, pos,
					new SoundEvent(new ResourceLocation("minecraft", "entity.generic.explode")), SoundCategory.BLOCKS,
					1F, 1F);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pos.getX() + .5F, pos.getY() + .5F,
					pos.getZ() + .5F, motion, motion, motion);
			if (!worldIn.isRemote) {
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + .5F, pos.getY() + .5F, pos.getZ() + .5F,
						new ItemStack(list.get(rand.nextInt(list.size())))));
				if (!playerIn.isCreative())
					playerIn.getHeldItemMainhand().splitStack(1);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumFacing = EnumFacing.getFront(meta);
		if (enumFacing.getAxis() == EnumFacing.Axis.Y || enumFacing == EnumFacing.UP)
			enumFacing = EnumFacing.NORTH;
		if (enumFacing == EnumFacing.DOWN)
			enumFacing = EnumFacing.SOUTH;
		return this.getDefaultState().withProperty(FACING, enumFacing);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		setDefaultState(state);
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

}
