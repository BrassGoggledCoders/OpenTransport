package xyz.brassgoggledcoders.opentransport.modules;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.interactions.EnderChestInteraction;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

@Module(mod = OpenTransport.MODID)
public class VanillaModule extends ModuleBase
{
	public static IBlockContainer ENDER_CHEST;

	@Override
	public String getName()
	{
		return "Vanilla";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ENDER_CHEST = new BlockContainerBase(Blocks.ENDER_CHEST).setClickInteraction(new EnderChestInteraction());
		BlockContainerRegistry.registerContainer(ENDER_CHEST);
	}
}
