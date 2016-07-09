package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Buoy - Tristaric
 * Created using Tabula 4.1.1
 */
public class ModelBuoy extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer base2;
	public ModelRenderer support;
	public ModelRenderer support2;
	public ModelRenderer support3;
	public ModelRenderer support4;
	public ModelRenderer thing;
	public ModelRenderer lightBase;
	public ModelRenderer shape10;

	public ModelBuoy() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.support3 = new ModelRenderer(this, 0, 31);
		this.support3.setRotationPoint(-3.5F, -3.0F, -3.5F);
		this.support3.addBox(-1.0F, 0.0F, -1.0F, 2, 21, 2, 0.0F);
		this.setRotateAngle(support3, 0.0F, -3.141592653589793F, 0.0F);
		this.support4 = new ModelRenderer(this, 0, 31);
		this.support4.setRotationPoint(3.5F, -3.0F, -3.5F);
		this.support4.addBox(-1.0F, 0.0F, -1.0F, 2, 21, 2, 0.0F);
		this.setRotateAngle(support4, 0.0F, 1.5707963267948966F, 0.0F);
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.base.addBox(-7.5F, 0.0F, -7.5F, 15, 4, 15, 0.0F);
		this.thing = new ModelRenderer(this, 0, 56);
		this.thing.setRotationPoint(0.0F, -2.5F, 0.0F);
		this.thing.addBox(-4.0F, 0.0F, -4.0F, 8, 9, 8, 0.0F);
		this.support2 = new ModelRenderer(this, 0, 31);
		this.support2.setRotationPoint(-3.5F, -3.0F, 3.5F);
		this.support2.addBox(-1.0F, 0.0F, -1.0F, 2, 21, 2, 0.0F);
		this.setRotateAngle(support2, 0.0F, -1.5707963267948966F, 0.0F);
		this.base2 = new ModelRenderer(this, 0, 19);
		this.base2.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.base2.addBox(-5.0F, 0.0F, -5.0F, 10, 2, 10, 0.0F);
		this.support = new ModelRenderer(this, 0, 31);
		this.support.setRotationPoint(3.5F, -3.0F, 3.5F);
		this.support.addBox(-1.0F, 0.0F, -1.0F, 2, 21, 2, 0.0F);
		this.shape10 = new ModelRenderer(this, 45, 0);
		this.shape10.setRotationPoint(0.0F, -7.5F, 0.0F);
		this.shape10.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
		this.lightBase = new ModelRenderer(this, 57, 0);
		this.lightBase.setRotationPoint(0.0F, -3.5F, 0.0F);
		this.lightBase.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.support3.render(f5);
		this.support4.render(f5);
		this.base.render(f5);
		this.thing.render(f5);
		this.support2.render(f5);
		this.base2.render(f5);
		this.support.render(f5);
		this.shape10.render(f5);
		this.lightBase.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
