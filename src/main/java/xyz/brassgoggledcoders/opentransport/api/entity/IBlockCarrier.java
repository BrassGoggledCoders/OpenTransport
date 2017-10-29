package xyz.brassgoggledcoders.opentransport.api.entity;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.block.ICarriedBlock;

public interface IBlockCarrier {
    ICarriedBlock getBlock();

    World getWorld();
}
