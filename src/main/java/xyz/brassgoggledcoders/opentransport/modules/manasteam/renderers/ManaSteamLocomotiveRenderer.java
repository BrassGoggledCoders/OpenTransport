package xyz.brassgoggledcoders.opentransport.modules.manasteam.renderers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.entities.EntityManaSteamLocomotive;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class ManaSteamLocomotiveRenderer extends Render<EntityManaSteamLocomotive> {
    public static ResourceLocation texture = new ResourceLocation(OpenTransport.MODID, "textures/entity/manasteam/manasteam_locomotive.png");
    public static ManaSteamLocomotiveModel model = new ManaSteamLocomotiveModel();

    public ManaSteamLocomotiveRenderer(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(@Nonnull EntityManaSteamLocomotive entity, double x, double y, double z, float entityYaw, float partialTicks) {
        renderPass(entity, x, y, z, entityYaw, partialTicks, manaSteamLocomotive -> {
            model.render(manaSteamLocomotive, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        });

    }

    public boolean isMultipass() {
        return true;
    }

    public void renderMultipass(EntityManaSteamLocomotive entity, double x, double y, double z, float entityYaw, float partialTicks) {
        renderPass(entity, x, y, z, entityYaw, partialTicks, manaSteamLocomotive -> {
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            model.renderSecondPass(manaSteamLocomotive, 0.0625F);
            GlStateManager.disableBlend();
        });
    }

    public void renderPass(EntityManaSteamLocomotive entity, double x, double y, double z, float entityYaw,
                                float partialTicks, Consumer<EntityManaSteamLocomotive> render) {
        GlStateManager.pushMatrix();
        long i = (long) entity.getEntityId() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f = (((float) (i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f1 = (((float) (i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f2 = (((float) (i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GlStateManager.translate(f, f1, f2);
        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks;
        double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks;
        double d3 = 0.30000001192092896D;
        Vec3d vec3d = entity.getPos(d0, d1, d2);
        float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;

        if (vec3d != null) {
            Vec3d vec3d1 = entity.getPosOffset(d0, d1, d2, d3);
            Vec3d vec3d2 = entity.getPosOffset(d0, d1, d2, -d3);

            if (vec3d1 == null) {
                vec3d1 = vec3d;
            }

            if (vec3d2 == null) {
                vec3d2 = vec3d;
            }

            x += vec3d.xCoord - d0;
            y += (vec3d1.yCoord + vec3d2.yCoord) / 2.0D - d1;
            z += vec3d.zCoord - d2;
            Vec3d vec3d3 = vec3d2.addVector(-vec3d1.xCoord, -vec3d1.yCoord, -vec3d1.zCoord);

            if (vec3d3.lengthVector() != 0.0D) {
                vec3d3 = vec3d3.normalize();
                entityYaw = (float) (Math.atan2(vec3d3.zCoord, vec3d3.xCoord) * 180.0D / Math.PI);
                f3 = (float) (Math.atan(vec3d3.yCoord) * 73.0D);
            }
        }

        GlStateManager.translate((float) x, (float) y + 0.375F, (float) z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-f3, 0.0F, 0.0F, 1.0F);
        float f5 = (float) entity.getRollingAmplitude() - partialTicks;
        float f6 = entity.getDamage() - partialTicks;

        if (f6 < 0.0F) {
            f6 = 0.0F;
        }

        if (f5 > 0.0F) {
            GlStateManager
                    .rotate(MathHelper.sin(f5) * f5 * f6 / 10.0F * (float) entity.getRollingDirection(), 1.0F, 0.0F,
                            0.0F);
        }

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.translate(0, -1, 0);
        this.bindEntityTexture(entity);
        render.accept(entity);
        GlStateManager.popMatrix();

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(EntityManaSteamLocomotive entity) {
        return texture;
    }
}
