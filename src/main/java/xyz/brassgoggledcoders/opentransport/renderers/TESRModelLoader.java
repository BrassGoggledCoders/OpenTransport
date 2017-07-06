package xyz.brassgoggledcoders.opentransport.renderers;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static xyz.brassgoggledcoders.opentransport.OpenTransport.MODID;

public class TESRModelLoader implements ICustomModelLoader {
    private static Map<ResourceLocation, TESRModel> modelMap = new HashMap<>();

    public TESRModelLoader() {
        ModelLoaderRegistry.registerLoader(this);
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        if (modelLocation.getResourceDomain().equalsIgnoreCase(MODID)) {
            modelLocation = new ResourceLocation(MODID, modelLocation.getResourcePath().replace("models/item/", ""));
        }
        return modelMap.containsKey(modelLocation);
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        if (modelLocation.getResourceDomain().equalsIgnoreCase(MODID)) {
            modelLocation = new ResourceLocation(MODID, modelLocation.getResourcePath().replace("model/item/", ""));
        }
        return modelMap.get(modelLocation);
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {

    }

    public static void addTESRModel(IHasModel item, TESRModel model) {
        item.getResourceLocations(new ArrayList<>()).forEach(resourceLocation -> modelMap.put(resourceLocation, model));
    }
}
