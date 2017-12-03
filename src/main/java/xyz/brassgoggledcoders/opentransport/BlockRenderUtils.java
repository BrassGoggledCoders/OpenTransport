package xyz.brassgoggledcoders.opentransport;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

@SideOnly(Side.CLIENT)
public class BlockRenderUtils {
    public static void renderBlockAsEntity(@Nonnull IBlockState blockState, @Nullable TileEntity tileEntity, float partialTicks, float brightness) {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        switch (blockState.getRenderType()) {
            case MODEL:
                Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(blockState, brightness);
                break;
            case ENTITYBLOCK_ANIMATED:
                Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(blockState, brightness);
                Optional.ofNullable(tileEntity).ifPresent(value ->{
                    GlStateManager.rotate(90F, 0F, 1F, 0F);
                    TileEntityRendererDispatcher.instance.render(value, 0, 0, 0, partialTicks);
                    GlStateManager.enableRescaleNormal();
                });
                break;
            case LIQUID:
            case INVISIBLE:
                break;
        }

        GlStateManager.popMatrix();
    }
}
