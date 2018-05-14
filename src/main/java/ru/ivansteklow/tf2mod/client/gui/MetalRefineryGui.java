package ru.ivansteklow.tf2mod.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import ru.ivansteklow.isdev.gui.HoveringText;
import ru.ivansteklow.isdev.gui.ProgressBar;
import ru.ivansteklow.isdev.gui.ProgressBar.ProgressBarDirection;
import ru.ivansteklow.tf2mod.containers.MetalRefineryContainer;
import ru.ivansteklow.tf2mod.init.References;
import ru.ivansteklow.tf2mod.tileentities.MetalRefineryTileEntity;

public class MetalRefineryGui extends GuiContainer {

	public static final int WIDTH = 176;
	public static final int HEIGHT = 166;

	private static final ResourceLocation background = new ResourceLocation(References.MODID,
			"textures/gui/metal_refinery.png");
	private ProgressBar progressBar;
	private MetalRefineryTileEntity te;
	private HoveringText hText;

	public int time, maxTime = 0;

	public MetalRefineryGui(MetalRefineryTileEntity tileEntity, MetalRefineryContainer container) {
		super(container);
		this.te = tileEntity;
		xSize = WIDTH;
		ySize = HEIGHT;
		this.progressBar = new ProgressBar(background, ProgressBarDirection.LEFT_TO_RIGHT, 22, 15, 71, 34, 176, 0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format("gui.metalrefinery.header");
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.progressBar.setMin(time).setMax(maxTime);
		this.progressBar.draw(mc);
		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
		List<String> text = new ArrayList<String>();
		text.add(TextFormatting.YELLOW + I18n.format("gui.metalrefinery.time_remain"));
		text.add(TextFormatting.YELLOW + Integer.toString((this.maxTime - this.time) / 20) + " "
				+ I18n.format("gui.metalrefinery.time_remain.sec"));
		this.hText = new HoveringText(this, actualMouseX, actualMouseY, 71, 34, 22, 13, text);
		hText.draw();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	public void updateScreen() {
		this.maxTime = this.te.getMaxTime();
		super.updateScreen();
	}

	public void setProgress(int progress) {
		this.time = progress;
	}
}
