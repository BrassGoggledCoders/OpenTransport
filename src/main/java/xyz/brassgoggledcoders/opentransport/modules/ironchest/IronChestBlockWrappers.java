package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

public class IronChestBlockWrappers {
    public static void preInit(FMLPreInitializationEvent event) {
        //Minus 1 cause no wood
        Block ironChest = IronChest.ironChestBlock;
        for (int i = 0; i < IronChestType.values().length - 1; i++) {
            IronChestType type = IronChestType.values()[i];
            new BlockWrapperBase(ironChest).setRenderType(RenderType.TESR)
                    .setUnlocalizedName(ironChest.getUnlocalizedName() + "." + type.toString().toLowerCase())
                    .setBlockState(ironChest.getDefaultState().withProperty(BlockIronChest.VARIANT_PROP, type))
                    .setGuiInterface(new IronChestInterface()).register();
        }
    }
}
