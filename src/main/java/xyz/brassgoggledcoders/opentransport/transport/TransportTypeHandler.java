package xyz.brassgoggledcoders.opentransport.transport;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

import java.util.List;

public class TransportTypeHandler {
	List<ITransportType> typeList;

	public TransportTypeHandler(FMLPreInitializationEvent event) {
		typeList = ClassLoading.getInstances(event.getAsmData(), TransportType.class, ITransportType.class);
		typeList.forEach(transportType -> transportType.registerItems(BlockContainerRegistry.getAllBlockContainers()));
		typeList.forEach(ITransportType::registerEntities);
	}
}
