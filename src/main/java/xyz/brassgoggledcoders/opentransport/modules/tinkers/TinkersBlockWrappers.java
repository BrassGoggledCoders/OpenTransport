package xyz.brassgoggledcoders.opentransport.modules.tinkers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockCasting;
import slimeknights.tconstruct.smeltery.block.BlockCasting.CastingType;
import slimeknights.tconstruct.smeltery.block.BlockTank;
import slimeknights.tconstruct.smeltery.block.BlockTank.TankType;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.block.BlockToolTable;
import slimeknights.tconstruct.tools.common.block.BlockToolTable.TableTypes;
import xyz.brassgoggledcoders.opentransport.api.events.RegisterBlockWrappersEvent;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.CraftingStationInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PartChestInterface;
import xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces.PatternChestInterface;

public class TinkersBlockWrappers {
    @SubscribeEvent
    public void registerWrappers(RegisterBlockWrappersEvent event) {
        new BlockWrapper(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.BASIN).register();
        new BlockWrapper(TinkerSmeltery.castingBlock).withProperty(BlockCasting.TYPE, CastingType.TABLE).register();

        BlockWrapper searedTank = new BlockWrapper(TinkerSmeltery.searedTank).setRenderType(RenderType.COMBO);
        searedTank.register();
        searedTank.copy().withProperty(BlockTank.TYPE, TankType.GAUGE).register();
        searedTank.copy().withProperty(BlockTank.TYPE, TankType.WINDOW).register();

        new BlockWrapper(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PartChest)
                .setGuiInterface(new PartChestInterface()).register();
        new BlockWrapper(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.PatternChest)
                .setGuiInterface(new PatternChestInterface()).register();
        new BlockWrapper(TinkerTools.toolTables).withProperty(BlockToolTable.TABLES, TableTypes.CraftingStation)
                .setGuiInterface(new CraftingStationInterface()).register();
    }
}
