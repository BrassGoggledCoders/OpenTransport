package xyz.brassgoggledcoders.opentransport.vehicle.minecart;

import com.teamacronymcoders.base.creativetabs.CreativeTabBase;
import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.DefaultMinecart;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.item.ItemMinecartCarrier;

import static xyz.brassgoggledcoders.opentransport.OpenTransport.ID;

@Module("vehicle")
@EventBusSubscriber(modid = ID)
public class ModuleMinecart extends ModuleBase {
    public static final CreativeTabs MINECART_CARRIERS = new CreativeTabBase("carrier.minecart",
            () -> new ItemStack((Items.MINECART)));

    @Override
    public String getName() {
        return "minecart";
    }

    @Override
    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {
        itemRegistry.register(new ItemMinecartCarrier().setCreativeTab(MINECART_CARRIERS));
    }

    @Override
    public void registerEntities(ConfigRegistry configRegistry, EntityRegistry entityRegistry) {
        entityRegistry.register(EntityMinecartCarrier.class);
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public static void buildCustomMinecartRegistry(RegistryEvent.NewRegistry newRegistryEvent) {
        new RegistryBuilder<CustomMinecart>()
                .setName(new ResourceLocation(ID, "custom_minecart"))
                .setType(CustomMinecart.class)
                .setDefaultKey(new ResourceLocation(ID,"minecart"))
                .create();
    }

    @SubscribeEvent
    public static void registerCustomMinecarts(RegistryEvent.Register<CustomMinecart> customMinecartRegistryEvent) {
        customMinecartRegistryEvent.getRegistry().register(new DefaultMinecart());
    }
}
