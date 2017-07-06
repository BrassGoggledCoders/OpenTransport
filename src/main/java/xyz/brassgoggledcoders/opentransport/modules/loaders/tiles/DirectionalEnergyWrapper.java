package xyz.brassgoggledcoders.opentransport.modules.loaders.tiles;

import com.teamacronymcoders.base.blocks.properties.SideType;
import net.minecraftforge.energy.IEnergyStorage;

public class DirectionalEnergyWrapper implements IEnergyStorage {
    private SideType sideType;
    private IEnergyStorage energyStorage;

    public DirectionalEnergyWrapper(SideType sideType, IEnergyStorage energyStorage) {
        this.sideType = sideType;
        this.energyStorage = energyStorage;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return this.canReceive() ? this.energyStorage.receiveEnergy(maxReceive, simulate) : 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return this.canExtract() ? this.energyStorage.extractEnergy(maxExtract, simulate) : 0;
    }

    @Override
    public int getEnergyStored() {
        return this.energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return this.energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return sideType == SideType.OUTPUT;
    }

    @Override
    public boolean canReceive() {
        return sideType == SideType.INPUT;
    }
}
