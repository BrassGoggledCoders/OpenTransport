package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.blocks.BlockWrapperBase;
import xyz.brassgoggledcoders.opentransport.interactions.BlockActivationInteraction;
import xyz.brassgoggledcoders.opentransport.registries.BlockWrapperRegistry;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

public class IronChestBlockWrappers {
    public static void preInit(FMLPreInitializationEvent event) {
        //Minus 1 cause no wood
        IBlockWrapper[] IRON_CHESTS = new IBlockWrapper[IronChestType.values().length - 1];
        Block ironChest = IronChest.ironChestBlock;
        for (int i = 0; i < IRON_CHESTS.length; i++) {
            IronChestType type = IronChestType.values()[i];
            IRON_CHESTS[i] = new BlockWrapperBase(ironChest).setRenderType(RenderType.TESR)
                    .setUnlocalizedName(ironChest.getUnlocalizedName() + "." + type.toString().toLowerCase())
                    .setBlockState(ironChest.getDefaultState().withProperty(BlockIronChest.VARIANT_PROP, type))
                    .setGuiInterface(new IronChestInterface()).setClickInteraction(new BlockActivationInteraction());
            BlockWrapperRegistry.registerContainer(IRON_CHESTS[i]);
        }
        IronChestModule.IRON_CHESTS = IRON_CHESTS;
    }
}
