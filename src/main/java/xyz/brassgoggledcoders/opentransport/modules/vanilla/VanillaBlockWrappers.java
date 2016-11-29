package xyz.brassgoggledcoders.opentransport.modules.vanilla;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.opentransport.api.events.RegisterBlockWrappersEvent;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.guiinterfaces.*;
import xyz.brassgoggledcoders.opentransport.modules.vanilla.interactions.JukeBoxAction;

public class VanillaBlockWrappers {
    @SubscribeEvent
    public void registerWrappers(RegisterBlockWrappersEvent event) {
        //Vanilla cart blocks
        new BlockWrapper(Blocks.CHEST).setGuiInterface(new ChestGuiInterface()).register();
        new BlockWrapper(Blocks.MOB_SPAWNER).register();
        new BlockWrapper(Blocks.FURNACE).setGuiInterface(new FurnaceGuiInterface()).register();
        new BlockWrapper(Blocks.HOPPER).setGuiInterface(new HopperGuiInterface()).register();
        //TODO: Command Block Wrapper
        //new BlockWrapper(Blocks.COMMAND_BLOCK).setGuiInterface(new CommandGuiInterface()).register();
        //TODO:Explosive Blocks
        //new BlockWrapper(Blocks.TNT).register();

        //Other Stuff
        new BlockWrapper(Blocks.ENDER_CHEST).setGuiInterface(new ChestGuiInterface()).register();
        new BlockWrapper(Blocks.JUKEBOX).addActionListener(new JukeBoxAction()).register();
        new BlockWrapper(Blocks.CRAFTING_TABLE).setGuiInterface(new CraftingTableGuiInterface()).register();
        new BlockWrapper(Blocks.NOTEBLOCK).register();
        new BlockWrapper(Blocks.ANVIL).setGuiInterface(new AnvilGuiInterface()).register();
        new BlockWrapper(Blocks.CAULDRON).setItemStack(new ItemStack(Items.CAULDRON), false).register();
        new BlockWrapper(Blocks.CAKE).setItemStack(new ItemStack(Items.CAKE), false).register();
        new BlockWrapper(Blocks.BREWING_STAND).setGuiInterface(new BrewStandGuiInterface())
                .setItemStack(new ItemStack(Items.BREWING_STAND), false).register();
        new BlockWrapper(Blocks.ENCHANTING_TABLE).setRenderType(RenderType.COMBO).register();
        //Maybe?
        //new BlockWrapper(Blocks.DISPENSER).register();
        //new BlockWrapper(Blocks.DROPPER).register();
        //new BlockWrapper(Blocks.STANDING_SIGN).register();
        //new BlockWrapper(Blocks.STANDING_BANNER).register();
    }
}
