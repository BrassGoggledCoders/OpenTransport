package xyz.brassgoggledcoders.opentransport.api.wrappers.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class WorldWrapper extends World {
    private IWorldHarness worldHarness;
    private BlockPos originPos = new BlockPos(0, 0, 0);

    public WorldWrapper(IWorldHarness worldHarness) {
        this(worldHarness.getRealWorld(), worldHarness);
    }

    protected WorldWrapper(World world, IWorldHarness worldHarness) {
        super(world.getSaveHandler(), world.getWorldInfo(), world.provider, world.profiler, world.isRemote);
        this.worldHarness = worldHarness;
    }

    @Override
    @Nonnull
    protected IChunkProvider createChunkProvider() {
        return chunkProvider;
    }

    @Override
    protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
        return true;
    }

    //Enderchest use this for open and close
    @Override
    public void addBlockEvent(@Nonnull BlockPos pos, Block blockIn, int eventID, int eventParam) {
        this.getBlockWrapper().getBlockState().onBlockEventReceived(this, pos, eventID, eventParam);
    }

    @Override
    public boolean isSideSolid(@Nonnull BlockPos blockPos, @Nonnull EnumFacing blockSide) {
        return false;
    }

    @Override
    public TileEntity getTileEntity(@Nonnull BlockPos blockPos) {
        if (blockPos.equals(originPos)) {
            return this.getBlockWrapper().getTileEntity();
        }
        return null;
    }

    @Override
    public Entity getEntityByID(int id) {
        return this.getWorld().getEntityByID(id);
    }

    //Most Blocks use this.
    @Override
    @Nonnull
    public IBlockState getBlockState(@Nonnull BlockPos blockPos) {
        if (blockPos.equals(originPos)) {
            return this.getBlockWrapper().getBlockState();
        }
        return Blocks.AIR.getDefaultState();
    }

    //Enderchest Particles
    @Override
    public void spawnParticle(EnumParticleTypes enumParticleType, double posX, double posY, double posZ, double velX,
                              double velY, double velZ, @Nonnull int... what) {
        this.getWorld().spawnParticle(enumParticleType, posX + this.getPosX(), posY + this.getPosY(), posZ +
                this.getPosZ(), velX, velY, velZ);
    }

    //Infinitato tries to get Entities and add potion effects
    //TODO: Actually get the right AABB
    @Override
    @Nonnull
    public <T extends Entity> List<T> getEntitiesWithinAABB(@Nonnull Class<? extends T> entityClass,
                                                            @Nonnull AxisAlignedBB axisAlignedBB) {
        return this.getWorld().getEntitiesWithinAABB(entityClass, axisAlignedBB);
    }

    //Infinitato creates explosions when it lands
    @Override
    @Nonnull
    public Explosion createExplosion(Entity entity, double posX, double posY, double posZ, float size, boolean damage) {
        return this.getWorld().createExplosion(entity, this.getPosX(), this.getPosY(), this.getPosZ(), size, damage);
    }

    //Shia Labouef tiny potato screams "Just do it"
    @Override
    public void playSound(@Nullable EntityPlayer player, BlockPos pos, @Nonnull SoundEvent sound,
                          @Nonnull SoundCategory category, float volume, float pitch) {
        this.getWorld().playSound(player, this.getBlockPos(), sound, category, volume, pitch);
    }

    @Override
    @Nonnull
    public Chunk getChunkFromChunkCoords(int chunkX, int chunkZ) {
        return this.getWorld().getChunkFromChunkCoords(chunkX, chunkZ);
    }

    @Override
    public void markChunkDirty(@Nonnull BlockPos pos, @Nonnull TileEntity tileEntity) {
        this.getBlockWrapper().markDirty();
    }

    @Override
    public boolean isBlockPowered(BlockPos pos) {
        return this.getWorldHarness().getRedstonePower();
    }

    @Override
    public boolean setBlockToAir(@Nonnull BlockPos pos) {
        if (pos.equals(BlockPos.ORIGIN)) {
            this.getWorldHarness().setBlockToAir();
            return true;
        }
        return false;
    }

    @Override
    public boolean setBlockState(BlockPos pos, @Nonnull IBlockState newState, int flags) {
        if (BlockPos.ORIGIN.equals(pos)) {
            this.getBlockWrapper().alterBlockState(newState);
        }
        return true;
    }

    @Override
    public boolean spawnEntity(@Nonnull Entity entity) {
        entity.posX += this.getPosX();
        entity.posY += this.getPosY();
        entity.posZ += this.getPosZ();
        return this.getWorld().spawnEntity(entity);
    }

    public IBlockWrapper getBlockWrapper() {
        return this.getWorldHarness().getBlockWrapper();
    }

    @Override
    @Nonnull
    public WorldInfo getWorldInfo() {
        return this.getWorld().getWorldInfo();
    }

    @Override
    public boolean isRaining() {
        return this.getWorld().isRaining();
    }

    public IWorldHarness getWorldHarness() {
        return this.worldHarness;
    }

    public double getPosX() {
        return this.getWorldHarness().getPosX();
    }

    public double getPosY() {
        return this.getWorldHarness().getPosY();
    }

    public double getPosZ() {
        return this.getWorldHarness().getPosZ();
    }

    public BlockPos getBlockPos() {
        return new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ());
    }

    public World getWorld() {
        return this.worldHarness.getRealWorld();
    }

    public void notifyBlocks() {
        this.observedNeighborChanged(BlockPos.ORIGIN, this.getBlockState(BlockPos.ORIGIN).getBlock(), BlockPos.ORIGIN);
    }
}
