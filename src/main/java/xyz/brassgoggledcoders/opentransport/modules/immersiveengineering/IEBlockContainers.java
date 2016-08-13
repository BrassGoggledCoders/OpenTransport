package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockTypes_WoodenDevice0;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.interactions.BlockActivationInteraction;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

public class IEBlockContainers {
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
	}
}
