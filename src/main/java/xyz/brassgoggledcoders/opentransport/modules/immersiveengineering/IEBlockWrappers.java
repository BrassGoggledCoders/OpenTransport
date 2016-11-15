package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase;
import blusunrize.immersiveengineering.common.blocks.metal.BlockTypes_MetalDevice0;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockTypes_WoodenDevice0;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;

public class IEBlockWrappers {
    @SuppressWarnings("unchecked") //#BlameBlu
    public static void preInit(FMLPreInitializationEvent event) {
        BlockIEBase blockWoodenDevice0 = IEContent.blockWoodenDevice0;
        BlockWrapperBase woodenStorageCrate = new BlockWrapperBase(blockWoodenDevice0)
                .setUnlocalizedSuffix(BlockTypes_WoodenDevice0.CRATE.getName())
                .withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.CRATE)
                .setGuiInterface(new CrateInterface());
        woodenStorageCrate.register();

        woodenStorageCrate.copy().setUnlocalizedSuffix(BlockTypes_WoodenDevice0.REINFORCED_CRATE.getName())
                .withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.REINFORCED_CRATE).register();

        woodenStorageCrate.copy().withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.BARREL)
                .setUnlocalizedSuffix(BlockTypes_WoodenDevice0.BARREL.getName()).register();

        BlockIEBase blockMetalDevice0 = IEContent.blockMetalDevice0;
        for (int i = 0; i < 5; i++) {
            BlockTypes_MetalDevice0 currentDevice = BlockTypes_MetalDevice0.values()[i];
            new BlockWrapperBase(blockMetalDevice0).withProperty(blockMetalDevice0.property, currentDevice)
                    .setUnlocalizedSuffix(currentDevice.getName()).register();
        }
    }
}
