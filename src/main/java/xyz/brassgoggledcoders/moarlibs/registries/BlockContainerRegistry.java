package xyz.brassgoggledcoders.moarlibs.registries;

import org.apache.commons.lang3.SerializationUtils;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;

import java.util.HashMap;
import java.util.Map;

public class BlockContainerRegistry
{
	static Map<String, IBlockContainer> blockContainerMap = new HashMap<>();

	public static void registerContainer(IBlockContainer blockContainer)
	{
		blockContainerMap.put(blockContainer.getUnlocalizedName(), blockContainer);
	}

	public static IBlockContainer getBlockContainer(String name)
	{
		if(blockContainerMap.containsKey(name))
		{
			return SerializationUtils.clone(blockContainerMap.get(name));
		}
		return null;
	}

	public static Map<String, IBlockContainer> getAllBlockContainers()
	{
		return blockContainerMap;
	}
}
