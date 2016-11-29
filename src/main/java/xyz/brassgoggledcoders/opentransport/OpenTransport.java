package xyz.brassgoggledcoders.opentransport;

import com.teamacronymcoders.base.BaseModFoundation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.network.HolderUpdatePacket;
import xyz.brassgoggledcoders.opentransport.proxies.CommonProxy;
import xyz.brassgoggledcoders.opentransport.transport.TransportTypeHandler;

@Mod(modid = OpenTransport.MODID, name = OpenTransport.MODNAME, version = OpenTransport.VERSION,
        dependencies = OpenTransport.DEPENDENCIES)
public class OpenTransport extends BaseModFoundation<OpenTransport> {
    public static final String MODID = "opentransport";
    public static final String MODNAME = "OpenTransport";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "after:ironchest;after:immersiveengineering;required-after:base@[0.0.0,);" +
            "after:modularutilities;";

    @Instance(OpenTransport.MODID)
    public static OpenTransport instance;

    @SidedProxy(clientSide = "xyz.brassgoggledcoders.opentransport.proxies.ClientProxy",
            serverSide = "xyz.brassgoggledcoders.opentransport.proxies.CommonProxy")
    public static CommonProxy proxy;

    public static TransportTypeHandler transportTypeHandler;

    public OpenTransport() {
        super(MODID, MODNAME, VERSION, CreativeTabs.MISC);
        OpenTransportAPI.setInstance(new OpenTransportAPI(new ModWrapper()));
    }

    @EventHandler
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void modPreInit(FMLPreInitializationEvent event) {
        transportTypeHandler = new TransportTypeHandler(event);
        proxy.registerEntityRenders();
    }

    @EventHandler
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        this.getPacketHandler().registerPacket(HolderUpdatePacket.Handler.class, HolderUpdatePacket.class, Side.CLIENT);
    }

    @EventHandler
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public OpenTransport getInstance() {
        return instance;
    }
}
