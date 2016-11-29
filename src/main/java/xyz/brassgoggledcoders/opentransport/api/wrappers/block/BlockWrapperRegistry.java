package xyz.brassgoggledcoders.opentransport.api.wrappers.block;

import java.util.HashMap;
import java.util.Map;

public class BlockWrapperRegistry {
    Map<String, IBlockWrapper> blockWrapperMap;

    public BlockWrapperRegistry() {
        blockWrapperMap = new HashMap<>();
    }

    public void registerWrapper(IBlockWrapper blockWrapper) {
        blockWrapperMap.put(blockWrapper.getUnlocalizedName(), blockWrapper);
    }

    public IBlockWrapper getBlockWrapper(String name) {
        if (blockWrapperMap.containsKey(name)) {
            return blockWrapperMap.get(name).copy();
        }
        return null;
    }

    public Map<String, IBlockWrapper> getAllBlockWrappers() {
        return blockWrapperMap;
    }
}
