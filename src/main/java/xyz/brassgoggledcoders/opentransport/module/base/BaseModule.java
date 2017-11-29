package xyz.brassgoggledcoders.opentransport.module.base;

import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.parttype.MinecartPartType;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;

@Module(OpenTransport.MODID)
public class BaseModule extends ModuleBase {
    @Override
    public String getName() {
        return "b.a.s.e";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)  {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCustomMinecarts(RegistryEvent.Register<CustomMinecart> customMinecartRegisterEvent) {
        IForgeRegistry<CustomMinecart> registry = customMinecartRegisterEvent.getRegistry();

        MaterialSystem.getMaterialParts().values().stream()
                .filter(materialPart -> materialPart.getPartType() instanceof MinecartPartType)
                .map(MaterialCustomMinecart::new)
                .forEach(registry::register);
    }
}
