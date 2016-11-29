package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.block.Block;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.BlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IHasWrappers;
import xyz.brassgoggledcoders.opentransport.modules.ironchest.interfaces.IronChestInterface;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;

public class IronChestBlockWrappers implements IHasWrappers {
    @Override
    public void registerWrappers() {
        Block ironChest = IronChest.ironChestBlock;
        for(IronChestType type: IronChestType.values()) {
            if(type != IronChestType.WOOD) {
                new BlockWrapper(ironChest).withProperty(BlockIronChest.VARIANT_PROP, type)
                        .setRenderType(RenderType.TESR).setGuiInterface(new IronChestInterface()).register();
            }
        }
    }
}
