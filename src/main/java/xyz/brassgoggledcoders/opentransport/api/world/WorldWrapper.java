package xyz.brassgoggledcoders.opentransport.api.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.entity.IBlockCarrier;

public abstract class WorldWrapper extends World {
    private IBlockCarrier<Entity> blockCarrier;

    public WorldWrapper(World world) {
        super(world.getSaveHandler(), world.getWorldInfo(), world.provider, world.profiler, world.isRemote);
    }

    public BlockPos getActualPos(BlockPos blockPos) {
        BlockPos carrierPos = this.getBlockCarrierPos();
        int actualX = carrierPos.getX() + blockPos.getX();
        int actualY = carrierPos.getY() + blockPos.getY();
        int actualZ = carrierPos.getZ() + blockPos.getZ();
        return new BlockPos(actualX, actualY, actualZ);
    }

    public BlockPos getBlockCarrierPos() {
        return this.getBlockCarrier().getPosition();
    }

    public World getWorld() {
        return this.getBlockCarrier().getWorld();
    }

    public IBlockCarrier<Entity> getBlockCarrier() {
        return blockCarrier;
    }

    public void setBlockCarrier(IBlockCarrier<Entity> blockCarrier) {
        this.blockCarrier = blockCarrier;
    }
}
