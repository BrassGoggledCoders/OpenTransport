package xyz.brassgoggledcoders.moarlibs.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;

public class RenderBlock
{
	public void renderEntity(Entity entity, IBlockContainer blockContainer, float partialTicks)
	{
		TileEntity tileEntity = null;
		if(blockContainer.getTileContainer() != null)
		{
			tileEntity = blockContainer.getTileContainer().getTileEntity();
		}

		this.render(blockContainer.getRenderType(), blockContainer.getBlockState(), tileEntity, entity.getBrightness(partialTicks));
	}

	private void render(RenderType type, IBlockState blockState, TileEntity tileEntity, float brightness)
	{
		switch(type)
		{
			case VMC:
				this.renderVMC(blockState, brightness);
				break;
			case TESR:
				this.renderTESR(tileEntity);
				break;
			case COMBO:
				this.renderVMC(blockState, brightness);
				this.renderTESR(tileEntity);
				break;
			case CUSTOM:
				break;
		}
	}

	public void renderVMC(IBlockState blockState, float brightness)
	{
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(blockState, brightness);
		GlStateManager.popMatrix();
	}

	public void renderTESR(TileEntity tileEntity)
	{
		GlStateManager.pushMatrix();
		GlStateManager.rotate(90F, 0F, 1F, 0F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tileEntity, 0, 0, 0, 0.0F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();
	}
}