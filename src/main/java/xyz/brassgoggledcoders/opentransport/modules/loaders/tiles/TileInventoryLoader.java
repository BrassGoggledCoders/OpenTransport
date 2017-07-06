package xyz.brassgoggledcoders.opentransport.modules.loaders.tiles;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class TileInventoryLoader extends TileLoaderBase {
    ItemStackHandler itemStackHandler = new ItemStackHandler(5);

    @Override
    public boolean canInteractWith(ICapabilityProvider provider, EnumFacing enumFacing) {
        return provider.hasCapability(ITEM_HANDLER_CAPABILITY, enumFacing);
    }

    @Override
    public void interactWith(ICapabilityProvider provider, EnumFacing enumFacing, boolean loading) {
        IItemHandler otherInventory = provider.getCapability(ITEM_HANDLER_CAPABILITY, enumFacing);
        if (loading) {

        } else {

        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == ITEM_HANDLER_CAPABILITY ? ITEM_HANDLER_CAPABILITY.cast(itemStackHandler) : super.getCapability(capability, facing);
    }
}
