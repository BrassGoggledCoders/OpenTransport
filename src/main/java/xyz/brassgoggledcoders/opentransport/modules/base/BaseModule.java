package xyz.brassgoggledcoders.opentransport.modules.base;


import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.parts.GatherPartsEvent;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.base.materialsystem.MinecartPartType;

import static xyz.brassgoggledcoders.opentransport.OpenTransport.MODID;

@Module(MODID)
@EventBusSubscriber
public class BaseModule extends ModuleBase {
    public static MinecartPartType minecartPartType;

    @Override
    public String getName() {
        return "B.A.S.E";
    }

    @Override
    public String getClientProxyPath() {
        return "xyz.brassgoggledcoders.opentransport.modules.base.proxies.ClientProxy";
    }

    @SubscribeEvent
    public static void addPart(GatherPartsEvent gatherPartsEvent) {
        minecartPartType = new MinecartPartType();
        gatherPartsEvent.addPartType(minecartPartType);
        try {
            gatherPartsEvent.addPart(new PartBuilder().setName("minecart").setPartType(minecartPartType).build());
        } catch (MaterialException e) {
            OpenTransport.instance.getLogger().getLogger().error(e);
        }
    }
}
