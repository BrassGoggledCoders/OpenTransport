package xyz.brassgoggledcoders.opentransport.vehicle.minecart.renderer;

import com.teamacronymcoders.base.renderer.entity.loader.EntityRenderer;
import com.teamacronymcoders.base.renderer.entity.loader.IEntityRenderer;
import com.teamacronymcoders.base.renderer.entity.minecart.RenderMinecartBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.brassgoggledcoders.opentransport.BlockRenderUtils;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;

@EntityRenderer(module = "minecart", handler = "vehicle")
public class RenderMinecartCarrier extends RenderMinecartBase<EntityMinecartCarrier> implements IEntityRenderer<EntityMinecartCarrier> {
    public RenderMinecartCarrier() {
        super(null);
    }

    public RenderMinecartCarrier(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected void renderBlock(EntityMinecartCarrier entity, float partialTicks) {
        int j = entity.getDisplayTileOffset();
        float f4 = 0.75F;
        GlStateManager.scale(f4, f4, f4);
        GlStateManager.translate(-0.5F, (float) (j - 8) / 16.0F, 0.5F);
        CarriedBlockInstance carriedInstance = entity.getCarriedBlock();
        BlockRenderUtils.renderBlockAsEntity(carriedInstance.getBlockState(), carriedInstance.getTileEntity().orElse(null), partialTicks, entity.getBrightness());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void renderCartModel(EntityMinecartCarrier entity) {
        entity.getCustomMinecart().renderMinecartModel(entity);
    }

    @Override
    public Class<EntityMinecartCarrier> getEntityClass() {
        return EntityMinecartCarrier.class;
    }

    @Override
    public IRenderFactory<EntityMinecartCarrier> getRenderFactory() {
        return RenderMinecartCarrier::new;
    }
}
