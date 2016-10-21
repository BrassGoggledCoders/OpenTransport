package xyz.brassgoggledcoders.opentransport.registries;

import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;

import java.util.HashMap;
import java.util.Map;

public class BlockContainerRegistry {
    static Map<String, IBlockWrapper> blockContainerMap = new HashMap<>();

    public static void registerContainer(IBlockWrapper blockContainer) {
        blockContainerMap.put(blockContainer.getUnlocalizedName(), blockContainer);
    }

    public static IBlockWrapper getBlockContainer(String name) {
        if (blockContainerMap.containsKey(name)) {
            return blockContainerMap.get(name).copy();
        }
        return null;
    }

    public static Map<String, IBlockWrapper> getAllBlockContainers() {
        return blockContainerMap;
    }
}
