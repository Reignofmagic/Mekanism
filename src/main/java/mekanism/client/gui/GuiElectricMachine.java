package mekanism.client.gui;

import java.util.List;

import mekanism.api.util.ListUtils;
import mekanism.client.gui.GuiEnergyInfo.IInfoHandler;
import mekanism.client.gui.GuiProgress.IProgressInfoHandler;
import mekanism.client.gui.GuiProgress.ProgressBar;
import mekanism.client.gui.GuiSlot.SlotOverlay;
import mekanism.client.gui.GuiSlot.SlotType;
import mekanism.common.inventory.container.ContainerElectricMachine;
import mekanism.common.tile.TileEntityElectricMachine;
import mekanism.common.util.MekanismUtils;

import net.minecraft.entity.player.InventoryPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiElectricMachine extends GuiMekanism
{
	public TileEntityElectricMachine tileEntity;

	public GuiElectricMachine(InventoryPlayer inventory, TileEntityElectricMachine tentity)
	{
		super(tentity, new ContainerElectricMachine(inventory, tentity));
		tileEntity = tentity;

		guiElements.add(new GuiRedstoneControl(this, tileEntity, tileEntity.guiLocation));
		guiElements.add(new GuiUpgradeTab(this, tileEntity, tileEntity.guiLocation));
		guiElements.add(new GuiConfigurationTab(this, tileEntity, tileEntity.guiLocation));
		guiElements.add(new GuiPowerBar(this, tileEntity, tileEntity.guiLocation, 164, 15));
		guiElements.add(new GuiEnergyInfo(new IInfoHandler() {
			@Override
			public List<String> getInfo()
			{
				String multiplier = MekanismUtils.getEnergyDisplay(tileEntity.energyPerTick);
				return ListUtils.asList("Using: " + multiplier + "/t", "Needed: " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy()-tileEntity.getEnergy()));
			}
		}, this, tileEntity.guiLocation));

		guiElements.add(new GuiSlot(SlotType.INPUT, this, tileEntity.guiLocation, 55, 16));
		guiElements.add(new GuiSlot(SlotType.POWER, this, tileEntity.guiLocation, 55, 52).with(SlotOverlay.POWER));
		guiElements.add(new GuiSlot(SlotType.OUTPUT_LARGE, this, tileEntity.guiLocation, 111, 30));

		guiElements.add(new GuiProgress(new IProgressInfoHandler()
		{
			@Override
			public double getProgress()
			{
				return tileEntity.getScaledProgress();
			}
		}, getProgressType(), this, tileEntity.guiLocation, 77, 37));
	}
	
	public ProgressBar getProgressType()
	{
		return ProgressBar.BLUE;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		fontRendererObj.drawString(tileEntity.getInventoryName(), (xSize/2)-(fontRendererObj.getStringWidth(tileEntity.getInventoryName())/2), 6, 0x404040);
		fontRendererObj.drawString(MekanismUtils.localize("container.inventory"), 8, (ySize - 96) + 2, 0x404040);

		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
	{
		mc.renderEngine.bindTexture(tileEntity.guiLocation);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int guiWidth = (width - xSize) / 2;
		int guiHeight = (height - ySize) / 2;
		drawTexturedModalRect(guiWidth, guiHeight, 0, 0, xSize, ySize);

		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
	}
}