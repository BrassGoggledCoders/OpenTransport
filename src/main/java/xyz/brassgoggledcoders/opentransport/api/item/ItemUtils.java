package xyz.brassgoggledcoders.opentransport.api.item;

import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class ItemUtils {
    public static List<ItemStack> getCarrierItemStacksFor(Item item) {
        return OpenTransportRegistries.CARRIED_BLOCKS.getValues().parallelStream()
                .map(TextUtils::getRegistryLocation)
                .map(value -> {
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setString(OpenTransportAPI.CARRIED_BLOCK_NBT_NAME, value);
                    return compound;
                })
                .map(value -> new ItemStack(item, 1, 1, value))
                .collect(Collectors.toList());
    }
}
