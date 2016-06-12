package xyz.brassgoggledcoders.moarlibs.modules;

import net.minecraftforge.fml.common.Loader;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;

public class IronChestModule extends ModuleBase
{
	public static IBlockContainer[] IRON_CHEST;

	@Override
	public String getName()
	{
		return "Iron Chest";
	}

	@Override
	public boolean areDependenciesMet()
	{
		return Loader.isModLoaded("IronChest");
	}
}
