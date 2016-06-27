package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.interactions.BlockActivationInteraction;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

public class IronChestBlockContainers {
	public static void preInit(FMLPreInitializationEvent event)
	{
		IBlockContainer[] IRON_CHESTS = new IBlockContainer[IronChestType.values().length];
		Block ironChest = IronChest.ironChestBlock;
		for(int i = 0; i < IRON_CHESTS.length; i++)
		{
			IronChestType type = IronChestType.values()[i];
			IRON_CHESTS[i] = new BlockContainerBase(ironChest).setRenderType(RenderType.TESR)
					.setUnlocalizedName(ironChest.getUnlocalizedName() + "." + type.toString().toLowerCase())
					.setBlockState(ironChest.getDefaultState().withProperty(BlockIronChest.VARIANT_PROP, type))
					.setGuiInterface(new IronChestInterface()).setClickInteraction(new BlockActivationInteraction());
			BlockContainerRegistry.registerContainer(IRON_CHESTS[i]);
		}
		IronChestModule.IRON_CHESTS = IRON_CHESTS;
	}
}
