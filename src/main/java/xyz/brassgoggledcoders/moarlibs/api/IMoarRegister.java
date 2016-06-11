package xyz.brassgoggledcoders.moarlibs.api;

import java.util.Map;

public interface IMoarRegister
{
	void registerItems(Map<String, IBlockContainer> blockContainers);

	void registerEntities();
}
