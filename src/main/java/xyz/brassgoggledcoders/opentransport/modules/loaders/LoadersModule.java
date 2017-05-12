package xyz.brassgoggledcoders.opentransport.modules.loaders;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import xyz.brassgoggledcoders.opentransport.modules.loaders.blocks.BlockLoaderBase;
import xyz.brassgoggledcoders.opentransport.modules.loaders.tiles.TileEnergyLoader;
import xyz.brassgoggledcoders.opentransport.modules.loaders.tiles.TileInventoryLoader;

import static xyz.brassgoggledcoders.opentransport.OpenTransport.MODID;

@Module(MODID)
public class LoadersModule extends ModuleBase {
    @Override
    public String getName() {
        return "Loaders";
    }

    @Override
    public void registerBlocks(ConfigRegistry configRegistry, BlockRegistry blockRegistry) {
        blockRegistry.register(new BlockLoaderBase<>("inventory", TileInventoryLoader::new));
        blockRegistry.register(new BlockLoaderBase<>("energy", TileEnergyLoader::new));
    }
}
