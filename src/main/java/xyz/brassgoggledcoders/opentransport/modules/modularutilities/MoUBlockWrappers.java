package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.modularutilities.modules.ender.EnderModule;
import xyz.brassgoggledcoders.opentransport.api.events.RegisterBlockWrappersEvent;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;

public class MoUBlockWrappers {
    @SubscribeEvent
    public void registerWrappers(RegisterBlockWrappersEvent event) {
        new BlockWrapper(EnderModule.ender_proxy).register();
    }
}
