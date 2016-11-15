package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import com.teamacronymcoders.base.modulesystem.Module;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.modulesystem.ModuleWrapperBase;

import javax.annotation.Nonnull;

@Module(OpenTransport.MODID)
public class ModularUtilitiesModule extends ModuleWrapperBase {
    @Override
    public String getName() {
        return "Modular Utilities";
    }

    @Nonnull
    @Override
    public IHasWrappers getWrapperHolder() {
        return new MoUBlockWrappers();
    }
}
