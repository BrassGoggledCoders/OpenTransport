package xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ManaCapability {
    @CapabilityInject(IManaHolder.class)
    public static Capability<IManaHolder> CARGO_SHIP_CAP;

    public static void register() {
        CapabilityManager.INSTANCE.register(IManaHolder.class, new Capability.IStorage<IManaHolder>() {
            @Override
            public NBTBase writeNBT(Capability<IManaHolder> capability, IManaHolder instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IManaHolder> capability, IManaHolder instance, EnumFacing side, NBTBase nbt) {
                NBTTagCompound nbtTagCompound = (NBTTagCompound) nbt;
                instance.deserializeNBT(nbtTagCompound);
            }
        }, ManaHolder::new);
    }
}
