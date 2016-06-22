package xyz.brassgoggledcoders.opentransport.renderers.boats;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.brassgoggledcoders.opentransport.entities.boats.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.models.boats.ModelBoatNoPaddles;
import xyz.brassgoggledcoders.opentransport.renderers.RenderBlock;

import javax.annotation.Nonnull;

public class RenderHolderBoat extends RenderBoat
{
	public enum Factory implements IRenderFactory<EntityBoatHolder>
	{
		INSTANCE;

		@Override
		public Render<? super EntityBoatHolder> createRenderFor(RenderManager manager) {
			return new RenderHolderBoat(manager);
		}
	}

	public RenderBlock renderBlock;

	public RenderHolderBoat(RenderManager renderManager)
	{
		super(renderManager);
		renderBlock = new RenderBlock();
		modelBoat = new ModelBoatNoPaddles();
	}

	@Override
	public void doRender(@Nonnull EntityBoat entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		EntityBoatHolder boatHolder = (EntityBoatHolder)entity;
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entity, entityYaw, partialTicks);
		this.bindEntityTexture(entity);

		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
		}

		this.modelBoat.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.pushMatrix();
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(-0.5,-0.20, 0.5);
		this.renderBlock.renderEntity(entity, boatHolder.getBlockContainer(), partialTicks);
		GlStateManager.popMatrix();

		if (this.renderOutlines)
		{
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
}
