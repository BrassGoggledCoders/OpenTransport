package xyz.brassgoggledcoders.opentransport.transport;

import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.events.RegisterBlockWrappersEvent;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;

import java.util.List;

public class TransportTypeHandler {
    private List<ITransportType> transportTypes;

    public TransportTypeHandler(FMLPreInitializationEvent event) {
        transportTypes = OpenTransport.proxy.getTransportTypes(event.getAsmData());

        transportTypes.forEach(transportType -> {
            this.getConfig().addEntry(transportType.getName(), new TransportTypeEntry(transportType));
            transportType.setIsActive(this.getConfig().getBoolean(transportType.getName(), true));
        });
    }

    public void registerItemsAndEntities() {
        MinecraftForge.EVENT_BUS.post(new RegisterBlockWrappersEvent(OpenTransportAPI.getBlockWrapperRegistry()));
        transportTypes.stream().filter(ITransportType::getIsActive).forEach(transportType -> {
            transportType.registerItems(OpenTransportAPI.getBlockWrapperRegistry().getAllBlockWrappers());
            transportType.registerEntities();
        });
        OpenTransport.proxy.registerRenderers(transportTypes);

    }

    public List<ITransportType> getTransportTypes() {
        return transportTypes;
    }

    private ConfigRegistry getConfig() {
        return OpenTransport.instance.getRegistry(ConfigRegistry.class, "CONFIG");
    }

    private static class TransportTypeEntry extends ConfigEntry {
        public TransportTypeEntry(ITransportType transportType) {
            super("Transport Types", transportType.getName() + " enabled", Property.Type.BOOLEAN, Boolean.toString(true));
        }
    }

}
