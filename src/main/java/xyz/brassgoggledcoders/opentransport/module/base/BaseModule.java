package xyz.brassgoggledcoders.opentransport.module.base;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.materials.MaterialBuilder;
import com.teamacronymcoders.base.materialsystem.parts.GatherPartsEvent;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import java.awt.Color;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Module(OpenTransport.ID)
@EventBusSubscriber(modid = OpenTransport.ID)
public class BaseModule extends ModuleBase {
    @Override
    public String getName() {
        return "b.a.s.e";
    }

    public void preInit(FMLPreInitializationEvent event)  {
        try {
            Material purple = new MaterialBuilder().setColor(new Color(160, 32, 240)).setName("Purple").build();
            OpenTransport.instance.getMaterialUser().registerPartsForMaterial(purple, "minecart");
        } catch (MaterialException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void registerParts(GatherPartsEvent event) {
        MinecartPartType minecartPartType = new MinecartPartType();
        event.getPartTypeList().add(minecartPartType);
        try {
            Part minecartPart = new PartBuilder()
                    .setName("minecart")
                    .setOwnerId(OpenTransport.ID)
                    .setPartType(minecartPartType)
                    .build();
            event.getPartList().add(minecartPart);
        } catch (MaterialException e) {
            OpenTransport.instance.getLogger().fatal("Couldn't create Minecart part");
        }
    }
}
