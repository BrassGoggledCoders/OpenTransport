package xyz.brassgoggledcoders.opentransport.api.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;

public interface IBlockCarrier<T> {
    CarriedBlockInstance getCarriedBlock();

    World getWorld();

    T getCarrier();

    BlockPos getPosition();
}
