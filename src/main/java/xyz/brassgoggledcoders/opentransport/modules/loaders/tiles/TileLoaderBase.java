package xyz.brassgoggledcoders.opentransport.modules.loaders.tiles;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.blocks.properties.SideType;
import com.teamacronymcoders.base.tileentities.TileEntitySidedBase;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public abstract class TileLoaderBase extends TileEntitySidedBase implements ITickable {
    private List<WeakReference<Entity>> foundEntities = Lists.newArrayList();
    private int timeSinceFoundEntities = 0;

    public void onLoad() {
        timeSinceFoundEntities = this.getWorld().rand.nextInt(5);
    }

    @Override
    public void update() {
        if (timeSinceFoundEntities-- <= 0) {
            foundEntities.clear();
            List<Entity> entities = this.getWorld().getEntities(Entity.class, Entity::isEntityAlive);
            entities.forEach(entity -> foundEntities.add(new WeakReference<>(entity)));
        }
        for (EnumFacing facing : EnumFacing.values()) {
            SideType sideType = this.getSideValue(facing.ordinal());
            BlockPos offset = this.getPos().offset(facing);
            if (sideType != SideType.NONE) {
                ICapabilityProvider provider = null;
                if (this.getWorld().isBlockLoaded(offset)) {
                    TileEntity tileEntity = this.getWorld().getTileEntity(offset);
                    if (tileEntity != null) {
                        provider = tileEntity;
                    }

                    if (provider == null) {
                        Iterator<WeakReference<Entity>> entities = foundEntities.iterator();
                        while(entities.hasNext() && provider == null) {
                            WeakReference<Entity> weakReference = entities.next();
                            Entity entity = weakReference.get();
                            if (entity != null) {
                                if (entity.getPosition().equals(offset)) {
                                    provider = entity;
                                }
                            } else {
                                entities.remove();
                            }
                        }
                    }
                }
                if (provider != null) {
                    if (this.canInteractWith(provider, facing.getOpposite())) {
                        this.interactWith(provider, facing.getOpposite(), sideType == SideType.OUTPUT);
                    }
                }

            }
        }
    }

    public abstract boolean canInteractWith(ICapabilityProvider provider, EnumFacing enumFacing);

    public abstract void interactWith(ICapabilityProvider provider, EnumFacing enumFacing, boolean loading);
}
