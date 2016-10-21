package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;

import java.util.List;

@Module(OpenTransport.MODID)
public class IronChestModule extends ModuleBase {
	public static IBlockWrapper[] IRON_CHESTS;

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
