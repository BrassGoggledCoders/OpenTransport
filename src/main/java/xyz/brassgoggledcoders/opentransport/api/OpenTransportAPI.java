package xyz.brassgoggledcoders.opentransport.api;

import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapperRegistry;

public class OpenTransportAPI {
    private static OpenTransportAPI instance;
    private IModWrapper modWrapper;
    private BlockWrapperRegistry blockWrapperRegistry;

    public OpenTransportAPI(IModWrapper modWrapper) {
        this.modWrapper = modWrapper;
        this.blockWrapperRegistry = new BlockWrapperRegistry();
    }

    public static OpenTransportAPI getInstance() {
        return instance;
    }

    /*
     * Method called from OpenTransport's Mod Constructor
     * ONLY TO BE CALLED BY OPENTRANSPORT
     */
    public static void setInstance(OpenTransportAPI openTransportAPI) {
        if(instance == null) {
            instance = openTransportAPI;
        } else {
            instance.modWrapper.logError("Some other mod tried to set api instance");
        }
    }

    public static IModWrapper getModWrapper() {
        return getInstance().modWrapper;
    }

    public static BlockWrapperRegistry getBlockWrapperRegistry() {
        return getInstance().blockWrapperRegistry;
    }
}
