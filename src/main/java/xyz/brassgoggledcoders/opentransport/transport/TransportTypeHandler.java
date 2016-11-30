package xyz.brassgoggledcoders.opentransport.transport;

import com.teamacronymcoders.base.registry.config.ConfigEntry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;

import java.util.List;

public class TransportTypeHandler {
    private List<ITransportType> transportTypes;

    public TransportTypeHandler(FMLPreInitializationEvent event) {
        transportTypes = ClassLoading.getInstances(event.getAsmData(), TransportType.class, ITransportType.class);

        transportTypes.forEach(transportType -> {
            this.getConfig().addEntry(transportType.getName(), new TransportTypeEntry(transportType));
            transportType.setIsActive(this.getConfig().getBoolean(transportType.getName(), true));
        });
    }

    public void registerItemsAndEntities() {
        transportTypes.stream().filter(ITransportType::getIsActive).forEach(transportType -> {
            transportType.registerItems(OpenTransportAPI.getBlockWrapperRegistry().getAllBlockWrappers());
            transportType.registerEntities();
        });
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
