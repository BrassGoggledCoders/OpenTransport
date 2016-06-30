package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.entities.EntityBuoy;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.models.ModelBuoy;

import javax.annotation.Nonnull;

public class RenderBuoy extends Render<EntityBuoy> {
	public enum Factory implements IRenderFactory<EntityBuoy> {
		INSTANCE;

		@Override
		public Render<? super EntityBuoy> createRenderFor(RenderManager renderManager) {
			return new RenderBuoy(renderManager);
		}
	}

	private ResourceLocation textureBuoy;
	private ModelBuoy modelBuoy;

	public RenderBuoy(RenderManager renderManager) {
		super(renderManager);
		modelBuoy = new ModelBuoy();
		textureBuoy = new ResourceLocation(OpenTransport.MODID, "textures/entity/buoy/buoy.png");
	}

	@Override
	public void doRender(@Nonnull EntityBuoy entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.45, z);
		GlStateManager.rotate(180, 1, 0, 0);
		this.bindEntityTexture(entity);

		modelBuoy.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.popMatrix();
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull EntityBuoy entity) {
		return textureBuoy;
	}
}