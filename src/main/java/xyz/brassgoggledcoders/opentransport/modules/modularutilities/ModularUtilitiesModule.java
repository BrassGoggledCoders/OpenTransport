package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;

import java.util.List;

@Module(OpenTransport.MODID)
public class ModularUtilitiesModule extends ModuleBase {
    @Override
    public String getName() {
        return "Modular Utilities";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency("modularutilities"));
        return dependencies;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MoUBlockWrappers.register();
    }
}
