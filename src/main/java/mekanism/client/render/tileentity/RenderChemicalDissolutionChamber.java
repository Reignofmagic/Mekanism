package mekanism.client.render.tileentity;

import mekanism.client.model.ModelChemicalDissolutionChamber;
import mekanism.common.tile.TileEntityChemicalDissolutionChamber;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderChemicalDissolutionChamber extends TileEntitySpecialRenderer
{
	private ModelChemicalDissolutionChamber model = new ModelChemicalDissolutionChamber();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick, int damage)
	{
		renderAModelAt((TileEntityChemicalDissolutionChamber)tileEntity, x, y, z, partialTick);
	}

	private void renderAModelAt(TileEntityChemicalDissolutionChamber tileEntity, double x, double y, double z, float partialTick)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		bindTexture(MekanismUtils.getResource(ResourceType.RENDER, "ChemicalDissolutionChamber.png"));

		switch(tileEntity.getFacing())
		{
			case NORTH: GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); break;
			case SOUTH: GL11.glRotatef(180, 0.0F, 1.0F, 0.0F); break;
			case WEST: GL11.glRotatef(90, 0.0F, 1.0F, 0.0F); break;
			case EAST: GL11.glRotatef(270, 0.0F, 1.0F, 0.0F); break;
		}

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}
}
