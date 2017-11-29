package xyz.brassgoggledcoders.opentransport.module.vanilla;

import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockBuilder;

import java.util.List;

public class VanillaCarriedBlocks {
    public static List<CarriedBlock> getCarriedBlocks() {
        CarriedBlock chest = CarriedBlockBuilder.create(Blocks.CHEST).build();


        return Lists.newArrayList(chest);
    }
}
