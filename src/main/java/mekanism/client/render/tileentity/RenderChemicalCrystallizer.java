package mekanism.client.render.tileentity;

import mekanism.client.model.ModelChemicalCrystallizer;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.tile.TileEntityChemicalCrystallizer;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderChemicalCrystallizer extends TileEntitySpecialRenderer
{
	private ModelChemicalCrystallizer model = new ModelChemicalCrystallizer();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick, int damage)
	{
		renderAModelAt((TileEntityChemicalCrystallizer)tileEntity, x, y, z, partialTick);
	}

	private void renderAModelAt(TileEntityChemicalCrystallizer tileEntity, double x, double y, double z, float partialTick)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		bindTexture(MekanismUtils.getResource(ResourceType.RENDER, "ChemicalCrystallizer.png"));

		switch(tileEntity.getFacing())
		{
			case NORTH: GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); break;
			case SOUTH: GL11.glRotatef(180, 0.0F, 1.0F, 0.0F); break;
			case WEST: GL11.glRotatef(90, 0.0F, 1.0F, 0.0F); break;
			case EAST: GL11.glRotatef(270, 0.0F, 1.0F, 0.0F); break;
		}

		if(tileEntity.isActive)
		{
			tileEntity.spinSpeed = Math.min(1, tileEntity.spinSpeed+0.01F);
		}
		else {
			tileEntity.spinSpeed = Math.max(0, tileEntity.spinSpeed-0.02F);
		}

		tileEntity.spin = (tileEntity.spin + (tileEntity.spinSpeed*0.1F)) % 1;

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.renderWithRotation(0.0625F, tileEntity.spin);
		GL11.glPopMatrix();

		MekanismRenderer.machineRenderer.renderAModelAt(tileEntity, x, y, z, partialTick);
	}
}
