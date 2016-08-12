package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.BlockIEBase;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockTypes_WoodenDevice0;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.interactions.BlockActivationInteraction;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

public class IEBlockContainers {
	public static void preInit(FMLPreInitializationEvent event) {
		BlockIEBase blockWoodenDevice0 = IEContent.blockWoodenDevice0;
		IBlockContainer woodenStorageCrate = new BlockContainerBase(blockWoodenDevice0).setUnlocalizedName(
				blockWoodenDevice0.getUnlocalizedName() + "." + BlockTypes_WoodenDevice0.CRATE.getName()).setBlockState(
				blockWoodenDevice0.getDefaultState()
						.withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.CRATE))
				.setClickInteraction(new BlockActivationInteraction()).setGuiInterface(new CrateInterface());
		BlockContainerRegistry.registerContainer(woodenStorageCrate);

		IBlockContainer reinforcedWoodenStorageCrate = new BlockContainerBase(blockWoodenDevice0).setUnlocalizedName(
				blockWoodenDevice0.getUnlocalizedName() + "." + BlockTypes_WoodenDevice0.REINFORCED_CRATE.getName())
				.setBlockState(blockWoodenDevice0.getDefaultState()
						.withProperty(blockWoodenDevice0.property, BlockTypes_WoodenDevice0.REINFORCED_CRATE))
				.setClickInteraction(new BlockActivationInteraction()).setGuiInterface(new CrateInterface());
		BlockContainerRegistry.registerContainer(reinforcedWoodenStorageCrate);
	}
}
