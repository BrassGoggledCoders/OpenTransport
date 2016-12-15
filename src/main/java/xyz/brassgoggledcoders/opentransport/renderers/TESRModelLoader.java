package xyz.brassgoggledcoders.opentransport.renderers;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class TESRModelLoader implements ICustomModelLoader {
    private Map<ResourceLocation, TESRModel> modelMap;

    public TESRModelLoader() {
        modelMap = new HashMap<>();
        ModelLoaderRegistry.registerLoader(this);
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modelMap.containsKey(modelLocation);
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        return modelMap.get(modelLocation);
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {

    }
}
