package xyz.brassgoggledcoders.opentransport.boats.renderers;

import com.teamacronymcoders.base.client.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.boats.items.ItemBoatHolder;
import xyz.brassgoggledcoders.opentransport.boats.models.ModelBoatNoPaddles;
import xyz.brassgoggledcoders.opentransport.renderers.RenderBlock;

public class RenderItemHolderBoat extends TileEntitySpecialRenderer<RenderItemHolderBoat.DummyTile> {
    private static final String shortCut = "textures/entity/boat/boat_";
    private static final ResourceLocation[] BOAT_TEXTURES =
            new ResourceLocation[]{new ResourceLocation(shortCut + "oak.png"),
                    new ResourceLocation(shortCut + "spruce.png"), new ResourceLocation(shortCut + "birch.png"),
                    new ResourceLocation(shortCut + "jungle.png"), new ResourceLocation(shortCut + "acacia.png"),
                    new ResourceLocation(shortCut + "darkoak.png")};
    public static ItemBoatHolder itemBoatHolder;
    public static ItemStack itemStack;
    public static ItemCameraTransforms.TransformType type;

    private RenderBlock renderBlock;
    private ModelBoatNoPaddles modelBoat;

    public RenderItemHolderBoat() {
        renderBlock = new RenderBlock();
        modelBoat = new ModelBoatNoPaddles();
    }

    public void renderTileEntityAt(DummyTile te, double x, double y, double z, float partialTicks, int destroyStage) {
        IBlockWrapper blockWrapper = itemBoatHolder.getBlockWrapper(itemStack);
        GlStateManager.pushMatrix();
        GlStateManager.translate(.5, .55, .5);
        switch (type) {
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
        renderBoat(itemStack.getItemDamage());
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.scale(1.5, 1.5, 1.5);
        GlStateManager.translate(-.5, -0.25, .5);
        renderBlockWrapper(blockWrapper);
        GlStateManager.popMatrix();
    }

    protected void renderBoat(int boatNumber) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180, 0, 0, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(BOAT_TEXTURES[boatNumber]);
        modelBoat.render(ClientHelper.player(), 0, 0, 0, 0, 0, 0.1F);
        GlStateManager.popMatrix();
    }

    protected void renderBlockWrapper(IBlockWrapper blockWrapper) {
        renderBlock.renderEntity(ClientHelper.player(), blockWrapper, 0);
    }

    public static class DummyTile extends TileEntity {

    }
}
