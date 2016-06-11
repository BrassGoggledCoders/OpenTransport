package xyz.brassgoggledcoders.moarlibs;

import net.minecraft.creativetab.CreativeTabs;
import xyz.brassgoggledcoders.boilerplate.BoilerplateModBase;
import xyz.brassgoggledcoders.moarlibs.api.IMoarRegister;
import xyz.brassgoggledcoders.moarlibs.registries.BlockContainerRegistry;

public abstract class MoarLibModBase extends BoilerplateModBase
{
	public MoarLibModBase(String modid, String name, String version, CreativeTabs creativeTab)
	{
		super(modid, name, version, creativeTab);
	}

	@Override
	protected void afterModuleConstruct()
	{
		this.getMoarRegister().registerItems(BlockContainerRegistry.getAllBlockContainers());
		this.getMoarRegister().registerEntities();
	}

	public abstract IMoarRegister getMoarRegister();
}
