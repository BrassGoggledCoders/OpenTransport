package xyz.brassgoggledcoders.opentransport;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import xyz.brassgoggledcoders.opentransport.api.block.ICarriedBlock;

import java.util.List;

import static xyz.brassgoggledcoders.opentransport.OpenTransport.*;

@Mod(modid = ID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
@EventBusSubscriber
public class OpenTransport extends BaseModFoundation<OpenTransport> {
    public static final String ID = "opentransport";
    public static final String NAME = "OpenTransport";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:base@[0.0.0,);";

    private ModuleHandler vehicleHandler;

    @Instance
    public static OpenTransport instance;

    public OpenTransport() {
        super(ID, NAME, VERSION, null, true);
    }

    @EventHandler
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void beforeModuleHandlerInit(FMLPreInitializationEvent event) {
        vehicleHandler = new ModuleHandler("vehicle", this, event.getAsmData());
    }

    @EventHandler
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @EventHandler
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public List<ModuleHandler> getOtherModuleHandlers() {
        return Lists.newArrayList(vehicleHandler);
    }

    @Override
    public OpenTransport getInstance() {
        return this;
    }

    @SubscribeEvent
    public static void buildCarriedBlockRegistry(RegistryEvent.NewRegistry newRegistryEvent) {
        new RegistryBuilder<ICarriedBlock>()
                .setName(new ResourceLocation(ID, "carried_blocks"))
                .setType(ICarriedBlock.class)
                .create();
    }
}
