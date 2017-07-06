package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModModuleDependency;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

//TODO: Bring back MoU @Module(OpenTransport.MODID)
public class ModularUtilitiesModule extends ModuleBase {
    @Override
    public String getName() {
        return "Modular Utilities";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModModuleDependency("modularutilities", "Ender"));
        return dependencies;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new MoUBlockWrappers());
    }
}
