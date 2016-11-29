package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase;
import blusunrize.immersiveengineering.common.blocks.metal.BlockTypes_MetalDevice0;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockTypes_WoodenDevice0;
import net.minecraft.block.properties.PropertyEnum;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.modules.immersiveengineering.interfaces.CrateInterface;

public class IEBlockWrappers implements IHasWrappers {
    @Override
    @SuppressWarnings("unchecked")//#BlameBlu
    public void registerWrappers() {
        BlockIEBase blockWoodenDevice0 = IEContent.blockWoodenDevice0;
        PropertyEnum woodenType = blockWoodenDevice0.property;
        BlockWrapper woodenDevice = new BlockWrapper(blockWoodenDevice0);
        woodenDevice.copy().withProperty(woodenType, BlockTypes_WoodenDevice0.CRATE).setGuiInterface(new CrateInterface()).register();
        woodenDevice.copy().withProperty(woodenType, BlockTypes_WoodenDevice0.REINFORCED_CRATE).setGuiInterface(new CrateInterface()).register();
        woodenDevice.copy().withProperty(woodenType, BlockTypes_WoodenDevice0.BARREL).register();
        //TODO: Explosive Blocks
        //woodenDevice.copy().withProperty(woodenType, BlockTypes_WoodenDevice0.GUNPOWDER_BARREL).register();

        BlockIEBase blockMetalDevice0 = IEContent.blockMetalDevice0;
        for (int i = 0; i < 5; i++) {
            BlockTypes_MetalDevice0 currentDevice = BlockTypes_MetalDevice0.values()[i];
            new BlockWrapper(blockMetalDevice0).withProperty(blockMetalDevice0.property, currentDevice).register();
        }
    }
}
