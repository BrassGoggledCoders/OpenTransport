package xyz.brassgoggledcoders.opentransport.minecarts.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;
import xyz.brassgoggledcoders.opentransport.renderers.RenderBlock;

public class RenderHolderMinecart extends RenderMinecartBase<EntityMinecartHolder> {
    private RenderBlock renderBlock;

    public RenderHolderMinecart(RenderManager renderManager) {
        super(renderManager);
        renderBlock = new RenderBlock();
    }

    protected void renderBlock(EntityMinecartHolder entity, float partialTicks) {
        int j = entity.getDisplayTileOffset();
        float f4 = 0.75F;
        GlStateManager.scale(f4, f4, f4);
        GlStateManager.translate(-0.5F, (float) (j - 8) / 16.0F, 0.5F);
        this.renderBlock.renderEntity(entity, entity.getBlockWrapper(), partialTicks);
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindEntityTexture(entity);
    }

}
