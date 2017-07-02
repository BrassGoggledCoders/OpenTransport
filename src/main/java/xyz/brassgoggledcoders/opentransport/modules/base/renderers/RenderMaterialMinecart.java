package xyz.brassgoggledcoders.opentransport.modules.base.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderMinecartBase;
import xyz.brassgoggledcoders.opentransport.modules.base.entities.EntityMaterialMinecart;

import java.awt.*;

public class RenderMaterialMinecart extends RenderMinecartBase<EntityMaterialMinecart> {
    public RenderMaterialMinecart(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected void renderCartModel(EntityMaterialMinecart entity) {
        Color color = new Color(entity.getColor());
        GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue());
        this.modelMinecart.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.color(1F, 1F, 1F);
    }
}
