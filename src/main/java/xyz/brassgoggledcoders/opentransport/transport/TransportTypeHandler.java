package xyz.brassgoggledcoders.opentransport.transport;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

import java.util.List;

public class TransportTypeHandler {
	private List<ITransportType> transportTypes;

	public TransportTypeHandler(FMLPreInitializationEvent event) {
		transportTypes = ClassLoading.getInstances(event.getAsmData(), TransportType.class, ITransportType.class);

		for(ITransportType transportType: this.getTransportTypes()) {
			this.getConfig().addEntry(transportType.getName(), new TransportTypeEntry(transportType));
			transportType.setIsActive(this.getConfig().getBoolean(transportType.getName(), true));
			if(transportType.getIsActive()) {
				transportType.registerItems(BlockContainerRegistry.getAllBlockContainers());
				transportType.registerEntities();
			}
		}
	}

	public List<ITransportType> getTransportTypes() {
		return transportTypes;
	}

	private ConfigRegistry getConfig() {
		return OpenTransport.INSTANCE.getRegistryHolder().getConfigRegistry();
	}

	private static class TransportTypeEntry extends ConfigEntry {
		public TransportTypeEntry(ITransportType transportType) {
			super("Transport Types", transportType.getName() + " enabled", Type.BOOLEAN, true + "");
		}
	}

}
