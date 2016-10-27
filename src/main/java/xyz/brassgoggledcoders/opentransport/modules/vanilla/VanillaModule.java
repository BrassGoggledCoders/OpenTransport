package xyz.brassgoggledcoders.opentransport.modules.vanilla;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.interactions.BlockActivationInteraction;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.containers.CraftingTableGuiInterface;

@Module(OpenTransport.MODID)
public class VanillaModule extends ModuleBase {
    @Override
    public String getName() {
        return "Vanilla";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        new BlockWrapperBase(Blocks.ENDER_CHEST).setClickInteraction(new EnderChestInteraction()).register();
        new BlockWrapperBase(Blocks.JUKEBOX).setClickInteraction(new BlockActivationInteraction()).register();
        new BlockWrapperBase(Blocks.CRAFTING_TABLE).setClickInteraction(new BlockActivationInteraction())
                .setGuiInterface(new CraftingTableGuiInterface()).register();
        new BlockWrapperBase(Blocks.NOTEBLOCK).setClickInteraction(new BlockActivationInteraction()).register();
    }
}
