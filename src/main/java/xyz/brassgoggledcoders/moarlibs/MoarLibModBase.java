package xyz.brassgoggledcoders.moarlibs;

import net.minecraft.creativetab.CreativeTabs;
import xyz.brassgoggledcoders.boilerplate.BoilerplateModBase;
import xyz.brassgoggledcoders.moarlibs.api.IMoarRegister;

public abstract class MoarLibModBase extends BoilerplateModBase
{
	public MoarLibModBase(String modid, String name, String version, CreativeTabs creativeTab)
	{
		super(modid, name, version, creativeTab);
	}

	abstract IMoarRegister getMoarRegister();
}
