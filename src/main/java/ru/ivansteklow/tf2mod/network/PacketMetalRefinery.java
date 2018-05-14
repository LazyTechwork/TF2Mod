package ru.ivansteklow.tf2mod.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import ru.ivansteklow.tf2mod.client.gui.MetalRefineryGui;
import ru.ivansteklow.tf2mod.containers.MetalRefineryContainer;
import ru.ivansteklow.tf2mod.tileentities.MetalRefineryTileEntity;

public class PacketMetalRefinery implements IMessage {

	private boolean isMessageValid = false;
	private int time = 0;
	private int x, y, z;

	public PacketMetalRefinery() {
	}

	public PacketMetalRefinery(int time, BlockPos pos) {
		this.time = time;
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.isMessageValid)
			return;
		buf.writeInt(this.time);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
	}

	public class PacketMetalRefineryHandler implements IMessageHandler<PacketMetalRefinery, IMessage> {

		@Override
		public IMessage onMessage(PacketMetalRefinery message, MessageContext ctx) {
			if (!message.isMessageValid && ctx.side != Side.CLIENT)
				return null;
			Minecraft.getMinecraft().addScheduledTask(() -> {
				World world = ctx.getServerHandler().player.getEntityWorld();
				TileEntity te = world.getTileEntity(new BlockPos(message.x, message.y, message.z));
				if (te instanceof MetalRefineryTileEntity)
					if (ctx.getServerHandler().player.openContainer instanceof MetalRefineryContainer) {
						MetalRefineryGui gui = new MetalRefineryGui((MetalRefineryTileEntity) te,
								(MetalRefineryContainer) ctx.getServerHandler().player.openContainer);
						gui.setProgress(message.time);
					}
			});
			return null;
		}

	}

}
