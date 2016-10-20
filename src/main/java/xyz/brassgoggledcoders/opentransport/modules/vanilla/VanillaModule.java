package xyz.brassgoggledcoders.opentransport.modules.vanilla;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

@Module(OpenTransport.MODID)
public class VanillaModule extends ModuleBase {
	public static IBlockContainer ENDER_CHEST;

	@Override
	public String getName() {
		return "Vanilla";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ENDER_CHEST = new BlockContainerBase(Blocks.ENDER_CHEST).setClickInteraction(new EnderChestInteraction());
		BlockContainerRegistry.registerContainer(ENDER_CHEST);
	}
}
