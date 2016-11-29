package xyz.brassgoggledcoders.opentransport.api.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapperRegistry;

public class RegisterBlockWrappersEvent extends Event {
    private BlockWrapperRegistry blockWrapperRegistry;

    public RegisterBlockWrappersEvent(BlockWrapperRegistry blockWrapperRegistry) {
        this.blockWrapperRegistry = blockWrapperRegistry;
    }

    public BlockWrapperRegistry getBlockWrapperRegistry() {
        return this.blockWrapperRegistry;
    }
}
