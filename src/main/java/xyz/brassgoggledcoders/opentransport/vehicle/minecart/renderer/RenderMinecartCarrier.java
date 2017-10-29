package xyz.brassgoggledcoders.opentransport.vehicle.minecart.renderer;

import com.teamacronymcoders.base.renderer.entity.loader.EntityRenderer;
import com.teamacronymcoders.base.renderer.entity.loader.IEntityRenderer;
import com.teamacronymcoders.base.renderer.entity.minecart.RenderMinecartBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;

@EntityRenderer(module = "minecart", handler = "vehicle")
public class RenderMinecartCarrier extends RenderMinecartBase<EntityMinecartCarrier> implements IEntityRenderer<EntityMinecartCarrier> {
    public RenderMinecartCarrier(RenderManager renderManagerIn) {
        super(renderManagerIn);
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
