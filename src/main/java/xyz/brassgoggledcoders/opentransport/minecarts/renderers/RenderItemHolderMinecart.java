package xyz.brassgoggledcoders.opentransport.minecarts.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;
import xyz.brassgoggledcoders.opentransport.renderers.RenderBlock;

public class RenderItemHolderMinecart extends TileEntitySpecialRenderer<RenderItemHolderMinecart.DummyTile> {
    private RenderBlock renderBlock;
    private ModelMinecart modelMinecart;
    private ResourceLocation minecartTexture = new ResourceLocation("textures/entity/minecart.png");

    public static ItemMinecartHolder itemMinecartHolder;
    public static ItemStack itemStackMinecartHolder;
    public static ItemCameraTransforms.TransformType transformType;

    public RenderItemHolderMinecart() {
        renderBlock = new RenderBlock();
        modelMinecart = new ModelMinecart();
    }

    public void renderTileEntityAt(DummyTile tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
        IBlockWrapper blockWrapper = itemMinecartHolder.getBlockWrapper();

        GlStateManager.pushMatrix();
        GlStateManager.translate(.5, .55, .5);
        switch (transformType) {
            case GROUND:
                GlStateManager.scale(.15, .15, .15);
                break;
            case GUI:
                GlStateManager.scale(.25, .25, .25);
                GlStateManager.rotate(45, 1, -1, 0);
                break;
            case FIRST_PERSON_LEFT_HAND:
                GlStateManager.scale(.2, .2, .2);
                GlStateManager.rotate(45, 1, 0, 0);
                break;
            case FIRST_PERSON_RIGHT_HAND:
                GlStateManager.scale(.2, .2, .2);
                GlStateManager.rotate(45, 1, 0, 0);
                break;
            case THIRD_PERSON_LEFT_HAND:
                GlStateManager.scale(.15, .15, .15);
                GlStateManager.rotate(45, 1, 0, 0);
                break;
            case THIRD_PERSON_RIGHT_HAND:
                GlStateManager.scale(.15, .15, .15);
                GlStateManager.rotate(45, 1, 0, 0);
                break;
            default:
                break;
        }
        renderMinecart();
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.scale(1.5, 1.5, 1.5);
        GlStateManager.translate(-.5, -0.25, .5);
        renderBlockWrapper(blockWrapper);
        GlStateManager.popMatrix();
    }

    protected void renderMinecart() {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180, 0, 0, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(minecartTexture);
        modelMinecart.render(Minecraft.getMinecraft().thePlayer, 0, 0, 0, 0, 0, 0.1F);
        GlStateManager.popMatrix();
    }

    protected void renderBlockWrapper(IBlockWrapper blockWrapper) {
        renderBlock.renderEntity(Minecraft.getMinecraft().thePlayer, blockWrapper, Minecraft.getMinecraft().getRenderPartialTicks());
    }

    public static class DummyTile extends TileEntity {

    }
}
