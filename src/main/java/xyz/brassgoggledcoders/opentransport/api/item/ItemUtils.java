package xyz.brassgoggledcoders.opentransport.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class ItemUtils {
    private static ResourceLocation AIR = new ResourceLocation("opentransport", "minecraft_air");

    public static List<ItemStack> getCarrierItemStacksFor(final Item item) {
        return OpenTransportRegistries.CARRIED_BLOCKS.getValues().parallelStream()
                .filter(value -> !AIR.equals(value.getRegistryName()))
                .map(value -> new ItemStack(item, 1, OpenTransportRegistries.CARRIED_BLOCKS.getID(value)))
                .collect(Collectors.toList());
    }
}
