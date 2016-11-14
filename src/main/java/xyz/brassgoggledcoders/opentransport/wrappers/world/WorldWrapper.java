package xyz.brassgoggledcoders.opentransport.wrappers.world;

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
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class WorldWrapper extends World {
    private IHolderEntity entity;
    private BlockPos originPos = new BlockPos(0, 0, 0);

    public WorldWrapper(IHolderEntity entity) {
        this(entity.getEntity().worldObj, entity);
    }

    protected WorldWrapper(World world, IHolderEntity entity) {
        super(world.getSaveHandler(), world.getWorldInfo(), world.provider, world.theProfiler, world.isRemote);
        this.entity = entity;
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
        this.getWorld().spawnParticle(enumParticleType,posX + this.getPosX(), posY + this.getPosY(), posZ +
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
        this.getWorld().playSound(player, this.getEntity().getPosition(), sound, category, volume, pitch);
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
        return this.getHolderEntity().getRedstonePower();
    }

    public Entity getEntity() {
        return this.getHolderEntity().getEntity();
    }

    public IHolderEntity getHolderEntity() {
        return this.entity;
    }

    public IBlockWrapper getBlockWrapper() {
        return this.getHolderEntity().getBlockWrapper();
    }

    public double getPosX() {
        return this.getEntity().posX;
    }

    public double getPosY() {
        return this.getEntity().posY;
    }

    public double getPosZ() {
        return this.getEntity().posZ;
    }

    public World getWorld() {
        return this.getEntity().worldObj;
    }

    public void notifyBlocks() {
        this.notifyBlockOfStateChange(BlockPos.ORIGIN, this.getBlockState(BlockPos.ORIGIN).getBlock());
    }
}
