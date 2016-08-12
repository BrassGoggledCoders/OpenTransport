package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.ModDependency;
import xyz.brassgoggledcoders.opentransport.OpenTransport;

import java.util.List;

@Module(mod=OpenTransport.MODID)
public class IEModule extends ModuleBase {
	@Override
	public String getName() {
		return "Immersive Engineering";
	}

	@Override
	public List<IDependency> getDependencies(List<IDependency> dependencies) {
		dependencies.add(new ModDependency("immersiveengineering"));
		return dependencies;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		IEBlockContainers.preInit(event);
	}
}
