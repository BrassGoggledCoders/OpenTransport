package xyz.brassgoggledcoders.opentransport.modules.modularutilities;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.opentransport.api.events.RegisterBlockWrappersEvent;

public class MoUBlockWrappers {
    @SubscribeEvent
    public void registerWrappers(RegisterBlockWrappersEvent event) {
        //new BlockWrapper(EnderModule.ender_proxy).register();
    }
}
