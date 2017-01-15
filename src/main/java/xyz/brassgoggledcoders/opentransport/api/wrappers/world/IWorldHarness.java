package xyz.brassgoggledcoders.opentransport.api.wrappers.world;

import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

public interface IWorldHarness {
    IBlockWrapper getBlockWrapper();

    World getRealWorld();

    void setBlockToAir();

    boolean getRedstonePower();

    double getPosX();

    double getPosY();

    double getPosZ();
}
