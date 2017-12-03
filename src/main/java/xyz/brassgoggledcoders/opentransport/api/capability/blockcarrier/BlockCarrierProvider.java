package xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier.CapabilityBlockCarrier.BLOCK_CARRIER_ITEM;

public class BlockCarrierProvider implements ICapabilityProvider {
    private IBlockCarrier blockCarrier;

    public BlockCarrierProvider(IBlockCarrier blockCarrier) {
        this.blockCarrier = blockCarrier;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == BLOCK_CARRIER_ITEM;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? BLOCK_CARRIER_ITEM.cast(blockCarrier) : null;
    }
}
