package xyz.brassgoggledcoders.opentransport.vehicle.minecart;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.item.ItemMinecartCarrier;

@Module("vehicle")
public class ModuleMinecart extends ModuleBase {
    @Override
    public String getName() {
        return "minecart";
    }

    @Override
    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {
        itemRegistry.register(new ItemMinecartCarrier());
    }

    @Override
    public void registerEntities(ConfigRegistry configRegistry, EntityRegistry entityRegistry) {
        entityRegistry.register(EntityMinecartCarrier.class);
    }
}
