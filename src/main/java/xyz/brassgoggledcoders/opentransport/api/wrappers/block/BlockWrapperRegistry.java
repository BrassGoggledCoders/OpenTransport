package xyz.brassgoggledcoders.opentransport.api.wrappers.block;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class BlockWrapperRegistry {
    private Map<String, IBlockWrapper> blockWrapperMap;

    public BlockWrapperRegistry() {
        blockWrapperMap = new HashMap<>();
    }

    public void registerWrapper(IBlockWrapper blockWrapper) {
        blockWrapperMap.put(blockWrapper.getUnlocalizedName(), blockWrapper);
    }

    public IBlockWrapper getBlockWrapper(String name) {
        IBlockWrapper blockWrapper;
        if (blockWrapperMap.containsKey(name)) {
            blockWrapper = blockWrapperMap.get(name).copy();
        } else {
            blockWrapper = blockWrapperMap.entrySet().iterator().next().getValue().copy();
        }
        return blockWrapper;
    }

    public Map<String, IBlockWrapper> getAllBlockWrappers() {
        return blockWrapperMap;
    }

    public IBlockWrapper getLoadedBlockWrapper(ItemStack itemStack) {
        String blockWrapperName = itemStack.getSubCompound("blockWrapper", true).getString("name");
        return OpenTransportAPI.getModWrapper().getLoadedBlockWrapper(blockWrapperName);
    }
}
