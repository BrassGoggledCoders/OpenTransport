package xyz.brassgoggledcoders.opentransport.api;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;

public class OpenTransportRegistries {
    public static final ForgeRegistry<CarriedBlock> CARRIED_BLOCKS =
            (ForgeRegistry<CarriedBlock>)GameRegistry.findRegistry(CarriedBlock.class);

    public static final IForgeRegistry<CustomMinecart> CUSTOM_MINECARTS = GameRegistry.findRegistry(CustomMinecart.class);
}
