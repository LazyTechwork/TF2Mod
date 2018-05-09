package ru.ivansteklow.tf2mod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;
import ru.ivansteklow.tf2mod.config.ModConfig;
import ru.ivansteklow.tf2mod.recipes.RefineryRecipes;

public class MetalRefineryTileEntity extends TileEntity implements ITickable, ICapabilityProvider {

	public static final int SIZE = 3;
	public final int METAL_REFINERY_WORKTIME = ModConfig.metalRefineryWorkTime;
	public int time = 0;

	private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
		protected void onContentsChanged(int slot) {
			MetalRefineryTileEntity.this.markDirty();
		};
	};

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("items", itemStackHandler.serializeNBT());
		compound.setInteger("time", time);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("items")) {
			itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
		}
		this.time = compound.getInteger("time");
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	private void processItem() {
		ItemStack input = this.itemStackHandler.getStackInSlot(0);
		if (RefineryRecipes.instance().getResult(input) != null) {
			if (this.itemStackHandler.getStackInSlot(1) == null && this.itemStackHandler.getStackInSlot(2) == null) {
				ItemStack[] output = RefineryRecipes.instance().getResult(input);
				this.itemStackHandler.setStackInSlot(1, output[0]);
				this.itemStackHandler.setStackInSlot(2, output[1]);
				this.itemStackHandler.setStackInSlot(0, null);
			}
		}
	}

	@Override
	public void update() {
		ItemStack input = this.itemStackHandler.getStackInSlot(0);
		if (RefineryRecipes.instance().getResult(input) != null) {
			if (this.time == METAL_REFINERY_WORKTIME) {
				processItem();
			} else if (time < METAL_REFINERY_WORKTIME) {
				this.time++;
			}
		} else {
			this.time = 0;
		}
	}

}
