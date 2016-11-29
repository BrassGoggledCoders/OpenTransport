package xyz.brassgoggledcoders.opentransport.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;

public class RenderBlock {
    public void renderEntity(Entity entity, IBlockWrapper blockWrapper, float partialTicks) {
        this.render(blockWrapper.getRenderType(), blockWrapper.getBlockState(),
                blockWrapper.hasTileEntity() ? blockWrapper.getTileEntity() : null,
                entity.getBrightness(partialTicks));
    }

    private void render(RenderType type, IBlockState blockState, TileEntity tileEntity, float brightness) {
        switch (type) {
            case VMC:
                this.renderVMC(blockState, brightness);
                break;
            case TESR:
                this.renderTESR(tileEntity);
                break;
            case COMBO:
                this.renderVMC(blockState, brightness);
                this.renderTESR(tileEntity);
                break;
            case CUSTOM:
                break;
        }
    }

    public void renderVMC(IBlockState blockState, float brightness) {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(blockState, brightness);
        GlStateManager.popMatrix();
    }

    public void renderTESR(TileEntity tileEntity) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(90F, 0F, 1F, 0F);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(tileEntity, 0, 0, 0, 0.0F);
        GlStateManager.enableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
