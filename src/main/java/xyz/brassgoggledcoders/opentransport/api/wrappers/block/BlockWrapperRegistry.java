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
    private Map<String, IBlockWrapper> loadedBlockWrapperMap;
    private SecureRandom random = new SecureRandom();

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

    public IBlockWrapper getLoadedBlockWrapper(ItemStack itemStack) {
        String loadedId;
        NBTTagCompound tagCompound = itemStack.getSubCompound("blockWrapper", true);
        if(tagCompound.hasKey("loadedId")) {
            loadedId = tagCompound.getString("loadedId");
        } else {
            loadedId = nextNextLoadedId(tagCompound.getString("name"));
            tagCompound.setString("loadedId", loadedId);
        }

        IBlockWrapper blockWrapper;
        if(loadedBlockWrapperMap.containsKey(loadedId)) {
            blockWrapper = loadedBlockWrapperMap.get(loadedId);
        } else {
            blockWrapper = this.getBlockWrapper(loadedId.substring(0, loadedId.indexOf(".") - 1));
            OpenTransportAPI.getModWrapper().setWorldHarness(blockWrapper);
            loadedBlockWrapperMap.put(loadedId, blockWrapper);
        }

        return blockWrapper;
    }

    public String nextNextLoadedId(String blockWrapperName) {
        return blockWrapperName + "." + new BigInteger(130, random).toString(32);
    }
}
