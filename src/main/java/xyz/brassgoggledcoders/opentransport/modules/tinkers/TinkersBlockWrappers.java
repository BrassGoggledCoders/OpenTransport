package xyz.brassgoggledcoders.opentransport.modules.tinkers;

import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockCasting;
import slimeknights.tconstruct.smeltery.block.BlockCasting.CastingType;
import slimeknights.tconstruct.smeltery.block.BlockTank;
import slimeknights.tconstruct.smeltery.block.BlockTank.TankType;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.block.BlockToolTable;
import slimeknights.tconstruct.tools.common.block.BlockToolTable.TableTypes;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.CraftingStationInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PartChestInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PatternChestInterface;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;

public class TinkersBlockWrappers implements IHasWrappers {
    @Override
    public void registerWrappers() {
        new BlockWrapper(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.BASIN).register();
        new BlockWrapper(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.TABLE).register();

        BlockWrapper searedTank = new BlockWrapper(TinkerSmeltery.searedTank).setRenderType(RenderType.COMBO);
        searedTank.register();
        searedTank.withProperty(BlockTank.TYPE, TankType.GAUGE).register();
        searedTank.withProperty(BlockTank.TYPE, TankType.WINDOW).register();

        new BlockWrapper(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PartChest)
                .setGuiInterface(new PartChestInterface()).register();
        new BlockWrapper(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PatternChest)
                .setGuiInterface(new PatternChestInterface()).register();
        new BlockWrapper(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.CraftingStation)
                .setGuiInterface(new CraftingStationInterface()).register();
    }
}
