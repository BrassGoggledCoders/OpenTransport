package xyz.brassgoggledcoders.opentransport.modules.tinkers;

import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockCasting;
import slimeknights.tconstruct.smeltery.block.BlockCasting.CastingType;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.block.BlockToolTable;
import slimeknights.tconstruct.tools.common.block.BlockToolTable.TableTypes;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PartChestInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PatternChestInterface;

public class TinkersBlockWrappers implements IHasWrappers {
    @Override
    public void registerWrappers() {
        new BlockWrapperBase(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.BASIN).register();
        new BlockWrapperBase(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.TABLE).register();

        new BlockWrapperBase(TinkerSmeltery.searedTank).register();

        new BlockWrapperBase(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PartChest)
                .setGuiInterface(new PartChestInterface()).register();
        new BlockWrapperBase(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PatternChest)
                .setGuiInterface(new PatternChestInterface()).register();
    }
}
