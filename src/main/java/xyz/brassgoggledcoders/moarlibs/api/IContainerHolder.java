package xyz.brassgoggledcoders.moarlibs.api;

import net.minecraft.entity.Entity;

public interface IContainerHolder<T extends Entity>
{
	T getEntity();

	IBlockContainer getBlockContainer();

	void setBlockContainer(IBlockContainer blockContainer);
}
