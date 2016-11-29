package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import com.teamacronymcoders.base.modulesystem.Module;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.modulesystem.ModuleWrapperBase;

import javax.annotation.Nonnull;

@Module(OpenTransport.MODID)
public class IEModule extends ModuleWrapperBase {
    @Override
    public String getName() {
        return "Immersive Engineering";
    }

    @Nonnull
    @Override
    public IHasWrappers getWrapperHolder() {
        return new IEBlockWrappers();
    }
}
