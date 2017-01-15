package xyz.brassgoggledcoders.opentransport.modules.manasteam;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import com.teamacronymcoders.base.registry.EntityRegistry;
import com.teamacronymcoders.base.registry.ItemRegistry;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.entities.EntityManaSteamLocomotive;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.items.ItemManaSteamLocomotive;

import java.util.List;

@Module(OpenTransport.MODID)
public class ManaSteamModule extends ModuleBase {
    @Override
    public String getName() {
        return "Mana-Steam Locomotive";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency("railcraft"));
        dependencies.add(new ModDependency("Botania"));
        return dependencies;
    }

    @Override
    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {
        itemRegistry.register(new ItemManaSteamLocomotive());
    }

    @Override
    public void registerEntities(ConfigRegistry configRegistry, EntityRegistry entityRegistry) {
        entityRegistry.register(EntityManaSteamLocomotive.class);
    }

    @Override
    public String getClientProxyPath() {
        return "xyz.brassgoggledcoders.opentransport.modules.manasteam.proxies.ClientProxy";
    }
}
