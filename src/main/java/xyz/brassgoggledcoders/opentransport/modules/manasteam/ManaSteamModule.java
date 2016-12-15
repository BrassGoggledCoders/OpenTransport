package xyz.brassgoggledcoders.opentransport.modules.manasteam;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import xyz.brassgoggledcoders.opentransport.OpenTransport;

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
        dependencies.add(new ModDependency("botania"));
        return dependencies;
    }

    @Override
    public String getClientProxyPath() {
        return "xyz.brassgoggledcoders.opentransport.modules.manasteam.proxies.ClientProxy";
    }
}
