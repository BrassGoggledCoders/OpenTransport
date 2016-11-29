package xyz.brassgoggledcoders.opentransport.modules.tinkers;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.modulesystem.ModuleWrapperBase;

import javax.annotation.Nonnull;
import java.util.List;

@Module(OpenTransport.MODID)
public class TinkersModule extends ModuleWrapperBase {
    @Override
    public String getName() {
        return "Tinkers' Construct";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency("tconstruct"));
        return dependencies;
    }

    @Nonnull
    @Override
    public IHasWrappers getWrapperHolder() {
        return new TinkersBlockWrappers();
    }
}
