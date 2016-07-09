package xyz.brassgoggledcoders.opentransport.models.boats;

import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ModelBoatNoPaddles extends ModelBoat {
	@Override
	public void render(@Nonnull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scale) {
		GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

		for(int i = 0; i < 5; ++i) {
			this.boatSides[i].render(scale);
		}
	}
}
