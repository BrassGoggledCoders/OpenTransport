package xyz.brassgoggledcoders.moarlibs.modules;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;
import xyz.brassgoggledcoders.moarlibs.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.moarlibs.interactions.EnderChestInteraction;

@Module
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
	}
}
