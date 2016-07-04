package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityCargoSlip {
	@CapabilityInject(ICargoSlip.class)
	public static Capability<ICargoSlip> CARGO_SLIP_CAP;

	public static void register() {
		CapabilityManager.INSTANCE.register(ICargoSlip.class, new Capability.IStorage<ICargoSlip>() {
			@Override
			public NBTBase writeNBT(Capability<ICargoSlip> capability, ICargoSlip instance, EnumFacing side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<ICargoSlip> capability, ICargoSlip instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound nbtTagCompound = (NBTTagCompound)nbt;
				instance.deserializeNBT(nbtTagCompound);
			}
		}, CargoSlipHandler::new);
	}
}
