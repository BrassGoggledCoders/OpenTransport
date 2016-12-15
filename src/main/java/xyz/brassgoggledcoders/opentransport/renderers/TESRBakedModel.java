package xyz.brassgoggledcoders.opentransport.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.List;

public class TESRBakedModel<ITEM extends Item> implements IPerspectiveAwareModel {
    private ITEM item;
    private ItemStack itemStack;
    private IItemTESRAccessor<ITEM> itemTESRAccessor;
    public TESRBakedModel(ITEM item, ItemStack itemStack, IItemTESRAccessor<ITEM> itemTESRAccessor) {
        this.item = item;
        this.itemStack = itemStack;
        this.itemTESRAccessor = itemTESRAccessor;
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return Collections.emptyList();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        this.itemTESRAccessor.setTESRItemStack(this.item, this.itemStack);
        return true;
    }

    @Override
    @Nonnull
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
    }

    @Override
    @Nonnull
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    @Nonnull
    public ItemOverrideList getOverrides() {
        return new ItemOverrideList(Collections.emptyList());
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
        this.itemTESRAccessor.setCameraTransformType(cameraTransformType);
        return  Pair.of(this, TRSRTransformation.identity().getMatrix());
    }
}
