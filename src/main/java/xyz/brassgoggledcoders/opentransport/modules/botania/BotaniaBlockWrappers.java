package xyz.brassgoggledcoders.opentransport.modules.botania;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.api.state.enums.AltarVariant;
import vazkii.botania.api.state.enums.DrumVariant;
import vazkii.botania.api.state.enums.PoolVariant;
import vazkii.botania.common.block.ModBlocks;
import xyz.brassgoggledcoders.opentransport.api.events.RegisterBlockWrappersEvent;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;

public class BotaniaBlockWrappers {
    @SubscribeEvent
    public void registerBlockWrappers(RegisterBlockWrappersEvent event) {
        BlockWrapper manaPool = new BlockWrapper(ModBlocks.pool).setRenderType(RenderType.COMBO);
        for(PoolVariant variant: BotaniaStateProps.POOL_VARIANT.getAllowedValues()) {
            manaPool.copy().withProperty(BotaniaStateProps.POOL_VARIANT, variant).register();
        }

        BlockWrapper drum = new BlockWrapper(ModBlocks.forestDrum);
        for(DrumVariant variant: BotaniaStateProps.DRUM_VARIANT.getAllowedValues()) {
            drum.copy().withProperty(BotaniaStateProps.DRUM_VARIANT, variant).register();
        }

        BlockWrapper pedalApothecary = new BlockWrapper(ModBlocks.altar).setRenderType(RenderType.VMC);
        for(AltarVariant variant: BotaniaStateProps.ALTAR_VARIANT.getAllowedValues()) {
            pedalApothecary.copy().withProperty(BotaniaStateProps.ALTAR_VARIANT, variant).register();
        }

        new BlockWrapper(ModBlocks.tinyPotato).setRenderType(RenderType.COMBO).register();
        new BlockWrapper(ModBlocks.teruTeruBozu).setRenderType(RenderType.TESR).register();
    }
}
