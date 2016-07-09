package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.models.ModelBuoy;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.tiles.TileEntityBuoy;

import javax.annotation.Nullable;

public class RenderBuoy extends TileEntitySpecialRenderer<TileEntityBuoy> {
	private ResourceLocation textureBuoy;
	private ModelBuoy modelBuoy;

	public RenderBuoy() {
		super();
		modelBuoy = new ModelBuoy();
		textureBuoy = new ResourceLocation(OpenTransport.MODID, "textures/entity/buoy/buoy.png");
	}

	@Override
	public void renderTileEntityAt(@Nullable TileEntityBuoy te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		if(te == null || te.isBottom()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + .5, y + 1.45, z + .5);
			GlStateManager.rotate(180, 1, 0, 0);
			this.bindTexture(textureBuoy);

			modelBuoy.render(null, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GlStateManager.popMatrix();
		}
	}
}