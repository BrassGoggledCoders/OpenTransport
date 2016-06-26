package xyz.brassgoggledcoders.opentransport.api.transporttypes;

import net.minecraft.entity.Entity;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;

import javax.annotation.Nonnull;
import java.util.Map;

public interface ITransportType<E extends Entity>
{
	@Nonnull
	String getName();

	@Nonnull
	Class<E> getBaseEntity();

	void registerItems(Map<String, IBlockContainer> blockContainers);

	void registerEntities();
}
