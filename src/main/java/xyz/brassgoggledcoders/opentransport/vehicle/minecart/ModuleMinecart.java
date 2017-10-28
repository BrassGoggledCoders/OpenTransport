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
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.item.ItemMinecartCarrier;

@Module("vehicle")
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
}
