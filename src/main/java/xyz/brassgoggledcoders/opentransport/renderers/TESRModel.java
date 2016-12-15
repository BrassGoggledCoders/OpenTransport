package xyz.brassgoggledcoders.opentransport.renderers;

import com.google.common.base.Function;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TESRModel<ITEM extends Item> implements IModel {
    private IModelState emptyModelState;
    private List<ResourceLocation> emptyList;
    private TESRBakedModel<ITEM> bakedModel;

    public TESRModel(ITEM item, IItemTESRAccessor<ITEM> tesrAccessor) {
        emptyModelState = new EmptyModelState();
        emptyList = Collections.emptyList();
        bakedModel = new TESRBakedModel<>(item, tesrAccessor);
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return emptyList;
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return emptyList;
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return bakedModel;
    }

    @Override
    public IModelState getDefaultState() {
        return emptyModelState;
    }
}
