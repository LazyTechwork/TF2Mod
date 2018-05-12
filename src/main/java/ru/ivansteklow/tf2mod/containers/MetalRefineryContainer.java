package ru.ivansteklow.tf2mod.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import ru.ivansteklow.isdev.inventory.SlotItemOutput;
import ru.ivansteklow.tf2mod.tileentities.MetalRefineryTileEntity;

public class MetalRefineryContainer extends Container {

	private MetalRefineryTileEntity te;

	public MetalRefineryContainer(IInventory playerInventory, MetalRefineryTileEntity te) {
		this.te = te;

		addOwnSlots();
		addPlayerSlots(playerInventory);
	}

	private void addPlayerSlots(IInventory playerInventory) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	private void addOwnSlots() {
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(itemHandler, 0, 45, 34));
		addSlotToContainer(new SlotItemOutput(itemHandler, 1, 99, 34));
		addSlotToContainer(new SlotItemOutput(itemHandler, 2, 125, 34));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);
		ItemStackHandler handler = this.te.itemStackHandler;

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < handler.getSlots()) {
				// From the block breaker inventory to player's inventory
				if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
					return ItemStack.EMPTY;
			} else {
				// From the player's inventory to block breaker's inventory
				if (current.getItem() == Items.ENCHANTED_BOOK) {
					if (!this.mergeItemStack(current, 9, handler.getSlots(), false))
						return ItemStack.EMPTY;
				}
				if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
					return ItemStack.EMPTY;
			}

			if (current.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (current.getCount() == previous.getCount())
				return null;
			slot.onTake(playerIn, current);
		}
		return previous;
	}

}
