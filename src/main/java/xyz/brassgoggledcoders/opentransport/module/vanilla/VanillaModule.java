package xyz.brassgoggledcoders.opentransport.module.vanilla;

import com.teamacronymcoders.base.modulesystem.Module;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.module.OTModuleBase;

import java.util.List;

@Module(OpenTransport.MODID)
public class VanillaModule extends OTModuleBase {
    @Override
    public String getName() {
        return "Vanilla";
    }

    @Override
    public List<CarriedBlock> getCarriedBlocks() {
        return VanillaCarriedBlocks.getCarriedBlocks();
    }
}
