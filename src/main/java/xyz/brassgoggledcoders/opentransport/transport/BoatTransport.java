package xyz.brassgoggledcoders.opentransport.transport;

import net.minecraft.entity.item.EntityBoat;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.entities.boats.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.items.boats.ItemBoatHolder;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.Map;

@TransportType
public class BoatTransport implements ITransportType<EntityBoat> {
	@Override
	@Nonnull
	public String getName() {
		return "Boats";
	}

	@Override
	@Nonnull
	public Class<EntityBoat> getBaseEntity() {
		return EntityBoat.class;
	}

	@Override
	public void registerItems(Map<String, IBlockContainer> blockContainers)
	{
		Iterator<Map.Entry<String, IBlockContainer>> containerIterable = blockContainers.entrySet().iterator();
		while(containerIterable.hasNext())
		{
			IBlockContainer firstContainer = containerIterable.next().getValue();
			IBlockContainer secondContainer = null;
			if(containerIterable.hasNext())
			{
				secondContainer = containerIterable.next().getValue();
			}
			ItemBoatHolder holder = new ItemBoatHolder(firstContainer, secondContainer);
			OpenTransport.INSTANCE.getRegistryHolder().getItemRegistry().registerItem(holder);
		}
	}

	@Override
	public void registerEntities()
	{
		OpenTransport.INSTANCE.getRegistryHolder().getEntityRegistry().registerEntity(EntityBoatHolder.class);
	}
}
