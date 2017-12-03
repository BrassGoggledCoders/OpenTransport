package xyz.brassgoggledcoders.opentransport.api;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;

public class OpenTransportRegistries {
    public static final IForgeRegistry<CarriedBlock> CARRIED_BLOCKS = GameRegistry.findRegistry(CarriedBlock.class);
    public static final IForgeRegistry<CustomMinecart> CUSTOM_MINECARTS = GameRegistry.findRegistry(CustomMinecart.class);
}
