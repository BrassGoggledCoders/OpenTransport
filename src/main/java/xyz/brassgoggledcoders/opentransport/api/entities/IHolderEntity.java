package xyz.brassgoggledcoders.opentransport.api.entities;

import net.minecraft.entity.Entity;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;

public interface IHolderEntity<T extends Entity>
{
	T getEntity();

	IBlockContainer getBlockContainer();

	void setBlockContainer(IBlockContainer blockContainer);
}
