package ru.ivansteklow.tf2mod.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.ivansteklow.tf2mod.enums.PickupTypeEnum;
import ru.ivansteklow.tf2mod.init.QualityList;

public class BlockPickup extends BlockBase {

	private Random rand = new Random();
	private PickupTypeEnum type = PickupTypeEnum.HEALTH;
	private float value = 2F;

	public BlockPickup(String name, PickupTypeEnum type, float value) {
		super(Material.CLOTH, name, QualityList.QUALITY_NORMAL, 1F, false, true);
		setBlockUnbreakable();
		this.type = type;
		this.value = value;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			worldIn.destroyBlock(pos, false);
			int particleNum = rand.nextInt(5);
			if (particleNum < 1)
				particleNum++;
			for (int i = 0; i < particleNum; i++) {
				worldIn.spawnParticle(type.getPickupTypeParticle(), pos.getX() + rand.nextFloat(), pos.getY(),
						pos.getZ() + rand.nextFloat(), rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D,
						rand.nextGaussian() * 0.02D);
			}
			worldIn.playSound(player, pos, type.getPickupTypeSound(), SoundCategory.BLOCKS, 1F, 1F);
			if (!worldIn.isRemote) {
				if (type == PickupTypeEnum.HEALTH)
					player.setHealth(player.getHealth() + value);
				if (type == PickupTypeEnum.FOOD)
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + (int) value);
			}
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
