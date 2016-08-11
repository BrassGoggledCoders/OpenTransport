package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.ModDependency;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;

import java.util.List;

@Module(mod = OpenTransport.MODID)
public class IronChestModule extends ModuleBase {
	public static IBlockContainer[] IRON_CHESTS;

	@Override
	public String getName() {
		return "Iron Chest";
	}

	@Override
	public List<IDependency> getDependencies(List<IDependency> dependencies) {
		dependencies.add(new ModDependency("ironchest"));
		return dependencies;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		IronChestBlockContainers.preInit(event);
	}
}
