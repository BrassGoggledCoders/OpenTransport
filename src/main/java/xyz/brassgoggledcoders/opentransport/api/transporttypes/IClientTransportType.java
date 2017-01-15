package xyz.brassgoggledcoders.opentransport.api.transporttypes;

import net.minecraft.entity.Entity;

public interface IClientTransportType<E extends Entity> extends ITransportType<E> {
    void registerEntityRenderer();

    void registerItemRenderer();
}
