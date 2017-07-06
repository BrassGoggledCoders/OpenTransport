package xyz.brassgoggledcoders.opentransport.modules.loaders.tiles;

import com.teamacronymcoders.base.blocks.properties.SideType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEnergyLoader extends TileLoaderBase {
    @CapabilityInject(IEnergyStorage.class)
    public static Capability<IEnergyStorage> ENERGY_STORAGE;

    private IEnergyStorage energyStorage = new EnergyStorage(100000, 1000);

    @Override
    public boolean canInteractWith(ICapabilityProvider provider, EnumFacing enumFacing) {
        return provider.hasCapability(ENERGY_STORAGE, enumFacing);
    }

    @Override
    public void interactWith(ICapabilityProvider provider, EnumFacing enumFacing, boolean loadingOther) {
        IEnergyStorage otherEnergyStorage = provider.getCapability(ENERGY_STORAGE, enumFacing);
        if (otherEnergyStorage != null) {
            IEnergyStorage pullFrom;
            IEnergyStorage pushTo;
            if (loadingOther) {
                pullFrom = this.energyStorage;
                pushTo = otherEnergyStorage;
            } else {
                pullFrom = otherEnergyStorage;
                pushTo = this.energyStorage;
            }
            int pulledAmount = pullFrom.extractEnergy(1000, true);
            if (pulledAmount > 0) {
                int pushedAmount = pushTo.receiveEnergy(pulledAmount, false);
                if (pushedAmount > 0) {
                    pullFrom.extractEnergy(pushedAmount, false);
                }
            }
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        boolean hasCap = false;
        if (capability == ENERGY_STORAGE) {
            hasCap = facing == null || this.getSideValue(facing.ordinal()) != SideType.NONE;
        }
        return hasCap || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        IEnergyStorage storage = null;
        if (capability == ENERGY_STORAGE) {
            if (facing != null) {
                storage = new DirectionalEnergyWrapper(this.getSideValue(facing.ordinal()), this.energyStorage);
            } else {
                storage = this.energyStorage;
            }
        }
        return storage != null ? ENERGY_STORAGE.cast(storage) : super.getCapability(capability, facing);
    }

    @Override
    public void readFromDisk(NBTTagCompound data) {
        int energyAmount = 0;
        if (data.hasKey("energy_amount")) {
            energyAmount = data.getInteger("energy_amount");
        }
        this.energyStorage = new EnergyStorage(100000, 1000, 1000, energyAmount);
        super.readFromDisk(data);
    }

    @Override
    public NBTTagCompound writeToDisk(NBTTagCompound data) {
        data.setInteger("energy_amount", this.energyStorage.getEnergyStored());
        return super.writeToDisk(data);
    }
}
