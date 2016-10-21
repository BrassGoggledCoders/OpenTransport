package xyz.brassgoggledcoders.opentransport.modules.vanilla;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.registries.BlockWrapperRegistry;

@Module(OpenTransport.MODID)
public class VanillaModule extends ModuleBase {
    public static IBlockWrapper ENDER_CHEST;

    @Override
    public String getName() {
        return "Vanilla";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ENDER_CHEST = new BlockWrapperBase(Blocks.ENDER_CHEST).setClickInteraction(new EnderChestInteraction());
        BlockWrapperRegistry.registerContainer(ENDER_CHEST);
    }
}
