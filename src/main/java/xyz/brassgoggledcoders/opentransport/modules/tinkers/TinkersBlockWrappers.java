package xyz.brassgoggledcoders.opentransport.modules.tinkers;

import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockCasting;
import slimeknights.tconstruct.smeltery.block.BlockCasting.CastingType;
import slimeknights.tconstruct.smeltery.block.BlockTank;
import slimeknights.tconstruct.smeltery.block.BlockTank.TankType;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.block.BlockToolTable;
import slimeknights.tconstruct.tools.common.block.BlockToolTable.TableTypes;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.CraftingStationInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PartChestInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PatternChestInterface;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

public class TinkersBlockWrappers implements IHasWrappers {
    @Override
    public void registerWrappers() {
        new BlockWrapperBase(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.BASIN).register();
        new BlockWrapperBase(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.TABLE).register();

        BlockWrapperBase searedTank = new BlockWrapperBase(TinkerSmeltery.searedTank).setRenderType(RenderType.COMBO);
        searedTank.register();
        searedTank.withProperty(BlockTank.TYPE, TankType.GAUGE).register();
        searedTank.withProperty(BlockTank.TYPE, TankType.WINDOW).register();

        new BlockWrapperBase(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PartChest)
                .setGuiInterface(new PartChestInterface()).register();
        new BlockWrapperBase(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PatternChest)
                .setGuiInterface(new PatternChestInterface()).register();
        new BlockWrapperBase(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.CraftingStation)
                .setGuiInterface(new CraftingStationInterface()).register();
    }
}
