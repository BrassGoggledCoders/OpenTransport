package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import xyz.brassgoggledcoders.modularutilities.modules.ender.EnderModule;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;

public class MoUBlockWrappers implements IHasWrappers {
    @Override
    public void registerWrappers() {
        new BlockWrapper(EnderModule.ender_proxy).register();
    }
}
