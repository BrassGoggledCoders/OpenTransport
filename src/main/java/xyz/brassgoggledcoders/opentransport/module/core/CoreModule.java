package xyz.brassgoggledcoders.opentransport.module.core;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.modulesystem.Module;
import net.minecraft.init.Blocks;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockBuilder;
import xyz.brassgoggledcoders.opentransport.module.OTModuleBase;

import java.util.List;

@Module(OpenTransport.MODID)
public class CoreModule extends OTModuleBase {
    @Override
    public String getName() {
        return "Core";
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public List<CarriedBlock> getCarriedBlocks() {
        return Lists.newArrayList(CarriedBlockBuilder.create(Blocks.AIR).build());
    }
}
