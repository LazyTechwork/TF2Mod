package ru.ivansteklow.tf2mod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.ivansteklow.tf2mod.config.ModConfig;
import ru.ivansteklow.tf2mod.init.ItemList;
import ru.ivansteklow.tf2mod.recipes.RefineryRecipes;

public class MetalRefineryTileEntity extends TileEntity implements ITickable, ICapabilityProvider {

	public static final int SIZE = 3;
	public static final int METAL_REFINERY_WORKTIME = ModConfig.metalRefineryWorkTime;
	public static int time = 0;

	private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
		protected void onContentsChanged(int slot) {
			MetalRefineryTileEntity.this.markDirty();
		};
	};

	@Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        if(compound.hasKey("time")) {
        	time = compound.getInteger("time");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setInteger("time", time);
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

	private void processItem(ItemStack output, ItemStack output2) {
			this.itemStackHandler.setStackInSlot(1, output);
			this.itemStackHandler.setStackInSlot(2, output2);
			this.itemStackHandler.setStackInSlot(0, new ItemStack(Blocks.AIR));
	}

	@Override
	public void update() {
		ItemStack input = this.itemStackHandler.getStackInSlot(0);
		if (input.getItem() == Items.IRON_INGOT && input.getCount() == 2) {
			if (time == METAL_REFINERY_WORKTIME) {
				processItem(new ItemStack(ItemList.scrap_metal), new ItemStack(ItemList.scrap_metal));
			} else if (time < METAL_REFINERY_WORKTIME) {
				time++;
			}
		} else {
			time = 0;
		}
	}
	
	public int getElapsedTime() {
		return time;
	}
	
	public int getMaxTime() {
		return METAL_REFINERY_WORKTIME;
	}

}
