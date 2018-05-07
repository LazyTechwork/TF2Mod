package ru.ivansteklow.tf2mod.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.ivansteklow.tf2mod.client.gui.MetalRefineryGui;
import ru.ivansteklow.tf2mod.containers.MetalRefineryContainer;
import ru.ivansteklow.tf2mod.tileentities.MetalRefineryTileEntity;

public class GuiProxy implements IGuiHandler{

	@Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof MetalRefineryTileEntity) {
            return new MetalRefineryContainer(player.inventory, (MetalRefineryTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof MetalRefineryTileEntity) {
            MetalRefineryTileEntity containerTileEntity = (MetalRefineryTileEntity) te;
            return new MetalRefineryGui(containerTileEntity, new MetalRefineryContainer(player.inventory, containerTileEntity));
        }
        return null;
    }

}
