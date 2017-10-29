package xyz.brassgoggledcoders.opentransport.api.world;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import xyz.brassgoggledcoders.opentransport.api.entity.IBlockCarrier;

public class WorldWrapper extends World {


    public WorldWrapper(IBlockCarrier blockCarrier, World world) {
        super(world.getSaveHandler(), world.getWorldInfo(), world.provider, world.profiler, world.isRemote);
    }

    @Override
    protected IChunkProvider createChunkProvider() {
        return chunkProvider;
    }

    @Override
    protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
        return false;
    }
}
