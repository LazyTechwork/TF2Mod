package ru.ivansteklow.tf2mod.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.ivansteklow.tf2mod.blocks.BlockMetalRefinery;
import ru.ivansteklow.tf2mod.config.ModConfig;
import ru.ivansteklow.tf2mod.recipes.RefineryRecipes;
import ru.ivansteklow.tf2mod.recipes.RefineryRecipes.RefineryRecipe;

public class MetalRefineryTileEntity extends TileEntity implements ITickable, ICapabilityProvider {

	public static final int SIZE = 3;
	public static final int METAL_REFINERY_WORKTIME = ModConfig.metalRefineryWorkTime;
	public int time = 0;
	public boolean isActive = false;
	private Random rand = new Random();

	public ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
		protected void onContentsChanged(int slot) {
			MetalRefineryTileEntity.this.markDirty();
		};
	};

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("items")) {
			this.itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
		}
		if (compound.hasKey("this.time")) {
			this.time = compound.getInteger("this.time");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("items", this.itemStackHandler.serializeNBT());
		compound.setInteger("this.time", this.time);
		return compound;
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
		}
		return super.getCapability(capability, facing);
	}

	private void processItem(RefineryRecipe recipe, int chance) {
		ItemStack output = new ItemStack(recipe.output1.getItem(),
				this.itemStackHandler.getStackInSlot(1).getCount() + recipe.output1.getCount());
		this.itemStackHandler.setStackInSlot(1, ItemStack.EMPTY);
		this.itemStackHandler.setStackInSlot(1, output);
		output = new ItemStack(recipe.output2.getItem(),
				this.itemStackHandler.getStackInSlot(2).getCount() + recipe.output2.getCount());
		if (rand.nextInt(100) <= chance)
			this.itemStackHandler.setStackInSlot(2, output);
		this.itemStackHandler.setStackInSlot(0, new ItemStack(this.itemStackHandler.getStackInSlot(0).getItem(),
				this.itemStackHandler.getStackInSlot(0).getCount() - recipe.input.getCount()));
	}

	@Override
	public void update() {
		if (!this.world.isRemote) {
			ItemStack input = this.itemStackHandler.getStackInSlot(0);
			if (RefineryRecipes.instance().getResult(input) != null) {
				RefineryRecipe recipe = RefineryRecipes.instance().getResult(input);
				if (this.time == METAL_REFINERY_WORKTIME) {
					if ((this.itemStackHandler.getStackInSlot(1).getItem().equals(recipe.output1.getItem())
							|| this.itemStackHandler.getStackInSlot(1).getCount() == 0)
							&& (this.itemStackHandler.getStackInSlot(2).getItem().equals(recipe.output2.getItem())
									|| this.itemStackHandler.getStackInSlot(2).isEmpty())
							&& (this.itemStackHandler.getStackInSlot(1).getCount() == 0
									|| this.itemStackHandler.getStackInSlot(1).getCount()
											+ recipe.output1.getCount() <= 64)
							&& (this.itemStackHandler.getStackInSlot(2).getCount() == 0
									|| this.itemStackHandler.getStackInSlot(2).getCount()
											+ recipe.output2.getCount() <= 64)
							&& (this.itemStackHandler.getStackInSlot(0).getCount() - recipe.input.getCount() >= 0)) {
						processItem(recipe, 5);
						this.time = 0;
						this.isActive = true;
					}
				} else if (this.time < METAL_REFINERY_WORKTIME) {
					this.time++;
					this.isActive = true;
				}
			} else {
				this.time = 0;
				this.isActive = false;
			}
			((BlockMetalRefinery) this.getWorld().getBlockState(this.getPos()).getBlock()).setBlockActivated(isActive);
		}
	}

	public int getElapsedTime() {
		return this.time;
	}

	public int getMaxTime() {
		return METAL_REFINERY_WORKTIME;
	}

}
