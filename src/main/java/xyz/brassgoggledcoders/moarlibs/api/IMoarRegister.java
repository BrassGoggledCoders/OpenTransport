package xyz.brassgoggledcoders.moarlibs.api;

import java.util.Map;

public interface IMoarRegister
{
	void registerItems(Map<String, IHolderEntity> blockContainers);

	void registerEntities();
}
