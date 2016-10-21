package xyz.brassgoggledcoders.opentransport.registries;

import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;

import java.util.HashMap;
import java.util.Map;

public class BlockWrapperRegistry {
    static Map<String, IBlockWrapper> blockWrapperMap = new HashMap<>();

    public static void registerContainer(IBlockWrapper blockWrapper) {
        blockWrapperMap.put(blockWrapper.getUnlocalizedName(), blockWrapper);
    }

    public static IBlockWrapper getBlockWrapper(String name) {
        if (blockWrapperMap.containsKey(name)) {
            return blockWrapperMap.get(name).copy();
        }
        return null;
    }

    public static Map<String, IBlockWrapper> getAllBlockWrappers() {
        return blockWrapperMap;
    }
}
