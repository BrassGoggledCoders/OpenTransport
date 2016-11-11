package xyz.brassgoggledcoders.opentransport.modules.vanilla;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.guiinterfaces.AnvilGuiInterface;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.guiinterfaces.CraftingTableGuiInterface;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.interactions.EnderChestInteraction;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.interactions.JukeBoxInteraction;

@Module(OpenTransport.MODID)
public class VanillaModule extends ModuleBase {
    @Override
    public String getName() {
        return "Vanilla";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        //Vanilla cart blocks
        new BlockWrapperBase(Blocks.CHEST).register();
        new BlockWrapperBase(Blocks.MOB_SPAWNER).register();
        new BlockWrapperBase(Blocks.FURNACE).register();
        new BlockWrapperBase(Blocks.HOPPER).register();
        new BlockWrapperBase(Blocks.COMMAND_BLOCK).register();
        new BlockWrapperBase(Blocks.FURNACE).register();

        //Other Stuff
        new BlockWrapperBase(Blocks.ENDER_CHEST).setClickInteraction(new EnderChestInteraction()).register();
        new BlockWrapperBase(Blocks.JUKEBOX).setClickInteraction(new JukeBoxInteraction()).register();
        new BlockWrapperBase(Blocks.CRAFTING_TABLE).setGuiInterface(new CraftingTableGuiInterface()).register();
        new BlockWrapperBase(Blocks.NOTEBLOCK).register();
        new BlockWrapperBase(Blocks.ANVIL).setGuiInterface(new AnvilGuiInterface()).register();


    }
}
