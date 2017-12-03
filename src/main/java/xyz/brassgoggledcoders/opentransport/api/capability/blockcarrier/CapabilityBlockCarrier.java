package xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityBlockCarrier {
    @CapabilityInject(IBlockCarrier.class)
    public static Capability<IBlockCarrier> BLOCK_CARRIER_ITEM = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IBlockCarrier.class, new Capability.IStorage<IBlockCarrier>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IBlockCarrier> capability, IBlockCarrier instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IBlockCarrier> capability, IBlockCarrier instance, EnumFacing side, NBTBase nbt) {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, BlockCarrier::new);
    }
}
