package xyz.brassgoggledcoders.opentransport.api;

import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;
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

    public static void setInstance(OpenTransportAPI openTransportAPI) {
        if(instance == null) {
            instance = openTransportAPI;
        } else {
            throw new ActionNotSupportedException("Something else attempted to set the API Object after creation");
        }
    }

    public static IModWrapper getModWrapper() {
        return getInstance().modWrapper;
    }

    public static BlockWrapperRegistry getBlockWrapperRegistry() {
        return getInstance().blockWrapperRegistry;
    }
}
