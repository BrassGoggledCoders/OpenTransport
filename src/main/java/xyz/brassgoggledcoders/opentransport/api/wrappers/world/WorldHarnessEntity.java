package xyz.brassgoggledcoders.opentransport.api.wrappers.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

public class WorldHarnessEntity implements IWorldHarness {
    private IHolderEntity entity;

    public WorldHarnessEntity(IHolderEntity entity) {
        this.entity = entity;
    }


    @Override
    public IBlockWrapper getBlockWrapper() {
        return this.entity.getBlockWrapper();
    }

    @Override
    public World getRealWorld() {
        return this.entity.getEntity().getEntityWorld();
    }

    @Override
    public void setBlockToAir() {
        this.entity.getEntity().setDead();
        if(!this.getRealWorld().isRemote) {
            Entity emptyEntity = this.entity.getEmptyEntity();
            this.getRealWorld().spawnEntity(emptyEntity);
        }
    }

    @Override
    public boolean getRedstonePower() {
        return this.entity.getRedstonePower();
    }

    @Override
    public double getPosX() {
        return this.entity.getEntity().posX;
    }

    @Override
    public double getPosY() {
        return this.entity.getEntity().posY;
    }

    @Override
    public double getPosZ() {
        return this.entity.getEntity().posZ;
    }
}
