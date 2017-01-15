package xyz.brassgoggledcoders.opentransport.modules.manasteam.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ManaSteamLocomotiveModel - wiiv
 * Created using Tabula 4.1.1
 */
public class ManaSteamLocomotiveModel extends ModelBase {
    public ModelRenderer tank;
    public ModelRenderer shape1;
    public ModelRenderer tube;
    public ModelRenderer tube_1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer front;
    public ModelRenderer railing;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer grass;

    public ManaSteamLocomotiveModel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.shape1_3 = new ModelRenderer(this, 1, 20);
        this.shape1_3.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1_3.addBox(-11.0F, 4.0F, -8.0F, 23, 2, 16, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 38);
        this.shape1_4.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1_4.addBox(-11.0F, -8.0F, -7.0F, 10, 10, 14, 0.0F);
        this.tank = new ModelRenderer(this, 0, 62);
        this.tank.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.tank.addBox(3.01F, -9.0F, -8.0F, 9, 7, 16, 0.0F);
        this.tube_1 = new ModelRenderer(this, 86, 19);
        this.tube_1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.tube_1.addBox(-4.0F, -11.0F, -4.0F, 12, 3, 3, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 86, 26);
        this.shape1_1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1_1.addBox(-10.0F, -12.0F, -3.0F, 6, 4, 6, 0.0F);
        this.front = new ModelRenderer(this, 86, 0);
        this.front.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.front.addBox(-13.0F, -1.0F, -6.0F, 2, 7, 12, 0.0F);
        this.tube = new ModelRenderer(this, 86, 19);
        this.tube.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.tube.addBox(-4.0F, -11.0F, 1.0F, 12, 3, 3, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 48, 38);
        this.shape1_2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1_2.addBox(-1.0F, -7.0F, -5.0F, 4, 9, 10, 0.0F);
        this.grass = new ModelRenderer(this, 50, 82);
        this.grass.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.grass.addBox(4.01F, -2.01F, -7.0F, 7, 1, 14, 0.0F);
        this.shape1 = new ModelRenderer(this, 50, 62);
        this.shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1.addBox(3.01F, -2.0F, -8.0F, 9, 4, 16, 0.0F);
        this.railing = new ModelRenderer(this, 0, 0);
        this.railing.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.railing.addBox(-12.0F, 2.0F, -9.0F, 25, 2, 18, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1_3.render(f5);
        this.shape1_4.render(f5);
        this.tank.render(f5);
        this.tube_1.render(f5);
        this.shape1_1.render(f5);
        this.front.render(f5);
        this.tube.render(f5);
        this.shape1_2.render(f5);
        this.grass.render(f5);
        this.shape1.render(f5);
        this.railing.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
