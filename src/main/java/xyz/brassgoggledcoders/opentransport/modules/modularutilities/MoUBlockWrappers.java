package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import xyz.brassgoggledcoders.modularutilities.modules.ender.EnderModule;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;

public class MoUBlockWrappers implements IHasWrappers {
    @Override
    public void registerWrappers() {
        new BlockWrapperBase(EnderModule.ender_proxy).register();
    }
}
