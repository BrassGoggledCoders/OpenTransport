package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import xyz.brassgoggledcoders.modularutilities.modules.ender.EnderModule;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;

public class MoUBlockWrappers {
    public static void register() {
        new BlockWrapperBase(EnderModule.ender_proxy).register();
    }
}
