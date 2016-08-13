package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase;
import blusunrize.immersiveengineering.common.blocks.metal.BlockTypes_MetalDevice0;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockTypes_WoodenDevice0;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.interactions.BlockActivationInteraction;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

public class IEBlockContainers {
	@SuppressWarnings("unchecked") //#BlameBlu
	public static void preInit(FMLPreInitializationEvent event) {
		BlockIEBase blockWoodenDevice0 = IEContent.blockWoodenDevice0;
		BlockContainerBase woodenStorageCrate = new BlockContainerBase(blockWoodenDevice0)
				.setUnlocalizedSuffix(BlockTypes_WoodenDevice0.CRATE.getName())
				.withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.CRATE)
				.setClickInteraction(new BlockActivationInteraction()).setGuiInterface(new CrateInterface());
		BlockContainerRegistry.registerContainer(woodenStorageCrate);

		BlockContainerBase reinforcedWoodenStorageCrate =
				woodenStorageCrate.copy().setUnlocalizedSuffix(BlockTypes_WoodenDevice0.REINFORCED_CRATE.getName())
						.withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.REINFORCED_CRATE);
		BlockContainerRegistry.registerContainer(reinforcedWoodenStorageCrate);

		BlockContainerBase woodenBarrel =
				woodenStorageCrate.copy().withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.BARREL)
						.setUnlocalizedSuffix(BlockTypes_WoodenDevice0.BARREL.getName());
		BlockContainerRegistry.registerContainer(woodenBarrel);

		BlockIEBase blockMetalDevice0 = IEContent.blockMetalDevice0;
		for(int i = 0; i < 5; i++) {
			BlockTypes_MetalDevice0 currentDevice = BlockTypes_MetalDevice0.values()[i];
			BlockContainerBase metalDevice0 =
					new BlockContainerBase(blockMetalDevice0).withProperty(blockMetalDevice0.property, currentDevice)
							.setUnlocalizedSuffix(currentDevice.getName());
			BlockContainerRegistry.registerContainer(metalDevice0);
		}
	}
}
