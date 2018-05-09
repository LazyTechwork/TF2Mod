package ru.ivansteklow.tf2mod.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import ru.ivansteklow.tf2mod.Core;
import ru.ivansteklow.tf2mod.client.gui.ProgressBar.ProgressBarDirection;
import ru.ivansteklow.tf2mod.containers.MetalRefineryContainer;
import ru.ivansteklow.tf2mod.init.References;
import ru.ivansteklow.tf2mod.tileentities.MetalRefineryTileEntity;

public class MetalRefineryGui extends GuiContainer{

	public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    private static final ResourceLocation background = new ResourceLocation(References.MODID, "textures/gui/metal_refinery.png");
    private ProgressBar progressBar;
    private MetalRefineryTileEntity te;
    
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
    
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	String s = I18n.format("gui.metalrefinery.header");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.progressBar.setMin(time).setMax(maxTime);
        this.progressBar.draw(mc);
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    public void updateScreen() {
    	this.time = te.getElapsedTime();
    	this.maxTime = te.getMaxTime();
    	Core.logger.info(time);
    }

}
