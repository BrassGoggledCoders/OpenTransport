package xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportRegistries;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier.CapabilityBlockCarrier.BLOCK_CARRIER_ITEM;

public class ItemBlockCarrierProvider implements ICapabilityProvider {
    private IBlockCarrier blockCarrier;

    public ItemBlockCarrierProvider(ItemStack itemStack) {
        CarriedBlock carriedBlock = OpenTransportRegistries.CARRIED_BLOCKS.getValue(itemStack.getMetadata());
        if (carriedBlock != null) {
            blockCarrier = new BlockCarrier(carriedBlock.getNewInstance());
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == BLOCK_CARRIER_ITEM && blockCarrier != null;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? BLOCK_CARRIER_ITEM.cast(blockCarrier) : null;
    }
}
