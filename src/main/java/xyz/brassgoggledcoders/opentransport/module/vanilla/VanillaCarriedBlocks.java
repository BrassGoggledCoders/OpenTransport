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
        CarriedBlock noteBlock = CarriedBlockBuilder.create(Blocks.NOTEBLOCK).build();
        CarriedBlock craftingTable = CarriedBlockBuilder.create(Blocks.CRAFTING_TABLE).build();
        CarriedBlock furnace = CarriedBlockBuilder.create(Blocks.FURNACE).build();
        CarriedBlock cake = CarriedBlockBuilder.create(Blocks.CAKE).build();

        return Lists.newArrayList(chest, noteBlock, craftingTable, furnace, cake);
    }
}
