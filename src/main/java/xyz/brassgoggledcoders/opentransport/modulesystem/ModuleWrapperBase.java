package xyz.brassgoggledcoders.opentransport.modulesystem;

import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Locale;

public abstract class ModuleWrapperBase extends ModuleBase {
    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency(this.getName().replace(" ", "").toLowerCase(Locale.ENGLISH)));
        return dependencies;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.getWrapperHolder().registerWrappers();
    }

    @Nonnull
    public abstract IHasWrappers getWrapperHolder();
}
